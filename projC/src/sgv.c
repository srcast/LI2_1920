#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

#include "../include/const.h"
#include "../include/catclientes.h"
#include "../include/catprod.h"
#include "../include/faturacao.h"
#include "../include/filial.h"
#include "../include/sgv.h"

#define MAXBUFSIZE 256
#define TODAS 0
#define NORMAL 0
#define PROMO 1

struct sgv {
    CatClientes cat_c;
	CatProd cat_p;
    Faturacao fat;
	Filial filiais[N_FILIAIS];
	Info info;
};

struct info {
	int clientes_lidos, clientes_validos;
	int produtos_lidos, produtos_validos;
	int vendas_lidas, vendas_validas;
	char *clientes;
	char *produtos;
	char *vendas;
	bool err; /* true caso os ficheiros tenham sido lidos com sucesso, false caso contrário */
};

struct query3 {
	int nVendas[N_FILIAIS][TIPOS_VENDA];
	int nVendasGlobal[TIPOS_VENDA];
	double totalFaturado[N_FILIAIS][TIPOS_VENDA];
	double totalFaturadoGlobal[TIPOS_VENDA];
};

struct query8 {
	int nVendas;
	double totalFaturado;
};

struct query11 {
	GPtrArray* produtos;
	int nCompradores[N_FILIAIS];
	int nVendas[N_FILIAIS];
};

static void initInfo(Info r) {
	if (r) {
		r->clientes_lidos = 0;
		r->clientes_validos = 0;
		r->produtos_lidos = 0;
		r->produtos_validos = 0;
		r->vendas_lidas = 0;
		r->vendas_validas = 0;
		r->err = true;
	}
}

static void destroyInfo(Info r) {
	if (r) {
		free(r->clientes);
		free(r->produtos);
		free(r->vendas);
	}
	free(r);
}

int getClientesValidos(SGV sgv) {
	return  sgv->info->clientes_validos;
}

int getClientesLidos(SGV sgv) {
	return sgv->info->clientes_lidos;
}

char* getClientesFileName(SGV sgv) {
	return strdup(sgv->info->clientes);
}

int getProdutosValidos(SGV sgv) {
	return sgv->info->produtos_validos;
}

int getProdutosLidos(SGV sgv) {
	return sgv->info->produtos_lidos;
}

char* getProdutosFileName(SGV sgv) {
	return strdup(sgv->info->produtos);
}

int getVendasValidas(SGV sgv) {
	return sgv->info->vendas_validas;
}

int getVendasLidas(SGV sgv) {
	return sgv->info->vendas_lidas;
}

char* getVendasFileName(SGV sgv) {
	return strdup(sgv->info->vendas);
}

bool getFilesSuccess(SGV sgv) {
	return sgv->info->err;
}

/* QUERY 3 */
int* getNVendasGlobalQuery3(QUERY3 r) {
    return r->nVendasGlobal;
}

int** getNVendasQuery3(QUERY3 r) {
    return r->nVendas;
}

double* getTotalFaturadoGlobalQuery3(QUERY3 r) {
    return r->totalFaturadoGlobal;
}

double** getTotalFaturadoQuery3(QUERY3 r) {
    return r->totalFaturado;
}

void destroyQuery3(QUERY3 r) {
    free(r);
}

int getNVendasQuery8(QUERY8 r) {
	return r->nVendas;
}

double getTotalFaturadoQuery8(QUERY8 r) {
	return r->totalFaturado;
}

void destroyQuery8(QUERY8 r) {
	free(r);
}

GPtrArray* getProdutosQuery11(QUERY11 r) {
	return r->produtos;
}

void destroyQuery11(QUERY11 r) {
	if(r)
		g_ptr_array_free(r->produtos, TRUE);
	free(r);
}

/* Leitura dos ficheiros */
static bool readClientes(SGV sgv, char *filename) {
	char buffer[MAXBUFSIZE];
	bool r;
	sgv->info->clientes = strdup(filename);
	FILE *fp_clientes = fopen(filename, "r");
	if (!fp_clientes) r = false;
	else {
		r = true;
		while (fgets(buffer, MAXBUFSIZE, fp_clientes)) {
			char *token = strtok(buffer, "\r\n");
			sgv->info->clientes_lidos++;
			if (validaCliente(token)) {
				sgv->info->clientes_validos++;
				Cliente c = mkCliente(token);
				insereClienteCat(sgv->cat_c, c);
			}
		}
		fclose(fp_clientes);
	}
	return r;
}


static bool readProdutos(SGV sgv, char *filename) {
	char buffer[MAXBUFSIZE];
	bool r;
	sgv->info->produtos = strdup(filename);
	FILE *fp_produtos = fopen(filename, "r");
	if (!fp_produtos) r = false;
	else {
		r = true;
		while (fgets(buffer, MAXBUFSIZE, fp_produtos)) {
			char *token = strtok(buffer, "\r\n");
			sgv->info->produtos_lidos++;
			if (validaProduto(token)) {
				sgv->info->produtos_validos++;
				Produto p = mkProduto(token);
				insereProdutoCat(sgv->cat_p, p);
                insereProdutoFaturacao(sgv->fat, p);
			}
		}
		fclose(fp_produtos);
	}
	return r;
}

static bool readVendas(SGV sgv, char* filename) {
	char buffer[MAXBUFSIZE];
	bool r;
	sgv->info->vendas = strdup(filename);
	FILE* fp_vendas = fopen(filename, "r");
	if(fp_vendas == NULL) r = false;
	else {
		r = true;
		while(fgets(buffer, MAXBUFSIZE, fp_vendas)) {
			sgv->info->vendas_lidas++;
			char* token = strtok(buffer, "\r\n"); 
			if(validaVenda(sgv->cat_p, sgv->cat_c, token)) {
				sgv->info->vendas_validas++;
				Venda v = mkVenda(token);
				insereVendaFaturacao(sgv->fat, v);
				insereVendaFilial(sgv->filiais, v);
				destroyVenda(v);
			}
		}
		fclose(fp_vendas);
	}
	return r;
}

/* QUERY 1 */
SGV initSGV() {
    SGV sgv = (SGV) malloc(sizeof(struct sgv));
    if (sgv) {
        sgv->cat_c = initCatClientes();
        sgv->cat_p = initCatProd();
        sgv->fat = initFaturacao();
        for (int i = 0; i < N_FILIAIS; i++)
            sgv->filiais[i] = initFilial();
        sgv->info = (Info) malloc(sizeof(struct info));
    }
    return sgv;
}

SGV loadSGVFromFiles(SGV sgv, char *clientesFilePath, char *productsFilePath, char *salesFilePath) {
	if (sgv->info) initInfo(sgv->info);
	bool c = readClientes(sgv, clientesFilePath);
	bool p = readProdutos(sgv, productsFilePath);
	bool v = readVendas(sgv, salesFilePath);
	sgv->info->err = c && p && v;
	return sgv;
}

void destroySGV(SGV sgv) {
	if (sgv) {		
		destroyCatClientes(sgv->cat_c);
		destroyCatProd(sgv->cat_p);
		destroyFaturacao(sgv->fat);
		for (int i = 0; i < N_FILIAIS; i++)
			destroyFilial(sgv->filiais[i]);
		destroyInfo(sgv->info);
	}
	free(sgv);
}

/* QUERY 2 */
GPtrArray *getProductsStartedByLetter(SGV sgv, char letter) {
	GPtrArray *arr = getProdutosLetra(sgv->cat_p, letter);
	return arr;
}

/* QUERY 3 */
QUERY3 getProductSalesAndProfit(SGV sgv, char *productID, int month) {
	QUERY3 r = (QUERY3) malloc(sizeof(struct query3));
	if(r) {
		for(int i=0; i<TIPOS_VENDA; i++) {
			r->nVendasGlobal[i] = 0;
			r->totalFaturadoGlobal[i] = 0;
		}
		for(int i=0; i<N_FILIAIS; i++) {
			r->nVendasGlobal[NORMAL] += getNVendasMesTipoProd(sgv->fat, productID, month, 'N', i+1); 
			r->nVendasGlobal[PROMO] += getNVendasMesTipoProd(sgv->fat, productID, month, 'P', i+1);
			r->nVendas[i][NORMAL] = getNVendasMesTipoProd(sgv->fat, productID, month, 'N', i+1);
			r->nVendas[i][PROMO] = getNVendasMesTipoProd(sgv->fat, productID, month, 'P', i+1);
			r->totalFaturadoGlobal[NORMAL] += getTotalFaturadoMesTipoProd(sgv->fat, productID, month, 'N', i+1);
			r->totalFaturadoGlobal[PROMO] += getTotalFaturadoMesTipoProd(sgv->fat, productID, month, 'P', i+1);
			r->totalFaturado[i][NORMAL] = getTotalFaturadoMesTipoProd(sgv->fat, productID, month, 'N', i+1);
			r->totalFaturado[i][PROMO] = getTotalFaturadoMesTipoProd(sgv->fat, productID, month, 'N', i+1);
		}
	}
	return r;
}

/* QUERY 4 */
GPtrArray* getProductsNeverBought(SGV sgv, int branchID) {
	GPtrArray* prods = getTodosProdutos(sgv->cat_p);
	GPtrArray* arr = g_ptr_array_new_with_free_func(free);
	for(int i=0; i<prods->len; i++)
		if(ninguemComprou(sgv->fat, prods->pdata[i], branchID))
			g_ptr_array_add(arr, strdup(prods->pdata[i]));			
	g_ptr_array_free(prods, TRUE);
	return arr;
}

/* QUERY 5 */
GPtrArray* getClientsofAllBranches(SGV sgv) {
	GPtrArray* clientes = getTodosClientes(sgv->cat_c);
	GPtrArray* arr = g_ptr_array_new_with_free_func(free);
	for(int i=0; i<clientes->len; i++)
		if(clienteTodasFiliais(sgv->filiais, clientes->pdata[i]))
			g_ptr_array_add(arr, strdup(clientes->pdata[i]));
	g_ptr_array_free(clientes, TRUE);
	return arr;
}

/* QUERY 6 */
int* getClientsAndProductsNeverBoughCount(SGV sgv) {
	GPtrArray* prods = getTodosProdutos(sgv->cat_p);
	GPtrArray* clientes = getTodosClientes(sgv->cat_c);
	int* r = (int*) malloc(2 * sizeof(int));
	if(r) {
		r[0] = 0;
		r[1] = 0;
		for(int i=0; i<prods->len; i++)
			if(ninguemComprou(sgv->fat, prods->pdata[i], TODAS))
				r[0]++;
		for(int i=0; i<clientes->len; i++)
			if(naoComprador(sgv->filiais, clientes->pdata[i]))
				r[1]++;
	}
	g_ptr_array_free(prods, TRUE);
	g_ptr_array_free(clientes, TRUE);
	return r;
}

/* QUERY 7 */
int** getProductsBoughtByClient(SGV sgv, char* clientID) {
	int** r = (int**) malloc(N_MESES * sizeof(int*));
	for(int i=0; i<N_MESES; i++)
		r[i] = (int*) malloc(N_FILIAIS * sizeof(int));
	if(r) {
		for (int i=0; i<N_MESES; i++) 
			for(int j=0; j<N_FILIAIS; j++)
				r[i][j] = getClienteMesQt(clientID, (i+1), sgv->filiais[j]);
	}
	return r;
}

/* QUERY 8 */
QUERY8 getSalesAndProfit(SGV sgv, int minMonth, int maxMonth) {
	QUERY8 r = (QUERY8) malloc(sizeof(struct query8));
	if(r) {
		r->totalFaturado = 0;
		r->nVendas = 0;
		for(; minMonth <= maxMonth; minMonth++) {
			r->nVendas += getNVendasMes(sgv->fat, minMonth);
			r->totalFaturado += getTotalFaturadoMes(sgv->fat, minMonth);
		}
	}
	return r;
}

/* QUERY 9 */
GPtrArray** getProductBuyers(SGV sgv, char* productID, int branch) {
	GPtrArray** arr = (GPtrArray**) malloc(sizeof(TIPOS_VENDA * sizeof(GPtrArray*)));
	Compradores c = getCompradores(sgv->filiais[branch-1], productID);
	GPtrArray* tmpN= getCompradoresTipo(c,'N');
	GPtrArray* tmpP = getCompradoresTipo(c, 'P');
	arr[NORMAL] = g_ptr_array_new_with_free_func(free);
	arr[PROMO] = g_ptr_array_new_with_free_func(free);
	for(int i=0; i<tmpN->len; i++)
		g_ptr_array_add(arr[NORMAL], strdup(tmpN->pdata[i]));
	for(int i=0; i<tmpP->len; i++)
		g_ptr_array_add(arr[PROMO], strdup(tmpP->pdata[i]));

	destroyCompradores(c);
	return arr;
}

/* QUERY 10 */
GPtrArray* getClientFavoriteProducts(SGV sgv, char* clientID, int month) {
    GPtrArray* arr = getMaisCompradosCliente(sgv->filiais, clientID, month);
    return arr;
}
/* QUERY 11 */
QUERY11 getTopSelledProducts(SGV sgv, int limit) {
	QUERY11 r = (QUERY11) malloc(sizeof(struct query11));
	if(r) {
		r->produtos = getMaisVendidos(sgv->fat, limit);
	/*	for(int i=0; i<N_FILIAIS; i++) {
			r->nCompradores[i] = getNCompradores(sgv->filiais[i], r->produtos->pdata[i]);
			r->nVendas[i] = getNVendasProd(sgv->fat, i+1, r->produtos->pdata[i]);
		} */
	} 
	return r;
}

/* QUERY 12 */
GPtrArray* getClientTopProfitProducts(SGV sgv, char* clientID, int limit) {
	GPtrArray* arr = getNProdMaisCompradosCliente(sgv->filiais, clientID, limit);
	return arr;
}
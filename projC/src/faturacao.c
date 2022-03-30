#include <stdlib.h>
#include <string.h>

#include "../include/const.h"
#include "../include/faturacao.h"

#define NORMAL 0
#define PROMO 1

typedef struct infoproduto {
	char* codProd;
	int nVendas[N_MESES][N_FILIAIS][TIPOS_VENDA];
	double totalFaturado[N_MESES][N_FILIAIS][TIPOS_VENDA];
	int quantidadeVendida[N_MESES][N_FILIAIS][TIPOS_VENDA];
	int nVendasGlobal;
	double totalFaturadoGlobal;
	int quantidadeVendidaGlobal;
} *InfoProduto;

struct faturacao {
	double totalFaturado[N_MESES][TIPOS_VENDA];
	int nVendas[N_MESES][TIPOS_VENDA];
	GHashTable *produtos;
};

/* Função de comparação */
int cmpFat(const void* a, const void* b) {
    InfoProduto x = *(InfoProduto*) a;  
    InfoProduto y = *(InfoProduto*) b;  
    return y->quantidadeVendidaGlobal - x->quantidadeVendidaGlobal;
}

static char* getCodProd(InfoProduto info) {
	return strdup(info->codProd);
}

/* Funcções Privadas */
static InfoProduto initInfoProduto(char* codProd) {
	InfoProduto info = (InfoProduto) malloc(sizeof(struct infoproduto));
	if (info) {
		info->codProd = strdup(codProd);
		memset(info->nVendas, 0, N_MESES * N_FILIAIS * TIPOS_VENDA * sizeof(int));
		memset(info->totalFaturado, 0, N_MESES * N_FILIAIS * TIPOS_VENDA * sizeof(double));
		memset(info->quantidadeVendida, 0, N_MESES * N_FILIAIS * TIPOS_VENDA * sizeof(int));
		info->nVendasGlobal = 0;
		info->totalFaturadoGlobal = 0;
		info->quantidadeVendidaGlobal = 0;
	}
	return info;
}

static void updateInfoProduto(InfoProduto info, Venda v) {
	double totalFaturado = getTotalFaturadoVenda(v);
	int quantidade = getQuantidadeVenda(v);
	int tipo = (getTipoVenda(v) == 'N') ? NORMAL : PROMO;
	int filial = getFilialVenda(v);
	int mes = getMesVenda(v);
	info->nVendas[mes - 1][filial - 1][tipo]++;
	info->totalFaturado[mes - 1][filial - 1][tipo] += totalFaturado;
	info->quantidadeVendida[mes - 1][filial - 1][tipo] += quantidade;
	info->nVendasGlobal++;
	info->totalFaturadoGlobal += totalFaturado;
	info->quantidadeVendidaGlobal += quantidade;
}

static void destroyInfoProduto(InfoProduto info) {
	if(info)
		free(info->codProd);
	free(info);
}

Faturacao initFaturacao() {
    Faturacao fat = (Faturacao) malloc(sizeof(struct faturacao));
    if (fat) {
        memset(fat->totalFaturado, 0, N_MESES * TIPOS_VENDA * sizeof(double));
        memset(fat->nVendas, 0, N_MESES * TIPOS_VENDA * sizeof(int));
        fat->produtos = g_hash_table_new_full((GHashFunc) g_str_hash, (GEqualFunc) g_str_equal, (GDestroyNotify) free,
                                        (GDestroyNotify) destroyInfoProduto);
    }
    return fat;
}

void insereProdutoFaturacao(Faturacao fat, Produto p) {
    char *codProd = getCodProduto(p);
    g_hash_table_insert(fat->produtos, codProd, initInfoProduto(codProd));
}

void insereVendaFaturacao(Faturacao fat, Venda v) {
    char *codProd = getCodProdutoVenda(v);
    InfoProduto info = g_hash_table_lookup(fat->produtos, codProd);
    updateInfoProduto(info, v);
    free(codProd);
    int mes = getMesVenda(v);
    int totalfatvenda = getTotalFaturadoVenda(v);
    int tipo = (getTipoVenda(v) == 'N') ? NORMAL : PROMO;
    fat->nVendas[mes - 1][tipo]++;
    fat->totalFaturado[mes - 1][tipo] += totalfatvenda;
}

bool ninguemComprou(Faturacao fat, char* codProd, int filial) {
	InfoProduto info = g_hash_table_lookup(fat->produtos, codProd);
    int nVendas = 0;
    if(info) {
	    if (filial == TODAS) nVendas = info->nVendasGlobal;
	    else {
	        for (int i = 0; i < N_MESES; i++)
	            for (int j = 0; j < TIPOS_VENDA; j++)
	                nVendas += info->nVendas[i][filial - 1][j];
	    }
	}
    return (nVendas == 0);
}

int getNVendasMesTipoProd(Faturacao fat, char* codProd ,int mes, char tipo, int filial) {
	InfoProduto info = g_hash_table_lookup(fat->produtos, codProd);
	int t = (tipo == 'N') ? NORMAL : PROMO;
	int r = 0;
	if(info) {
		if(filial == TODAS) {
			for(int i=0; i<N_FILIAIS; i++)
				for(int j=0; j<TIPOS_VENDA; j++)
					r += info->nVendas[mes-1][i][j];
		} else r = info->nVendas[mes-1][filial-1][t];
	}
	return r;
}

double getTotalFaturadoMesTipoProd(Faturacao fat, char *codProd, int mes, char tipo, int filial) {
	InfoProduto info = g_hash_table_lookup(fat->produtos, codProd);
	int t = (tipo == 'N') ? NORMAL : PROMO;
	double r = 0;
	if(info) {
		if(filial == TODAS) {
			for(int i=0; i<N_FILIAIS; i++)
				for(int j=0; j<TIPOS_VENDA; j++)
					r += info->totalFaturado[mes-1][filial-1][t];
		} else r = info->totalFaturado[mes-1][filial-1][t];
	}
	return r;
}

int getNVendasMes(Faturacao fat, int mes) {
	int r=0;
	for(int i=0; i<TIPOS_VENDA; i++)
		r += fat->nVendas[mes-1][i];
	return r;
}

double getTotalFaturadoMes(Faturacao fat, int mes) {
	double r=0;
	for(int i=0; i<TIPOS_VENDA; i++)
		r += fat->totalFaturado[mes-1][i];
	return r;
}


static InfoProduto cloneInfo(InfoProduto info) {
	InfoProduto clone = (InfoProduto) malloc(sizeof(struct infoproduto));
	clone->codProd = strdup(info->codProd);
	clone->nVendasGlobal = info->nVendasGlobal;
	clone->totalFaturadoGlobal = info->totalFaturadoGlobal;
	clone->quantidadeVendidaGlobal = info->quantidadeVendidaGlobal;
    memcpy(clone->totalFaturado, info->totalFaturado, N_MESES * N_FILIAIS * TIPOS_VENDA * sizeof(double));
    memcpy(clone->quantidadeVendida, info->quantidadeVendida, N_MESES * N_FILIAIS * TIPOS_VENDA * sizeof(int));
    memcpy(clone->nVendas, info->nVendas, N_MESES * N_FILIAIS * TIPOS_VENDA * sizeof(int));
	return clone;
}

static void addInfoToArray(gpointer key, gpointer value, gpointer data) {
	char* codProd = (char*) key;
	InfoProduto info = (InfoProduto) value;
	GPtrArray* aux = (GPtrArray*) data;
	g_ptr_array_add(aux, cloneInfo(info));
}

GPtrArray* getMaisVendidos(Faturacao fat, int N) {
    GPtrArray *arr = g_ptr_array_new_with_free_func(free);
    GPtrArray *aux = g_ptr_array_new_with_free_func(destroyInfoProduto);
    g_hash_table_foreach(fat->produtos, (GHFunc) addInfoToArray, aux);
    g_ptr_array_sort(aux, (GCompareFunc) cmpFat);
    for(int i=0; i<N; i++)
    	g_ptr_array_add(arr, getCodProd(aux->pdata[i]));
    g_ptr_array_free(aux, TRUE);
	return arr;
}

int getNVendasProd(Faturacao fat, int fil, char *codProd) {
    int r = 0;
    InfoProduto info = g_hash_table_lookup(fat->produtos, codProd);
    for (int i = 0; i < N_MESES; i++)
        for (int j = 0; j < TIPOS_VENDA; j++)
            r += info->nVendas[i][fil - 1][j];
    return r;
}

void destroyFaturacao(Faturacao fat) {
    if (fat)
        g_hash_table_destroy(fat->produtos);
    free(fat);
}

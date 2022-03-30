#include <stdlib.h>
#include <string.h>
#include <gmodule.h>

#include "../include/const.h"
#include "../include/filial.h"

#define NORMAL 0
#define PROMO 1

struct filial {
    GHashTable *clientes;
    GHashTable *produtos;
};

struct compradores {
    GPtrArray *arr[TIPOS_VENDA];
    int quantidade[TIPOS_VENDA];
};

typedef enum {
    N, P, AMBOS
} Tipo;

typedef struct nodocliente {
    char *codCliente;
    GHashTable *produtos;
    int quantidade[N_MESES];
} *NodoCliente;

typedef struct nodoproduto {
    char *codProd;
    GHashTable *compradores;
} *NodoProduto;

typedef struct produtocliente {
    char *codProd;
    char *codCliente;
    int quantidade[N_MESES];
    double totalFaturado;
} *ProdCliente;

/* Funções de Compração */
static int cmpProdClienteTotal(const void *a, const void *b) {
    ProdCliente x = (ProdCliente) a;
    ProdCliente y = (ProdCliente) b;
    return (x->totalFaturado - y->totalFaturado);
}

static int cmpProdClienteTotalQt(const void *a, const void *b, void *c) {
    ProdCliente x = (ProdCliente) a;
    ProdCliente y = (ProdCliente) b;
    int mes = *((int *) c);
    return (y->quantidade[mes - 1] - x->quantidade[mes - 1]);
}

/* Funções privadas */
static ProdCliente initProdCliente(char *codCliente, char *codProd, int qt, double totalFaturado, int mes) {
    ProdCliente pc = (ProdCliente) malloc(sizeof(struct produtocliente));
    if (pc) {
        pc->codProd = strdup(codProd);
        pc->codCliente = strdup(codCliente);
        memset(pc->quantidade, 0, N_MESES * sizeof(int));
        pc->quantidade[mes - 1] += qt;
        pc->totalFaturado = totalFaturado;
    }
    return pc;
}

static void updateProdCliente(ProdCliente pc, int qt, int mes, double totalFaturado) {
    pc->quantidade[mes - 1] += qt;
    pc->totalFaturado += totalFaturado;
}

static void destroyProdCliente(ProdCliente pc) {
    if (pc) {
        free(pc->codCliente);
        free(pc->codProd);
    }
    free(pc);
}

static NodoCliente initNodoCliente(char *codCliente) {
    NodoCliente c = (NodoCliente) malloc(sizeof(struct nodocliente));
    if (c) {
        c->codCliente = strdup(codCliente);
        memset(c->quantidade, 0, N_MESES * sizeof(int));
        c->produtos = g_hash_table_new_full(g_str_hash, g_str_equal, NULL, (GDestroyNotify) destroyProdCliente);
    }
    return c;
}

static void updateNodoCliente(NodoCliente c, char *codProd, int qt, double totalFaturado, int mes) {
    ProdCliente pc = g_hash_table_lookup(c->produtos, codProd);
    if (!pc) {
        pc = initProdCliente(c->codCliente, codProd, qt, totalFaturado, mes);
        g_hash_table_insert(c->produtos, pc->codProd, pc);
    } else updateProdCliente(pc, qt, mes, totalFaturado);
    c->quantidade[mes - 1] += qt;
}

static void destroyNodoCliente(NodoCliente c) {
    if (c) {
        free(c->codCliente);
        g_hash_table_destroy(c->produtos);
    }
    free(c);
}

static NodoProduto initNodoProduto(char *codProd, char *codCliente, Tipo t) {
    NodoProduto p = (NodoProduto) malloc(sizeof(struct nodoproduto));
    if (p) {
        p->codProd = strdup(codProd);
        Tipo *tipo = (Tipo *) malloc(sizeof(Tipo));
        *tipo = t;
        char *cliente = strdup(codCliente);
        p->compradores = g_hash_table_new_full(g_str_hash, g_str_equal, free, free);
        g_hash_table_insert(p->compradores, cliente, tipo);
    }
    return p;
}

static void updateNodoProduto(NodoProduto p, char *codCliente, Tipo t) {
    Tipo *tipo = g_hash_table_lookup(p->compradores, codCliente);
    if (tipo && *tipo != t) *tipo = AMBOS;
    if (!tipo) {
        char *cliente = strdup(codCliente);
        tipo = malloc(sizeof(Tipo));
        *tipo = t;
        g_hash_table_insert(p->compradores, cliente, tipo);
    }
}

static void destroyNodoProduto(NodoProduto p) {
    if (p) {
        g_hash_table_destroy(p->compradores);
        free(p->codProd);
    }
    free(p);
}
static void getProdCompTipo(gpointer key, gpointer value, gpointer data) {
    char *codCliente = (char *) key;
    Tipo tipo = *((Tipo *) value);
    Compradores c = (Compradores) data;
    if (tipo != P) {
        g_ptr_array_add(c->arr[NORMAL], strdup(codCliente));
        c->quantidade[NORMAL]++;
    }
    if (tipo != N) {
        g_ptr_array_add(c->arr[PROMO], strdup(codCliente));
        c->quantidade[PROMO]++;
    }
}

Compradores getCompradores(Filial fil, char *codProd) {
    NodoProduto p = g_hash_table_lookup(fil->produtos, codProd);
    Compradores c = (Compradores) malloc(sizeof(struct compradores));
    if (c) {
        for (int i = 0; i < TIPOS_VENDA; i++) {
            c->quantidade[i] = 0;
            c->arr[i] = g_ptr_array_new_with_free_func(free);
        }
    }
    if (p)
        g_hash_table_foreach(p->compradores, (GHFunc) getProdCompTipo, c);
    return c;
}

GPtrArray* getCompradoresTipo(Compradores c, char tipo) {
    int t = (t == 'N') ? NORMAL : PROMO;
    GPtrArray* arr =  g_ptr_array_new_with_free_func(free);
    for(int i=0; i<c->arr[t]->len; i++)
        g_ptr_array_add(arr, strdup(c->arr[t]->pdata[i]));
    return arr;
}

void destroyCompradores(Compradores c) {
    if (c) {
        for (int i = 0; i < TIPOS_VENDA; i++)
            g_ptr_array_free(c->arr[i], TRUE);
    }
    free(c);
}

Filial initFilial() {
    Filial fil = (Filial) malloc(sizeof(struct filial));
    if (fil) {
        fil->clientes = g_hash_table_new_full(g_str_hash, g_str_equal, NULL, (GDestroyNotify) destroyNodoCliente);
        fil->produtos = g_hash_table_new_full(g_str_hash, g_str_equal, NULL, (GDestroyNotify) destroyNodoProduto);
    }
    return fil;
}

void insereVendaFilial(Filial fil[], Venda v) {
    int filial = getFilialVenda(v);
    char *codCliente = getCodClienteVenda(v);
    char *codProd = getCodProdutoVenda(v);
    int quantidade = getQuantidadeVenda(v);
    double totalFaturado = getTotalFaturadoVenda(v);
    int mes = getMesVenda(v);
    char t = getTipoVenda(v);
    Tipo tipo = (t == 'N') ? N : P;
    
    NodoCliente c = g_hash_table_lookup(fil[filial - 1]->clientes, codCliente);
    NodoProduto p = g_hash_table_lookup(fil[filial - 1]->produtos, codProd);
    if (!c) {
        c = initNodoCliente(codCliente);
        updateNodoCliente(c, codProd, quantidade, totalFaturado, mes);
        g_hash_table_insert(fil[filial - 1]->clientes, c->codCliente, c);
    } else updateNodoCliente(c, codProd, quantidade, totalFaturado, mes);
    if (!p) {
        p = initNodoProduto(codProd, codCliente, tipo);
        g_hash_table_insert(fil[filial - 1]->produtos, p->codProd, p);
    } else updateNodoProduto(p, codCliente, tipo);
    free(codCliente);
    free(codProd);
}

bool clienteTodasFiliais(Filial fil[], char* codCliente) {
    int r = 0;
    for(int i=0; i<N_FILIAIS; i++)
        if(g_hash_table_lookup(fil[i]->clientes, codCliente))
            r++;
    return (r == N_FILIAIS);
}

bool naoComprador(Filial fil[], char* codCliente) {
    int r = 0;
    for(int i=0; i<N_FILIAIS; i++)
        if(g_hash_table_lookup(fil[i]->clientes, codCliente))
            r++;
    return (r == 0);
}

int getClienteMesQt(char *codCliente, int mes, Filial fil) {
    NodoCliente c = g_hash_table_lookup(fil->clientes, codCliente);
    return c ? c->quantidade[mes - 1] : 0;
}

static void mergeFiliais(void *key, void *value, void *data) {
    char *codCliente = (char *) key;
    ProdCliente pc = (ProdCliente) value;
    GHashTable *prods = (GHashTable *) data;
    ProdCliente tmp = g_hash_table_lookup(prods, codCliente);
    int qt = 0;
    for (int i = 0; i < N_MESES; i++)
        qt += pc->quantidade[i]; 
    if (!tmp) {
        tmp = initProdCliente(pc->codCliente, pc->codProd, pc->quantidade[0], pc->totalFaturado, 1);
        for (int i = 1; i < N_MESES; i++)
            tmp->quantidade[i] += pc->quantidade[i];
        g_hash_table_insert(prods, strdup(codCliente), tmp);
    } else {
        tmp->totalFaturado += pc->totalFaturado;
        for (int i = 0; i < N_MESES; i++)
            tmp->quantidade[i] += pc->quantidade[i];
    }
}

GPtrArray *getMaisCompradosCliente(Filial fil[], char *codCliente, int mes) {
    GPtrArray *arr = g_ptr_array_new(); // array de resposta
    GHashTable *ht_aux = g_hash_table_new_full(g_str_hash, g_str_equal, (GDestroyNotify) free, (GDestroyNotify) destroyProdCliente); 
    GHashTableIter iterator;
    ProdCliente p;
    char *key;
    for (int i = 0; i < N_FILIAIS; i++) {
        NodoCliente c = g_hash_table_lookup(fil[i]->clientes, codCliente); 
        if (c) g_hash_table_foreach(c->produtos, (GHFunc) mergeFiliais, ht_aux); 
    }
    int tam = g_hash_table_size(ht_aux);
    g_hash_table_iter_init(&iterator, ht_aux);
    int i = 0;
    ProdCliente array[tam];
    /* The iteration order of a GHashTableIter over the keys/values in a hash table is not defined. */
    while (g_hash_table_iter_next(&iterator, (void *) key, (void *) &p))
        array[i++] = p;
    qsort_r(array, tam, sizeof(ProdCliente), cmpProdClienteTotalQt, &mes);
    for (i = 0; i < tam; i++)
        g_ptr_array_add(arr, strdup(array[i]->codProd));
    g_hash_table_destroy(ht_aux);
    return arr;
}

GPtrArray *getNProdMaisCompradosCliente(Filial fil[], char *codCliente, int N) {
    GPtrArray *arr = g_ptr_array_new();
    GHashTable *ht_aux = g_hash_table_new(g_str_hash, g_str_equal);
    GHashTableIter iterator;
    ProdCliente p;
    char *key;
    for (int i = 0; i < N_FILIAIS; i++) {
        NodoCliente c = g_hash_table_lookup(fil[i]->clientes, codCliente);
        if (c) g_hash_table_foreach(c->produtos, (GHFunc) mergeFiliais, ht_aux);
    }
    int tam = g_hash_table_size(ht_aux);
    g_hash_table_iter_init(&iterator, ht_aux);
    int i = 0;
    ProdCliente array[tam];
    /* The iteration order of a GHashTableIter over the keys/values in a hash table is not defined. */
    while (g_hash_table_iter_next(&iterator, (void *) key, (void *) &p))
        array[i++] = p;
    qsort(array, tam, sizeof(ProdCliente), cmpProdClienteTotal);
    for (i = 0; i < N && i < tam; i++)
        g_ptr_array_add(arr, strdup(array[i]->codProd));
    g_hash_table_destroy(ht_aux);
    return arr;
}

int getNCompradores(Filial fil, char *codProd) {
    NodoProduto p = g_hash_table_lookup(fil->produtos, codProd);
    return p ? g_hash_table_size(p->compradores) : 0;
}

void destroyFilial(Filial fil) {
    if (fil) {
        g_hash_table_destroy(fil->clientes);
        g_hash_table_destroy(fil->produtos);
    }
    free(fil);
}
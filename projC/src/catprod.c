#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <ctype.h>

#include "../include/const.h"
#include "../include/catprod.h"

struct produto {
    char *codProd;
};

struct catprod {
    GTree *avl[N_LETRAS];
};

Produto mkProduto(char *codProd) {
    Produto p = (Produto) malloc(sizeof(struct produto));
    if (p)
        p->codProd = strdup(codProd);
    return p;
}

bool validaProduto(char *codProd) {
    char c1, c2;
    int n;
    bool r = false;
    if (strlen(codProd) == PROD_LEN) {
        sscanf(codProd, "%c%c%d%*s", &c1, &c2, &n);
        r = isupper(c1) && isupper(c2) && n >= 1000 && n <= 9999;
    }
    return r;
}

char *getCodProduto(Produto p) {
    return strdup(p->codProd);
}

void destroyProduto(Produto p) {
    if (p)
        free(p->codProd);
    free(p);
}

CatProd initCatProd() {
    CatProd cat = (CatProd) malloc(sizeof(struct catprod));
    for(int i = 0; i<N_LETRAS; i++)
        cat->avl[i] = g_tree_new_full((GCompareFunc) g_ascii_strcasecmp, NULL, (GDestroyNotify) free, (GDestroyNotify) destroyProduto);
    return cat;
}

void insereProdutoCat(CatProd cat, Produto p) {
    char* codProd = getCodProduto(p);
    int index = codProd[0] - 'A';
    g_tree_insert(cat->avl[index], codProd, p);
}

bool existeProduto(CatProd cat, char *codProd) {
    int index = codProd[0] - 'A';
    return g_tree_lookup(cat->avl[index], codProd) != NULL;
}

static gboolean addToArray(gpointer key, gpointer value, gpointer data) {
    char* codProd = (char*) key;
    Produto p = (Produto) value;
    GPtrArray* arr = (GPtrArray*) data;
    g_ptr_array_add(arr, strdup(codProd));
    return FALSE;
}

GPtrArray *getProdutosLetra(CatProd cat, char letra) {
    int index = letra - 'A';
    GPtrArray *arr = g_ptr_array_new_with_free_func(free);
    g_tree_foreach(cat->avl[index], (GTraverseFunc) addToArray, arr);
    return arr;
}

GPtrArray *getTodosProdutos(CatProd cat) {
    GPtrArray *arr = g_ptr_array_new_with_free_func(free);
    for(int i=0; i<N_LETRAS; i++)
        g_tree_foreach(cat->avl[i], (GTraverseFunc) addToArray, arr);
    return arr;
}

void destroyCatProd(CatProd cat) {
    if (cat) {
        for (int i = 0; i < N_LETRAS; i++)
            g_tree_destroy(cat->avl[i]);
    }
    free(cat);
}

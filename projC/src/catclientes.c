#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <ctype.h>

#include "../include/const.h"
#include "../include/catclientes.h"

struct cliente {
    char *codCliente;
};

struct catclientes {
    GTree *avl[N_LETRAS];
};

Cliente mkCliente(char *codCliente) {
    Cliente c = (Cliente) malloc(sizeof(struct cliente));
    if (c)
        c->codCliente = strdup(codCliente);
    return c;
}

bool validaCliente(char *codCliente) {
    char c;
    int n;
    bool r = false;
    if (strlen(codCliente) == CLIENTE_LEN) {
        sscanf(codCliente, "%c%d%*s", &c, &n);
        r = isupper(c) && n >= 1000 && n <= 5000;
    }
    return r;
}

char *getCodigoCliente(Cliente c) {
    return strdup(c->codCliente);
}

void destroyCliente(Cliente c) {
    if (c)
        free(c->codCliente);
    free(c);
}

CatClientes initCatClientes() {
    CatClientes cat = (CatClientes) malloc(sizeof(struct catclientes));
    if (cat) {
        for (int i = 0; i < N_LETRAS; i++)
            cat->avl[i] = g_tree_new_full((GCompareFunc) g_strcmp0, NULL, (GDestroyNotify) free, (GDestroyNotify) destroyCliente);
    }
    return cat;
}

void insereClienteCat(CatClientes cat, Cliente c) {
    char *codCliente = getCodigoCliente(c);
    int index = codCliente[0] - 'A';
    g_tree_insert(cat->avl[index], codCliente, c);
}

bool existeCliente(CatClientes cat, char *codCliente) {
    int index = codCliente[0] - 'A';
    return g_tree_lookup(cat->avl[index], codCliente) != NULL;
}

static gboolean addClientToArray(gpointer key, gpointer value, gpointer data) {
    char *codCliente = (char *) key;
    Cliente c = (Cliente) value;
    GPtrArray *arr = (GPtrArray *) data;
    g_ptr_array_add(arr, strdup(codCliente));
    return FALSE;
}

GPtrArray *getTodosClientes(CatClientes cat) {
    GPtrArray *arr = g_ptr_array_new_with_free_func(free);
    for (int i = 0; i < N_LETRAS; i++)
        g_tree_foreach(cat->avl[i], (GTraverseFunc) addClientToArray, arr);
    return arr;
}

void destroyCatClientes(CatClientes cat) {
    if (cat) {
        for (int i = 0; i < N_LETRAS; i++)
            g_tree_destroy(cat->avl[i]);
    }
    free(cat);
}

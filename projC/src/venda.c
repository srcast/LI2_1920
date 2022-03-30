#include <stdlib.h>
#include <string.h>

#include "../include/const.h"
#include "../include/venda.h"

#define CAMPOSVENDA 7
#define PRECO_MIN 0
#define PRECO_MAX 999.99
#define QUANT_MIN 0
#define QUANT_MAX 200

/*
tokens[0] = codigo do produto
tokens[1] = preço unitario
tokens[2] = unidades compradas
tokens[3] = Promocao ou Normal
tokens[4] = codigo do cliente
tokens[5] = mes
tokens[6] = filial
*/

struct venda {
    char* codProd;
    char* codCliente;
    double precoUnit;
    int quantidade;
    char tipo;
    int mes;
    int filial;
};

static char** tokenizeLinhaVenda(char* venda) {
    char** campos = malloc(CAMPOSVENDA * sizeof(char*));
    char* buff = strdup(venda);
    char* f = buff;
    buff = strtok(buff, " ");
    for(int i = 0; i < CAMPOSVENDA && buff; i++) {
        campos[i] = strdup(buff);
        buff = strtok(NULL, " ");
    }
    free(f);
    return campos;
}

Venda mkVenda(char* venda) {
    Venda v = (Venda) malloc(sizeof(struct venda));
    if(venda) {
        char** campos = tokenizeLinhaVenda(venda);
        v->codProd = strdup(campos[0]);
        v->precoUnit = atof(campos[1]);
        v->quantidade = atoi(campos[2]);
        v->tipo = campos[3][0];
        v->codCliente = strdup(campos[4]);
        v->mes = atoi(campos[5]);
        v->filial = atoi(campos[6]);
        for(int i=0; i<CAMPOSVENDA; i++) free(campos[i]);
        free(campos);
    }
    return v;
}

bool validaVenda(CatProd cat_p, CatClientes cat_c,  char* venda) {
    char** tokens = tokenizeLinhaVenda(venda);
    bool r = existeProduto(cat_p, tokens[0]) &&
            atof(tokens[1]) >= PRECO_MIN && atof(tokens[1]) <= PRECO_MAX &&
            atoi(tokens[2]) > QUANT_MIN && atoi(tokens[2]) <= QUANT_MAX &&
            (*tokens[3] == 'N' || *tokens[3] == 'P') &&
            existeCliente(cat_c, tokens[4]);// &&
            atoi(tokens[5]) > 0 && atoi(tokens[5]) <= N_MESES &&
            atoi(tokens[6]) > 0 && atoi(tokens[6]) <= N_FILIAIS;
    for(int i=0; i<CAMPOSVENDA; i++) free(tokens[i]);
    free(tokens);
    return r;
}

char* getCodProdutoVenda(Venda v) {
    return strdup(v->codProd);
}

char* getCodClienteVenda(Venda v) {
    return strdup(v->codCliente);
}

double getPrecoUnitVenda(Venda v) {
    return v->precoUnit;
}

int getQuantidadeVenda(Venda v) {
    return v->quantidade;
}

char getTipoVenda(Venda v) {
    return v->tipo;
}

int getMesVenda(Venda v) {
    return v->mes;
}

int getFilialVenda(Venda v) {
    return v->filial;
}

double getTotalFaturadoVenda(Venda v) {
    return (v->quantidade * v->precoUnit);
}

void destroyVenda(Venda v) {
    if(v) {
        free(v->codCliente);
        free(v->codProd);
    }
    free(v);
}
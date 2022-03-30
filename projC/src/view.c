#include <stdio.h>
#include <ctype.h>
#include <gmodule.h>

#include "../include/const.h"
#include "../include/navegador.h"
#include "../include/view.h"

#define NORMAL 0
#define PROMO 1 

static void printArray(GPtrArray* arr) {
    if(arr->len == 0)
        printf("Lista Vazia\n");
    else {
        for(int i=0; i<arr->len; i++)
            printf("%s\n", arr->pdata[i]);
    }
}

void menu() {
    system("clear");
    printf( "_________________________________________________________________________________________\n" );
    printf( "\n\t\t\t\tMENU PRINCIPAL\n\n" );
    printf( "_________________________________________________________________________________________\n\n" );
    printf("Query 1  - Ler os 3 ficheiros\n");
    printf("Query 2  - Lista lista de produtos cujo codigo se inicia por uma dada letra\n");
    printf("Query 3  - Numero de vendas e total faturado com um produto num mes\n");
    printf("Query 4  - Lista ordenada de produtos que ninguem comprou\n");
    printf("Query 5  - Lista de clientes que compraram em todas as filiais\n");
    printf("Query 6  - Numero de produtos nao comprados e clientes que nao efetuaram compras\n");
    printf("Query 7  - Tabela de produtos comprados por um cliente, por filial\n");
    printf("Query 8  - Numero de vendas e total faturado num intervalo de meses\n");
    printf("Query 9  - Clientes que compraram um produto numa filial\n");
    printf("Query 10 - Lista de produtos mais comprados por um cliente num mes (Quantidade)\n");
    printf("Query 11 - Lista dos N produtos mais vendidos em todo o ano\n");
    printf("Query 12 - Lista dos N produtos em que um clientemais gastou dinheiro durante o ano\n");
    printf("Query 13 - Resultados da leitura dos ficheiros\n");
    printf( "_________________________________________________________________________________________\n");
    printf("Selecionar uma query (Inserir 0 para sair)\n");
}

void printTime(double t) {
    printf("Elapsed time: %f\n", t);
}

void printMensagemSaida() {
    printf("---------------------------------| Programa terminado |----------------------------------\n");
}

void printFileError() {
    printf("Erro ao ler os ficheiros\n");
}

void printEstruturasNaoInicializadas() {
    printf("Estruturas de dados nao inicializadas.\n");
    printf("Primeiro e necessario ler os ficheiros de dados e inicializar as estruturas.\n");
}


void query3outputGlobal(int* nVendas, double* totalFaturado) {
    if(nVendas) {
        printf("Numero total de vendas (Venda Normal): %d\n", nVendas[NORMAL]);
        printf("Numero total de vendas (Venda Promocao): %d\n", nVendas[PROMO]);
    }
    printf("\n");
    if(totalFaturado) {
        printf("Total Faturado (Venda Normal): %.2f\n", totalFaturado[NORMAL]);
        printf("Total Faturado (Venda Promocao): %.2f\n", totalFaturado[PROMO]);
    }
}

void query3outputFilial(int** nVendas, double** totalFaturado) {
   /* for(int i=0; i<N_FILIAIS; i++) {
        printf("Filial %d\n", i+1);
        printf("Numero total de vendas (Venda Normal): %d\n", nVendas[i][NORMAL]);
        printf("Numero total de vendas (Venda Normal): %d\n", nVendas[i][PROMO]);
        printf("Total Faturado (Venda Normal): %.2f\n", totalFaturado[i][NORMAL]);
        printf("Total Faturado (Venda Promocao): %.2f\n", totalFaturado[i][PROMO]);
        printf("\n");
    } */
    printf("Seg fault nos totais faturados e numero de vendas por filial, so da o resultado global\n");
}

void query6output(int* r) {
    printf("%d produtos nao foram comprados.\n", r[0]);
    printf("%d clientes nao efeturaram compras.\n", r[1]);
}

static void printFilialHeader(int n) {
    for(int i=0; i<n; i++)
        printf("\tFilial %d\t", i+1);
}

void query7output(int** r) {
    printFilialHeader(N_FILIAIS);
    for(int i=0; i<N_MESES; i++) {
        printf("\n");
        printf("MES %d\n", i+1);
        for(int j=0; j<N_FILIAIS; j++)
            printf("\t%d\t\t", r[i][j]);
    }
    printf("\n");
}

void query8output(int nVendas, double totalFaturado) {
    printf("Numero total de vendas: %d\n", nVendas);
    printf("Total faturado %.2f\n", totalFaturado);
}

void query9output(GPtrArray** compradores) {
    system("clear");
    printf("Compradores normal:\n");
    printf("\n");
    printArray(compradores[NORMAL]);
    printf("Compradores promocao:\n");
    printArray(compradores[PROMO]);
}

void query10output(GPtrArray* arr, int N) {
    system("clear");
    for(int i=0; i<N; i++)
        printf("%s\n", arr->pdata[i]);
}

void query11output(GPtrArray* arr, int N) {
    for(int i=0; i<N; i++)
        printf("%s\n", arr->pdata[i]);
    printf("Seg fault nos totais faturados e numero de vendas\n");
}

void query12output(GPtrArray* arr) {
    printArray(arr);
}

void query13output(char* clientes_filename, int clientes_lidos, int clientes_validos, char* produtos_filename, int produtos_lidos, int produtos_validos, char* vendas_filename, int vendas_lidas, int vendas_validas) {
    system("clear");
    printf("Ficheiro de clientes: %s\n", clientes_filename);
    printf("%d clientes lidos, %d clientes validos\n", clientes_lidos, clientes_validos);
    printf("Ficheiro de produtos: %s\n", produtos_filename);
    printf("%d produtos lidos, %d produtos validos\n", produtos_lidos, produtos_validos);
    printf("Ficheiro de vendas: %s\n", vendas_filename);
    printf("%d vendas lidas, %d vendas validas\n", vendas_lidas, vendas_validas);
}

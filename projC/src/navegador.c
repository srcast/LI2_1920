#include <stdio.h>
#include <string.h>
#include <stdbool.h>

#include "../include/const.h"
#include "../include/navegador.h"

static void printHeader(int pag, int nPag) {
    printf("---Pagina %d/%d ---\n", pag, nPag);
}

static void printFooter(int size) {
    printf("\t\t %d elementos\n", size);
    printf("\nProxima pagina -> p | Pagina anterior -> a | Sair -> s\n");
}

void navegarArray(GPtrArray *arr) {
    system("clear");
    int index = 0;
    char resposta;
    bool r = true;
    int pagina = 1;
    int fim = ELEMS_PAG;
    int tam = arr->len;
    int nPaginas = (tam / ELEMS_PAG) + 1;
    char *string = NULL;

    if(tam == 0) {
        printf("Lista vazia.\n");
        return;
    }

    do {
        printHeader(pagina, nPaginas);
        for (; index < fim; index++) {
            string = (char *) g_ptr_array_index(arr, index);
            if (string)
                printf("%s\n", string);
            else printf("Fim\n");
        }

        printFooter(tam);
        scanf("%c%*c", &resposta);
        system("clear");

        if (resposta == 'p' && pagina < nPaginas) {
            if (tam - fim < ELEMS_PAG) {
                pagina++;
                index = fim;
                fim = tam - fim;
            } else {
                pagina++;
                index = fim;
                fim += ELEMS_PAG;
            }
        } else if (resposta == 'a' && pagina > 1) {
            pagina--;
            fim = index;
            index = ELEMS_PAG;
        } else if (resposta == 's') r = false;
        else {
            printf("Opção inválida\n");
            r = false;
        }
    } while (r);
}
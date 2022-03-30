/**
 * @file filial.h
 * @brief Gestão de Filial
 *
 * Módulo de dados que, para uma Filial, contém as estruturas
 * de dados adequadas à representação dos relacionamentos entre produtos e
 * clientes, ou seja, para cada produto, saber quais os clientes
 * que o compraram, quantas unidades cada um comprou, em que mês, etc.
 * NB: Este módulo apenas faz referência a produtos que foram vendidos e
 * a clientes que efeturaram compras.
 */
#ifndef FILIAL_H
#define FILIAL_H 

#include <stdbool.h>
#include <gmodule.h>

#include "venda.h"

/**
 * @brief Declaração do tipo abstrato Filial.
 */
typedef struct filial *Filial;

/**
 * @brief Declaração do tipo abstrato Compradores.
 */
typedef struct compradores *Compradores;

/**
 * @brief Devolve uma estrutura com a informação dos compradores de um produto.
 * @param fil Filial.
 * @param codProd Código do produtp.
 * @return Estrutura com a informação dos compradores do produto.
 */
Compradores getCompradores(Filial fil, char *codProd);

/**
 * Preenche um array com os códigos dos clientes que compraram
 * um detemrinado produto, considerando apenas um certo tipo de vendas.
 * @param c Estrutura compradores.
 * @param tipo TIpo de venda (Normal/Promoção)
 * @return Array com os códigos dos clientes
 */
GPtrArray* getCompradoresTipo(Compradores c, char tipo);

/**
 * @brief Liberta espaço em memória previamente alocada para a estrutura de compradores.
 * @param c Estrutura com a informação dos compradores de um produto.
 */
void destroyCompradores(Compradores c);

/**
 * @brief Aloca espaço em memória e inicializa a estrutura de uma Filial.
 * @return Filial.
 */
Filial initFilial();

/**
 * @brief Atualiza a estrutura de uma filial com as informaçeõs de uma venda.
 * @param fil Array de filiais..
 * @param v Venda.
 */
void insereVendaFilial(Filial fil[], Venda v);

/**
 * @brief Testa se um cliente efetuou compras em todas as filiais.
 * @param fil Filiais.
 * @param codCliente Código do cliente.
 * @return true caso o cliente tenha efetuado compras em todas as filiais, false caso contrário.
 */
bool clienteTodasFiliais(Filial fil[], char* codCliente);

/**
 * @brief Testa se um cliente não efetuou compras.
 * @param fil Filiais.
 * @param codCliente Código do cliente.
 * @return true caso o cliente não tenha efetuado compras, false caso contrário.
 */
bool naoComprador(Filial fil[], char* codCliente);

/**
 * @brief Calcula a quantidade total de prodtos que um cliente comprou num determinado mês numa filial.
 * @param codCliente Código do cliente.
 * @param mes Mês.
 * @param fil Filial.
 * @return Quantidade total de produtos comprados.
 */
int getClienteMesQt(char *codCliente, int mes, Filial fil);

/**
 * @brief Preenche um array com códigos dos produtos que um cliente comprou, ordenados por ordem decrescente
 * pela quantidade comprada num mês.
 * @param fil Filiais
 * @param codCliente Código do cliente.
 * @param mes Mês.
 * @return Array com códigos dos produtos.
 */
GPtrArray *getMaisCompradosCliente(Filial fil[], char *codCliente, int mes);

/**
 * @brief Preenche um array com os códigos dos N produtos em que um determinado cliente mais
 * gastou dinheiro durante o ano.
 * @param fil Filiais.
 * @param codCliente Código do cliente.
 * @param N Valor de N.
 * @return Array com os códigos dos produtos.
 */
GPtrArray *getNProdMaisCompradosCliente(Filial fil[], char *codCliente, int N);

/**
 * @brief Determina o número de compradores de um produto, 
 * dada uma filial.
 * @param fil Filial.
 * @param codProd Código do produto.
 * @return Número do produto.
 */
int getNCompradores(Filial fil, char *codProd); 

/**
 * @brief Liberta espaço em memória previamente alocado para uma Filial.
 * @param fil Filial.
 */
void destroyFilial(Filial fil);

#endif
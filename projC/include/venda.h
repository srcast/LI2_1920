/**
 * @file venda.h
 * @brief Venda
 *
 * Módulo de tratamento de uma venda individual.
 */
#ifndef VENDA_H
#define VENDA_H

#include <stdbool.h>

#include "catclientes.h"
#include "catprod.h"

/**
 * @brief Declaração do tipo abstrato Venda.
 */
typedef struct venda *Venda;

/**
 * @brief Aloca espaço em memória e inicializa a estrutura venda.
 * @param venda String com os dados de uma venda.
 * @return Venda.
 */
Venda mkVenda(char *venda);

/**
 * @brief Testa se uma venda é válida.
 * @param cat_p Catálogo de clientes.
 * @param cat_c Catálogo de produtos.
 * @param venda String com as informações da venda.
 * @return true caso a venda seja válida, false caso contrário.
 */
bool validaVenda(CatProd cat_p, CatClientes cat_c, char *venda);

/**
 * @brief Devolve o código do produto comprado.
 * @param v Venda.
 * @return Código do produto comprado.
 */
char *getCodProdutoVenda(Venda v);

/**
 * @brief Devolve o código do cliente que efetuou a compra.
 * @param v Venda.
 * @return Código do cliente que efetuou a compra.
 */
char *getCodClienteVenda(Venda v);

/**
 * @brief Devolve o preço unitário do produto comprado.
 * @param v Venda.
 * @return Preço unitário do produto comprado
 */
double getPrecoUnitVenda(Venda v);

/**
 * @brief Devolve a quantidade comprada de um produto.
 * @param v Venda.
 * @return Quantidade comprada de um produto.
 */
int getQuantidadeVenda(Venda v);

/**
 * @brief Devolve o tipo da venda (Normal / Promoção).
 * @param v Venda.
 * @return Tipo da venda (Normal / Promoção).
 */
char getTipoVenda(Venda v);

/**
 * @brief Devolve o mês em que a venda foi efetuada.
 * @param v Venda.
 * @return Mês em que a venda foi efetuada.
 */
int getMesVenda(Venda v);

/**
 * @brief Devolve a filial onde ocorreu a venda.
 * @param v Venda.
 * @return Filial onde ocorreu a venda.
 */
int getFilialVenda(Venda v);

/**
 * @brief Calcula o total faturado numa venda.
 * @param v Venda.
 * @return Total faturado na venda.
 */
double getTotalFaturadoVenda(Venda v);

/**
 * @brief Liberta espaço em memória previamente alocado para uma venda.
 * @param v Venda.
 */
void destroyVenda(Venda v);

#endif
/**
 * @file faturacao.h
 * @brief Faturação Global
 *
 * Módulo de dados que contém as estruturas de dados responsáveis
 * pela resposta eficiente a questões quantitativas que relacionam
 * os produtos às suas vendas mensais, em modo Normal (N) ou em
 * Promoção (P), para cada um dos casos guardando o número de
 * vendas e o valor total de faturação de cada um destes tipos.
 * NB: Este módulo referencia todos os produtos, mesmo os que nunca
 * foram vendidos e não contém qualquer referência a clientes,
 * sendo, no entanto, capaz de distinguir os valores obtidos em cada filial.
 */
#ifndef FATURACAO_H
#define FATURACAO_H

#include <stdbool.h>
#include <gmodule.h>

#include "catprod.h"
#include "venda.h"

/**
 * @brief Declaração do tipo abstrato Faturação.
 */
typedef struct faturacao* Faturacao;


/**
 * @brief Aloca espaço em memória e inicializa a estrutura de faturação.
 * @return Faturação.
 */
Faturacao initFaturacao();

/**
 * @brief Insere um produto na estrutura de Faturação.
 * @param fat Faturação.
 * @param p Produto.
 */
void insereProdutoFaturacao(Faturacao fat, Produto p);

/**
 * @brief Atualiza a estrutura de Faturação com as informações de uma venda.
 * @param fat Faturação.
 * @param v Venda.
 */
void insereVendaFaturacao(Faturacao fat, Venda v);

/**
 * @brief Testa se ninguém comprou um produto numa filial.
  * @param fat Faturação.
 * @param codProd Código do produto.
 * @param filial Filial.
 * @return true caso ninguém tenha comprado o produto nessa filial, false caso contrário.
 */
bool ninguemComprou(Faturacao fat, char* codProd, int filial);

/**
 * @brief Detemrina o número total de vendas de um produto, considerando apenas vendas de um certo tipo,
 * num certo mês e numa certa filial.
 * @param fat Faturação.
 * @param codProd Código do produto.
 * @param mes Mês.
 * @param tipo Tipo de venda (Normal/Promoção).
 * @param filial Filial.
 * @return Número total de vendas.
 */
int getNVendasMesTipoProd(Faturacao fat, char *codProd, int mes, char tipo, int filial);

/**
 * @brief Detemrina o total faturado com um produto, considerando apenas vendas de um certo tipo,
 * num certo mês e numa certa filial.
 * @param fat Faturação.
 * @param codProd Código do produto.
 * @param mes Mês.
 * @param tipo Tipo de venda (Normal/Promoção).
 * @param filial Filial.
 * @return Total faturado.
 */
double getTotalFaturadoMesTipoProd(Faturacao fat, char *codProd, int mes, char tipo, int filial);

/**
 * @brief Determina o número de total de vendas num mês.
 * @param fat Faturação.
 * @param mes Mês.
 * @return Número de total de vendas.
 */
int getNVendasMes(Faturacao fat, int mes);

/**
 * @brief Determina o total faturado num mês.
 * @param fat Faturação.
 * @param mes Mês.
 * @return Total faturado.
 */
double getTotalFaturadoMes(Faturacao fat, int mes);

/**
 * @brief Liberta espaço em memória previamente alocado para uma estrutura Faturalão.
 * @param fat Faturação.
 */
void destroyFaturacao(Faturacao fat);

/**
 * @brief Determina o número de vendas de um produto numa dada filial.
 * @param fat Faturação.
 * @param fil Filial.
 * @param codProd Código do produto.
 * @return Número de produtos.
 */
int getNVendasProd(Faturacao fat, int fil, char *codProd);

/**
 * @brief Preenche um array com os dódigo dos N produtos mais vendidos.
 * @param fat Faturação.
 * @param N Valor de N.
 * @return Array com os códigos dos produtos
 */
GPtrArray* getMaisVendidos(Faturacao fat, int N);

#endif
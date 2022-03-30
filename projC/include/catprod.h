/**
 * @file catprod.h
 * @brief Catálogo de Produtos
 *
 * Módulo de dados onde são guardados os códigos válidos de todos os
 * produtos do ficheiro lido. Os códigos são organizados por índice alfabético,
 * o que permite, de forma eficaz, saber quais e quantos são os produtos
 * cujos códigos começam por uma determinada letra do alfabeto.
 */
#ifndef CAT_PROD_H
#define CA_TPROD_H

#include <stdbool.h>
#include <gmodule.h>

/**
 * @brief Declaração do tipo abstrato Produto.
 */
typedef struct produto *Produto;

/**
 * @brief Declaração do tipo abstrato CatProd.
 */
typedef struct catprod *CatProd;

/**
 * @brief Aloca espaço em memória e inicializa a estrutura produto.
 * @param codProd Código do produto
 * @return Produto.
 */
Produto mkProduto(char *codProd);

/**
 * @brief Testa se um código é válido.
 * @param codProd Código a validar.
 * @return true caso o código seja válido.
 */
bool validaProduto(char *codProd);

/**
 * @brief Devolve o código de um produto.
 * @param p Produto.
 * @return Código do produto.
 */
char *getCodProduto(Produto p);

/**
 * @brief Liberta espaço em memória previamente alocado para um produto.
 * @param p Produto
 */
void destroyProduto(Produto p);

/**
 * @brief Aloca espaço em memória e inicializa um catálogo de produtos.
 * @return Catálogo de produtos.
 */
CatProd initCatProd();

/**
 * @brief Insere um produto no catálogo.
 * @param cat Catálogo de Produtos.
 * @param p Produto a inserir.
 */
void insereProdutoCat(CatProd cat, Produto p);

/**
 * @brief Testa se um produto pertence ao catálogo.
 * @param cat Catálogo de produtos.
 * @param codProd Código do produto.
 * @return true caso o produto pertença ao catálogo, false caso contrário.
 */
bool existeProduto(CatProd cat, char *codProd);

/**
 * @brief Preenche um array com os códigos dos produtos começados por uma certa letra.
 * @param cat Catálogo de produtos.
 * @param letra Letra.
 * @return Array contendo os códigos dos produtos começados por uma certa letra.
 */
GPtrArray *getProdutosLetra(CatProd cat, char letra);

/**
 * @brief Preenche um array com os códigos de todos os produtos no catálogo.
 * @param cat Catálogo de produtos.
 * @return array com os códigos dos produtos.
 */
GPtrArray *getTodosProdutos(CatProd cat);

/**
 * @brief Liberta espaço em memória previamente alocado para um catálogo de produtos.
 * @param cat Catálogo de produtos.
 */
void destroyCatProd(CatProd cat);

#endif
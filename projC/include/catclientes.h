/**
 * @file catclientes.h
 * @brief Catálogo de Clientes
 *
 * Módulo de dados onde são guardados os códigos válidos de todos os
 * clientes do ficheiro lido.
 * NB: Os códigos são organizados por índice alfabético,
 */
#ifndef CAT_CLIENTES_H
#define CAT_CLIENTES_H

#include <stdbool.h>
#include <gmodule.h>

/**
 * @brief Declaração do tipo abstrato Cliente.
 */
typedef struct cliente *Cliente;

/**
 * @brief Declaração do tipo abstrato CatClientes.
 */
typedef struct catclientes *CatClientes;

/**
 * @brief Aloca espaço em memória e inicializa a estrutura cliente.
 * @param codCliente Código do cliente.
 * @return Cliente.
 */
Cliente mkCliente(char *codCliente);

/**
 * @brief Testa se um código é válido.
 * @param codCliente Código a validar.
 * @return true caso o código seja válido, false caso contrário.
 */
bool validaCliente(char *codCliente);

/**
 * @brief Devolve o código de um cliente.
 * @param c Cliente.
 * @return Código do cliente.
 */
char *getCodigoCliente(Cliente c);

/**
 * @brief Liberta espaço em memória previamente alocado para um cliente.
 * @param c Cliente.
 */
void destroyCliente(Cliente c);

/**
 * @brief Aloca espaço em memória e inicializa um catálogo de clientes..
 * @return Catálogo de clientes.
 */
CatClientes initCatClientes();

/**
 * @brief Insere um cliente no catálogo.
 * @param cat Catálogo de clientes.
 * @param c Cliente a inserir.
 */
void insereClienteCat(CatClientes cat, Cliente c);

/**
 * @brief Testa se um cliente pertence ao catálogo.
 * @param cat Catálogo de clientes.
 * @param codCliente Código do cliente.
 * @return true caso o produto pertença ao catálogo, false caso contrário.
 */
bool existeCliente(CatClientes cat, char *codCliente);

/**
 * @brief Preenche um array com os códigos de todos os clientes.
 * @param cat Catálogo de clientes.
 * @return Array com os códigos de todos os clientes.
 */
GPtrArray* getTodosClientes(CatClientes cat);

/**
 * @brief Liberta espaço em memória previamente alocado para um catálogo de clientes.
 * @param cat Catálogo de clientes.
 */
void destroyCatClientes(CatClientes cat);

#endif
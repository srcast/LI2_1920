/**
 * @file view.h
 * @brief Componente visual responsável por mostrar ao utilizador as páginas, tabelas e menus.
 */
#ifndef VIEW_H
#define VIEW_H 

/**
 * @brief Apresenta o menu inicial.
 */
void menu();

/**
 * @brief Imprime no ecrã o tempo de execucção (Elapsed time).
 * @param t Tempo em ms.
 */
void printTime(double t);

/**
 * @brief Imprime no ecrã uma mensagem de saída quando o programa termina.
 */
void printMensagemSaida();

/**
 * @brief Imprime no ecrã uma mensagem que informa o utilizador que os ficheiros não foram
 * lidos com sucesso.
 */
void printFileError();

/**
 * @brief Imprime no ecrã uma mensagem que informa o utilizador que os ficheiros não foram
 * lidos e, como tal, as estruturas de dados nao foram inicializadas.
 */
void printEstruturasNaoInicializadas();

/**
 * @brief Imprime no ecrã resultados globais sobre o número total de vendas
 * e total faturado com um produto num mês.
 * @param nVendas Array com o número de vendas, por tipo.
 * @param totalFaturado Array com o total faturado, por tipo de venda.
 */
void query3outputGlobal(int* nVendas, double* totalFaturado);

/**
 * @brief Imprime no ecrã resultados de cada filialsobre o número total de vendas
 * e total faturado com um produto num determinado mês.
 * @param nVendas Matriz com o número de vendas.
 * @param totalFaturado Matriz com o total faturado.
 */
void query3outputFilial(int** nVendas, double** totalFaturado);

/**
 * @brief Imprime no ecrã informação relativa à query 6.
 * @param produtos Número de produtos que ninguém comprou.
 * @param clientes Número de clientes que não efetuaram compras.
 */
void query6output(int* r);

/**
 * @brief Imprime no ecrã o número total de produtos que um cliente
 * comprou, fazendo a distinção entre meses e filiais.
 * @param r Matriz com o número total de produtos comprados, por 
 * mes e filial.
 */
void query7output(int** r);

/**
 * @brief Imprime no ecrã informação relativa à query 8.
 * @param nVendas Número de vendas.
 * @param totalFaturado Total faturado.
 */
void query8output(int nVendas, double totalFaturado);

/**
 * @brief Imprime no ecã informação relativa aos clientes que compraram
 * um detemrinado produto, fazendo a distinção entre tipos de venda.
 * @param compradores 
 */
void query9output(GPtrArray** compradores);

/**
 * @brief Imprime no ecrã informação relativa à query 10.
 * @param arr Array com os códigos dos produtos a imprimir.
 * @param N Valor de N. 
 */
void query10output(GPtrArray* arr, int N);

/**
 * @brief Imprime no ecrã informação relativa à query 11.
 * @param arr Array com os códigos dos produtos.
 * @param N Valor de N.
 */
void query11output(GPtrArray* arr, int N);

/**
 * @brief Imprime no ecrã informação relativa à query 12.
 * @param arr Array com os códigos dos produtos a imprimir.
 */
void query12output(GPtrArray* arr);

/**
 * @brief Imprime no ecrã informação relativa à query 13.
 * @param clientes_filename Ficheiro de clientes.
 * @param clientes_lidos Número de clientes lidos.
 * @param clientes_validos Número de clientes válidos.
 * @param produtos_filename Ficheiro de produtos.
 * @param produtos_lidos Número de produtos lidos.
 * @param produtos_validos Número de produtos válidos
 * @param vendas_filename Ficheiro de vendas.
 * @param vendas_lidas Número de vendas lidas.
 * @param vendas_valida Número de vendas válidas.s
 */
void query13output(char *clientes_filename, int clientes_lidos, int clientes_validos, char *produtos_filename,
                   int produtos_lidos, int produtos_validos, char *vendas_filename, int vendas_lidas,
                   int vendas_validas);

#endif
/**
 * @file sgv.h
 * @brief SGV - Sistema de Gestão de Vendas.
 *
 * Módulo de daods que elaciona todos os anteriores de modo a responder, 
 * deforma eficiente, a todas asqueries
 */
#ifndef SGV_H
#define SGV_H

#include <gmodule.h>

//#include "filial.h"

/**
 * @brief Declaração do tipo abstrato SGV.
 */
typedef struct sgv* SGV;

/**
 * @brief Declaração do tipo abstrato QUERY13.
 */
typedef struct info* Info;

/**
 * @brief Declaração do tipo abstrato QUERY3.
 */
typedef struct query3* QUERY3;

/**
 * @brief Declaração do tipo abstrato QUERY6.
 */
typedef struct query6* QUERY6;

/**
 * @brief Declaração do tipo abstrato QUERY8.
 */
typedef struct query8* QUERY8;

/**
 * @brief Declaração do tipo abstrato QUERY11.
 */
typedef struct query11*  QUERY11;

/**
 * @brief Permite aceder ao campo que contém o número de clientes válidos.
 * @param sgv SGV.
 * @return Número de clientes válidos.
 */
int getClientesValidos(SGV sgv);

/**
 * @brief Permite aceder ao campo que contém o número de clientes lidos.
 * @param sgv SGV.
 * @return Número de clientes lidos.
 */
int getClientesLidos(SGV sgv);

/**
 * @brief Permite aceder ao campo que contém o nome do ficheiro de clientes.
 * @param sgv SGV.
 * @return Nome do ficheiro de clientes.
 */
char* getClientesFileName(SGV sgv);

/**
 * @brief Permite aceder ao campo que contém o número de produtos válidos.
 * @param sgv SGV.
 * @return Número de produtos válidos.
 */
int getProdutosValidos(SGV sgv);

/**
 * @brief Permite aceder ao campo que contém o número de produtos lidos.
 * @param sgv SGV.
 * @return Número de produtos lidos.
 */
int getProdutosLidos(SGV sgv);

/**
 * @brief Permite aceder ao campo que contém o nome do ficheiro de produtos.
 * @param sgv SGV.
 * @return Nome do ficheiro de produtos.
 */
char* getProdutosFileName(SGV sgv);

/**
 * @brief Permite aceder ao campo que contém o número de vendas válidas.
 * @param sgv SGV.
 * @return Número de vendas válidas.
 */
int getVendasValidas(SGV sgv);

/**
 * @brief Permite aceder ao campo que contém o número de vendas lidas.
 * @param sgv SGV.
 * @return Número de vendas lidas.
 */
int getVendasLidas(SGV sgv);

/**
 * @brief Permite aceder ao campo que contém o nome do ficheiro de vendas.
 * @param sgv SGV.
 * @return Nome do ficheiro de vendas.
 */
char* getVendasFileName(SGV sgv);

/**
 * @brief Permite a saber se os ficheiros foram lidos com sucesso.
 * @param sgv SGV.
 * @return true caso os ficheiros tenham sido lidos com sucesso, false caso contrário.
 */
bool getFilesSuccess(SGV sgv);

/**
 * @brief Permite aceder ao campo da estrutura auxiliar de resposta à query 3 que
 * contém informação sobre o número de vendas global, por tipo de venda.
 * @param r Estrutura auxiliar de resposta à query 3.
 * @return Campo que contém informação sobre o número total de vendas.
 */
int* getNVendasGlobalQuery3(QUERY3 r);

/**
 * @brief Permite aceder ao campo da estrutura auxiliar de resposta à query 3 que
 * contém informação sobre o número total de vendas, por filial, por tipo de venda.
 * @param r Estrutura auxiliar de resposta à query 3.
 * @return Campo que contém informação sobre o número total de vendas.
 */
int** getNVendasQuery3(QUERY3 r);

/**
 * @brief Permite aceder ao campo da estrutura auxiliar de resposta à query 3 que
 * contém informação sobre o total faturado globalmente, por tipo de venda.
 * @param r Estrutura auxiliar de resposta à query 3.
 * @return Campo que contém informação sobre o número total de vendas.
 */
double* getTotalFaturadoGlobalQuery3(QUERY3 r);

/**
 * @brief Permite aceder ao campo da estrutura auxiliar de resposta à query 3 que
 * contém informação sobre o total faturado, por filial, por tipo de venda.
 * @param r Estrutura auxiliar de resposta à query 3.
 * @return Campo que contém informação sobre o total faturado.
 */
double** getTotalFaturadoQuery3(QUERY3 r);

/**
 * @brief Liberta espaço em memória previamente alocado para
 * para a estrutura auxiliar de resposta à query 3.
 * @param r Estrutura auxiliar de resposta à query 3.
 */
void destroyQuery6(QUERY6 r);

/**
 * @brief Permite aceder ao campo da estrutura auxiliar
 * de resposta à query 6 que contém o número de clientes.
 * @param r Estrutura auxiliar de resposta à query 6.
 * @return
 */
int getClientesQuery6(QUERY6 r);

/**
 * @brief Permite aceder ao campo da estrutura auxiliar
 * de resposta à query 6 que contém o número de produtos.
 * @param r Estrutura auxiliar de resposta à query 6.
 * @return
 */
int getProdutosQuery6(QUERY6 r);

/**
 * @brief Liberta espaço em memória previamente alocado para
 * a estrutura auxiliar de resposta à query 6.
 * @param r Estrutura auxiliar de resposta à query 6.
 */
void destroyQuery6(QUERY6 r);

/**
 * @brief Permite aceder ao campo nVendas da estrutura auxiliar de
 * reposta à query 8.
 * @param r Estrutura auxiliar de resposta à query 8.
 * @return Total faturado.
 */
int getNVendasQuery8(QUERY8 r);

/**
 * @brief Permite aceder ao campo totalFaturado da estrutura auxiliar de
 * resposta à query 8.
 * @param r Estrutura auxiliar de resposta à query 8.
 * @return Total faturado.
 */
double getTotalFaturadoQuery8(QUERY8 r);

/**
 * @brief Liberta espaço em memória previamente alocado para a estrutura
 * auxiliar de resposta à query 8.
 * @param r Estrutura auxiliar de resposta à query 8.
 */
void destroyQuery8(QUERY8 r);

/**
 * @brief Permite aceder ao campo da estrutura auxiliar de resposta à query 11 que
 * contém os códigos dos produtos.
 * @param r Estrutura auxiliar de resposta à query 11.
 * @return Array com os códigos dos produtos.
 */
GPtrArray* getProdutosQuery11(QUERY11 r);

/**
 * @brief Liberta espaço em memória previamente alocado para a estrurua
 * auxiliar de resposta à query 11.
 * @param r Estrutura auxiliar de resposta à query 11.
 */
void destroyQuery11(QUERY11 r);

/**
 * @brief Aloca espaço em memória e inicializa o SGV.
 * @return SGV.
 */
SGV initSGV();

/**
 * @brief
 * @param sgv SGV.
 * @param clientesFilePath
 * @param productsFilePath
 * @param salesFilePath
 * @return SGV.
 **/
SGV loadSGVFromFiles(SGV sgv, char* clientesFilePath, char* productsFilePath, char* salesFilePath);

/**
 * @brief Preenche um array com os códigos ordenados dos produtos que começam por uma determinada letra.
 * @param sgv SGV
 * @param letter letra
 * @return Array com os códigos dos produtos
 */
GPtrArray* getProductsStartedByLetter(SGV sgv, char letter);

/**
 * @brief
 * @param sgv SGV.
 * @param productID Código do produto. 
 * @param month Mês.
 * @return Estrutura auxiliar com informação relativa ao número total 
 * de vendas e total faturado.
 */
QUERY3 getProductSalesAndProfit(SGV sgv, char* productID, int month);

/**
 * @brief Preenche um array com os códigos dos produtos que ninguem comprou.
 * O resultador pode ser relativo a uma dada filial ou global.
 * @param sgv SGV.
 * @param branchID Filial.
 * @return Array com os códigos dos produtos.
 */
GPtrArray* getProductsNeverBought(SGV sgv, int branchID);

/**
 * @brief Preenche um array com os códigos dos clientes que efeturaram
 * compras em todas as filiais.
 * @param sgv SGV
 * @return Array com os códigos dos clientes.
 */
GPtrArray* getClientsofAllBranches(SGV sgv);

/**
 * @brief Preenche um array com o número de clientes que não efetuaram
 * compras, assim como o número de produtos que ninguém comprou.
 * @param sgv SGV.
 * @return Array com o número de clientes e produtos.
 */
int* getClientsAndProductsNeverBoughCount(SGV sgv);

/**
 * @brief Preenche uma matriz com o número de produtos que um cliente
 * comprou, por mês e filial.
 * @param sgv SGV.
 * @param clientID Código do cliente.
 * @return Matriz preenchida com o número de produtos.
 */
int** getProductsBoughtByClient(SGV sgv, char* clientID);

/**
 * @brief Determina o total faturado e o número de vendas num
 * intervalo fechado de meses.
 * @param sgv SGV.
 * @param minMonth Primeiro mês do intervalo. 
 * @param maxMonth Último mês do intervalo.
 * @return Estrutura auxiliar de resposta à query 8.
 */
QUERY8 getSalesAndProfit(SGV sgv, int minMonth, int maxMonth);

/**
 *@brief Determinar os códigos e número total dos clientes que compraram um dado produto,
 * distinguindo diferentes tipos de venda.
 * @param sgv SGV.
 * @param productID Código do produto.
 * @param branch Filial.
 * @return Esturtura com o código dos clientes.
 */
GPtrArray** getProductBuyers(SGV sgv, char* productID, int branch);

/**
 * @brief Preenche um array com os códigos dos produtos que um cliente mais comprou num dado mês (por quantidade),
 * por ordem decrescente.
 * @param sgv SGV.
 * @param clientID Código do cliente.
 * @param month Mês.
 * @return Array com os códigos dos produtos.
 */
GPtrArray* getClientFavoriteProducts(SGV sgv, char* clientID, int month);

/**
 * @brief Determina os N produtos mais vendidos todo o ano, indicando o número total de clientes
 * que o compraram e o número de unidades vendidas, filial a filial.
 * @param sgv SGV.
 * @param limit Valor de N.
 * @return
 */
QUERY11 getTopSelledProducts(SGV sgv, int limit);
/**
 * @brief Preenche um array com os códigos dos N produtos em que um
 * cliente mais gastou dinheiro durante o ano;
 * @param sgv SGV.
 * @param clientID Código do cliente.
 * @param limit Valor de N.
 * @return
 */
GPtrArray* getClientTopProfitProducts(SGV sgv, char* clientID, int limit);

/**
 * @brief Liberta espaço em memória previamente alocado para um SGV.
 * @param sgv
 */
void destroySGV(SGV sgv);

#endif
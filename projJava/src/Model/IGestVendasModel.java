package Model;

import Exceptions.ClienteInvalidoException;
import Exceptions.MesInvalidoException;
import Exceptions.ProdutoInvalidoException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IGestVendasModel {
    /**
     * Método que devolve o nome do ficheiro de vendas lido.
     *
     * @return Nome do ficheiro de vendas.
     */
    String getVendasFilename();

    /**
     * Método que devolve o número de vendas inválidas.
     *
     * @return Número de vendas inválidas.
     */
    int getVendasInvalidas();

    /**
     * Método que devolve o número de vendas cuja faturação é 0.0.
     *
     * @return Número de vendas cuja faturação é 0.0.
     */
    int getVendasZero();

    /**
     * Método que determina o número total de produtos.
     *
     * @return Número total de produtos.
     */
    int nProdutos();

    /**
     * Método que determina o número de produtos que foram comprados.
     *
     * @return Número de produtos que foram comprados.
     */
    int produtosComprados();

    /**
     * Método que determina o número de produtos que não foram comprados.
     *
     * @return Número de produtos que não foram comprados.
     */
    int produtosNaoComprados();

    /**
     * Método que determina o número total de clientes.
     *
     * @return Número total de clientes.
     */
    int nClientes();

    /**
     * Método que determina o número de clientes que efetuaram compras.
     *
     * @return Número de clientes que efetuaram compras.
     */
    int clientesCompradores();

    /**
     * Método que determina o número de clientes que não efetuaram compras.
     *
     * @return Número de clientes que não efetuaram compras.
     */
    int clientesNaoCompradores();

    /**
     * Método que determina o total faturado com todas as vendas da cadeia de distribuição.
     *
     * @return Total faturado.
     */
    double totalFaturado();

    /* ESTATISTICAS 2 */

    /**
     * Método que determina o número de vendas num dado mês.
     *
     * @param mes Mês.
     * @return Número de vendas.
     */
    int vendasMes(int mes);

    /**
     * Método que, dado um mês, determiina a faturação para cada filial.
     *
     * @param mes Mês.
     * @return Map em que a cada filial se associa a faturação nessa filial.
     */
    Map<Integer, Double> faturacaoFilial(int mes);

    /**
     * Método que, dado um mês, determina, para cada uma das filiais, número de clientes distintos que fizeram
     * compras nessa filial.
     *
     * @param mes Mẽs.
     * @return Map em que a cada filial se associa o número de clientes distintos que fizeram compras.
     */
    Map<Integer, Integer> clientesDistintosFilial(int mes);

    /**
     * Método que lê os ficheiros de texto e popula as estruturas de dados.
     *
     * @param clientesFilename Ficheiro de clientes.
     * @param produtosFilename Ficheiro de produtos.
     * @param vendasFilename   Ficheiro de vendas.
     */
    void readFiles(String clientesFilename, String produtosFilename, String vendasFilename);

    /**
     * Método que devolve a lista ordenada alfabeticamente com os códigos dos produtos que nunca
     * foram comprados.
     *
     * @return Lista ordenada com os códigos dos produtos nunca comprados.
     */
    List<String> produtosNuncaComprados();

    /**
     * Método que, dado um mês, determina o número número de vendas realizadas e o número total de
     * clientes distintos que as fizeram, tanto globalmente como filial a filial.
     *
     * @param mes Mês.
     * @return Map em que a cada filial se associa um par com o número de vendas e número de clientes
     * distintos.
     * @throws MesInvalidoException Exceção caso o valor recebido como argumento seja inválido.
     */
    Map<Integer, IParVendasNClientes> vendasClientesMes(int mes) throws MesInvalidoException;

    /**
     * Método que, dado um código de cliente, determina, para cada mês, quantas compras fez, quantos
     * rodutos distintos comprou e quanto gastou no total.
     *
     * @param cliente Código do cliente.
     * @return Map em que a cada código de cliente se associa um terno cujas componentes são o número de compras,
     * os número de produtos distintos comprados e o total faturado.
     * @throws ClienteInvalidoException Exceção caso o código de cliente recebido como argumento não seja
     * válido.
     */
    Map<Integer, ITernoComprasProdutosFaturacao> estatisticasCliente(String cliente) throws ClienteInvalidoException;

    /**
     * Método que, dado o código de um produto, determina, para cada mês, quantas vezes foi comprado, por
     * quantos clientes distintos e o total faturado.
     *
     * @param produto Código do produto.
     * @return Map em que a cada mês se associa um terno cujas componentes são o número de vendas, o número
     * de clientes que o compraram e o total faturado.
     * @throws ProdutoInvalidoException Exceção caso o código de produto recebido como argumento seja
     *                                  inválido.
     */
    Map<Integer, ITernoVendasClientesFaturacao> estatisticasProduto(String produto) throws ProdutoInvalidoException;

    /**
     * Método que determina a lista de códigos de produtos que um cliente mais comprou, e quantos, ordenadas
     * por ordem decrescente de quantidae.
     *
     * @param cliente Código de cliente.
     * @return Lista de códigos de produtos que um cliente mais comprou. Cada elemento da lista é um par
     * cujas componentes são o código do produto e a quantidade comprada.
     * @throws ClienteInvalidoException Exceção caso o código recebido como argumento seja inválido.
     */
    List<IParProdutoQuantidade> produtosMaisComprados(String cliente) throws ClienteInvalidoException;

    /**
     * Método que determina a lista dos N produtos mais vendidos.
     *
     * @param n Valor de N.
     * @return Lista cujos elementos são um terno que contém o código do produto, a quantidade vendida e um Set com os
     * clientes distintos que o compraram.
     */
    List<ITernoProdutoQuantidadeLista> produtosMaisVendidos(int n);

    /**
     * Método que, para uma determinada filial, determina uma lista com o código dos 3 melhores compradores.
     *
     * @param filial Filial.
     * @return Lista com o código dos 3 melhores compradores.
     */
    List<String> melhoresClientesPorFilial(int filial);

    /**
     * Método que determina a lista dos N clientes que compraram mais produtos diferentes,
     *
     * @param n Valor de N.
     * @return Lista cujos elementos são um par que contém o código de cada cliente e um Set com os códigos dos
     * produtos que este comprou.
     */
    List<IParClienteProdutos> maisProdutosDiferentes(int n);

    /**
     * Método que determina uma lista com informação sobre os N cliuentes que mais compraram um produto.
     *
     * @param produto Código do produto.
     * @param n Valor de N.
     * @return Lista com informação sobre os N cliuentes que mais compraram um produto.
     * @throws ProdutoInvalidoException Exceção caso o código do produto recebido como argumento seja inválido.
     */
    List<ITernoClienteQtFaturacao> maioresCompradoresProduto(String produto, int n) throws ProdutoInvalidoException;

    /**
     * Método que determina a faturação total de um produto num dado mês e numa dada filial.
     *
     * @param mes    Mês.
     * @param filial Filial.
     * @return Map em que a cada código de produto se associa o total faturado.
     */
    Map<String, Double> faturacaoProd(int mes, int filial);

    /**
     * Método que recupera o estado gravado anteriormente.
     *
     * @param filename Nome do ficheiro a partir do qual será recuperado o estado do programa.
     * @return Estado do programa recuperado.
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     */
    GestVendasModel loadState(String filename) throws IOException, FileNotFoundException, ClassNotFoundException;

    /**
     * Método que guarda o estado do programa.
     *
     * @param filename Nome do ficheiro para o qual será guardado o estado do programa.
     * @throws FileNotFoundException
     * @throws IOException
     */
    void saveState(String filename) throws FileNotFoundException, IOException;

}

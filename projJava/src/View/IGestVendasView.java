package View;

import Model.*;
import Utils.Menu;

import java.util.List;
import java.util.Map;

public interface IGestVendasView {
    /**
     * Método que define o menu.
     *
     * @param s Opções.
     **/
    void setMenu(String[] s);

    /**
     * Método que apresenta o menu.
     */
    void showMenu();

    /**
     * Método responsável por limpar o ecrã.
     */
    void clear();

    /**
     * Método que imprime uma mensagem no ecrã.
     *
     * @param message Mensagem a imprimir.
     */
    void showMessage(String message);

    /**
     * Método que apresenta ao utilizador dados referentes ao último ficheiro de vendas.
     *
     * @param vendasFilename         Nome do ficheiro de vendas.
     * @param regErrados             Número de registos errados.
     * @param produtos               Número total de produtos.
     * @param produtosComprados      Número de produtos comprados.
     * @param produtosNaoComprados   Número de produtos não comprados.
     * @param clientes               Número total de clientes.
     * @param clientesCompradores    Número de clientes compradores.
     * @param clientesNaoCompradores Número de clientes não compradores.
     * @param vendasZero             Número de vendas cuja faturação é 0.0.
     * @param faturacaoTotal         Faturação total da cadeia de distribuição.
     */
    void estatisticas1(String vendasFilename, int regErrados, int produtos, int produtosComprados,
                       int produtosNaoComprados, int clientes, int clientesCompradores,
                       int clientesNaoCompradores, int vendasZero, double faturacaoTotal);

    /**
     * Método responsável por imprimir estatísticas mensais.
     *
     * @param mes       Mẽs
     * @param vendasMes Número de vendas nesse mês.
     * @param faturacao Faturação por filial.
     * @param clientes  Clientes distintos que efetuaram compras, por filial.
     */
    void estatisticas2(int mes, int vendasMes, Map<Integer, Double> faturacao, Map<Integer, Integer> clientes);

    /**
     * Método responsável por imprimri os resultados relattivos à query 1.
     *
     * @param r Resultados a imprimir.
     */
    void query1(List<String> r);

    /**
     * Método responsável por imprimir os resultados relativos à query 2.
     *
     * @param r Resultados a imprimir.
     */
    void query2(Map<Integer, IParVendasNClientes> r);

    /**
     * Método responsável por imprimir os resultados relativos à query 3.
     *
     * @param r Resultados a imprimir.
     */
    void query3(Map<Integer, ITernoComprasProdutosFaturacao> r);

    /**
     * Método responsável por imprimir os resultados relativos à query 4.
     * .
     * @param r Resultados a imprimir.
     */
    void query4(Map<Integer, ITernoVendasClientesFaturacao> r);


    /**
     * Método responsável por imprimir os resultados relativos à query 5.
     *
     * @param r Resultados a imprimir.
     */
    void query5(List<IParProdutoQuantidade> r);

    /**
     * Método responsável por imprimir os resultados relativos à query 6.
     *
     * @param r Resultados a imprimir.
     */
    void query6(List<ITernoProdutoQuantidadeLista> r);

    /**
     * Método responsável por imprimir os resultados relativos à query 7.
     *
     * @param r Resultados a imprimir.
     */
    void query7(List<List<String>> r);

    /**
     * Método responsável por imprimir os resultados relativos à query 8.
     *
     * @param r Resultados a imprimir.
     */
    void query8(List<IParClienteProdutos> r);

    /**
     * Método responsável por imprimir os resultados relativos à query 9.
     *
     * @param r Resultados a imprimir.
     */
    void query9(List<ITernoClienteQtFaturacao> r);

    /**
     * Método responsável por imprimir os resultados relativos à query 10.
     *
     * @param r Resultados a imprimir.
     */
    void query10(Map<String, Double> r);
}

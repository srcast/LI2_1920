package Model;

import java.util.Map;
import java.util.Set;

public interface IInfoMensalCliente {
    /**
     * Método que devolve um Map em que a cada código de um produto se associa um terno contendo a
     * quantidade, número de vendas e faturação desse produto.
     *
     * @return Map em que a cada código de um produto se associa um terno contendo a quantidade, número de vendas
     * e faturação desse produto.
     */
    Map<String, ITernoQtVendasFaturacao> getProdutos();

    /**
     * Método que testa se um cliente comprou um determinado produto no mês em questão.
     *
     * @param produto Código do produto.
     * @return true caso o cliente tenha comprado o produto nesse mês, false caso contrário.
     */
    boolean contains(String produto);
    /**
     * Método que, dada uma venda, atualiza a informação mensal de um cliente com a iformação dessa venda.
     *
     * @param venda Venda com informação a adicionar à estrutura.
     */
    void insereVenda(IVenda venda);

    /**
     * Método que devolve um Set que contém os códigos dos produtos que o cliente comprou no mês me questão.
     *
     * @return Set com os códigos dos produtos
     */
    Set<String> getCodProdutos();

    /**
     * Método que calcula a quantidade total de produtos que o cliente comprou no mês em questão.
     *
     * @return Qantidade total de produtos.
     */
    int getQuantidadeTotal();

    /**
     * Método que calcula o número total de compras que o cliente fez no mês em questão.
     *
     * @return Número total de compras.
     */
    int getNCompras();

    /**
     * Método que calcula o total faturado com as compras que o cliente fez no mês em questão.
     *
     * @return Total faturado.
     */
    double getFaturacaoTotal();

    /**
     * Método que etermina a quantidade vendida e a faturação de
     * um dado produto para o mês em questão
     *
     * @param produto Código do produto.
     * @return Par cujas componentes são a quantidade e a faturação.
     */
    IParQtFaturacao getQtFaturacao(String produto);

    /**
     * Método que devolve um Map em que a cada código de produto se associa a quantidade
     * vendida no mês considerado.
     *
     * @return Map em que a cada código de produto se associa a quantidade.
     */
    Map<String, Integer> mapProdQt();
}

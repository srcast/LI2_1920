package Model;

import java.util.Map;

public interface IInfoProduto {
    /**
     * Método que devolve o código do produto.
     *
     * @return Código do produto.
     */
    String getProduto();

    /**
     * Método que determina se um produto foi comprado ou não.
     *
     * @return true caso o produto tenha sido comprado, false caso contrário.
     */
    boolean isComprado();

    /**
     * Método que devolve uma matriz com o número de vendas, por mês e filial.
     *
     * @return Matriz com o número de vendas.
     */
    int[][] getnVendas();

    /**
     * Método que devolve uma matriz com a faturação, por mês e filial.
     *
     * @return Matriz com a faturação.
     */
    double[][] getFaturacao();

    /**
     * Método que devolve a quantidade comprada por mês.
     *
     * @return Quantidade comprada por mês.
     */
    int[] getQuantidade();

    /**
     * Método que atualiza a informação de um produto com a informação de uma venda.
     *
     * @param venda Venda com informação a adicionar à estrutura.
     */
    void update(IVenda venda);

    /**
     * Método que determina o número total de vendas de um determinado produto.
     *
     * @return Número total de vendas.
     */
    int getnVendasTotal();

    /**
     * Método que detemrina a faturação total de um determinado produto.
     *
     * @return Faturação total.
     */
    double getFaturacaoTotal();

    /**
     * Método que determina a quantidade total vendida de um determinado produto.
     *
     * @return Quantidade total vendida.
     */
    int getQuantidadeTotal();

    /**
     * Método que determina o número de vendas para um dado mês.
     *
     * @param mes Mês.
     * @return Número de vendas.
     */
    int nVendasMensal(int mes);

    /**
     * Método que, para um dado produto, determina a faturação total em cada mês para uma dada filial.
     *
     * @param filial Filial considerada.
     * @return Map em que a cada mês se associa o total faturado nesse mês.
     */
    Map<Integer, Double> getFaturacaoFilial(int filial);

    /**
     * Método que calcula a faturação mensal de um produto para cada um dos meses considerados.
     *
     * @return Map em que a cada mês se associa o total faturado nesse mês.
     */
    Map<Integer, Double> faturacaoMensal();
}

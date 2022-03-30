package Model;

import java.util.List;
import java.util.Map;

public interface IFaturacao {
    /**
     * Método que devolve informação sobre um produto.
     *
     * @param produto Código do produto.
     * @return Informação sobre um produto.
     */
    IInfoProduto getInfoProduto(String produto);

    /**
     * Método que atualiza a estrutura faturação com a informação de uma venda.
     *
     * @param venda Venda com informação a adicionar à estrutura.
     */
    void update(IVenda venda);

    /**
     * Método que determina o número de produtos que foram comprados.
     *
     * @return Número de produtos que foram comprados.
     */
    int produtosCompradosCount();

    /**
     * Método que devolve uma lista com os códigos dos produtos que não foram comprados.
     *
     * @return Lista com os códigos dos produtos não comprados.
     */
    List<String> produtosNaoComprados();

    /**
     * Método que calcula a quantidade total de produtos vendida.
     *
     * @return Quantidade total.
     */
    int quantidadeTotal();

    /**
     * Método que calcula o número total de vendas.
     *
     * @return Número total de vendas.
     */
    int nVendasTotal();

    /**
     * Método que calcula a faturação total.
     *
     * @return Faturação total.
     */
    double faturacaoTotal();

    /**
     * Método que determina o total faturado em cada mês.
     *
     * @return Map em que a cada mês se associa o total faturado nesse mês.
     */
    Map<Integer, Double> faturacaoMes();

    /**
     * Método que, para uma determinada filial, determina a faturação mensal para cada um dos
     * meses considerados.
     *
     * @param filial Filial considerada.
     * @return Map em que a cada mês se associa o total faturado nesse mês.
     */
    Map<Integer, Double> faturacaoMesFilial(int filial);
}

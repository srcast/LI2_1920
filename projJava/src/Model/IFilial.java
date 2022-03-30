package Model;

import java.util.Map;
import java.util.Set;

public interface IFilial {
    /**
     * Método que atualiza a estrutura de uma filial com a informação de uma venda.
     *
     * @param venda Venda com informação a adicionar à estrutura.
     */
    void insereVenda(IVenda venda);

    /**
     * Método que devolve o número de compras que um cliente fez num dado mês.
     *
     * @param cliente Código do cliente.
     * @param mes Mês.
     * @return Número de compras.
     */
    int nComprasCliente(String cliente, int mes);

    /**
     * Método que determina o total faturado com as vendas de um cliente num dado mês.
     *
     * @param cliente Código do cliente.
     * @param mes Mês.
     * @return Total faturado.
     */
    double faturacaoCliente(String cliente, int mes);

    /**
     * Método que devolve um Set com os códigos dos produtos que um cliente comprou num dado mªes.
     *
     * @param cliente Código do cliente.
     * @param mes Mês.
     * @return Set com os códigos dos produtos comprados pelo cliente.
     */
    Set<String> produtosDif(String cliente, int mes);

    /**
     * Método que devolve um Set com os códigos dos clientes que fizeram compras num determinado mês.
     *
     * @param mes Mês.
     * @return Set com os códigos dos clientes que fizeram compras.
     */
    Set<String> clientesDif(int mes);

    /**
     * Método que devolve um Set com os códigos dos diferentes clientes qe compraram um produto
     * num dado mês.
     *
     * @param produto Código do produto.
     * @param mes Mês.
     * @return Set com os códigos dos clientes.
     */
    Set<String> clientesDifProd(String produto, int mes);

    /**
     * Método que devolve um set com os códigos dos clientes que efetuaram compras na filial.
     *
     * @return Set com os códigos dos clientes.
     */
    Set<String> clientesCompradores();

    /**
     * Método que determina os produtos mais comprados de um determinado cliente, devolvendo um
     * Map em que a cada código de cliente se associa a quantidade comprada.
     *
     * @param cliente Código do cliente,
     * @return Map em que a cada código de cliente se associa a quantidade comprada.
     */
    Map<String, Integer> produtosMaisComprados(String cliente);

    /**
     * Método que determina os clientes que mais gastaram dinheiro na filial.
     *
     * @return Set com os códigos dos clientes e respetiva faturação.
     * */
    Set<IParClienteFaturacao> maioresCompradoresFilial();

    /**
     * Método que determina os clientes que mais compraram um produto, por faturação.
     *
     * @param produto Código do produto.
     * @return Map em que a cada código de cliente se associa o respetivo par com quantidade e
     * total faturado.
     */
    Map<String, IParQtFaturacao> maioresCompradoresProduto(String produto);

    /**
     * Método que, para cada cliente, determina os produtos comprados por este.
     *
     * @return Map em que a cada código de cliente se associa um Set com os códigos dos produtos que este comprou.
     */
    Map<String, Set<String>> produtosDiferentes();

    /**
     * Método que determina os produtos mais vendidos.
     *
     * @return Map em que a cada código de cliente se associa um par com a quantidade comprada e um
     * Set com os códigos dos clientes que o compraram.
     */
    Map<String, IParQtClientes> produtosMaisVendidos();
}

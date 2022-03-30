package Model;

public interface ITernoVendasClientesFaturacao {
    /**
     * Método que devolve o número de vendas.
     *
     * @return Número de vendas.
     */
    int getnVendas();
    /**
     * Método que devolve o número de clientes distintos que compraram um produto.
     *
     * @return Número de clientes distintos que compraram um produto.
     */
    int getnClientes();

    /**
     * Método que devolve a faturação.
     *
     * @return Faturação.
     */
    double getFaturacao();
}

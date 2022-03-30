package Model;

public interface ITernoComprasProdutosFaturacao {
    /**
     * Método que devolve o número de compras.
     *
     * @return Número de compras.
     */
    int getnCompras();

    /**
     * Método que devolve o número de produtos distintos que o cliente comprou.
     *
     * @return Número de produtos distintos que o cliente comprou.
     */
    int getProdutos();

    /**
     * Método que devolve a faturação.
     *
     * @return Faturação.
     */
    double getFaturacao();
}

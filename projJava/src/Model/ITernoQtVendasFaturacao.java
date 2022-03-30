package Model;

public interface ITernoQtVendasFaturacao {
    /**
     * Método que devolve a quantidade.
     *
     * @return Quantidade.
     */
    int getQuantidade();

    /**
     * Método que devolve o número de vendas.
     *
     * @return Número de vendas.
     */
    int getVendas();

    /**
     * Método que devolve a faturação.
     *
     * @return Faturação.
     */
    double getFaturacao();

    /**
     * Método que atualiza as variáveis de instância.
     *
     * @param quantidade Quantidade a adicionar.
     * @param faturacao  Faturação a adicionar.
     */
    void update(int quantidade, double faturacao);
}

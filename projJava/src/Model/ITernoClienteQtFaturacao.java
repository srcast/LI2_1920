package Model;

public interface ITernoClienteQtFaturacao {
    /**
     * Método que devolve o código do cliente.
     *
     * @return Código do cliente.
     */
    String getCliente();

    /**
     * Método que devolve a quantidade.
     *
     * @return Quantidade.
     */
    int getQuantidade();

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

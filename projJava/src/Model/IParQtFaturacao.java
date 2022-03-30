package Model;

public interface IParQtFaturacao {
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
     * @param quantidade Valor a adicionar à quantidade.
     * @param faturacao  Valor a adicionar à faturação.
     */
    void update(int quantidade, double faturacao);
}

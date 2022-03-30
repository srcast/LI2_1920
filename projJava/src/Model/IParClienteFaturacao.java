package Model;

public interface IParClienteFaturacao {
    /**
     * Método que devolve o código do cliente.
     *
     * @return Código do cliente.
     */
    String getCliente();

    /**
     * Método que devolve a faturação.
     *
     * @return Faturação.
     */
    double getFaturacao();

    /**
     * Método que atualiza o valor da faturaçõa.
     *
     * @param faturacao Valor a adicionar à faturação.
     */
    void update(double faturacao);
}

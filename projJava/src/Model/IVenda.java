package Model;

public interface IVenda {
    /**
     * Método que devolve o cósido do produto comprado.
     *
     * @return Código do produto.
     */
    String getProduto();

    /**
     * Método que devolve o cliente que efetuou a compra.
     *
     * @return Código do cliente.
     */
    String getCliente();

    /**
     * Método que devolve o preço unitário do produto comprado.
     *
     * @return Preço unitário.
     */
    double getPrecoUnit();

    /**
     * Método que devolve a quantidade comprada.
     *
     * @return Quantidade comprada.
     */
    int getQuantidade();

    /**
     * Método que devolve o tipo de venda (Normal/Promoção).
     *
     * @return Tipo de venda (Normal/Promoção).
     */
    char getTipo();

    /**
     * Método que devolve o mês em que a venda ocorreu.
     *
     * @return Mês em que a venda ocorreu.
     */
    int getMes();

    /**
     * Método que devolve a filial em que a venda ocorreu.
     *
     * @return Filial em que a venda ocorreu.
     */
    int getFilial();

    /**
     * Método que devolve a faturação da venda em questão.
     *
     * @return Faturação da venda.
     */
    double getFaturacao();
}

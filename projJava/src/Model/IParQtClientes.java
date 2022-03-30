package Model;

import java.util.Set;

public interface IParQtClientes {
    /**
     * Método que devolve a quantidade.
     *
     * @return Quantidade.
     */
    int getQuantidade();

    /**
     * Método que devolve um Set com os códigos dos clientes que compraram o produto.
     *
     * @return Set com os códigos dos clientes que compraram o produto.
     */
    Set<String> getClientes();

    /**
     * Método que atualiza a quantidade.
     *
     * @param quantidade Valor a adicionar à quantidade.
     */
    void updateQuantidade(int quantidade);

    /**
     * Método que adiciona um código de cliente ao Set.
     *
     * @param cliente Código de cliente a adicionar.
     */
    void add(String cliente);

    /**
     * Método que, dado um Set com códigos de clientes, adiciona todos os códigos ao Set que contém os
     * códigos de clientes que compraram o produto.
     *
     * @param clientes Set que contém os códigos a adicionar.
     */
    void addSet(Set<String> clientes);
}

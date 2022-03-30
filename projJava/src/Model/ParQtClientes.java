package Model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe auxiliar que implementa um par de um inteiro e um Set de Strings. O inteiro corresponde
 * à quantidade comprada e o Set contém os códigos dos clientes que compraram o produto.
 *
 * @author Ricardo Gomes.
 * @author Rui Fernandes,
 */
public class ParQtClientes implements IParQtClientes, Serializable {
    /**
     * Quantidade
     */
    private int quantidade;

    /**
     * Set que contém os códigos dos clientes
     */
    private Set<String> clientes;

    /**
     * Construtor por omissão para objetos da classe ParQtClientes..
     */
    public ParQtClientes() {
        this.quantidade = 0;
        this.clientes = new HashSet<>();
    }

    /**
     * Construtor parametrizado para objetos da classe ParQtClientes.
     *
     * @param quantidade Quantidade.
     * @param cliente    Código de cliente a adicionar ao Set.
     */
    public ParQtClientes(int quantidade, String cliente) {
        this.quantidade = quantidade;
        this.clientes = new HashSet<>();
        this.clientes.add(cliente);
    }

    /**
     * Construtor de cópia para objetos da classe ParQtClientes.
     *
     * @param par Instância da classe ParQtClientes a partir da qual se instancia um novo objeto.
     */
    public ParQtClientes(ParQtClientes par) {
        this.quantidade = par.getQuantidade();
        this.clientes = par.getClientes();
    }

    /**
     * Método que devolve a quantidade.
     *
     * @return Quantidade.
     */
    public int getQuantidade() {
        return this.quantidade;
    }

    /**
     * Método que devolve um Set com os códigos dos clientes que compraram o produto.
     *
     * @return Set com os códigos dos clientes que compraram o produto.
     */
    public Set<String> getClientes() {
        return new HashSet<>(this.clientes);
    }

    /**
     * Método que atualiza a quantidade.
     *
     * @param quantidade Valor a adicionar à quantidade.
     */
    public void updateQuantidade(int quantidade) {
        this.quantidade += quantidade;
    }

    /**
     * Método que adiciona um código de cliente ao Set.
     *
     * @param cliente Código de cliente a adicionar.
     */
    public void add(String cliente) {
        this.clientes.add(cliente);
    }

    /**
     * Método que, dado um Set com códigos de clientes, adiciona todos os códigos ao Set que contém os
     * códigos de clientes que compraram o produto.
     *
     * @param clientes Set que contém os códigos a adicionar.
     */
    public void addSet(Set<String> clientes) {
        this.clientes.addAll(clientes);
    }

    /**
     * Implementação do método hashCode.
     *
     * @return Código hash.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.quantidade, this.clientes});
    }

    /**
     * Implementação do método equals.
     *
     * @param obj Objeto com o qual se testa a igualdade.
     * @return true caso o objeto argumento for igual ao objeto sobre o qual o método é chamado,
     * false caso contrário
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        ParQtClientes par = (ParQtClientes) obj;
        return this.quantidade == par.getQuantidade() &&
                this.clientes.equals(par.getClientes());
    }

    /**
     * Implementação do método clone.
     *
     * @return Cópia do objeto sobre o qual o método é chamado.
     */
    @Override
    public ParQtClientes clone() {
        return new ParQtClientes(this);
    }

}
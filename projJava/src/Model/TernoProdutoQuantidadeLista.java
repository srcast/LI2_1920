package Model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TernoProdutoQuantidadeLista implements ITernoProdutoQuantidadeLista, Comparable {
    /**
     * Código do produto.
     */
    private String produto;

    /**
     * Quantidade.
     */
    private int quantidade;

    /**
     * Set com os códigos dos clientes que compraram o produto.
     */
    private Set<String> clientes;

    /**
     * Construtor por omissão para objetos da classe TernoProdutoQuantidadeLista.
     */
    public TernoProdutoQuantidadeLista() {
        this.produto = new String();
        this.quantidade = 0;
        this.clientes = new HashSet<>();
    }

    /**
     * Construtor parametrizado para objetos da classe TernoProdutoQuantidadeLista.
     *
     * @param produto    Código do produto.
     * @param quantidade Quantidade.
     * @param clientes   Set com os códigos dos clientes que compraram o produto.
     */
    public TernoProdutoQuantidadeLista(String produto, int quantidade, Set<String> clientes) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.clientes = new HashSet<>(clientes);
    }

    /**
     * Construtor de cópia para objetos da classe TernoProdutoQuantidadeLista.
     *
     * @param terno Instância da classe TernoProdutoQuantidadeLista a partir da qual se instancia um
     * novo objeto.
     */
    public TernoProdutoQuantidadeLista(TernoProdutoQuantidadeLista terno) {
        this.produto = terno.getProduto();
        this.quantidade = terno.getQuantidade();
        this.clientes = terno.getClientes();
    }

    /**
     * Método que devolve o código do produto.
     *
     * @return Código do produto.
     */
    public String getProduto() {
        return produto;
    }

    /**
     * Método que devolve a quantidade.
     *
     * @return Quantidade.
     */
    public int getQuantidade() {
        return quantidade;
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
     * Implementação do método hashCode.
     *
     * @return Código hash.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.produto, this.quantidade, this.clientes});
    }

    /**
     * Implementação do método equals.
     *
     * @param obj Objeto com o qual se testa a igualdade.
     * @return true caso o objeto argumento for igual ao objeto sobre o qual o método é chamado, false
     * caso contrário.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        TernoProdutoQuantidadeLista terno = (TernoProdutoQuantidadeLista) obj;
        return this.produto.equals(terno.getProduto()) &&
                this.quantidade == terno.getQuantidade() &&
                this.clientes.equals(terno.getClientes());
    }

    /**
     * Implementação do método clone.
     *
     * @return Cópia do objeto sobre o qual o método é chamado.
     */
    @Override
    public TernoProdutoQuantidadeLista clone() {
        return new TernoProdutoQuantidadeLista(this);
    }

    /**
     * Implementação do método compareTo.
     *
     * @param o Objeto a comparar.
     * @return 1, 0 ou -1 caso o objeto passado como argumento seja, respetivamente,
     * maior, igual ou menor que o objeto que recebe a mensagem.
     */
    @Override
    public int compareTo(Object o) {
        TernoProdutoQuantidadeLista terno = (TernoProdutoQuantidadeLista) o;
        if (this.quantidade == terno.getQuantidade())
            return this.produto.compareTo(terno.getProduto());
        return Math.negateExact(((Integer) this.quantidade).compareTo(terno.getQuantidade()));

    }
}

package Model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Classe auxiliar que implementa um par de uma String e um inteiro. A String representa o código do produto
 * e o inteiro a quantidade vendida.
 *
 * @author Ricardo Gomes.
 * @author Rui Fernnades.
 */
public class ParProdutoQuantidade implements IParProdutoQuantidade, Comparable, Serializable {
    /**
     * Código do produto.
     */
    private String produto;

    /**
     * Quantidade.
     */
    private int quantidade;

    /**
     * Construtor por omissão para objetos da classe ParProdutoQuantidade.
     */
    public ParProdutoQuantidade() {
        this.produto = new String();
        this.quantidade = 0;
    }

    /**
     * Construtor parametrizado para objetos da classe ParProdutoQuantidade.
     *
     * @param produto    Código do produto.
     * @param quantidade Quantidade.
     */
    public ParProdutoQuantidade(String produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    /**
     * Construtor de cópia para objetos da classe ParProdutoQuantidade.
     *
     * @param par Instância da classe ParProdutoQuantidade a partir da qual se instancia um novo objeto.
     */
    public ParProdutoQuantidade(ParProdutoQuantidade par) {
        this.produto = par.getProduto();
        this.quantidade = par.getQuantidade();
    }

    /**
     * Método que devolve o código do produto.
     *
     * @return Código do produto.
     */
    public String getProduto() {
        return this.produto;
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
     * Implementação do método hashCode.
     *
     * @return Código hash.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.produto, this.quantidade});
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
        if(obj == this) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        ParProdutoQuantidade par = (ParProdutoQuantidade) obj;
        return this.produto.equals(par.getProduto()) &&
                this.quantidade == par.getQuantidade();
    }

    /**
     * Implementação do método clone.
     *
     * @return Cópia do objeto sobre o qual o método é chamado.
     */
    @Override
    public ParProdutoQuantidade clone() {
        return new ParProdutoQuantidade(this);
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
        ParProdutoQuantidade p = (ParProdutoQuantidade) o;
        if (this.quantidade == p.getQuantidade())
            return produto.compareTo(p.getProduto());
        return (Math.negateExact(((Integer) this.quantidade).compareTo(p.getQuantidade())));
    }
}

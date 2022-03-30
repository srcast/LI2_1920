package Model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Classe auxiliar que implementa um terno de dois inteiros e um double. O primeiro inteiro representa o
 * número de compras,o segundo o número de produtos distintos comprados e o double representa
 * a faturação com as vendas desses mesmos produtos.
 *
 * @author Ricardo Gomes.
 * @author Rui Fernandes.
 */
public class TernoComprasProdutosFaturacao implements ITernoComprasProdutosFaturacao, Serializable {
    /**
     * Número de compras.
     */
    private int nCompras;

    /**
     * Número de produtos distintos que o cliente comprou.
     */
    private int produtos;

    /**
     * Faturação com as vendas dos produtos.
     */
    private double faturacao;

    /**
     * Construtor por omissão para objetos da classe TernoComprasProdutosFaturacao.
     */
    public TernoComprasProdutosFaturacao() {
        this.nCompras = 0;
        this.produtos = 0;
        this.faturacao = 0.0;
    }

    /**
     * Construtor parametrizado para objetos da classe TernoComprasProdutosFaturacao.
     *
     * @param nCompras  Número de compras.
     * @param produtos  Número de produtos distintos que o cliente comprou.
     * @param faturacao Faturação com as vendas dos produtos.
     */
    public TernoComprasProdutosFaturacao(int nCompras, int produtos, double faturacao) {
        this.nCompras = nCompras;
        this.produtos = produtos;
        this.faturacao = faturacao;
    }

    /**
     * Construtor de cópia para objetos da classe TernoComprasProdutosFaturacao.
     *
     * @param terno Instância da classe TernoComprasProdutosFaturacao a partir da qual se
     *              instancia um novo objeto.
     */
    public TernoComprasProdutosFaturacao(TernoComprasProdutosFaturacao terno) {
        this.nCompras = terno.getnCompras();
        this.produtos = terno.getProdutos();
        this.faturacao = terno.getFaturacao();
    }

    /**
     * Método que devolve o número de compras.
     *
     * @return Número de compras.
     */
    public int getnCompras() {
        return this.nCompras;
    }

    /**
     * Método que devolve o número de produtos distintos que o cliente comprou.
     *
     * @return Número de produtos distintos que o cliente comprou.
     */
    public int getProdutos() {
        return this.produtos;
    }

    /**
     * Método que devolve a faturação.
     *
     * @return Faturação.
     */
    public double getFaturacao() {
        return this.faturacao;
    }

    /**
     * Implementação do método hashCode.
     *
     * @return Código hash.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.nCompras, this.produtos, this.faturacao});
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
        TernoComprasProdutosFaturacao terno = (TernoComprasProdutosFaturacao) obj;
        return this.nCompras == terno.getnCompras() &&
                this.produtos == terno.getProdutos() &&
                this.faturacao == terno.getFaturacao();
    }

    /**
     * Implementação do método clone.
     *
     * @return Cópia do objeto sobre o qual o método é chamado.
     */
    @Override
    public TernoComprasProdutosFaturacao clone() {
        return new TernoComprasProdutosFaturacao(this);
    }
}

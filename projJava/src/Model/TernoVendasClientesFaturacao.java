package Model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Classe que implementa um terno de dois inteiros e um double. O primeiro inteiro representa o número
 * de vendas, o segundo o número de clientes distintos que compraram o produto e o double representa
 * a faturaçãp.
 *
 * @author Ricardo Gomes.
 * @author Rui Fernandes.
 */
public class TernoVendasClientesFaturacao implements ITernoVendasClientesFaturacao, Serializable {
    /**
     * Número de vendas.
     */
    private int nVendas;

    /**
     * Número de clientes distintos que compraram o produto.
     */
    private int nClientes;

    /**
     * Faturação.
     */
    private double faturacao;

    /**
     * Construtor por omissão para objetos da classe TernoVendasClientesFaturacao.
     */
    public TernoVendasClientesFaturacao() {
        this.nVendas = 0;
        this.nClientes = 0;
        this.faturacao = 0.0;
    }

    /**
     * Construtor parametrizado para objetos da classe TernoVendasClientesFaturacao.
     *
     * @param nVendas Número de vendas.
     * @param nClientes Número de clientes distintos que compraram um produto.
     * @param faturacao Fsturação
     */
    public TernoVendasClientesFaturacao(int nVendas, int nClientes, double faturacao) {
        this.nVendas = nVendas;
        this.nClientes = nClientes;
        this.faturacao = faturacao;
    }

    /**
     * Construtor de cópia para objetos da classe TernoVendasClientesFaturacao.
     *
     * @param terno Instância da classe TernoVendasClientesFaturacao a partir da qual se instancia um
     * novo objeto.
     */
    public TernoVendasClientesFaturacao(TernoVendasClientesFaturacao terno) {
        this.nVendas = terno.getnVendas();
        this.nClientes = terno.getnClientes();
        this.faturacao = terno.getFaturacao();
    }

    /**
     * Método que devolve o número de vendas.
     *
     * @return Número de vendas.
     */
    public int getnVendas() {
        return this.nVendas;
    }

    /**
     * Método que devolve o número de clientes distintos que compraram um produto.
     *
     * @return Número de clientes distintos que compraram um produto.
     */
    public int getnClientes() {
        return this.nClientes;
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
        return Arrays.hashCode(new Object[]{this.nVendas, this.nClientes, this.faturacao});
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
        return super.equals(obj);
    }

    /**
     * Implementação do método clone.
     *
     * @return Cópia do objeto sobre o qual o método é chamado.
     */
    @Override
    public TernoVendasClientesFaturacao clone()  {
        return new TernoVendasClientesFaturacao(this);
    }

}

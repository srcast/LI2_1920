package Model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Classe auxiliar que implementa um par de inteiros. O primeiro representa o número de vendas e o
 * segundo representa número de clientes distintos que compraram o produto.
 *
 * @author Ricardo Gomes.
 * @author Rui Fernandes.
 */
public class ParVendasNClientes implements IParVendasNClientes, Serializable {
    /**
     * Número de vendas
     */
    private int vendas;
    /**
     * Número de clientes que compraram o produto
     */
    private int nClientes;

    /**
     * Construtor por omissão para objetos da classe ParVendasNClientes.
     */
    public ParVendasNClientes() {
        this.vendas = 0;
        this.nClientes = 0;
    }

    /**
     * Construtor parametrizado para objetos da classe ParVendasNClientes.
     *
     * @param vendas    Número de vendas.
     * @param nClientes Número de clientes.
     */
    public ParVendasNClientes(int vendas, int nClientes) {
        this.vendas = vendas;
        this.nClientes = nClientes;
    }

    /**
     * Construtor de cópia para objetos da classe ParVendasNClientes.
     *
     * @param par Instância da classe ParVendasNClientes a partir da qual se instancia um novo objeto.
     */
    public ParVendasNClientes(ParVendasNClientes par) {
        this.vendas = par.getVendas();
        this.nClientes = par.getnClientes();
    }

    /**
     * Método que devolve o número de vendas.
     *
     * @return Número de vendas.
     */
    public int getVendas() {
        return this.vendas;
    }

    /**
     * Método que devolve o número de clientes.
     *
     * @return Número de clientes.
     */
    public int getnClientes() {
        return this.nClientes;
    }

    /**
     * Implementação do método hashCode.
     *
     * @return Código hash.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.vendas, this.nClientes});
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
        ParVendasNClientes par = (ParVendasNClientes) obj;
        return this.vendas == par.getVendas() &&
                this.nClientes == par.getnClientes();
    }

    /**
     * Implementação do método clone.
     *
     * @return Cópia do objeto sobre o qual o método é chamado.
     */
    @Override
    public ParVendasNClientes clone() {
        return new ParVendasNClientes(this);
    }
}

package Model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Classe auxiliar que implementa um par de uma String e um double. A String representa o código
 * do cliente e o double representa a faturação.
 *
 * @author Ricardo Gomes.
 * @author Rui Fernandes,
 */
public class ParClienteFaturacao implements IParClienteFaturacao, Comparable, Serializable {
    /**
     * Código do cliente.
     */
    private String cliente;

    /**
     * Faturação.
     */
    private double faturacao;

    /**
     * Construtor por omissão para objetos da classe ParClienteFaturacao.
     */
    public ParClienteFaturacao() {
        this.cliente = new String();
        this.faturacao = 0.0;
    }

    /**
     * Construtor parametrizado para objetos da classe ParClienteFaturacao.
     *
     * @param cliente Código do cliente.
     * @param faturacao Faturação.
     */
    public ParClienteFaturacao(String cliente, double faturacao) {
        this.cliente = cliente;
        this.faturacao = faturacao;
    }

    /**
     * Construtor de cópia para objetos da classe ParClienteFaturacao.
     *
     * @param par Instância da classe ParClienteFaturacao a partir da qual se instancia um novo objeto.
     */
    public ParClienteFaturacao (ParClienteFaturacao par) {
        this.cliente = par.getCliente();
        this.faturacao = par.getFaturacao();
    }

    /**
     * Método que devolve o código do cliente.
     *
     * @return Código do cliente.
     */
    public String getCliente() {
        return this.cliente;
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
     * Método que atualiza o valor da faturaçõa.
     *
     * @param faturacao Valor a adicionar à faturação.
     */
    public void update(double faturacao) {
        this.faturacao += faturacao;
    }

    /**
     * Implementação do método hashCode.
     *
     * @return Código hash.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.cliente, this.faturacao});
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
        ParClienteFaturacao par = (ParClienteFaturacao) obj;
        return this.cliente.equals(par.getCliente()) &&
                this.faturacao == par.getFaturacao();
    }

    /**
     * Implementação do método clone.
     *
     * @return Cópia do objeto sobre o qual o método é chamado.
     */
    @Override
    public ParClienteFaturacao clone()  {
        return new ParClienteFaturacao(this);
    }

    /**
     * Implementação do método compareTo.
     *
     * @return 1, 0 ou -1 caso o objeto passado como argumento seja, respetivamente,
     * maior, igual ou menor que o objeto que recebe a mensagem.
     */
    @Override
    public int compareTo(Object o) {
        ParClienteFaturacao par = (ParClienteFaturacao) o;
        if (this.faturacao == par.getFaturacao())
            return this.cliente.compareTo(par.getCliente());
        return (Math.negateExact(((Double) this.faturacao).compareTo(par.getFaturacao())));
    }
}
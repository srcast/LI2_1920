package Model;

import java.io.Serializable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe auxiliar que implementa um par de uma String e um Set. A String corresponde ao código do
 * cliente e o Set corresponde ao conjunto dos códigos dos produtos que o este comprou.
 *
 * @author Ricardo Gomes.
 * @author Rui Fernandes.
 */
public class ParClienteProdutos implements IParClienteProdutos, Comparable, Serializable {
    /**
     * Código do cliente.
     */
    private String cliente;

    /**
     * Produtos que comprou.
     */
    private Set<String> produtos;

    /**
     * Construtor por omissão para objetos da classe ParClienteProdutos.
     */
    public ParClienteProdutos() {
        this.cliente = new String();
        this.produtos = new HashSet<>();
    }

    /**
     * Construtor parametrizado para objetos da classe ParClienteProdutos.
     *
     * @param cliente  Código do cliente.
     * @param produtos Set com os códigos dos produtos que o cliente comprou.
     */
    public ParClienteProdutos(String cliente, Set<String> produtos) {
        this.cliente = cliente;
        this.produtos = new HashSet<>(produtos);
    }

    /**
     * Construtor de cópia para objetos da classe ParClienteProdutos.
     *
     * @param par Instância da classe ParClienteProdutos a partir da qual se instancia um novo objeto.
     */
    public ParClienteProdutos(ParClienteProdutos par) {
        this.cliente = par.getCliente();
        this.produtos = par.getProdutos();
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
     * Método que devolve um Set com os códigos dos produtos comprados.
     *
     * @return Set com os códigos dos produtos comprados.
     */
    public Set<String> getProdutos() {
        return new HashSet<>(this.produtos);
    }

    /**
     * Implementação do método hashCode.
     *
     * @return Código hash.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.cliente, this.produtos});
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
        ParClienteProdutos par = (ParClienteProdutos) obj;
        return this.cliente.equals(par.getCliente()) &&
                this.produtos.equals(par.getProdutos());
    }

    /**
     * Implementação do método clone.
     *
     * @return Cópia do objeto sobre o qual o método é chamado.
     */
    @Override
    public ParClienteProdutos clone() {
        return new ParClienteProdutos(this);
    }

    /**
     * Implementação do método compareTo.
     *
     * @return 1, 0 ou -1 caso o objeto passado como argumento seja, respetivamente,
     * maior, igual ou menor que o objeto que recebe a mensagem.
     */
    @Override
    public int compareTo(Object o) {
        ParClienteProdutos p = (ParClienteProdutos) o;
        if (this.produtos.size() == p.getProdutos().size())
            return this.cliente.compareTo(p.getCliente());
        return (Math.negateExact(((Integer) this.produtos.size()).compareTo(p.getProdutos().size())));
    }
}

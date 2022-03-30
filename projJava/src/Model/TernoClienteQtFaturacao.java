package Model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Classe que implementa um terno de uma String, um inteiro e um double. A String representa
 * o código do cliente, o inteiro a quantidade comprada e o double a faturação.
 *
 * @author Ricardo Gomes.
 * @author Rui Fernandes.
 */
public class TernoClienteQtFaturacao implements ITernoClienteQtFaturacao, Comparable, Serializable {
    /**
     * Código do cliente.
     */
    private String cliente;

    /**
     * Quantidade.
     */
    private int quantidade;

    /**
     * Faturação.
     */
    private double faturacao;

    /**
     * Construtor por omissão para objetos da classe TernoClienteQtFaturacao.
     */
    public TernoClienteQtFaturacao() {
        this.cliente = new String();
        this.quantidade = 0;
        this.faturacao = 0.0;
    }

    /**
     * Construtor parametrizado para objetos da classe TernoClienteQtFaturacao.
     *
     * @param cliente    Código de cliente.
     * @param quantidade Quantidade.
     * @param faturacao  Faturação.
     */
    public TernoClienteQtFaturacao(String cliente, int quantidade, double faturacao) {
        this.cliente = cliente;
        this.quantidade = quantidade;
        this.faturacao = faturacao;
    }

    /**
     * Construtor de cópia para objetos da classe TernoClienteQtFaturacao.
     *
     * @param terno Instância da classe TernoClienteQtFaturacao a partir da qual se instancia um novo objeto
     */
    public TernoClienteQtFaturacao(TernoClienteQtFaturacao terno) {
        this.cliente = terno.getCliente();
        this.quantidade = terno.getQuantidade();
        this.faturacao = terno.getQuantidade();
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
     * Método que devolve a quantidade.
     *
     * @return Quantidade.
     */
    public int getQuantidade() {
        return this.quantidade;
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
     * Método que atualiza as variáveis de instância.
     *
     * @param quantidade Quantidade a adicionar.
     * @param faturacao  Faturação a adicionar.
     */
    public void update(int quantidade, double faturacao) {
        this.quantidade += quantidade;
        this.faturacao += faturacao;
    }

    /**
     * Implementação do método hashCode.
     *
     * @return Código hash.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.cliente, this.quantidade, this.faturacao});
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
        TernoClienteQtFaturacao terno = (TernoClienteQtFaturacao) obj;
        return this.cliente.equals(terno.getCliente()) &&
                this.quantidade == terno.getQuantidade() &&
                this.faturacao == terno.getFaturacao();
    }

    /**
     * Implementação do método clone.
     *
     * @return Cópia do objeto sobre o qual o método é chamado.
     */
    @Override
    public TernoClienteQtFaturacao clone() {
        return new TernoClienteQtFaturacao(this);
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
        TernoClienteQtFaturacao terno = (TernoClienteQtFaturacao) o;
        if (this.quantidade == terno.getQuantidade()) return this.cliente.compareTo(terno.getCliente());
        return Math.negateExact((((Integer) this.quantidade).compareTo(terno.getQuantidade())));
    }
}

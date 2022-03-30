package Model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Classe auxiliar que implementa um par de um inteiro e um double. O inteiro representa a
 * quantidade e o double representa a faturação.
 *
 * @author Ricardo Gomes.
 * @author Rui Fernandes.
 */
public class ParQtFaturacao implements IParQtFaturacao, Serializable {
    /**
     * Quantidade.
     */
    private int quantidade;

    /**
     * Faturação.
     */
    private double faturacao;

    /**
     * Construtor por omissão para objetos da classe ParQtFaturacao.
     */
    public ParQtFaturacao() {
        this.quantidade = 0;
        this.faturacao = 0.0;
    }

    /**
     * Construtor parametrizado para objetos da classe ParQtFaturacao.
     *
     * @param quantidade Quantidade.
     * @param faturacao  Faturação.
     */
    public ParQtFaturacao(int quantidade, double faturacao) {
        this.quantidade = quantidade;
        this.faturacao = faturacao;
    }

    /**
     * Construtor de cópia para objetos da classe ParQtFaturacao.
     *
     * @param par Instância da classe ParQtFaturacao a partir da qual se instancia um novo objeto.
     */
    public ParQtFaturacao(ParQtFaturacao par) {
        this.quantidade = par.getQuantidade();
        this.faturacao = par.getFaturacao();
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
     * @param quantidade Valor a adicionar à quantidade.
     * @param faturacao  Valor a adicionar à faturação.
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
        return Arrays.hashCode(new Object[]{this.quantidade, this.faturacao});
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
        ParQtFaturacao par = (ParQtFaturacao) obj;
        return this.quantidade == par.getQuantidade() &&
                this.faturacao == par.getFaturacao();
    }

    /**
     * Implementação do método clone.
     *
     * @return Cópia do objeto sobre o qual o método é chamado.
     */
    @Override
    public ParQtFaturacao clone() {
        return new ParQtFaturacao(this);
    }

}

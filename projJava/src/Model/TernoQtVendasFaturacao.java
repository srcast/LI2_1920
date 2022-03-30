package Model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Classe auxiliar que implementa um terno de dois inteiros e um double. Os inteiros representam
 * a quantidade comprada e o número de vendas, e o double representa a faturação obtida.
 *
 * @author Ricardo Gomes.
 * @author Rui Fernandes.
 */
public class TernoQtVendasFaturacao implements ITernoQtVendasFaturacao, Serializable {
    /**
     * Quantidade.
     */
    private int quantidade;

    /**
     * Vendas.
     */
    private int vendas;

    /**
     * Faturação
     */
    private double faturacao;

    /**
     * Construtor por omissão para objetos da classe TernoQtVendasFaturacao.
     */
    public TernoQtVendasFaturacao() {
        this.quantidade = 0;
        this.vendas = 0;
        this.faturacao = 0.0;
    }

    /**
     * Construtor parametrizado para objetos da classe TernoQtVendasFaturacao.
     *
     * @param quantidade Quantidade.
     * @param vendas     Vendas.
     * @param faturacao  Faturação.
     */
    public TernoQtVendasFaturacao(int quantidade, int vendas, double faturacao) {
        this.quantidade = quantidade;
        this.vendas = vendas;
        this.faturacao = faturacao;
    }

    /**
     * Construtor parametrizado para objetos da classe TernoQtVendasFaturacao.
     *
     * @param venda Venda que contém a informação a inserir.
     */
    public TernoQtVendasFaturacao(IVenda venda) {
        this.quantidade = venda.getQuantidade();
        this.vendas = 1;
        this.faturacao = venda.getFaturacao();
    }

    /**
     * Construtor de cópia para objetos da classe TernoQtVendasFaturacao.
     *
     * @param terno Instância da classe TernoQtVendasFaturacao a partir da qual se instancia um novo objeto
     */
    public TernoQtVendasFaturacao(TernoQtVendasFaturacao terno) {
        this.quantidade = terno.getQuantidade();
        this.vendas = terno.getVendas();
        this.faturacao = terno.getFaturacao();
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
     * Método que devolve o número de vendas.
     *
     * @return Número de vendas.
     */
    public int getVendas() {
        return this.vendas;
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
        this.vendas++;
        this.faturacao += faturacao;
    }

    /**
     * Implementação do método hashCode.
     *
     * @return Código hash.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.quantidade, this.vendas, this.faturacao});
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
        TernoQtVendasFaturacao terno = (TernoQtVendasFaturacao) obj;
        return this.quantidade == terno.getQuantidade() &&
                this.vendas == terno.getVendas() &&
                this.faturacao == terno.getFaturacao();
    }

    /**
     * Implementação do método clone.
     *
     * @return Cópia do objeto sobre o qual o método é chamado.
     */
    @Override
    public TernoQtVendasFaturacao clone() {
        return new TernoQtVendasFaturacao(this);
    }

}

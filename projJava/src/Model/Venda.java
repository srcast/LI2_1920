package Model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Classe que implementa a estrutura de uma venda, lida a partir do ficheiro de vendas.
 *
 * @author Ricardo Gomes.
 * @author Rui Fernandes,
 */
public class Venda implements IVenda, Serializable {
    /**
     * Código do produto.
     */
    private String produto;

    /**
     * Código do cliente.
     */
    private String cliente;

    /**
     * Preço Unitário.
     */
    private double precoUnit;

    /**
     * Quantidade.
     */
    private int quantidade;

    /**
     * Tipo de venda (Normal/Promocao).
     */
    private char tipo;

    /**
     * Mês.
     */
    private int mes;

    /**
     * Filial.
     */
    private int filial;

    /**
     * Construtor por omissão para objetos da classe Venda.
     */
    public Venda() {
        this.produto = new String();
        this.cliente = new String();
        this.precoUnit = 0.0;
        this.quantidade = 0;
        this.tipo = ' ';
        this.mes = 0;
        this.filial = 0;
    }

    /**
     * Construtor parametrizado para objetos da classe Venda.
     *
     * @param produto    Código do produto.
     * @param cliente    Código do cliente.
     * @param precoUnit  Preço unitário.
     * @param quantidade Quantidade.
     * @param tipo       Tipo de venda.
     * @param mes        Mês.
     * @param filial     Filial.
     */
    public Venda(String produto, String cliente, double precoUnit, int quantidade, char tipo, int mes, int filial) {
        this.produto = produto;
        this.cliente = cliente;
        this.precoUnit = precoUnit;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.mes = mes;
        this.filial = filial;
    }

    /**
     * Construtor parametrizado para objetos da classe Venda.
     *
     * @param venda String com a informação de uma venda.
     */
    public Venda(String venda) {
        String[] campos = venda.split(" ");
        this.produto = campos[0];
        this.precoUnit = Double.parseDouble(campos[1]);
        this.quantidade = Integer.parseInt(campos[2]);
        this.tipo = campos[3].toCharArray()[0];
        this.cliente = campos[4];
        this.mes = Integer.parseInt(campos[5]);
        this.filial = Integer.parseInt(campos[6]);
    }

    /**
     * Construtor de cópia para objetos da classe Venda.
     *
     * @param venda Instância da classe Venda a partir da qual se instancia um novo objeto.
     */
    public Venda(Venda venda) {
        this.produto = venda.getProduto();
        this.cliente = venda.getCliente();
        this.precoUnit = venda.getPrecoUnit();
        this.quantidade = venda.getQuantidade();
        this.tipo = venda.getTipo();
        this.mes = venda.getMes();
        this.filial = venda.getFilial();
    }

    /**
     * Método que devolve o cósido do produto comprado.
     *
     * @return Código do produto.
     */
    public String getProduto() {
        return this.produto;
    }

    /**
     * Método que devolve o cliente que efetuou a compra.
     *
     * @return Código do cliente.
     */
    public String getCliente() {
        return this.cliente;
    }

    /**
     * Método que devolve o preço unitário do produto comprado.
     *
     * @return Preço unitário.
     */
    public double getPrecoUnit() {
        return this.precoUnit;
    }

    /**
     * Método que devolve a quantidade comprada.
     *
     * @return Quantidade comprada.
     */
    public int getQuantidade() {
        return this.quantidade;
    }

    /**
     * Método que devolve o tipo de venda (Normal/Promoção).
     *
     * @return Tipo de venda (Normal/Promoção).
     */
    public char getTipo() {
        return this.tipo;
    }

    /**
     * Método que devolve o mês em que a venda ocorreu.
     *
     * @return Mês em que a venda ocorreu.
     */
    public int getMes() {
        return this.mes;
    }

    /**
     * Método que devolve a filial em que a venda ocorreu.
     *
     * @return Filial em que a venda ocorreu.
     */
    public int getFilial() {
        return this.filial;
    }

    /**
     * Método que devolve a faturação da venda em questão.
     *
     * @return Faturação da venda.
     */
    public double getFaturacao() {
        return this.quantidade * this.precoUnit;
    }

    /**
     * Implementação do método hashCode.
     *
     * @return Código hash.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.produto, this.cliente, this.precoUnit, this.quantidade,
                this.tipo, this.mes, this.filial});
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
        Venda venda = (Venda) obj;
        return this.produto.equals(venda.getProduto()) &&
                this.cliente.equals(venda.getCliente()) &&
                this.precoUnit == venda.getPrecoUnit() &&
                this.quantidade == venda.getQuantidade() &&
                this.tipo == venda.getTipo() &&
                this.mes == venda.getMes() &&
                this.filial == venda.filial;
    }

    /**
     * Implementação do método clone.
     *
     * @return Cópia do objeto sobre o qual o método é chamado.
     */
    @Override
    public Venda clone() {
        return new Venda(this);
    }
}
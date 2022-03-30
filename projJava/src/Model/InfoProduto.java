package Model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe que representa a informação de um produto, nomeadamento a faturação, quantidade e
 * número de vendas.
 *
 * @author Ricardo Gomes.
 * @author Rui Fernandes,
 */
public class InfoProduto implements IInfoProduto, Serializable {
    /**
     * Código do produto.
     */
    private String produto;

    /**
     * Booleano que permite saber se o produto foi comprado.
     */
    private boolean comprado;

    /**
     * Número de vendas, por mes e filial.
     */
    private int[][] nVendas;

    /**
     * Faturação, por mês e filial.
     */
    private double[][] faturacao;

    /**
     * Quantidade, por mês.
     */
    private int[] quantidade;

    /**
     * Construtor por omissão para objetos da classe InfoProduto.
     */
    public InfoProduto() {
        this.produto = new String();
        this.comprado = false;
        this.nVendas = new int[Const.N_MESES][Const.N_FILIAIS];
        this.faturacao = new double[Const.N_MESES][Const.N_FILIAIS];
        this.quantidade = new int[Const.N_MESES];
    }

    /**
     * Construtor parametrizado para objetos da classe InfoProduto.
     *
     * @param produto Código do produto.
     */
    public InfoProduto(String produto) {
        this.produto = produto;
        this.comprado = false;
        this.nVendas = new int[Const.N_MESES][Const.N_FILIAIS];
        this.faturacao = new double[Const.N_MESES][Const.N_FILIAIS];
        this.quantidade = new int[Const.N_MESES];
    }

    /**
     * Construtor parametrizado para objetos da classe InfoProduto.
     *
     * @param produto    Código do produto.
     * @param comprado   Booleano que permite saber se o produto foi comprado.
     * @param nVendas    Número de vendas, por mes e filial.
     * @param faturacao  Faturação, por mês e filial.
     * @param quantidade Quantidade, por mês.
     */
    public InfoProduto(String produto, boolean comprado, int[][] nVendas, double[][] faturacao, int[] quantidade) {
        this.produto = produto;
        this.comprado = comprado;
        this.nVendas = nVendas.clone();
        this.faturacao = faturacao.clone();
        this.quantidade = quantidade.clone();
    }

    /**
     * Construtor de cópia para objetos da classe InfoProduto.
     *
     * @param info Instância da classe InfoProduto a partir da qual se instancia um novo objeto.
     */
    public InfoProduto(InfoProduto info) {
        this.produto = info.getProduto();
        this.comprado = info.isComprado();
        this.nVendas = info.getnVendas();
        this.faturacao = info.getFaturacao();
        this.quantidade = info.getQuantidade();
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
     * Método que determina se um produto foi comprado ou não.
     *
     * @return true caso o produto tenha sido comprado, false caso contrário.
     */
    public boolean isComprado() {
        return this.comprado;
    }

    /**
     * Método que devolve uma matriz com o número de vendas, por mês e filial.
     *
     * @return Matriz com o número de vendas.
     */
    public int[][] getnVendas() {
        int[][] r = new int[Const.N_MESES][Const.N_FILIAIS];
        for(int i=0; i<Const.N_MESES; i++)
            for(int j=0; j<Const.N_FILIAIS; j++)
                r[i][j] = this.nVendas[i][j];
        return r;
    }

    /**
     * Método que devolve uma matriz com a faturação, por mês e filial.
     *
     * @return Matriz com a faturação.
     */
    public double[][] getFaturacao() {
        double[][] r = new double [Const.N_MESES][Const.N_FILIAIS];
        for(int i=0; i<Const.N_MESES; i++)
            for(int j=0; j<Const.N_FILIAIS; j++)
                r[i][j] = this.faturacao[i][j];
        return r;
    }

    /**
     * Método que devolve a quantidade comprada por mês.
     *
     * @return Quantidade comprada por mês.
     */
    public int[] getQuantidade() {
        int[] r = new int[Const.N_MESES];
        for(int i=0; i<Const.N_MESES; i++)
            r[i] = this.quantidade[i];
        return r;
    }

    /**
     * Método que atualiza a informação de um produto com a informação de uma venda.
     *
     * @param venda Venda com informação a adicionar à estrutura.
     */
    public void update(IVenda venda) {
        this.comprado = true;
        this.nVendas[venda.getMes() - 1][venda.getFilial() - 1]++;
        this.faturacao[venda.getMes() - 1][venda.getFilial() - 1] += venda.getFaturacao();
        this.quantidade[venda.getMes() - 1] += venda.getQuantidade();
    }

    /**
     * Método que determina o número total de vendas de um determinado produto.
     *
     * @return Número total de vendas.
     */
    public int getnVendasTotal() {
        int r = 0;
        for (int i = 0; i < Const.N_MESES; i++)
            for (int j = 0; j < Const.N_FILIAIS; j++)
                r += this.nVendas[i][j];
        return r;
    }

    /**
     * Método que detemrina a faturação total de um determinado produto.
     *
     * @return Faturação total.
     */
    public double getFaturacaoTotal() {
        double r = 0;
        for (int i = 0; i < Const.N_MESES; i++)
            for (int j = 0; j < Const.N_FILIAIS; j++)
                r += this.faturacao[i][j];
        return r;
    }

    /**
     * Método que determina a quantidade total vendida de um determinado produto.
     *
     * @return Quantidade total vendida.
     */
    public int getQuantidadeTotal() {
        return Arrays.stream(this.quantidade).sum();
    }

    /**
     * Método que determina o número de vendas para um dado mês.
     *
     * @param mes Mês.
     * @return Número de vendas.
     */
    public int nVendasMensal(int mes) {
        int r = 0;
        for(int i=0; i<Const.N_FILIAIS; i++)
            r += this.nVendas[mes-1][i];
        return r;
    }

    /**
     * Método que, para um dado produto, determina a faturação total em cada mês para uma dada filial.
     *
     * @param filial Filial considerada.
     * @return Map em que a cada mês se associa o total faturado nesse mês.
     */
    public Map<Integer, Double> getFaturacaoFilial(int filial) {
        Map<Integer, Double> r = new HashMap<>();
        for (int i = 0; i < Const.N_MESES; i++)
            r.put(i, this.faturacao[i][filial - 1]);
        return r;
    }

    /**
     * Método auxiliar que calcula a faturação de um produto durante um determinado mês.
     *
     * @param mes Mês considerado.
     * @return Faturação mensal.
     */
    private double faturacaoMensal(int mes) {
        double r = 0;
        for (int i = 0; i < Const.N_FILIAIS; i++)
            r += this.faturacao[mes - 1][i];
        return r;
    }

    /**
     * Método que calcula a faturação mensal de um produto para cada um dos meses considerados.
     *
     * @return Map em que a cada mês se associa o total faturado nesse mês.
     */
    public Map<Integer, Double> faturacaoMensal() {
        Map<Integer, Double> r = new HashMap<>();
        for (int i = 0; i < Const.N_MESES; i++)
            r.put(i+1, faturacaoMensal(i+1));
        return r;
    }

    /**
     * Implementação do método hashCode.
     *
     * @return Código hash.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.produto, this.comprado, this.nVendas,
                this.faturacao, this.quantidade});
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
        InfoProduto info = (InfoProduto) obj;
        return this.produto.equals(info.getProduto()) &&
                this.comprado == info.isComprado() &&
                this.nVendas.equals(info.getnVendas()) &&
                this.faturacao.equals(info.getFaturacao()) &&
                this.quantidade.equals(info.getQuantidade());
    }

    /**
     * Implementação do método clone.
     *
     * @return Cópia do objeto sobre o qual o método é chamado, o que garante o encapsulamento de dados.
     */
    @Override
    public InfoProduto clone() {
        return new InfoProduto(this);
    }
}

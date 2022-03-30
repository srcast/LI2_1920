package Model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Classe que representa a informação de um cliente num determinado mês, nomeadamente quais os produtos
 * que comprou nesse mês e a respetiva informação relativa às vendas.
 *
 * @author Ricardo Gomes.
 * @author Rui Fernandes.
 */
public class InfoMensalCliente implements IInfoMensalCliente, Serializable {
    /**
     * Map em que a cada código de um produto se associa um terno contendo a
     * quantidade, número de vendas e faturação desse produto.
     */
    private Map<String, ITernoQtVendasFaturacao> produtos;

    /**
     * Construtor vazio para objetos da classe InfoMensalCliente.
     */
    public InfoMensalCliente() {
        this.produtos = new HashMap<>();
    }

    /**
     * Construtor parametrizado para objetos da classe InfoMensalCliente.
     *
     * @param produtos Map em que a cada código de um produto se associa um terno contendo a
     *                 quantidade, número de vendas e faturação desse produto.
     */
    public InfoMensalCliente(Map<String, ITernoQtVendasFaturacao> produtos) {
        this.produtos = new HashMap<>();
        produtos.forEach((k, v) -> this.produtos.put(k, new TernoQtVendasFaturacao(v.getQuantidade(),
                v.getVendas(), v.getFaturacao())));
    }

    /**
     * Construtor de cópia para objetos da classe InfoMensalCliente.
     *
     * @param info Instância da classe InfoMensalCliente a partir da qual se instancia um novo objeto.
     */
    public InfoMensalCliente(InfoMensalCliente info) {
        this.produtos = info.getProdutos();
    }

    /**
     * Método que devolve um Map em que a cada código de um produto se associa um terno contendo a
     * quantidade, número de vendas e faturação desse produto.
     *
     * @return Map em que a cada código de um produto se associa um terno contendo a quantidade, número de vendas
     * e faturação desse produto.
     */
    public Map<String, ITernoQtVendasFaturacao> getProdutos() {
        Map<String, ITernoQtVendasFaturacao> r = new HashMap<>();
        this.produtos.forEach((k, v) -> r.put(k, new TernoQtVendasFaturacao(v.getQuantidade(),
                v.getVendas(), v.getFaturacao())));
        return r;
    }

    /**
     * Método que testa se um cliente comprou um determinado produto no mês em questão.
     *
     * @param produto Código do produto.
     * @return true caso o cliente tenha comprado o produto nesse mês, false caso contrário.
     */
    public boolean contains(String produto) {
        return this.produtos.containsKey(produto);
    }

    /**
     * Método que, dada uma venda, atualiza a informação mensal de um cliente com a iformação dessa venda.
     *
     * @param venda Venda com informação a adicionar à estrutura.
     */
    public void insereVenda(IVenda venda) {
        ITernoQtVendasFaturacao terno = this.produtos.get(venda.getProduto());
        if (terno == null) this.produtos.put(venda.getProduto(), new TernoQtVendasFaturacao(venda));
        else terno.update(venda.getQuantidade(), venda.getFaturacao());
    }

    /**
     * Método que devolve um Set que contém os códigos dos produtos que o cliente comprou no mês me questão.
     *
     * @return Set com os códigos dos produtos
     */
    public Set<String> getCodProdutos() {
        return this.produtos.keySet();
    }

    /**
     * Método que calcula a quantidade total de produtos que o cliente comprou no mês em questão.
     *
     * @return Qantidade total de produtos.
     */
    public int getQuantidadeTotal() {
        return this.produtos.values().stream().mapToInt(ITernoQtVendasFaturacao::getQuantidade).sum();
    }

    /**
     * Método que calcula o número total de compras que o cliente fez no mês em questão.
     *
     * @return Número total de compras.
     */
    public int getNCompras() {
        return this.produtos.values().stream().mapToInt(ITernoQtVendasFaturacao::getVendas).sum();
    }

    /**
     * Método que calcula o total faturado com as compras que o cliente fez no mês em questão.
     *
     * @return Total faturado.
     */
    public double getFaturacaoTotal() {
        return this.produtos.values().stream().mapToDouble(ITernoQtVendasFaturacao::getFaturacao).sum();
    }

    /**
     * Método que etermina a quantidade vendida e a faturação de
     * um dado produto para o mês em questão
     *
     * @param produto Código do produto.
     * @return Par cujas componentes são a quantidade e a faturação.
     */
    public IParQtFaturacao getQtFaturacao(String produto) {
        try {
            ITernoQtVendasFaturacao terno = this.produtos.get(produto);
            return new ParQtFaturacao(terno.getQuantidade(), terno.getFaturacao());
        } catch (NullPointerException e) {
            return new ParQtFaturacao();
        }
    }

    /**
     * Método que devolve um Map em que a cada código de produto se associa a quantidade
     * vendida no mês considerado.
     *
     * @return Map em que a cada código de produto se associa a quantidade.
     */
    public Map<String, Integer> mapProdQt() {
        Map<String, Integer> r = new HashMap<>();
        this.produtos.forEach((k, v) -> r.put(k, v.getQuantidade()));
        return r;
    }

    /**
     * Implementação do método hashCode.
     *
     * @return Código hash.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.produtos});
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
        InfoMensalCliente info = (InfoMensalCliente) obj;
        return this.produtos.equals(info.getProdutos());
    }

    /**
     * Implementação do método clone.
     *
     * @return Cópia do objeto sobre o qual o método é chamado.
     */
    @Override
    public InfoMensalCliente clone() {
        return new InfoMensalCliente(this);
    }
}

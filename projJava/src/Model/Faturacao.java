package Model;

import javax.swing.plaf.PanelUI;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Classe responsável pela resposta eficiente a questões quantitativas que relacionam os produtos e
 * às suas vendas, guardando o número de vendas e o valor total de faturação. Esta classe referencia todos
 * os produtos, mesmo os que nunca foram vendidos e não contém qualquer referência a clientes.
 *
 * @author Ricardo Gomes.
 * @author Rui Fernandes.
 */
public class Faturacao implements IFaturacao, Serializable {
    /**
     * Map em que a cada código de produto se associa uma estrutura com informação
     * sobre as suas vendas.
     */
    private Map<String, IInfoProduto> faturacao;

    /**
     * Método que devolve informação sobre um produto.
     *
     * @param produto Código do produto.
     * @return Informação sobre um produto.
     */
    public IInfoProduto getInfoProduto(String produto) {
        IInfoProduto aux = this.faturacao.get(produto);
        return new InfoProduto(aux.getProduto(), aux.isComprado(), aux.getnVendas(), aux.getFaturacao(),
                aux.getQuantidade());
    }

    /**
     * Consturtor por omissão para objetos da classe Faturacao.
     */
    public Faturacao() {
        this.faturacao = new HashMap<>();
    }

    /**
     * Construtor parametrizado para objetos da classe Faturacao.
     *
     * @param faturacao Map com informação sobre a faturação da cadeia de distrinuição.
     */
    public Faturacao(Map<String, IInfoProduto> faturacao) {
        this.faturacao = new HashMap<>();
        faturacao.forEach((k, v) -> this.faturacao.put(k, new InfoProduto(v.getProduto(),
                v.isComprado(), v.getnVendas(), v.getFaturacao(), v.getQuantidade())));
    }

    /**
     * Construtor parametrizado para objetos da classe Faturacao.
     *
     * @param catalogo Catálogo com os códigos de todos produtos.
     */
    public Faturacao(ICatalogo catalogo) {
        this.faturacao = catalogo.getCodigos().stream()
                .collect(Collectors.toMap(Function.identity(), InfoProduto::new));
    }

    /**
     * Construtor de cópia para objetos da classe Faturacao.
     *
     * @param faturacao Instância da classe Faturacao a partir da qual se instancia um novo objeto.
     */
    public Faturacao(Faturacao faturacao) {
        this.faturacao = faturacao.getFaturacao();
    }

    /**
     * Método que devolve um Map em que a cada código de produto se associa uma estrutura com
     * informação sobre as suas vendas.
     *
     * @return Map que a cada produto associa uma estrutura com a informação sobre as suas vendas.
     */
    public Map<String, IInfoProduto> getFaturacao() {
        Map<String, IInfoProduto> r = new HashMap<>();
        this.faturacao.forEach((k, v) -> r.put(k, new InfoProduto(v.getProduto(), v.isComprado(),
                v.getnVendas(), v.getFaturacao(), v.getQuantidade())));
        return r;
    }

    /**
     * Método que atualiza a estrutura faturação com a informação de uma venda.
     *
     * @param venda Venda com informação a adicionar à estrutura.
     */
    public void update(IVenda venda) {
        this.faturacao.get(venda.getProduto()).update(venda);
    }

    /**
     * Método que determina o número de produtos que foram comprados.
     *
     * @return Número de produtos que foram comprados.
     */
    public int produtosCompradosCount() {
        return (int) this.faturacao.values().stream().filter(IInfoProduto::isComprado).count();
    }

    /**
     * Método que devolve uma lista com os códigos dos produtos que não foram comprados.
     *
     * @return Lista com os códigos dos produtos não comprados.
     */
    public List<String> produtosNaoComprados() {
        return this.faturacao.values().stream().filter(p -> !p.isComprado())
                .map(IInfoProduto::getProduto).sorted().collect(Collectors.toList());
    }

    /**
     * Método que calcula a quantidade total de produtos vendida.
     *
     * @return Quantidade total.
     */
    public int quantidadeTotal() {
        return this.faturacao.values().stream().mapToInt(IInfoProduto::getQuantidadeTotal).sum();
    }

    /**
     * Método que calcula o número total de vendas.
     *
     * @return Número total de vendas.
     */
    public int nVendasTotal() {
        return this.faturacao.values().stream().mapToInt(IInfoProduto::getnVendasTotal).sum();
    }

    /**
     * Método que calcula a faturação total.
     *
     * @return Faturação total.
     */
    public double faturacaoTotal() {
        return this.faturacao.values().stream().mapToDouble(IInfoProduto::getFaturacaoTotal).sum();
    }

    /**
     * Método que determina o total faturado em cada mês.
     *
     * @return Map em que a cada mês se associa o total faturado nesse mês.
     */
    public Map<Integer, Double> faturacaoMes() {
        return this.faturacao.values().stream()
                .flatMap(e -> e.faturacaoMensal().entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Double::sum));
    }

    /**
     * Método que, para uma determinada filial, determina a faturação mensal para cada um dos
     * meses considerados.
     *
     * @param filial Filial considerada.
     * @return Map em que a cada mês se associa o total faturado nesse mês.
     */
    public Map<Integer, Double> faturacaoMesFilial(int filial) {
        return this.faturacao.values().stream()
                .flatMap(e -> e.getFaturacaoFilial(filial).entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Double::sum));
    }

    /**
     * Implementação do método hashCode,
     *
     * @return Código hash.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.faturacao});
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
        Faturacao faturacao = (Faturacao) obj;
        return this.faturacao.equals(faturacao.getFaturacao());
    }

    /**
     * Implementação do método clone.
     *
     * @return Cópia do objeto sobre o qual o método é chamado.
     */
    @Override
    public Faturacao clone() {
        return new Faturacao(this);
    }
}

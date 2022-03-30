package Model;

import java.io.Serializable;
import java.util.*;

/**
 * Classe que implementa a estrutura de uma filial da cadeia de distribuição. Importa referir
 * que esta classe apenas faz referência a clientes que, de facto, efetuaram compras.
 *
 * @author Ricardo Gomes.
 * @author Rui Fernandes.
 */
public class Filial implements IFilial, Serializable {
    /**
     * Lista cujos elementos são um Map em que a cada codigo de cliente se faz corresponder a informação
     * mensal desse mesmo cliente para o dado mês
     */
    private List<Map<String, IInfoMensalCliente>> clientes;

    /**
     * Construtor por omissão para objetos da classe Filial.
     */
    public Filial() {
        this.clientes = new ArrayList<>(Const.N_MESES);
        for (int i = 0; i < Const.N_MESES; i++)
            this.clientes.add(new HashMap<>());
    }

    /**
     * Construtor parametrizado para objetos da classe Filial.
     *
     * @param clientes Lista com informação mensal de cada cliente,
     */
    public Filial(List<Map<String, IInfoMensalCliente>> clientes) {
        this.clientes = new ArrayList<>(Const.N_MESES);
        for(Map<String, IInfoMensalCliente> m : clientes) {
            Map<String, IInfoMensalCliente> aux = new HashMap<>();
            m.forEach((k, v) -> aux.put(k, new InfoMensalCliente(v.getProdutos())));
            this.clientes.add(aux);
        }
    }

    /**
     * Construtor de cópia para objetos da classe Filial.
     *
     * @param filial Instância da classe Filial a partir da qual se instancia um novo objeto.
     */
    public Filial(Filial filial) {
        this.clientes = filial.getClientes();
    }

    /**
     * Método que devolve uma lista cujos elementos são um map que a cada codigo de cliente associa a
     * informação mensal desse mesmo cliente para o dado mês
     *
     * @return lista com informação mensal de cada cliente para cada um dos meses considerados.
     */
    public List<Map<String, IInfoMensalCliente>> getClientes() {
        List<Map<String, IInfoMensalCliente>> r = new ArrayList<>(Const.N_MESES);
        for(Map<String, IInfoMensalCliente> m : this.clientes) {
            Map<String, IInfoMensalCliente> aux = new HashMap<>();
            m.forEach((k, v) -> aux.put(k, new InfoMensalCliente(v.getProdutos())));
            r.add(aux);
        }
        return r;
    }

    /**
     * Método que atualiza a estrutura de uma filial com a informação de uma venda.
     *
     * @param venda Venda com informação a adicionar à estrutura.
     */
    public void insereVenda(IVenda venda) {
        Map<String, IInfoMensalCliente> aux = this.clientes.get(venda.getMes() - 1);
        IInfoMensalCliente info = aux.get(venda.getCliente());
        if (info == null) {
            info = new InfoMensalCliente();
            info.insereVenda(venda);
            aux.put(venda.getCliente(), info);
        } else info.insereVenda(venda);
    }

    /**
     * Método que devolve o número de compras que um cliente fez num dado mês.
     *
     * @param cliente Código do cliente.
     * @param mes Mês.
     * @return Número de compras.
     */
    public int nComprasCliente(String cliente, int mes) {
        IInfoMensalCliente info = this.clientes.get(mes - 1).get(cliente);
        if (info == null) return 0;
        else return info.getNCompras();
    }

    /**
     * Método que determina o total faturado com as vendas de um cliente num dado mês.
     *
     * @param cliente Código do cliente.
     * @param mes Mês.
     * @return Total faturado.
     */
    public double faturacaoCliente(String cliente, int mes) {
        IInfoMensalCliente info = this.clientes.get(mes - 1).get(cliente);
        if (info == null) return 0;
        else return info.getFaturacaoTotal();
    }

    /**
     * Método que devolve um Set com os códigos dos produtos que um cliente comprou num dado mªes.
     *
     * @param cliente Código do cliente.
     * @param mes Mês.
     * @return Set com os códigos dos produtos comprados pelo cliente.
     */
    public Set<String> produtosDif(String cliente, int mes) {
        try {
            return this.clientes.get(mes - 1).get(cliente).getCodProdutos();
        } catch (NullPointerException e) {
            return new HashSet<>();
        }
    }

    /**
     * Método que devolve um Set com os códigos dos clientes que fizeram compras num determinado mês.
     *
     * @param mes Mês.
     * @return Set com os códigos dos clientes que fizeram compras.
     */
    public Set<String> clientesDif(int mes) {
        return this.clientes.get(mes - 1).keySet();
    }

    /**
     * Método que devolve um Set com os códigos dos diferentes clientes qe compraram um produto
     * num dado mês.
     *
     * @param produto Código do produto.
     * @param mes Mês.
     * @return Set com os códigos dos clientes.
     */
    public Set<String> clientesDifProd(String produto, int mes) {
        Set<String> r = new HashSet<>();
        Map<String, IInfoMensalCliente> m = this.clientes.get(mes-1);
        for (String s: m.keySet())
            if (m.get(s).contains(produto)) r.add(s);
        return r;
    }

    /**
     * Método que devolve um set com os códigos dos clientes que efetuaram compras na filial.
     *
     * @return Set com os códigos dos clientes.
     */
    public Set<String> clientesCompradores() {
        Set<String> r = new HashSet<>();
        for (int i = 0; i < Const.N_MESES; i++)
            r.addAll(this.clientes.get(i).keySet());
        return r;
    }

    /**
     * Método que determina os produtos mais comprados de um determinado cliente, devolvendo um Map em que
     * a cada código de cliente se associa a quantidade comprada.
     *
     * @param cliente Código do cliente,
     * @return Map em que a cada código de cliente se associa a quantidade comprada.
     */
    public Map<String, Integer> produtosMaisComprados(String cliente) {
        Map<String, Integer> r = new HashMap<>();
        for(int i=0; i<Const.N_MESES; i++) {
            IInfoMensalCliente info = this.clientes.get(i).get(cliente);
            try {
                Map<String, Integer> m2 = info.mapProdQt();
                for(String produto : m2.keySet()) {
                    Integer quantidade = r.get(produto);
                    if(quantidade == null) r.put(produto, m2.get(produto));
                    else r.put(produto, quantidade + m2.get(produto));
                }
            } catch (NullPointerException e) {}
        }
        return r;
    }

    /**
     * Método que determina os clientes que mais gastaram dinheiro na filial.
     *
     * @return Set com os códigos dos clientes e respetiva faturação.
     * */
    public Set<IParClienteFaturacao> maioresCompradoresFilial() {
        Map<String, Double> m = new HashMap<>();
        for (int i = 0; i < Const.N_MESES; i++) {
            Map<String, IInfoMensalCliente> m2 = this.clientes.get(i);
            for (String cliente : m2.keySet()) {
                Double faturacao = m.get(cliente);
                if (faturacao == null) m.put(cliente, m2.get(cliente).getFaturacaoTotal());
                else m.put(cliente, faturacao + m2.get(cliente).getFaturacaoTotal());
            }
        }
        Set<IParClienteFaturacao> r = new TreeSet<>();
        for (String cliente : m.keySet())
            r.add(new ParClienteFaturacao(cliente, m.get(cliente)));
        return r;
    }

    /**
     * Método que determina os clientes que mais compraram um produto, por faturação.
     *
     * @param produto Código do produto.
     * @return Map em que a cada código de cliente se associa o respetivo par com quantidade e total faturado.
     */
    public Map<String, IParQtFaturacao> maioresCompradoresProduto(String produto) {
        Map<String, IParQtFaturacao> r = new HashMap<>();
        for(int i=0; i<Const.N_MESES; i++) {
            Map<String, IInfoMensalCliente> m2 = this.clientes.get(i);
            for(String cliente : m2.keySet()) {
                IParQtFaturacao p = r.get(cliente);
                IParQtFaturacao p2 = m2.get(cliente).getQtFaturacao(produto);
                if(p2 != null) {
                    if(p == null) r.put(cliente, p2);
                    else p.update(p2.getQuantidade(), p2.getFaturacao());
                }
            }
        }
        return r;
    }

    /**
     * Método que, para cada cliente, determina os produtos comprados por este.
     *
     * @return Map em que a cada código de cliente se associa um Set com os códigos dos produtos que este comprou.
     */
    public Map<String, Set<String>> produtosDiferentes() {
        Map<String, Set<String>> r = new HashMap<>();
        for(int i=0; i<Const.N_MESES; i++) {
            Map<String, IInfoMensalCliente> m2 = this.clientes.get(i);
            for(String cliente : m2.keySet()) {
                Set<String> set = r.get(cliente);
                Set<String> s2 = m2.get(cliente).getCodProdutos();
                if(set == null) {
                    set = new HashSet<>();
                    set.addAll(s2);
                    r.put(cliente, set);
                }
                else set.addAll(s2);
            }
        }
        return r;
    }

    /**
     * Método que determina os produtos mais vendidos.
     *
     * @return Map em que a cada código de cliente se associa um par com a quantidade comprada e um
     * Set com os códigos dos clientes que o compraram.
     */
    public Map<String, IParQtClientes> produtosMaisVendidos() {
        Map<String, IParQtClientes> r = new HashMap<>();
        for (int i=0; i<Const.N_MESES; i++) {
            Map<String, IInfoMensalCliente> m2 = this.clientes.get(i);
            for(String cliente : m2.keySet()) {
                IInfoMensalCliente info = m2.get(cliente);
                Map<String, Integer> m3 = info.mapProdQt();
                for(String produto : m3.keySet()) {
                    IParQtClientes p = r.get(produto);
                    if(p == null) r.put(produto, new ParQtClientes(m3.get(produto), cliente));
                    else {
                        p.updateQuantidade(m3.get(produto));
                        p.add(cliente);
                    }
                }
            }
        }
        return r;
    }

    /**
     * Implementação do método hashCode.
     *
     * @return Código hash.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.clientes});
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
        Filial filial = (Filial) obj;
        return this.clientes.equals(filial.getClientes());
    }

    /**
     * Implementação do método clone.
     *
     * @return Cópia do objeto sobre o qual o método é chamado.
     */
    @Override
    public Filial clone() {
        return new Filial(this);
    }
}

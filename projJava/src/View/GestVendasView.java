package View;

import Model.*;
import Utils.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;

/**
 * Classe responsável responsável por mostrar ao utilizador os elementos visuais,
 * nomeadamente as páginas, tabelas e menus.
 *
 * @author Ricardo Gomes.
 * @author Rui Fernandes.
 */
public class GestVendasView implements IGestVendasView {
    /**
     * Menu atual.
     */
    private Menu menu;

    /**
     * Método que define o menu.
     *
     * @param s Opções.
     **/
    public void setMenu(String[] s) {
        this.menu = new Menu(s);
    }

    /**
     * Método que apresenta o menu.
     */
    public void showMenu() {
        this.menu.showMenu();
    }

    /**
     * Método responsável por limpar o ecrã.
     */
    public void clear() {
        final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";
        System.out.println(ANSI_CLS + ANSI_HOME);
    }

    /**
     * Método que imprime uma mensagem no ecrã.
     *
     * @param message Mensagem a imprimir.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Método que apresenta ao utilizador dados referentes ao último ficheiro de vendas.
     *
     * @param vendasFilename         Nome do ficheiro de vendas.
     * @param regErrados             Número de registos errados.
     * @param produtos               Número total de produtos.
     * @param produtosComprados      Número de produtos comprados.
     * @param produtosNaoComprados   Número de produtos não comprados.
     * @param clientes               Número total de clientes.
     * @param clientesCompradores    Número de clientes compradores.
     * @param clientesNaoCompradores Número de clientes não compradores.
     * @param vendasZero             Número de vendas cuja faturação é 0.0.
     * @param faturacaoTotal         Faturação total da cadeia de distribuição.
     */
    public void estatisticas1(String vendasFilename, int regErrados, int produtos, int produtosComprados,
                              int produtosNaoComprados, int clientes, int clientesCompradores,
                              int clientesNaoCompradores, int vendasZero, double faturacaoTotal) {
        System.out.println(("Ficheiro lido: " + vendasFilename));
        System.out.println("Registos de venda errados: " + regErrados + "\n");
        System.out.println("Numero total de produtos: " + produtos);
        System.out.println("Numero de produtos diferemtes comprados: " + produtosComprados);
        System.out.println("Numero de produtos nao comprados: " + produtosNaoComprados + "\n");
        System.out.println("Numero total de clientes: " + clientes);
        System.out.println("Numero de clientes compradores: " + clientesCompradores);
        System.out.println("Numero de clientes nao compradores: " + clientesNaoCompradores + "\n");
        System.out.println("Numero de vendas de valor total 0.0: " + vendasZero);
        System.out.println("Faturacao total: " + faturacaoTotal);
    }

    /**
     * Método responsável por imprimir estatísticas mensais.
     *
     * @param mes       Mẽs
     * @param vendasMes Número de vendas nesse mês.
     * @param faturacao Faturação por filial.
     * @param clientes  Clientes distintos que efetuaram compras, por filial.
     */
    public void estatisticas2(int mes, int vendasMes, Map<Integer, Double> faturacao, Map<Integer, Integer> clientes) {
        System.out.println();
        System.out.println("MES " + mes + ":");
        System.out.println("Numero de vendas: " + vendasMes);
        for (int i = 0; i < Const.N_FILIAIS; i++) {
            System.out.println("FILIAL " + (i + 1) + ":");
            if(faturacao.get(i) == null) System.out.println("Faturacao: 0");
            else System.out.println("Faturacao: " + faturacao.get(i));
            if(clientes.get(i) == null) System.out.println("Numero de clientes distintos que fizeram compras: 0");
            else System.out.println("Numero de clientes distintos que fizeram compras: " + clientes.get(i));
            System.out.println();
        }
    }

    /**
     * Método responsável por imprimri os resultados relattivos à query 1.
     *
     * @param r Resultados a imprimir.
     */
    public void query1(List<String> r) {
        clear();
        System.out.println(r.size() + " produtos nao foram comprados");
        INavegador navegador = new Navegador(r);
        try {
            navegador.apresentacao();
        } catch (Exception e) {
        }
    }

    /**
     * Método responsável por imprimir os resultados relativos à query 2.
     *
     * @param r Resultados a imprimir.
     */
    public void query2(Map<Integer, IParVendasNClientes> r) {
        System.out.println();
        System.out.println("Numero global de vendas: " + r.get(0).getVendas());
        System.out.println("Numero de clientes distintos que fizeram essas compras: " + r.get(0).getVendas() + "\n");
        for (int i = 0; i < Const.N_FILIAIS; i++) {
            System.out.println("FILIAL " + (i + 1));
            System.out.println("Numero global de vendas: " + r.get(i + 1).getVendas());
            System.out.println("Numero de clientes distintos que fizeram essas compras: "
                    + r.get(i + 1).getnClientes() + "\n");
        }
    }

    /**
     * Método responsável por imprimir os resultados relativos à query 3.
     *
     * @param r Resultados a imprimir.
     */
    public void query3(Map<Integer, ITernoComprasProdutosFaturacao> r) {
        for (int i = 0; i < Const.N_MESES; i++) {
            System.out.println();
            System.out.println("MES " + (i + 1));
            ITernoComprasProdutosFaturacao terno = r.get(i + 1);
            System.out.println("Numero total de compras: " + terno.getnCompras());
            System.out.println("Numero total de produtos comprados:" + terno.getProdutos());
            System.out.println("Total gasto: " + terno.getFaturacao());
        }
    }

    /**
     * Método responsável por imprimir os resultados relativos à query 4.
     * .
     * @param r Resultados a imprimir.
     */
    public void query4(Map<Integer, ITernoVendasClientesFaturacao> r) {
        for (int i = 0; i < Const.N_MESES; i++) {
            System.out.println();
            System.out.println("MES " + (i + 1));
            ITernoVendasClientesFaturacao terno = r.get(i + 1);
            System.out.println("Numero de vendas: " + terno.getnVendas());
            System.out.println("Produto comprado por " + terno.getnClientes() + " clientes diferentes");
            System.out.println("Total faturado: " + terno.getFaturacao());
        }
    }

    /**
     * Método responsável por imprimir os resultados relativos à query 5.
     *
     * @param r Resultados a imprimir.
     */
    public void query5(List<IParProdutoQuantidade> r) {
        List<String> list = new ArrayList<>();
        for (IParProdutoQuantidade par : r)
            list.add("Produto: " + par.getProduto() + "\tQuantidade: " + par.getQuantidade());
        INavegador navegador = new Navegador(list);
        try {
            navegador.apresentacao();
        } catch (Exception e) {}
    }

    /**
     * Método responsável por imprimir os resultados relativos à query 6.
     *
     * @param r Resultados a imprimir.
     */
    public void query6(List<ITernoProdutoQuantidadeLista> r) {
        for (ITernoProdutoQuantidadeLista terno : r) {
            System.out.println("PRODUTO: " + terno.getProduto());
            System.out.println(" Unidades vendidas: " + terno.getQuantidade());
            System.out.println(" Comprado por " + terno.getClientes().size() + " clientes distintos");
            System.out.println();
        }
    }

    /**
     * Método responsável por imprimir os resultados relativos à query 7.
     *
     * @param r Resultados a imprimir.
     */
    public void query7(List<List<String>> r) {
        int i = 1;
        for (List<String> aux : r) {
            System.out.println("FILIAL " + i);
            for(String s : aux)
                System.out.print(s + " ");
            i++;
            System.out.println("\n");
        }
        System.out.println();
    }

    /**
     * Método responsável por imprimir os resultados relativos à query 8.
     *
     * @param r Resultados a imprimir.
     */
    public void query8(List<IParClienteProdutos> r) {
        for (IParClienteProdutos par : r)
            System.out.println("Cliente: " + par.getCliente() + " Produtos distintos:: " + par.getProdutos().size());
    }

    /**
     * Método responsável por imprimir os resultados relativos à query 9.
     *
     * @param r Resultados a imprimir.
     */
    public void query9(List<ITernoClienteQtFaturacao> r) {
        for (ITernoClienteQtFaturacao terno : r)
            System.out.println("CLIENTE: " + terno.getCliente() + " Valor gasto: " + terno.getFaturacao());
    }


    /**
     * Método responsável por imprimir os resultados relativos à query 10.
     *
     * @param r Resultados a imprimir.
     */
    public void query10(Map<String, Double> r) {
        List<String> list = new ArrayList<>();
        r.forEach((k, v) -> list.add("Produto: " + k + "\tFaturacao: " + v));
        INavegador navegador = new Navegador(list);
        try {
            navegador.apresentacao();
        } catch (Exception e) {
        }
    }
}

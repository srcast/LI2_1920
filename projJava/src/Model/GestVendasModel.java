package Model;

import Exceptions.ClienteInvalidoException;
import Exceptions.MesInvalidoException;
import Exceptions.ProdutoInvalidoException;
import Utils.Input;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe que implementa a estrutura da cadeia de distribuição, relacionando,
 * de forma eficiente, as classes anteriores.
 *
 * @author Ricardo Gomes.
 * @author Rui Fernandes.
 */
public class GestVendasModel implements IGestVendasModel, Serializable {
    /**
     * Catálogo de clientes.
     */
    private ICatalogo clientes;

    /**
     * Catálogo de produtos.
     */
    private ICatalogo produtos;

    /**
     * Faturação Global.
     */
    private IFaturacao faturacao;

    /**
     * Filiais.
     */
    private List<IFilial> filiais;

    /**
     * Lista com as vendas válidas.
     */
    private List<IVenda> vendas;

    /**
     * Nome do ficheiro de vendas.
     */
    private String vendasFilename;

    /**
     * Número de vendas inválidas.
     */
    private int vendasInvalidas;

    /**
     * Número de vendas cujo total faturado é 0.0.
     */
    private int vendasZero;

    /**
     * Construtor por omissão para objetos da classe GestVendasModel.
     */
    public GestVendasModel() {
        this.clientes = new Catalogo();
        this.produtos = new Catalogo();
        this.faturacao = new Faturacao();
        this.filiais = new ArrayList<>(Const.N_FILIAIS);
        for (int i = 0; i < Const.N_FILIAIS; i++)
            this.filiais.add(new Filial());
        this.vendas = new ArrayList<>();
        this.vendasFilename = new String();
        this.vendasInvalidas = 0;
        this.vendasZero = 0;
    }

    /* ESTATISTICAS 1 */

    /**
     * Método que devolve o nome do ficheiro de vendas lido.
     *
     * @return Nome do ficheiro de vendas.
     */
    public String getVendasFilename() {
        return this.vendasFilename;
    }

    /**
     * Método que devolve o número de vendas inválidas.
     *
     * @return Número de vendas inválidas.
     */
    public int getVendasInvalidas() {
        return this.vendasInvalidas;
    }

    /**
     * Método que devolve o número de vendas cuja faturação é 0.0.
     *
     * @return Número de vendas cuja faturação é 0.0.
     */
    public int getVendasZero() {
        return this.vendasZero;
    }

    /**
     * Método que determina o número total de produtos.
     *
     * @return Número total de produtos.
     */
    public int nProdutos() {
        return this.clientes.size();
    }

    /**
     * Método que determina o número de produtos que foram comprados.
     *
     * @return Número de produtos que foram comprados.
     */
    public int produtosComprados() {
        return this.faturacao.produtosCompradosCount();
    }

    /**
     * Método que determina o número de produtos que não foram comprados.
     *
     * @return Número de produtos que não foram comprados.
     */
    public int produtosNaoComprados() {
        return this.produtos.size() - this.faturacao.produtosCompradosCount();
    }

    /**
     * Método que determina o número total de clientes.
     *
     * @return Número total de clientes.
     */
    public int nClientes() {
        return this.clientes.size();
    }

    /**
     * Método que determina o número de clientes que efetuaram compras.
     *
     * @return Número de clientes que efetuaram compras.
     */
    public int clientesCompradores() {
        Set<String> clientesCompraram = new HashSet<>();
        for (int i = 0; i < Const.N_FILIAIS; i++)
            clientesCompraram.addAll(this.filiais.get(i).clientesCompradores());
        return clientesCompraram.size();
    }

    /**
     * Método que determina o número de clientes que não efetuaram compras.
     *
     * @return Número de clientes que não efetuaram compras.
     */
    public int clientesNaoCompradores() {
        return this.clientes.size() - clientesCompradores();
    }

    /**
     * Método que determina o total faturado com todas as vendas da cadeia de distribuição.
     *
     * @return Total faturado.
     */
    public double totalFaturado() {
        return this.faturacao.faturacaoTotal();
    }

    /* ESTATISTICAS 2 */

    /**
     * Método que determina o número de vendas num dado mês.
     *
     * @param mes Mês.
     * @return Número de vendas.
     */
    public int vendasMes(int mes) {
        return (int) this.vendas.stream().filter(venda -> venda.getMes() == mes).count();
    }

    /**
     * Método que determina a faturação num dado mês e numa dada filial.
     *
     * @param mes    Mês.
     * @param filial Filial.
     * @return Faturação.
     */
    private double faturacaoMesaFilial(int mes, int filial) {
        return this.vendas.stream().filter(venda -> venda.getMes() == mes && venda.getFilial() == filial)
                .mapToDouble(IVenda::getFaturacao).sum();
    }

    /**
     * Método que, dado um mês, determiina a faturação para cada filial.
     *
     * @param mes Mês.
     * @return Map em que a cada filial se associa a faturação nessa filial.
     */
    public Map<Integer, Double> faturacaoFilial(int mes) {
        Map<Integer, Double> r = new HashMap<>();
        for (int i = 0; i < Const.N_FILIAIS; i++)
            r.put(i + 1, faturacaoMesaFilial(mes, i + 1));
        return r;
    }

    /**
     * Método que determina o número de clientes distintos que fizeram compras num dado mês e numa dada filial.
     *
     * @param mes    Mês.
     * @param filial Filial.
     * @return Número de clientes distintos que fizeram compras.
     */
    private int clientesDistintosMesFilial(int mes, int filial) {
        return (int) this.vendas.stream().filter(venda -> venda.getMes() == mes && venda.getFilial() == filial)
                .map(IVenda::getCliente).distinct().count();
    }

    /**
     * Método que, dado um mês, determina, para cada uma das filiais, número de clientes distintos que fizeram
     * compras nessa filial.
     *
     * @param mes Mẽs.
     * @return Map em que a cada filial se associa o número de clientes distintos que fizeram compras.
     */
    public Map<Integer, Integer> clientesDistintosFilial(int mes) {
        Map<Integer, Integer> r = new HashMap<>();
        for (int i = 0; i < Const.N_FILIAIS; i++)
            r.put(i + 1, clientesDistintosMesFilial(mes, i + 1));
        return r;
    }

    /**
     * Método que valida o código de um cliente.
     *
     * @param cliente Código a validar.
     * @return true caso o código seja válido, false caso contrário.
     */
    private boolean validaCliente(String cliente) {
        return cliente.matches("[A-Z]([0-4]\\d{3}|50{3})");
    }

    /**
     * Método que valida o código de um produto.
     *
     * @param produto Código a validar.
     * @return true caso o código seja válido, false caso contrário.
     */
    private boolean validaProduto(String produto) {
        return produto.matches("[A-Z]{2}\\d{4}");
    }

    /**
     * Método que valida uma venda.
     *
     * @param venda Venda a validar.
     * @return true caso a venda seja válida, false caso contrário.
     */
    private boolean validaVenda(IVenda venda) {
        return this.produtos.contains(venda.getProduto()) &&
                this.clientes.contains(venda.getCliente()) &&
                venda.getPrecoUnit() >= Const.PRECO_MIN && venda.getPrecoUnit() <= Const.PRECO_MAX &&
                venda.getQuantidade() >= Const.QUANT_MIN && venda.getQuantidade() <= Const.QUANT_MAX &&
                venda.getMes() > 0 && venda.getMes() <= Const.N_MESES &&
                venda.getFilial() > 0 && venda.getFilial() <= Const.N_FILIAIS &&
                (venda.getTipo() == 'N' || venda.getTipo() == 'P');
    }

    /**
     * Método que atualiza as estruturas de dados com as informações de uma venda.
     *
     * @param venda Venda a inserir.
     */
    private void insereVenda(IVenda venda) {
        if (validaVenda(venda)) {
            this.vendas.add(venda);
            this.faturacao.update(venda);
            this.filiais.get(venda.getFilial() - 1).insereVenda(venda);
            if (venda.getFaturacao() == 0.0) this.vendasZero++;
        } else this.vendasInvalidas++;
    }

    /**
     * Método que lê os ficheiros de texto e popula as estruturas de dados.
     *
     * @param clientesFilename Ficheiro de clientes.
     * @param produtosFilename Ficheiro de produtos.
     * @param vendasFilename   Ficheiro de vendas.
     */
    public void readFiles(String clientesFilename, String produtosFilename, String vendasFilename) {
        this.vendasFilename = vendasFilename;
        List<String> clientes = Input.readLinesWithBuff(clientesFilename);
        clientes.stream().filter(cliente -> validaCliente(cliente)).forEach(s -> this.clientes.add(s));
        List<String> produtos = Input.readLinesWithBuff(produtosFilename);
        produtos.stream().filter(produto -> validaProduto(produto)).forEach(s -> this.produtos.add(s));
        this.faturacao = new Faturacao(this.produtos);
        List<IVenda> vendas = Input.readLinesWithBuff(vendasFilename).stream().map(Venda::new)
                .filter(venda -> validaVenda(venda)).collect(Collectors.toList());
        vendas.forEach(venda -> insereVenda(venda));
    }

    /* QUERY 1 */

    /**
     * Método que devolve a lista ordenada alfabeticamente com os códigos dos produtos que nunca
     * foram comprados.
     *
     * @return Lista ordenada com os códigos dos produtos nunca comprados.
     */
    public List<String> produtosNuncaComprados() {
        return this.faturacao.produtosNaoComprados();
    }

    /* QUERY 2 */

    /**
     * Método que, dado um mês, determina o número número de vendas realizadas e o número total de
     * clientes distintos que as fizeram, tanto globalmente como filial a filial.
     *
     * @param mes Mês.
     * @return Map em que a cada filial se associa um par com o número de vendas e número de clientes
     * distintos.
     * @throws MesInvalidoException Exceção caso o valor recebido como argumento seja inválido.
     */
    public Map<Integer, IParVendasNClientes> vendasClientesMes(int mes) throws MesInvalidoException {
        if (mes <= 0 || mes > Const.N_FILIAIS) throw new MesInvalidoException("Mes invalido");
        Map<Integer, IParVendasNClientes> r = new HashMap<>();
        List<IVenda> vendaList = this.vendas.stream().filter(venda -> venda.getMes() == mes)
                .collect(Collectors.toList());
        r.put(0, new ParVendasNClientes(vendaList.size(),
                (int) vendaList.stream().map(IVenda::getCliente).distinct().count()));
        for (int i = 0; i < Const.N_MESES; i++) {
            final int filial = i + 1;
            r.put(filial, new ParVendasNClientes((int) vendaList.stream()
                    .filter(venda -> venda.getFilial() == filial).count(),
                    (int) vendaList.stream().filter(venda -> venda.getFilial() == filial)
                            .map(IVenda::getCliente).distinct().count()));
        }
        return r;
    }

    /* QUERY 3 */

    /**
     * Método que, dado um código de cliente, determina, para cada mês, quantas compras fez, quantos
     * rodutos distintos comprou e quanto gastou no total.
     *
     * @param cliente Código do cliente.
     * @return Map em que a cada código de cliente se associa um terno cujas componentes são o número de compras,
     * os número de produtos distintos comprados e o total faturado.
     * @throws ClienteInvalidoException Exceção caso o código de cliente recebido como argumento não seja
     *                                  válido.
     */
    public Map<Integer, ITernoComprasProdutosFaturacao> estatisticasCliente(String cliente) throws ClienteInvalidoException {
        if (!this.clientes.contains(cliente)) throw new ClienteInvalidoException("Cliente invalido");
        Map<Integer, ITernoComprasProdutosFaturacao> r = new HashMap<>();
        for (int i = 0; i < Const.N_MESES; i++) {
            final int mes = i + 1;
            int nCompras = (int) this.vendas.stream().filter(venda -> venda.getCliente().equals(cliente) && venda.getMes() == mes)
                    .count();
            int produtosDistintos = (int) this.vendas.stream().filter(venda -> venda.getCliente().equals(cliente) && venda.getMes() == mes)
                    .map(IVenda::getProduto).distinct().count();
            double faturacao = this.vendas.stream().filter(venda -> venda.getCliente().equals(cliente) && venda.getMes() == mes)
                    .mapToDouble(IVenda::getFaturacao).sum();
            r.put(mes, new TernoComprasProdutosFaturacao(nCompras, produtosDistintos, faturacao));
        }
        return r;
    }

    /* QUERY 4 */

    /**
     * Método que detemrina o número de clientes diferentes que compraram um dado produto num dado mes
     *
     * @param mes     Mês.
     * @param produto Código do produto.
     * @return O numero de clientes diferentes que compraram o produto nesse mês.
     */
    private int clientesDiferentes(String produto, int mes) {
        Set<String> r = new HashSet<>();
        for (int i = 0; i < Const.N_FILIAIS; i++) {
            Set<String> aux = this.filiais.get(i).clientesDifProd(produto, mes);
            r.addAll(aux);
        }
        return r.size();
    }

    /**
     * Método que, dado o código de um produto, determinar, para cada mês, quantas vezes foi comprado, por
     * quantos clientes distintos e o total faturado.
     *
     * @param produto Código do produto.
     * @return Map em que a cada mês se associa um terno cujas componentes são o número de vendas, o número
     * de clientes que o compraram e o total faturado.
     * @throws ProdutoInvalidoException Exceção caso o código de produto recebido como argumento seja
     *                                  inválido.
     */
    public Map<Integer, ITernoVendasClientesFaturacao> estatisticasProduto(String produto) throws ProdutoInvalidoException {
        if (!this.produtos.contains(produto)) throw new ProdutoInvalidoException("Produto invalido");
        Map<Integer, ITernoVendasClientesFaturacao> r = new HashMap<>();
        for (int i = 0; i < Const.N_MESES; i++) {
            final int mes = i + 1;
            int vezesComprado = this.faturacao.getInfoProduto(produto).nVendasMensal(mes);
            int clientesDistintos = clientesDiferentes(produto, mes);
            double faturacao = this.faturacao.getInfoProduto(produto).faturacaoMensal().get(mes);
            r.put(mes, new TernoVendasClientesFaturacao(vezesComprado, clientesDistintos, faturacao));
        }
        return r;
    }

    /* QUERY 5 */

    /**
     * Método que determina a lista de códigos de produtos que um cliente mais comprou, e quantos, ordenadas
     * por ordem decrescente de quantidae.
     *
     * @param cliente Código de cliente.
     * @return Lista de códigos de produtos que um cliente mais comprou. Cada elemento da lista é um par
     * cujas componentes são o código do produto e a quantidade comprada.
     * @throws ClienteInvalidoException Exceção caso o código recebido como argumento seja inválido.
     */
    public List<IParProdutoQuantidade> produtosMaisComprados(String cliente) throws ClienteInvalidoException {
        if (!this.clientes.contains(cliente)) throw new ClienteInvalidoException("Codigo invalido");
        Map<String, Integer> m = new HashMap<>();
        for (int i = 0; i < Const.N_FILIAIS; i++) {
            Map<String, Integer> m2 = this.filiais.get(i).produtosMaisComprados(cliente);
            for (String produto : m2.keySet()) {
                Integer quantidade = m.get(produto);
                if (quantidade == null) m.put(produto, m2.get(produto));
                else m.put(produto, quantidade + m2.get(produto));
            }
        }
        Set<IParProdutoQuantidade> s = new TreeSet<>();
        for (String produto : m.keySet())
            s.add(new ParProdutoQuantidade(produto, m.get(produto)));
        List<IParProdutoQuantidade> l = new ArrayList<>();
        for (IParProdutoQuantidade p : s)
            l.add(p);
        return l;
    }

    /* QUERY 6 */

    /**
     * Método que determina a lista dos N produtos mais vendidos.
     *
     * @param n Valor de N.
     * @return Lista cujos elementos são um terno que contém o código do produto, a quantidade vendida e um Set com os
     * clientes distintos que o compraram.
     */
    public List<ITernoProdutoQuantidadeLista> produtosMaisVendidos(int n) {
        Map<String, IParQtClientes> m = new HashMap<>();
        for (int i = 0; i < Const.N_FILIAIS; i++) {
            Map<String, IParQtClientes> aux = this.filiais.get(i).produtosMaisVendidos();
            for (String produto : aux.keySet()) {
                IParQtClientes p = m.get(produto);
                IParQtClientes p2 = aux.get(produto);
                if (p == null) m.put(produto, p2);
                else {
                    p.updateQuantidade(p2.getQuantidade());
                    p.addSet(p2.getClientes());
                }
            }
        }
        Set<ITernoProdutoQuantidadeLista> s = new TreeSet<>();
        for (String produto : m.keySet()) {
            IParQtClientes p = m.get(produto);
            s.add(new TernoProdutoQuantidadeLista(produto, p.getQuantidade(), p.getClientes()));
        }
        return s.stream().sorted().limit(n).collect(Collectors.toList());
    }

    /* QUERY 7 */

    /**
     * Método que, para uma determinada filial, determina uma lista com o código dos 3 melhores compradores.
     *
     * @param filial Filial.
     * @return Lista com o código dos 3 melhores compradores.
     */
    public List<String> melhoresClientesPorFilial(int filial) {
        return this.filiais.get(filial - 1).maioresCompradoresFilial().stream()
                .sorted().limit(3).map(IParClienteFaturacao::getCliente)
                .collect(Collectors.toList());
    }

    /* QUERY 8 */

    /**
     * Método que determina a lista dos N clientes que compraram mais produtos diferentes,
     *
     * @param n Valor de N.
     * @return Lista cujos elementos são um par que contém o código de cada cliente e um Set com os códigos dos
     * produtos que este comprou.
     */
    public List<IParClienteProdutos> maisProdutosDiferentes(int n) {
        Map<String, Set<String>> m = new HashMap<>();
        for (int i = 0; i < Const.N_FILIAIS; i++) {
            Map<String, Set<String>> m2 = this.filiais.get(i).produtosDiferentes();
            for (String cliente : m2.keySet()) {
                Set<String> s = m.get(cliente);
                if (s == null) m.put(cliente, m2.get(cliente));
                else s.addAll(m2.get(cliente));
            }
        }
        Set<IParClienteProdutos> s = new TreeSet<>();
        ;
        for (String cliente : m.keySet())
            s.add(new ParClienteProdutos(cliente, m.get(cliente)));
        return s.stream().sorted().limit(n).collect(Collectors.toList());
    }

    /* QUERY 9 */

    /**
     * Método que determina uma lista com informação sobre os N cliuentes que mais compraram um produto.
     *
     * @param produto Código do produto.
     * @param n       Valor de N.
     * @return Lista com informação sobre os N cliuentes que mais compraram um produto.
     * @throws ProdutoInvalidoException Exceção caso o código do produto recebido como argumento seja inválido.
     */
    public List<ITernoClienteQtFaturacao> maioresCompradoresProduto(String produto, int n) throws ProdutoInvalidoException {
        if (!this.produtos.contains(produto)) throw new ProdutoInvalidoException("Produto invalido");
        Map<String, IParQtFaturacao> m = new HashMap<>();
        for (int i = 0; i < Const.N_FILIAIS; i++) {
            Map<String, IParQtFaturacao> m2 = this.filiais.get(i).maioresCompradoresProduto(produto);
            for (String cliente : m2.keySet()) {
                IParQtFaturacao p = m.get(cliente);
                IParQtFaturacao p2 = m2.get(cliente);
                if (p == null) m.put(cliente, p2);
                else p.update(p2.getQuantidade(), p2.getFaturacao());
            }
        }
        Set<ITernoClienteQtFaturacao> s = new TreeSet<>();
        for (String cliente : m.keySet()) {
            IParQtFaturacao p = m.get(cliente);
            s.add(new TernoClienteQtFaturacao(cliente, p.getQuantidade(), p.getFaturacao()));
        }
        return s.stream().sorted().limit(n).collect(Collectors.toList());
    }

    /* QUERY 10 */

    /**
     * Método que determina a faturação total de um produto num dado mês e numa dada filial.
     *
     * @param mes    Mês.
     * @param filial Filial.
     * @return Map em que a cada código de produto se associa o total faturado.
     */
    public Map<String, Double> faturacaoProd(int mes, int filial) {
        return this.vendas.stream().filter(venda -> venda.getMes() == mes && venda.getFilial() == filial)
                .collect(Collectors.toMap(IVenda::getProduto, IVenda::getFaturacao, Double::sum));
    }

    /**
     * Método que recupera o estado gravado anteriormente.
     *
     * @param filename Nome do ficheiro a partir do qual será recuperado o estado do programa.
     * @return Estado do programa recuperado.
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     */
    public GestVendasModel loadState(String filename) throws IOException, FileNotFoundException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        GestVendasModel model = (GestVendasModel) ois.readObject();
        ois.close();
        return model;
    }

    /**
     * Método que guarda o estado do programa.
     *
     * @param filename Nome do ficheiro para o qual será guardado o estado do programa.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void saveState(String filename) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }
}

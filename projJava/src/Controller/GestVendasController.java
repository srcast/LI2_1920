package Controller;

import Exceptions.ClienteInvalidoException;
import Exceptions.MesInvalidoException;
import Exceptions.ProdutoInvalidoException;
import Model.*;
import Utils.Crono;
import Utils.Input;
import View.IGestVendasView;

import java.io.IOException;
import java.util.*;

/**
 * Classe que trata e valida o input do utilizador, efeuta a comunicação necessária com
 * o sistema e passa à View a informação que deve ser mostrada ao utilizador.
 *
 * @author Ricardo Gomes.
 * @author Rui Fernandes.
 */
public class GestVendasController implements IGestVendasController {
    /**
     * Model.
     */
    private IGestVendasModel model;

    /**
     * View.
     */
    private IGestVendasView view;


    /**
     * Método que define o Model do programa.
     *
     * @param model Model.
     */
    public void setModel(IGestVendasModel model) {
        this.model = model;
    }

    /**
     * Método que define a view do programa.
     *
     * @param view View.
     */
    public void setView(IGestVendasView view) {
        this.view = view;
    }

    /**
     * Método que lê uma opção.
     *
     * @param opcoes Número de opções possíveis.
     */
    private int lerOpcao(int opcoes) {
        int op;
        System.out.print("Opção: ");
        try {
            op = Input.lerInt();
        } catch (InputMismatchException e) { // Não foi inscrito um int
            op = -1;
        }
        if (op < 0 || op > opcoes) {
            this.view.showMessage("Opção Inválida!");
            op = -1;
        }
        return op;
    }

    /**
     * Método que inicializa o fluxo do programa.
     */
    public void start() {
        this.view.clear();
        String[] opcoes = {"GestVendas", "Leitura/Gravação de dados",
                "Estatísticas", "Queries"};
        this.view.setMenu(opcoes);
        this.view.showMenu();
        while (true) {
            int opt = lerOpcao(opcoes.length);
            switch (opt) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    menuLeituraGravacao();
                    break;
                case 2:
                    menuEstatisticas();
                    break;
                case 3:
                    menuQueries();
                    break;
            }
        }
    }

    /**
     * Método que ap+resenta o menu de leitura e escrita de ficheiros.
     */
    private void menuLeituraGravacao() {
        this.view.clear();
        String opcoes[] = {"LEITURA E GRAVACAO", "Ler dados a partir de ficheiros de texto",
                "Gravar estado em ficheiro objeto", "Recuperar estado a partir de ficheiro objeto", "Voltar"};
        this.view.setMenu(opcoes);
        this.view.showMenu();
        while (true) {
            int opt = lerOpcao(opcoes.length);
            switch (opt) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    menuFichTexto();
                    break;
                case 2:
                    this.view.showMessage("Nome do ficheiro objeto a gravar: ");
                    String fich = Input.lerString();
                    try {
                        Crono.start();
                        this.model.saveState(fich);
                        double time = Crono.stop();
                        this.view.showMessage("Gravação concluídae em " + time + " s");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    this.view.showMessage("\"ENTER\" para contiunar...");
                    Scanner scanner = new Scanner(System.in);
                    scanner.nextLine();
                    break;
                case 3:
                    this.view.showMessage("Nome do ficheiro objeto a ler: ");
                    String file = Input.lerString();
                    try {
                        Crono.start();
                        this.model = this.model.loadState(file);
                        double tempo = Crono.stop();
                        this.view.showMessage("Leitura concluídae em " + tempo + " s");

                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    this.view.showMessage("\"ENTER\" para contiunar...");
                    Scanner scannerr = new Scanner(System.in);
                    scannerr.nextLine();
                    break;
                case 4:
                    start();
                    break;
            }
        }
    }

    /**
     * Método que apresenta o menu de leitura de ficheiros de texto.
     */
    private void menuFichTexto() {
        this.view.clear();
        String[] opcoes = {"LEITURA A PARTIR DE FICHEIROS DE TEXTO",
                "Ficheiros pre definidos",
                "Outros ficheiros"};
        this.view.setMenu(opcoes);
        this.view.showMenu();
        while (true) {
            int opcao = lerOpcao(opcoes.length);
            switch (opcao) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    Crono.start();
                    this.model.readFiles(Const.CLIENTES_FILENAME, Const.PRODUTOS_FILENAME, Const.VENDAS_FILENAME);
                    double t = Crono.stop();
                    this.view.showMessage("Elapsed time: " + t + " s");
                    this.view.showMessage("\"ENTER\" para contiunar...");
                    Scanner scanner = new Scanner(System.in);
                    scanner.nextLine();
                    start();
                    break;
                case 2:
                    this.view.showMessage("Nome do ficheiro de clientes: ");
                    String clientes = Input.lerString();
                    this.view.showMessage("Nome do ficheiro de produtos: ");
                    String produtos = Input.lerString();
                    this.view.showMessage("Nome do ficheiro de vendas: ");
                    String vendas = Input.lerString();
                    try {
                        Crono.start();
                        this.model.readFiles(clientes, produtos, vendas);
                        double time = Crono.stop();
                        this.view.showMessage("Elapsed time: " + time + " s");
                    } catch (Exception e) {
                        this.view.showMessage("Ficheiros invalidos");
                    }
                    this.view.showMessage("\"ENTER\" para contiunar...");
                    Scanner scannerr = new Scanner(System.in);
                    scannerr.nextLine();
                    start();
                    break;
            }
        }
    }

    /**
     * Método responsável pela apresentação do menu de estatísticas.
     */
    private void menuEstatisticas() {
        this.view.clear();
        String[] opcoes = {"ESTATÍSTICAS", "Dados referentes ao ultimo ficheiro de vendas lido",
                "Numeros gerais relativos aos dados ja registados", "Voltar"};
        this.view.setMenu(opcoes);
        this.view.showMenu();
        while (true) {
            int opcao = lerOpcao(opcoes.length);
            switch (opcao) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    estatisticas1();
                    menuEstatisticas();
                    break;
                case 2:
                    estatisticas2();
                    menuEstatisticas();
                    break;
                case 3:
                    start();
                    break;
            }
        }
    }

    /**
     * Método que apresenta o menu das queries interativas.
     */
    private void menuQueries() {
        this.view.clear();
        String[] opcoes = {"QUERIES INTERATIVAS",
                "Codigos de produtos nunca comprados",
                "Numero total global de vendas realizadas e numero total de clientes que as fizeram de um dado mes",
                "Dado um codigo de cliente, determinar quantas compras fez, quantos produtos distintos comprou e quanto gastou no total",
                "Dado um codigo de produto determinar quantas vezes foi comprado, por quantos clientes diferentes e o total faturado",
                "Dado o código de um cliente determinar a lista de códigos de produtos que mais comprou",
                "Determinar o conjunto dos X produtos mais vendidos em todo o ano",
                "Determinar a lista dos três maiores compradores, em termos defaturacao, para cada filial",
                "Determinar os códigos dos X clientes que compraram mais produtos diferentes",
                "Dado um codigo de produto determinar o conjunto de clientes que mais o compraram e, para cada um, o valor gasto",
                "Faturacao total com cada produto",
                "Voltar"};
        this.view.setMenu(opcoes);
        this.view.showMenu();
        while (true) {
            int opt = lerOpcao(opcoes.length);
            switch (opt) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    query1();
                    menuQueries();
                    break;
                case 2:
                    query2();
                    menuQueries();
                    break;
                case 3:
                    query3();
                    menuQueries();
                    break;
                case 4:
                    query4();
                    menuQueries();
                    break;
                case 5:
                    query5();
                    menuQueries();
                    break;
                case 6:
                    query6();
                    menuQueries();
                    break;
                case 7:
                    query7();
                    menuQueries();
                    break;
                case 8:
                    query8();
                    menuQueries();
                    break;
                case 9:
                    query9();
                    menuQueries();
                    break;
                case 10:
                    query10();
                    menuQueries();
                    break;
                case 11:
                    start();
                    break;
            }
        }
    }

    /**
     * Método responsável pela apresentação das estatísticas relativas ao último ficheiro lido.
     */
    private void estatisticas1() {
        this.view.clear();
        String vendasFilename = this.model.getVendasFilename();
        int regErrados = this.model.getVendasInvalidas();
        int produtos = this.model.nProdutos();
        int produtosComprados = this.model.produtosComprados();
        int produtosNaoComprados = this.model.produtosNaoComprados();
        int clientes = this.model.nClientes();
        int clientesCompradores = this.model.clientesCompradores();
        int clientesNaoCompradores = this.model.clientesNaoCompradores();
        int vendasZero = this.model.getVendasZero();
        double faturacaoTotal = this.model.totalFaturado();
        this.view.estatisticas1(vendasFilename, regErrados, produtos, produtosComprados, produtosNaoComprados,
                clientes, clientesCompradores,
                clientesNaoCompradores, vendasZero, faturacaoTotal);
        this.view.showMessage("\"ENTER\" para contiunar...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    /**
     * Método responsável pela apresentação de estatísticas mensais.
     */
    private void estatisticas2() {
        this.view.clear();
        for (int i = 0; i < Const.N_MESES; i++) {
            int vendasMes = this.model.vendasMes(i + 1);
            Map<Integer, Double> fatFil = this.model.faturacaoFilial(i + 1);
            Map<Integer, Integer> clientesFil = this.model.clientesDistintosFilial(i + 1);
            this.view.estatisticas2(i + 1, vendasMes, fatFil, clientesFil
            );
            this.view.showMessage("\"ENTER\" para ver as estatisticas do mes seguinte");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        }
    }

    /**
     * Método responsável pela execução da query 1.
     */
    private void query1() {
        Crono.start();
        List<String> r = this.model.produtosNuncaComprados();
        double time = Crono.stop();
        this.view.query1(r);
        this.view.showMessage("Elapsed time: " + time + " s");
        this.view.showMessage("\"ENTER\" para contiunar...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    /**
     * Método responsável pela execução da query 2.
     */
    private void query2() {
        this.view.clear();
        this.view.showMessage("Introduzir o mes pretendido: ");
        int mes = Input.lerInt();
        try {
            Crono.start();
            Map<Integer, IParVendasNClientes> r = this.model.vendasClientesMes(mes);
            double time = Crono.stop();
            this.view.query2(r);
            this.view.showMessage("Elapsed time: " + time + " s");
        } catch (MesInvalidoException e) {
            System.out.println(e.getMessage());
        }
        this.view.showMessage("\"ENTER\" para contiunar...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    /**
     * Método responsável pela execução da query 3.
     */
    private void query3() {
        this.view.clear();
        this.view.showMessage("Introduzir o codigo de cliente: ");
        String cliente = Input.lerString();
        try {
            Crono.start();
            Map<Integer, ITernoComprasProdutosFaturacao> r = this.model.estatisticasCliente(cliente);
            double time = Crono.stop();
            this.view.query3(r);
            this.view.showMessage("Elapsed time: " + time + " s");
        } catch (ClienteInvalidoException e) {
            this.view.showMessage(e.getMessage());
        }
        this.view.showMessage("\"ENTER\" para contiunar...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    /**
     * Método responsável pela execução da query 4.
     */
    private void query4() {
        this.view.clear();
        this.view.showMessage("Introduzir o codigo de produto: ");
        String produto = Input.lerString();
        try {
            Crono.start();
            Map<Integer, ITernoVendasClientesFaturacao> r = this.model.estatisticasProduto(produto);
            double time = Crono.stop();
            this.view.query4(r);
            this.view.showMessage("Elapsed time: " + time + " s");
        } catch (ProdutoInvalidoException e) {
            this.view.showMessage(e.getMessage());
        }
        this.view.showMessage("\"ENTER\" para contiunar...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    /**
     * Método responsável pela execução da query 5.
     */
    private void query5() {
        this.view.clear();
        this.view.showMessage("Introduzir o codigo do cliente: ");
        String cliente = Input.lerString();
        try {
            Crono.start();
            List<IParProdutoQuantidade> r = this.model.produtosMaisComprados(cliente);
            double time = Crono.stop();
            this.view.query5(r);
            this.view.showMessage("Elapsed time: " + time + " s");
        } catch (ClienteInvalidoException e) {
            this.view.showMessage(e.getMessage());
        }
        this.view.showMessage("\"ENTER\" para contiunar...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    /**
     * Método responsável pela execução da query 6.
     */
    private void query6() {
        this.view.clear();
        this.view.showMessage("Quantos produtos quer ver: ");
        int x = Input.lerInt();
        Crono.start();
        List<ITernoProdutoQuantidadeLista> r = this.model.produtosMaisVendidos(x);
        double time = Crono.stop();
        this.view.query6(r);
        this.view.showMessage("Elapsed time: " + time + " s");
        this.view.showMessage("\"ENTER\" para contiunar...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    /**
     * Método responsável pela execução da query 7.
     */
    private void query7() {
        this.view.clear();
        Crono.start();
        List<List<String>> r = new ArrayList<>(Const.N_FILIAIS);
        for (int i = 0; i < Const.N_FILIAIS; i++)
            r.add(this.model.melhoresClientesPorFilial(i + 1));
        double time = Crono.stop();
        this.view.query7(r);
        this.view.showMessage("Elapsed time: " + time + " s");
        this.view.showMessage("\"ENTER\" para contiunar...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    /**
     * Método responsável pela execução da query 8.
     */
    private void query8() {
        this.view.clear();
        this.view.showMessage("Quantos produtos quer ver: ");
        int x = Input.lerInt();
        Crono.start();
        List<IParClienteProdutos> r = this.model.maisProdutosDiferentes(x);
        double time = Crono.stop();
        this.view.query8(r);
        this.view.showMessage("Elapsed time: " + time + " s");
        this.view.showMessage("\"ENTER\" para contiunar...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    /**
     * Método responsável pela execução da query 9.
     */
    private void query9() {
        this.view.clear();
        this.view.showMessage("Introduzir o codigo do produto: ");
        String produto = Input.lerString();
        this.view.showMessage("Quantos clientes quer ver: ");
        int x = Input.lerInt();
        try {
            Crono.start();
            List<ITernoClienteQtFaturacao> r = this.model.maioresCompradoresProduto(produto, x);
            double time = Crono.stop();
            this.view.query9(r);
            this.view.showMessage("Elapsed time: " + time + " s");
        } catch (ProdutoInvalidoException e) {
            this.view.showMessage(e.getMessage());
        }
        this.view.showMessage("\"ENTER\" para contiunar...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public void query10() {
        this.view.clear();
        this.view.showMessage("Mês a pesquisar:");
        int mes = Input.lerInt();
        this.view.showMessage("Filial a pesquisar [1-" + Const.N_FILIAIS + "]:");
        int filial = Input.lerInt();
        Crono.start();
        Map<String, Double> r = this.model.faturacaoProd(mes, filial);
        double time = Crono.stop();
        this.view.query10(r);
        this.view.showMessage("Elapsed time: " + time + " s");
        this.view.showMessage("\"ENTER\" para contiunar...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }


}

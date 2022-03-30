package View;

import Exceptions.OpcaoInvalidaException;
import Exceptions.PaginaInexistenteException;
import Model.Const;
import Utils.Input;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe responsável pela paginação de Listas com um elevado número de elementos.
 *
 * @author Ricardo Gomes.
 * @author Rui Fernandes.
 */
public class Navegador implements INavegador {
    /**
     * Lista de páginas que guarda as Strings para navegação.
     */
    private List<List<String>> paginas;

    /**
     * Construtor parametrizado para objetos da classe Navegador.
     *
     * @param lista Lista de páginas.
     */
    public Navegador(List<String> lista) {
        int total = lista.size();
        int nPags = total / Const.ELEMS_PAGINA;
        this.paginas = new ArrayList<>(nPags);
        if (total % Const.ELEMS_PAGINA != 0) nPags++;
        Iterator it = lista.iterator();
        List<String> pag;
        while (it.hasNext()) {
            pag = new ArrayList<>(Const.ELEMS_PAGINA);
            for (int i = 0; it.hasNext() && i < Const.ELEMS_PAGINA; i++)
                pag.add((String) it.next());
            this.paginas.add(pag);
        }
    }

    /**
     * Método que permite navegar pelas diferentes páginas.
     *
     * @throws PaginaInexistenteException Exceção caso a página inserida não exista.
     * @throws OpcaoInvalidaException Exceção caso a opção inserida seja inválida.
     */
    public void apresentacao() throws PaginaInexistenteException, OpcaoInvalidaException {
        boolean r = true;
        int pagina = 1;
        int nPaginas = this.paginas.size();
        List<String> p;
        if (nPaginas == 0) {
            System.out.println("Lista vazia");
            return;
        }
        while (r) {
            System.out.println("---------------");
            System.out.println("Pagina " + pagina + "/" + nPaginas);
            System.out.println("---------------\n");
            p = this.paginas.get(pagina - 1);
            for (int index = 0; index < Const.ELEMS_PAGINA; index++) {
                String elem = p.get(index);
                if (elem != null) System.out.println(elem);
                else System.out.println("Fim");
            }
            System.out.println("Proxima pagina -> p | Pagina anterior -> a | Escolher página -> e | Sair -> s\n");
            String resposta = Input.lerString();
            System.out.println("\u001b[2J\u001b[H"); // limpa o ecra
            if (resposta.equals("p") && pagina < nPaginas) pagina++;
            else if (resposta.equals("a") && pagina > 1) pagina--;
            else if (resposta.equals("e")) {
                System.out.println("Página pretendida: ");
                int respostaPag = Input.lerInt();
                if (respostaPag < 1 || respostaPag > nPaginas)
                    throw new PaginaInexistenteException("Pagina Inexistente\n");
                while (respostaPag < 1 || respostaPag > nPaginas) {
                    System.out.println("Página pretendida: ");
                    respostaPag = Input.lerInt();
                }
            } else if (resposta.equals("s")) r = false;
            else {
                r = false;
                throw new OpcaoInvalidaException("Opcao Invalida");
            }
        }
    }
}

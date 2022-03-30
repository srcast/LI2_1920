package Exceptions;

/**
 * Classe que devolve uma mensagem caso uma página não exista.
 * Para ser lançada quando se tenta aceder a uma página não existente de uma apresentação paginada.
 *
 * @author Ricardo Gomes.
 * @author Rui Fernandes.
 */
public class PaginaInexistenteException extends Exception {
    /**
     * Construtor por omissão para objetos da classe PaginaInexistenteException.
     */
    public PaginaInexistenteException() {
        super();
    }

    /**
     * Construtor parametrizado para objetos da classe PaginaInexistenteException.
     *
     * @param message Mensagem.
     */
    public PaginaInexistenteException(String message) {
        super(message);
    }
}

package Exceptions;

/**
 * Classe que devolve uma mensagem caso um código de cliente não pertença
 * ao catálogo.
 * Poderá ser lançada durante a validação de um código de cliente.
 *
 * @author Ricardo Gomes.
 * @author Rui Fernandes.
 */
public class ClienteInvalidoException extends Exception {
    /**
     * Construtor por omissão para objetos da classe ClienteInvalidoException.
     */
    public ClienteInvalidoException() {
        super();
    }

    /**
     * Construtor parametrizado para objetos da classe ClienteInvalidoException.
     *
     * @param message Mensagem.
     */
    public ClienteInvalidoException(String message) {
        super(message);
    }
}

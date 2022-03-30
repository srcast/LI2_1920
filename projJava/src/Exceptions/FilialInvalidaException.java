package Exceptions;

/**
 * Classe que devolve uma mensagem caso uma filial não seja válida.
 * Poderá ser lançada durante a validação de uma filial.
 *
 * @author Ricardo Gomes.
 * @author Rui Fernandes.
 */
public class FilialInvalidaException extends Exception {
    /**
     * Construtor por omissão para objetos da classe FilialInvalidaException.
     */
    public FilialInvalidaException() {
        super();
    }

    /**
     * Construtor parametrizado para objetos da classe FilialInvalidaException.
     *
     * @param message Mensagem.
     */
    public FilialInvalidaException(String message) {
        super(message);
    }
}

package Exceptions;

/**
 * Classe que devolve uma mensagem caso um mês não seja válido.
 * Poderá ser lançada durante a validação de um mês.
 *
 * @author Ricardo Gomes.
 * @author Rui Fernandes.
 */
public class MesInvalidoException extends Exception {
    /**
     * Construtor por omissão para objetos da classe MesInvalidoException.
     */
    public MesInvalidoException() {
        super();
    }

    /**
     * Construtor parametrizado para objetos da classe MesInvalidoException.
     *
     * @param message Mensagem.
     */
    public MesInvalidoException(String message) {
        super(message);
    }
}

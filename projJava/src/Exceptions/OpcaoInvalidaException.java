package Exceptions;

/**
 * Classe que devolve uma mensagem caso a opção selecionada não seja válida.
 * Poderá ser lançada durante a validação de um opção introduzida pelo utilizador.
 *
 * @author Ricardo Gomes.
 * @author Rui Fernandes.
 */
public class OpcaoInvalidaException extends Exception {
    /**
     * Construtor por omissão para objetos da classe OpcaoInvalidaException.
     */
    public OpcaoInvalidaException() {
        super();
    }

    /**
     * Construtor parametrizado para objetos da classe OpcaoInvalidaException.
     *
     * @param message Mensagem.
     */
    public OpcaoInvalidaException(String message) {
        super(message);
    }
}

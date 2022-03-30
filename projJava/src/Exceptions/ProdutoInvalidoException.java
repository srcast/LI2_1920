package Exceptions;

/**
 * Classe que devolve uma mensagem caso um código de produto não pertença
 * ao catálogo.
 * Poderá ser lançada durante a validação de um código de produto.
 *
 * @author Ricardo Gomes.
 * @author Rui Fernandes.
 */
public class ProdutoInvalidoException extends Exception {
    /**
     * Construtor por omissão para objetos da classe ProdutoInvalidoException.
     */
    public ProdutoInvalidoException() {
        super();
    }

    /**
     * Construtor parametrizado para objetos da classe ProdutoInvalidoException.
     *
     * @param message Mensagem.
     */
    public ProdutoInvalidoException(String message) {
        super(message);
    }
}

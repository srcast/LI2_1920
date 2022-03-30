package View;

import Exceptions.OpcaoInvalidaException;
import Exceptions.PaginaInexistenteException;

public interface INavegador {
    /**
     * Método que permite navegar pelas diferentes páginas.
     *
     * @throws PaginaInexistenteException Exceção caso a página inserida não exista.
     * @throws OpcaoInvalidaException Exceção caso a opção inserida seja inválida.
     */
    void apresentacao() throws PaginaInexistenteException, OpcaoInvalidaException;
}
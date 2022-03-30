package Model;

import java.util.Set;

public interface IParClienteProdutos {
    /**
     * Método que devolve o código do cliente.
     *
     * @return Código do cliente.
     */
    String getCliente();

    /**
     * Método que devolve um Set com os códigos dos produtos comprados.
     *
     * @return Set com os códigos dos produtos comprados.
     */
    Set<String> getProdutos();
}

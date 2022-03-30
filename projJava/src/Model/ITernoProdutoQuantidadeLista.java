package Model;

import java.util.HashSet;
import java.util.Set;

public interface ITernoProdutoQuantidadeLista {
    /**
     * Método que devolve o código do produto.
     *
     * @return Código do produto.
     */
    String getProduto();
    /**
     * Método que devolve a quantidade.
     *
     * @return Quantidade.
     */
    int getQuantidade();

    /**
     * Método que devolve um Set com os códigos dos clientes que compraram o produto.
     *
     * @return Set com os códigos dos clientes que compraram o produto.
     */
    Set<String> getClientes();
}

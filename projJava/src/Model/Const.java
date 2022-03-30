package Model;

import java.io.Serializable;

/**
 * Classe na qual se definem algumas constantes.
 *
 * @author Ricardo Gomes.
 * @author Rui Fernandes.
 */
public class Const implements Serializable {
    /**
     * Número de campos de uma venda.
     */
    public static final int CAMPOS_VENDA = 7;

    /**
     * Comprimento do código de um produto.
     */
    public static final int PROD_LEN = 6;

    /**
     * Comprimento do código de um cliente.
     */
    public static final int CLIENTE_LEN = 5;

    /**
     * Número de filiais da cadeia de distruibuição.
     */
    public static final int N_FILIAIS = 3;

    /**
     * Número de meses considerados.
     */
    public static final int N_MESES = 12;

    /**
     * Preço unitário mínimo de um produto.
     */
    public static final double PRECO_MIN = 0.0;

    /**
     * Preço unitário máximo de um produto.
     */
    public static final double PRECO_MAX = 999.99;

    /**
     * Quantidade mínima de um produto que pode ser comprada numa venda.
     */
    public static final int QUANT_MIN = 0;

    /**
     * Quantidade máxima de um produto que pode ser comprada numa venda.
     */
    public static final int QUANT_MAX = 200;

    /**
     * Nome do ficheiro de vendas por defeito.
     */
    public static final String VENDAS_FILENAME = "../db/Vendas_1M.txt";

    /**
     * Nome do ficheiro de produtos por defeito.
     */
    public static final String PRODUTOS_FILENAME = "../db/Produtos.txt";

    /**
     * Nome do ficheiro de clientes por defeito.
     */
    public static final String CLIENTES_FILENAME = "../db/Clientes.txt";

    /**
     * Número de elementos a apresentar em cada página.
     */
    public static final int ELEMS_PAGINA = 25;
}

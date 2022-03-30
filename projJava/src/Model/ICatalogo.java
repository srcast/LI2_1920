package Model;

import java.util.Set;

public interface ICatalogo {
    /**
     * Método que devolve um Set com todos os códigos no catálogo.
     *
     * @return Set com os códigos.
     */
    Set<String> getCodigos();

    /**
     * Método que adiciona um código ao catálogo.
     *
     * @param codigo Código a adicionar ao catálogo.
     */
    void add(String codigo);

    /**
     * Método que verifica a existência de um código no catálogo.
     *
     * @param codigo Código cuja existência se pretende verificar.
     * @return true caso o código recebido como argumento exista, false caso contrário.
     */
    boolean contains(String codigo);

    /**
     * Método que devolve o tamanho do catálogo. Entende-se tamanho como sendo o número de elementos
     * presentes no ctálogo.
     *
     * @return Tamanho do catálogo.
     */
    int size();
}

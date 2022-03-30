package Model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe que implementa a estrutura de um catálogo.
 *
 * @author Ricardo Gomes.
 * @author Rui Fernandes.
 */
public class Catalogo implements ICatalogo, Serializable {
    /**
     * Set no qual são guardados os códigos após serem validados.
     */
    private Set<String> codigos;

    /**
     * Construtor por omissão para objetos da classe Catalogo.
     */
    public Catalogo() {
        this.codigos = new HashSet<>();
    }

    /**
     * Construtor parametrizado para objetos da classe Catalogo.
     *
     * @param codigos Set que contém os códigos a adicionar ao catálogo.
     */
    public Catalogo(Set<String> codigos) {
        this.codigos = new HashSet<>(codigos);
    }

    /**
     * Construtor de cópia para objetos da classe Catálogo.
     *
     * @param catalogo Instância da classe Catalogo a partir da qual se instancia um novo objeto.
     */
    public Catalogo(Catalogo catalogo) {
        this.codigos = catalogo.getCodigos();
    }

    /**
     * Método que devolve um Set com todos os códigos no catálogo.
     *
     * @return Set com os códigos.
     */
    public Set<String> getCodigos() {
        return new HashSet<>(this.codigos);
    }

    /**
     * Método que adiciona um código ao catálogo.
     *
     * @param codigo Código a adicionar ao catálogo.
     */
    public void add(String codigo) {
        this.codigos.add(codigo);
    }

    /**
     * Método que verifica a existência de um código no catálogo.
     *
     * @param codigo Código cuja existência se pretende verificar.
     * @return true caso o código recebido como argumento exista, false caso contrário.
     */
    public boolean contains(String codigo) {
        return this.codigos.contains(codigo);
    }

    /**
     * Método que devolve o tamanho do catálogo. Entende-se tamanho como sendo o número de elementos
     * presentes no ctálogo.
     *
     * @return Tamanho do catálogo.
     */
    public int size() {
        return this.codigos.size();
    }

    /**
     * Implementação do método hashCode.
     *
     * @return Código hash.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.codigos});
    }

    /**
     * Implementação do método equals.
     *
     * @param obj Objeto com o qual se testa a igualdade.
     * @return true caso o objeto argumento for igual ao objeto sobre o qual o método é chamado,
     * false caso contrário
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Catalogo catalogo = (Catalogo) obj;
        return this.codigos.equals(catalogo.getCodigos());
    }

    /**
     * Implementação do método clone.
     *
     * @return Cópia do objeto sobre o qual o método é chamado.
     */
    @Override
    public Catalogo clone() {
        return new Catalogo(this);
    }
}

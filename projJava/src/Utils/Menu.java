package Utils;

/**
 * Esta classe implementa um menu em modo texto.
 *
 * @author José Creissac Campos
 * @version v2.0 (20150601)
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Menu {
    // variáveis de instância
    private List<String> opcoes;
    private int op;

    /**
     * Constructor for objects of class Menu
     */
    public Menu(String[] opcoes) {
        this.opcoes = new ArrayList<String>();
        for (String op : opcoes)
            this.opcoes.add(op);
        this.op = 0;
    }

    /**
     * Método para apresentar o menu e ler uma opção.
     */
    public void executa() {
        do {
            showMenu();
            this.op = lerOpcao();
            System.out.println();
        } while (this.op == -1);
    }

    /**
     * Apresentar o menu
     */
    public void showMenu() {
        System.out.println(" *** " + this.opcoes.get(0) + " *** ");
        for (int i = 1; i < this.opcoes.size(); i++) {
            System.out.print(i);
            System.out.print(" - ");
            System.out.println(this.opcoes.get(i));
        }
        System.out.println("0 - Sair");
    }

    /**
     * Ler uma opção válida
     */
    private int lerOpcao() {
        int op;
        Scanner is = new Scanner(System.in);

        System.out.print("Opção: ");
        try {
            op = is.nextInt();
        } catch (InputMismatchException e) { // Não foi inscrito um int
            op = -1;
        }
        if (op < 0 || op > this.opcoes.size()) {
            System.out.println("Opção Inválida!!!");
            op = -1;
        }
        return op;
    }

    /**
     * Método para obter a última opção lida
     */
    public int getOpcao() {
        return this.op;
    }
}
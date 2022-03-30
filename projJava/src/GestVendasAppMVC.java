import Controller.GestVendasController;
import Controller.IGestVendasController;
import Model.GestVendasModel;
import Model.IGestVendasModel;
import View.GestVendasView;
import View.IGestVendasView;

/**
 * Classe responsável pela execução do programa.
 *
 * @author Ricardo Gomes.
 * @author Rui Fernandes.
 */
public class GestVendasAppMVC {
    public static void main(String[] args) {
        IGestVendasController controller = new GestVendasController();
        IGestVendasModel model = new GestVendasModel();
        IGestVendasView view = new GestVendasView();
        controller.setModel(model);
        controller.setView(view);
        controller.start();
        System.exit(0);
    }
}

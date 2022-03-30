package Controller;

import Model.IGestVendasModel;
import View.IGestVendasView;

public interface IGestVendasController {
    /**
     * Método que define o Model do programa.
     *
     * @param model Model.
     */
    void setModel(IGestVendasModel model);

    /**
     * Método que define a view do programa.
     *
     * @param view View.
     */
    void setView(IGestVendasView view);

    /**
     * Método que inicializa o fluxo do programa.
     */
    void start();
}

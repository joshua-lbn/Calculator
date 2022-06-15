package Calculator;

import Calculator.controller.Controller;
import Calculator.model.Model;
import Calculator.view.View;

/**
 * Die Hauptklasse, welche als erstes ausgeführt wird.
 * Sie erstellt die drei Hauptkomponenten nach dem MVC-Modell und gibt ihnen jeweils die Referenzen auf die anderen Hauptkomponenten
 */
public class Main {
    public static void main(String[] args) {
        // Drei Hauptkomponenten deklarieren und initialisieren
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller();
        // Weitergabe der Referenzen über gesonderte Methoden
        model.UpdateLinks(view, controller);
        view.UpdateLinks(model, controller);
        controller.UpdateLinks(model, view);
    }
}
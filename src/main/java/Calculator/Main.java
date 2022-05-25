package Calculator;

public class Main {
    public static void main(String args[])
    {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller();
        model.UpdateLinks(view, controller);
        view.UpdateLinks(model, controller);
        controller.UpdateLinks(model, view);
    }
}
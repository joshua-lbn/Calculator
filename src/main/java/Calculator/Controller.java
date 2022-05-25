package Calculator;

public class Controller {
    private LatexRenderer latexRenderer;
    private Model model;
    private View view;
    public Controller()
    {
        latexRenderer = new LatexRenderer();
    }
    public void UpdateLinks(Model m, View v)
    {
        model = m;
        view = v;
    }
    public void GenerateLatexView(String latexString)
    {
        model.SetImage(latexRenderer.RenderLatex(latexString));
    }
    public void SetLatexView()
    {
        // view.SetLatexView(model.GetImage());
    }
    public void UpdateView()
    {
        GenerateLatexView(model.GetLatexExpression());
        view.UpdateIconView(model.GetImage());
    }
    public void Update(String input){
        switch(input){
            case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" -> {
                model.ExtendExpression(input);
                model.ExtendLatex(input);
            }
            case "." -> {
                model.ExtendExpression(".");
                model.ExtendLatex("\\cdot");
            }
            case "+", "-", "*", "/" -> {
                model.ExtendExpression(input);
                model.ExtendLatex(input);
            }
            case "cos", "sin", "tan" -> {
                model.ExtendExpression(input);
                model.ExtendLatex("\\" + input + "(");
            }
            case "log" -> {
                model.ExtendExpression(input);
                model.ExtendLatex("\\log{10}(");
            }
            case "=" -> {
                //calculateOperation();
                //model.ExtendExpression(input);
            }
            case "delOneSign" -> {
                //model.ShortenExpression();
                //model.ShortenLatex();
                // TODO Jannis
            }
            case "delAll" -> {
                //model.ClearExpression();
                //model.ClearLatex();
            }
        }
    }
}

package Calculator;

import java.awt.Image;
import java.util.LinkedList;

public class Model {
    private View view;
    private Controller controller;
    private String expression;
    private String latexString;
    private LinkedList<String> expressionsElementsList;
    private LinkedList<String> latexElementsList;
    private double answer;
    private int tempReturnValue;
    private Image latexImage;

    public Model()
    {
        expression = "";
        latexString = "";
        LinkedList<String> expressionsElementsList = new LinkedList<String>();
        expressionsElementsList.add("");
        LinkedList<String> latexElementsList = new LinkedList<String>();
        latexElementsList.add("");
        answer = 0;
    }
    public void UpdateLinks(View v, Controller c)
    {
        view = v;
        controller = c;
    }
    public void ExtendExpression(String extension) {
        expressionsElementsList.add(extension);
        GenerateNewExpression();
    }
    public void ShortenExpression() {
        expressionsElementsList.remove(expressionsElementsList.size());
        GenerateNewExpression();
    }
    public void ExtendLatex(String extension) {
        latexElementsList.add(extension);
        GenerateNewLatex();
    }
    public void ShortenLatex(int last_to_shorten) {
        latexElementsList.remove(latexElementsList.size());
        GenerateNewLatex();
    }
    public void SetAnswer(double gottenAnswer) {
        answer = gottenAnswer;
    }
    public double GetAnswer() {
        return answer;
    }
    public Image GetImage()
    {
        return latexImage;
    }
    public void SetImage(Image i)
    {
        latexImage = i;
    }
    public String GetLatexExpression()
    {
        return latexString;
    }
    public String GetExpression()
    {
        return expression;
    }
    public void GenerateNewExpression()
    {
        expression = expressionsElementsList.toString();
    }
    public void GenerateNewLatex()
    {
        latexString = latexElementsList.toString();
    }
}

package Calculator;

import javax.swing.*;
import java.awt.Image;
import java.util.Iterator;
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
        LinkedList<String> latexElementsList = new LinkedList<String>();
        answer = 0;
    }
    public void UpdateLinks(View v, Controller c)
    {
        view = v;
        controller = c;
    }
    public void ExtendExpression(String extension) {
        expressionsElementsList.add(expressionsElementsList.size(),extension);
        GenerateNewExpression();
    }
    public void ShortenExpression() {
        expressionsElementsList.remove(expressionsElementsList.size());
        GenerateNewExpression();
    }
    public void ExtendLatex(String extension) {
        latexElementsList.add(latexElementsList.size(),extension);
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
        expression = "";
        Iterator<String> expressionIterator = expressionsElementsList.iterator();
        while(expressionIterator.hasNext())
        {
            expression = expression + expressionIterator.next();
        }
    }
    public void GenerateNewLatex()
    {
        latexString = "";
        Iterator<String> latexIterator = latexElementsList.iterator();
        while(latexIterator.hasNext())
        {
            latexString = latexString + latexIterator.next();
        }
    }
}

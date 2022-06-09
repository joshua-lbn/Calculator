package Calculator.model;

import Calculator.controller.Controller;
import Calculator.view.View;

import java.awt.Image;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Model-Klasse mit allen gespeicherten Daten
 */
public class Model {
    // Referenzen zu Controller und View für Methoden
    private View view;
    private Controller controller;
    // String mit Rechenausdruck
    private String expression;
    // String mit Latex-Ausdruck
    private String latexString;
    // Liste mit durch Eingaben eingefügten Elementen (des Ausdrucks)
    private LinkedList<String> expressionsElementsList;
    // Liste mit durch Eingaben eingefügten Elementen in Latex-Form
    private LinkedList<String> latexElementsList;
    // Antwort der letzten Rechnung (s. Ans-Taste)
    private double answer;
    // Latex-Ausdruck in Bildform zur Darstellung
    private Image latexImage;

    public Model()
    {
        // Beide Strings als leer initialisieren
        expression = "";
        latexString = "";
        // Listen initialisieren
        expressionsElementsList = new LinkedList<String>();
        latexElementsList = new LinkedList<String>();
        // Letzte Antwort als 0 initialisieren
        answer = 0;
    }
    /**
     * Methode zur Setzung der Referenzen auf View und Controller in Main
     * @param v View-Instanz
     * @param c Controller-Instanz
     */
    public void UpdateLinks(View v, Controller c)
    {
        view = v;
        controller = c;
    }
    /**
     * Methode zur Erweiterung des Ausdrucks-Strings: Element wird in Liste eingefuegt und anschliessend der String erweitert
     * @param extension Zu erweiternder Ausdruck
     */
    public void ExtendExpression(String extension) {
        expressionsElementsList.add(extension);
        expression = expression + extension;
    }
    /**
     * Methode zur Verkürzung des Ausdrucks-Strings: letztes Element wird aus der Liste entfernt und mithilfe der Liste der String neu generiert
     */
    public void ShortenExpression() {
        expressionsElementsList.remove(expressionsElementsList.size());
        GenerateNewExpression();
    }

    /**
     * Methode zur Räumung des Expression-Stringes
     */
    public void ClearExpression()
    {
        expression = "";
    }
    /**
     * Methode zur Erweiterung des Latex-Strings: Element wird in Liste eingefuegt und anschliessend der String erweitert
     * @param extension Zu erweiternder Ausdruck
     */
    public void ExtendLatex(String extension) {
        latexElementsList.add(extension);
        latexString = latexString + extension;
    }
    /**
     * Methode zur Verkürzung des Ausdrucks-Strings: letztes Element wird aus der Liste entfernt und mithilfe der Liste der String neu generiert
     */
    public void ShortenLatex() {
        latexElementsList.remove(latexElementsList.size());
        GenerateNewLatex();
    }
    /**
     * Methode zur Räumung des Latex-Strings
     */
    public void ClearLatex()
    {
        latexString = "";
    }
    /**
     * Getter für die Answer-Variable, die als Antwortspeicher dient
     * @return Gespeicherte Antwort
     */
    public double GetAnswer() {
        return answer;
    }
    /**
     * Setter für die Answer-Variable, die als Antwortspeicher dient
     * @param gottenAnswer Ausgerechneter Wert
     */
    public void SetAnswer(double gottenAnswer) {
        answer = gottenAnswer;
    }
    /**
     * Getter für die Image-Variable, welche den Latex-String in Bildform enthält
     * @return Latex-Bild
     */
    public Image GetImage()
    {
        return latexImage;
    }
    /**
     * Setter für die Image-Variable, welche den Latex-String in Bildform enthält
     * @param i Zu speicherndes Bild
     */
    public void SetImage(Image i)
    {
        latexImage = i;
    }

    /**
     * Getter für die LatexString-Variable, die den Latex-Ausdruck in Textform enthält
     * @return String mit Latex-Ausdruck
     */
    public String GetLatexExpression()
    {
        return latexString;
    }
    /**
     * Getter für die Expression-Variable, die den normalen Ausdruck in Textform enthält
     * @return String mit Ausdruck
     */
    public String GetExpression()
    {
        return expression;
    }

    /**
     * Methode zur Generierung des Expression-Strings aus der Liste
     */
    public void GenerateNewExpression()
    {
        expression = "";
        Iterator iterator = expressionsElementsList.iterator();
        while(iterator.hasNext())
        {
            expression = expression + iterator.next();
        }
    }
    /**
     * Methode zur Generierung des Latex-Strings aus der Liste
     */
    public void GenerateNewLatex()
    {
        latexString = "";
        Iterator iterator = latexElementsList.iterator();
        while(iterator.hasNext())
        {
            latexString = latexString + iterator.next();
        }
    }
}

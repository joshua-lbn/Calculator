package Calculator.model;

import Calculator.controller.Controller;
import Calculator.view.View;

import java.awt.Image;
import java.util.LinkedList;

/**
 * Model-Klasse mit allen gespeicherten Daten
 */
public class Model {
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
    private double ans;
    // Latex-Ausdruck in Bildform zur Darstellung
    private Image latexImage;

    public Model() {
        // Beide Strings als leer initialisieren
        expression = "";
        latexString = "";
        // Listen initialisieren
        expressionsElementsList = new LinkedList<>();
        latexElementsList = new LinkedList<>();
        // Letzte Antwort als 0 initialisieren
        ans = 0;
    }

    /**
     * Methode zur Setzung der Referenzen auf View und Controller in Main
     *
     * @param v View-Instanz
     * @param c Controller-Instanz
     */
    public void UpdateLinks(View v, Controller c) {
        view = v;
        controller = c;
    }

    /**
     * Methode zur Erweiterung des Ausdrucks-Strings: Element wird in Liste eingefuegt und anschliessend der String erweitert
     *
     * @param extension Zu erweiternder Ausdruck
     */
    public void ExtendExpression(String extension) {
        expressionsElementsList.add(extension);
        expression += extension;
    }

    /**
     * Methode zur Verkürzung des Ausdrucks-Strings: letztes Element wird aus der Liste entfernt und mithilfe der Liste der String neu generiert
     */
    public void ShortenExpression() {
        expressionsElementsList.remove(expressionsElementsList.size());
        expression = GenerateNewExpression();
    }

    /**
     * Methode zur Räumung des Expression-Stringes
     */
    public void ClearExpression() {
        expression = "";
    }

    /**
     * Methode zur Erweiterung des Latex-Strings: Element wird in Liste eingefuegt und anschliessend der String erweitert
     *
     * @param extension Zu erweiternder Ausdruck
     */
    public void ExtendLatex(String extension) {
        latexElementsList.add(extension);
        latexString += extension;
    }

    /**
     * Methode zur Verkürzung des Ausdrucks-Strings: letztes Element wird aus der Liste entfernt und mithilfe der Liste der String neu generiert
     */
    public void ShortenLatex() {
        latexElementsList.removeLast();
        latexString = GenerateNewLatex();
    }

    /**
     * Methode zur Räumung des Latex-Strings
     */
    public void ClearLatex() {
        latexString = "";
    }

    /**
     * Getter für die Answer-Variable, die als Antwortspeicher dient
     *
     * @return Gespeicherte Antwort
     */
    public double GetAnswer() {
        return ans;
    }

    /**
     * Setter für die Answer-Variable, die als Antwortspeicher dient
     *
     * @param gottenAnswer Ausgerechneter Wert
     */
    public void SetAnswer(double gottenAnswer) {
        ClearExpression();
        ClearLatex();
        ans = gottenAnswer;
        ExtendExpression(Double.toString(ans));
        ExtendLatex(Double.toString(ans));
    }

    /**
     * Getter für die Image-Variable, welche den Latex-String in Bildform enthält
     *
     * @return Latex-Bild
     */
    public Image GetImage() {
        return latexImage;
    }

    /**
     * Setter für die Image-Variable, welche den Latex-String in Bildform enthält
     *
     * @param i Zu speicherndes Bild
     */
    public void SetImage(Image i) {
        latexImage = i;
    }

    /**
     * Getter für die LatexString-Variable, die den Latex-Ausdruck in Textform enthält
     *
     * @return String mit Latex-Ausdruck
     */
    public String GetLatexExpression() {
        return latexString;
    }

    /**
     * Getter für die Expression-Variable, die den normalen Ausdruck in Textform enthält
     *
     * @return String mit Ausdruck
     */
    public String GetExpression() {
        return expression;
    }

    /**
     * Methode zur Generierung des Expression-Strings aus der Liste
     *
     * @return Generierte Expression
     */
    public String GenerateNewExpression() {
        return expressionsElementsList.stream().reduce("", (a, b) -> a + b);
    }

    /**
     * Methode zur Generierung des Latex-Strings aus der Liste
     *
     * @return Generierte Expression
     */
    public String GenerateNewLatex() {
        return latexElementsList.stream().reduce("", (a, b) -> a + b);
    }
}

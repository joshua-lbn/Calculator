package Calculator.model;

import Calculator.controller.Controller;
import Calculator.view.View;

import java.awt.Image;
import java.util.LinkedList;

/**
 * Model-Klasse mit allen gespeicherten Daten.
 */
public class Model {
    private View view;
    private Controller controller;
    // String mit Rechenausdruck
    private String expression;
    // String mit Latex-Ausdruck
    private String latex;
    // Liste mit durch Eingaben eingefügten Elementen (des Ausdrucks)
    private static LinkedList<String> expressionsElementsList;
    // Liste mit durch Eingaben eingefügten Elementen in Latex-Form
    private LinkedList<String> latexElementsList;
    // Antwort der letzten Rechnung (s. Ans-Taste)
    private double ans;
    // Latex-Ausdruck in Bildform zur Darstellung
    private Image latexImage;
    private int cursorPosition;
    private String cursorSymbolLatex;
    private CalculatorState state;

    public Model() {
        // Beide Strings als leer initialisieren
        expression = "";
        latex = "";
        // Listen initialisieren
        expressionsElementsList = new LinkedList<>();
        latexElementsList = new LinkedList<>();
        // Letzte Antwort als 0 initialisieren
        ans = 0;
        cursorSymbolLatex = "|";
        CursorBack();
    }

    /**
     * Methode zur Erweiterung des Ausdrucks-Strings: Element wird in Liste eingefuegt und anschliessend der String erweitert.
     * @param extension Zu erweiternder Ausdruck
     */
    public void ExtendExpression(String extension) {
        expressionsElementsList.add(cursorPosition, extension);
        expression = GenerateNewExpression();
    }

    /**
     * Methode zur Verkürzung des Ausdrucks-Strings: letztes Element wird aus der Liste entfernt und mithilfe der Liste der String neu generiert.
     */
    public void ShortenExpression() {
        if(expressionsElementsList.size() > 0) {
            expressionsElementsList.remove(cursorPosition-1);
            expression = GenerateNewExpression();
        }
    }

    /**
     * Methode zur Räumung des Expression-Strings und seiner Liste.
     */
    public void ClearExpression() {
        expression = "";
        expressionsElementsList.clear();
    }

    /**
     * Methode zur Erweiterung des Latex-Strings: Element wird in Liste eingefuegt und anschliessend der String erweitert.
     * @param extension Zu erweiternder Ausdruck
     */
    public void ExtendLatex(String extension) {
        latexElementsList.add(cursorPosition, extension);
        cursorPosition += 1;
        latex = GenerateNewLatex();
    }

    /**
     * Methode zur Verkürzung des Ausdrucks-Strings: letztes Element wird aus der Liste entfernt und mithilfe der Liste der String neu generiert.
     */
    public void ShortenLatex() {
        if(latexElementsList.size() > 1) {
            latexElementsList.remove(cursorPosition-1);
            cursorPosition -= 1;
            latex = GenerateNewLatex();
        }
    }

    /**
     * Methode zur Leerung des Latex-Strings und seiner Liste, wobei der Cursor an dne Zeilenanfang zurueckkehrt.
     */
    public void ClearLatex() {
        latex = "";
        latexElementsList.clear();
        CursorBack();
    }

    /**
     * Methode zur Leerung des Latex-Strings und seiner Liste ohne Cursor zur Ergebnisanzeige.
     */
    public void ClearLatexSolution() {
        latex = "";
        latexElementsList.clear();
        // Um Ergebnis an erster Stelle einzufuegen, muss die Einfuegeposition zurueckgesetzt werden
        cursorPosition = 0;
    }

    /**
     * Getter für die Answer-Variable, die als Antwortspeicher dient.
     * @return Gespeicherte Antwort als String
     */
    public String GetAnswer() {
        return Double.toString(ans);
    }

    /**
     * Setter für die Answer-Variable, die als Antwortspeicher dient.
     * @param gottenAnswer Ausgerechneter Wert
     */
    public void SetAnswer(double gottenAnswer) {
        // Strings leeren
        ClearExpression();
        ClearLatexSolution();
        // Antwort speichern
        ans = gottenAnswer;
        // Antwort zu Strings hinzufuegen
        ExtendExpression(Double.toString(ans));
        ExtendLatex(Double.toString(ans));
    }

    /**
     * Getter für die Image-Variable, welche den Latex-String in Bildform enthält.
     * @return Latex-Bild
     */
    public Image GetImage() {
        return latexImage;
    }

    /**
     * Setter für die Image-Variable, welche den Latex-String in Bildform enthält.
     * @param i Zu speicherndes Bild
     */
    public void SetImage(Image i) {
        latexImage = i;
    }

    /**
     * Getter für die LatexString-Variable, die den Latex-Ausdruck in Textform enthält.
     * @return String mit Latex-Ausdruck
     */
    public String GetLatexExpression() {
        return latex;
    }

    /**
     * Getter für die Expression-Variable, die den normalen Ausdruck in Textform enthält.
     * @return String mit Ausdruck
     */
    public String GetExpression() {
        return expression;
    }

    /**
     * Methode zur Generierung des Expression-Strings aus der Liste.
     * @return Generierte Expression
     */
    public String GenerateNewExpression() {
        return expressionsElementsList.stream().reduce("", (a, b) -> a + b);
    }

    /**
     * Methode zur Generierung des Latex-Strings aus der Liste.
     * @return Generierte Expression
     */
    public String GenerateNewLatex() {
        return latexElementsList.stream().reduce("", (a, b) -> a + b);
    }

    /**
     * Methode, um den Cursor um eine Position nach links zu verschieben.
     */
    public void CursorLeft()
    {
        if(cursorPosition > 0)
        {
            latexElementsList.remove(cursorPosition);
            cursorPosition -= 1;
            latexElementsList.add(cursorPosition, cursorSymbolLatex);
            expression = GenerateNewExpression();
            latex = GenerateNewLatex();
        }
    }

    /**
     * Methode, um den Cursor um eine Position nach rechts zu verschieben.
     */
    public void CursorRight() {
        if (cursorPosition < latexElementsList.size() - 1) {
            latexElementsList.remove(cursorPosition);
            cursorPosition += 1;
            latexElementsList.add(cursorPosition, cursorSymbolLatex);
            latex = GenerateNewLatex();
        }
    }

    /**
     * Methode, um den Cursor wieder in den Ursprungszustand zu versetzen.
     */
    public void CursorBack() {
        cursorPosition = 0;
        latexElementsList.add(cursorSymbolLatex);
        latex = GenerateNewLatex();
    }

    /**
     * Methode, um den Zustand des Taschenrechners zu erhalten.
     */
    public CalculatorState GetState() {
        return state;
    }

    /**
     * Methode, um den Zustand des Taschenrechners zu setzen.
     */
    public void SetState(CalculatorState newState) {
        state = newState;
    }

    /**
     * Methode zur Setzung der Referenzen auf View und Controller in Main.
     * @param v View-Instanz
     * @param c Controller-Instanz
     */
    public void UpdateLinks(View v, Controller c) {
        view = v;
        controller = c;
    }
    public static int ListSize() {
        return expressionsElementsList.size();
    }
}

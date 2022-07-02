package Calculator.model;

import Calculator.controller.Controller;
import Calculator.view.View;

import java.util.LinkedList;

/**
 * Model-Klasse mit allen gespeicherten Daten.
 */
public class Model {
    private View view;
    private Controller controller;
    // String mit Rechenausdruck
    private String expression;
    // String mit HTML-Ausdruck
    private String html;
    // Liste mit durch Eingaben eingefügten Elementen (des Ausdrucks)
    private LinkedList<String> expressionsElementsList;
    // Liste mit durch Eingaben eingefügten Elementen in HTML-Form
    private LinkedList<String> htmlElementsList;
    // Antwort der letzten Rechnung (s. Ans-Taste)
    private double ans;
    // HTML-Ausdruck in Bildform zur Darstellung
    private int cursorPosition;
    private String cursorSymbolHTML;
    private CalculatorState state;
    private boolean exponentMode;

    public Model() {
        // Beide Strings als leer initialisieren
        expression = "";
        html = "";
        // Listen initialisieren
        expressionsElementsList = new LinkedList<>();
        htmlElementsList = new LinkedList<>();
        // Letzte Antwort als 0 initialisieren
        ans = 0;
        cursorSymbolHTML = "&#10073;";
        CursorBack();
        exponentMode = false;
    }

    /**
     * Methode zur Erweiterung des Ausdrucks-Strings: Element wird in Liste eingefuegt und anschliessend der String erweitert.
     * @param extension Zu erweiternder Ausdruck
     */
    public void ExtendExpression(String extension) {
        if(expressionsElementsList.size() < 101)
        {
            expressionsElementsList.add(cursorPosition, extension);
            expression = GenerateNewExpression();
        }
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
     * Methode zur Erweiterung des HTML-Strings: Element wird in Liste eingefuegt und anschliessend der String erweitert.
     * @param extension Zu erweiternder Ausdruck
     */
    public void ExtendHTML(String extension) {
        htmlElementsList.add(cursorPosition, extension);
        cursorPosition += 1;
        html = GenerateNewHTML();
    }

    /**
     * Methode zur Verkürzung des Ausdrucks-Strings: letztes Element wird aus der Liste entfernt und mithilfe der Liste der String neu generiert.
     */
    public void ShortenHTML() {
        if(htmlElementsList.size() > 1) {
            htmlElementsList.remove(cursorPosition-1);
            cursorPosition -= 1;
            html = GenerateNewHTML();
        }
    }

    /**
     * Methode zur Leerung des HTML-Strings und seiner Liste, wobei der Cursor an dne Zeilenanfang zurueckkehrt.
     */
    public void ClearHTML() {
        html = "";
        htmlElementsList.clear();
        CursorBack();
    }

    /**
     * Methode zur Leerung des HTML-Strings und seiner Liste ohne Cursor zur Ergebnisanzeige.
     */
    public void ClearHTMLSolution() {
        html = "";
        htmlElementsList.clear();
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
        ClearHTMLSolution();
        // Antwort speichern
        ans = gottenAnswer;
        // Antwort zu Strings hinzufuegen
        ExtendExpression(Double.toString(ans));
        ExtendHTML(Double.toString(ans));
    }

    /**
     * Getter für die HTML-String-Variable, die den HTML-Ausdruck in Textform enthält.
     * Dabei werden die umschliessenden <HTML>-Tags mit eingefuegt.
     * @return String mit HTML-Ausdruck
     */
    public String GetHTMLExpression() {
        return "<html><pre style=\"font-family: Consolas; font-size: " + CalculateSize() + "px\">" + html + "</pre></html>";
    }

    /**
     * Methode, um die aktuelle Schriftgroesse in Abhaengigkeit von der Fenstergroeße zu berechnen.
     * @return Neue Schriftgroesse
     */
    public int CalculateSize()
    {
        return Math.round(35 * view.GetWindowHeight() / 300);
    }

    /**
     * Methode, um die Laenge der HTML-Liste zu erhalten.
     * @return Laenge der Liste
     */
    public int GetHTMLElementsListSize()
    {
        return htmlElementsList.size();
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
     * Methode zur Generierung des HTML-Strings aus der Liste.
     * @return Generierte Expression
     */
    public String GenerateNewHTML() {
        return htmlElementsList.stream().reduce("", (a, b) -> a + b);
    }

    /**
     * Methode, um den Cursor um eine Position nach links zu verschieben.
     */
    public void CursorLeft()
    {
        if(cursorPosition > 0)
        {
            htmlElementsList.remove(cursorPosition);
            cursorPosition -= 1;
            htmlElementsList.add(cursorPosition, cursorSymbolHTML);
            expression = GenerateNewExpression();
            html = GenerateNewHTML();
        }
    }

    /**
     * Methode, um den Cursor um eine Position nach rechts zu verschieben.
     */
    public void CursorRight() {
        if (cursorPosition < htmlElementsList.size() - 1) {
            htmlElementsList.remove(cursorPosition);
            cursorPosition += 1;
            htmlElementsList.add(cursorPosition, cursorSymbolHTML);
            html = GenerateNewHTML();
        }
    }

    /**
     * Methode, um den Cursor wieder in den Ursprungszustand zu versetzen.
     */
    public void CursorBack() {
        cursorPosition = 0;
        htmlElementsList.add(cursorSymbolHTML);
        html = GenerateNewHTML();
    }

    /**
     * Methode, um die Position des Cursors in der Liste zu erhalten.
     * @return Position als int
     */
    public int GetCursorPosition()
    {
        return cursorPosition;
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
     * Methode, um den Exponentenmodus zu erhalten.
     * Genutzt im Controller, um zu erkennen, ob nun <sup> oder </sup> verwendet werden sollte, d.h. ob nun der
     * Exponent "geoeffnet" oder "geschlossen" wird.
     */
    public boolean GetExponentMode()
    {
        return exponentMode;
    }

    /**
     * Methode, um den Exponentenmodus zu aendern.
     * Genutzt im Controller, um den Modus umzuschalten.
     */
    public void ChangeExponentMode()
    {
        exponentMode = !exponentMode;
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
}

package Calculator.controller;

import Calculator.model.Model;
import Calculator.view.View;

/**
 * Controller-Klasse mit Programmier-Logik
 */
public class Controller {
    private Model model;
    private View view;
    private CalculatorState calculatorState;
    private LatexRenderer latexRenderer;
    private Parser parser;

    /**
     * Konstruktur: Latex-Renderer und Parser initialisieren sowie den State setzen.
     */
    public Controller() {
        latexRenderer = new LatexRenderer();
        parser = new Parser();
        calculatorState = CalculatorState.CALCULATION;
    }

    /**
     * Methode zur Setzung der Referenzen auf View und Controller in Main
     * @param m Model-Instanz
     * @param v View-Instanz
     */
    public void UpdateLinks(Model m, View v) {
        model = m;
        view = v;
    }

    /**
     * Methode zur Erweiterung der Model-Strings anhand der Eingabe, die der View weitergibt.
     * Beginnt nach einer Berechnung eine neue Eingabe.
     * @param input Eingabe-String
     */
    public void Update(String input) {
        // Wenn eine Berechnung fertig ist und eine neue Berechnung angefangen wird, soll die alte Berechnung geloescht werden
        if (calculatorState == CalculatorState.SOLUTION) {
            if(!input.equals("=")) {
                // Ist-Gleich fuehrt zu gleichem Ergebnis; funktionslos
            }
            // Eigentliche Funktion
            else {
                // Expression sowie LatexString leeren und Anzeige (zu leer) aktualisieren
                model.ClearExpression();
                model.ClearLatex();
                UpdateView();
                // State setzen
                calculatorState = CalculatorState.CALCULATION;
            }
        }

        switch (input) {
            // Zahleneingabe, Klammern, Grundoperatoren: einfach einfuegen
            case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "(", ")", "+", "-", "*", "/" -> {
                model.ExtendExpression(input);
                model.ExtendLatex(input);
            }
            // Komma: Expression und Interna nutzen Punkt, Latex mit üblichem Komma
            case "." -> {
                model.ExtendExpression(input);
                model.ExtendLatex(",");
            }
            // Trigonometrie: Expression einfach einfuegen, Latex einfuegen und Klammer anfuegen
            case "cos", "sin", "tan" -> {
                model.ExtendExpression(input);
                model.ExtendLatex("\\" + input + "(");
            }
            // Logarithmus: Expression einfach einfuegen, in Latex Sonderkommando
            case "lg" -> {
                model.ExtendExpression(input);
                model.ExtendLatex("\\log_{10}(");
            }
            // Ist-Gleich: Berechnung anstossen
            case "=" -> {
                // Parser berechnet aktuellen Ausdruck im Model und Setter leert expression sowie latexString und fügt Ergebnis ein
                model.SetAnswer(parser.Calculate(model.GetExpression()));
                // Anzeige aktualisieren
                UpdateView();
                // State setzen
                calculatorState = CalculatorState.SOLUTION;
            }
            // Einfachkorrektur: in beiden Strings das zuletzt eingefuegte Element entfernen
            case "delOneSign" -> {
                model.ShortenExpression();
                model.ShortenLatex();
            }
            // Komplettloeschung: beide Strings komplett leeren
            case "delAll" -> {
                model.ClearExpression();
                model.ClearLatex();
            }
        }
    }

    /**
     * Methode zur Generierung des Bildes anhand des Latex-Strings.
     * @param latexString Latex-String zur Generierung
     */
    public void GenerateLatexView(String latexString) {
        // Speicherung der Antwort, die der LatexRenderer gibt, im Model
        model.SetImage(latexRenderer.RenderLatex(latexString));
    }

    /**
     * Methode zum Aktualisieren des Latex-Bildes in der GUI.
     */
    public void UpdateView() {
        // Latex-Bild anhand der Liste im Model generieren
        GenerateLatexView(model.GetLatexExpression());
        // Latex-Bild aus Model, da in GenerateLatexView() dort gespeichert, an View weitergeben
        view.UpdateIconView(model.GetImage());
    }
}

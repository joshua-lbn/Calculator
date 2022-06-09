package Calculator.controller;

import Calculator.view.LatexRenderer;
import Calculator.model.Model;
import Calculator.view.View;

/**
 * Controller-Klasse mit Programmier-Logik
 */
public class Controller {
    // Referenz auf die Klasse LatexRenderer, die das Bild zum Latex-String generiert
    private LatexRenderer latexRenderer;
    // Referenzen auf Model und View
    private Model model;
    private View view;
    private CalculatorState calculatorState;

    /**
     * Constructor: Latex-Renderer initialisieren
     */
    public Controller() {
        latexRenderer = new LatexRenderer();
        calculatorState = CalculatorState.CALCULATION;
    }

    /**
     * Methode zur Setzung der Referenzen auf View und Controller in Main
     *
     * @param m Model-Instanz
     * @param v View-Instanz
     */
    public void UpdateLinks(Model m, View v) {
        model = m;
        view = v;
    }

    /**
     * Methode zur Erweiterung der Model-Strings anhand der Eingabe, die der View weitergibt
     *
     * @param input Eingabe-String
     */
    public void Update(String input) {
        if (!input.equals("=") && calculatorState == CalculatorState.SOLUTION) {
            model.ClearExpression();
            model.ClearLatex();
            calculatorState = CalculatorState.CALCULATION;
        }
        switch (input) {
            // Zahleneingabe: einfach einfuegen
            case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" -> {
                model.ExtendExpression(input);
                model.ExtendLatex(input);
            }
            // Punkt: einfach einfuegen
            case "." -> {
                model.ExtendExpression(".");
                model.ExtendLatex(".");
            }
            // Grundoperatoren: einfach einfuegen
            case "+", "-", "*", "/" -> {
                model.ExtendExpression(input);
                model.ExtendLatex(input);
            }
            // Trigonometrie: Expression einfach einfuegen, Latex einfuegen und Klammer anfuegen
            case "cos", "sin", "tan" -> {
                model.ExtendExpression(input);
                model.ExtendLatex("\\" + input + "(");
            }
            // Logarithmus: Expression einfach einfuegen, in Latex Sonderkommando
            case "log" -> {
                model.ExtendExpression(input);
                model.ExtendLatex("\\log_{10}(");
            }
            // Ist-Gleich: Berechnung anstossen
            case "=" -> {
                calculatorState = CalculatorState.SOLUTION;
                //calculateOperation();
                //model.SetAnswer();
                //model.ClearExpression();
                //model.ClearLatex();
                //model.ExtendExpression(model.GetAnswer());
            }
            // Einfachkorrektur: in beiden Strings das zuletzt eingefuegte Element entfernen
            case "delOneSign" -> {
                model.ShortenExpression();
                model.ShortenLatex();
            }
            // Komplettloeschung: beide Strings komplett räumen
            case "delAll" -> {
                model.ClearExpression();
                model.ClearLatex();
            }
        }
    }

    /**
     * Methode zur Generierung des Bildes zum Latex-String
     *
     * @param latexString Latex-String zur Generierung
     */
    public void GenerateLatexView(String latexString) {
        // Speicherung der Antwort, die der LatexRenderer gibt, im Model
        model.SetImage(latexRenderer.RenderLatex(latexString));
    }

    /**
     * Methode zum Update des Latex-Bildes in der GUI
     */
    public void UpdateView() {
        // Latex-Bild anhand der Liste im Model generieren
        GenerateLatexView(model.GetLatexExpression());
        // Latex-Bild aus Model, da in GenerateLatexView dort gespeichert, an View weitergeben
        view.UpdateIconView(model.GetImage());
    }
}

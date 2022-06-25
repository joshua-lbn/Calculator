package Calculator.controller;

import Calculator.model.CalculatorState;
import Calculator.model.Model;
import Calculator.view.View;

/**
 * Controller-Klasse mit Programmierlogik.
 */
public class Controller {
    private Model model;
    private View view;
    private LatexRenderer latexRenderer;
    private Parser parser;

    boolean ShifttasteGedrückt;

    /**
     * Konstruktor: Latex-Renderer und Parser initialisieren sowie den State setzen.
     */
    public Controller() {
        latexRenderer = new LatexRenderer();
        parser = new Parser();
        //panelLevel.setFocusable(true);
    }

    /**
     * Methode zur Erweiterung der Model-Strings anhand der Eingabe, die der View weitergibt.
     * Beginnt nach einer Berechnung eine neue Eingabe.
     * @param input Eingabe-String
     */
    public void Update(String input) {
        /*
         * Wenn eine Berechnung fertig ist (somit der Taschenrechner im Loesungsanzeige-Zustand ist) und eine
         * neue Berechnung angefangen wird, soll die alte Berechnung gelöscht werden
         *
         */
        if (model.GetState() == CalculatorState.SOLUTION) {
            // Falls "Ist-Gleich"-Taste gedrueckt: nichts tun, da bereits Berechnung ausgefuerht
            if(input.equals("=")) {
            }
            // Eigentliche Methode
            else {
                // Beide Eingabestrings loeschen
                model.ClearExpression();
                model.ClearLatex();
                // Zustand auf Berechnung setzen
                model.SetState(CalculatorState.CALCULATION);
            }
        }
        // Zahleneingabe und Grundoperatoren: einfach einfuegen
        if(input.matches("[0123456789+\\-*/]")) {
            model.ExtendExpression(input);
            model.ExtendLatex(input);
        }
        // Klammern: in Expression einfach einfuegen, in Latex mit geschweiften Klammern umrunden
        else if(input.equals("(")) {
            model.ExtendExpression(input);
            model.ExtendLatex("{(");
        }
        else if(input.equals(")")) {
            model.ExtendExpression(input);
            model.ExtendLatex(")}");
        }
        // Komma: Expression und Latex mit Punkt
        else if (input.equals(",")) {
            model.ExtendExpression(".");
            model.ExtendLatex(".");
        }
        // Exponent: nur "Hoch"-Zeichen einfuegen
        else if (input.equals("x^")) {
            model.ExtendExpression("^");
            model.ExtendLatex("^");
        }
        // Trigonometrie: Expression einfach einfuegen, in Latex als Kommando einfuegen
        else if (input.equals("sin(") || input.equals("cos(") || input.equals("tan(")) {
            model.ExtendExpression(input);
            model.ExtendLatex("\\" + input.substring(0,3) + "{(");
        }
        // Logarithmus: Expression einfach einfuegen, in Latex Sonderkommando
        else if (input.equals("lg(")) {
            model.ExtendExpression(input);
            model.ExtendLatex("\\log_{10}{(");
        }
        // Ist-Gleich: Berechnung anstossen
        else if (input.equals("=")) {
            // Parser berechnet aktuellen Ausdruck im Model und Setter leert expression sowie latexString und fügt Ergebnis ein
            model.SetAnswer(parser.Calculate(model.GetExpression()));
            // Anzeige aktualisieren
            UpdateView();
            // Zustand auf Loesungsanzeige setzen
            model.SetState(CalculatorState.SOLUTION);
        }
        // Ans: in Expression und Latex Dezimalwert aus Antwortspeicher einfuegen
        else if (input.equals("Ans")) {
            model.ExtendExpression(model.GetAnswer());
            model.ExtendLatex(model.GetAnswer());
        }
        // Einfachkorrektur: in beiden Strings das zuletzt eingefuegte Element entfernen
        else if (input.equals("DEL")) {
            model.ShortenExpression();
            model.ShortenLatex();
        }
        // Komplettloeschung: beide Strings komplett leeren und Cursor zuruecksetzen
        else if (input.equals("AC")) {
            model.ClearExpression();
            model.ClearLatex();
        }
        // Cursortasten: Aenderungen im Model anstossen
        else if (input.equals("<-")) {
            model.CursorLeft();
        }
        else if (input.equals("->")) {
            model.CursorRight();
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
     * Methode zum Aktualisieren des Latex-Bildes i, View.
     */
    public void UpdateView() {
        // Latex-Bild anhand der Liste im Model generieren
        GenerateLatexView(model.GetLatexExpression());
        // Latex-Bild aus Model, da in GenerateLatexView() dort gespeichert, an View weitergeben
        view.UpdateTest(model.ListSize()/8);
        view.UpdateIconView(model.GetImage());
    }

    /**
     * Methode zur Setzung der Referenzen auf Model und View in Main.
     * @param m Model-Instanz
     * @param v View-Instanz
     */
    public void UpdateLinks(Model m, View v) {
        model = m;
        view = v;
        model.SetState(CalculatorState.CALCULATION);
        //view.addKeyListener1(this);
    }
}


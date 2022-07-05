package Calculator.controller;

import Calculator.model.CalculatorState;
import Calculator.model.Model;
import Calculator.view.main.View;

/**
 * Controller-Klasse mit Programmierlogik.
 */
public class Controller {
    private Model model;
    private View view;
    private Parser parser;

    /**
     * Konstruktor: Parser initialisieren.
     */
    public Controller() {
        parser = new Parser();
    }

    /**
     * Methode zur Erweiterung der Model-Strings anhand der Eingabe, die der View weitergibt.
     * Beginnt nach einer Berechnung eine neue Eingabe.
     * @param input Eingabe-String
     */
    public void Update(String input) {
        /*
         * Wenn eine Berechnung fertig ist (somit ist Taschenrechner im Loesungsanzeige-Zustand) und eine
         * neue Berechnung angefangen wird, soll die alte Berechnung gelöscht werden
         *
         */
        if (model.GetState() == CalculatorState.SOLUTION) {
            // Falls "Ist-Gleich"-Taste gedrueckt: nichts tun, da bereits Berechnung ausgefuerht
            if(input.equals("=")) {
            }
            // Eigentliche Methode
            else {
                // Anzeigestring loeschen
                model.ClearHTML();
                // Zustand auf Berechnung setzen
                model.SetState(CalculatorState.CALCULATION);
            }
        }
        // Zahleneingabe, Kommas und Trigonometrie: in beide einfach einfuegen
        if(input.matches("[0123456789()]") || input.equals("sin(") || input.equals("cos(") || input.equals("tan(")) {
            model.ExtendExpression(input);
            model.ExtendHTML(input);
        }
        // Grundoperatoren: in Expression einfach einfuegen, in HTML mit Sonderzeichen
        else if(input.equals("+"))
        {
            model.ExtendExpression(input);
            model.ExtendHTML("&#43;");
        }
        else if(input.equals("-"))
        {
            model.ExtendExpression(input);
            model.ExtendHTML("&#8722;");
        }
        else if(input.equals("*"))
        {
            model.ExtendExpression(input);
            model.ExtendHTML("&#215;");
        }
        else if(input.equals("/"))
        {
            model.ExtendExpression(input);
            model.ExtendHTML("&#247;");
        }
        // Komma: Expression mit Punkt und HTML mit Komma
        else if (input.equals(",")) {
            model.ExtendExpression(".");
            model.ExtendHTML(",");
        }
        /* Exponent: Exponent in Klammern in Expression einfuegen; in HTML erst erkennen, ob nun geoeffnet oder
         * geschlossen, dann einfuegen der entsprechenden Zeichens und Aendern des Modus
         */
        else if (input.equals("x^")) {
            if(model.GetExponentMode())
            {
                model.ExtendExpression(")");
                model.ExtendHTML("</sup>");
                model.ChangeExponentMode();
            }
            else if(!model.GetExponentMode())
            {
                model.ExtendExpression("^(");
                model.ExtendHTML("<sup>");
                model.ChangeExponentMode();
            }
        }
        // Logarithmus: Expression einfach einfuegen, in HTML Sonderkommando
        else if (input.equals("lg(")) {
            model.ExtendExpression(input);
            model.ExtendHTML("log<sub>10</sub>(");
        }
        // Ist-Gleich: Berechnung anstossen
        else if (input.equals("=")) {
            // Parser berechnet aktuellen Ausdruck im Model und Setter leert expression sowie HTML-String und fügt Ergebnis ein
            model.SetAnswer(parser.Calculate(model.GetExpression()));
            // Anzeige aktualisieren
            view.UpdateView();
            // Zustand auf Loesungsanzeige setzen
            model.SetState(CalculatorState.SOLUTION);
        }
        // Ans: in beide Strings Dezimalwert aus Antwortspeicher einfuegen
        else if (input.equals("Ans")) {
            model.ExtendExpression(model.GetAnswer());
            model.ExtendHTML(model.GetAnswer());
        }
        // Einfachkorrektur: in beiden Strings das zuletzt eingefuegte Element entfernen
        else if (input.equals("DEL")) {
            model.ShortenExpression();
            model.ShortenHTML();
        }
        // Komplettloeschung: beide Strings komplett leeren und Cursor zuruecksetzen
        else if (input.equals("AC")) {
            model.ClearExpression();
            model.ClearHTML();
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
     * Methode zur Setzung der Referenzen auf Model und View in Main.
     * Auch Ausfuehrung von Code, welcher im Konstruktur waere, jedoch die Links zu den anderen Klassen benoetigt.
     * @param m Model-Instanz
     * @param v View-Instanz
     */
    public void UpdateLinks(Model m, View v) {
        model = m;
        view = v;
        // Fortgefuehrter Konstruktor
        model.SetState(CalculatorState.CALCULATION);
        view.UpdateView();
    }
}


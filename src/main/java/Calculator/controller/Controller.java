package Calculator.controller;

import Calculator.model.CalculatorState;
import Calculator.model.Model;
import Calculator.view.main.View;
import Calculator.view.viewVolume.viewCone;
import Calculator.view.viewVolume.viewCuboid;
//import Calculator.view.general.ViewNumeralSystem;

import java.util.Arrays;
import Calculator.view.viewVolume.viewCylinder;

/**
 * Controller-Klasse mit Programmierlogik.
 */
public class Controller {
    private Model model;
    private View view;
    private Parser parser;
    private viewCone viewCone;
    private viewCuboid viewCuboid;

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
            view.UpdateCalculator();
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
        view.UpdateCalculator();
    }

    public void VolumeCalculate(int nummer) {
        switch (nummer) {
            case 1:
                view.Calculate(1);
                break;
            case 2:
                viewCuboid.Calculate();
            case 3:
            case 4:
        }
    }

    //Rechnet vom Dezimal-Zahlensystem in die beiden anderen um
    public String[] DectoHex_Bin (String decimalString)
    {
        int decimalNumber = Integer.parseInt(decimalString);
        double decimalNumberRounded = ((double) Math.round(decimalNumber * 1));
        int decInt = (int) decimalNumberRounded;
        //Erstellung eines Feldes: Zwei Werte werden übergegeben, einmal Hexa (1.) und einmal Binaer (2.)
        String[] ArrayHex_Bin = new String[2];
        if (decimalNumber < 0) {
            decInt = Math.abs(decInt);
            ArrayHex_Bin[0] = "-" + Integer.toHexString(decInt);
            ArrayHex_Bin[1] = "-" + Integer.toBinaryString(decInt);
            System.out.println(ArrayHex_Bin);
            return ArrayHex_Bin;
        } else {
            //Umrechnung vom Dezimalsystem in die anderen beiden Zahlensysteme
            ArrayHex_Bin[0] = Integer.toHexString(decInt);
            ArrayHex_Bin[1] = Integer.toBinaryString(decInt);
            return ArrayHex_Bin;
        }
    }
    //Rechnet vom Hexa-Zahlensystem in die beiden anderen um
    public String[] HextoDec_Bin (String HexNumber)
    {
        // Feld: s.oben
        String[] ArrayDec_Bin = new String[2];
        //int für if-Bedingung (nicht in Hexa umgerechnet)
        if(HexNumber.indexOf("-") == 0) {
            HexNumber = HexNumber.replace("-","");
            int decIntNeg = Integer.parseInt(HexNumber, 16);
            ArrayDec_Bin[0] = "-" + Integer.toString(decIntNeg);
            ArrayDec_Bin[1] = "-" + Integer.toBinaryString(decIntNeg);
            return ArrayDec_Bin;
        }
        else {
            int decInt = Integer.parseInt(HexNumber, 16);
            ArrayDec_Bin[0] =Integer.toString(decInt);
            ArrayDec_Bin[1] =Integer.toBinaryString(decInt);
            return ArrayDec_Bin;
        }
    }
    //Rechnet vom Binaer-Zahlensystem in die beiden anderen um
    public String[] BintoDec_Hex(String BinString)
    {
        int BinNumberInt = Integer.parseInt(BinString);
        String[] ArrayDec_Hex = new String[2];
        if(BinString.indexOf("-") == 0)
        {
            int decIntNeg = Integer.parseInt(BinString, 2);
            ArrayDec_Hex[0] ="-"+ Integer.toString(decIntNeg);
            ArrayDec_Hex[1] = "-" +Integer.toHexString(decIntNeg);
            return ArrayDec_Hex;
        }
        else {
            int decInt = Integer.parseInt(BinString, 2);
            ArrayDec_Hex[0] = Integer.toString(decInt);
            ArrayDec_Hex[1] = Integer.toHexString(decInt);
            return ArrayDec_Hex;
        }
    }
//NumeralSystemConverter dann noch herauszulöschen


}


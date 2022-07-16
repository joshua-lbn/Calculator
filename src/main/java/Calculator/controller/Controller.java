package Calculator.controller;

import Calculator.model.CalculatorState;
import Calculator.model.Model;
import Calculator.view.main.View;
import Calculator.view.viewVolume.viewCone;
import Calculator.view.viewVolume.viewCuboid;
//import Calculator.view.general.ViewNumeralSystem;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

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
    private String[] c;
    private String[] c01;
    double in1;
    double in2;
    StringJoiner joiner;
    String currencies;

    /**
     * Konstruktor: Parser initialisieren.
     */
    public Controller() {
        parser = new Parser();
        joiner = new StringJoiner("");
    }

    /**
     * Methode zur Erweiterung der Model-Strings anhand der Eingabe, die der View weitergibt.
     * Beginnt nach einer Berechnung eine neue Eingabe.
     *
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
            if (input.equals("=")) {
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
        if (input.matches("[0123456789()]")) {
            model.ExtendExpression(input);
            model.ExtendHTML(input);
        }
        // Grundoperatoren: in Expression einfach einfuegen, in HTML mit Sonderzeichen
        else if (input.equals("\u002B")) {
            model.ExtendExpression("+");
            model.ExtendHTML("&#43;");
        } else if (input.equals("\u2212")) {
            model.ExtendExpression("-");
            model.ExtendHTML("&#8722;");
        } else if (input.equals("\u00D7")) {
            model.ExtendExpression("*");
            model.ExtendHTML("&#215;");
        } else if (input.equals("\u00F7")) {
            model.ExtendExpression("/");
            model.ExtendHTML("&#247;");
        }
        // Komma: Expression mit Punkt und HTML mit Komma
        else if (input.equals("\u002C")) {
            model.ExtendExpression(".");
            model.ExtendHTML(",");
        }
        // Exponent: an Model weitergeben, da dort spezifische Methode
        else if (input.equals("\u207F")) {
            model.AddExponent();
        }
        // Wurzel: an Model weitergeben, da dort spezifische Methode
        else if (input.equals("\u221A")) {
            model.AddSquareRoot();
        }
        // Trigonometrie: modifiziert in Expression und HTML
        if (input.equals("sin") || input.equals("cos") || input.equals("tan")) {
            model.ExtendExpression(input + "(");
            model.ExtendHTML(input + "(");
        }
        // Logarithmus: Expression einfach einfuegen, in HTML Sonderkommando
        else if (input.equals("\u33D2")) {
            model.ExtendExpression(input + "(");
            model.ExtendHTML("log<sub>10</sub>(");
        }
        // Konstanten: in Expression als Text einfuegen (in Parser behandelt), in HTML als Symbol
        else if (input.equals("\u03C0")) {
            model.ExtendExpression("pi");
            model.ExtendHTML("&pi;");
        } else if (input.equals("\u212F")) {
            model.ExtendExpression("e");
            model.ExtendHTML("&#8495;");
        } else if (input.equals("\u03D5")) {
            model.ExtendExpression("[phi]");
            model.ExtendHTML("&#x3D5;");
        }
        // Ist-Gleich: Berechnung anstossen
        else if (input.equals("\u003D")) {
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
        else if (input.equals("\u2190")) {
            model.CursorLeft();
        } else if (input.equals("\u2192")) {
            model.CursorRight();
        }
    }

    /**
     * Methode zur Setzung der Referenzen auf Model und View in Main.
     * Auch Ausfuehrung von Code, welcher im Konstruktur waere, jedoch die Links zu den anderen Klassen benoetigt.
     *
     * @param m Model-Instanz
     * @param v View-Instanz
     */
    public void UpdateLinks(Model m, View v) {
        model = m;
        view = v;
        // Fortgefuehrter Konstruktor
        model.SetState(CalculatorState.CALCULATION);
        view.UpdateCalculator();
        currencies = view.GetCurrenciesAsString();
    }

    /**
     * Methode zur Berechnung des Volumens eines Kegels.
     */
    public void VolumeCalculateCone() {
        String radius = view.GetConeRadius();
        String height = view.GetConeHeight();
        double volumeRadius = -1;
        double volumeHeight = -1;
        try {
            volumeRadius = Double.parseDouble(radius.replace(',', '.'));
            volumeHeight = Double.parseDouble(height.replace(',', '.'));
        } catch (NumberFormatException e) {
            view.VolumeOutput(1, "Ung\u00FCltige Eingabe");
        }
        if (volumeRadius <= 0 || volumeHeight <= 0) {
            view.VolumeOutput(1, "Ung\u00FCltige Eingabe");
        } else {
            double volumeResult = (1.0 / 3.0) * Math.PI * volumeRadius * volumeRadius * volumeHeight;
            double volume = (double) (Math.round(volumeResult * 100)) / 100;
            String volumeString = (Double.toString(volume)).replace('.', ',');
            view.VolumeOutput(1, volumeString);
        }
    }

    /**
     * Methode zur Berechnung des Volumens eines Quaders.
     */
    public void VolumeCalculateCuboid() {
        String length = view.GetCuboidLength();
        String width = view.GetCuboidWidth();
        String height = view.GetCuboidHeight();
        double volumeLength = -1;
        double volumeWidth = -1;
        double volumeHeight = -1;
        try {
            volumeLength = Double.parseDouble(length.replace(',', '.'));
            volumeWidth = Double.parseDouble(width.replace(',', '.'));
            volumeHeight = Double.parseDouble(height.replace(',', '.'));
        } catch (NumberFormatException e) {
            view.VolumeOutput(2, "Ung\u00FCltige Eingabe");
        }
        if (volumeLength <= 0 || volumeWidth <= 0 || volumeHeight <= 0) {
            view.VolumeOutput(2, "Ung\u00FCltige Eingabe");
        } else {
            double volumeResult = volumeLength * volumeWidth * volumeHeight;
            double volume = (double) (Math.round(volumeResult * 100)) / 100;
            String volumeString = (Double.toString(volume)).replace('.', ',');
            view.VolumeOutput(2, volumeString);
        }
    }

    /**
     * Methode zur Berechnung des Volumens eines Zylinders.
     */
    public void VolumeCalculateCylinder() {
        String radius = view.GetCylinderRadius();
        String height = view.GetCylinderHeight();
        double volumeRadius = -1;
        double volumeHeight = -1;
        try {
            volumeRadius = Double.parseDouble(radius.replace(',', '.'));
            volumeHeight = Double.parseDouble(height.replace(',', '.'));
        } catch (NumberFormatException e) {
            view.VolumeOutput(3, "Ung\u00FCltige Eingabe");
        }
        if (volumeRadius <= 0 || volumeHeight <= 0) {
            view.VolumeOutput(3, "Ung\u00FCltige Eingabe");
        } else {
            double volumeResult = Math.PI * volumeRadius * volumeRadius * volumeHeight;
            System.out.println("" + volumeResult);
            double volume = (double) (Math.round(volumeResult * 100)) / 100;
            String volumeString = (Double.toString(volume)).replace('.', ',');
            view.VolumeOutput(3, volumeString);
        }

    }

    /**
     * Methode zur Berechnung des Volumens einer Kugel.
     */
    public void VolumeCalculateSphere() {
        String radius = view.GetSphereRadius();
        double volumeRadius = -1;
        try {
            volumeRadius = Double.parseDouble(radius.replace(',', '.'));
        } catch (NumberFormatException e) {
            view.VolumeOutput(4, "Ung\u00FCltige Eingabe");
        }
        if (volumeRadius <= 0) {
            view.VolumeOutput(4, "Ung\u00FCltige Eingabe");
        } else {
            double volumeResult = (4.0 / 3.0) * Math.PI * volumeRadius * volumeRadius * volumeRadius;
            System.out.println("" + volumeResult);
            double volume = (double) (Math.round(volumeResult * 100)) / 100;
            String volumeString = (Double.toString(volume)).replace('.', ',');
            view.VolumeOutput(4, volumeString);
        }
    }


    //Rechnet vom Dezimal-Zahlensystem in die beiden anderen um
    public String[] DectoHex_Bin(String decimalString) {
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
    public String[] HextoDec_Bin(String HexNumber) {
        // Feld: s.oben
        String[] ArrayDec_Bin = new String[2];
        //int für if-Bedingung (nicht in Hexa umgerechnet)
        if (HexNumber.indexOf("-") == 0) {
            HexNumber = HexNumber.replace("-", "");
            int decIntNeg = Integer.parseInt(HexNumber, 16);
            ArrayDec_Bin[0] = "-" + Integer.toString(decIntNeg);
            ArrayDec_Bin[1] = "-" + Integer.toBinaryString(decIntNeg);
            return ArrayDec_Bin;
        } else {
            int decInt = Integer.parseInt(HexNumber, 16);
            ArrayDec_Bin[0] = Integer.toString(decInt);
            ArrayDec_Bin[1] = Integer.toBinaryString(decInt);
            return ArrayDec_Bin;
        }
    }

    //Rechnet vom Binaer-Zahlensystem in die beiden anderen um
    public String[] BintoDec_Hex(String BinString) {
        int BinNumberInt = Integer.parseInt(BinString);
        String[] ArrayDec_Hex = new String[2];
        if (BinString.indexOf("-") == 0) {
            int decIntNeg = Integer.parseInt(BinString, 2);
            ArrayDec_Hex[0] = "-" + Integer.toString(decIntNeg);
            ArrayDec_Hex[1] = "-" + Integer.toHexString(decIntNeg);
            return ArrayDec_Hex;
        } else {
            int decInt = Integer.parseInt(BinString, 2);
            ArrayDec_Hex[0] = Integer.toString(decInt);
            ArrayDec_Hex[1] = Integer.toHexString(decInt);
            return ArrayDec_Hex;
        }
    }
    //NumeralSystemConverter dann noch herauszulöschen

    public double ConvertCurrency(double input, String inputCurrency, String outputCurrency) {
        if (input < 0) {
            return 0.0;
        } else {
            c = new String[4];
            if (((String) Objects.requireNonNull(inputCurrency)).equals("USD")) {
                in1 = 1.0;
            } else {
                for (int i = 0; i < 4; i++) {
                    char s;
                    s = currencies.charAt(currencies.indexOf((String) Objects.requireNonNull(inputCurrency)) + i + 5);
                    c[i] = String.valueOf(s);
                }
                for (int i = 0; i < c.length; i++) {
                    joiner.add(c[i]);
                }
                String str = joiner.toString();
                in1 = Double.parseDouble(str);
            }
            c01 = new String[4];
            StringJoiner joiner01 = new StringJoiner("");
            if (((String) Objects.requireNonNull(outputCurrency)).equals("USD")) {
                in2 = 1.0;
            } else {
                for (int i = 0; i < 4; i++) {
                    char s;
                    s = currencies.charAt(currencies.indexOf((String) Objects.requireNonNull(outputCurrency)) + i + 5);
                    c01[i] = String.valueOf(s);
                }
                for (int i = 0; i < c01.length; i++) {
                    joiner01.add(c01[i]);
                }
                String str01 = joiner01.toString();
                in2 = Double.parseDouble(str01);
            }
            return input * (in2 / in1);
        }
    }
}
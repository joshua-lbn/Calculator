package Calculator.controller;

// Java-Imports
import java.util.Objects;
import java.util.StringJoiner;
// Import weiterer Projektklassen
import Calculator.model.Model;
import Calculator.model.CalculatorState;
import Calculator.view.main.View;
import Calculator.view.volume.ViewCone;
import Calculator.view.volume.ViewCuboid;
// Import des Rechen-Packages
import org.mariuszgromada.math.mxparser.Expression;

/**
 * Controller-Klasse mit Programmierlogik.
 */
public class Controller {
    // Referenz auf Model und View
    private Model model;
    private View view;
    // Expression des Packages, um mathematischen Ausdruck auszuwerten
    Expression e;
    // Objekte, um Waehrungen umzurechnen
    private String[] c;
    private String[] c01;
    double in1;
    double in2;
    StringJoiner joiner;
    String currencies;

    /**
     * Konstruktor: Expression und Joiner initialisieren.
     */
    public Controller() {
        e = new Expression();
        joiner = new StringJoiner("");
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
        // Zahleneingabe und Klammern: in beide einfach einfuegen
        if (input.matches("[0123456789()]")) {
            model.ExtendExpression(input);
            model.ExtendHTML(input);
        }
        // Komma: Expression mit Punkt und HTML mit Komma
        else if (input.equals("\u002C")) {
            model.ExtendExpression(".");
            model.ExtendHTML(",");
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
        // Logarithmus: modifiziert in Expression, in HTML Sonderkommando
        else if (input.equals("\u33D2")) {
            model.ExtendExpression(input + "(");
            model.ExtendHTML("log<sub>10</sub>(");
        }
        // Konstanten: in Expression als Text einfuegen (durch Package behandelt), in HTML als Symbol
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
        // Ans: in beide Strings Dezimalwert aus Antwortspeicher einfuegen
        else if (input.equals("Ans")) {
            model.ExtendExpression(model.GetAnswer());
            model.ExtendHTML(model.GetAnswer());
        }
        // Einfachkorrektur: in beiden Strings das zuletzt eingefuegte Element durch Methoden entfernen
        else if (input.equals("DEL")) {
            model.ShortenExpression();
            model.ShortenHTML();
        }
        // Komplettloeschung: spezifische Methoden
        else if (input.equals("AC")) {
            model.ClearExpression();
            model.ClearHTML();
        }
        // Cursortasten: spezifische Methoden
        else if (input.equals("\u2190")) {
            model.CursorLeft();
        } else if (input.equals("\u2192")) {
            model.CursorRight();
        }
        // Ist-Gleich: Berechnung anstossen
        else if (input.equals("\u003D")) {
            // Ergebnis berechnen und im Model setzen
            model.SetAnswer(CalculateCalculator());
            // Anzeige aktualisieren
            view.UpdateCalculator();
            // Zustand auf Loesungsanzeige setzen
            model.SetState(CalculatorState.SOLUTION);
        }
    }

    /**
     * Methode zur Berechnung des Ausdruckes im regulaeren Taschenrechner.
     * @return Das Ergebnis der Berechnung (als Double)
     */
    public double CalculateCalculator() {
        // String mit Ausdruck an Instanz der Expression übergeben
        e.setExpressionString(model.GetExpression());
        // Ausrechnen und zurückgeben
        return e.calculate();
    }

    /**
     * Methode zur Berechnung des Volumens eines Kegels.
     */
    public void VolumeCalculateCone() {
        // Eingabe-Strings
        String radius = view.GetConeRadius();
        String height = view.GetConeHeight();
        // Doubles mit ungueltigen Werten initialisieren
        double volumeRadius = -1;
        double volumeHeight = -1;
        try {
            // Versuch, die Strings zu Doubles zu machen (dabei Komma durch Punkt ersetzt)
            volumeRadius = Double.parseDouble(radius.replace(',', '.'));
            volumeHeight = Double.parseDouble(height.replace(',', '.'));
        } catch (NumberFormatException e) {
            // Fehlerbehandlung
            view.VolumeOutput(1, "Ung\u00FCltige Eingabe");
        }
        if (volumeRadius <= 0 || volumeHeight <= 0) {
            // Fehlerbehandlung, falls Zahlen zu klein
            view.VolumeOutput(1, "Ung\u00FCltige Eingabe");
        } else {
            // Ergebnis berechnen
            double volumeResult = (1.0 / 3.0) * Math.PI * volumeRadius * volumeRadius * volumeHeight;
            double volume = (double) (Math.round(volumeResult * 100)) / 100;
            // Ergebnis zu String mit Komma statt Punkt
            String volumeString = (Double.toString(volume)).replace('.', ',');
            // Ergebnis setzen
            view.VolumeOutput(1, volumeString);
        }
    }

    /**
     * Methode zur Berechnung des Volumens eines Quaders.
     */
    public void VolumeCalculateCuboid() {
        // Eingabe-Strings
        String length = view.GetCuboidLength();
        String width = view.GetCuboidWidth();
        String height = view.GetCuboidHeight();
        // Doubles mit ungueltigen Werten initialisieren
        double volumeLength = -1;
        double volumeWidth = -1;
        double volumeHeight = -1;
        try {
            // Versuch, die Strings zu Doubles zu machen (dabei Komma durch Punkt ersetzt)
            volumeLength = Double.parseDouble(length.replace(',', '.'));
            volumeWidth = Double.parseDouble(width.replace(',', '.'));
            volumeHeight = Double.parseDouble(height.replace(',', '.'));
        } catch (NumberFormatException e) {
            // Fehlerbehandlung
            view.VolumeOutput(2, "Ung\u00FCltige Eingabe");
        }
        if (volumeLength <= 0 || volumeWidth <= 0 || volumeHeight <= 0) {
            // Fehlerbehandlung, falls Zahlen zu klein
            view.VolumeOutput(2, "Ung\u00FCltige Eingabe");
        } else {
            // Ergebnis berechnen
            double volumeResult = volumeLength * volumeWidth * volumeHeight;
            double volume = (double) (Math.round(volumeResult * 100)) / 100;
            // Ergebnis zu String mit Komma statt Punkt
            String volumeString = (Double.toString(volume)).replace('.', ',');
            // Ergebnis setzen
            view.VolumeOutput(2, volumeString);
        }
    }

    /**
     * Methode zur Berechnung des Volumens eines Zylinders.
     */
    public void VolumeCalculateCylinder() {
        // Eingabe-Strings
        String radius = view.GetCylinderRadius();
        String height = view.GetCylinderHeight();
        // Doubles mit ungueltigen Werten initialisieren
        double volumeRadius = -1;
        double volumeHeight = -1;
        try {
            // Versuch, die Strings zu Doubles zu machen (dabei Komma durch Punkt ersetzt)
            volumeRadius = Double.parseDouble(radius.replace(',', '.'));
            volumeHeight = Double.parseDouble(height.replace(',', '.'));
        } catch (NumberFormatException e) {
            // Fehlerbehandlung
            view.VolumeOutput(3, "Ung\u00FCltige Eingabe");
        }
        if (volumeRadius <= 0 || volumeHeight <= 0) {
            // Fehlerbehandlung, falls Zahlen zu klein
            view.VolumeOutput(3, "Ung\u00FCltige Eingabe");
        } else {
            // Ergebnis berechnen
            double volumeResult = Math.PI * volumeRadius * volumeRadius * volumeHeight;
            System.out.println("" + volumeResult);
            double volume = (double) (Math.round(volumeResult * 100)) / 100;
            // Ergebnis zu String mit Komma statt Punkt
            String volumeString = (Double.toString(volume)).replace('.', ',');
            // Ergebnis setzen
            view.VolumeOutput(3, volumeString);
        }
    }

    /**
     * Methode zur Berechnung des Volumens einer Kugel.
     */
    public void VolumeCalculateSphere() {
        // Eingabe-String
        String radius = view.GetSphereRadius();
        // Double mit ungueltigem Wert initialisieren
        double volumeRadius = -1;
        try {
            // Versuch, den String zu einem Double zu machen (dabei Komma durch Punkt ersetzt)
            volumeRadius = Double.parseDouble(radius.replace(',', '.'));
        } catch (NumberFormatException e) {
            // Fehlerbehandlung
            view.VolumeOutput(4, "Ung\u00FCltige Eingabe");
        }
        if (volumeRadius <= 0) {
            // Fehlerbehandlung, falls Zahl zu klein
            view.VolumeOutput(4, "Ung\u00FCltige Eingabe");
        } else {
            // Ergebnis berechnen
            double volumeResult = (4.0 / 3.0) * Math.PI * volumeRadius * volumeRadius * volumeRadius;
            System.out.println("" + volumeResult);
            double volume = (double) (Math.round(volumeResult * 100)) / 100;
            // Ergebnis zu String mit Komma statt Punkt
            String volumeString = (Double.toString(volume)).replace('.', ',');
            // Ergebnis setzen
            view.VolumeOutput(4, volumeString);
        }
    }

    /**
     * Methode, um Eingabe im Dezimalsystem in die anderen beiden Systeme umzuwandeln.
     * @param decimalString Eingabe im Dezimalsystem
     * @return Array mit hexadezimaler und binaerer Ausgabe
     */
    public String[] DectoHex_Bin(String decimalString) {
        // Erstellung eines Feldes: Hexa- und Binaerwert werden gespeichert
        String[] ArrayHex_Bin = new String[2];
        try {
            double decimalDouble = Double.parseDouble(decimalString);
            //Runden auf eine ganzzahlige Zahl
            double decimalNumberRounded = ((double) Math.round(decimalDouble * 1));
            int decInt = (int) decimalNumberRounded;
            if (decimalDouble < 0) {
                decInt = Math.abs(decInt);
                ArrayHex_Bin[0] = "-" + Integer.toHexString(decInt);
                ArrayHex_Bin[1] = "-" + Integer.toBinaryString(decInt);
                return ArrayHex_Bin;
            } else {
                ArrayHex_Bin[0] = Integer.toHexString(decInt);
                ArrayHex_Bin[1] = Integer.toBinaryString(decInt);
                return ArrayHex_Bin;
            }

        } catch (NumberFormatException e) {
            ArrayHex_Bin[0] = "Fehler";
            return ArrayHex_Bin;
        }
    }

    /**
     * Methode, um Eingabe im hexadezimalen System in die anderen beiden Systeme umzuwandeln.
     * @param HexNumber Eingabe im Hexadezimalsystem
     * @return Array mit dezimaler und binaerer Ausgabe
     */
    public String[] HextoDec_Bin(String HexNumber) {
        // Erstellung eines Feldes: Binaer- und Dezimalwert werden gespeichert
        String[] ArrayDec_Bin = new String[2];
        // int für if-Bedingung (nicht in Hexa umgerechnet)
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

    /**
     * Methode, um Eingabe im binaeren System in die anderen beiden Systeme umzuwandeln.
     * @param BinString Eingabe im Binaersystem
     * @return Array mit dezimaler und hexadezimaler Ausgabe
     */
    public String[] BintoDec_Hex(String BinString) {
        // Erstellung eines Feldes: Dezimal- und Hexadezimalwert werden gespeichert
        String[] ArrayDec_Hex = new String[2];
        try {
            double BinNumberInt = Double.parseDouble(BinString);
            if (BinString.indexOf("-") == 0) {
                long decIntNeg = Integer.parseInt(BinString, 2);
                ArrayDec_Hex[0] = "-" + Long.toString(decIntNeg);
                ArrayDec_Hex[1] = "-" + Long.toHexString(decIntNeg);
                return ArrayDec_Hex;
            } else {
                long decInt = Long.parseLong(BinString, 2);
                ArrayDec_Hex[0] = Long.toString(decInt);
                ArrayDec_Hex[1] = Long.toHexString(decInt);
                return ArrayDec_Hex;
            }
        } catch (NumberFormatException e){
            ArrayDec_Hex[0] = "Fehler";
            return ArrayDec_Hex;
        }
    }

    /**
     * Methode, um von einer in eine andere Waehrung umzuwandeln.
     * @param input Umzuwandelnder Zahlenwert als Double
     * @param inputCurrency Waehrung der Eingabe als String
     * @param outputCurrency Waehrung der Ausgabe als String
     * @return Umgewandelter Zahlenwert als Double
     */
    public double ConvertCurrency(double input, String inputCurrency, String outputCurrency) {
        // Falls kleiner 0: auf 0 setzen
        if (input < 0) {
            return 0.0;
        } else {
            // Array erstellen
            c = new String[4];
            // Falls Eingabewaehrung US-Dollar: Faktor 1
            if (((String) Objects.requireNonNull(inputCurrency)).equals("USD")) {
                in1 = 1.0;
            // Bei anderer Eingabewaehrung: suchen und Faktor setzen
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
            // Neues Array und neuen StringJoiner erstellen
            c01 = new String[4];
            StringJoiner joiner01 = new StringJoiner("");
            // Falls Eingabewaehrung US-Dollar: Faktor 1
            if (((String) Objects.requireNonNull(outputCurrency)).equals("USD")) {
                in2 = 1.0;
            // Bei anderer Eingabewaehrung: suchen und Faktor setzen
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
            // Eingabe mit Faktoren verrechnen und zurueckgeben
            return input * (in2 / in1);
        }
    }

    /**
     * Methode zur Setzung der Referenzen auf Model und View in Main sowie Ausfuehrung von Code,
     * welcher im Konstruktur waere, jedoch die Links zu den anderen Klassen benoetigt.
     * @param m Model-Instanz
     * @param v View-Instanz
     */
    public void UpdateLinks(Model m, View v) {
        // Referenzen setzen
        model = m;
        view = v;
        /* Fortgefuehrter Konstruktor
         * Zustand des Rechners auf "rechnend" setzen
         * Oberflaeche des Rechners aktualisieren
         * Waehrungsdaten als String erhalten
         */
        model.SetState(CalculatorState.CALCULATION);
        view.UpdateCalculator();
        currencies = view.GetCurrenciesAsString();
    }
}
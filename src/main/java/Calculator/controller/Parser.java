package Calculator.controller;

import org.mariuszgromada.math.mxparser.*;

/**
 * Klasse zur Auswertung des mathematischen Ausdruckes im Model anhand des mXparer-Maven-Moduls (MathParser.org-mXparser).
 * Programmtechnisch an den Controller gegliedert.
 */
public class Parser {
    // Ausdruck deklarieren
    Expression e;

    /**
     * Konstruktor, der die Expression initialisiert.
     */
    public Parser()
    {
        e = new Expression();
    }

    /**
     * Methode zur Berechnung eines Ausdruckes.
     * @param expression Der den Ausdruck enthaltende String
     * @return Das Ergebnis der Berechnung (als Double)
     */
    public Double Calculate(String expression)
    {
        // String an Expression übergeben
        e.setExpressionString(expression);
        // Ausrechnen und zurückgeben
        return e.calculate();
    }
}
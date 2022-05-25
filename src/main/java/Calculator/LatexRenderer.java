package Calculator;

import java.awt.Image;
import java.awt.Color;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;

/**
 * Klasse zur Erstellung des Latex-Bildes mithilfe des JLatexMath-Maven-Moduls
 * Programmtechnisch an den Controller gegliedert
 */
public class LatexRenderer {
    /**
     * Leerer Constructor
     */
    public LatexRenderer()
    {
        // nothing to initialize
    }

    /**
     * Methode zur Erstellung eines neuen Latex-Bildes anhand eines Strings
     * @param latexString Eingabe-String
     * @return Generiertes Bild
     */
    public Image RenderLatex(String latexString) {
        // String in TeX-Formel umwandeln
        TeXFormula formula = new TeXFormula(latexString);
        // Aus der Formel ein Bild erstellen: Farben schwarz auf weiss, Schriftgroesse 500
        Image bufferedImage = formula.createBufferedImage(TeXConstants.STYLE_DISPLAY, 500, Color.black, Color.white);
        return bufferedImage;
    }
}
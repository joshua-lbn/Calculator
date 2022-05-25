package Calculator;

import java.awt.Image;
import java.awt.Color;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;

public class LatexRenderer {
    public LatexRenderer()
    {
        // nothing to initialize
    }
    public Image RenderLatex(String latexString) {
        TeXFormula formula = new TeXFormula(latexString);
        Image bufferedImage = formula.createBufferedImage(TeXConstants.STYLE_DISPLAY, 500, Color.black, Color.white);
        return bufferedImage;
    }
}
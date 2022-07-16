package Calculator.view.currency;

import java.util.Objects;
import java.util.StringJoiner;

public class ProcessCurrencyInput implements java.awt.event.ActionListener {
    private ViewCurrency viewCurrency;
    double norm;
    double output;
    public ProcessCurrencyInput(ViewCurrency vc) {
        viewCurrency = vc;
    }
    public void actionPerformed(java.awt.event.ActionEvent e) {
        // Noch prüfen Buchstaben + leere Eingabe
        // Parse Double, da nicht möglich String direkt in Zahl umwandeln
        norm = Double.parseDouble(viewCurrency.GetTextInput().getText());
        output = viewCurrency.ConvertCurrency(norm);
        viewCurrency.SetResult(output);
    }
}
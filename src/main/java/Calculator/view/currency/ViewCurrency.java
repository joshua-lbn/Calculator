package Calculator.view.currency;

// Java-Imports
import javax.swing.*;
import java.awt.*;
import java.util.Objects;
// Imports weiterer Programmklassen
import Calculator.view.main.View;

/**
 * ViewCurrency-Klasse mit Waehrungs-Unterfenster.
 * Verwendet, um Abstraktion zwischen View-Oberklasse und den Unter-Darstellungen zu schaffen.
 */
public class ViewCurrency extends JRootPane {
    // Referenzen auf weitere Instanzen
    private View view;
    // Graphische Oberflaeche
    private JLabel label1;
    private JComboBox eingabeCB;
    private JComboBox ausgabeCB;
    private JTextField textInput;
    // Eingabeverarbeitung des textInput-JTextField und der eingabeCB- und ausgabeCB-JComboBoxes
    private ProcessCurrencyInput processCurrencyInput;

    /**
     * Konstruktor: Referenz auf View-Instanz setzen, Waehrungsarray initialisieren und befuellen, Oberflaeche
     * erstellen und Eingabeverarbeitung hinzufuegen.
     */
    public ViewCurrency (View v)  {
        // Referenz setzen
        view = v;
        // Array mit Waehrungen erstellen
        String[] currencyNames = new String[]{"Argentinischer Peso", "Australischer Dollar", "Lew",
                "Brasilianischer Real", "Kanadischer Dollar", "Schweizer Franken", "Chinesischer Renminbi Yuan",
                "Tschechische Krone", "D\u00E4nische Krone", "Algerischer Dinar", "Pfund Sterling", "Hongkong-Dollar",
                "Kroatische Kuna", "Forint", "Indonesische Rupiah", "Neuer Schekel", "Indische Rupie",
                "Isl\u00E4ndische Krone", "Yen", "S\u00FCdkoreanischer Won", "Marokkanischer Dirham",
                "Mexikanischer Peso", "Malaysischer Ringgit", "Norwegische Krone", "Neuseeland-Dollar",
                "Philippinischer Peso", "Zloty", "Rum\u00E4nischer Leu", "Russischer Rubel", "Schwedische Krone",
                "Singapur-Dollar", "Baht", "Neue Lira", "Taiwan-Dollar", "US-Dollar", "S\u00FCdafrikanischer Rand"};
        // Eingabeverarbeitungsinstanz kreieren
        processCurrencyInput = new ProcessCurrencyInput(this);
        // Oberflaeche erstellen
        eingabeCB = new JComboBox(currencyNames);
        eingabeCB.setEditable(false);
        eingabeCB.addActionListener(processCurrencyInput);
        getContentPane().add(eingabeCB, BorderLayout.WEST);
        ausgabeCB = new JComboBox(currencyNames);
        ausgabeCB.setEditable(false);
        ausgabeCB.addActionListener(processCurrencyInput);
        getContentPane().add(ausgabeCB, BorderLayout.EAST);
        textInput = new JTextField();
        textInput.getDocument().addDocumentListener(processCurrencyInput);
        getContentPane().add(textInput, BorderLayout.NORTH);
        label1 = new JLabel("Umgerechnete W\u00E4hrung");
        getContentPane().add(label1, BorderLayout.CENTER);
    }

    /**
     * Methode, um die Waehrungsumrechnung anzustossen.
     */
    protected void ConvertCurrency() {
        Double input;
        try {
            // Input in Double umzuwandeln versuchen
            input = Double.parseDouble(textInput.getText().replace(",", "."));
        } catch (NumberFormatException e) {
            // Bei Umwandlungsfehler: Fehler ausgeben
            label1.setText("Fehlerhafte Eingabe");
            return;
        }
        if(input < 0) {
            // Bei zu kleinem Wert (kleiner 0): Fehler ausgeben
            label1.setText("Fehlerhafte Eingabe");
            return;
        }
        else {
            // Umrechnung im Controller anstossen
            Double output = view.ConvertCurrency(input, (String) eingabeCB.getSelectedItem(),
                    (String) ausgabeCB.getSelectedItem());
            // Falls null: Fehler ausgeben
            if(Objects.isNull(output)) {
                label1.setText("Fehler");
            }
            else {
                // Runden auf zwei Nachkommastellen
                output = Math.round(output * 100.0) / 100.0;
                // Formatiert anzeigen
                label1.setText(Double.toString(output).replace(".", ","));
            }
        }
    }

    /**
     * Methode, um den hellen Modus zu setzen.
     */
    public void SetLightmode() {
        label1.setForeground(Color.black);
        label1.setBackground(Color.white);
        eingabeCB.setForeground(Color.black);
        eingabeCB.setBackground(Color.white);
        ausgabeCB.setForeground(Color.black);
        ausgabeCB.setBackground(Color.white);
        textInput.setForeground(Color.black);
        textInput.setBackground(Color.white);
        getContentPane().setBackground(Color.white);
    }

    /**
     * Methode, um den dunklen Modus zu setzen.
     */
    public void SetDarkmode() {
        label1.setForeground(Color.white);
        label1.setBackground(Color.black);
        eingabeCB.setForeground(Color.white);
        eingabeCB.setBackground(Color.black);
        ausgabeCB.setForeground(Color.white);
        ausgabeCB.setBackground(Color.black);
        textInput.setForeground(Color.white);
        textInput.setBackground(Color.black);
        getContentPane().setBackground(Color.black);
    }
}

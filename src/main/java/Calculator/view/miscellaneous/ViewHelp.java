package Calculator.view.miscellaneous;

import javax.swing.*;
import java.awt.*;

public class ViewHelp extends JRootPane {
    // Panel mit Inhalt und ScrollPane dazu
    private JPanel panel;
    private JScrollPane jScrollPane;
    // JTextArea mit den Hilfstexten
    private JTextArea text;

    /**
     * Konstruktor: Texte definieren, in JTextArea einfuegen und in das Panel tun.
     */
    public ViewHelp() {
        // Panel definieren
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        // Text hinzufuegen
        text = new JTextArea();
        text.append("Dies ist der Hilfstext f\u00FCr den vorliegenden Taschenrechner.\n");
        text.append("Man navigiert zwischen den Modi, indem man auf einen Reiter in der obersten Leiste dr\u00FCckt, " +
                "wie 'Allgemein', und im daraufhin erscheinenden Men\u00FC das Gew\u00FCnschte ausw\u00E4hlt. " +
                "Dies k\u00F6nnten die 'Einstellungen' sein.\n");
        text.append("Im Taschenrechnermodus gibt man einen Ausdruck \u00FCber die Tastatur oder die Kn\u00F6pfe ein. " +
                "Durch den '='-Knopf oder die 'Enter'-Taste st\u00F6\u00DFt man die Berechnung an. Das Ergebnis " +
                "wird anschlie\u00DFend angezeigt, au\u00DFer die Eingabe erg\u00E4be etwas zu gro\u00DFes " +
                "(Anzeige: 'Unendlich') oder ist mathematisch nicht korrekt (Anzeige: 'Kein g\u00FCltiger Ausdruck')." +
                " Durch erneute Eingabe beginnt eine neue Berechnung.\n");
        text.append("Im Zahlensystemsmodus gibt man in eines der drei Eingabefelder eine Zahl im angegebenen System " +
                "ein. Danach bet\u00E4tigt man den danebenstehenden Knopf. Die Ergebnisse werden in den anderen " +
                "Systemen in den urspr\u00FCnglich leeren Textfeldern erg\u00E4nzt.\n");
        text.append("In einem der Volumenmodi f\u00FCllt man alle Textfelder mit den ben\u00F6tigten Informationen " +
                "und dr\u00FCckt den Knopf. Das berechnete Ergebnis erscheint unten.\n");
        text.append("Im W\u00E4hrungsmodus w\u00E4hlt man im ersten Men\u00FC die Ausgangsw\u00E4hrung und gibt den " +
                "umzurechnenden Betrag in das Textfeld darunter ein. Danach w\u00E4hlt man die Zielw\u00E4hrung im " +
                "zweiten Men\u00FC aus. Dann bet\u00E4tigt man die 'Enter'-Taste. Das Ergebnis erscheint im zweiten " +
                "Textfeld.");
        // Eigenschaften der JTextArea setzen
        text.setEditable(false);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        // Text skalieren, in Panel und dann ContentPane hinzufuegen, dabei JScrollPane (Scrollbars) anfuegen
        text.setSize(10, 1000000);
        panel.add(text);
        jScrollPane = new JScrollPane(panel);
        getContentPane().add(jScrollPane);
    }

    /**
     * Methode, um bei Groessenveraenderungen des Fensters den Text auf Fenstergroesse zu skalieren.
     */
    public void UpdateView() {
        text.setSize(10, 1000000);
    }

    /**
     * Methode, um den hellen Modus zu aktivieren.
     */
    public void SetLightmode() {
        panel.setBackground(Color.white);
        text.setForeground(Color.black);
        text.setBackground(Color.white);
        jScrollPane.getVerticalScrollBar().setBackground(Color.white);
    }

    /**
     * Methode, um den dunklen Modus zu aktivieren.
     */
    public void SetDarkmode() {
        panel.setBackground(Color.black);
        text.setForeground(Color.white);
        text.setBackground(Color.black);
        jScrollPane.getVerticalScrollBar().setBackground(Color.black);
    }
}

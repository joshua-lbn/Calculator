package Calculator.view.viewGeneral;

import javax.swing.*;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class ViewHelp extends JRootPane {
    // Panel mit allen Inhalten und ScrollPane dazu
    private JPanel panel;
    private JScrollPane jScrollPane;
    // JLabels mit den Hilfstexten
    private JTextArea general;
    private JTextArea windows;
    private JTextPane calculator;
    private JTextArea numeral;
    private JTextArea volume;
    private JTextArea currency;

    /**
     * Konstruktor: Texte definieren und in das Panel einfuegen.
     */
    public ViewHelp() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(6,1));
        general = new JTextArea("Dies ist der Hilfstext f\u00FCr das vorliegende Programm.");
        general.setEditable(false);
        general.setLineWrap(true);
        general.setWrapStyleWord(true);
        //panel.add(general);
        windows = new JTextArea("Man navigiert zwischen den Modi, indem man auf einen Reiter in der obersten " +
                "Leiste dr\u00FCckt, wie 'Allgemein', und im daraufhin erscheinenden Men\u00FC das " +
                "Gew\u00FCnschte ausw\u00E4hlt. Dies k\u00F6nnten die 'Einstellungen' sein.");
        //windows.setEditable(false);
        //windows.setLineWrap(true);
        //windows.setWrapStyleWord(true);
        //panel.add(windows);
        calculator = new JTextPane();
        calculator.setText("Im Taschenrechnermodus gibt man einen Ausdruck \u00FCber die Tastatur " +
                "oder die Kn\u00F6pfe ein. Durch die '='-Knopf oder den die 'Enter'-Taste st\u00F6\u00DFt man die " +
                "Berechnung an. Das Ergebnis wir dann angezeigt, au\u00DFer die Eingabe erg\u00E4be etwas zu " +
                "gro\u00DFes (Anzeige: 'Unendlich') oder ist mathemathisch nicht korrekt (Anzeige: 'Kein " +
                "g\u00FCltiger Ausdruck'). Durch erneute Eingabe beginnt eine neue Berechnung.");
        //calculator.setEditable(false);
        //calculator.setLineWrap(true);
        //calculator.setWrapStyleWord(true);
        panel.add(calculator);
        numeral = new JTextArea("Im Zahlensystemsmodus gibt man in eines der drei Eingabefelder eine Zahl im " +
                "angegebenen Format ein. Danach bet\u00E4tigt man den Knopf. Die Ergebnisse werden in den anderen, " +
                "urspr\u00FCnglich leeren Textfeldern angezeigt.");
        numeral.setEditable(false);
        numeral.setLineWrap(true);
        numeral.setWrapStyleWord(true);
        //panel.add(numeral);
        volume = new JTextArea("In einem der Volumenmodi f\u00FCllt man alle Textfelder mit den angegebenen " +
                "Informationen und dr\u00FCckt den Knopf. Das Ergebnis erscheint unten. Es werden bewusst keine " +
                "Einheiten verwendet, damit volle Freiheit bei der Einheitenwahl hat und nicht durche eine " +
                "vorgegebene Auswahl beschr\u00E4nkt ist.");
        volume.setEditable(false);
        volume.setLineWrap(true);
        volume.setWrapStyleWord(true);
        //panel.add(volume);
        currency = new JTextArea("Im W\u00E4hrungsmodus w\u00E4hlt man im ersten Men\u00FC die " +
                "Ausgangsw\u00E4hrung und gibt den Betrag darunter ein. Danach w\u00E4hlt man die Zielw\u00E4hrung" +
                "im zweiten Men\u00FC aus. Dann bet\u00E4tigt man die 'Enter'-Taste. Das Ergebnis erscheint im " +
                "zweiten Textfeld.");
        currency.setEditable(false);
        currency.setLineWrap(true);
        currency.setWrapStyleWord(true);
        //panel.add(currency);
        jScrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        //this.getContentPane().add(jScrollPane);
        getContentPane().add(jScrollPane);
        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                calculator.repaint();
            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });
    }

    /**
     * Methode, um den hellen Modus zu aktivieren.
     */
    public void SetLightmode() {
        panel.setBackground(Color.white);
        general.setForeground(Color.black);
        general.setBackground(Color.white);
        windows.setForeground(Color.black);
        windows.setBackground(Color.white);
        calculator.setForeground(Color.black);
        calculator.setBackground(Color.white);
        numeral.setForeground(Color.black);
        numeral.setBackground(Color.white);
        volume.setForeground(Color.black);
        volume.setBackground(Color.white);
        currency.setForeground(Color.black);
        currency.setBackground(Color.white);
    }

    /**
     * Methode, um den dunklen Modus zu aktivieren.
     */
    public void SetDarkmode() {
        panel.setBackground(Color.black);
        general.setForeground(Color.white);
        general.setBackground(Color.black);
        windows.setForeground(Color.white);
        windows.setBackground(Color.black);
        calculator.setForeground(Color.white);
        calculator.setBackground(Color.black);
        numeral.setForeground(Color.white);
        numeral.setBackground(Color.black);
        volume.setForeground(Color.white);
        volume.setBackground(Color.black);
        currency.setForeground(Color.white);
        currency.setBackground(Color.black);
    }
}

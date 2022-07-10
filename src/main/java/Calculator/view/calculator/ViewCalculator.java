package Calculator.view.calculator;

import Calculator.controller.Controller;
import Calculator.model.Model;
import Calculator.view.main.View;
import Calculator.model.ColorMode;

import javax.swing.*;
import java.awt.*;

/**
 * ViewCalculator-Klasse mit grafischer Benutzeroberflaeche des Taschenrechners.
 * Verwendet, um Abstraktion zwischen View-Oberklasse und den Unter-Darstellungen zu schaffen.
 */
public class ViewCalculator extends JRootPane {
    // Referenzen auf andere Klassen
    private Model model;
    private View view;
    private Controller controller;
    // JPanels fuer das Layout
    private javax.swing.JPanel spacer1 = new javax.swing.JPanel();
    private javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
    private javax.swing.JPanel jPanelRight = new javax.swing.JPanel();
    private javax.swing.JPanel jPanelMiddle = new javax.swing.JPanel();
    private javax.swing.JPanel jPanelLeft = new javax.swing.JPanel();
    // JTextPane mit Ausgabe bzw. Anzeige des Ausdruckes, in JScrollBar gehuellt
    private javax.swing.JTextPane jTextPane;
    private JScrollPane jScrollPane;
    // Arrays mit Knoepfen
    private javax.swing.JButton[] jButtonsNumber;
    private javax.swing.JButton[] jButtonsLeft;
    private javax.swing.JButton[] jButtonsMiddle;
    private javax.swing.JButton[] jButtonsRight;
    // Arrays mit Beschriftungen der Knoepfe
    private String[] textsLeft;
    private String[] textsMiddle;
    private String[] textsRight;
    // Auswerter der Knopfdruecke
    private ProcessButtonInput processButtonInput;

    /**
     * Konstruktor: Initialisierung der Oberflaeche.
     * Hinzufuegen der Eingabeverarbeitung.
     */
    public ViewCalculator(Model m, View v, Controller c) {
        model = m;
        view = v;
        controller = c;
        // Erstellung des Layouts durch Schachtelung der Panels und Hinzufuegen des JTextPane
        spacer1.setLayout(new GridLayout(2, 1));
        jTextPane = new JTextPane();
        jTextPane.setContentType("text/html");
        jTextPane.setEditable(false);
        jTextPane.setFocusable(false);
        jScrollPane = new JScrollPane (jTextPane);
        spacer1.add(jScrollPane);
        jPanel1.setLayout(new GridLayout(1, 3));
        spacer1.add(jPanel1);
        jPanelRight.setLayout(new GridLayout(4, 3));
        jPanel1.add(jPanelRight);
        jPanelMiddle.setLayout(new GridLayout(4, 3));
        jPanel1.add(jPanelMiddle);
        jPanelLeft.setLayout(new GridLayout(4, 2));
        jPanel1.add(jPanelLeft);
        // Definierung der Beschriftungen und Deklarierung der Button-Arrays nach Anzahl der Texte
        jButtonsNumber = new JButton[10];
        textsLeft = new String[]{"\u2190", "\u2192", "\u002B", "\u2212", "\u00D7", "\u00F7", "(", ")"};
        jButtonsLeft = new JButton[textsLeft.length];
        textsMiddle = new String[]{"\u002C", "\u003D"};
        jButtonsMiddle = new JButton[textsMiddle.length];
        textsRight = new String[]{"DEL", "AC", "Ans", "sin", "cos", "tan", "\u207F", "\u33D2", "\u221A",
                "\u03C0", "\u212F", "\u03D5"};
        jButtonsRight = new JButton[textsRight.length];
        // Instanz der Beiklasse "ProcessButtonInput", um auf Knopfdrucke zu reagieren
        processButtonInput = new ProcessButtonInput(this);
        // Generierung der einzelnen Knoepfe: ueber jeder Knopf-Array iterieren und dabei Knoepfe mit Beschriftungen aus
        // Texte-Array erstellen, ProcessButtonInput uebergeben und Knoepfe ins Layout hinzufuegen
        for (int i = 0; i < jButtonsNumber.length; i++) {
            jButtonsNumber[i] = new javax.swing.JButton(Integer.toString((Integer) i));
            jButtonsNumber[i].addActionListener(processButtonInput);
            jPanelMiddle.add(jButtonsNumber[i]);
        }
        for (int i = 0; i < jButtonsLeft.length; i++) {
            jButtonsLeft[i] = new javax.swing.JButton("" + textsLeft[i] + "");
            jButtonsLeft[i].addActionListener(processButtonInput);
            jPanelLeft.add(jButtonsLeft[i]);
        }
        for (int i = 0; i < jButtonsMiddle.length; i++) {
            jButtonsMiddle[i] = new javax.swing.JButton("" + textsMiddle[i] + "");
            jPanelMiddle.add(jButtonsMiddle[i]);
            jButtonsMiddle[i].addActionListener(processButtonInput);
        }
        for (int i = 0; i < jButtonsRight.length; i++) {
            jButtonsRight[i] = new javax.swing.JButton("" + textsRight[i] + "");
            jButtonsRight[i].addActionListener(processButtonInput);
            jPanelRight.add(jButtonsRight[i]);
        }
        // Hinzufuegen des Gesamtlayouts in die ContentPane (das "Fenster")
        this.getContentPane().add(spacer1);
    }

    /**
     * Methode zur Uebergabe des neu hinzugefuegten Zeichens (aus dem ProcessButtonInput) an den Controller.
     * @param s Neues Zeichen
     */
    public void Update(String s) {
        controller.Update(s);
    }

    /**
     * Methode, um den neue HTML-Ausdruck aus dem Model ins Fenster einzufuegen.
     */
    public void UpdateView() {
        // Text (aus Model) in HTML-Darstellung anzeigen
        jTextPane.setText(model.GetHTMLExpression());
        // Cursor-Position berechnen und setzen
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                jScrollPane.getHorizontalScrollBar().setValue(Math.round(model.GetCursorPosition() * jTextPane.
                        getWidth() / model.GetHTMLElementsListSize()));
            }
        });
    }

    /**
     * Methode, um den UpdateFocus()-Aufruf an die View weiterzugeben.
     */
    public void UpdateFocus() {
        view.UpdateFocus();
    }

    /**
     * Methode, um den Farbmodus zu wechseln.
     */
    protected void SwitchMode() {
        if (model.GetColorMode() == ColorMode.DARKMODE) {
            SetLightmode();
        }
        else {
            SetDarkmode();
        }
    }

    /**
     * Methode, um den hellen Modus zu aktivieren.
     */
    public void SetLightmode() {
        // Hauptkomponenten setzen
        spacer1.setBackground(Color.white);
        jPanel1.setBackground(Color.white);
        jPanelRight.setBackground(Color.white);
        jPanelMiddle.setBackground(Color.white);
        jPanelLeft.setBackground(Color.white);
        // HTML-Darstellung setzen
        jTextPane.setBackground(Color.white);
        // Scrollbar setzen
        jScrollPane.getHorizontalScrollBar().setBackground(Color.white);
        // Knoepfe setzen
        for (int i = 0; i < jButtonsNumber.length; i++) {
            jButtonsNumber[i].setBackground(Color.white);
            jButtonsNumber[i].setForeground(Color.black);
        }
        for (int i = 0; i < jButtonsLeft.length; i++) {
            jButtonsLeft[i].setBackground(Color.white);
            jButtonsLeft[i].setForeground(Color.black);
        }
        for (int i = 0; i < jButtonsMiddle.length; i++) {
            jButtonsMiddle[i].setBackground(Color.white);
            jButtonsMiddle[i].setForeground(Color.black);
        }
        for (int i = 0; i < jButtonsRight.length; i++) {
            jButtonsRight[i].setBackground(Color.white);
            jButtonsRight[i].setForeground(Color.black);
        }
    }

    /**
     * Methode, um den dunklen Modus zu aktivieren.
     */
    public void SetDarkmode() {
        // Hauptkomponenten setzen
        spacer1.setBackground(Color.black);
        jPanel1.setBackground(Color.black);
        jPanelRight.setBackground(Color.black);
        jPanelMiddle.setBackground(Color.black);
        jPanelLeft.setBackground(Color.black);
        // HTML-Darstellung setzen
        jTextPane.setBackground(Color.black);
        // Scrollbar setzen
        jScrollPane.getHorizontalScrollBar().setBackground(Color.black);
        // Knoepfe setzen
        for (int i = 0; i < jButtonsNumber.length; i++) {
            jButtonsNumber[i].setBackground(Color.black);
            jButtonsNumber[i].setForeground(Color.white);
        }
        for (int i = 0; i < jButtonsLeft.length; i++) {
            jButtonsLeft[i].setBackground(Color.black);
            jButtonsLeft[i].setForeground(Color.white);
        }
        for (int i = 0; i < jButtonsMiddle.length; i++) {
            jButtonsMiddle[i].setBackground(Color.black);
            jButtonsMiddle[i].setForeground(Color.white);
        }
        for (int i = 0; i < jButtonsRight.length; i++) {
            jButtonsRight[i].setBackground(Color.black);
            jButtonsRight[i].setForeground(Color.white);
        }
    }

    /**
     * Getter-Methode fuer JButton[] jButtonsNumber.
     * @return JButton[] jButtonsNumber
     */
    protected JButton[] GetJButtonsNumber() {
        return jButtonsNumber;
    }

    /**
     * Getter-Methode fuer JButton[] jButtonsLeft.
     * @return JButton[] jButtonsLeft
     */
    protected JButton[] GetJButtonsLeft() {
        return jButtonsLeft;
    }

    /**
     * Getter-Methode fuer String[] textsLeft.
     * @return String[] textsLeft
     */
    protected String[] GetTextsLeft() {
        return textsLeft;
    }

    /**
     * Getter-Methode fuer JButton[] jButtonsMiddle.
     * @return JButton[] jButtonsMiddle
     */
    protected JButton[] GetJButtonsMiddle() {
        return jButtonsMiddle;
    }

    /**
     * Getter-Methode fuer String[] textsMiddle.
     * @return String[] textsMiddle
     */
    protected String[] GetTextsMiddle() {
        return textsMiddle;
    }

    /**
     * Getter-Methode fuer JButton[] jButtonsRight.
     * @return JButton[] jButtonsRight
     */
    protected JButton[] GetJButtonsRight() {
        return jButtonsRight;
    }

    /**
     * Getter-Methode fuer String[] textsRight.
     * @return String[] textsRight
     */
    protected String[] GetTextsRight() {
        return textsRight;
    }

    /**
     * Methode zur Setzung der Referenzen auf Model und Controller in Main.
     * Zudem Methoden, die eigentlich im Konstruktor waeren, jedoch Referenzen auf andere Klassen benoetigen und daher
     * erst hier ausgefuehrt werden koennen.
     * @param m Model-Instanz
     * @param c Controller-Instanz
     */
    public void UpdateLinks(Model m, Controller c) {
        model = m;
        controller = c;
        SetLightmode();
    }
}
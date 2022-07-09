package Calculator.view.calculator;

import Calculator.controller.Controller;
import Calculator.model.Model;
import Calculator.view.main.ProcessKeyInput;
import Calculator.view.main.View;
import Calculator.model.ColorMode;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
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
    private javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
    private javax.swing.JPanel jPanel3 = new javax.swing.JPanel();
    private javax.swing.JPanel jPanel4 = new javax.swing.JPanel();
    // JTextPane mit Ausgabe bzw. Anzeige des Ausdruckes, in JScrollBar gehuellt
    private javax.swing.JTextPane jTextPane = new javax.swing.JTextPane();
    private JScrollPane jScrollPane;
    // Arrays mit Knoepfen
    private javax.swing.JButton[] jButtonsNumber;
    private javax.swing.JButton[] jButtonsLeft;
    private javax.swing.JButton[] jButtonsMiddle;
    private javax.swing.JButton[] jButtonsRight;
    private javax.swing.JButton[] jButtonsCursor;
    private javax.swing.JButton[] jButtonsMode;
    // Arrays mit Beschriftungen der Knoepfe
    private String[] textsLeft;
    private String[] textsMiddle;
    private String[] textsRight;
    private String[] textsCursor;
    private String[] textsMode;
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
        jPanel2.setLayout(new GridLayout(3, 3));
        jPanel1.add(jPanel2);
        jPanel3.setLayout(new GridLayout(4, 3));
        jPanel1.add(jPanel3);
        jPanel4.setLayout(new GridLayout(4, 2));
        jPanel1.add(jPanel4);
        // Definierung der Beschriftungen und Deklarierung der Button-Arrays nach Anzahl der Texte
        jButtonsNumber = new JButton[10];
        textsLeft = new String[]{"DEL", "AC", "+", "-", "*", "/", "Ans", "x^"};
        jButtonsLeft = new JButton[textsLeft.length];
        textsMiddle = new String[]{",", "="};
        jButtonsMiddle = new JButton[textsMiddle.length];
        textsRight = new String[]{"sin(", "cos(", "tan(", "lg(", "(", ")"};
        jButtonsRight = new JButton[textsRight.length];
        textsCursor = new String[]{"<-", "->"};
        jButtonsCursor = new JButton[textsCursor.length];
        textsMode = new String[]{"L"};
        jButtonsMode = new JButton[textsMode.length];
        // Instanz der Beiklasse "ProcessButtonInput", um auf Knopfdrucke zu reagieren
        processButtonInput = new ProcessButtonInput(this);
        // Generierung der einzelnen Knoepfe: ueber jeder Knopf-Array iterieren und dabei Knoepfe mit Beschriftungen aus
        // Texte-Array erstellen, ProcessButtonInput uebergeben und Knoepfe ins Layout hinzufuegen
        for (int i = 0; i < jButtonsNumber.length; i++) {
            jButtonsNumber[i] = new javax.swing.JButton(Integer.toString((Integer) i));
            jButtonsNumber[i].addActionListener(processButtonInput);
            jPanel3.add(jButtonsNumber[i]);
        }
        for (int i = 0; i < jButtonsLeft.length; i++) {
            jButtonsLeft[i] = new javax.swing.JButton("" + textsLeft[i] + "");
            jButtonsLeft[i].addActionListener(processButtonInput);
            jPanel4.add(jButtonsLeft[i]);
        }
        for (int i = 0; i < jButtonsMiddle.length; i++) {
            jButtonsMiddle[i] = new javax.swing.JButton("" + textsMiddle[i] + "");
            jPanel3.add(jButtonsMiddle[i]);
            jButtonsMiddle[i].addActionListener(processButtonInput);
        }
        for (int i = 0; i < jButtonsRight.length; i++) {
            jButtonsRight[i] = new javax.swing.JButton("" + textsRight[i] + "");
            jButtonsRight[i].addActionListener(processButtonInput);
            jPanel2.add(jButtonsRight[i]);
        }
        for (int i = 0; i < jButtonsCursor.length; i++) {
            jButtonsCursor[i] = new javax.swing.JButton("" + textsCursor[i] + "");
            jButtonsCursor[i].addActionListener(processButtonInput);
            jPanel2.add(jButtonsCursor[i]);
        }
        for (int i = 0; i < jButtonsMode.length; i++) {
            jButtonsMode[i] = new javax.swing.JButton("" + textsMode[i] + "");
            jButtonsMode[i].addActionListener(processButtonInput);
            jPanel2.add(jButtonsMode[i]);
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
                jScrollPane.getHorizontalScrollBar().setValue(Math.round(model.GetCursorPosition() * jTextPane.getWidth() / model.GetHTMLElementsListSize()));
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
        jPanel2.setBackground(Color.white);
        jPanel3.setBackground(Color.white);
        jPanel4.setBackground(Color.white);
        // HTML-Darstellung setzen
        jTextPane.setBackground(Color.white);
        // Scrollbar setzen
        jScrollPane.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.lightGray;
                this.trackColor = Color.white;
            }
            /* @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton button = super.createDecreaseButton(orientation);
                button.setForeground(Color.black);
                button.setBackground(Color.white);
                return button;
            }
            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = super.createIncreaseButton(orientation);
                button.setForeground(Color.black);
                button.setBackground(Color.white);
                return button;
            } */
        });
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
        for (int i = 0; i < jButtonsCursor.length; i++) {
            jButtonsCursor[i].setBackground(Color.white);
            jButtonsCursor[i].setForeground(Color.black);
        }
        for (int i = 0; i < jButtonsMode.length; i++) {
            jButtonsMode[i].setBackground(Color.white);
            jButtonsMode[i].setForeground(Color.black);
        }
        // Text auf Knopf aktualisieren
        jButtonsMode[0].setText("L");
    }

    /**
     * Methode, um den dunklen Modus zu aktivieren.
     */
    public void SetDarkmode() {
        // Hauptkomponenten setzen
        spacer1.setBackground(Color.black);
        jPanel1.setBackground(Color.black);
        jPanel2.setBackground(Color.black);
        jPanel3.setBackground(Color.black);
        jPanel4.setBackground(Color.black);
        // HTML-Darstellung setzen
        jTextPane.setBackground(Color.black);
        // Scrollbar setzen
        jScrollPane.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.darkGray;
                this.trackColor = Color.black;
            }
            /* @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton button = super.createDecreaseButton(orientation);
                button.setForeground(Color.white);
                button.setBackground(Color.black);
                return button;
            }
            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = super.createIncreaseButton(orientation);
                button.setForeground(Color.white);
                button.setBackground(Color.black);
                return button;
            } */
        });
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
        for (int i = 0; i < jButtonsCursor.length; i++) {
            jButtonsCursor[i].setBackground(Color.black);
            jButtonsCursor[i].setForeground(Color.white);
        }
        for (int i = 0; i < jButtonsMode.length; i++) {
            jButtonsMode[i].setBackground(Color.black);
            jButtonsMode[i].setForeground(Color.white);
        }
        // Text auf Knopf aktualisieren
        jButtonsMode[0].setText("D");
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
     * Getter-Methode fuer JButton[] jButtonsCursor.
     * @return JButton[] jButtonsCursor
     */
    protected JButton[] GetJButtonsCursor() {
        return jButtonsCursor;
    }

    /**
     * Getter-Methode fuer String[] textsCursor.
     * @return String[] textsCursor
     */
    protected String[] GetTextsCursor() {
        return textsCursor;
    }

    /**
     * Getter-Methode fuer JButton[] jButtonsMode.
     * @return JButton[] jButtonsMode
     */
    protected JButton[] GetJButtonMode() {
        return jButtonsMode;
    }

    /**
     * Getter-Methode fuer String[] textsMode.
     * @return String[] textsMode
     */
    protected String[] GetTextsMode() {
        return textsMode;
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
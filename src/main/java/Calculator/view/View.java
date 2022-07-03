package Calculator.view;

import Calculator.controller.Controller;
import Calculator.model.ColorMode;
import Calculator.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * View-Klasse mit grafischer Benutzeroberflaeche.
 */
public class View extends JFrame {
    private Model model;
    private Controller controller;
    // Obermenue mit Reitern
    private javax.swing.JMenuBar bar = new javax.swing.JMenuBar();
    private javax.swing.JMenu calculator = new javax.swing.JMenu();
    private javax.swing.JMenu numeralSystems = new javax.swing.JMenu();
    private javax.swing.JMenu volumes = new javax.swing.JMenu();
    private javax.swing.JMenuItem binary = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem decimal = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem hexadecimal = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem cone = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem square = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem cylinder = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem sphere = new javax.swing.JMenuItem();
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
    // Auswerter der Knopf- bzw. Tastaturtastendruecke
    private ProcessButtonInput processButtonInput;
    private ProcessKeyInput processKeyInput;

    /**
     * Konstruktor: Initialisierung der vollen Oberflaeche.
     * Hinzufuegen der Eingabeverarbeitung.
     */
    public View() {
        // Bei Schliessen Programm beenden
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Instanz der Beiklasse "ProcessButtonInput", um auf Klicks zu reagieren
        processButtonInput = new ProcessButtonInput(this);
        processKeyInput = new ProcessKeyInput(this);
        addKeyListener(processKeyInput);
        // Erstellung des Obermenues
        bar = new JMenuBar();
        calculator = new JMenu("Rechner");
        numeralSystems = new JMenu("Zahlensystem ");
        binary = new JMenuItem("Binär");
        decimal = new JMenuItem("Dezimal");
        hexadecimal = new JMenuItem("Hexadezimal");
        volumes = new JMenu("Volumen ");
        cone = new JMenuItem("Kegel");
        square = new JMenuItem("Quader");
        cylinder = new JMenuItem("Zylinder");
        sphere = new JMenuItem("Kugel");
        binary.addActionListener(processButtonInput);
        decimal.addActionListener(processButtonInput);
        hexadecimal.addActionListener(processButtonInput);
        cone.addActionListener(processButtonInput);
        square.addActionListener(processButtonInput);
        cylinder.addActionListener(processButtonInput);
        sphere.addActionListener(processButtonInput);
        numeralSystems.add(decimal);
        numeralSystems.add(binary);
        numeralSystems.add(hexadecimal);
        volumes.add(cone);
        volumes.add(square);
        volumes.add(cylinder);
        volumes.add(sphere);
        bar.add(calculator);
        bar.add(numeralSystems);
        bar.add(volumes);
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
        // Generierung der einzelnen Knoepfe: ueber jeder Knopf-Array iterieren und dabei Knoepfe mit Beschriftungen aus Texte-Array erstellen, ProcessButtonInput uebergeben und Knoepfe ins Layout hinzufuegen
        for (int i = 0; i < jButtonsNumber.length; i++) {
            jButtonsNumber[i] = new JButton(Integer.toString((Integer) i));
            jButtonsNumber[i].addActionListener(processButtonInput);
            jPanel3.add(jButtonsNumber[i]);
        }
        for (int i = 0; i < jButtonsLeft.length; i++) {
            jButtonsLeft[i] = new JButton("" + textsLeft[i] + "");
            jButtonsLeft[i].addActionListener(processButtonInput);
            jPanel4.add(jButtonsLeft[i]);
        }
        for (int i = 0; i < jButtonsMiddle.length; i++) {
            jButtonsMiddle[i] = new JButton("" + textsMiddle[i] + "");
            jPanel3.add(jButtonsMiddle[i]);
            jButtonsMiddle[i].addActionListener(processButtonInput);
        }
        for (int i = 0; i < jButtonsRight.length; i++) {
            jButtonsRight[i] = new JButton("" + textsRight[i] + "");
            jButtonsRight[i].addActionListener(processButtonInput);
            jPanel2.add(jButtonsRight[i]);
        }
        for (int i = 0; i < jButtonsCursor.length; i++) {
            jButtonsCursor[i] = new JButton("" + textsCursor[i] + "");
            jButtonsCursor[i].addActionListener(processButtonInput);
            jPanel2.add(jButtonsCursor[i]);
        }
        for (int i = 0; i < jButtonsMode.length; i++) {
            jButtonsMode[i] = new JButton("" + textsMode[i] + "");
            jButtonsMode[i].addActionListener(processButtonInput);
            jPanel2.add(jButtonsMode[i]);
        }
        // Hinzufuegen des Gesamtlayouts in die ContentPane (das "Fenster")
        this.getRootPane().setJMenuBar(bar);
        this.getContentPane().add(spacer1);
        // Fenster als dynamisch skalierbar definieren
        pack();
        // Groesse setzen
        setSize(600,300);
        // Sichtbar setzen
        setVisible(true);
        // Fokussierbar machen und Fokus (fuer Tastatureingabe) anfordern
        setFocusable(true);
        requestFocus();
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
        // Fokus fuer Tastatureingabe anfordern
        requestFocus();
        // Cursor-Positon berechnen und setzen
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                jScrollPane.getHorizontalScrollBar().setValue(Math.round(model.GetCursorPosition() * jTextPane.getWidth() / model.GetHTMLElementsListSize()));
            }
        });
    }

    /**
     * Methode, um den Farbmodus zu weckseln.
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
    protected void SetLightmode() {
        // Im Model setzen
        model.SetLightmode();
        // Obermenue setzen
        bar.setForeground(Color.black);
        bar.setBackground(Color.white);
        calculator.setForeground(Color.black);
        calculator.setBackground(Color.white);
        numeralSystems.setForeground(Color.black);
        numeralSystems.setBackground(Color.white);
        binary.setForeground(Color.black);
        binary.setBackground(Color.white);
        decimal.setForeground(Color.black);
        decimal.setBackground(Color.white);
        hexadecimal.setForeground(Color.black);
        hexadecimal.setBackground(Color.white);
        volumes.setForeground(Color.black);
        volumes.setBackground(Color.white);
        cone.setForeground(Color.black);
        cone.setBackground(Color.white);
        square.setForeground(Color.black);
        square.setBackground(Color.white);
        cylinder.setForeground(Color.black);
        cylinder.setBackground(Color.white);
        sphere.setForeground(Color.black);
        sphere.setBackground(Color.white);
        // Hauptkomponenten setzen
        spacer1.setBackground(Color.white);
        jPanel1.setBackground(Color.white);
        jPanel2.setBackground(Color.white);
        jPanel3.setBackground(Color.white);
        jPanel4.setBackground(Color.white);
        // HTML-Darstellung setzen
        jTextPane.setBackground(Color.white);
        jScrollPane.setBackground(Color.white);
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
    protected void SetDarkmode() {
        // Im Model setzen
        model.SetDarkmode();
        // Obermenue setzen
        bar.setForeground(Color.white);
        bar.setBackground(Color.black);
        calculator.setForeground(Color.white);
        calculator.setBackground(Color.black);
        numeralSystems.setForeground(Color.white);
        numeralSystems.setBackground(Color.black);
        binary.setForeground(Color.white);
        binary.setBackground(Color.black);
        decimal.setForeground(Color.white);
        decimal.setBackground(Color.black);
        hexadecimal.setForeground(Color.white);
        hexadecimal.setBackground(Color.black);
        volumes.setForeground(Color.white);
        volumes.setBackground(Color.black);
        cone.setForeground(Color.white);
        cone.setBackground(Color.black);
        square.setForeground(Color.white);
        square.setBackground(Color.black);
        cylinder.setForeground(Color.white);
        cylinder.setBackground(Color.black);
        sphere.setForeground(Color.white);
        sphere.setBackground(Color.black);
        // Hauptkomponenten setzen
        spacer1.setBackground(Color.black);
        jPanel1.setBackground(Color.black);
        jPanel2.setBackground(Color.black);
        jPanel3.setBackground(Color.black);
        jPanel4.setBackground(Color.black);
        // HTML-Darstellung setzen
        jTextPane.setBackground(Color.black);
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
     * Getter-Methode fuer das JMenuItem binary.
     * @return JMenuItem binary
     */
    protected JMenuItem GetJMenuItemBinary() {
        return binary;
    }

    /**
     * Getter-Methode fuer das JMenuItem decimal.
     * @return JMenuItem decimal.
     */
    protected JMenuItem GetJMenuItemDecimal() {
        return decimal;
    }

    /**
     * Getter-Methode fuer das JMenuItem hexadecimal.
     * @return JMenuItem hexadecimal
     */
    protected JMenuItem GetJMenuItemHexa() {
        return hexadecimal;
    }

    /**
     * Getter-Methode fuer das JMenuItem cone.
     * @return JMenuItem cone
     */
    protected JMenuItem GetJMenuItemCone() {
        return cone;
    }

    /**
     * Getter-Methode fuer das JMenuItem square.
     * @return JMenuItem square
     */
    protected JMenuItem GetJMenuItemSquare() {
        return square;
    }

    /**
     * Getter-Methode fuer das JMenuItem cylinder.
     * @return JMenuItem cylinder
     */
    protected JMenuItem GetJMenuItemCylinder() {
        return cylinder;
    }

    /**
     * Getter-Methode fuer das JMenuItem sphere.
     * @return JMenuItem sphere
     */
    protected JMenuItem GetJMenuItemSphere() {
        return sphere;
    }

    /**
     * Getter-Methode fuer die Fensterhoehe.
     * Genutzt, um die HTML-Schriftgroesse im Model zu bestimmen.
     * @return int Fenstergroesse
     */
    public int GetWindowHeight()
    {
        return getHeight();
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
        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                UpdateView();
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
        SetLightmode();
    }
}
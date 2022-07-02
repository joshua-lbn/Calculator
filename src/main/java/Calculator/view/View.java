package Calculator.view;

import Calculator.controller.Controller;
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
    // JPanels fuer das Layout
    private javax.swing.JPanel spacer1 = new javax.swing.JPanel();
    private javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
    private javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
    private javax.swing.JPanel jPanel3 = new javax.swing.JPanel();
    private javax.swing.JPanel jPanel4 = new javax.swing.JPanel();
    // Label mit Ausgabe bzw. Anzeige des Ausdruckes
    private javax.swing.JTextPane jTextPane = new javax.swing.JTextPane();
    private javax.swing.JLabel[] test;
    private javax.swing.JPanel test3 = new javax.swing.JPanel();
    //private javax.swing.JButton mode1 = new javax.swing.JButton();
    // Arrays mit Knoepfen
    private javax.swing.JButton[] jButtonsNumber;
    private javax.swing.JButton[] jButtonsLeft;
    private javax.swing.JButton[] jButtonsMiddle;
    private javax.swing.JButton[] jButtonsRight;
    private javax.swing.JButton[] jButtonsCursor;
    private javax.swing.JButton[] jButtonMode;
    // Arrays mit Beschriftungen der Knoepfe
    private String[] textsLeft;
    private String[] textsMiddle;
    private String[] textsRight;
    private String[] textsCursor;
    private String[] textsMode;
    private ProcessKeyInput processKeyInput;
    private Mode mode;
    private JScrollPane jScrollPane;

    //-----------------------------
    private javax.swing.JMenuItem cone = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem  square = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem cylinder = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem sphere = new javax.swing.JMenuItem();
    private javax.swing.JMenu calculator = new javax.swing.JMenu();
    private javax.swing.JMenu numeralSystems = new javax.swing.JMenu();
    private javax.swing.JMenu volumes = new javax.swing.JMenu();
    private javax.swing.JMenuBar bar = new javax.swing.JMenuBar();
    private javax.swing.JMenuItem decimal = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem binary = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem hexadecimal = new javax.swing.JMenuItem();
    private int fontSize;

    //-----------------------------
    //Flag für Shifttaste
    boolean shifttasteGedrueckt;

    /**
     * Konstruktor: Initialisierung der vollen Oberflaeche.
     * Hinzufügen des ProcessKeyInput.
     */
    public View() {
        // Bei Schliessen Programm beenden
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        // Erstellung des Layouts durch Schachtelung der Panels und Hinzufuegen des Labels
        spacer1.setLayout(new java.awt.GridLayout(2, 1));
        jTextPane = new javax.swing.JTextPane();
        jTextPane.setContentType("text/html");
        jTextPane.setEditable(false);
        jTextPane.setFocusable(false);
        jScrollPane = new JScrollPane (jTextPane);
        spacer1.add(jScrollPane);
        jPanel1.setLayout(new java.awt.GridLayout(1, 3));
        spacer1.add(jPanel1);
        jPanel2.setLayout(new java.awt.GridLayout(3, 3));
        jPanel1.add(jPanel2);
        jPanel3.setLayout(new java.awt.GridLayout(4, 3));
        jPanel1.add(jPanel3);
        jPanel4.setLayout(new java.awt.GridLayout(4, 2));
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
        jButtonMode = new JButton[textsMode.length];
        mode = mode.LIGHTMODE;

        // Instanz der Beiklasse "ProcessButtonInput", um auf Klicks zu reagieren
        ProcessButtonInput bl = new ProcessButtonInput(this);
        //fügt KeyListener hinzu und ermöglicht Zugriff auf das Frame
        processKeyInput = new ProcessKeyInput(this);
        this.addKeyListener(processKeyInput);
        // Generierung der einzelnen Knoepfe: ueber jeder Knopf-Array iterieren und dabei Knoepfe mit Beschriftungen aus Texte-Array erstellen, ProcessButtonInput uebergeben und Knoepfe ins Layout hinzufuegen
        for (int i = 0; i < jButtonsNumber.length; i++) {
            jButtonsNumber[i] = new javax.swing.JButton(Integer.toString((Integer) i));
            jButtonsNumber[i].addActionListener(bl);
            jPanel3.add(jButtonsNumber[i]);
        }
        for (int i = 0; i < jButtonsLeft.length; i++) {
            jButtonsLeft[i] = new javax.swing.JButton("" + textsLeft[i] + "");
            jButtonsLeft[i].addActionListener(bl);
            jPanel4.add(jButtonsLeft[i]);
        }
        for (int i = 0; i < jButtonsMiddle.length; i++) {
            jButtonsMiddle[i] = new javax.swing.JButton("" + textsMiddle[i] + "");
            jPanel3.add(jButtonsMiddle[i]);
            jButtonsMiddle[i].addActionListener(bl);
        }
        for (int i = 0; i < jButtonsRight.length; i++) {
            jButtonsRight[i] = new javax.swing.JButton("" + textsRight[i] + "");
            jButtonsRight[i].addActionListener(bl);
            jPanel2.add(jButtonsRight[i]);
        }
        for (int i = 0; i < jButtonsCursor.length; i++) {
            jButtonsCursor[i] = new javax.swing.JButton("" + textsCursor[i] + "");
            jButtonsCursor[i].addActionListener(bl);
            jPanel2.add(jButtonsCursor[i]);
        }
        for (int i = 0; i < jButtonMode.length; i++) {
            jButtonMode[i] = new javax.swing.JButton("" + textsMode[i] + "");
            jButtonMode[i].addActionListener(bl);
            jPanel2.add(jButtonMode[i]);
        }
        //-----------------------------

        bar = new javax.swing.JMenuBar();

        calculator = new javax.swing.JMenu("Rechner");
        numeralSystems = new javax.swing.JMenu("Zahlensystem ");
        volumes = new javax.swing.JMenu("Volumen ");
        cone = new javax.swing.JMenuItem("Kegel");
        square = new javax.swing.JMenuItem("Quader");
        cylinder = new javax.swing.JMenuItem("Zylinder");
        sphere = new javax.swing.JMenuItem("Kugel");
        binary =new javax.swing.JMenuItem("Binär");
        decimal =new javax.swing.JMenuItem("Dezimal");
        hexadecimal =new javax.swing.JMenuItem("Hexadezimal");

        cone.addActionListener(bl);
        square.addActionListener(bl);
        cylinder.addActionListener(bl);
        sphere.addActionListener(bl);
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

        SetLightmode();
        // Hinzufuegen des Gesamtlayouts in die ContentPane (das "Fenster")
        this.getContentPane().add(spacer1);
        this.getRootPane().setJMenuBar(bar);
        // Fenster als dynamisch skalierbar definieren
        pack();
        // Fenster sichtbar setzen
        setSize(600,300);
        setVisible(true);
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
        jTextPane.setText(model.GetHTMLExpression());
        requestFocus();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                jScrollPane.getHorizontalScrollBar().setValue(Math.round(model.GetCursorPosition() * jTextPane.getWidth() / model.GetHTMLElementsListSize()));
            }
        });
    }

    // Veraltet! Bitte nicht mehr verwenden.
    /* public void UpdateTest(int a) {


        spacer1.remove(jTextPane);

        spacer1.removeAll();
        test3.removeAll();

        jPanel1.removeAll();
        jPanel2.removeAll();
        jPanel3.removeAll();
        jPanel4.removeAll();


        // Bei Schliessen Programm beenden
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        // Erstellung des Layouts durch Schachtelung der Panels und Hinzufuegen des Labels
        spacer1.setLayout(new java.awt.GridLayout(2, 1));
        jTextPane = new javax.swing.JTextPane();
        jTextPane.setContentType("text/html");
        JScrollPane scrollPane = new JScrollPane (jTextPane, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        //scrollPane.setBounds(0,0,200,300);
        this.getContentPane().add(scrollPane);
        //------------------------------------------------


        test = new JLabel[a * 2];

        test3.setLayout(new java.awt.GridLayout(a * 2 + 1, 1));


        spacer1.add(test3);

        for (int i = 0; i < a; i++) {
            test[i] = new javax.swing.JLabel();
            test3.add(test[i]);
        }
        test3.add(jTextPane);
        for (int i = 0; i < a; i++) {
            test[i] = new javax.swing.JLabel();
            test3.add(test[i]);
        }


        //------------------------------------------------
        jPanel1.setLayout(new java.awt.GridLayout(1, 3));
        spacer1.add(jPanel1);
        jPanel2.setLayout(new java.awt.GridLayout(3, 3));
        jPanel1.add(jPanel2);
        jPanel3.setLayout(new java.awt.GridLayout(4, 3));
        jPanel1.add(jPanel3);
        jPanel4.setLayout(new java.awt.GridLayout(4, 2));
        jPanel1.add(jPanel4);
        // Definierung der Beschriftungen und Deklarierung der Button-Arrays nach Anzahl der Texte
        jButtonsNumber = new JButton[10];
        textsLeft = new String[]{"DEL", "AC", "+", "-", "*", "/", "Ans", "x^"};
        jButtonsLeft = new JButton[textsLeft.length];
        textsMiddle = new String[]{",", "="};
        jButtonsMiddle = new JButton[textsMiddle.length];
        textsRight = new String[]{"cos(", "sin(", "tan(", "lg(", "(", ")"};
        jButtonsRight = new JButton[textsRight.length];
        textsCursor = new String[]{"<-", "->"};
        jButtonsCursor = new JButton[textsCursor.length];
        textsMode = new String[]{"L"};
        jButtonMode = new JButton[textsMode.length];
        // Instanz der Beiklasse "ProcessButtonInput", um auf Klicks zu reagieren
        ProcessButtonInput bl = new ProcessButtonInput(this);
        // Generierung der einzelnen Knoepfe: ueber jeder Knopf-Array iterieren und dabei Knoepfe mit Beschriftungen aus Texte-Array erstellen, ProcessButtonInput uebergeben und Knoepfe ins Layout hinzufuegen
        for (int i = 0; i < jButtonsNumber.length; i++) {
            jButtonsNumber[i] = new javax.swing.JButton(Integer.toString((Integer) i));
            jButtonsNumber[i].addActionListener(bl);
            jPanel3.add(jButtonsNumber[i]);
        }
        for (int i = 0; i < jButtonsLeft.length; i++) {
            jButtonsLeft[i] = new javax.swing.JButton("" + textsLeft[i] + "");
            jButtonsLeft[i].addActionListener(bl);
            jPanel4.add(jButtonsLeft[i]);
        }
        for (int i = 0; i < jButtonsMiddle.length; i++) {
            jButtonsMiddle[i] = new javax.swing.JButton("" + textsMiddle[i] + "");
            jPanel3.add(jButtonsMiddle[i]);
            jButtonsMiddle[i].addActionListener(bl);
        }
        for (int i = 0; i < jButtonsRight.length; i++) {
            jButtonsRight[i] = new javax.swing.JButton("" + textsRight[i] + "");
            jButtonsRight[i].addActionListener(bl);
            jPanel2.add(jButtonsRight[i]);
        }
        for (int i = 0; i < jButtonsCursor.length; i++) {
            jButtonsCursor[i] = new javax.swing.JButton("" + textsCursor[i] + "");
            jButtonsCursor[i].addActionListener(bl);
            jPanel2.add(jButtonsCursor[i]);
        }
        for (int i = 0; i < jButtonMode.length; i++) {
            jButtonMode[i] = new javax.swing.JButton("" + textsMode[i] + "");
            jButtonMode[i].addActionListener(bl);
            jPanel2.add(jButtonMode[i]);
        }
        //-----------------------------

        bar = new javax.swing.JMenuBar();


        rechner = new javax.swing.JMenu("Rechner");
        zahlensystem = new javax.swing.JMenu("Zahlensystem ");
        volumen = new javax.swing.JMenu("Volumen ");
        cone = new javax.swing.JMenuItem("Kegel");
        square = new javax.swing.JMenuItem("Quader");
        cylinder = new javax.swing.JMenuItem("Zylinder");
        sphere = new javax.swing.JMenuItem("Kugel");
        kegel = new javax.swing.JMenuItem("Kegel");
        kegel=new javax.swing.JMenuItem("Kegel");
        dezimal=new javax.swing.JMenuItem("Dezimal");
        binaer =new javax.swing.JMenuItem("Binär");
        hexa =new javax.swing.JMenuItem("Hexa");

        cone.addActionListener( bl );
        square.addActionListener( bl );
        cylinder.addActionListener( bl );
        sphere.addActionListener( bl );
        volumen.add(cone);
        volumen.add(square);
        volumen.add(cylinder);
        volumen.add(sphere);
        zahlensysteme.add(dezimal);
        zahlensysteme.add(binaer);
        zahlensysteme.add(hexa);

        volumen.add(kegel);

        bar.add(rechner);
        bar.add(zahlensystem);
        bar.add(volumen);


        //-----------------------------

        // Hinzufuegen des Gesamtlayouts in die ContentPane (das "Fenster")
        this.getContentPane().add(spacer1);
        this.getRootPane().setJMenuBar(bar);
        // Fenster als dynamisch skalierbar definieren
        if (mode == mode.LIGHTMODE) {
            SetLightmode();
        }
        else {
            SetDarkmode();
        }
        pack();
        // Fenster sichtbar setzen
        setVisible(true);
    } */

    /**
     * Getter-Methode für JButton[] jButtonsNumber
     * @return JButton[] jButtonsNumber
     */
    protected JButton[] GetJButtonsNumber() {
        return jButtonsNumber;
    }

    /**
     * Getter-Methode für JButton[] jButtonsLeft
     *
     * @return JButton[] jButtonsLeft
     */
    protected JButton[] GetJButtonsLeft() {
        return jButtonsLeft;
    }

    /**
     * Getter-Methode für String[] textsLeft
     *
     * @return String[] textsLeft
     */
    protected String[] GetTextsLeft() {
        return textsLeft;
    }

    /**
     * Getter-Methode für JButton[] jButtonsMiddle
     *
     * @return JButton[] jButtonsMiddle
     */
    protected JButton[] GetJButtonsMiddle() {
        return jButtonsMiddle;
    }

    /**
     * Getter-Methode für String[] textsMiddle
     *
     * @return String[] textsMiddle
     */
    protected String[] GetTextsMiddle() {
        return textsMiddle;
    }

    /**
     * Getter-Methode für JButton[] jButtonsRight
     *
     * @return JButton[] jButtonsRight
     */
    protected JButton[] GetJButtonsRight() {
        return jButtonsRight;
    }

    /**
     * Getter-Methode für String[] textsRight
     *
     * @return String[] textsRight
     */
    protected String[] GetTextsRight() {
        return textsRight;
    }

    /**
     * Getter-Methode für JButton[] jButtonsCursor
     *
     * @return JButton[] jButtonsCursor
     */
    protected JButton[] GetJButtonsCursor() {
        return jButtonsCursor;
    }

    /**
     * Getter-Methode für String[] textsCursor
     *
     * @return String[] textsCursor
     */
    protected String[] GetTextsCursor() {
        return textsCursor;
    }

    protected JButton[] GetJButtonMode() {
        return jButtonMode;
    }

    protected String[] GetTextsMode() {
        return textsMode;
    }
    public Mode GetMode(){
        return mode;
    }

    protected JMenuItem GetJMenuItemCone() {
        return cone;
    }
    protected JMenuItem GetJMenuItemSquare() {
        return square;
    }
    protected JMenuItem GetJMenuItemCylinder() {
        return cylinder;
    }
    protected JMenuItem GetJMenuItemSphere() {
        return sphere;
    }
    protected void SwitchMode() {
        if (mode == mode.DARKMODE) {
            SetLightmode();
        }
        else {
            SetDarkmode();
        }
    }
    protected void SetLightmode() {
        mode = mode.LIGHTMODE;
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
        for (int i = 0; i < jButtonMode.length; i++) {
            jButtonMode[i].setBackground(Color.white);
            jButtonMode[i].setForeground(Color.black);
        }
        spacer1.setBackground(Color.white);
        bar.setBackground(Color.white);
        volumes.setForeground(Color.black);
        cone.setForeground(Color.black);
        calculator.setForeground(Color.black);
        numeralSystems.setForeground(Color.black);
        jPanel1.setBackground(Color.white);
        jPanel2.setBackground(Color.white);
        jPanel3.setBackground(Color.white);
        jPanel4.setBackground(Color.white);
        jButtonMode[0].setText("D");
    }

    protected void SetDarkmode() {
        mode = mode.DARKMODE;
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
        for (int i = 0; i < jButtonMode.length; i++) {
            jButtonMode[i].setBackground(Color.black);
            jButtonMode[i].setForeground(Color.white);
        }
        spacer1.setBackground(Color.black);
        bar.setBackground(Color.black);
        volumes.setForeground(Color.white);
        cone.setForeground(Color.white);
        calculator.setForeground(Color.white);
        numeralSystems.setForeground(Color.white);
        jPanel1.setBackground(Color.black);
        jPanel2.setBackground(Color.black);
        jPanel3.setBackground(Color.black);
        jPanel4.setBackground(Color.black);
        jButtonMode[0].setText("L");
    }

    public int GetWindowHeight()
    {
        return getHeight();
    }

    /**
     * Methode zur Setzung der Referenzen auf Model und Controller in Main
     *
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
    }
}



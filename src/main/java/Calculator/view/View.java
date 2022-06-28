package Calculator.view;

import Calculator.controller.Controller;
import Calculator.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
    private javax.swing.JLabel label = new javax.swing.JLabel();

    private javax.swing.JLabel[] test;

    private javax.swing.JPanel test3 = new javax.swing.JPanel();

    // Arrays mit Knoepfen
    private javax.swing.JButton[] jButtonsNumber;
    private javax.swing.JButton[] jButtonsLeft;
    private javax.swing.JButton[] jButtonsMiddle;
    private javax.swing.JButton[] jButtonsRight;
    private javax.swing.JButton[] jButtonsCursor;
    // Arrays mit Beschriftungen der Knoepfe
    private String[] textsLeft;
    private String[] textsMiddle;
    private String[] textsRight;
    private String[] textsCursor;
    private ProcessKeyInput processKeyInput;

    //-----------------------------
    private javax.swing.JMenuItem kegel = new javax.swing.JMenuItem();
    private javax.swing.JMenu rechner = new javax.swing.JMenu();
    private javax.swing.JMenu zahlensysteme = new javax.swing.JMenu();
    private javax.swing.JMenu volumen = new javax.swing.JMenu();
    private javax.swing.JMenuBar bar = new javax.swing.JMenuBar();
    private javax.swing.JMenuItem Dezimal = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem Binär = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem Hexa = new javax.swing.JMenuItem();

    //-----------------------------
    /**
     * Konstruktor: Initialisierung der vollen Oberflaeche.
     * Hinzufügen des KeyListeners und Zugriff zulassen
     */
    public View() {
        //fügt KeyListener hinzu und ermöglicht Zugriff auf das Frame
        processKeyInput = new ProcessKeyInput(this);
       this.addKeyListener(processKeyInput);
       this.setFocusable(true);
        // Bei Schliessen Programm beenden
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        // Erstellung des Layouts durch Schachtelung der Panels und Hinzufuegen des Labels
        spacer1.setLayout(new java.awt.GridLayout(2, 1));
        label = new javax.swing.JLabel();
        spacer1.add(label);
        jPanel1.setLayout(new java.awt.GridLayout(1, 3));
        spacer1.add(jPanel1);
        jPanel2.setLayout(new java.awt.GridLayout(4, 2));
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
        //-----------------------------

        bar = new javax.swing.JMenuBar();


        rechner = new javax.swing.JMenu("Rechner");
        zahlensysteme = new javax.swing.JMenu("Zahlensystem ");
        volumen = new javax.swing.JMenu("Volumen ");
        kegel = new javax.swing.JMenuItem("Kegel");
        Dezimal =new javax.swing.JMenuItem("Dezimal");
        Binär =new javax.swing.JMenuItem("Binär");
        Hexa =new javax.swing.JMenuItem("Hexa");

        volumen.add(kegel);
        zahlensysteme.add(Dezimal);
        zahlensysteme.add(Binär);
        zahlensysteme.add(Hexa);


        bar.add(rechner);
        bar.add(zahlensysteme);
        bar.add(volumen);


        //-----------------------------
        // Hinzufuegen des Gesamtlayouts in die ContentPane (das "Fenster")
        this.getContentPane().add(spacer1);
        this.getRootPane().setJMenuBar(bar);
        // Fenster als dynamisch skalierbar definieren
        pack();
        // Fenster sichtbar setzen
        setVisible(true);
        //this.addKeyListener(this);
    }

    /**
     * Methode zur Uebergabe des neu hinzugefuegten Zeichens (aus dem ProcessButtonInput) an den Controller.
     *
     * @param s Neues Zeichen
     */
    public void Update(String s) {
        controller.Update(s);
    }
    /**
     * Methode, um den Controller zum Generieren eines neuen Bildes bzw. Ausgabe aufzufordern.
     */
    public void UpdateView() {
        controller.UpdateView();
    }

    public void UpdateTest(int a) {


        spacer1.remove(label);

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
        label = new javax.swing.JLabel();
        //------------------------------------------------


        test = new JLabel[a * 2];

        test3.setLayout(new java.awt.GridLayout(a * 2 + 1, 1));


        spacer1.add(test3);

        for (int i = 0; i < a; i++) {
            test[i] = new javax.swing.JLabel();
            test3.add(test[i]);
        }
        test3.add(label);
        for (int i = 0; i < a; i++) {
            test[i] = new javax.swing.JLabel();
            test3.add(test[i]);
        }


        //------------------------------------------------
        jPanel1.setLayout(new java.awt.GridLayout(1, 3));
        spacer1.add(jPanel1);
        jPanel2.setLayout(new java.awt.GridLayout(4, 2));
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

        //-----------------------------

        bar = new javax.swing.JMenuBar();


        rechner = new javax.swing.JMenu("Rechner");
        zahlensysteme = new javax.swing.JMenu("Zahlensystem ");
        volumen = new javax.swing.JMenu("Volumen ");
        kegel = new javax.swing.JMenuItem("Kegel");
        kegel=new javax.swing.JMenuItem("Kegel");
        Dezimal=new javax.swing.JMenuItem("Dezimal");
        Binär=new javax.swing.JMenuItem("Binär");
        Hexa=new javax.swing.JMenuItem("Hexa");

        zahlensysteme.add(Dezimal);
        zahlensysteme.add(Binär);
        zahlensysteme.add(Hexa);

        volumen.add(kegel);

        bar.add(rechner);
        bar.add(zahlensysteme);
        bar.add(volumen);


        //-----------------------------

        // Hinzufuegen des Gesamtlayouts in die ContentPane (das "Fenster")
        this.getContentPane().add(spacer1);
        this.getRootPane().setJMenuBar(bar);
        // Fenster als dynamisch skalierbar definieren
        pack();
        // Fenster sichtbar setzen
        setVisible(true);
    }

    /**
     * Methode, um eine neue Darstellung im Fenster zu setzen.
     * @param i Neue Darstellung bzw. Bild
     */
    public void UpdateIconView(Image i) {
        // Bild skalieren
        i = i.getScaledInstance(-1, label.getHeight(), Image.SCALE_SMOOTH);
        // Bild als ImageIcon im Label setzen
        label.setIcon(new ImageIcon(i));
    }

    /**
     * Getter-Methode für JButton[] jButtonsNumber
     *
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

    /**
     * Methode zur Setzung der Referenzen auf Model und Controller in Main
     *
     * @param m Model-Instanz
     * @param c Controller-Instanz
     */
    public void UpdateLinks(Model m, Controller c) {
        model = m;
        controller = c;
    }
}



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

    //-----------------------------
    private javax.swing.JMenuItem cone = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem  square = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem cylinder = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem sphere = new javax.swing.JMenuItem();
    private javax.swing.JMenu rechner = new javax.swing.JMenu();
    private javax.swing.JMenu zahlensystem = new javax.swing.JMenu();
    private javax.swing.JMenu volumen = new javax.swing.JMenu();
    private javax.swing.JMenuBar bar = new javax.swing.JMenuBar();

    //-----------------------------
    //Flag für Shifttaste
    boolean shifttasteGedrueckt;

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

        cone.addActionListener( bl );
        square.addActionListener( bl );
        cylinder.addActionListener( bl );
        sphere.addActionListener( bl );

        volumen.add(cone);
        volumen.add(square);
        volumen.add(cylinder);
        volumen.add(sphere);

        bar.add(rechner);
        bar.add(zahlensystem);
        bar.add(volumen);

        SetLightmode();
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
public void addKeyListener1(KeyListener Kl)
{
    addKeyListener(Kl);
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

        cone.addActionListener( bl );
        square.addActionListener( bl );
        cylinder.addActionListener( bl );
        sphere.addActionListener( bl );
        volumen.add(cone);
        volumen.add(square);
        volumen.add(cylinder);
        volumen.add(sphere);

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
        volumen.setForeground(Color.black);
        cone.setForeground(Color.black);
        rechner.setForeground(Color.black);
        zahlensystem.setForeground(Color.black);
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
        volumen.setForeground(Color.white);
        cone.setForeground(Color.white);
        rechner.setForeground(Color.white);
        zahlensystem.setForeground(Color.white);
        jPanel1.setBackground(Color.black);
        jPanel2.setBackground(Color.black);
        jPanel3.setBackground(Color.black);
        jPanel4.setBackground(Color.black);
        jButtonMode[0].setText("L");
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
    /* @Override
    public void keyPressed(KeyEvent e) {
        //Prüfung, ob Shifttaste gedrückt...
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            shifttasteGedrueckt = true;
        } else if (shifttasteGedrueckt == true && e.getKeyCode() == KeyEvent.VK_7) {
            Update("/");
            UpdateView();
        }else if (shifttasteGedrueckt == true && e.getKeyCode() == KeyEvent.VK_8) {
            this.Update("(");
            this.UpdateView();
        } else if (shifttasteGedrueckt == true && e.getKeyCode() == KeyEvent.VK_9) {
            this.Update(")");
            this.UpdateView();
        }else if (shifttasteGedrueckt && e.getKeyCode() == KeyEvent.VK_PLUS) {
            this.Update("*");
            this.UpdateView();
        } else if (e.getKeyCode() == KeyEvent.VK_PLUS) {
            this.Update("+");
            this.UpdateView();
        } else if (e.getKeyCode() == KeyEvent.VK_MINUS) {
            this.Update("-");
            this.UpdateView();
        } else if (shifttasteGedrueckt && e.getKeyCode() == KeyEvent.VK_0) {
            this.Update("=");
            this.UpdateView();
        }else if (e.getKeyCode() == KeyEvent.VK_0 || e.getKeyCode() == KeyEvent.VK_NUMPAD0) {
            Update("" + 0 + "");
            UpdateView();
        }
           else if (e.getKeyCode() == KeyEvent.VK_1 || e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
                Update("" + 1 + "");
                UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_2 || e.getKeyCode() == KeyEvent.VK_NUMPAD2) {
                Update("" + 2 + "");
                UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_3 || e.getKeyCode() == KeyEvent.VK_NUMPAD3) {
                Update("" + 3 + "");
                UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_4 || e.getKeyCode() == KeyEvent.VK_NUMPAD4) {
                Update("" + 4 + "");
                UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_5 || e.getKeyCode() == KeyEvent.VK_NUMPAD5) {
                Update("" + 5 + "");
                UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_6 || e.getKeyCode() == KeyEvent.VK_NUMPAD6) {
                Update("" + 6 + "");
                UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_7 || e.getKeyCode() == KeyEvent.VK_NUMPAD7) {
                Update("" + 7 + "");
                UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_8 || e.getKeyCode() == KeyEvent.VK_NUMPAD8) {
                Update("" + 8 + "");
                UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_9 || e.getKeyCode() == KeyEvent.VK_NUMPAD9) {
                Update("" + 9 + "");
                UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                Update("DEL");
                UpdateView();
                model.ClearLatex();
            } else if (e.getKeyCode() == KeyEvent.VK_S) {
                Update("sin(");
                UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_T) {
                Update("tan(");
                UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_C) {
                this.Update("cos(");
                this.UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                this.Update("AC");
                this.UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_L) {
                this.Update("lg(");
                this.UpdateView();
            } else if(e.getKeyCode() == KeyEvent.VK_CIRCUMFLEX) {
                Update("x^");
                UpdateView();
            } else if(e.getKeyCode() == KeyEvent.VK_COMMA) {
                Update(",");
                UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                this.Update("CURSOR-RIGHT");
                this.UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                this.Update("CURSOR_LEFT");
                this.UpdateView();
            }

        }


    //keine Nutzung
    @Override
    public void keyTyped(KeyEvent e) {

    }
//Wenn Shifttaste losgelassen wird
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            shifttasteGedrueckt = false;
        }

    }
    */
}



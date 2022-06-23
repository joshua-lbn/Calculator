package Calculator.view;

import Calculator.controller.Controller;
import Calculator.model.Model;

import javax.swing.*;
import java.awt.*;

/**
 * View-Klasse mit grafischer Benutzeroberflaeche.
 */
public class View extends JFrame{
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
    private javax.swing.JButton[] jButtonsCursor
    // Array mit Beschriftung der Knoepfe
    ;
    private String [ ] textsLeft;
    private String [ ] textsMiddle;
    private String [ ] textsRight;
    private String [ ] textsCursor;

    /**
     * Konstruktor: Initialisierung der vollen Oberflaeche.
     */
    public View() {
        // Bei Schliessen Programm beenden
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        // Erstellung des Layouts durch Schachtelung der Panels und Hinzufuegen des Labels
        spacer1.setLayout(new java.awt.GridLayout(2, 1));
        label = new javax.swing.JLabel();
        spacer1.add(label);
        jPanel1.setLayout( new java.awt.GridLayout( 1, 3 ) );
        spacer1.add (jPanel1);
        jPanel2.setLayout( new java.awt.GridLayout( 3, 2 ) );
        jPanel1.add (jPanel2);
        jPanel3.setLayout( new java.awt.GridLayout( 4, 3 ) );
        jPanel1.add (jPanel3);
        jPanel4.setLayout( new java.awt.GridLayout( 4, 2 ) );
        jPanel1.add (jPanel4);
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
        // Instanz der Beiklasse "ButtonListener", um auf Klicks zu reagieren
        ButtonListener bl = new ButtonListener(this);
        // Generierung der einzelnen Knoepfe: ueber jeder Knopf-Array iterieren und dabei Knoepfe mit Beschriftungen aus Texte-Array erstellen, ButtonListener uebergeben und Knoepfe ins Layout hinzufuegen
        for ( int i = 0; i < jButtonsNumber.length; i++ ) {
            jButtonsNumber[i] = new javax.swing.JButton(Integer.toString((Integer)i));
            jButtonsNumber[i].addActionListener(bl);
            jPanel3.add(jButtonsNumber[i]);
        }
        for ( int i = 0; i < jButtonsLeft.length; i++ ) {
            jButtonsLeft[i] = new javax.swing.JButton ( "" + textsLeft[i] + "" );
            jButtonsLeft[i].addActionListener ( bl );
            jPanel4.add ( jButtonsLeft[i] );
        }
        for ( int i = 0; i < jButtonsMiddle.length; i++) {
            jButtonsMiddle[i] = new javax.swing.JButton("" + textsMiddle[i] + "");
            jPanel3.add(jButtonsMiddle[i]);
            jButtonsMiddle[i].addActionListener(bl);
        }
        for ( int i = 0; i < jButtonsRight.length; i++ ) {
            jButtonsRight[i] = new javax.swing.JButton ( "" + textsRight[i] + "" );
            jButtonsRight[i].addActionListener ( bl );
            jPanel2.add ( jButtonsRight[i] );
        }
        for ( int i = 0; i < jButtonsCursor.length; i++ ) {
            jButtonsCursor[i] = new javax.swing.JButton ( "" + textsCursor[i] + "" );
            jButtonsCursor[i].addActionListener ( bl );
            jPanel2.add ( jButtonsCursor[i] );
        }
        // Hinzufuegen des Gesamtlayouts in die ContentPane (das "Fenster")
        this.getContentPane().add(spacer1);
        // Fenster als dynamisch skalierbar definieren
        pack();
        // Fenster sichtbar setzen
        setVisible (true);
    }

    /**
     * Methode zur Uebergabe des neu hinzugefuegten Zeichens (aus dem ButtonListener) an den Controller.
     * @param s Neues Zeichen
     */
    public void Update(String s)
    {
        controller.Update(s);
    }

    /**
     * Methode, um den Controller zum Generieren eines neuen Bildes bzw. Ausgabe aufzufordern.
     */
    public void UpdateView()
    {
        controller.UpdateView();
    }

    public void UpdateTest(int a){


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



        test = new JLabel[a*2];

        test3.setLayout(new java.awt.GridLayout(a*2+1, 1));



        spacer1.add(test3);

        for ( int i = 0; i < a; i++ ) {
            test[i] = new javax.swing.JLabel();
            test3.add(test[i]);
        }
        test3.add(label);
        for ( int i = 0; i < a; i++ ) {
            test[i] = new javax.swing.JLabel();
            test3.add(test[i]);
        }


        //------------------------------------------------
        jPanel1.setLayout( new java.awt.GridLayout( 1, 3 ) );
        spacer1.add (jPanel1);
        jPanel2.setLayout( new java.awt.GridLayout( 3, 2 ) );
        jPanel1.add (jPanel2);
        jPanel3.setLayout( new java.awt.GridLayout( 4, 3 ) );
        jPanel1.add (jPanel3);
        jPanel4.setLayout( new java.awt.GridLayout( 4, 2 ) );
        jPanel1.add (jPanel4);
        // Definierung der Beschriftungen und Deklarierung der Button-Arrays nach Anzahl der Texte
        jButtonsNumber = new JButton[10];
        textsLeft = new String[]{"DEL", "AC", "+", "-", "*", "/", "Ans", "x^"};
        jButtonsLeft = new JButton[textsLeft.length];
        textsMiddle = new String[]{",", "="};
        jButtonsMiddle = new JButton[textsMiddle.length];
        textsRight = new String[]{"cos(", "sin(", "tan(", "lg(", "(", ")"};
        jButtonsRight = new JButton[textsRight.length];
        // Instanz der Beiklasse "ButtonListener", um auf Klicks zu reagieren
        ButtonListener bl = new ButtonListener(this);
        // Generierung der einzelnen Knoepfe: ueber jeder Knopf-Array iterieren und dabei Knoepfe mit Beschriftungen aus Texte-Array erstellen, ButtonListener uebergeben und Knoepfe ins Layout hinzufuegen
        for ( int i = 0; i < jButtonsNumber.length; i++ ) {
            jButtonsNumber[i] = new javax.swing.JButton(Integer.toString((Integer)i));
            jButtonsNumber[i].addActionListener(bl);
            jPanel3.add(jButtonsNumber[i]);
        }
        for ( int i = 0; i < jButtonsLeft.length; i++ ) {
            jButtonsLeft[i] = new javax.swing.JButton ( "" + textsLeft[i] + "" );
            jButtonsLeft[i].addActionListener ( bl );
            jPanel4.add ( jButtonsLeft[i] );
        }
        for ( int i = 0; i < jButtonsMiddle.length; i++) {
            jButtonsMiddle[i] = new javax.swing.JButton("" + textsMiddle[i] + "");
            jPanel3.add(jButtonsMiddle[i]);
            jButtonsMiddle[i].addActionListener(bl);
        }
        for ( int i = 0; i < jButtonsRight.length; i++ ) {
            jButtonsRight[i] = new javax.swing.JButton ( "" + textsRight[i] + "" );
            jButtonsRight[i].addActionListener ( bl );
            jPanel2.add ( jButtonsRight[i] );
        }
        // Hinzufuegen des Gesamtlayouts in die ContentPane (das "Fenster")
        this.getContentPane().add(spacer1);
        // Fenster als dynamisch skalierbar definieren
        pack();
        // Fenster sichtbar setzen
        setVisible (true);
    }

    /**
     * Methode, um eine neue Darstellung im Fenster zu setzen.
     * @param i Neue Darstellung bzw. Bild
     */
    public void UpdateIconView(Image i)
    {
        // Bild skalieren
        i = i.getScaledInstance(-1, label.getHeight(), Image.SCALE_SMOOTH);
        // Bild als ImageIcon im Label setzen
        label.setIcon(new ImageIcon(i));
    }

    /**
     * Getter-Methode für JButton[] jButtonsNumber
     * @return JButton[] jButtonsNumber
     */
    protected JButton[] GetJButtonsNumber() {
        return jButtonsNumber;
    }

    /**
     * Getter-Methode für JButton[] jButtonsLeft
     * @return JButton[] jButtonsLeft
     */
    protected JButton[] GetJButtonsLeft() {
        return jButtonsLeft;
    }

    /**
     * Getter-Methode für String[] textsLeft
     * @return String[] textsLeft
     */
    protected String[] GetTextsLeft() {
        return textsLeft;
    }

    /**
     * Getter-Methode für JButton[] jButtonsMiddle
     * @return JButton[] jButtonsMiddle
     */
    protected JButton[] GetJButtonsMiddle() {
        return jButtonsMiddle;
    }

    /**
     * Getter-Methode für String[] textsMiddle
     * @return String[] textsMiddle
     */
    protected String[] GetTextsMiddle() {
        return textsMiddle;
    }

    /**
     * Getter-Methode für JButton[] jButtonsRight
     * @return JButton[] jButtonsRight
     */
    protected JButton[] GetJButtonsRight() {
        return jButtonsRight;
    }

    /**
     * Getter-Methode für String[] textsRight
     * @return String[] textsRight
     */
    protected String[] GetTextsRight() {
        return textsRight;
    }

    /**
     * Methode zur Setzung der Referenzen auf Model und Controller in Main
     * @param m Model-Instanz
     * @param c Controller-Instanz
     */
    public void UpdateLinks(Model m, Controller c)
    {
        model = m;
        controller = c;
    }
}

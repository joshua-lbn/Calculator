package Calculator.view.main;

import Calculator.controller.Controller;
import Calculator.model.Model;
import Calculator.view.calculator.ViewCalculator;
import Calculator.view.ViewNumeralSystem;
import Calculator.view.ViewVolumeSquare;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.lang.invoke.VolatileCallSite;

/**
 * View-Klasse, die die Hauptoberflaeche inklusive Reiter verwaltet.
 */
public class View extends JFrame {
    private Model model;
    private Controller controller;
    // Instanz der Beiklasse "ProcessMenuInput", um auf Menuedrucke zu reagieren
    private ProcessMenuInput processMenuInput;
    // Instanz der Beiklasse "ProcessKeyInput", um ViewCalculator-Instanz die Tastatureingaben zu uebergeben
    private ProcessKeyInput processKeyInput;
    // Obermenue mit Reitern
    private javax.swing.JMenuBar bar = new javax.swing.JMenuBar();
    private javax.swing.JMenu calculator = new javax.swing.JMenu();
    private javax.swing.JMenu numeralSystems = new javax.swing.JMenu();
    private javax.swing.JMenu volumes = new javax.swing.JMenu();
    private javax.swing.JMenuItem calculatorItem = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem binary = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem decimal = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem hexadecimal = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem cone = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem square = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem cylinder = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem sphere = new javax.swing.JMenuItem();
    // Unterfenster
    private ViewCalculator viewCalculator;
    private ViewNumeralSystem viewNumeralSystem;
    private ViewVolumeSquare viewVolumeSquare;

    /**
     * Konstruktor: Initialisierung der Reiter.
     * Hinzufuegen der Menueinteraktionsverarbeitung.
     */
    public View() {
        // Bei Schliessen Programm beenden
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Instanz der Beiklasse "ProcessButtonInput", um auf Knopfdrucke zu reagieren
        processMenuInput = new ProcessMenuInput(this);
        // Instanz der Beiklasse "ProcessKeyInput", um auf Tastatureingaben zu reagieren; sowie Voraussetzungen dafuer
        processKeyInput = new ProcessKeyInput(this);
        addKeyListener(processKeyInput);
        setFocusable(true);
        // Erstellung des Obermenues: Instanziierung
        bar = new JMenuBar();
        calculator = new JMenu("Rechner");
        numeralSystems = new JMenu("Zahlensystem ");
        calculatorItem = new JMenuItem("Rechner");
        binary = new JMenuItem("Bin\u00E4r");
        decimal = new JMenuItem("Dezimal");
        hexadecimal = new JMenuItem("Hexadezimal");
        volumes = new JMenu("Volumen ");
        cone = new JMenuItem("Kegel");
        square = new JMenuItem("Quader");
        cylinder = new JMenuItem("Zylinder");
        sphere = new JMenuItem("Kugel");
        // Erstellung des Obermenues: Listener hinzufuegen
        calculatorItem.addActionListener(processMenuInput);
        binary.addActionListener(processMenuInput);
        decimal.addActionListener(processMenuInput);
        hexadecimal.addActionListener(processMenuInput);
        cone.addActionListener(processMenuInput);
        square.addActionListener(processMenuInput);
        cylinder.addActionListener(processMenuInput);
        sphere.addActionListener(processMenuInput);
        // Erstellung des Obermenues: Hinzufuegen der Eintraege in das Menue
        calculator.add(calculatorItem);
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
        // Hinzufuegen des Reiters in RootPane (das "Fenster")
        this.getRootPane().setJMenuBar(bar);
        // Fenster als dynamisch skalierbar definieren
        pack();
        // Anfangsgroesse setzen
        setSize(600,300);
        // Sichtbar und fokussierbar setzen
        setVisible(true);
        setFocusable(true);
    }

    /**
     * Methode, um das Calculator-Unterfenster im Hauptfenster anzuzeigen.
     */
    public void SetCalculator() {
        // Listener fuer Calculator aktivieren
        processKeyInput.Activate();
        // Hinzufuegen
        getContentPane().removeAll();
        getContentPane().add(viewCalculator);
        // Voraussetzungen fuer Tastatureingabe
        requestFocus();
        // Update der Oberflaeche
        revalidate();
        repaint();
    }

    /**
     * Methode, um das Calculator-Unterfenster im Hauptfenster anzuzeigen.
     */
    public void SetNumeralSystem() {
        // Listener fuer Calculator deaktivieren
        processKeyInput.Deactivate();
        // Hinzufuegen
        getContentPane().removeAll();
        getContentPane().add(viewNumeralSystem);
        // Update der Oberflaeche
        revalidate();
        repaint();
    }

    /**
     * Methode, um das Calculator-Unterfenster im Hauptfenster anzuzeigen.
     */
    public void SetVolumeSquare() {
        // Listener fuer Calculator deaktivieren
        processKeyInput.Deactivate();
        // Hinzufuegen
        getContentPane().removeAll();
        getContentPane().add(viewVolumeSquare);
        // Update der Oberflaeche
        revalidate();
        repaint();
    }

    /**
     * Methode, um den hellen Modus zu aktivieren.
     */
    public void SetLightmode() {
        // Im Model setzen
        model.SetLightmode();
        // Menue setzen
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
        // Update der Oberflaeche
        repaint();
    }

    /**
     * Methode, um den dunklen Modus zu aktivieren.
     */
    public void SetDarkmode() {
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
        // Update der Oberflaeche
        repaint();
    }

    /**
     * Methode, um Updates aus der Instanz von "ProcessKeyInput" an die View weiterzugeben
     * @param s String mit Updatecode
     */
    public void Update(String s) {
        viewCalculator.Update(s);
    }

    /**
     * Methode, um den UpdateView-Befehl an die ViewCalculator-Instanz weiterzugeben.
     */
    public void UpdateView() {
        viewCalculator.UpdateView();
        // Fokus fuer Tastatureingabe anfordern
        requestFocus();
    }

    /**
     * Getter-Methode fuer das JMenuItem calculatorItem.
     * @return JMenuItem calculatorItem
     */
    protected JMenuItem GetJMenuItemCalculator() {
        return calculatorItem;
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
    protected JMenuItem GetJMenuItemHexadecimal() {
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
        // Instanziierung der Unterfenster
        viewCalculator = new ViewCalculator(model, this, controller);
        viewNumeralSystem = new ViewNumeralSystem();
        viewVolumeSquare = new ViewVolumeSquare();
        // Fenster auf Taschenrechner setzen
        SetCalculator();
        // ComponentListener, damit viewCalculator auf Groessenaenderungen reagieren kann
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
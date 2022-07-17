package Calculator.view.main;

// Java-Imports
import javax.swing.*;
import java.awt.*;
// Imports anderer Programmklassen
import Calculator.controller.Controller;
import Calculator.model.Model;
import Calculator.view.currency.ViewCurrency;
import Calculator.view.miscellaneous.ViewSettings;
import Calculator.view.miscellaneous.ViewHelp;
import Calculator.view.volume.ViewCone;
import Calculator.view.volume.ViewCuboid;
import Calculator.view.numeralSystem.ViewNumeralSystem;
import Calculator.view.volume.ViewCylinder;
import Calculator.view.volume.ViewSphere;
import Calculator.view.calculator.ViewCalculator;

/**
 * View-Klasse, die die Hauptoberflaeche bzw. das Rahmenfenster inklusive Menue verwaltet.
 */
public class View extends JFrame {
    // Referenzen auf Instanzen anderer Klassen
    private Model model;
    private Controller controller;
    // Instanz der Beiklasse "ProcessMenuInput", um auf Menuedrucke zu reagieren
    private ProcessMenuInput processMenuInput;
    // Instanz der Beiklasse "ProcessKeyInput", um ViewCalculator-Instanz die Tastatureingaben zu uebergeben
    private ProcessKeyInput processKeyInput;
    // Menue mit Reitern
    private javax.swing.JMenuBar bar = new javax.swing.JMenuBar();
    private javax.swing.JMenu miscellaneous = new javax.swing.JMenu();
    private javax.swing.JMenu calculator = new javax.swing.JMenu();
    private javax.swing.JMenu numeralSystem = new javax.swing.JMenu();
    private javax.swing.JMenu volumes = new javax.swing.JMenu();
    private javax.swing.JMenu currency = new javax.swing.JMenu();
    private javax.swing.JMenuItem settingsItem = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem helpItem = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem calculatorItem = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem numeralSystemItem = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem cone = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem cuboid = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem cylinder = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem sphere = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem currencyItem = new javax.swing.JMenuItem();
    // Instanzen der Unterfenster
    private ViewSettings viewSettings;
    private ViewHelp viewHelp;
    private ViewCalculator viewCalculator;
    private ViewNumeralSystem viewNumeralSystem;
    private ViewCone viewCone;
    private ViewCuboid viewCuboid;
    private ViewCylinder viewCylinder;
    private ViewSphere viewSphere;
    private ViewCurrency viewCurrency;
    // Darkmode-Zustand
    boolean darkmodeActive;

    /**
     * Konstruktor: Initialisierung der Reiter und Instanzen der Unterfenster sowie Menueinteraktionsverarbeitung.
     * Soweit noetig, manche Variablen setzen.
     */
    public View() {
        // Bei Schliessen des Fensters Programm beenden
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Instanz der Beiklasse "ProcessButtonInput", um auf Knopfdrucke zu reagieren
        processMenuInput = new ProcessMenuInput(this);
        // Instanz der Beiklasse "ProcessKeyInput", um auf Tastatureingaben zu reagieren
        processKeyInput = new ProcessKeyInput(this);
        // Tastatureingaben ermoeglichen
        addKeyListener(processKeyInput);
        setFocusable(true);
        // Erstellung des Menues: Eintraege erstellen
        bar = new JMenuBar();
        miscellaneous = new JMenu("Allgemein");
        calculator = new JMenu("Rechner");
        numeralSystem = new JMenu("Zahlensysteme");
        currency = new JMenu("W\u00E4hrungen");
        settingsItem = new JMenuItem("Einstellungen");
        helpItem = new JMenuItem("Hilfe");
        calculatorItem = new JMenuItem("\u00D6ffnen");
        numeralSystemItem = new JMenuItem("\u00D6ffnen");
        volumes = new JMenu("Volumen ");
        cone = new JMenuItem("Kegel");
        cuboid = new JMenuItem("Quader");
        cylinder = new JMenuItem("Zylinder");
        sphere = new JMenuItem("Kugel");
        currencyItem = new JMenuItem("\u00D6ffnen");
        // Erstellung des Menues: Listener hinzufuegen
        settingsItem.addActionListener(processMenuInput);
        helpItem.addActionListener(processMenuInput);
        calculatorItem.addActionListener(processMenuInput);
        numeralSystemItem.addActionListener(processMenuInput);
        cone.addActionListener(processMenuInput);
        cuboid.addActionListener(processMenuInput);
        cylinder.addActionListener(processMenuInput);
        sphere.addActionListener(processMenuInput);
        currencyItem.addActionListener(processMenuInput);
        // Erstellung des Menues: Hinzufuegen der Eintraege in die JMenus
        miscellaneous.add(settingsItem);
        miscellaneous.add(helpItem);
        calculator.add(calculatorItem);
        numeralSystem.add(numeralSystemItem);
        volumes.add(cone);
        volumes.add(cuboid);
        volumes.add(cylinder);
        volumes.add(sphere);
        currency.add(currencyItem);
        // Erstellung des Menues: Hinzufuegen der JMenus in die Leiste
        bar.add(miscellaneous);
        bar.add(calculator);
        bar.add(numeralSystem);
        bar.add(volumes);
        bar.add(currency);
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
     * Methode, um das Einstellungs-Unterfenster im Hauptfenster anzuzeigen.
     */
    public void SetSettings() {
        // Listener fuer Calculator deaktivieren
        processKeyInput.Deactivate();
        // Hinzufuegen
        getContentPane().removeAll();
        getContentPane().add(viewSettings);
        // Update der Oberflaeche
        revalidate();
        repaint();
    }

    /**
     * Methode, um das Hilfe-Unterfenster im Hauptfenster anzuzeigen.
     */
    public void SetHelp() {
        // Listener fuer Calculator deaktivieren
        processKeyInput.Deactivate();
        // Hinzufuegen
        getContentPane().removeAll();
        getContentPane().add(viewHelp);
        // Update der Oberflaeche
        revalidate();
        repaint();
    }

    /**
     * Methode, um das Rechner-Unterfenster im Hauptfenster anzuzeigen.
     */
    public void SetCalculator() {
        // Listener fuer Calculator aktivieren
        processKeyInput.Activate();
        // Hinzufuegen
        getContentPane().removeAll();
        getContentPane().add(viewCalculator);
        // Voraussetzungen fuer Tastatureingabe
        UpdateFocus();
        // Update der Oberflaeche
        revalidate();
        repaint();
    }

    /**
     * Methode, um das Zahlensystems-Unterfenster im Hauptfenster anzuzeigen.
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
     * Methode, um eines der Volumen-Unterfenster im Hauptfenster anzuzeigen.
     */
    public void SetVolume(int nummer) {
        // Listener fuer Calculator deaktivieren
        processKeyInput.Deactivate();
        // Hinzufuegen
        getContentPane().removeAll();
        switch(nummer) {
            case 1:
                getContentPane().add(viewCone);
                break;
            case 2:
                getContentPane().add(viewCuboid);
                break;
            case 3:
                getContentPane().add(viewCylinder);
                break;
            case 4:
                getContentPane().add(viewSphere);
                break;
        }
        // Update der Oberflaeche
        revalidate();
        repaint();
    }

    /**
     * Methode, um das Waehrungs-Unterfenster im Hauptfenster anzuzeigen.
     */
    public void SetCurrency() {
        // Listener fuer Calculator deaktivieren
        processKeyInput.Deactivate();
        // Hinzufuegen
        getContentPane().removeAll();
        getContentPane().add(viewCurrency);
        // Update der Oberflaeche
        revalidate();
        repaint();
    }

    /**
     * Methode, um den Fokus auf die Tastatureingabe zu schalten.
     */
    public void UpdateFocus() {
        requestFocus();
    }

    /**
     * Methode, um den UpdateView-Befehl an die ViewHelp-Instanz weiterzugeben.
     */
    public void UpdateViewHelp() {
        viewHelp.UpdateView();
    }

    /**
     * Methode, um Updates aus der Instanz von "ProcessKeyInput" an die View weiterzugeben
     * @param s String mit Updatecode
     */
    public void UpdateCalculatorExpression(String s) {
        viewCalculator.Update(s);
    }

    /**
     * Methode, um den UpdateView-Befehl an die ViewCalculator-Instanz weiterzugeben.
     */
    public void UpdateViewCalculator() {
        viewCalculator.UpdateView();
    }

    /**
     * Methode, um das Ergebnis einer der vier Volumenberechnungen im Fenster zu setzen.
     * @param number Nummer der angesteuerten ViewVolume-Instanz
     * @param output Ergebnis als String
     */
    public void VolumeOutput(int number, String output) {
        switch (number) {
            case 1:
                viewCone.VolumeOutput(output);
                break;
            case 2:
                viewCuboid.VolumeOutput(output);
                break;
            case 3:
                viewCylinder.VolumeOutput(output);
                break;
            case 4:
                viewSphere.VolumeOutput(output);
                break;
        }
    }

    /**
     * Methode, um von einer in eine andere Waehrung umzuwandeln.
     * Passiert im Controller.
     * @param input Eingabewert als double
     * @param inputCurrency Eingabewaehrung als dreistelliger Code
     * @param outputCurrency Ausgabewaehrung als dreistelliger Code
     * @return Ausgabewert als Double
     */
    public Double ConvertCurrency(double input, String inputCurrency, String outputCurrency) {
        return controller.ConvertCurrency(input, inputCurrency, outputCurrency);
    }

    /**
     * Getter-Methode fuer das JMenuItem settingsItem.
     * @return JMenuItem settingsItem
     */
    protected JMenuItem GetJMenuItemSettings() {
        return settingsItem;
    }

    /**
     * Getter-Methode fuer das JMenuItem helpItem.
     * @return JMenuItem helpItem
     */
    protected JMenuItem GetJMenuItemHelp() {
        return helpItem;
    }

    /**
     * Getter-Methode fuer das JMenuItem calculatorItem.
     * @return JMenuItem calculatorItem
     */
    protected JMenuItem GetJMenuItemCalculator() {
        return calculatorItem;
    }

    /**
     * Getter-Methode fuer das JMenuItem OpenNumeralSystem.
     * @return JMenuItem OpenNumeralSystem
     */
    protected JMenuItem GetJMenuItemOpenNumeralSystem() {
        return numeralSystemItem;
    }

    /**
     * Getter-Methode fuer das JMenuItem cone.
     * @return JMenuItem cone
     */
    protected JMenuItem GetJMenuItemCone() {
        return cone;
    }

    /**
     * Getter-Methode fuer den Radius der Kegel-Volumenberechnung.
     * @return String Radius
     */
    public String GetConeRadius() {
        return viewCone.GetTextRadius();
    }

    /**
     * Getter-Methode fuer die Hoehe der Kegel-Volumenberechnung.
     * @return String Hoehe
     */
    public String GetConeHeight() {
        return viewCone.GetTextHeight();
    }

    /**
     * Getter-Methode fuer das JMenuItem cuboid.
     * @return JMenuItem cuboid
     */
    protected JMenuItem GetJMenuItemCuboid() {
        return cuboid;
    }

    /**
     * Getter-Methode fuer die Laenge der Quader-Volumenberechnung.
     * @return String Laenge
     */
    public String GetCuboidLength() {
        return viewCuboid.GetTextLength();
    }

    /**
     * Getter-Methode fuer die Breite der Quader-Volumenberechnung.
     * @return String Breite
     */
    public String GetCuboidWidth() {
        return viewCuboid.GetTextWidth();
    }

    /**
     * Getter-Methode fuer die Hoehe der Quader-Volumenberechnung.
     * @return String Hoehe
     */
    public String GetCuboidHeight() {
        return viewCuboid.GetTextHeight();
    }

    /**
     * Getter-Methode fuer das JMenuItem cylinder.
     * @return JMenuItem cylinder
     */
    protected JMenuItem GetJMenuItemCylinder() {
        return cylinder;
    }

    /**
     * Getter-Methode fuer den Radius der Zylinder-Volumenberechnung.
     * @return String Radius
     */
    public String GetCylinderRadius() {
        return viewCylinder.GetTextRadius();
    }

    /**
     * Getter-Methode fuer die Hoehe der Zylinder-Volumenberechnung.
     * @return String Hoehe
     */
    public String GetCylinderHeight() {
        return viewCylinder.GetTextHeight();
    }

    /**
     * Getter-Methode fuer das JMenuItem sphere.
     * @return JMenuItem sphere
     */
    protected JMenuItem GetJMenuItemSphere() {
        return sphere;
    }

    /**
     * Getter-Methode fuer den Radius der Kugel-Volumenberechnung.
     * @return String Radius
     */
    public String GetSphereRadius() {
        return viewSphere.GetTextRadius();
    }

    /**
     * Getter-Methode fuer das JMenuItem currencyItem.
     * @return JMenuItem currencyItem
     */
    protected JMenuItem GetJMenuItemCurrency() {
        return currencyItem;
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
     * Methode, um den hellen Modus zu aktivieren.
     */
    public void SetLightmode() {
        // Im Model setzen
        model.SetLightmode();
        // Menue setzen
        bar.setForeground(Color.black);
        bar.setBackground(Color.white);
        miscellaneous.setForeground(Color.black);
        miscellaneous.setBackground(Color.white);
        settingsItem.setForeground(Color.black);
        settingsItem.setBackground(Color.white);
        helpItem.setForeground(Color.black);
        helpItem.setBackground(Color.white);
        calculator.setForeground(Color.black);
        calculator.setBackground(Color.white);
        calculatorItem.setForeground(Color.black);
        calculatorItem.setBackground(Color.white);
        numeralSystem.setForeground(Color.black);
        numeralSystem.setBackground(Color.white);
        numeralSystemItem.setForeground(Color.black);
        numeralSystemItem.setBackground(Color.white);
        volumes.setForeground(Color.black);
        volumes.setBackground(Color.white);
        cone.setForeground(Color.black);
        cone.setBackground(Color.white);
        cuboid.setForeground(Color.black);
        cuboid.setBackground(Color.white);
        cylinder.setForeground(Color.black);
        cylinder.setBackground(Color.white);
        sphere.setForeground(Color.black);
        sphere.setBackground(Color.white);
        currency.setForeground(Color.black);
        currency.setBackground(Color.white);
        currencyItem.setForeground(Color.black);
        currencyItem.setBackground(Color.white);
        // In Unterfenstern setzen
        viewSettings.SetLightmode();
        viewHelp.SetLightmode();
        viewCalculator.SetLightmode();
        viewNumeralSystem.SetLightmode();
        viewCone.SetLightmode();
        viewCuboid.SetLightmode();
        viewCylinder.SetLightmode();
        viewSphere.SetLightmode();
        viewCurrency.SetLightmode();
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
        miscellaneous.setForeground(Color.white);
        miscellaneous.setBackground(Color.black);
        settingsItem.setForeground(Color.white);
        settingsItem.setBackground(Color.black);
        helpItem.setForeground(Color.white);
        helpItem.setBackground(Color.black);
        calculator.setForeground(Color.white);
        calculator.setBackground(Color.black);
        calculatorItem.setForeground(Color.white);
        calculatorItem.setBackground(Color.black);
        numeralSystem.setForeground(Color.white);
        numeralSystem.setBackground(Color.black);
        numeralSystemItem.setForeground(Color.white);
        numeralSystemItem.setBackground(Color.black);
        volumes.setForeground(Color.white);
        volumes.setBackground(Color.black);
        cone.setForeground(Color.white);
        cone.setBackground(Color.black);
        cuboid.setForeground(Color.white);
        cuboid.setBackground(Color.black);
        cylinder.setForeground(Color.white);
        cylinder.setBackground(Color.black);
        sphere.setForeground(Color.white);
        sphere.setBackground(Color.black);
        currency.setForeground(Color.white);
        currency.setBackground(Color.black);
        currencyItem.setForeground(Color.white);
        currencyItem.setBackground(Color.black);
        // In Unterfenstern setzen
        viewSettings.SetDarkmode();
        viewHelp.SetDarkmode();
        viewCalculator.SetDarkmode();
        viewNumeralSystem.SetDarkmode();
        viewCone.SetDarkmode();
        viewCuboid.SetDarkmode();
        viewCylinder.SetDarkmode();
        viewSphere.SetDarkmode();
        viewCurrency.SetDarkmode();
        // Update der Oberflaeche
        repaint();
    }

    /**
     * Methode, um die Darkmode-Einstellung permanent ueber das Model zu setzen.
     * @param active Wahrheitswert, ob Darkmode aktiv
     */
    public void SaveDarkmode(boolean active) {
        model.SaveDarkmode(active);
    }

    /**
     * Methode zur Setzung der Referenzen auf Model und Controller in Main.
     * Zudem Methoden, die eigentlich im Konstruktor waeren, jedoch Referenzen auf andere Klassen benoetigen und daher
     * erst hier ausgefuehrt werden koennen.
     * @param m Model-Instanz
     * @param c Controller-Instanz
     */
    public void UpdateLinks(Model m, Controller c) {
        // Referenzen setzen
        model = m;
        controller = c;
        // Instanziierung der Unterfenster
        viewSettings = new ViewSettings(this);
        viewHelp = new ViewHelp();
        viewCalculator = new ViewCalculator(model, this, controller);
        viewNumeralSystem = new ViewNumeralSystem(controller);
        viewCone = new ViewCone(controller);
        viewCuboid = new ViewCuboid(controller);
        viewCylinder = new ViewCylinder(controller);
        viewSphere = new ViewSphere(controller);
        viewCurrency = new ViewCurrency(this);
        // Fenster auf Taschenrechner setzen
        SetCalculator();
        // Darstellungsmodus aus Datei auslesen und setzen
        darkmodeActive = model.ExtractDarkmode();
        if (darkmodeActive) {
            viewSettings.ActivateDarkmodeSetting();
        }
        else if (!darkmodeActive) {
            viewSettings.DeactivateDarkmodeSetting();
        }
        else {
            // Falls keine Einstellungen gefunden
            viewSettings.DeactivateDarkmodeSetting();
        }
        // Instanz der Beiklasse ProcessResize, damit viewCalculator auf Groessenaenderungen reagieren kann
        addComponentListener(new ProcessResize(this));
    }
}
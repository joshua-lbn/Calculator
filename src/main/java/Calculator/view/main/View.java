package Calculator.view.main;

import Calculator.controller.Controller;
import Calculator.model.Model;
import Calculator.view.viewGeneral.ViewHelp;
import Calculator.view.volumeCone;
import Calculator.view.viewGeneral.ViewSettings;
import Calculator.view.volumeCuboid;
import Calculator.view.volumeCylinder;
import Calculator.view.volumeSphere;
import Calculator.view.calculator.ViewCalculator;
import Calculator.view.ViewNumeralSystem;
import Calculator.view.viewCurrency.ViewCurrency;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
    private javax.swing.JMenu general = new javax.swing.JMenu();
    private javax.swing.JMenu calculator = new javax.swing.JMenu();
    private javax.swing.JMenu numeralSystems = new javax.swing.JMenu();
    private javax.swing.JMenu volumes = new javax.swing.JMenu();
    private javax.swing.JMenu currency = new javax.swing.JMenu();
    private javax.swing.JMenuItem settingsItem = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem helpItem = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem calculatorItem = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem binary = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem decimal = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem hexadecimal = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem cone = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem cuboid = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem cylinder = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem sphere = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem currencyItem = new javax.swing.JMenuItem();
    // Unterfenster
    private ViewSettings viewSettings;
    private ViewHelp viewHelp;
    private ViewCalculator viewCalculator;
    private ViewNumeralSystem viewNumeralSystem;
    private volumeCone volumeCone;
    private volumeCuboid volumeCuboid;
    private volumeCylinder volumeCylinder;
    private volumeSphere volumeSphere;
    private ViewCurrency viewCurrency;

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
        general = new JMenu("Allgemein");
        calculator = new JMenu("Rechner");
        numeralSystems = new JMenu("Zahlensystem ");
        currency = new JMenu("W\u00E4hrungen");
        settingsItem = new JMenuItem("Einstellungen");
        helpItem = new JMenuItem("Hilfe");
        calculatorItem = new JMenuItem("\u00D6ffnen");
        binary = new JMenuItem("Bin\u00E4r");
        decimal = new JMenuItem("Dezimal");
        hexadecimal = new JMenuItem("Hexadezimal");
        volumes = new JMenu("Volumen ");
        cone = new JMenuItem("Kegel");
        cuboid = new JMenuItem("Quader");
        cylinder = new JMenuItem("Zylinder");
        sphere = new JMenuItem("Kugel");
        currencyItem = new JMenuItem("\u00D6ffnen");
        // Erstellung des Obermenues: Listener hinzufuegen
        settingsItem.addActionListener(processMenuInput);
        helpItem.addActionListener(processMenuInput);
        calculatorItem.addActionListener(processMenuInput);
        binary.addActionListener(processMenuInput);
        decimal.addActionListener(processMenuInput);
        hexadecimal.addActionListener(processMenuInput);
        cone.addActionListener(processMenuInput);
        cuboid.addActionListener(processMenuInput);
        cylinder.addActionListener(processMenuInput);
        sphere.addActionListener(processMenuInput);
        currencyItem.addActionListener(processMenuInput);
        // Erstellung des Obermenues: Hinzufuegen der Eintraege in die JMenus
        general.add(settingsItem);
        general.add(helpItem);
        calculator.add(calculatorItem);
        numeralSystems.add(decimal);
        numeralSystems.add(binary);
        numeralSystems.add(hexadecimal);
        volumes.add(cone);
        volumes.add(cuboid);
        volumes.add(cylinder);
        volumes.add(sphere);
        currency.add(currencyItem);
        // Erstellung des Obermenues: Hinzufuegen der JMenus in die Leiste
        bar.add(general);
        bar.add(calculator);
        bar.add(numeralSystems);
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
     * Methode, um das SettingsItem-Unterfenster im Hauptfenster anzuzeigen.
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
     * Methode, um das HelpItem-Unterfenster im Hauptfenster anzuzeigen.
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
     * Methode, um das Calculator-Unterfenster im Hauptfenster anzuzeigen.
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
     * Methode, um das NumeralSystem-Unterfenster im Hauptfenster anzuzeigen.
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
     * Methode, um das VolumeCuboid-Unterfenster im Hauptfenster anzuzeigen.
     */
    public void SetVolume(int nummer) {
        // Listener fuer Calculator deaktivieren
        processKeyInput.Deactivate();
        // Hinzufuegen
        getContentPane().removeAll();
        switch(nummer) {
            case 1:
                getContentPane().add(volumeCone);
                break;
            case 2:
                getContentPane().add(volumeCuboid);
                break;
            case 3:
                getContentPane().add(volumeCylinder);
                break;
            case 4:
                getContentPane().add(volumeSphere);
                break;

        }
        // Update der Oberflaeche
        revalidate();
        repaint();
    }

    /**
     * Methode, um das CurrencyItem-Unterfenster im Hauptfenster anzuzeigen.
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
     * Methode, um den hellen Modus zu aktivieren.
     */
    public void SetLightmode() {
        // Im Model setzen
        model.SetLightmode();
        // Menue setzen
        bar.setForeground(Color.black);
        bar.setBackground(Color.white);
        general.setForeground(Color.black);
        general.setBackground(Color.white);
        settingsItem.setForeground(Color.black);
        settingsItem.setBackground(Color.white);
        helpItem.setForeground(Color.black);
        helpItem.setBackground(Color.white);
        calculator.setForeground(Color.black);
        calculator.setBackground(Color.white);
        calculatorItem.setForeground(Color.black);
        calculatorItem.setBackground(Color.white);
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
        volumeCone.SetLightmode();
        volumeCuboid.SetLightmode();
        volumeCylinder.SetLightmode();
        volumeSphere.SetLightmode();
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
        general.setForeground(Color.white);
        general.setBackground(Color.black);
        settingsItem.setForeground(Color.white);
        settingsItem.setBackground(Color.black);
        helpItem.setForeground(Color.white);
        helpItem.setBackground(Color.black);
        calculator.setForeground(Color.white);
        calculator.setBackground(Color.black);
        calculatorItem.setForeground(Color.white);
        calculatorItem.setBackground(Color.black);
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
        volumeCone.SetDarkmode();
        volumeCuboid.SetDarkmode();
        volumeCylinder.SetDarkmode();
        volumeSphere.SetDarkmode();
        viewCurrency.SetDarkmode();
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
    }

    /**
     * Methode, um den Fokus auf die Tastatureingabe zu schalten.
     */
    public void UpdateFocus() {
        requestFocus();
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
     * Getter-Methode fuer das JMenuItem cuboid.
     * @return JMenuItem cuboid
     */
    protected JMenuItem GetJMenuItemCuboid() {
        return cuboid;
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
        viewSettings = new ViewSettings(this);
        viewHelp = new ViewHelp();
        viewCalculator = new ViewCalculator(model, this, controller);
        viewNumeralSystem = new ViewNumeralSystem();
        volumeCone = new volumeCone();
        volumeCuboid = new volumeCuboid();
        volumeCylinder = new volumeCylinder();
        volumeSphere = new volumeSphere();
        viewCurrency = new ViewCurrency();
        // Fenster auf Taschenrechner setzen
        SetCalculator();
        // Darstellungsmodus aus Datei auslesen und setzen
        String data = "";
        try {
            File file = new File(System.getenv("APPDATA") + "\\Calculator\\settings.txt");
            Scanner scanner = new Scanner(file);
            data = scanner.nextLine();
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        if (data.equals("Darkmode: on")) {
            viewSettings.ActivatedDarkmodeSetting();
        }
        else if (data.equals("Darkmode: off")) {
            viewSettings.DeactivatedDarkmodeSetting();
        }
        else {
            // Falls keine Einstellungen gefunden
            viewSettings.DeactivatedDarkmodeSetting();
        }
        // Instanz der Beiklasse ProcessResize, damit viewCalculator auf Groessenaenderungen reagieren kann
        addComponentListener(new ProcessResize(this));
    }
}
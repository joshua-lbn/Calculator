package Calculator.view.main;

// Java-Imports
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Klasse mit Listener, um auf Groessenaenderungen des Fensters zu reagieren.
 * Programmtechnisch an die View-Klasse gegliedert.
 */
public class ProcessResize implements ComponentListener {
    // Referenz auf die View-Instanz
    private View view;

    /**
     * Konstruktor: View-Instanz setzen.
     * @param v View-Instanz
     */
    public ProcessResize(View v) {
        view = v;
    }

    /**
     * Methode, um bei Groessenaenderung ein Update der Oberflaeche anzustossen.
     * @param e Ereignis bzw. "Event", das verarbeitet werden soll
     */
    @Override
    public void componentResized(ComponentEvent e) {
        // Taschenrechner-Oberflaeche updaten, da dort die Schriftgroesse geaendert werden muss
        view.UpdateCalculator();
        // Hilfe-Oberflaeche updaten, da dort die Textskalierung geaendert werden muss
        view.UpdateHelp();
    }

    // Weitere Methoden, um Implementierung zu vervollstaendigen, welche jedoch nicht genutzt werden
    @Override
    public void componentMoved(ComponentEvent e) {}
    @Override
    public void componentShown(ComponentEvent e) {}
    @Override
    public void componentHidden(ComponentEvent e) {}
}

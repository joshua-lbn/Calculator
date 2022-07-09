package Calculator.view.main;

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
     * Es wird die ViewCalculator- und ViewHelp-Instanz aktualisiert.
     * @param e Ereignis bzw. "Event", das verarbeitet werden soll
     */
    @Override
    public void componentResized(ComponentEvent e) {
        view.UpdateCalculator();
        view.UpdateHelp();
    }

    // Weitere ungenutzte Methoden, um Implementierung zu vervollstaendigen
    @Override
    public void componentMoved(ComponentEvent e) {
    }
    @Override
    public void componentShown(ComponentEvent e) {
    }
    @Override
    public void componentHidden(ComponentEvent e) {
    }

}

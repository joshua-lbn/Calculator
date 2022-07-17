package Calculator.view.main;

// Java-Imports
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * ProcessKeyInput-Klasse zum Auswerten der Interaktionen mit der grafischen Taschenrechneroberflaeche
 * ueber die Tastatur.
 * Programmtechnisch de-facto an die View-Klasse gegliedert, theoretisch jedoch der ViewCalculator zugehoerig.
 */
public class ProcessKeyInput implements KeyListener {
    // Referenz auf die View-Instanz
    private View view;
    // Boolean, ob Listener aktiv (bei Auswahl anderer Unterfenster deaktivieren)
    boolean active;

    /**
     * Konstruktor: View-Referenz speichern und Listener deaktivieren.
     * @param v Referenz auf View
     */
    public ProcessKeyInput(View v) {
        view = v;
        active = false;
    }

    /**
     * Methode, die bei Tastaturtastendruck aufgerufen wird und diesen an die View weitergibt.
     * @param e "Event" bzw. Ereignis des Tastaturtastendruckes
     */
    public void keyPressed(KeyEvent e) {
        if (active) {
            if (e.isShiftDown() && (e.getKeyCode() == KeyEvent.VK_7 || e.getKeyCode() == KeyEvent.VK_PERIOD)) {
                // Eingabe an Controller (ueber View) leiten
                view.UpdateCalculatorExpression("\u00F7");
                // Anzeige des Calculator-Unterfensters ueber View aktualisieren
                view.UpdateViewCalculator();
            } else if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_8) {
                view.UpdateCalculatorExpression("(");
                view.UpdateViewCalculator();
            } else if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_9) {
                view.UpdateCalculatorExpression(")");
                view.UpdateViewCalculator();
            } else if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_PLUS) {
                view.UpdateCalculatorExpression("\u00D7");
                view.UpdateViewCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_PLUS) {
                view.UpdateCalculatorExpression("\u002B");
                view.UpdateViewCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_MINUS) {
                view.UpdateCalculatorExpression("\u2212");
                view.UpdateViewCalculator();
            } else if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_0) {
                view.UpdateCalculatorExpression("\u003D");
                view.UpdateViewCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                view.UpdateCalculatorExpression("\u2192");
                view.UpdateViewCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                view.UpdateCalculatorExpression("\u2190");
                view.UpdateViewCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_0 || e.getKeyCode() == KeyEvent.VK_NUMPAD0) {
                view.UpdateCalculatorExpression("" + 0 + "");
                view.UpdateViewCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_1 || e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
                view.UpdateCalculatorExpression("" + 1 + "");
                view.UpdateViewCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_2 || e.getKeyCode() == KeyEvent.VK_NUMPAD2) {
                view.UpdateCalculatorExpression("" + 2 + "");
                view.UpdateViewCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_3 || e.getKeyCode() == KeyEvent.VK_NUMPAD3) {
                view.UpdateCalculatorExpression("" + 3 + "");
                view.UpdateViewCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_4 || e.getKeyCode() == KeyEvent.VK_NUMPAD4) {
                view.UpdateCalculatorExpression("" + 4 + "");
                view.UpdateViewCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_5 || e.getKeyCode() == KeyEvent.VK_NUMPAD5) {
                view.UpdateCalculatorExpression("" + 5 + "");
                view.UpdateViewCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_6 || e.getKeyCode() == KeyEvent.VK_NUMPAD6) {
                view.UpdateCalculatorExpression("" + 6 + "");
                view.UpdateViewCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_7 || e.getKeyCode() == KeyEvent.VK_NUMPAD7) {
                view.UpdateCalculatorExpression("" + 7 + "");
                view.UpdateViewCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_8 || e.getKeyCode() == KeyEvent.VK_NUMPAD8) {
                view.UpdateCalculatorExpression("" + 8 + "");
                view.UpdateViewCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_9 || e.getKeyCode() == KeyEvent.VK_NUMPAD9) {
                view.UpdateCalculatorExpression("" + 9 + "");
                view.UpdateViewCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_S) {
                view.UpdateCalculatorExpression("sin");
                view.UpdateViewCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_C) {
                view.UpdateCalculatorExpression("cos");
                view.UpdateViewCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_T) {
                view.UpdateCalculatorExpression("tan");
                view.UpdateViewCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_L) {
                view.UpdateCalculatorExpression("\u33D2");
                view.UpdateViewCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                view.UpdateCalculatorExpression("DEL");
                view.UpdateViewCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                view.UpdateCalculatorExpression("AC");
                view.UpdateViewCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                view.UpdateCalculatorExpression("=");
                view.UpdateViewCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_DEAD_CIRCUMFLEX) {
                view.UpdateCalculatorExpression("\u207F");
                view.UpdateViewCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_COMMA || e.getKeyCode() == KeyEvent.VK_PERIOD) {
                view.UpdateCalculatorExpression("\u002C");
                view.UpdateViewCalculator();
            } else if(e.getKeyCode() == KeyEvent.VK_A) {
                view.UpdateCalculatorExpression("Ans");
                view.UpdateViewCalculator();
            } else if(e.getKeyCode() == KeyEvent.VK_B) {
                view.SetLightmode();
            } else if(e.getKeyCode() == KeyEvent.VK_D) {
                view.SetDarkmode();
            } else if(e.getKeyCode() == KeyEvent.VK_P) {
                view.UpdateCalculatorExpression("\u03C0");
                view.UpdateViewCalculator();
            } else if(e.getKeyCode() == KeyEvent.VK_E) {
                view.UpdateCalculatorExpression("\u212F");
                view.UpdateViewCalculator();
            } else if(e.getKeyCode() == KeyEvent.VK_G) {
                view.UpdateCalculatorExpression("\u03D5");
                view.UpdateViewCalculator();
            } else if(e.getKeyCode() == KeyEvent.VK_W) {
                view.UpdateCalculatorExpression("\u221A");
                view.UpdateViewCalculator();
            }
        }
    }

    /**
     * Methode, um den Listener zu aktivieren.
     * Hilfreich, wenn der Calculator aktiv ist und empfangen soll.
     */
    public void Activate()
    {
        active = true;
    }

    /**
     * Methode, um den Listener zu pausieren.
     * Hilfreich, wenn andere Unterfenster aktiv sind und zufaellige Eingaben nicht verarbeitet werden sollen.
     */
    public void Deactivate()
    {
        active = false;
    }

    // Vervollstaendigung der Implementierung durch Methoden, welche jedoch nicht benoetigt werden
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}

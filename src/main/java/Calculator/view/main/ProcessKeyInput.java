package Calculator.view.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * ProcessKeyInput-Klasse zum Auswerten der Interaktionen mit der grafischen Taschenrechneroberflaeche
 * ueber die Tastatur.
 * Programmtechnisch an die View-Klasse gegliedert.
 */
public class ProcessKeyInput implements KeyListener {
    private View view;
    // Boolean, ob Listener aktiv
    boolean active;

    /**
     * Konstruktor: View-Referenz speichern.
     * @param v Referenz auf View
     */
    public ProcessKeyInput(View v) {
        view = v;
        // Sicherheitshalber deaktiveren
        active = false;
    }

    /**
     * Methode, um die bei Tastaturtastendruck aufgerufen wird und diesen an die View weitergibt.
     * @param e "Event" bzw. Ereignis des Tastendruckes
     */
    public void keyPressed(KeyEvent e) {
        if (active) {
            if (e.isShiftDown() && (e.getKeyCode() == KeyEvent.VK_7 || e.getKeyCode() ==KeyEvent.VK_PERIOD)) {
                view.UpdateExpression("/");
                view.UpdateCalculator();
            } else if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_8) {
                view.UpdateExpression("(");
                view.UpdateCalculator();
            } else if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_9) {
                view.UpdateExpression(")");
                view.UpdateCalculator();
            } else if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_PLUS) {
                view.UpdateExpression("*");
                view.UpdateCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_PLUS) {
                view.UpdateExpression("+");
                view.UpdateCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_MINUS) {
                view.UpdateExpression("-");
                view.UpdateCalculator();
            } else if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_0) {
                view.UpdateExpression("=");
                view.UpdateCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                view.UpdateExpression("->");
                view.UpdateCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                view.UpdateExpression("<-");
                view.UpdateCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_0 || e.getKeyCode() == KeyEvent.VK_NUMPAD0) {
                view.UpdateExpression("" + 0 + "");
                view.UpdateCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_1 || e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
                view.UpdateExpression("" + 1 + "");
                view.UpdateCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_2 || e.getKeyCode() == KeyEvent.VK_NUMPAD2) {
                view.UpdateExpression("" + 2 + "");
                view.UpdateCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_3 || e.getKeyCode() == KeyEvent.VK_NUMPAD3) {
                view.UpdateExpression("" + 3 + "");
                view.UpdateCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_4 || e.getKeyCode() == KeyEvent.VK_NUMPAD4) {
                view.UpdateExpression("" + 4 + "");
                view.UpdateCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_5 || e.getKeyCode() == KeyEvent.VK_NUMPAD5) {
                view.UpdateExpression("" + 5 + "");
                view.UpdateCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_6 || e.getKeyCode() == KeyEvent.VK_NUMPAD6) {
                view.UpdateExpression("" + 6 + "");
                view.UpdateCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_7 || e.getKeyCode() == KeyEvent.VK_NUMPAD7) {
                view.UpdateExpression("" + 7 + "");
                view.UpdateCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_8 || e.getKeyCode() == KeyEvent.VK_NUMPAD8) {
                view.UpdateExpression("" + 8 + "");
                view.UpdateCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_9 || e.getKeyCode() == KeyEvent.VK_NUMPAD9) {
                view.UpdateExpression("" + 9 + "");
                view.UpdateCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_S) {
                view.UpdateExpression("sin(");
                view.UpdateCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_C) {
                view.UpdateExpression("cos(");
                view.UpdateCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_T) {
                view.UpdateExpression("tan(");
                view.UpdateCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_L) {
                view.UpdateExpression("lg(");
                view.UpdateCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                view.UpdateExpression("DEL");
                view.UpdateCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                view.UpdateExpression("AC");
                view.UpdateCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                view.UpdateExpression("=");
                view.UpdateCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_DEAD_CIRCUMFLEX) {
                view.UpdateExpression("x^");
                view.UpdateCalculator();
            } else if (e.getKeyCode() == KeyEvent.VK_COMMA || e.getKeyCode() == KeyEvent.VK_PERIOD) {
                view.UpdateExpression(",");
                view.UpdateCalculator();
            } else if(e.getKeyCode() == KeyEvent.VK_A) {
                view.UpdateExpression("Ans");
                view.UpdateCalculator();
            } else if(e.getKeyCode() == KeyEvent.VK_B) {
                view.SetLightmode();
            } else if(e.getKeyCode() == KeyEvent.VK_D) {
                view.SetDarkmode();
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
     * Hilfreich, wenn andere Unterfenster aktiv sind.
     */
    public void Deactivate()
    {
        active = false;
    }

    // Vervollstaendigung der Implementierung, jedoch nicht benoetigt
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}

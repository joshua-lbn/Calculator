package Calculator.view.main;

import Calculator.view.calculator.ViewCalculator;

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
                view.Update("/");
                view.UpdateView();
            } else if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_8) {
                view.Update("(");
                view.UpdateView();
            } else if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_9) {
                view.Update(")");
                view.UpdateView();
            } else if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_PLUS) {
                view.Update("*");
                view.UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_PLUS) {
                view.Update("+");
                view.UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_MINUS) {
                view.Update("-");
                view.UpdateView();
            } else if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_0) {
                view.Update("=");
                view.UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                view.Update("->");
                view.UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                view.Update("<-");
                view.UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_0 || e.getKeyCode() == KeyEvent.VK_NUMPAD0) {
                view.Update("" + 0 + "");
                view.UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_1 || e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
                view.Update("" + 1 + "");
                view.UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_2 || e.getKeyCode() == KeyEvent.VK_NUMPAD2) {
                view.Update("" + 2 + "");
                view.UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_3 || e.getKeyCode() == KeyEvent.VK_NUMPAD3) {
                view.Update("" + 3 + "");
                view.UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_4 || e.getKeyCode() == KeyEvent.VK_NUMPAD4) {
                view.Update("" + 4 + "");
                view.UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_5 || e.getKeyCode() == KeyEvent.VK_NUMPAD5) {
                view.Update("" + 5 + "");
                view.UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_6 || e.getKeyCode() == KeyEvent.VK_NUMPAD6) {
                view.Update("" + 6 + "");
                view.UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_7 || e.getKeyCode() == KeyEvent.VK_NUMPAD7) {
                view.Update("" + 7 + "");
                view.UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_8 || e.getKeyCode() == KeyEvent.VK_NUMPAD8) {
                view.Update("" + 8 + "");
                view.UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_9 || e.getKeyCode() == KeyEvent.VK_NUMPAD9) {
                view.Update("" + 9 + "");
                view.UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_S) {
                view.Update("sin(");
                view.UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_C) {
                view.Update("cos(");
                view.UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_T) {
                view.Update("tan(");
                view.UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_L) {
                view.Update("lg(");
                view.UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                view.Update("DEL");
                view.UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                view.Update("AC");
                view.UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                view.Update("=");
                view.UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_DEAD_CIRCUMFLEX) {
                view.Update("x^");
                view.UpdateView();
            } else if (e.getKeyCode() == KeyEvent.VK_COMMA || e.getKeyCode() == KeyEvent.VK_PERIOD) {
                view.Update(",");
                view.UpdateView();
            } else if(e.getKeyCode() == KeyEvent.VK_A) {
                view.Update("Ans");
                view.UpdateView();
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

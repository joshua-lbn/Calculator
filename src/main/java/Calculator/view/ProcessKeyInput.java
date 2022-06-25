package Calculator.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * ProcessKeyInput-Klasse zum Auswerten der Interaktionen mit der grafischen Oberflaeche ueber die Tastatur.
 * Programmtechnisch an die View-Klasse gegliedert.
 */
public class ProcessKeyInput implements KeyListener {
    private View view;
    boolean shifttasteGedrueckt;

    public ProcessKeyInput(View v) {
        view = v;
    } //Methode in view nötig //in ProcessButtonInput

    /* KeyPressed prueft die gedrückten Tasten, Aufruf beim Druecken einer Taste,
    * diese werden dann an die view weitergegeben und ausgegeben
    * eventuell Wiederholung für 0 - 9, möglich?
     */
    public void keyPressed(KeyEvent e) {
        //Zunächst überprüfen, ob Shift-Taste gedrückt, dann setzen unseres Parameters @Shifttaste
        //auf true
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            shifttasteGedrueckt = true;
        }
        if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_7) {
            view.Update("/");
            view.UpdateView();
        } else if (shifttasteGedrueckt && e.getKeyCode() == KeyEvent.VK_8) {
            view.Update("(");
            view.UpdateView();
        } else if (shifttasteGedrueckt && e.getKeyCode() == KeyEvent.VK_9) {
            view.Update(")");
            view.UpdateView();
        } else if (shifttasteGedrueckt && e.getKeyCode() == KeyEvent.VK_PLUS) {
            view.Update("*");
            view.UpdateView();
        } else if (e.getKeyCode() == KeyEvent.VK_PLUS) {
            view.Update("+");
            view.UpdateView();
        } else if (e.getKeyCode() == KeyEvent.VK_MINUS) {
            view.Update("*");
            view.UpdateView();
        } else if (shifttasteGedrueckt && e.getKeyCode() == KeyEvent.VK_0) {
            view.Update("=");
            view.UpdateView();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            view.Update("->");
            view.UpdateView();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            view.Update("<-");
            view.UpdateView();
        }
        else if (e.getKeyCode() == KeyEvent.VK_0) {
            view.Update("" + 0 + "");
            view.UpdateView();
        } else if (e.getKeyCode() == KeyEvent.VK_1) {
            view.Update("" + 1 + "");
            view.UpdateView();
        } else if (e.getKeyCode() == KeyEvent.VK_2) {
            view.Update("" + 2 + "");
            view.UpdateView();
        } else if (e.getKeyCode() == KeyEvent.VK_3) {
            view.Update("" + 3 + "");
            view.UpdateView();
        } else if (e.getKeyCode() == KeyEvent.VK_4) {
            view.Update("" + 4 + "");
            view.UpdateView();
        } else if (e.getKeyCode() == KeyEvent.VK_5) {
            view.Update("" + 5 + "");
            view.UpdateView();
        } else if (e.getKeyCode() == KeyEvent.VK_6) {
            view.Update("" + 6 + "");
            view.UpdateView();
        } else if (e.getKeyCode() == KeyEvent.VK_7) {
            view.Update("" + 7 + "");
            view.UpdateView();
        } else if (e.getKeyCode() == KeyEvent.VK_8) {
            view.Update("" + 8 + "");
            view.UpdateView();
        } else if (e.getKeyCode() == KeyEvent.VK_9) {
            view.Update("" + 9 + "");
            view.UpdateView();
        }
        // Restliche Buttons
        else if (e.getKeyCode() == KeyEvent.VK_S) {
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
        }   else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            view.Update("=");
            view.UpdateView();
        }
        //Ab hier wird die Shift-Taste genutzt
        //NumPad
        else if (e.getKeyCode() == KeyEvent.VK_NUMPAD0) {
            view.Update("" + 0 + "");
            view.UpdateView();
        } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
            view.Update("" + 1 + "");
            view.UpdateView();
        } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD2) {
            view.Update("" + 2 + "");
            view.UpdateView();
        } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD3) {
            view.Update("" + 3 + "");
            view.UpdateView();
        } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD4) {
            view.Update("" + 4 + "");
        } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD5) {
            view.Update("" + 5 + "");
            view.UpdateView();
        } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD6) {
            view.Update("" + 6 + "");
            view.UpdateView();
        } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD7) {
            view.Update("" + 7 + "");
            view.UpdateView();
        } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD8) {
            view.Update("" + 8 + "");
            view.UpdateView();
        } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD9) {
            view.Update("" + 9 + "");
            view.UpdateView();
        } else if (e.getKeyCode() == KeyEvent.VK_CIRCUMFLEX) {
            view.Update("^x^");
            view.UpdateView();
        }
        //Exponentialrechnung?
        //auch mit OpenBrackets/Close möglich, aber erkennen möglich, dann ohne den Wahrheitswert
    }

    //wenn die Shift-Taste losgelassen wird
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            shifttasteGedrueckt = false;
        }
    }

    //nicht nötig
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            //shifttasteGedrueckt = !shifttasteGedrueckt;
        }
    }
}

/* ab hier löschen  public static void main(String[] main)
{
    //stellt einen KeyListener her und übergibt diesen dem (Fenster???)
    ProcessKeyInput p = new ProcessKeyInput();
    view.addKeyListener(p); //bei Frame, bei uns?
}
}
*/

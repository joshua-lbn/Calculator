package Calculator.view;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/*
 * ButtonListener-Klasse zum Auswerden der Interaktionen mit der grafischen Oberflaeche.
 * Programmtechnisch an die View-Klasse gegliedert.
 */
public class ButtonListener implements java.awt.event.ActionListener {
    private View view;
    // Relevante Attribute: Arrays mit Knopfobjekten und Beschriftungen
    private JButton[] jButtonsNumber;
    private JButton[] jButtonsLeft;
    private String[] textsLeft;
    private JButton[] jButtonsMiddle;
    private String[] textsMiddle;
    private JButton[] jButtonsRight;
    private String[] textsRight;
    boolean ShifttasteGedrückt;

    /**
     * Konstruktor, der die übergebene View-Instanz speichert und die lokalen Attribute aus der View ueber Getter erhaelt.
     *
     * @param v View-Instanz
     */
    public ButtonListener(View v) {
        // View speichern
        view = v;
        // Getter aufrufen
        jButtonsNumber = view.GetJButtonsNumber();
        jButtonsLeft = view.GetJButtonsLeft();
        textsLeft = view.GetTextsLeft();
        jButtonsMiddle = view.GetJButtonsMiddle();
        textsMiddle = view.GetTextsMiddle();
        jButtonsRight = view.GetJButtonsRight();
        textsRight = view.GetTextsRight();
        //ButtonListener Bl = new ButtonListener(view);
       // view.addKeyListener(this);



    }

    /**
     * Methode, welche bei Knopfdrucken aufgerufen wird.
     *
     * @param e Das "Event" bzw. Ereignis, welches verarbeitet werden soll
     */
    public void actionPerformed(java.awt.event.ActionEvent e) {
        /*
         * Alle JNumberButtons und im weiteren Verlauf die anderen Arrays werden geprueft.
         * Bei Uebereinstimmung des Eventursprungs mit einem Knopf wird:
         * der View der Befehl gegeben, die hinzugefuegten Zeichen an den Controller zu geben, sodass sie hinzugefuegt werden koennen.
         * der View der Befehl gegeben, nach Änderung der Darstellung (durch den Controller) diese zu aktualisieren.
         * die weitere Ausfuehrung der Methode aus Effizienzgruenden abgebrochen.
         */
        for (int i = 0; i < jButtonsNumber.length; i++) {
            if (e.getSource() == jButtonsNumber[i]) {
                view.Update("" + (i) + "");
                view.UpdateView();
                return;
            }
        }
        for (int i = 0; i < jButtonsLeft.length; i++) {
            if (e.getSource() == jButtonsLeft[i]) {
                view.Update(textsLeft[i]);
                view.UpdateView();
                return;
            }
        }
        for (int i = 0; i < jButtonsMiddle.length; i++) {
            if (e.getSource() == jButtonsMiddle[i]) {
                view.Update(textsMiddle[i]);
                view.UpdateView();
                return;
            }
        }
        for (int i = 0; i < jButtonsRight.length; i++) {
            if (e.getSource() == jButtonsRight[i]) {
                view.Update(textsRight[i]);
                view.UpdateView();
                return;
            }
        }
    }}
/*
    public class ProcessKeyInput implements KeyListener {
        private View view;
        boolean ShifttasteGedrückt;
        public void ProcessKeyInput(View v) {
            view = v;
        } //Methode in view nötig //in ButtonListener



    //@KeyPressed prüft die gedrückten Tasten, Aufruf beim drücken einer Taste,
// diese werden dann an die view weitergegeben und ausgegeben
    //eventuell Wiederholung für 0 - 9, möglich?
    public void keyPressed(KeyEvent e) {
        view.Update("(");
        view.UpdateView();
        if (e.getKeyCode() == KeyEvent.VK_0) {
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
        } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            //model.ClearExpression(); //nicht model, sondern view
            //model.ClearLatex();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            //Restliche Buttons
        } else if (e.getKeyCode() == KeyEvent.VK_T) {
            view.Update("tan(");
            view.UpdateView();
        } else if (e.getKeyCode() == KeyEvent.VK_C) {
            view.Update("cos(");
            view.UpdateView();
        } else if (e.getKeyCode() == KeyEvent.VK_DELETE) {
            view.Update("DEL");
            view.UpdateView();
        } else if (e.getKeyCode() == KeyEvent.VK_L) {
            view.Update("lg(");
            view.UpdateView();
        }
        //Ab hier wird die Shift-Taste genutzt
        //Zunächst überprüfen, ob Shift-Taste gedrückt, dann setzen unseres Parameters @Shifttaste
        //auf true
        else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            ShifttasteGedrückt = true;
        } else if (ShifttasteGedrückt && e.getKeyCode() == KeyEvent.VK_7) {
            view.Update("/");
            view.UpdateView();
        } else if (ShifttasteGedrückt && e.getKeyCode() == KeyEvent.VK_8) {
            view.Update("(");
            view.UpdateView();
        } else if (ShifttasteGedrückt && e.getKeyCode() == KeyEvent.VK_9) {
            view.Update(")");
            view.UpdateView();
        } else if (e.getKeyCode() == KeyEvent.VK_PLUS) {
            view.Update("*");
            view.UpdateView();
        } else if (e.getKeyCode() == KeyEvent.VK_MINUS) {
            view.Update("*");
            view.UpdateView();
        } else if (ShifttasteGedrückt && e.getKeyCode() == KeyEvent.VK_PLUS) {
            view.Update("*");
            view.UpdateView();
        } else if (ShifttasteGedrückt && e.getKeyCode() == KeyEvent.VK_0) {
            view.Update("=");
            view.UpdateView();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            view.Update("CURSOR-RIGHT");
            view.UpdateView();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            view.Update("CURSOR_LEFT");
            view.UpdateView();
        }
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
        }


        //Exponentialrechnung?
        //auch mit OpenBrackets/Close möglich, aber erkennen möglich, dann ohne den Wahrheitswert


    }

    //wenn die Shift-Taste losgelassen wird
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            ShifttasteGedrückt = false;
        }
        view.Update("(");
        view.UpdateView();
    }

    //nicht nötig
    public void keyTyped(KeyEvent e) {view.Update("(");
        view.UpdateView();
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



package Calculator.view;

/*
 * Hier werden die Buttons ausgewertet und an die View (Oberfläche)
 * weitergegeben und dort angezeigt
 */
public class ButtonListener implements java.awt.event.ActionListener {
    private View view;

    public ButtonListener(View v) {
        view = v;
    }

    public void actionPerformed(java.awt.event.ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == view.buttonsNumber[i]) {
                view.Update("" + (i) + "");
                view.UpdateView();
            }
        }
        if (e.getSource() == view.buttonPlus) {
            view.Update("+");
            view.UpdateView();
        } else if (e.getSource() == view.buttonMinus) {
            view.Update("-");
            view.UpdateView();
        } else if (e.getSource() == view.buttonMultiply) {
            view.Update("*");
            view.UpdateView();
        } else if (e.getSource() == view.buttonDivide) {
            view.Update("/");
            view.UpdateView();
        } else if (e.getSource() == view.buttonEquals) {
            view.Update("=");
            view.UpdateView();
        }
    }
}
//Test

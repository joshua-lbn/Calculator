package Calculator;
/*
* Hier werden die Buttons ausgewertet und an die View (Oberfläche)
* weitergegeben und dort angezeigt
 */
public class ButtonListener implements java.awt.event.ActionListener {
    private View view;
    public ButtonListener(View v)
    {
        view = v;
    }
    public void actionPerformed(java.awt.event.ActionEvent e) {
        for (int i=0; i<10; i++) {
            if(e.getSource() == view.jButton[i] ){
                view.Update("" + (i) + "");
                view.UpdateView();
            }
        }
        if (e.getSource() == view.Button11) {
            view.Update("+");
            view.UpdateView();
        }
        else if (e.getSource() == view.Button12) {
            view.Update("-");
            view.UpdateView();
        }
        else if(e.getSource() == view.Button13){
            view.Update("*");
            view.UpdateView();
        }
        else if(e.getSource() == view.Button14){
            view.Update("/");
            view.UpdateView();
        }
        else if(e.getSource() == view.Button15){
            view.Update("=");
            view.UpdateView();
        }
    }
}
//Test

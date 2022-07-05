package Calculator.view.main;

/*
 * ProcessButtonInput-Klasse zum Auswerten der Interaktionen mit der grafischen Hauptoberflaeche.
 * Programmtechnisch an die View-Klasse gegliedert.
 */
public class ProcessMenuInput implements java.awt.event.ActionListener {
    private View view;

    /**
     * Konstruktor: übergebene View-Instanz speichern.
     * @param v View-Instanz
     */
    public ProcessMenuInput(View v) {
        view = v;
    }

    /**
     * Methode, welche bei Druecken auf Menueeintraege aufgerufen wird.
     * @param e Das "Event" bzw. Ereignis, welches verarbeitet werden soll
     */
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getSource() == view.GetJMenuItemCalculator()) {
            // Die jeweilige Methode in der View ausfuehren
            view.SetCalculator();
        }
        else if (e.getSource() == view.GetJMenuItemCone()) {
            //view.SetVolumeSquare();
        }
        else if (e.getSource() == view.GetJMenuItemSquare()) {
            view.SetVolumeSquare();
        }
        else if (e.getSource() == view.GetJMenuItemCylinder()) {
            //view.SetVolumeCylinder();
        }
        else if (e.getSource() == view.GetJMenuItemSphere()) {
            //view.SetVolumeSphere();
        }
        else if (e.getSource() == view.GetJMenuItemDecimal()) {
            view.SetNumeralSystem();
        }
        else if (e.getSource() == view.GetJMenuItemHexadecimal()) {
            view.SetNumeralSystem();
        }
        else if (e.getSource() == view.GetJMenuItemBinary()) {
            view.SetNumeralSystem();
        }
    }
}

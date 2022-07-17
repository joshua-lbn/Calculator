package Calculator.view.volume;

// Java-Imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// Imports weiterer Programmklassen
import Calculator.controller.Controller;

/*
 * ProcessVolumeInput-Klasse zum Auswerten der Interaktionen mit der grafischen Oberflaeche der Volumen-Unterfenster.
 * Programmtechnisch an die vier Klassen ViewCone, ViewCuboid, ViewCylinder und ViewSphere gegliedert.
 */
public class ProcessVolumeInput implements ActionListener {
    // Referenz auf Instanzen anderer Klassen
    private Controller controller;
    private ViewVolume viewVolume;

    /**
     * Konstruktor: uebergebene ViewVolume-Instanz und Controller-Instanz speichern.
     * @param vV ViewVolume-Instanz
     * @param c Controller-Instanz
     */
    public ProcessVolumeInput(ViewVolume vV, Controller c) {
        viewVolume = vV;
        controller = c;
    }

    /**
     * Methode, um auf Knopfdrucke der Instanzen der vier Klassen zu reagieren.
     * @param e Ereignis bzw. "Event", welches verarbeitet werden soll
     */
    public void actionPerformed(ActionEvent e) {
        // Unterscheidung der Klassen
        if (viewVolume instanceof ViewCone) {
            // Falls Berechnungsknopf Ursprung des Ereignisses
            if (e.getSource() == viewVolume.getCalculate()) {
                // Berechnung im Controller anstossen
                controller.VolumeCalculateCone();
            }
        }
        else if (viewVolume instanceof ViewCuboid) {
            if (e.getSource() == viewVolume.getCalculate()) {
                controller.VolumeCalculateCuboid();
            }
        }
        else if (viewVolume instanceof ViewCylinder) {
            if (e.getSource() == viewVolume.getCalculate()) {
                controller.VolumeCalculateCylinder();
            }
        }
        else if (viewVolume instanceof ViewSphere) {
            if (e.getSource() == viewVolume.getCalculate()) {
                controller.VolumeCalculateSphere();
            }
        }
    }

}

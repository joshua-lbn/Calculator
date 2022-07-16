package Calculator.view.volume;

import java.awt.event.ActionEvent;
import Calculator.controller.Controller;

/*
 * ProcessVolumeInput Klasse zum Auswerten der Interaktion mit der grafischen Volumenoberfläche.
 */
public class ProcessVolumeInput implements java.awt.event.ActionListener {
    private Controller controller;
    private ViewVolume v;

    /**
     * Konstruktor: übergebene ViewVolume-Instanz und Controller-Instanz speichern.
     * @param vV ViewVolume-Instanz
     * @param c Controller-Instanz
     */
    public ProcessVolumeInput(ViewVolume vV, Controller c) {
        v = vV;
        controller = c;
    }
    public void actionPerformed(ActionEvent e) {
        if (v instanceof ViewCone) {
            if (e.getSource() == v.getCalculate()) {
                controller.VolumeCalculateCone();
            }
        }
        else if (v instanceof ViewCuboid) {
            if (e.getSource() == v.getCalculate()) {
                controller.VolumeCalculateCuboid();
            }
        }
        else if (v instanceof ViewCylinder) {
            if (e.getSource() == v.getCalculate()) {
                controller.VolumeCalculateCylinder();
            }
        }
        else if (v instanceof ViewSphere) {
            if (e.getSource() == v.getCalculate()) {
                controller.VolumeCalculateSphere();
            }
        }
    }

}

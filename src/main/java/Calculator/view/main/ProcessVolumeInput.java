package Calculator.view.main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import Calculator.view.viewVolume.viewCone;
import Calculator.view.viewVolume.viewCuboid;
import Calculator.controller.Controller;

public class ProcessVolumeInput implements java.awt.event.ActionListener {
    private Controller controller;
    private viewCone viewCone;
    private viewCuboid viewCuboid;
    public ProcessVolumeInput(viewCone vC, viewCuboid vCu) {
        viewCone = vC;
        viewCuboid = vCu;
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewCone.getCalculate()) {
            controller.VolumeCalculate(1);
        }
        if (e.getSource() == viewCuboid.getCalculate()) {
            controller.VolumeCalculate(2);
        }
    }

}

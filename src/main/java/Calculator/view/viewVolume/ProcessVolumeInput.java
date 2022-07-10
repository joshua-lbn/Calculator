package Calculator.view.viewVolume;

import javax.swing.*;
import java.awt.event.ActionEvent;
import Calculator.view.viewVolume.viewCone;
import Calculator.view.viewVolume.viewCylinder;
import Calculator.controller.Controller;
import Calculator.view.main.View;

public class ProcessVolumeInput implements java.awt.event.ActionListener {
    private Controller controller;
    private viewCone viewCone;
    private viewCone viewCuboid;
    private viewCone viewCylinder;
    private ViewVolume v;
    //private View view;
    public ProcessVolumeInput(ViewVolume vV, Controller c) {
        v = vV;
        controller = c;
    }
    public void actionPerformed(ActionEvent e) {
        if (v instanceof viewCone) {
            System.out.println("instance");
            if (e.getSource() == v.getCalculate()) {
                System.out.println("getSource");
                controller.VolumeCalculate(1);
            }
        }
        else if (v instanceof viewCuboid) {
            if (e.getSource() == v.getCalculate()) {
                System.out.println("getSource");
                controller.VolumeCalculate(2);
            }
        }
        else if (v instanceof viewCylinder) {
            if (e.getSource() == v.getCalculate()) {
                System.out.println("getSource");
                controller.VolumeCalculate(3);
            }
        }
        else if (v instanceof viewSphere) {
            if (e.getSource() == v.getCalculate()) {
                System.out.println("getSource");
                controller.VolumeCalculate(4);
            }
        }
    }

}

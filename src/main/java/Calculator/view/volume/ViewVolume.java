package Calculator.view.volume;

// Java-Imports
import javax.swing.*;

/*
 * ViewVolume-Interface fuer die vier Volumenoberflaechenklassen.
 * Fuehrt dazu, dass nur eine ListenerKlasse (ProcessVolumeInput) fuer die Volumen-GUIs noetig ist.
 */
public interface ViewVolume {
    // JButton calculate, welcher die Berechnung anstoesst
    JButton getCalculate();
}

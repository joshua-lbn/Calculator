package Calculator.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ViewNumeralSystem extends JFrame {
            private JPanel NumeralmainPanel;
            private JTextField decimalTextfield;
    private JTextField hexaTextField;
    private JTextField binaryTextField;
    private JButton button1;
    private JButton button2;
    private JButton convertButton;

    public ViewNumeralSystem(){
       // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(NumeralmainPanel);
        this.pack();
        this.setVisible(true);
        }

            public static void main (String[] args)
            {
            JFrame frame = new ViewNumeralSystem();

                frame.setVisible(true);
            }

        }
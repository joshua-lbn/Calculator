package Calculator.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Test1 extends JFrame{
    private JPanel mainPanel;
    private JTextField lengthTextField;
    private JTextField widthTextField;
    private JTextField heightTextField;
    private JPanel lengthJPanel;
    private JLabel length;
    private JPanel widthJPanel;
    private JLabel width;
    private JPanel heightJPanel;
    private JLabel height;
    private JPanel volumeJPanel;
    private JLabel volume;
    private JButton convert;
    private JPanel volumeOutput;
    private JLabel volumeOutputSquare;


    public Test1() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        //this.setVisible(true);
        convert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //noch prüfen Buchstaben + negativ
                // parse Double, da nicht möglich String direkt in Zahl umwandeln
                double volumeLength = Double.parseDouble(lengthTextField.getText());
                double volumeWidth = Double.parseDouble(widthTextField.getText());
                double volumeHeight = Double.parseDouble(heightTextField.getText());

                double volumeResult = volumeLength * volumeWidth * volumeHeight;
                System.out.println((double) (Math.round(volumeResult*100))/100);
                volumeOutputSquare.setText("" + (double) (Math.round(volumeResult*100))/100);
            }
        });
    }
    public static void main(String[] args) {
            JFrame frame = new Test1();
            frame.setVisible(true);
    }
}


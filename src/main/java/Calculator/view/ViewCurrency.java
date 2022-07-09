package Calculator.view;

import Calculator.controller.Controller;
import Calculator.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ViewCurrency extends JFrame{

    //erstellen der GridLayouts
    private javax.swing.JPanel grid1 = new javax.swing.JPanel();
    private javax.swing.JPanel grid2 = new javax.swing.JPanel();
    private javax.swing.JPanel grid3 = new javax.swing.JPanel();
    private javax.swing.JPanel grid11 = new javax.swing.JPanel();

    //Labels für Währungsnamen und Währungswert
    private javax.swing.JLabel label1 = new javax.swing.JLabel();
    private javax.swing.JLabel label2 = new javax.swing.JLabel();
    private javax.swing.JLabel label3 = new javax.swing.JLabel();
    private javax.swing.JLabel label4 = new javax.swing.JLabel();
    private javax.swing.JLabel label11 = new javax.swing.JLabel();
    private javax.swing.JLabel label21 = new javax.swing.JLabel();
    private javax.swing.JLabel label22 = new javax.swing.JLabel();
    private javax.swing.JLabel label23 = new javax.swing.JLabel();
    private javax.swing.JLabel label24 = new javax.swing.JLabel();

    private String[] currency;


    //Eingabenfeld
    private javax.swing.JTextField textinput = new javax.swing.JTextField();;


    public ViewCurrency () {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        grid1.setLayout(new GridLayout(1, 3));
        ButtonListener bl = new ButtonListener();

        grid11.setLayout(new GridLayout(2, 1));
        grid1.add(grid11);

        //Name der Ausgangswährung
        currency = new String[]{"Dollar","Euro","Pound","Yuan"};
        label11 = new JLabel("" + currency[getA()] + "" );
        grid11.add(label11);

        //Eingabenfeld
        textinput = new JTextField();
        grid11.add(textinput);
        textinput.addActionListener( bl );


        grid2.setLayout(new GridLayout(4, 1));
        grid1.add(grid2);

        //umgerechneten Währungswerte
        label21 = new JLabel();
        label22 = new JLabel();
        label23 = new JLabel();
        label24 = new JLabel();
        grid2.add(label21);
        grid2.add(label22);
        grid2.add(label23);
        grid2.add(label24);



        grid3.setLayout(new GridLayout(4, 1));
        grid1.add(grid3);

        //Währungsnamen der Währungen in die umgerechnet wurde
        label1 = new JLabel("Dollar");
        label2 = new JLabel("Euro");
        label3 = new JLabel("Pound");
        label4 = new JLabel("Yuan");

        grid3.add(label1);
        grid3.add(label2);
        grid3.add(label3);
        grid3.add(label4);


        this.getContentPane().add(grid1);
        // Fenster als dynamisch skalierbar definieren
        pack();
        // Groesse setzen
        setSize(600,300);
        // Sichtbar setzen
        setVisible(true);


    }
    public static void main(String args[]) {
        // Ein Objekt der Klasse erzeugen und sichtbar machen.
        new ViewCurrency().setVisible ( true );
    }

    class ButtonListener implements java.awt.event.ActionListener {
        public void actionPerformed(java.awt.event.ActionEvent e) {

            // Noch prüfen Buchstaben + leere Eingabe
            // Parse Double, da nicht möglich String direkt in Zahl umwandeln
            double norm = Double.parseDouble(textinput.getText());
            if (norm <= 0 ) {
                label21.setText("ungültige Eingabe");
                label22.setText("ungültige Eingabe");
                label23.setText("ungültige Eingabe");
                label24.setText("ungültige Eingabe");
            }
            else {
                //umrechnen in andere Währungen
                if (getA() == 0) {
                    double dollar1 = 1 * norm;
                    System.out.println((double) (Math.round(dollar1 * 100)) / 100);
                    label21.setText("" + (double) (Math.round(dollar1 * 100)) / 100);

                    double euro1 = 0.97 * norm;
                    System.out.println((double) (Math.round(euro1 * 100)) / 100);
                    label22.setText("" + (double) (Math.round(euro1 * 100)) / 100);

                    double pound1 = 0.84 *  norm;
                    System.out.println((double) (Math.round(pound1 * 100)) / 100);
                    label23.setText("" + (double) (Math.round(pound1 * 100)) / 100);

                    double yuan1 = 6.72 * norm;
                    System.out.println((double) (Math.round(yuan1 * 100)) / 100);
                    label24.setText("" + (double) (Math.round(yuan1 * 100)) / 100);
                }
                else if (getA() == 1) {
                    double dollar2 = 1.03 * norm;
                    System.out.println((double) (Math.round(dollar2 * 100)) / 100);
                    label21.setText("" + (double) (Math.round(dollar2 * 100)) / 100);

                    double euro2 = 1 * norm;
                    System.out.println((double) (Math.round(euro2 * 100)) / 100);
                    label22.setText("" + (double) (Math.round(euro2 * 100)) / 100);

                    double pound2 = 0.86 * norm;
                    System.out.println((double) (Math.round(pound2 * 100)) / 100);
                    label23.setText("" + (double) (Math.round(pound2 * 100)) / 100);

                    double yuan2 = 6.90 * norm;
                    System.out.println((double) (Math.round(yuan2 * 100)) / 100);
                    label24.setText("" + (double) (Math.round(yuan2 * 100)) / 100);
                }
                else if (getA() == 2) {
                    double dollar3 = 1.20 * norm;
                    System.out.println((double) (Math.round(dollar3 * 100)) / 100);
                    label21.setText("" + (double) (Math.round(dollar3 * 100)) / 100);

                    double euro3 = 1.16 * norm;
                    System.out.println((double) (Math.round(euro3 * 100)) / 100);
                    label22.setText("" + (double) (Math.round(euro3 * 100)) / 100);

                    double pound3 = 1 * norm;
                    System.out.println((double) (Math.round(pound3 * 100)) / 100);
                    label23.setText("" + (double) (Math.round(pound3 * 100)) / 100);

                    double yuan3 = 8.04 * norm;
                    System.out.println((double) (Math.round(yuan3 * 100)) / 100);
                    label24.setText("" + (double) (Math.round(yuan3 * 100)) / 100);
                }
                else if (getA() == 3) {
                    double dollar4 = norm;
                    System.out.println((double) (Math.round(dollar4 * 100)) / 100);
                    label21.setText("" + (double) (Math.round(dollar4 * 100)) / 100);

                    double euro4 = norm;
                    System.out.println((double) (Math.round(euro4 * 100)) / 100);
                    label22.setText("" + (double) (Math.round(euro4 * 100)) / 100);

                    double pound4 = norm;
                    System.out.println((double) (Math.round(pound4 * 100)) / 100);
                    label23.setText("" + (double) (Math.round(pound4 * 100)) / 100);

                    double yuan4 = norm;
                    System.out.println((double) (Math.round(yuan4 * 100)) / 100);
                    label24.setText("" + (double) (Math.round(yuan4 * 100)) / 100);
                }
            }
        }
    }

    public int getA () {
        //ausgewählte Währung aus dem JMenuBar übernehmen (als Zahl; int a = "view.getWaehrung()" - 1)
        int a = 1-1;
        return a;
    }
}

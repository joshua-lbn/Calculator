package Calculator.view;

import Calculator.controller.Controller;
import Calculator.model.Model;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private Model model;
    private Controller controller;
    protected javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
    protected javax.swing.JLabel label = new javax.swing.JLabel();
    protected javax.swing.JPanel spacer1 = new javax.swing.JPanel();
    protected javax.swing.JButton[] buttonsNumber = new javax.swing.JButton[10];
    protected javax.swing.JButton buttonPlus;
    protected javax.swing.JButton buttonMinus;
    protected javax.swing.JButton buttonMultiply;
    protected javax.swing.JButton buttonDivide;
    protected javax.swing.JButton buttonEquals;


    public View() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        spacer1.setLayout(new java.awt.GridLayout(2, 1));

        label = new javax.swing.JLabel();
        spacer1.add(label);


        jPanel1.setLayout(new java.awt.GridLayout(3, 5));
        spacer1.add(jPanel1);


        ButtonListener bl = new ButtonListener(this);

        for (int i = 0; i < 10; i++) {
            buttonsNumber[i] = new javax.swing.JButton(i + "");
            buttonsNumber[i].addActionListener(bl);
            jPanel1.add(buttonsNumber[i]);
        }

        buttonPlus = new javax.swing.JButton("+");
        jPanel1.add(buttonPlus);
        buttonPlus.addActionListener(bl);

        buttonMinus = new javax.swing.JButton("-");
        jPanel1.add(buttonMinus);
        buttonMinus.addActionListener(bl);

        buttonMultiply = new javax.swing.JButton("*");
        jPanel1.add(buttonMultiply);
        buttonMultiply.addActionListener(bl);

        buttonDivide = new javax.swing.JButton("/");
        jPanel1.add(buttonDivide);
        buttonDivide.addActionListener(bl);

        buttonEquals = new javax.swing.JButton("=");
        jPanel1.add(buttonEquals);
        buttonEquals.addActionListener(bl);
        this.getContentPane().add(spacer1);
        pack();
        setVisible(true);
    }

    public void UpdateIconView(Image i) {
        i = i.getScaledInstance(-1, label.getHeight(), Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(i));
    }

    public void UpdateView() {
        controller.UpdateView();
    }

    public void UpdateLinks(Model m, Controller c) {
        model = m;
        controller = c;
    }

    public void Update(String s) {
        controller.Update(s);
    }
}

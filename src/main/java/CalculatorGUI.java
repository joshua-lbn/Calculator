import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class CalculatorGUI {

    private JFrame frame;
    private JButton button1;
    private JButton button2;

    private JLabel anzeige;

    private CalculatorFunctions calculatorFunctions;

    private List<JButton> buttons;

    public CalculatorGUI(){
        calculatorFunctions = new CalculatorFunctions();

        frame = new JFrame("Calculator");
        button1 = new JButton("1");
        button2 = new JButton("2");
        anzeige = new JLabel();

        button1.setSize(50, 50);
        button2.setSize(50, 50);

        frame.add(button1);
        frame.add(button2);

        frame.setLayout(new GridLayout(4, 4));

        buttons = new LinkedList<>();
        buttons.add(button1);
        buttons.add(button2);

        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        for (JButton btn : buttons){
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calculatorFunctions.update(btn.getText());
                }
            });
        }

    }


    public static void main(String[] args){
        new CalculatorGUI();
    }
}

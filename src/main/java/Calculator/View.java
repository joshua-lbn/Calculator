package Calculator;
import javax.swing.*;
import java.awt.*;
/*
Klasse View stellt das Image, bzw. die Oberfläche unseres Taschenrechners da. Hier werden die einzelnen Buttons, bzw.
das Label definiert.
 */
public class View extends JFrame{
    private Model model;
    private Controller controller;
    protected javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
    protected javax.swing.JLabel label = new javax.swing.JLabel();
    protected javax.swing.JPanel spacer1 = new javax.swing.JPanel();
    protected javax.swing.JButton[] jButton = new javax.swing.JButton[10];
    protected javax.swing.JButton Button11 = new javax.swing.JButton();
    protected javax.swing.JButton Button12 = new javax.swing.JButton();
    protected javax.swing.JButton Button13 = new javax.swing.JButton();
    protected javax.swing.JButton Button14 = new javax.swing.JButton();
    protected javax.swing.JButton Button15 = new javax.swing.JButton();

/*
Im Konstruktor wird das Fenster geöffnet und die Buttons und das Label auf unserem JFrame angeordnet. Außerdem wird
hier ein Buttonlistener erstellt und die Buttons übermittelt
 */
    public View() { //Rechenoperationen als Feld speichern und abrufen

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        spacer1.setLayout(new java.awt.GridLayout(2, 1));


        label = new javax.swing.JLabel();
        spacer1.add(label);


        jPanel1.setLayout(new java.awt.GridLayout(3, 5));
        spacer1.add(jPanel1);


        ButtonListener bl = new ButtonListener(this);

        for (int i = 0; i < 10; i++) {
            jButton[i] = new javax.swing.JButton("" + (i) + "");
            jButton[i].addActionListener(bl);
            jPanel1.add(jButton[i]);
        }

        Button11 = new javax.swing.JButton("+");
        jPanel1.add(Button11);
        Button11.addActionListener(bl);

        Button12 = new javax.swing.JButton("-");
        jPanel1.add(Button12);
        Button12.addActionListener(bl);

        Button13 = new javax.swing.JButton("*");
        jPanel1.add(Button13);
        Button13.addActionListener(bl);

        Button14 = new javax.swing.JButton("/");
        jPanel1.add(Button14);
        Button14.addActionListener(bl);

        Button15 = new javax.swing.JButton("=");
        jPanel1.add(Button15);
        Button15.addActionListener(bl);
        this.getContentPane().add(spacer1);
        pack();
        setVisible (true);
        //ToDo 'Ans'
    }
    /*
    Hier wird das Image im Label in Icons angezeigt
     */
    public void UpdateIconView(Image i)
    {
        label.setIcon(new ImageIcon(i));
    }
    /*
    controller updated die View
     */
    public void UpdateView()
    {
        controller.UpdateView();
    }
    public void UpdateLinks(Model m, Controller c)
    {
        model = m;
        controller = c;
    }
    public void Update(String s)
    {
        controller.Update(s);
    }
}

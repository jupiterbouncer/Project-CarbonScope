import javax.swing.*;
import java.awt.*;

public class CarbonScope extends JFrame{

    // Member variables of the CarbonScope app, integrating User, Vehicle, Diet and Home objects
    private User user;
    private Vehicle vehicle;
    private Diet diet;
    private Home home;
    private double kgOfWaste;

    private JTextField userName;
    private JTextArea outputArea;

    private JButton calculateButton;

    public CarbonScope () {
        setTitle("CARBONSCOPE");
        setSize(1000,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("User: "));
        userName = new JTextField(15);
        add(userName);

        calculateButton = new JButton("CALCULATE");
        add(calculateButton);
    }

    public static void main(String[] args) {
        new CarbonScope();
    }
}

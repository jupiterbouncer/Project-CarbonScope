import javax.swing.*;
import java.awt.*;

public class CarbonScope extends JFrame{

    // Member variables of the CarbonScope app, integrating User, Vehicle, Diet and Home objects
    private User user;
    private Vehicle vehicle;
    private Diet diet;
    private Home home;
    private double kgOfWaste;

    private JTextField userName, userLocation;

    // For the Car form
    private JTextField mileage, fuelConsumption;

    private JTextArea outputArea;

    private JButton homeButton, calculatorButton, summaryButton, tipsButton;

    public CarbonScope() {

        setTitle("CARBONSCOPE: YOUR CARBON FOOTPRINT ASSISTANT");
        setSize(1000,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        JTabbedPane carbonTab = new JTabbedPane();

        add(new JLabel("Name: ")); 
        userName = new JTextField(15);
        add(userName);

        add(new JLabel("Location: "));
        userLocation = new JTextField(15);
        add(userLocation);

        calculatorButton = new JButton("CALCULATE");
        add(calculatorButton);

        // JPanel for the Vehicle class
        JPanel vehiclePanel = new JPanel();
        vehiclePanel.setBackground(Color.LIGHT_GRAY);
        vehiclePanel.setLayout(new BorderLayout());
        vehiclePanel.add(new JLabel("Select Vehicle Type"), BorderLayout.CENTER);

        String [] vehicleType = {"Car", "Bus", "Aeroplane"};
        JComboBox<String> vehicleComboBox = new JComboBox<>(vehicleType);

        add(vehiclePanel);
        add(vehicleComboBox);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CarbonScope();
    }
}

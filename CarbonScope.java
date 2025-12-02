import javax.swing.*;
import java.awt.*;

public class CarbonScope extends JFrame{

    // Member variables of the CarbonScope app, integrating User, Vehicle, Diet and Home objects
    private User user;
    private Vehicle userVehicle;
    private Diet diet;
    private Home home;

    // User info fields
    private JTextField userNameField, userLocationField;

    // For the Vehicle screen
    private JTextField mileageField, fuelConsumptionField;

    // For the Aeroplane screen
    private JTextField flightsPerYearField;

    // For the Bus screen
    private JTextField yearlyUseField;

    // Output area
    private JTextArea outputArea;

    private JButton homeButton, calculatorButton, summaryButton, tipsButton;
    private JButton vehicleButton, homeActivityButton, dietButton;

    public CarbonScope() {

        setTitle("CARBONSCOPE: YOUR CARBON FOOTPRINT ASSISTANT");
        setSize(900,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Left Panel (Navigation)
        JPanel leftPanel = new JPanel(new GridLayout(4,1,10,10));
        leftPanel.setPreferredSize(new Dimension(150, 200));

        // Main buttons for navigating the app
        homeButton = new JButton("HOME");
        calculatorButton = new JButton("CALCULATE");
        summaryButton = new JButton("SUMMARY");
        tipsButton = new JButton("TIPS");

        leftPanel.add(homeButton);
        leftPanel.add(calculatorButton);
        leftPanel.add(summaryButton);
        leftPanel.add(tipsButton);

        add(leftPanel, BorderLayout.WEST);

        // Center panel with content
        JPanel centerPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        centerPanel.setLayout(cardLayout);

        // Home screen
        JPanel homeScreen = new JPanel(new GridLayout(3,1,10,10));
        vehicleButton = new JButton("VEHICLE");
        homeActivityButton = new JButton("HOME ACTIVITIES");
        dietButton = new JButton("DIET");

        homeScreen.add(vehicleButton);
        homeScreen.add(homeActivityButton);
        homeScreen.add(dietButton);

        // Vehicle screen
        JPanel vehicleScreen = new JPanel(new BorderLayout());

        JPanel vehicleSelectPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        vehicleSelectPanel.add(new JLabel("Select Vehicle Type: "));

        String[] vehicleTypes = {"---","Car", "Bus", "Aeroplane"};
        JComboBox<String> vehicleCombo = new JComboBox<>(vehicleTypes);
        vehicleSelectPanel.add(vehicleCombo);

        vehicleScreen.add(vehicleSelectPanel, BorderLayout.NORTH);

        JPanel vehicleDetailsPanel = new JPanel();
        vehicleDetailsPanel.setLayout(new BoxLayout(vehicleDetailsPanel, BoxLayout.Y_AXIS));
        vehicleScreen.add(vehicleDetailsPanel, BorderLayout.CENTER);

        // Diet screen
        JPanel dietScreen = new JPanel();
        dietScreen.add(new JLabel("Implement diet here"));

        // Home activities screen
        JPanel homeActivityScreen = new JPanel();
        homeActivityScreen.add(new JLabel("Implement home activities here"));

        // Add all screens to center panel
        centerPanel.add(homeScreen, "HOME");
        centerPanel.add(vehicleScreen, "VEHICLE");
        centerPanel.add(dietScreen, "DIET");
        centerPanel.add(homeActivityScreen, "HOME ACTIVITY");

        add(centerPanel, BorderLayout.CENTER);
        
        // Output area
        outputArea = new JTextArea(10,70);
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        // Navigation buttons and their action listeners
        homeButton.addActionListener(hb -> {
            cardLayout.show(centerPanel, "HOME");
        });

        vehicleButton.addActionListener(vb -> {
            cardLayout.show(centerPanel, "VEHICLE");
        });

        dietButton.addActionListener(db -> {
            cardLayout.show(centerPanel, "DIET");
        });

        homeActivityButton.addActionListener(hab -> {
            cardLayout.show(centerPanel, "HOME ACTIVITY");
        });

        
        // This is the vehicle layout
        vehicleCombo.addActionListener(vc -> {
            vehicleDetailsPanel.removeAll();
            String choice = (String) vehicleCombo.getSelectedItem();

            switch (choice) {

                case "Car" -> { 
                    vehicleDetailsPanel.add(new JLabel("Mileage (km/week): ")); 
                    mileageField = new JTextField(10);
                    vehicleDetailsPanel.add(mileageField);

                    vehicleDetailsPanel.add(new JLabel("Fuel Consumption (L/week): ")); 
                    fuelConsumptionField = new JTextField(10);
                    vehicleDetailsPanel.add(fuelConsumptionField);

                    JButton calculateCarButton = new JButton("Calculate Car Footprint");
                    vehicleDetailsPanel.add(calculateCarButton);

                    calculateCarButton.addActionListener(ccb ->{

                    try {
                        double mileage = Double.parseDouble(mileageField.getText());
                        double fuelConsumption = Double.parseDouble(fuelConsumptionField.getText());

                        if (mileage < 0 || fuelConsumption < 0) {
                            outputArea.append("Mileage nor fuel cannot be negative.\n");
                            return;
                        }

                        userVehicle = new Car("Gasoline", mileage, fuelConsumption); 
                        double vehicleFootprint = userVehicle.calculateFootprint();

                        outputArea.append("Car Footprint: " + vehicleFootprint + "kg CO₂/week\n");
                    
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        });
                    }

                case "Bus" -> {
                    vehicleDetailsPanel.add(new JLabel("Number of bus ride/week: ")); 
                    yearlyUseField = new JTextField(10);
                    vehicleDetailsPanel.add(yearlyUseField);

                    vehicleDetailsPanel.add(new JLabel("Mileage (km/week): ")); 
                    mileageField = new JTextField(10);
                    vehicleDetailsPanel.add(mileageField);

                    JButton calculateBusButton = new JButton("Calculate Bus Footprint");
                    vehicleDetailsPanel.add(calculateBusButton);

                    calculateBusButton.addActionListener(cbb ->{

                    try {
                        int rides = Integer.parseInt(mileageField.getText());
                        double mileage = Double.parseDouble(mileageField.getText());

                        if (rides < 0 || mileage < 0) {
                            outputArea.append("Number of rides nor mileage cannot be negative.\n");
                            return;
                        }

                        userVehicle = new Bus(rides, mileage, 3.4); 
                        double vehicleFootprint = userVehicle.calculateFootprint();

                        outputArea.append("Bus Footprint: " + vehicleFootprint + "kg CO₂/week\n");
                    
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        });
                    }

                    case "Aeroplane" -> {
                    vehicleDetailsPanel.add(new JLabel("Flights this year: ")); 
                    flightsPerYearField = new JTextField(10);
                    vehicleDetailsPanel.add(flightsPerYearField);

                    vehicleDetailsPanel.add(new JLabel("Mileage (km/week): ")); 
                    mileageField = new JTextField(10);
                    vehicleDetailsPanel.add(mileageField);

                    JButton calculatePlaneButton = new JButton("Calculate Plane Footprint");
                    vehicleDetailsPanel.add(calculatePlaneButton);

                    calculatePlaneButton.addActionListener(cpb ->{

                    try {
                        int flightsPerYear = Integer.parseInt(flightsPerYearField.getText());
                        double mileage = Double.parseDouble(mileageField.getText());

                        if (flightsPerYear < 0 || mileage < 0) {
                            outputArea.append("Number of flights nor mileage cannot be negative.\n");
                            return;
                        }

                        userVehicle = new Aeroplane(flightsPerYear, mileage, 90000); 
                        double vehicleFootprint = userVehicle.calculateFootprint();

                        outputArea.append("Aeroplane Footprint: " + vehicleFootprint + "kg CO₂/year\n");
                    
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        });
                    }
                    
            }

            vehicleDetailsPanel.revalidate();
            vehicleDetailsPanel.repaint();
        });

        setVisible(true);
        
    }

    public static void main(String[] args) {
        new CarbonScope();
    }
}

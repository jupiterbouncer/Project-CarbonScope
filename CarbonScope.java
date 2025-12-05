import java.awt.*;
import javax.swing.*;

public class CarbonScope extends JFrame {

    // Member variables
    private User user;
    private Vehicle userVehicle;
    private Diet diet;
    private Home home;
    private Electricity electricityObj;
    private Cooking cookingObj;

    // User info fields
    private JTextField userNameField, userLocationField;

    // Vehicle fields
    private JTextField mileageField, fuelConsumptionField;
    private JTextField flightsPerYearField, yearlyUseField;

    // Diet fields
    private JTextField mealsPerDayField;

    // Home fields
    private JTextField electricityField, fuelUsageField;
    private JComboBox<String> fuelTypeBox;
    private JLabel homeTotalLabel;

    // Output area
    private JTextArea outputArea;

    // Buttons
    private JButton homeButton, calculatorButton, summaryButton, tipsButton;
    private JButton vehicleButton, homeActivityButton, dietButton;
    private JButton calculateTotalButton;

    public CarbonScope() {
        setTitle("CARBONSCOPE: YOUR CARBON FOOTPRINT ASSISTANT");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Logo
        ImageIcon logo = new ImageIcon("Logo.png");
        setIconImage(logo.getImage());
        JLabel logoLabel = new JLabel(logo);
        add(logoLabel, BorderLayout.NORTH);

        // Left panel (Navigation)
        JPanel leftPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        leftPanel.setPreferredSize(new Dimension(150, 200));
        homeButton = new JButton("HOME");
        calculatorButton = new JButton("CALCULATE");
        summaryButton = new JButton("SUMMARY");
        tipsButton = new JButton("TIPS");
        leftPanel.add(homeButton);
        leftPanel.add(calculatorButton);
        leftPanel.add(summaryButton);
        leftPanel.add(tipsButton);
        add(leftPanel, BorderLayout.WEST);

        // Center panel with CardLayout
        JPanel centerPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        centerPanel.setLayout(cardLayout);

        // -------------------- Home screen --------------------
        JPanel homeScreen = new JPanel(new GridLayout(3, 1, 10, 10));
        vehicleButton = new JButton("VEHICLE");
        homeActivityButton = new JButton("HOME ACTIVITIES");
        dietButton = new JButton("DIET");
        homeScreen.add(vehicleButton);
        homeScreen.add(homeActivityButton);
        homeScreen.add(dietButton);

        // -------------------- Vehicle screen --------------------
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

        // -------------------- Diet screen --------------------
        JPanel dietScreen = new JPanel(new BorderLayout());
        JPanel dietSelectPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dietSelectPanel.add(new JLabel("Select diet type"));
        String[] dietTypes = {"---","Vegetarian", "Meat-heavy", "Balanced"};
        JComboBox<String> dietCombo = new JComboBox<>(dietTypes);
        dietSelectPanel.add(dietCombo);
        dietScreen.add(dietSelectPanel, BorderLayout.NORTH);
        JPanel dietDetailsPanel = new JPanel();
        dietDetailsPanel.setLayout(new BoxLayout(dietDetailsPanel, BoxLayout.Y_AXIS));
        dietScreen.add(dietDetailsPanel, BorderLayout.CENTER);

        // -------------------- Home Activities screen --------------------
        JPanel homeActivityScreen = new JPanel(new BorderLayout());
        JPanel homeMainPanel = new JPanel(new GridLayout(1,2,20,0));

        // Electricity panel
        JPanel electricityPanel = new JPanel(new GridLayout(4,1,5,5));
        electricityPanel.setBorder(BorderFactory.createTitledBorder("Electricity"));
        electricityField = new JTextField();
        JButton saveElectricityBtn = new JButton("Save Electricity");
        electricityPanel.add(new JLabel("Monthly Electricity (kWh):"));
        electricityPanel.add(electricityField);
        electricityPanel.add(new JLabel("Emission factor: 0.3 kg CO₂/kWh"));
        electricityPanel.add(saveElectricityBtn);

        // Cooking panel
        JPanel cookingPanel = new JPanel(new GridLayout(5,1,5,5));
        cookingPanel.setBorder(BorderFactory.createTitledBorder("Cooking"));
        fuelUsageField = new JTextField();
        fuelTypeBox = new JComboBox<>(new String[]{"LPG","Charcoal","Firewood"});
        JButton saveCookingBtn = new JButton("Save Cooking");
        cookingPanel.add(new JLabel("Monthly Fuel Usage (kg/L):"));
        cookingPanel.add(fuelUsageField);
        cookingPanel.add(new JLabel("Fuel Type:"));
        cookingPanel.add(fuelTypeBox);
        cookingPanel.add(saveCookingBtn);

        homeMainPanel.add(electricityPanel);
        homeMainPanel.add(cookingPanel);

        // Bottom panel: Home total
        homeTotalLabel = new JLabel("Home Total Emissions: 0.0 kg CO₂/year");
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(homeTotalLabel);

        homeActivityScreen.add(homeMainPanel, BorderLayout.CENTER);
        homeActivityScreen.add(bottomPanel, BorderLayout.SOUTH);

        // -------------------- Add screens to center panel --------------------
        centerPanel.add(homeScreen, "HOME");
        centerPanel.add(vehicleScreen, "VEHICLE");
        centerPanel.add(dietScreen, "DIET");
        centerPanel.add(homeActivityScreen, "HOME ACTIVITY");
        add(centerPanel, BorderLayout.CENTER);

        // -------------------- Output area --------------------
        outputArea = new JTextArea(10, 70);
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        // -------------------- Navigation button actions --------------------
        homeButton.addActionListener(hb -> cardLayout.show(centerPanel, "HOME"));
        vehicleButton.addActionListener(vb -> cardLayout.show(centerPanel, "VEHICLE"));
        dietButton.addActionListener(db -> cardLayout.show(centerPanel, "DIET"));
        homeActivityButton.addActionListener(hab -> cardLayout.show(centerPanel, "HOME ACTIVITY"));

        // -------------------- Vehicle logic --------------------
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

                    calculateCarButton.addActionListener(ccb -> {
                        try {
                            double mileage = Double.parseDouble(mileageField.getText());
                            double fuelConsumption = Double.parseDouble(fuelConsumptionField.getText());
                            if (mileage < 0 || fuelConsumption < 0) {
                                outputArea.append("Mileage nor fuel cannot be negative.\n");
                                return;
                            }
                            userVehicle = new Car("Gasoline", mileage, fuelConsumption);
                            double vehicleFootprint = userVehicle.calculateFootprint();
                            outputArea.append("Car Footprint: " + vehicleFootprint + " kg CO₂/week\n");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    });
                }
                case "Bus" -> {
                    vehicleDetailsPanel.add(new JLabel("Number of bus rides/week: "));
                    yearlyUseField = new JTextField(10);
                    vehicleDetailsPanel.add(yearlyUseField);

                    vehicleDetailsPanel.add(new JLabel("Mileage (km/week): "));
                    mileageField = new JTextField(10);
                    vehicleDetailsPanel.add(mileageField);

                    JButton calculateBusButton = new JButton("Calculate Bus Footprint");
                    vehicleDetailsPanel.add(calculateBusButton);

                    calculateBusButton.addActionListener(cbb -> {
                        try {
                            int rides = Integer.parseInt(yearlyUseField.getText());
                            double mileage = Double.parseDouble(mileageField.getText());
                            if (rides < 0 || mileage < 0) {
                                outputArea.append("Number of rides or mileage cannot be negative.\n");
                                return;
                            }
                            userVehicle = new Bus(rides, mileage, 3.4);
                            double vehicleFootprint = userVehicle.calculateFootprint();
                            outputArea.append("Bus Footprint: " + vehicleFootprint + " kg CO₂/week\n");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    });
                }
                case "Aeroplane" -> {
                    vehicleDetailsPanel.add(new JLabel("Flights per year: "));
                    flightsPerYearField = new JTextField(10);
                    vehicleDetailsPanel.add(flightsPerYearField);

                    vehicleDetailsPanel.add(new JLabel("Mileage per flight (km): "));
                    mileageField = new JTextField(10);
                    vehicleDetailsPanel.add(mileageField);

                    JButton calculatePlaneButton = new JButton("Calculate Plane Footprint");
                    vehicleDetailsPanel.add(calculatePlaneButton);

                    calculatePlaneButton.addActionListener(cpb -> {
                        try {
                            int flights = Integer.parseInt(flightsPerYearField.getText());
                            double mileage = Double.parseDouble(mileageField.getText());
                            if (flights < 0 || mileage < 0) {
                                outputArea.append("Flights or mileage cannot be negative.\n");
                                return;
                            }
                            userVehicle = new Aeroplane(flights, mileage, 90000);
                            double vehicleFootprint = userVehicle.calculateFootprint();
                            outputArea.append("Aeroplane Footprint: " + vehicleFootprint + " kg CO₂/year\n");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    });
                }
            }
            vehicleDetailsPanel.revalidate();
            vehicleDetailsPanel.repaint();
        });

        // -------------------- Diet logic --------------------
        dietCombo.addActionListener(dc -> {
            dietDetailsPanel.removeAll();
            String choice = (String) dietCombo.getSelectedItem();
            switch (choice) {
                case "Vegetarian" -> addDietPanel(Food.VEGETARIAN, dietDetailsPanel);
                case "Meat-heavy" -> addDietPanel(Food.MEATHEAVY, dietDetailsPanel);
                case "Balanced" -> addDietPanel(Food.BALANCED, dietDetailsPanel);
            }
            dietDetailsPanel.revalidate();
            dietDetailsPanel.repaint();
        });

        // -------------------- Home Activities logic --------------------
        saveElectricityBtn.addActionListener(e -> {
            try {
                double consumption = Double.parseDouble(electricityField.getText());
                electricityObj = new Electricity(consumption, 0.3);
                updateHomeTotal();
                JOptionPane.showMessageDialog(this, "Electricity data saved!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid electricity input!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        saveCookingBtn.addActionListener(e -> {
            try {
                double usage = Double.parseDouble(fuelUsageField.getText());
                String fuelType = fuelTypeBox.getSelectedItem().toString();
                cookingObj = new Cooking(usage, fuelType);
                updateHomeTotal();
                JOptionPane.showMessageDialog(this, "Cooking data saved!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid cooking input!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // -------------------- Calculate total footprint --------------------
        calculateTotalButton = new JButton("Calculate Total Footprint");
        homeScreen.add(calculateTotalButton);
        calculateTotalButton.addActionListener(ct -> {
            double total = 0.0;
            if (userVehicle != null) total += userVehicle.calculateFootprint();
            if (diet != null) total += diet.calculateFootprint() * 7; // per week
            if (home != null) total += home.calculateFootprint();
            outputArea.append("\n=== Total Footprint: " + total + " kg CO₂ ===\n");
        });

        setVisible(true);
    }

    private void addDietPanel(Food type, JPanel panel) {
        panel.add(new JLabel(type + " meals per week: "));
        mealsPerDayField = new JTextField(10);
        panel.add(mealsPerDayField);
        JButton calculateButton = new JButton("Calculate Diet Footprint");
        panel.add(calculateButton);
        calculateButton.addActionListener(ccb -> {
            try {
                int mealsPerWeek = Integer.parseInt(mealsPerDayField.getText());
                if (mealsPerWeek < 0) {
                    outputArea.append("Meals per week cannot be negative.\n");
                    return;
                }
                diet = new Diet(type, mealsPerWeek);
                double dietFootprint = diet.calculateFootprint() * 7;
                outputArea.append(type + " Diet Footprint: " + dietFootprint + " kg CO₂/week\n");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void updateHomeTotal() {
        if (electricityObj != null || cookingObj != null) {
            home = new Home(electricityObj, cookingObj);
            homeTotalLabel.setText("Home Total Emissions: " +
                    String.format("%.2f", home.calculateFootprint()) + " kg CO₂/year");
        }
    }

    public static void main(String[] args) {
        new CarbonScope();
    }
}

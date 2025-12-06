import javax.swing.*;
import java.awt.*;

public class CarbonScope extends JFrame{

    // Member variables of the CarbonScope app, integrating User, Vehicle, Diet and Home objects
    private User user;
    private Vehicle userVehicle;
    private Diet diet;
    private Home home;
    private Electricity electricity;
    private Cooking cooking;

    // User info fields
    private JTextField userNameField, userLocationField;

    // Vehicle fields
    private JTextField mileageField, fuelConsumptionField;
    private JTextField flightsPerYearField, yearlyUseField;

    // Home activity fields
    private JTextField electricityField;
    
    // For the Diet screen
    private JTextField mealsPerDayField;

    // Output area
    private JTextArea outputArea;

    private JButton homeButton, calculatorButton, summaryButton, tipsButton;
    private JButton vehicleButton, homeActivityButton, dietButton;

    private JButton calculateTotalButton;

    public CarbonScope() {

        setTitle("CARBONSCOPE: Your Carbon Footprint Assistant");
        setSize(900,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Global colours
        Color cream = new Color(255, 247, 230);
        Color leafGreen = new Color(76, 175, 80);
        Color lightCream = new Color(248, 242, 233);

        getContentPane().setBackground(cream);

        // Global font
        UIManager.put("Label.font", new Font("Sagoe UI", Font.PLAIN, 14));
        UIManager.put("Button.font", new Font("Sagoe UI", Font.BOLD, 13));
        UIManager.put("TextField.font", new Font("Sagoe UI", Font.PLAIN, 14));

        // Adding logo
        ImageIcon logo = new ImageIcon("Logo.png");
        setIconImage(logo.getImage());
        JLabel logoLabel = new JLabel(logo);
        add(logoLabel, BorderLayout.NORTH);

        // Left Panel (Navigation)
        JPanel leftPanel = new JPanel(new GridLayout(4,1,10,10));
        leftPanel.setBackground(cream);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        leftPanel.setPreferredSize(new Dimension(150, 200));

        // Main buttons for navigating the app
        homeButton = new JButton("HOME");
        homeButton.setBackground(leafGreen);
        homeButton.setForeground(Color.WHITE);
        homeButton.setBorderPainted(false);
        homeButton.setOpaque(true);

        calculatorButton = new JButton("CALCULATE");
        calculatorButton.setBackground(leafGreen);
        calculatorButton.setForeground(Color.WHITE);
        calculatorButton.setBorderPainted(false);
        calculatorButton.setOpaque(true);

        summaryButton = new JButton("SUMMARY");
        summaryButton.setBackground(leafGreen);
        summaryButton.setForeground(Color.WHITE);
        summaryButton.setBorderPainted(false);
        summaryButton.setOpaque(true);

        tipsButton = new JButton("TIPS");
        tipsButton.setBackground(leafGreen);
        tipsButton.setForeground(Color.WHITE);
        tipsButton.setBorderPainted(false);
        tipsButton.setOpaque(true);

        leftPanel.add(homeButton);
        leftPanel.add(calculatorButton);
        leftPanel.add(summaryButton);
        leftPanel.add(tipsButton);

        add(leftPanel, BorderLayout.WEST);

        // Center panel with content
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(cream);
        CardLayout cardLayout = new CardLayout();
        centerPanel.setLayout(cardLayout);

        // Home screen
        JPanel homeScreen = new JPanel(new GridLayout(3,1,10,10));
        homeScreen.setBackground(lightCream);
        homeScreen.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        homeScreen.add(new JLabel("Name:"));
        userNameField = new JTextField(10);
        homeScreen.add(userNameField);
        userNameField.setBackground(lightCream);
        userNameField.setBorder(BorderFactory.createLineBorder(new Color(230, 220, 210)));

        homeScreen.add(new JLabel("Location:"));
        userLocationField = new JTextField(10);
        homeScreen.add(userLocationField);
        userLocationField.setBackground(lightCream);
        userLocationField.setBorder(BorderFactory.createLineBorder(new Color(230, 220, 210)));

        vehicleButton = new JButton("VEHICLE");
        vehicleButton.setBackground(leafGreen);
        vehicleButton.setForeground(Color.WHITE);
        vehicleButton.setBorderPainted(false);
        vehicleButton.setOpaque(true);

        homeActivityButton = new JButton("HOME ACTIVITIES");
        homeActivityButton.setBackground(leafGreen);
        homeActivityButton.setForeground(Color.WHITE);
        homeActivityButton.setBorderPainted(false);
        homeActivityButton.setOpaque(true);

        dietButton = new JButton("DIET");
        dietButton.setBackground(leafGreen);
        dietButton.setForeground(Color.WHITE);
        dietButton.setBorderPainted(false);
        dietButton.setOpaque(true);

        homeScreen.add(vehicleButton);
        homeScreen.add(homeActivityButton);
        homeScreen.add(dietButton);

        // Vehicle screen
        JPanel vehicleScreen = new JPanel(new BorderLayout());
        vehicleScreen.setBackground(lightCream);
        vehicleScreen.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

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
        JPanel dietScreen = new JPanel(new BorderLayout());
        dietScreen.setBackground(lightCream);
        dietScreen.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        JPanel dietSelectPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        dietScreen.add(new JLabel("Select diet type"));

        String[] dietTypes = {"---","Vegetarian", "Meat-heavy", "Balanced"};
        JComboBox<String> dietCombo = new JComboBox<>(dietTypes);
        dietSelectPanel.add(dietCombo);

        dietScreen.add(dietSelectPanel, BorderLayout.NORTH);

        JPanel dietDetailsPanel = new JPanel();
        dietDetailsPanel.setLayout(new BoxLayout(dietDetailsPanel, BoxLayout.Y_AXIS));
        dietScreen.add(dietDetailsPanel, BorderLayout.CENTER);

        // Home activities screen
        JPanel homeActivityScreen = new JPanel();
        homeActivityScreen.setBackground(lightCream);
        homeActivityScreen.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JPanel activitiesSelectPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        homeActivityScreen.add(new JLabel("Select home activities"));
        String[] homeActivityTypes = {"---","Cooking", "Electricity use"};
        JComboBox<String> homeActivitiesCombo = new JComboBox<>(homeActivityTypes);
        activitiesSelectPanel.add(homeActivitiesCombo);

        homeActivityScreen.add(activitiesSelectPanel, BorderLayout.NORTH);

        JPanel activityDetailsPanel = new JPanel();
        activityDetailsPanel.setLayout(new BoxLayout(activityDetailsPanel, BoxLayout.Y_AXIS));
        homeActivityScreen.add(activityDetailsPanel, BorderLayout.CENTER);

        // Add all screens to center panel
        centerPanel.add(homeScreen, "HOME");
        centerPanel.add(vehicleScreen, "VEHICLE");
        centerPanel.add(dietScreen, "DIET");
        centerPanel.add(homeActivityScreen, "HOME ACTIVITY");

        add(centerPanel, BorderLayout.CENTER);
        
        // Output area
        outputArea = new JTextArea(10,70);
        outputArea.setBackground(lightCream);
        outputArea.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
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

        // This is for the Home layout
        homeActivitiesCombo.addActionListener(vc -> {
            activityDetailsPanel.removeAll();
            String choice = (String) homeActivitiesCombo.getSelectedItem();

            switch (choice) {
                case "Cooking" -> {
                        activityDetailsPanel.add(new JLabel("Monthly Fuel Usage (kg/L):"));
                        fuelConsumptionField = new JTextField(10);
                        fuelConsumptionField.setBackground(lightCream);
                        fuelConsumptionField.setBorder(BorderFactory.createLineBorder(new Color(230, 220, 210)));

                        activityDetailsPanel.add(fuelConsumptionField);

                        activityDetailsPanel.add(new JLabel("Fuel Type:"));
                        String[] fuelType = {"---", "LPG","Charcoal","Firewood"};
                        JComboBox<String> fuelTypeBox = new JComboBox<>(fuelType);
                        activitiesSelectPanel.add(fuelTypeBox);
                        
                        activityDetailsPanel.add(activitiesSelectPanel, BorderLayout.NORTH);

                        JButton calculateCookingButton = new JButton("Calculate Cooking Footprint");
                        calculateCookingButton.setBackground(leafGreen);
                        calculateCookingButton.setForeground(Color.WHITE);
                        calculateCookingButton.setBorderPainted(false);
                        calculateCookingButton.setOpaque(true);

                        activityDetailsPanel.add(calculateCookingButton);

                        calculateCookingButton.addActionListener(e -> {

                    try {

                        double usage = Double.parseDouble(fuelConsumptionField.getText());
                        String fuelCategory = (String) fuelTypeBox.getSelectedItem();

                        cooking = new Cooking(usage, fuelCategory);
                        updateHomeTotal();

                        JOptionPane.showMessageDialog(this, "Cooking data saved!");

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Invalid cooking input!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });
                    
                }
                case "Electricity" -> {

                    activityDetailsPanel.add(new JLabel("Monthly Electricity (kWh):"));
                    electricityField = new JTextField(10);
                    electricityField.setBackground(lightCream);
                    electricityField.setBorder(BorderFactory.createLineBorder(new Color(230, 220, 210)));

                    activityDetailsPanel.add(electricityField);

                    JButton calculateElectricityButton = new JButton("Save Electricity");
                    calculateElectricityButton.setBackground(leafGreen);
                    calculateElectricityButton.setForeground(Color.WHITE);
                    calculateElectricityButton.setBorderPainted(false);
                    calculateElectricityButton.setOpaque(true);

                    activityDetailsPanel.add(calculateElectricityButton);

                    calculateElectricityButton.addActionListener(e -> {

                    try {

                        double consumption = Double.parseDouble(electricityField.getText());
                        electricity = new Electricity(consumption, 0.3);
                        updateHomeTotal();
                        JOptionPane.showMessageDialog(this, "Electricity data saved!");
                        
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Invalid electricity input!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
            });

                }
            }
        });

        // This is for the Diet layout
        dietCombo.addActionListener(vc -> {
            dietDetailsPanel.removeAll();
            String choice = (String) dietCombo.getSelectedItem();

            switch (choice) {

                case "Vegetarian" -> { 
                    dietDetailsPanel.add(new JLabel("Vegetarian meals (per/week): ")); 
                    mealsPerDayField = new JTextField(10);
                    mealsPerDayField.setBackground(lightCream);
                    mealsPerDayField.setBorder(BorderFactory.createLineBorder(new Color(230, 220, 210)));

                    dietDetailsPanel.add(mealsPerDayField);

                    JButton calculateVegButton = new JButton("Calculate Vegetarian Diet Footprint");
                    calculateVegButton.setBackground(leafGreen);
                    calculateVegButton.setForeground(Color.WHITE);
                    calculateVegButton.setBorderPainted(false);
                    calculateVegButton.setOpaque(true);

                    dietDetailsPanel.add(calculateVegButton);

                    calculateVegButton.addActionListener(ccb ->{

                    try {
                        int mealsPerDay = Integer.parseInt(mealsPerDayField.getText());

                        if (mealsPerDay < 0) {
                            outputArea.append("Meals per week cannot be negative.\n");
                            return;
                        }

                        diet = new Diet(Food.VEGETARIAN, mealsPerDay); 
                        double dietFootprint = diet.calculateFootprint() * 7;

                        outputArea.append("Vegetarian Footprint: " + dietFootprint + "kg CO₂/week\n");
                    
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        });
                    }

                case "Meat-heavy" -> {
                    dietDetailsPanel.add(new JLabel("Meat-heavy meals (per/week): ")); 
                    mealsPerDayField = new JTextField(10);
                    mealsPerDayField.setBackground(lightCream);
                    mealsPerDayField.setBorder(BorderFactory.createLineBorder(new Color(230, 220, 210)));

                    dietDetailsPanel.add(mealsPerDayField);

                    JButton calculateMeatButton = new JButton("Calculate Meat-Heavy Diet Footprint");
                    calculateMeatButton.setBackground(leafGreen);
                    calculateMeatButton.setForeground(Color.WHITE);
                    calculateMeatButton.setBorderPainted(false);
                    calculateMeatButton.setOpaque(true);

                    dietDetailsPanel.add(calculateMeatButton);

                    calculateMeatButton.addActionListener(ccb ->{

                    try {
                        int mealsPerDay = Integer.parseInt(mealsPerDayField.getText());

                        if (mealsPerDay < 0) {
                            outputArea.append("Meals per week cannot be negative.\n");
                            return;
                        }

                        diet = new Diet(Food.MEATHEAVY, mealsPerDay); 
                        double dietFootprint = diet.calculateFootprint() * 7;

                        outputArea.append("Vegetarian Footprint: " + dietFootprint + "kg CO₂/week\n");
                    
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        });
                }

                case "Balanced" -> {
                    dietDetailsPanel.add(new JLabel("Balanced meals (per/week): ")); 
                    mealsPerDayField = new JTextField(10);
                    mealsPerDayField.setBackground(lightCream);
                    mealsPerDayField.setBorder(BorderFactory.createLineBorder(new Color(230, 220, 210)));

                    dietDetailsPanel.add(mealsPerDayField);

                    JButton calculateBalancedButton = new JButton("Calculate Balanced Diet Footprint");
                    calculateBalancedButton.setBackground(leafGreen);
                    calculateBalancedButton.setForeground(Color.WHITE);
                    calculateBalancedButton.setBorderPainted(false);
                    calculateBalancedButton.setOpaque(true);

                    dietDetailsPanel.add(calculateBalancedButton);

                    calculateBalancedButton.addActionListener(ccb ->{

                    try {
                        int mealsPerDay = Integer.parseInt(mealsPerDayField.getText());

                        if (mealsPerDay < 0) {
                            outputArea.append("Meals per week cannot be negative.\n");
                            return;
                        }

                        diet = new Diet(Food.BALANCED, mealsPerDay); 
                        double dietFootprint = diet.calculateFootprint() * 7;

                        outputArea.append("Vegetarian Footprint: " + dietFootprint + "kg CO₂/week\n");
                    
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        });
                }
            }

            dietDetailsPanel.revalidate();
            dietDetailsPanel.repaint();
        });

        
        // This is for the vehicle layout
        vehicleCombo.addActionListener(vc -> {
            vehicleDetailsPanel.removeAll();
            String choice = (String) vehicleCombo.getSelectedItem();

            switch (choice) {

                case "Car" -> { 
                    vehicleDetailsPanel.add(new JLabel("Mileage (km/week): ")); 
                    mileageField = new JTextField(10);
                    mileageField.setBackground(lightCream);
                    mileageField.setBorder(BorderFactory.createLineBorder(new Color(230, 220, 210)));

                    vehicleDetailsPanel.add(mileageField);

                    vehicleDetailsPanel.add(new JLabel("Fuel Consumption (L/week): ")); 
                    fuelConsumptionField = new JTextField(10);
                    fuelConsumptionField.setBackground(lightCream);
                    fuelConsumptionField.setBorder(BorderFactory.createLineBorder(new Color(230, 220, 210)));

                    vehicleDetailsPanel.add(fuelConsumptionField);

                    JButton calculateCarButton = new JButton("Calculate Car Footprint");
                    calculateCarButton.setBackground(leafGreen);
                    calculateCarButton.setForeground(Color.WHITE);
                    calculateCarButton.setBorderPainted(false);
                    calculateCarButton.setOpaque(true);
                    
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
                    yearlyUseField.setBackground(lightCream);
                    yearlyUseField.setBorder(BorderFactory.createLineBorder(new Color(230, 220, 210)));

                    vehicleDetailsPanel.add(yearlyUseField);

                    vehicleDetailsPanel.add(new JLabel("Mileage (km/week): ")); 
                    mileageField = new JTextField(10);
                    mileageField.setBackground(lightCream);
                    mileageField.setBorder(BorderFactory.createLineBorder(new Color(230, 220, 210)));

                    vehicleDetailsPanel.add(mileageField);

                    JButton calculateBusButton = new JButton("Calculate Bus Footprint");
                    calculateBusButton.setBackground(leafGreen);
                    calculateBusButton.setForeground(Color.WHITE);
                    calculateBusButton.setBorderPainted(false);
                    calculateBusButton.setOpaque(true);

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
                    flightsPerYearField.setBackground(lightCream);
                    flightsPerYearField.setBorder(BorderFactory.createLineBorder(new Color(230, 220, 210)));

                    vehicleDetailsPanel.add(flightsPerYearField);

                    vehicleDetailsPanel.add(new JLabel("Mileage (km/week): ")); 
                    mileageField = new JTextField(10);
                    mileageField.setBackground(lightCream);
                    mileageField.setBorder(BorderFactory.createLineBorder(new Color(230, 220, 210)));
                    
                    vehicleDetailsPanel.add(mileageField);

                    JButton calculatePlaneButton = new JButton("Calculate Plane Footprint");
                    calculatePlaneButton.setBackground(leafGreen);
                    calculatePlaneButton.setForeground(Color.WHITE);
                    calculatePlaneButton.setBorderPainted(false);
                    calculatePlaneButton.setOpaque(true);

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

        // To model our user
        user = new User(userNameField.getText(), userLocationField.getText(), userVehicle, home, diet, 320);

        calculatorButton.addActionListener(cb -> {
            while (userNameField == null || userNameField.getText().isBlank() || userLocationField == null || userLocationField.getText().isBlank()){
                outputArea.append("Kindly enter your name and location");
            }

            // Calculate screen
            JPanel calculateScreen = new JPanel(new GridLayout(3,1,10,10));
            calculateScreen.setBackground(lightCream);
            calculateScreen.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

            // -------------------- Calculate user's total footprint --------------------
            calculateTotalButton = new JButton("Calculate Total Footprint");
            calculateTotalButton.setBackground(leafGreen);
            calculateTotalButton.setForeground(Color.WHITE);
            calculateTotalButton.setBorderPainted(false);
            calculateTotalButton.setOpaque(true);

            calculateScreen.add(calculateTotalButton);

            calculateTotalButton.addActionListener(ct -> {
                outputArea.append("\n=== Total Footprint: " + user.totalUserFootprint() + " kg CO₂ ===\n");
            });

        });

        summaryButton.addActionListener(cb -> {
            while (userNameField == null || userNameField.getText().isBlank() || userLocationField == null || userLocationField.getText().isBlank()){
                outputArea.append("Kindly enter your name and location");
            }

            // Summary statistics screen
            JPanel summaryScreen = new JPanel(new GridLayout(3,1,10,10));
            summaryScreen.setBackground(lightCream);
            summaryScreen.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

            // -------------------- Summarizing user's total footprint --------------------
            outputArea.append(user.generateSummary());

        });

        tipsButton.addActionListener(cb -> {
            while (userNameField == null || userNameField.getText().isBlank() || userLocationField == null || userLocationField.getText().isBlank()){
                outputArea.append("Kindly enter your name and location");
            }

            // Tips screen
            JPanel tipsScreen = new JPanel(new GridLayout(3,1,10,10));
            tipsScreen.setBackground(lightCream);
            tipsScreen.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

            // -------------------- Tips tailored to a user's total footprint --------------------
            outputArea.append(user.generateTips());
        });

        setVisible(true);
        
    }

    private void updateHomeTotal() {
        if (electricity != null || cooking != null) {
            home = new Home(electricity, cooking);
            outputArea.setText("Home Total Emissions: " + String.format("%.2f", home.calculateFootprint()) + " kg CO₂/year");
        }
    }

    public static void main(String[] args) {
        new CarbonScope();
    }
}

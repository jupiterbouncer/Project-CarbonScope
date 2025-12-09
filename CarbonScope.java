import java.awt.*;
import javax.swing.*;

// Our CarbonScope class will be the GUI
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

    // Summary Area
    private JTextArea summaryArea;

    // Tips Area
    private JTextArea tipsArea;

    // Navigation buttons
    private JButton homeButton, calculatorButton, summaryButton, tipsButton;
    private JButton vehicleButton, homeActivityButton, dietButton;

    // Calculating total footprint button
    private JButton calculateTotalButton;

    public CarbonScope() {

        // Intializing title, size and layout of the GUI
        setTitle("CARBONSCOPE: Your Carbon Footprint Assistant");
        setSize(900,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Global colours
        Color cream = new Color(255, 247, 230);
        Color lightCream = new Color(248, 242, 233);

        getContentPane().setBackground(cream);

        // Global font
        UIManager.put("Label.font", new Font("Sagoe UI", Font.PLAIN, 14));
        UIManager.put("Button.font", new Font("Sagoe UI", Font.BOLD, 13));
        UIManager.put("TextField.font", new Font("Sagoe UI", Font.PLAIN, 14));

        // Adding logo
        ImageIcon logo = new ImageIcon("Logo.png");
        setIconImage(logo.getImage());

        // Left Panel (Navigation)
        JPanel leftPanel = new JPanel(new GridLayout(4,1,10,10));
        leftPanel.setBackground(cream);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        leftPanel.setPreferredSize(new Dimension(150, 200));

        // Main buttons for navigating the app
        // Home button for inputting emission
        homeButton = new JButton("HOME");
        styleButton(homeButton);

        // Calculator button for the total carbon footprint of user
        calculatorButton = new JButton("CALCULATE");
        styleButton(calculatorButton);

        // Summary button that provides a breakdown
        summaryButton = new JButton("SUMMARY");
        styleButton(summaryButton);

        // Tips button that dispenses instructions based on earlier calculated footprints
        tipsButton = new JButton("TIPS");
        styleButton(tipsButton);

        // Adding these buttons to the left panel
        leftPanel.add(homeButton);
        leftPanel.add(calculatorButton);
        leftPanel.add(summaryButton);
        leftPanel.add(tipsButton);

        // Adding the panel (now with buttons) to the GUI
        add(leftPanel, BorderLayout.WEST);

        // Center panel where content will be displayed
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(cream);
        CardLayout cardLayout = new CardLayout();
        centerPanel.setLayout(cardLayout);

        // =============== DASHBOARD ==================== (contains logo, motivation message, statistic)
        JPanel dashboardScreen = new JPanel();
        dashboardScreen.setLayout(new BoxLayout(dashboardScreen, BoxLayout.Y_AXIS));
        dashboardScreen.setBackground(lightCream);
        dashboardScreen.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));

        JLabel logoLabel = new JLabel(logo);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dashboardScreen.add(logoLabel);

        dashboardScreen.add(Box.createRigidArea(new Dimension(20,30)));

        JLabel motivation = new JLabel("Track your impact. Take control!", SwingConstants.CENTER);
        motivation.setFont(new Font("Segoe UI", Font.BOLD, 22));
        motivation.setAlignmentX(Component.CENTER_ALIGNMENT);
        dashboardScreen.add(motivation);

        JButton getStartedButton = new JButton("Get Started");
        styleButton(getStartedButton);
        getStartedButton.setFont(new Font("Sagoe UI", Font.BOLD, 16));
        getStartedButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        dashboardScreen.add(getStartedButton);

        JLabel globalStat = new JLabel("Global Average CO₂ Emissions Per Person: 4.7 tons/year", SwingConstants.CENTER);
        globalStat.setFont(new Font("Segoe UI", Font.ITALIC, 16));
        getStartedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        dashboardScreen.add(globalStat);

        centerPanel.add(dashboardScreen, "DASHBOARD");

        // Once the get started button is pressed, user should be directed to the HOME panel
        getStartedButton.addActionListener(gsb -> {
            cardLayout.show(centerPanel, "HOME");
        });

        // Home screen (default screen) for users to pick which type of footprint to calculate
        JPanel homeScreen = new JPanel(new GridLayout(3,1,10,10));
        homeScreen.setBackground(lightCream);
        homeScreen.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        // Creating a form panel for users to add their name & location
        JPanel formPanel = new JPanel(new GridLayout(2,2,10,10));
        formPanel.setBackground(lightCream);

        // TextField for user's name
        formPanel.add(new JLabel("Name:"));
        userNameField = new JTextField(10);
        userNameField.setBackground(lightCream);
        userNameField.setBorder(BorderFactory.createLineBorder(new Color(230, 220, 210)));
        formPanel.add(userNameField);

        // TextField for user's location
        formPanel.add(new JLabel("Location:"));
        userLocationField = new JTextField(10);
        userLocationField.setBackground(lightCream);
        userLocationField.setBorder(BorderFactory.createLineBorder(new Color(230, 220, 210)));
        formPanel.add(userLocationField);

        // adding these forms to the HOMESCREEN
        homeScreen.add(formPanel, BorderLayout.NORTH);

        // The vehicle button for matters relating to Vehicles
        vehicleButton = new JButton("VEHICLE");
        styleButton(vehicleButton);

        // The home activity button for matters relating to Home
        homeActivityButton = new JButton("HOME ACTIVITIES");
        styleButton(homeActivityButton);

        // The diet button for matters relating to Diet
        dietButton = new JButton("DIET");
        styleButton(dietButton);

        // Adding these buttons to the home screen
        homeScreen.add(vehicleButton);
        homeScreen.add(homeActivityButton);
        homeScreen.add(dietButton);

        // Summary statistics screen
        JPanel summaryScreen = new JPanel(new BorderLayout());
        summaryScreen.setBackground(lightCream);
        summaryScreen.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        // Create summaryArea and put it inside a scroll pane
        summaryArea = new JTextArea(15, 60);
        summaryArea.setEditable(false);
        summaryArea.setLineWrap(true);
        summaryArea.setWrapStyleWord(true);
        summaryArea.setBackground(lightCream);
        summaryArea.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        summaryScreen.add(new JScrollPane(summaryArea), BorderLayout.CENTER);

        centerPanel.add(summaryScreen, "SUMMARY");

        // TIPS SCREEN
        JPanel tipsScreen = new JPanel(new BorderLayout());
        tipsScreen.setBackground(lightCream);
        tipsScreen.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        tipsArea = new JTextArea(15, 60);
        tipsArea.setEditable(false);
        tipsArea.setLineWrap(true);
        tipsArea.setWrapStyleWord(true);
        tipsArea.setBackground(lightCream);
        tipsArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        tipsScreen.add(new JScrollPane(tipsArea), BorderLayout.CENTER);

        centerPanel.add(tipsScreen, "TIPS");

        // Vehicle screen
        JPanel vehicleScreen = new JPanel(new BorderLayout());
        vehicleScreen.setBackground(lightCream);
        vehicleScreen.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        // Users are able to select their type of vehicle to calculate its specific emission
        JPanel vehicleSelectPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        vehicleSelectPanel.add(new JLabel("Select Vehicle Type: "));

        String[] vehicleTypes = {"---","Car", "Bus", "Aeroplane"};
        JComboBox<String> vehicleCombo = new JComboBox<>(vehicleTypes);
        vehicleSelectPanel.add(vehicleCombo);

        vehicleScreen.add(vehicleSelectPanel, BorderLayout.NORTH);

        JPanel vehicleDetailsPanel = new JPanel();
        vehicleDetailsPanel.setLayout(new BoxLayout(vehicleDetailsPanel, BoxLayout.Y_AXIS));
        vehicleScreen.add(vehicleDetailsPanel, BorderLayout.CENTER);

        // ============= Diet screen panel ==============
        JPanel dietScreen = new JPanel(new BorderLayout());
        dietScreen.setBackground(lightCream);
        dietScreen.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        // Users should be select their diet type to calculate its specific emission
        JPanel dietSelectPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dietSelectPanel.add(new JLabel("Select diet type"));

        String[] dietTypes = {"---","Vegetarian", "Meat-heavy", "Balanced"};
        JComboBox<String> dietCombo = new JComboBox<>(dietTypes);
        dietSelectPanel.add(dietCombo);

        dietScreen.add(dietSelectPanel, BorderLayout.NORTH);

        JPanel dietDetailsPanel = new JPanel();
        dietDetailsPanel.setLayout(new BoxLayout(dietDetailsPanel, BoxLayout.Y_AXIS));
        dietScreen.add(dietDetailsPanel, BorderLayout.CENTER);

        // ============== Home activities screen panel ==================
        JPanel homeActivityScreen = new JPanel();
        homeActivityScreen.setBackground(lightCream);
        homeActivityScreen.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        // Users should be select the type of home activity to calculate its specific emission
        JPanel activitiesSelectPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        activitiesSelectPanel.add(new JLabel("Select home activities"));
        
        String[] homeActivityTypes = {"---","Cooking", "Electricity"};
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

        // Adding the center panel ot the GUI
        add(centerPanel, BorderLayout.CENTER);
        
        // Customizing the output area
        outputArea = new JTextArea(10,70);
        outputArea.setBackground(lightCream);
        outputArea.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        outputArea.setEditable(false);

        add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        // Navigation buttons and their action listeners i.e switching screens to the corresponding title
        homeButton.addActionListener(hb -> {
            cardLayout.show(centerPanel, "DASHBOARD");
            centerPanel.revalidate();
            centerPanel.repaint();
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

            // A switch statement to handle the user's choice of Cooking or Electricity
            switch (choice) {
                case "Cooking" -> {
                        activityDetailsPanel.add(new JLabel("Monthly Fuel Usage (kg):"));
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
                        styleButton(calculateCookingButton);

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
                break;
                    
                }
                case "Electricity" -> {

                    activityDetailsPanel.add(new JLabel("Monthly Electricity (kWh):"));
                    electricityField = new JTextField(10);
                    electricityField.setBackground(lightCream);
                    electricityField.setBorder(BorderFactory.createLineBorder(new Color(230, 220, 210)));

                    activityDetailsPanel.add(electricityField);

                    JButton calculateElectricityButton = new JButton("Save Electricity");
                    styleButton(calculateElectricityButton);

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
                break;
                }
            }
        });

        // This is for the Diet layout
        dietCombo.addActionListener(vc -> {
            dietDetailsPanel.removeAll();
            String choice = (String) dietCombo.getSelectedItem();

            switch (choice) {

                case "Vegetarian" -> { 
                    dietDetailsPanel.add(new JLabel("Vegetarian meals (per/day): ")); 
                    mealsPerDayField = new JTextField(10);
                    mealsPerDayField.setBackground(lightCream);
                    mealsPerDayField.setBorder(BorderFactory.createLineBorder(new Color(230, 220, 210)));

                    dietDetailsPanel.add(mealsPerDayField);

                    JButton calculateVegButton = new JButton("Calculate Vegetarian Diet Footprint");
                    styleButton(calculateVegButton);

                    dietDetailsPanel.add(calculateVegButton);

                    calculateVegButton.addActionListener(ccb ->{

                    try {
                        int mealsPerDay = Integer.parseInt(mealsPerDayField.getText());

                        if (mealsPerDay < 0) {
                            outputArea.append("\nMeals per day cannot be negative.\n");
                            return;
                        }

                        diet = new Diet(Food.VEGETARIAN, mealsPerDay);
                        JOptionPane.showMessageDialog(this, "Vegetarian diet data saved!");
                        double dietFootprint = diet.calculateFootprint() ;

                        outputArea.append("\n" + choice + " Footprint: " + dietFootprint + "kg CO₂/month\n");
                    
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        });
                        break;
                    }

                case "Meat-heavy" -> {
                    dietDetailsPanel.add(new JLabel("Meat-heavy meals (per/day): ")); 
                    mealsPerDayField = new JTextField(10);
                    mealsPerDayField.setBackground(lightCream);
                    mealsPerDayField.setBorder(BorderFactory.createLineBorder(new Color(230, 220, 210)));

                    dietDetailsPanel.add(mealsPerDayField);

                    JButton calculateMeatButton = new JButton("Calculate Meat-Heavy Diet Footprint");
                    styleButton(calculateMeatButton);

                    dietDetailsPanel.add(calculateMeatButton);

                    calculateMeatButton.addActionListener(ccb ->{

                    try {
                        int mealsPerDay = Integer.parseInt(mealsPerDayField.getText());

                        if (mealsPerDay < 0) {
                            outputArea.append("\nMeals per day cannot be negative.\n");
                            return;
                        }

                        diet = new Diet(Food.MEATHEAVY, mealsPerDay);
                        JOptionPane.showMessageDialog(this, "Meat-heavy diet data saved!");
                        double dietFootprint = diet.calculateFootprint() ;

                        outputArea.append("\n" + choice + " Footprint: " + dietFootprint + "kg CO₂/month\n");
                    
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        });
                        break;
                }

                case "Balanced" -> {
                    dietDetailsPanel.add(new JLabel("Balanced meals (per/day): ")); 
                    mealsPerDayField = new JTextField(10);
                    mealsPerDayField.setBackground(lightCream);
                    mealsPerDayField.setBorder(BorderFactory.createLineBorder(new Color(230, 220, 210)));

                    dietDetailsPanel.add(mealsPerDayField);

                    JButton calculateBalancedButton = new JButton("Calculate Balanced Diet Footprint");
                    styleButton(calculateBalancedButton);

                    dietDetailsPanel.add(calculateBalancedButton);

                    calculateBalancedButton.addActionListener(ccb ->{

                    try {
                        int mealsPerDay = Integer.parseInt(mealsPerDayField.getText());

                        if (mealsPerDay < 0) {
                            outputArea.append("\nMeals per day cannot be negative.\n");
                            return;
                        }

                        diet = new Diet(Food.BALANCED, mealsPerDay);
                        JOptionPane.showMessageDialog(this, "Balanced diet data saved!");
                        double dietFootprint = diet.calculateFootprint() ;

                        outputArea.append("\n" + choice + " Footprint: " + dietFootprint + "kg CO₂/month\n");
                    
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        });
                        break;
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

                    vehicleDetailsPanel.add(new JLabel("Fuel Type:"));
                    String[] engineType = {"---", "GASOLINE","DIESEL","ELECTRIC","HYBRID"};
                    JComboBox<String> engineTypeBox = new JComboBox<>(engineType);
                    vehicleSelectPanel.add(engineTypeBox);
                        
                    vehicleDetailsPanel.add(vehicleSelectPanel, BorderLayout.NORTH);

                    JButton calculateCarButton = new JButton("Calculate Car Footprint");
                    styleButton(calculateCarButton);
                    
                    vehicleDetailsPanel.add(calculateCarButton);

                    calculateCarButton.addActionListener(ccb ->{

                    try {
                        double mileage = Double.parseDouble(mileageField.getText());
                        double fuelConsumption = Double.parseDouble(fuelConsumptionField.getText());
                        String engineChoice = (String) engineTypeBox.getSelectedItem();

                        if (mileage < 0 || fuelConsumption < 0) {
                            outputArea.append("\nMileage nor fuel cannot be negative.\n");
                            return;
                        }

                        userVehicle = new Car(engineChoice, mileage, fuelConsumption);
                        JOptionPane.showMessageDialog(this, "Car data saved!");
                        double vehicleFootprint =Math.round(userVehicle.calculateFootprint() * 4 * 100) / 100.0;

                        outputArea.append("\nCar Footprint: " + vehicleFootprint + "kg CO₂/month\n");
                    
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        });
                        break;
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
                    styleButton(calculateBusButton);

                    vehicleDetailsPanel.add(calculateBusButton);

                    calculateBusButton.addActionListener(cbb ->{

                    try {
                        int rides = Integer.parseInt(yearlyUseField.getText());
                        double mileage = Double.parseDouble(mileageField.getText());

                        if (rides < 0 || mileage < 0) {
                            outputArea.append("Number of rides nor mileage cannot be negative.\n");
                            return;
                        }

                        userVehicle = new Bus(rides, mileage, 3.4);
                        JOptionPane.showMessageDialog(this, "Bus data saved!");
                        double vehicleFootprint =Math.round(userVehicle.calculateFootprint() * 4 * 100) / 100.0;

                        outputArea.append("Bus Footprint: " + vehicleFootprint + "kg CO₂/month\n");
                    
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        });
                        break;
                    }

                    case "Aeroplane" -> {
                    vehicleDetailsPanel.add(new JLabel("Flights this year: ")); 
                    flightsPerYearField = new JTextField(10);
                    flightsPerYearField.setBackground(lightCream);
                    flightsPerYearField.setBorder(BorderFactory.createLineBorder(new Color(230, 220, 210)));

                    vehicleDetailsPanel.add(flightsPerYearField);

                    vehicleDetailsPanel.add(new JLabel("Average Distance travelled by flight(km): ")); 
                    mileageField = new JTextField(10);
                    mileageField.setBackground(lightCream);
                    mileageField.setBorder(BorderFactory.createLineBorder(new Color(230, 220, 210)));
                    
                    vehicleDetailsPanel.add(mileageField);

                    JButton calculatePlaneButton = new JButton("Calculate Plane Footprint");
                    styleButton(calculatePlaneButton);

                    vehicleDetailsPanel.add(calculatePlaneButton);

                    calculatePlaneButton.addActionListener(cpb ->{

                    try {
                        int flightsPerYear = Integer.parseInt(flightsPerYearField.getText());
                        double mileage = Double.parseDouble(mileageField.getText());

                        if (flightsPerYear < 0 || mileage < 0) {
                            outputArea.append("Number of flights nor mileage cannot be negative.\n");
                            return;
                        }

                        userVehicle = new Aeroplane(flightsPerYear, mileage, 90000); // The average fuel consumption of a plane is 90000L
                        JOptionPane.showMessageDialog(this, "Aeroplane data saved!");
                        double vehicleFootprint =Math.round(userVehicle.calculateFootprint() * 100) / 100.0;

                        outputArea.append("Aeroplane Footprint: " + vehicleFootprint + "kg CO₂/month\n");
                    
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        });
                        break;
                    }
            }
            vehicleDetailsPanel.revalidate();
            vehicleDetailsPanel.repaint();
        });

        // When the calculate button is clicked
        calculatorButton.addActionListener(cb -> {
            if (userNameField.getText().isBlank() || userLocationField.getText().isBlank()){
                JOptionPane.showMessageDialog(this, "Kindly enter your name and location first");
                return;
            }

            // To model our user (need a label to collect kg of waste)
            user = new User(userNameField.getText(), userLocationField.getText(), userVehicle, home, diet, 32);

            // Calculate screen
            JPanel calculateScreen = new JPanel(new GridLayout(3,1,10,10));
            calculateScreen.setBackground(lightCream);
            calculateScreen.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

            centerPanel.add(calculateScreen, "CALCULATE");
            cardLayout.show(centerPanel, "CALCULATE");

            // -------------------- Calculate user's total footprint --------------------
            calculateTotalButton = new JButton("Calculate Total Footprint");
            styleButton(calculateTotalButton);

            calculateScreen.add(calculateTotalButton);

            calculateTotalButton.addActionListener(ctb -> {
                outputArea.append("\n=== Total Footprint: " + Math.round(user.totalUserFootprint() * 100) / 100 + " kg CO₂ ===\n");
            });

            calculateScreen.revalidate();

        });

        // When the summary button is clicked
        summaryButton.addActionListener(sb -> {
            if (userVehicle == null || home == null || diet == null) {
                JOptionPane.showMessageDialog(this, "Please calculate your footprints first");
                return;
            }

            if (user == null) {
                user = new User(
                    userNameField.getText(),
                    userLocationField.getText(),
                    userVehicle,
                    home,
                    diet,
                    32
                );
            } else {
                user.setName(userNameField.getText());
                user.setLocation(userLocationField.getText());
                user.setVehicle(userVehicle);
                user.setHome(home);
                user.setDiet(diet);
            }

            // Get the summary text and guard against empty result
            String summaryText = user.generateSummary();
            if (summaryText == null || summaryText.isBlank()) {
                summaryText = "Summary is empty — please check your input or the generateSummary() method.";
                System.out.println("DEBUG: generateSummary() returned empty for user: " + user);
            }

            // Show summary card and set the text on the summaryArea (not global outputArea)
            cardLayout.show(centerPanel, "SUMMARY");
            summaryArea.setText(summaryText);
            summaryArea.setCaretPosition(0); // scroll to top
            summaryScreen.revalidate();
            summaryScreen.repaint();
        });



        // When the tips button is clicked
        tipsButton.addActionListener(tb -> {
            // Checks if a user object is yet to be created
            if (user == null){
                JOptionPane.showMessageDialog(this, "Please calculate your footprint first");
                return;
            }

            String tipsText = user.generateTips();
            
            // Pick the tips screen from the card layout
            cardLayout.show(centerPanel, "TIPS");

            // Put the tips on the tipsArea, NOT outputArea
            tipsArea.setText(tipsText);
            tipsArea.setCaretPosition(0);
            tipsScreen.revalidate();
            tipsScreen.repaint();
            
        });
        
        setVisible(true);
        
    }

    // Update home object and display total home footprint in output area
    private void updateHomeTotal() {
        if (electricity != null || cooking != null) {
            home = new Home(electricity, cooking);
            outputArea.append("\nHome Total Emissions: " + String.format("%.2f", home.calculateFootprint()) + " kg CO₂/month\n");
        }
    }

    // Helper method to style the buttons
    private void styleButton(JButton button){
        Color leafGreen = new Color(76, 175, 80);
        button.setBackground(leafGreen);
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setOpaque(true);
    }

    public static void main(String[] args) {
        new CarbonScope();
    }
}

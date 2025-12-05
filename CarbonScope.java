import javax.swing.*;
import java.awt.*;

public class CarbonScope extends JFrame {

    // User home data
    private Electricity electricityObj;
    private Cooking cookingObj;
    private Home homeObj;

    // GUI components
    private JTextField electricityField, fuelUsageField;
    private JComboBox<String> fuelTypeBox;
    private JLabel homeTotalLabel;
    private JTextArea outputArea;

    public CarbonScope() {
        setTitle("CARBONSCOPE: YOUR CARBON FOOTPRINT ASSISTANT");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Left navigation panel
        JPanel leftPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        leftPanel.setPreferredSize(new Dimension(150, 200));

        JButton homeButton = new JButton("HOME");
        JButton calculatorButton = new JButton("CALCULATE");
        JButton summaryButton = new JButton("SUMMARY");
        JButton tipsButton = new JButton("TIPS");

        leftPanel.add(homeButton);
        leftPanel.add(calculatorButton);
        leftPanel.add(summaryButton);
        leftPanel.add(tipsButton);
        add(leftPanel, BorderLayout.WEST);

        // Center panel with CardLayout
        JPanel centerPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        centerPanel.setLayout(cardLayout);

        // --- Home screen
        JPanel homeScreen = new JPanel(new GridLayout(3, 1, 10, 10));
        JButton vehicleButton = new JButton("VEHICLE");
        JButton homeActivityButton = new JButton("HOME ACTIVITIES");
        JButton dietButton = new JButton("DIET");
        homeScreen.add(vehicleButton);
        homeScreen.add(homeActivityButton);
        homeScreen.add(dietButton);

        // --- Vehicle screen placeholder
        JPanel vehicleScreen = new JPanel();
        vehicleScreen.add(new JLabel("Vehicle screen placeholder"));

        // --- Diet screen placeholder
        JPanel dietScreen = new JPanel();
        dietScreen.add(new JLabel("Diet screen placeholder"));

        // --- Home Activities screen
        JPanel homeActivityScreen = new JPanel(new BorderLayout());

        JPanel homeMainPanel = new JPanel(new GridLayout(1, 2, 20, 0));

        // LEFT — ELECTRICITY
        JPanel electricityPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        electricityPanel.setBorder(BorderFactory.createTitledBorder("Electricity"));

        electricityField = new JTextField();
        JButton saveElectricityBtn = new JButton("Save Electricity");

        electricityPanel.add(new JLabel("Monthly Electricity (kWh):"));
        electricityPanel.add(electricityField);
        electricityPanel.add(new JLabel("Emission factor fixed for Ghana Grid (0.3 kg CO₂/kWh)"));
        electricityPanel.add(saveElectricityBtn);

        // RIGHT — COOKING
        JPanel cookingPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        cookingPanel.setBorder(BorderFactory.createTitledBorder("Cooking"));

        fuelUsageField = new JTextField();
        fuelTypeBox = new JComboBox<>(new String[]{"LPG", "Charcoal", "Firewood"});
        JButton saveCookingBtn = new JButton("Save Cooking");

        cookingPanel.add(new JLabel("Monthly Fuel Usage (kg/L):"));
        cookingPanel.add(fuelUsageField);
        cookingPanel.add(new JLabel("Fuel Type:"));
        cookingPanel.add(fuelTypeBox);
        cookingPanel.add(saveCookingBtn);

        homeMainPanel.add(electricityPanel);
        homeMainPanel.add(cookingPanel);

        // FOOTPRINT LABEL
        homeTotalLabel = new JLabel("Home Total Emissions: 0.0 CO₂/year");
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(homeTotalLabel);

        homeActivityScreen.add(homeMainPanel, BorderLayout.CENTER);
        homeActivityScreen.add(bottomPanel, BorderLayout.SOUTH);

        // Add all screens to center panel
        centerPanel.add(homeScreen, "HOME");
        centerPanel.add(vehicleScreen, "VEHICLE");
        centerPanel.add(dietScreen, "DIET");
        centerPanel.add(homeActivityScreen, "HOME ACTIVITIES");

        add(centerPanel, BorderLayout.CENTER);

        // Output area at bottom
        outputArea = new JTextArea(8, 70);
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        //  Navigation buttons
        homeButton.addActionListener(e -> cardLayout.show(centerPanel, "HOME"));
        vehicleButton.addActionListener(e -> cardLayout.show(centerPanel, "VEHICLE"));
        dietButton.addActionListener(e -> cardLayout.show(centerPanel, "DIET"));
        homeActivityButton.addActionListener(e -> cardLayout.show(centerPanel, "HOME ACTIVITIES"));

        //  Electricity listener
        saveElectricityBtn.addActionListener(e -> {
            try {
                double consumption = Double.parseDouble(electricityField.getText());
                electricityObj = new Electricity(consumption);
                updateHomeTotal();
                JOptionPane.showMessageDialog(this, "Electricity data saved!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid electricity input!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        //  Cooking listener
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

        setVisible(true);
    }

    //  Update home total emissions
    private void updateHomeTotal() {
        if (electricityObj != null || cookingObj != null) {
            homeObj = new Home(electricityObj, cookingObj);
            homeTotalLabel.setText("Home Total Emissions: " +
                    String.format("%.2f", homeObj.getHomeFootprint()) + " CO₂/year");
        }
    }

    public static void main(String[] args) {
        new CarbonScope();
    }
}

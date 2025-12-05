public class Cooking extends CarbonFootprint {

    private double fuelUsage;
    private String fuelType;

    // Constructor with fuel usage and type
    public Cooking(double fuelUsage, String fuelType) {
        super();  // emissionFactor = 0 initially
        this.fuelUsage = fuelUsage;
        this.fuelType = fuelType;
        setEmissionFactorByFuel(fuelType); // automatically sets emissionFactor
    }

    // Optional: Constructor with explicit emission factor (override)
    public Cooking(double fuelUsage, double emissionFactor) {
        super(emissionFactor);
        this.fuelUsage = fuelUsage;
        this.fuelType = "Custom";
    }

    public double getFuelUsage() {
        return fuelUsage;
    }

    public void setFuelUsage(double fuelUsage) {
        this.fuelUsage = fuelUsage;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
        setEmissionFactorByFuel(fuelType);
    }

    // Set emission factor based on fuel type
    private void setEmissionFactorByFuel(String fuelType) {
        switch (fuelType) {
            case "LPG" -> this.emissionFactor = 2.98;     // kg CO2 per kg
            case "Charcoal" -> this.emissionFactor = 2.83; // kg CO2 per kg
            case "Firewood" -> this.emissionFactor = 1.75; // kg CO2 per kg
            default -> this.emissionFactor = 0.0;          // for electric or unknown
        }
    }

    @Override
    public double calculateFootprint() {
        // Monthly emissions
        return fuelUsage * emissionFactor ; // monthly usage * factor 
    }
}

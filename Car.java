public class Car extends Vehicle {
    // Member field for the Car class
    private String engineType;

    // Constructor to initalise the Car fields as well as the inherited ones using the super keyword
    public Car (String engineType, double mileage, double fuelConsumption){
        super(mileage, fuelConsumption);
        setEngineType(engineType);
    }

    // Getter method
    public String getEngineType(){
        return this.engineType;
    }

    // Setter method
    public void setEngineType(String engineType){
        if (engineType == null || engineType.isBlank()) throw new IllegalArgumentException("Engine type cannot be null nor blank");
    }

    // Implementing specific carbon footprint methods for the Car class to cater to different emission factors of different engines
    @Override
    public double calculateFootprint()
        if (engineType == "DIESEL")) return 2.51 * super.calculateFootprint() * 30;
        if (engineType == ("HYBRID")) return 0.188 * super.calculateFootprint() * 30; 
        if (engineType == ("ELECTRIC")) return 0.083 * super.calculateFootprint() * 30; 
        else return 2.3 * super.calculateFootprint(); // Carbon footprint for a gasoline car
    }
}


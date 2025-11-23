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
}
public class Vehicle extends CarbonFootprint {

    // Member fields for the Vehicle class
    protected double mileage;
    protected double fuelConsumption;

    // Constructor to initalise the Vehicle fields as well as the inherited ones using the super keyword
    public Vehicle (double mileage, double fuelConsumption){
        setMileage(mileage);
        setFuelConsumption(fuelConsumption);
    }

    public Vehicle (double mileage, double fuelConsumption, double emissionFactor){
        super (emissionFactor);
        setMileage(mileage);
        setFuelConsumption(fuelConsumption);
    }

    // Getter methods for the Vehicle class
    public double getMileage(){
        return this.mileage;
    }

    public double getFuelConsumption(){
        return this.fuelConsumption;
    }

    // Setter methods for the Vehicle class
    public void setMileage(double mileage){
        if (mileage < 0) throw new IllegalArgumentException("Mileage cannot be negative");
        else this.mileage = mileage;
    }

    public void setFuelConsumption(double fuelConsumption){
        if (fuelConsumption < 0) throw new IllegalArgumentException("Fuel consumption cannot be negative");
        else this.fuelConsumption = fuelConsumption;
    }

    // Calculating the carbon footprint for a gasoline car
    public double calculateFootprint(){
        return 2.3 * mileage * fuelConsumption;
    }
}


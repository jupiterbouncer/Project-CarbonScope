public class Bus extends Vehicle {
    // Member field for the Bus class
    private int yearlyUse;

    // Constructor to initalise the Bus fields as well as the inherited ones using the super keyword
    public Bus(int yearlyUse, double mileage, double fuelConsumption){
        super(mileage, fuelConsumption);
        setYearlyUse(yearlyUse);
    }

    // Getter method for the Bus class
    public int getYearlyUse(){
        return this.yearlyUse;
    }

    // Setter method for the Bus clas
    public void setYearlyUse(int yearlyUse){
        if (yearlyUse < 0) throw new IllegalArgumentException("Yearly use cannot be negative");
        else this.yearlyUse = yearlyUse;
    }

    @Override
    public double calculateFootprint(){
        return 0.026 * super.calculateFootprint() * yearlyUse;    // The 0.026 is the average footprint of an individual bus passenger
    }
}


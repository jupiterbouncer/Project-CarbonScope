public class Bus extends Vehicle {
    // Member field for the Bus class
    private int yearlyUse;

    // Constructor to initalise the Bus fields as well as the inherited ones using the super keyword
    public Bus(int yearlyUse, int mileage, double fuelConsumption){
        super(mileage, fuelConsumption);
        setYearlyUse(yearlyUse);
    }

    // Getter method
    public int getYearlyUse(){
        return this.yearlyUse;
    }

    // Setter method
    public void setYearlyUse(int yearlyUse){
        if (yearlyUse < 0) throw new IllegalArgumentException("Yearly use cannot be negative");
        else this.yearlyUse = yearlyUse;
    }

    @Override
    public double calculateFootprint(){
        return 0.026 * calculateFootprint() * yearlyUse;
    }
}

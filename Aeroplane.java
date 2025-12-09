public class Aeroplane extends Vehicle{
    // Member field for the Aeroplance class
    private int flightsPerYear;

    // Constructor to initalise the Aeroplane fields as well as the inherited ones using the super keyword
    public Aeroplane(int flightsPerYear, double mileage, double fuelConsumption){
        super(mileage, fuelConsumption);
        setFlightsPerYear(flightsPerYear);
    }

    // Getter method for the Aeroplane class
    public int getFlightsPerYear(){
        return this.flightsPerYear;
    }

    // Setter method for the Aeroplane class
    public void setFlightsPerYear(int flightsPerYear){
        if (flightsPerYear < 0) throw new IllegalArgumentException("Flights per year cannot be negative");
        else this.flightsPerYear = flightsPerYear;
    }

    // Calculating the carbon footprint
    @Override
    public double calculateFootprint(){
        return flightsPerYear * super.calculateFootprint();
    }

}

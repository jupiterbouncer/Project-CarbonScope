public abstract class CarbonFootprint {
    // Member fields for the CarbonFootprint abstract class
    protected double emissionFactor;

    // Constructors to initalise the CarbonFootprint field
    public CarbonFootprint(){}

    public CarbonFootprint(double emissionFactor){
        setEmissionFactor(emissionFactor);
    }

    // Getter method
    public double getEmissionFactor(){
        return this.emissionFactor;
    }

    // Setter method
    public void setEmissionFactor(double emissionFactor){
        if (emissionFactor < 0) throw new IllegalArgumentException("Emission factor cannot be negative");
        else this.emissionFactor = emissionFactor;
    }

    // Abstract method to calculate footprint (implementations may vary)
    public abstract double calculateFootprint();
}
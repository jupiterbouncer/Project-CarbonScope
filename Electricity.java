public class Electricity extends CarbonFootprint {
    private double eletricityConsumption;
    private double emissionFactor= 0; // CHANGE THIS TO ACTUAL EMISSION FACTOR
    
    public Electricity(double eletricityConsumption,double emissionFactor){
        super(emissionFactor);
        this.eletricityConsumption = eletricityConsumption;

    }

    // get/set emissionfactor will be inherited
    @Override
    public double calculateFootprint(){
        return eletricityConsumption * emissionFactor;
    }
}

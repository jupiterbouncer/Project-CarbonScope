public class Electricity extends CarbonFootprint {

    private double electricityConsumption;//Eletricity consumed in KWHr in a motnh

    public Electricity(double electricityConsumption, double emissionFactor) {
        super(0.3);//emission factor for Ghana Grid eletricity distribution
        this.electricityConsumption = electricityConsumption;
    }

    @Override
    public double calculateFootprint() {
        return electricityConsumption * emissionFactor;
    }

    public double getElectricityConsumption() {
        return electricityConsumption;
    }

    public void setElectricityConsumption(double electricityConsumption) {
        this.electricityConsumption = electricityConsumption;
    }
}

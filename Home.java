public class Home extends CarbonFootprint {

    private Electricity electricity;
    private Cooking cooking;

    public Home(Electricity electricity, Cooking cooking) {
        this.electricity = electricity;
        this.cooking = cooking;
    }

    // Getters
    public Electricity getElectricity() {
        return electricity;
    }

    public Cooking getCooking() {
        return cooking;
    }

    // Setters
    public void setElectricity(Electricity electricity) {
        this.electricity = electricity;
    }

    public void setCooking(Cooking cooking) {
        this.cooking = cooking;
    }

    // Implement abstract method from CarbonFootprint
    @Override
    public double calculateFootprint() {
        double total = 0;
        if (electricity != null) total += electricity.calculateFootprint();
        if (cooking != null) total += cooking.calculateFootprint();
        return total;
    }
}

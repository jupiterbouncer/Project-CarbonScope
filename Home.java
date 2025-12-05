public class Home {

    private Electricity electricity;
    private Cooking cooking;
    private double homeFootprint;

    public Home(Electricity electricity, Cooking cooking) {
        this.electricity = electricity;
        this.cooking = cooking;
        calculateHomeFootprint();
    }

    // Getters
    public Electricity getElectricity() {
        return electricity;
    }

    public Cooking getCooking() {
        return cooking;
    }

    public double getHomeFootprint() {
        return homeFootprint;
    }

    // Setters
    public void setElectricity(Electricity electricity) {
        this.electricity = electricity;
        calculateHomeFootprint();
    }

    public void setCooking(Cooking cooking) {
        this.cooking = cooking;
        calculateHomeFootprint();
    }

    // Automatically calculate total home footprint
    private void calculateHomeFootprint() {
        double total = 0;
        if (electricity != null) total += electricity.calculateFootprint();
        if (cooking != null) total += cooking.calculateFootprint();
        this.homeFootprint = total;
    }
}

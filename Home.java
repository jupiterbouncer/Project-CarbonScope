public class Home {
    private Electricity electricity;
    private Cooking cooking;
    private double homeFootprint;

    public Home(Electricity electricity,Cooking cooking, double homeFootprint){
        this.electricity=electricity;
        this.cooking=cooking;
        this.homeFootprint=homeFootprint;
    }


    public Electricity getEletricity(){
        return electricity;
    }

    public Cooking getCooking(){
        return cooking;
    }

    public void setHomeFootprint(double homeFootprint){
        this.homeFootprint=homeFootprint;
    }

    public void setElectricity(Electricity electricity){
        this.electricity=electricity;
    }

    public void setCooking(Cooking cooking){
        this.cooking=cooking;
    }

 }


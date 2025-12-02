public class User {  
    private String name; 
    private String location;
    private Vehicle vehicle; 
    private Home home; 
    private Diet diet; 
    private double kgofWaste;  

    public User(String name, String location, Vehicle vehicle, Home home, Diet diet, double kgofWaste) { 
        this.name = name;
        thsi.location = location; 
        this.vehicle = vehicle;
        this.home = home;
        this.diet = diet; 
        this.kgofWaste = kgofWaste; 

    } 

    public double totalUserFootprint(){
        //MUST IMPLEMENT THIS
    }; 

    public void addActivity(CarbonFootprint a){ 
        //MUST IMPLEMENT THIS 
    }; 

    public String getName() { 
        return this.name; 
    } 
    public String getLocation() { 
        return this.location;
    } 
    public Vehicle getVehicle(){ 
        return this.vehicle;
    } 

    public Diet getDiet(){ 
        return this.diet; 
    } 

    public double getKgofWaste(){
        return this.kgofWaste;
    } 
    public Home getHome(){
        return this.home;
    } 
    
    public void setName(String name){ 
        this.name = name;
    } 
    public void setLocation(String location){ 
        this.location = location;
    } 

    public void setDiet(Diet diet){
        this.diet = diet;
    } 
    public void setHome(Home home){ 
        this.home = home; 
    } 
    public void setKgofWaste(double kgofWaste){ 
        this.kgofWaste = kgofWaste; 
    } 
    public void setVehicle(Vehicle vehicle){ 
        this.vehicle = vehicle;
    }

}
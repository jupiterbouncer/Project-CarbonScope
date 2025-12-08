public class User implements EmissionFootprintSummary {
    //storing user name and location
    private String name;
    private String location;
    //calling the vehicle, home and diet objects as member variables
    private Vehicle vehicle; 
    private Home home; 
    private Diet diet;

    private double kgofWaste;  

    //constructor for the user class
    public User(String name, String location, Vehicle vehicle, Home home, Diet diet, double kgofWaste) { 
        this.name = name;
        this.location = location; 
        this.vehicle = vehicle;
        this.home = home;
        this.diet = diet; 
        this.kgofWaste = kgofWaste; 

    } 
    //method that calculates the total footprint based on the vehicle, home and diet footprint
    public double totalUserFootprint(){
        double total = 0.0; 
        if (this.vehicle != null) { 
            total += this.vehicle.calculateFootprint(); 
        } 
        if (this.home != null) {
            total += this.home.calculateFootprint(); 
        } 
        if(this.diet != null) { 
            total += this.diet.calculateFootprint(); 
        } 
        total += (this.kgofWaste * 0.5); // Research shows on average, 0.5 C02 emission per kg of waste 
        return total;
    }

    public void addActivity(CarbonFootprint a){ 
        if (a != null) { 
            if (a instanceof Vehicle) { 
                this.vehicle = (Vehicle) a;  
            } else if (a instanceof Home) { 
                this.home = (Home) a;
            } else if (a instanceof Diet) { 
                this.diet = (Diet) a; 
            }
        }
    }; 
    //getters and setters for the various member variables
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

    //this method generates a summary of the user footprint
    @Override
    public String generateSummary() { 
        double wasteFactor = 0.5;
        double wasteFootprint = this.kgofWaste * wasteFactor; 
        
       String summary = 
       "Carbon Footprint for: " + this.name + "\n" + 
       "Location: " + this.location + "\n" + 
       "Vehicle Footprint : " + vehicle.calculateFootprint() + " kg CO2\n" + 
       "Home Footprint: " + home.calculateFootprint() + " kg CO2\n" + 
       "Diet Footprint:" + diet.calculateFootprint() + " kg CO2\n" + 
       "Waste Footprint: " + wasteFootprint + " kg C02\n" + 
       "Total Carbon Footprint : " + totalUserFootprint() + " kgC02\\month\n"; 
       return summary;
    }

    // this method generates the tips for the user based on the calculated footprints
    @Override
    public String generateTips() {
    double wasteFactor = 0.5;
    double vehicleFootprint = vehicle.calculateFootprint();
    double homeFootprint = home.calculateFootprint();
    double dietFootprint = diet.calculateFootprint();
    double wasteFootprint = kgofWaste * wasteFactor; 

    String tips = "Carbon Footprint Reduction Tips for " + name + ":\n"; 

    if (vehicleFootprint > 1000) { 
        tips += "- Consider using public transportation, reducing car travel, or switching to a more fuel efficient or electric vehicle.\n"; 
    }  
    if(homeFootprint > 400) { 
        tips += "- Improve home energy efficiency by using energy-efficient appliances, insulating your home, and switching to renewable energy sources.\n";
    } 
    if (dietFootprint > 100) { 
        tips += "- Adopt a more plant-based diet, reduce meat consumption, and minimize food waste.\n";  
    } 
    if (wasteFootprint > 100) { 
        tips += "- Reduce waste by recycling, composting, and minimizing single-use plastics.\n"; 
    } 
    return tips; 

}  
} 

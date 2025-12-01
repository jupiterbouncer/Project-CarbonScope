
public class Food extends CarbonFootprint {
    //attributes
    private String dietType; // example : meat-heavy,vegan etc


    //Food Constructor

    public Food(String dietType, double emissionFactor){
        super(emissionFactor);
        this.dietType = dietType;

    }

    public String getDietType() {
        return dietType;
    }

    public void setDietType(String dietType) {
        this.dietType = dietType;
    }

    @Override
    public double calculateFootprint(){
        if (dietType=="meat-heavy"){
            return 0.0; //CHANGE THIS TO FOOTPRINT OF THE DIET!!!
        }

        if (dietType=="vegan"){
            return 0.0; //CHANGE THIS TO FOOTPRINT OF THE DIET!!!
        }

        if (dietType=="balanced"){
            return 0.0; //CHANGE THIS TO FOOTPRINT OF THE DIET!!!
        }

        else {
            return 0;
        }

    }


}

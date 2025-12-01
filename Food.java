package com.example2;

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
        return emissionFactor *;
    }


}

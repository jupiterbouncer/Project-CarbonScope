package com.example2;

public class Food extends CarbonFootprint {
    //attributes
    private String dietType; // example : meat-heavy,vegan etc
    private double amount; // amount of diet type consumed 


    //Food Constructor

    public Food(String dietType, double emissionFactor, double amount){
        super(emissionFactor);
        this.dietType = dietType;
        this.amount = amount;

    }

    public String getDietType() {
        return dietType;
    }

    public void setDietType(String dietType) {
        this.dietType = dietType;
    }

    public double getAmount(){
        return amount;
    }

    public void setAmount(){
        this.amount = amount;
    }

    @Override
    public double calculateFootprint(){
        return emissionFactor * amount;
    }


}


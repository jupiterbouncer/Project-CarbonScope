package com.example2;

public class Diet {

    //attributes
    private Food foodCategory;
    private double dietFootprint;


    //Constructor
    public Diet(Food foodCategory){
        this.foodCategory =foodCategory;

    }

    public Food  getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(Food foodCategory) {
        this.foodCategory = foodCategory;
    }

    public double getDietFootprint(){
        return foodCategory.calculateFootprint();

    }

    public void setDietFootprint(double dietFootprint){
        this.dietFootprint = dietFootprint;

    }

}


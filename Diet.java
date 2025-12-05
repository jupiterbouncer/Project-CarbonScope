public class Diet extends CarbonFootprint {

    //attributes
    private Food foodCategory;
    private int mealsPerDay;

    //Constructor
    public Diet(Food foodCategory, int mealsPerDay){
        this.foodCategory =foodCategory;
        this.mealsPerDay = mealsPerDay;
    }

    public Food getFoodCategory() {
        return this.foodCategory;
    }

    public int getmealsPerDay(){
        return this.mealsPerDay;
    }

    public void setFoodCategory(Food foodCategory) {
        if (foodCategory == null) throw new IllegalArgumentException("Food category cannot be null");
        this.foodCategory = foodCategory;
    }

    public void setMealsPerDay(int mealsPerDay){
        if (mealsPerDay < 0) throw new IllegalArgumentException("Meals per day cannot be negative");
        this.mealsPerDay = mealsPerDay;
    }

    @Override
    public double calculateFootprint(){
        if (foodCategory.equals(Food.MEATHEAVY)) return 3.6 * mealsPerDay;

        if (foodCategory.equals(Food.VEGETARIAN)) return 0.6 * mealsPerDay; 

        if (foodCategory.equals(Food.BALANCED)) return 0.75 * mealsPerDay; 

        else return 1;
    }

}

enum Food{
    VEGETARIAN,
    MEATHEAVY,
    BALANCED
}


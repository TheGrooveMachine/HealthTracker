package skeleton.model;

import java.util.ArrayList;
import java.util.Map;

import skeleton.Food;

public class BasicFood implements Food{

     /**
     * List of parents that have a reference to this food
     */
    private ArrayList<Food> usedIn = new ArrayList<Food>();

    private String name;
    private double calories;
    private double fat;
    private double carbs;
    private double protein;

    public BasicFood (String name, double calories, double fat,  double carbs, double protein) {
        this.setName(name);
        this.setCalories(calories);
        this.setFat(fat);
        this.setCarbs(carbs);
        this.setProtein(protein);
    }


    @Override
    public void addReference (Food food) {
        usedIn.add(food);
    }

    @Override
    public void removeReference (Food food) {
        usedIn.remove(food);
    }

    @Override
    public ArrayList<Food> getReferences(){
        return usedIn;
    }



    @Override
    public void setName (String name){
        this.name = name;
    }

    @Override
    public void setCalories (double cal){
        this.calories = cal;
    }

    @Override
    public void setFat (double fat){
        this.fat = fat;
    }

    @Override
    public void setCarbs (double carbs){
        this.carbs = carbs;
    }

    @Override
    public void setProtein (double protein){
        this.protein = protein;
    }


    @Override    
    public String getName (){
        return this.name;
    }

    @Override
    public double getCalories (){
        return this.calories;
    }

    @Override
    public double getFat (){
        return this.fat;
    }

    @Override
    public double getCarbs (){
        return this.carbs;
    }

    @Override
    public double getProtein (){
        return this.protein;
    }

    @Override
    public String toString(){
        return "\nname: " + this.getName() +  "\ncalories: " + this.getCalories() + 
        "\nfat: " + this.getFat() + "\ncarbs: " + this.getCarbs() + "\nprotein: " + this.getProtein() + "\n";
    }


    @Override
    public Map<String, Double> getAll() {
        return Map.of(
            "calories", getCalories(),
            "fat", getFat(),
            "carbs", getCarbs(),
            "protein", getProtein()
        );
    }
}

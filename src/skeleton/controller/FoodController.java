package skeleton.controller;

import java.util.Map;

import skeleton.model.Data;
import skeleton.model.FoodCollection;
import skeleton.model.Recipe;

public class FoodController {
    private FoodCollection fc;
    private Data type;

    public FoodController(FoodCollection fc, Data type) {
        this.fc = fc;
        this.type = type;
    }

    /**
     * Search for a given name in the food list
     * 
     * @param name the name to search for as a string
     * @return the info on food as a String
     */
    public String find(String name) {
        return fc.find(name).toString();
    }

    /**
     * Add a basic food to the food list
     * 
     * @param name     the name of the food as a string
     * @param calories the amount of calories as a double
     * @param fat      the amount of fat as a double
     * @param carbs    the amount of carbs as a double
     * @param protein  the amount of protein as a double
     * @return void
     */
    public void addBasicFood(String name, double calories, double fat, double carbs, double protein) {
        fc.addBasicFood(name, calories, fat, carbs, protein);
    }

    public void setName(String name, String newName) {
        fc.find(name).setName(newName);
    }

    public void setCalories(String name, double cal) {
        fc.find(name).setCalories(cal);;
    }


    public void setFat(String name, double fat) {
        fc.find(name).setFat(fat);
    }

    
    public void setCarbs(String name, double carbs) {
        fc.find(name).setCarbs(carbs);
    }


    public void setProtein(String name, double protein) {
        fc.find(name).setProtein(protein);
    }

    /**
     * Add a recipe to the food list
     * 
     * @param name      the name of the recipe as a string
     * @param foodNames Array of food names in string form
     */
    public void addRecipe(String name, Map<String, Double> foodNames) {
        fc.addRecipe(name, foodNames);
    }

    /**
     * Remove a food from the food list
     * 
     * @param name the name of the food as a string
     */
    public void removeFood(String name) {
        fc.removeFood(name);
    }

    public String showAllFoods() {
        return fc.toString();
    }

    public String getBaseFoods(String name){
        return ((Recipe)fc.find(name)).getBaseFoods().toString();
    }

    public void addBaseFood(String recipeName, String foodName, Double servings){
        ((Recipe)fc.find(recipeName)).addBaseFood(fc.find(foodName), servings);
    }

    public void removeBaseFood(String recipeName, String foodName){
        ((Recipe)fc.find(recipeName)).removeBaseFood(fc.find(foodName));
    }

    public void setServings (String recipeName, String foodName, double servings){
        ((Recipe)fc.find(recipeName)).setServings(fc.find(foodName), servings);
    }

    /**
     * save food collection to a data file
     * 
     * @return void
     */
    public void saveToFile() {
        new DataFileController(fc, type).saveFoods();
    }
}

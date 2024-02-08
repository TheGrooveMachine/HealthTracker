package skeleton.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import skeleton.Food;

public class Recipe implements Food {

    /**
     * List of parents that have a reference to this food
     */
    private ArrayList<Food> usedIn = new ArrayList<Food>();
     /**
     * Map of references to children this food has
     */
    private Map<Food, Double> baseFoods = new HashMap<Food, Double>(); // Food is the food object, Double is the
                                                                       // servings of the food

    private String name;
    private double calories;
    private double fat;
    private double carbs;
    private double protein;

    public Recipe(String name, Map<Food, Double> baseFoods) {
        this.setName(name);
        this.baseFoods = baseFoods;
        for (Map.Entry<Food, Double> entry : baseFoods.entrySet()) {
            entry.getKey().addReference(this);
        }
    }

    /**
     * Add a food to the baseFood map
     * 
     * @param base     food object that is being added as a base food
     * @param servings double that represents the servings of the food in the recipe
     * @return void
     */
    public void addBaseFood(Food base, double servings) {
        baseFoods.put(base, servings);
        base.addReference(this);
    }

    /**
     * Remove a food from the baseFood map
     * 
     * @param base food object that is being removed as a base food
     * @return void
     */
    public void removeBaseFood(Food base) {
        baseFoods.remove(base);
        base.removeReference(this);
    }

    /**
     * Get all foods that are in the recipe along with servings
     * 
     * @return Map<Food, Double> Food is the food, Double is the serving
     */
    public Map<Food, Double> getBaseFoods() {
        return baseFoods;
    }

    public void setServings (Food baseFood, double servings) {
        for (Map.Entry<Food, Double> foods : baseFoods.entrySet()) {
            if (foods.getKey().equals(baseFood)){
                foods.setValue(servings);
            }
        }
    }

    @Override
    public void addReference(Food food) {
        usedIn.add(food);
    }

    @Override
    public void removeReference(Food food) {
        usedIn.remove(food);
    }

    @Override
    public ArrayList<Food> getReferences() {
        return usedIn;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setCalories(double cal) {
        this.calories = cal;
    }

    @Override
    public void setFat(double fat) {
        this.fat = fat;
    }

    @Override
    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    @Override
    public void setProtein(double protein) {
        this.protein = protein;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getCalories() {
        double ret = 0;
        for (Map.Entry<Food, Double> foods : baseFoods.entrySet()) {
            ret += foods.getKey().getCalories() * foods.getValue();
        }
        return ret;
    }

    @Override
    public double getFat() {
        double ret = 0;
        for (Map.Entry<Food, Double> foods : baseFoods.entrySet()) {
            ret += foods.getKey().getFat() * foods.getValue();
        }
        return ret;
    }

    @Override
    public double getCarbs() {
        double ret = 0;
        for (Map.Entry<Food, Double> foods : baseFoods.entrySet()) {
            ret += foods.getKey().getCarbs() * foods.getValue();
        }
        return ret;
    }

    @Override
    public double getProtein() {
        double ret = 0;
        for (Map.Entry<Food, Double> foods : baseFoods.entrySet()) {
            ret += foods.getKey().getProtein() * foods.getValue();
        }
        return ret;
    }

    @Override
    public String toString() {
        return "name: " + this.getName() + ", calories: " + this.getCalories() +
                ", fat: " + this.getFat() + ", carbs: " + this.getCarbs() + ", protein: " +
                this.getProtein() + ", base foods: " + this.getBaseFoods().toString();
    }

    @Override
    public Map<String, Double> getAll() {
        return Map.of(
                "calories", getCalories(),
                "fat", getFat(),
                "carbs", getCarbs(),
                "protein", getProtein());
    }
}

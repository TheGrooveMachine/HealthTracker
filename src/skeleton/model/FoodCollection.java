package skeleton.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import skeleton.Food;

public class FoodCollection extends Observable {

    private List<Food> foodList;

    public FoodCollection() {
        foodList = new ArrayList<Food>();
    }

    public FoodCollection(Food[] foods) {
        foodList = new ArrayList<Food>(Arrays.asList(foods));
    }

    /**
     * Search for a given name in the food list
     * 
     * @param name the name to search for as a string
     * @return the food if found or null if not
     */
    public Food find(String name) {
        for (Food food : foodList) {
            if (food.getName().equals(name)) {
                return food;
            }
        }
        return null;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    /**
     * Add a basic food to the food list.
     * Also update observers about the change to the foodList.
     * 
     * @param name     the name of the food as a string
     * @param calories the amount of calories as a double
     * @param fat      the amount of fat as a double
     * @param carbs    the amount of carbs as a double
     * @param protein  the amount of protein as a double
     * @return void
     */
    public void addBasicFood(String name, double calories, double fat, double carbs, double protein) {
        if (foodList.add(new BasicFood(name, calories, fat, carbs, protein))) {
            setChanged();
            notifyObservers();
        }
    }

    /**
     * Add a recipe to the food list
     * Also update the observers about the change to the foodList
     * 
     * @param name      the name of the recipe as a string
     * @param foodNames Array of food names in string form
     */
    public void addRecipe(String name, Map<String, Double> foodNames) {
        Map<Food, Double> bases = new HashMap<Food, Double>();
        ArrayList<Food> refs = new ArrayList<Food>();
        for (Map.Entry<String, Double> foods : foodNames.entrySet()) {
            Food base = this.find(foods.getKey());
            refs.add(base);
            bases.put(base, foods.getValue());
        }
        Food newr = new Recipe(name, bases);
        for (Food ref : refs) {
            ref.addReference(newr);
        }
        if (foodList.add(newr)) {
            setChanged();
            notifyObservers();
        }
    }

    /**
     * Remove a food from the food list
     * Also update the observers about the change to the foodList
     * 
     * @param name the name of the food as a string
     */
    public void removeFood(String name) {
        Food food = this.find(name);
        ArrayList<Food> refs = food.getReferences();
        int len = refs.size();
        if (len > 0) {
            for (int i = 0; i < len; i++) {
                removeFood(refs.get(i).getName());
            }
        }
        if (foodList.remove(food)) {
            setChanged();
            notifyObservers();
        }
    }


    /**
     * save food collection to a data file
     * 
     * @return void
     */
    public void saveToFile(Data type) {
        type.saveFoods(foodList.toArray(new Food[foodList.size()]));
    }

    /**
     * toString method for FoodCollection
     * 
     * @return Formatted string with info on all foods
     */
    @Override
    public String toString() {
        int len = foodList.size();
        String ret = "";
        for (int i = 0; i < len; i++) {
            ret += foodList.get(i).toString();
            ret += "\n";
        }

        return ret;
    }
}

package skeleton;

import java.util.ArrayList;
import java.util.Map;

public interface Food {

    /**
     * Add a reference to where this food is used
     * @param food food object that is being refering to this food
     * @return void
     */
    public void addReference (Food food);

    /**
     * Remove a reference to where this food is used
     * @param food food object that is no longer refering to this food
     * @return void
     */
    public void removeReference (Food food) ;

     /**
     * Gets the references to where this food is used
     * @return ArrayList<Food>
     */
    public ArrayList<Food> getReferences();



    /**
     * Set the name of the food
     * @param name the name as a string to set
     * @return void
     */
    public void setName (String name);

     /**
     * Set the calories of the food
     * @param cal the amount of calories to set as a double
     * @return void
     */
    public void setCalories (double cal);

    /**
     * Set the fat of the food
     * @param fat the amount of fat to set as a double
     * @return void
     */
    public void setFat (double fat);

    /**
     * Set the carbs of the food
     * @param carbs the amount of carbs to set as a double
     * @return void
     */
    public void setCarbs (double carbs);

    /**
     * Set the protein of the food
     * @param protien the amount of protein to set as a double
     * @return void
     */
    public void setProtein (double protein);
    

    /**
     * Get the name of the food
     * @return The name of the food
     */
    public String getName ();

    /**
     * Get the calories of the food
     * @return The calories of the food as a double
     */
    public double getCalories ();

    /**
     * Get the fat of the food
     * @return The fat of the food as a double
     */
    public double getFat ();

    /**
     * Get the carbs of the food
     * @return The carbs of the food as a double
     */
    public double getCarbs ();

    /**
     * Get the protein of the food
     * @return The protein of the food as a double
     */
    public double getProtein ();

    /**
     * Get all of the attributes as a Map
     * @return a map containing calories, fat, carbs, and protein
     */
    public Map<String, Double> getAll();

     /**
     * toString method for Foods
     * @return Formated string with info a food
     */
    public String toString();

}

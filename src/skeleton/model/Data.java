package skeleton.model;

import skeleton.Exercise;
import skeleton.Food;

public interface Data {
    
    /**
     * Get the foods from storage
     * 
     * @return A FoodCollection containing the foods loaded from storage (or an empty array if it's empty)
     */
    public Food[] loadFoods();

    /**
     * Write the foods to the storage.
     * Note that the foods should be in a sane order - that is, Recipes should only contain Foods that come before
     * it in the array.
     * 
     * @param foods the Foods to save
     * @return true if the data was saved successfully
     */
    public boolean saveFoods(Food[] foods);

    /**
     * Get the DailyLog from the storage. Note that loadDailyLogs will only use foods that have been saved (with saveFoods) previously.
     * 
     * @return A DailyLog containing the logs loaded from storage
     */
    public Log[] loadDailyLog();

    /**
     * Write the foods to the storage
     * 
     * @param foods the FoodCollection to save
     * @return true if the DailyLog was saved successfully
     */
    public boolean saveDailyLog(Log[] logs);

    /**
     * Load the exercises to storage
     * 
     * @return an array of exercises
     */
    public Exercise[] loadExercises();

    /**
     * Save the exercises to storage
     * 
     * @param exercises
     * @return true 
     */
    public boolean saveExercises(Exercise[] exercises);
}

package skeleton.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import skeleton.Food;

public class DailyLog extends Observable{
    private FoodCollection foodList;

    private ArrayList<Log> dayLog = new ArrayList<Log>();
    
    /**
     * Search Daily Log by date
     * @return a List of the logs that have the same date
     */
    public List<Log> searchDailyDate(LocalDate date){

        List<Log> logs = new ArrayList<Log>();
        for (Log log : dayLog) {
            if(log.getDate().equals(date)){
                logs.add(log);
            }
        }

        return logs;
    }

    /**
     * Search for a Log, and return that Log if it was found. Otherwise, return null
     * 
     * @param log the log to search for
     * @return null if the Log was not found, otherwise return the Log
     */
    public Log searchDailyLog(Log log){
        //Log[] logarr;
        //in future return an Array of Logs since there can be multiple in one day.
        
        for (Log logs : dayLog) {
            if(logs == log){ 
                return logs;
            }
        }

        return null;
    }

    /**
     * Adds into the daily log in memory.
     * Also update the observers that there has been a change to the log
     * 
     */
    public void addDailyLog(Log log){
        if(dayLog.add(log)) {
            setChanged();
            notifyObservers();
        }
    }
    
    /**
     * Remove all DailyLogs that have the same date
     * Also, update observers that there was a change to to the logs
     * 
     * @param date the date
     * @return true if the daily log was successfully removed
     */
    public boolean removeDailyLogDate(LocalDate date){
        boolean success = false;
        List<Log> logs = searchDailyDate(date);
        for (Log logi : logs) {
            if(dayLog.remove(logi)) {
                success = true;
            } else {
                success = false;
                System.err.println("There was a problem removing the log: " + logi.toString());
            }
        }
        if (success) {
            setChanged();
            notifyObservers();
        }
        return success;
    }
    /**
     * Remove a log, given that log
     * @param log the log to remove
     * @return true if the log was removed
     */
    public boolean removeDailyLogLog(Log log){
        boolean success = false;
        Log logi = this.searchDailyLog(log);
        success = dayLog.remove(logi);
        if (success) {
            setChanged();
            notifyObservers();
        }
        return success;
    }

    /**
     * Get all of the nutrition information for a particular date.
     * The keys of the map are "calories", "fat", "carbs", and "protein"
     * 
     * @return a Map<String, Double> containing all of the attributes of the foods for that date.
     */
    public Map<String, Double> getNutrition(LocalDate date) {
        List<Log> logs = searchDailyDate(date);
        double calories = 0.0;
        double fat = 0.0;
        double carbs = 0.0;
        double protein = 0.0;
        for (Log log : logs) {
            if (log instanceof FoodLog){ // Only use FoodLogs
                FoodLog foodLog = (FoodLog) log; //cast to FoodLog
                Food food = foodLog.getFood();
                // Add all of the foods to the totals
                calories += food.getCalories();
                fat += food.getFat();
                carbs += food.getCarbs();
                protein += food.getProtein();
            }
            // If it's not a food log, skip it
        }
        return Map.of(
            "calories", calories,
            "fat", fat,
            "carbs", carbs,
            "protein", protein
        );
    }

    @Override
    public String toString(){
        int len = dayLog.size();
        String ret = "";
        for(int i = 0; i < len; i++){
            ret+= dayLog.get(i).toString();
            ret += "\n";
        }

        return ret;
    }

    private Log[] logToArray(){
        int len = dayLog.size();
        Log[] logarr = new Log[len];
        for(int i =0;i<len;i++){
            logarr[i] = dayLog.get(i);
        }
        return logarr;
    }

    public boolean writeTo(){
        Data logs = new CSV();
        return logs.saveDailyLog(logToArray());
    }
}

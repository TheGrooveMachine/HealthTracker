package skeleton.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;

import com.opencsv.*;
import com.opencsv.exceptions.CsvException;

import skeleton.Exercise;
import skeleton.Food;

public class CSV implements Data {
    private final String FOODS_CSV = "foods.csv";
    private final String LOG_CSV = "log.csv";
    private final String EXERCISE_CSV = "exercise.csv";
    private Food[] readFoods = null; // keep the foods that were loaded around so we can use them later

    /**
     * Write a list of lists of strings to a CSV file
     * 
     * @param stringList The list List<List<String>> to write
     * @param file the file to write to
     * @throws IOException
     */
    private void write(List<List<String>> stringList, String file) throws IOException {
        // First, create the new file if it doesn't exist
        File f = new File(file);
        f.createNewFile();
        // Then, write csv to the file
        ICSVWriter writer = new CSVWriter(new FileWriter(file),
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.NO_ESCAPE_CHARACTER,
                System.getProperty("line.separator")); // \r\n on Windows, \n on Mac/Linux.
        // Loop through the list we created earlier
        // and write each list to the csv
        for (List<String> list : stringList) {
            String[] listArr = list.toArray(new String[0]);
            writer.writeNext(listArr);
        }
        writer.close();
    }

    /**
     * Read in a List of String arrays from a file
     * 
     * @param file the file to read from
     * @return the List<String[]> of the strings read from the file
     * @throws IOException
     * @throws CsvException
     */
    private List<String[]> read(String file) throws IOException, CsvException {
        CSVReader reader = new CSVReaderBuilder(new FileReader(file)).build();
        List<String[]> strings = reader.readAll();
        reader.close();
        return strings;
    }

    /**
     * Check if the file exists and isn't a directory
     * 
     * @param file the path to the file
     * @return true if it's a file, and false if it isn't
     */
    private boolean checkFile(String path) {
        File f = new File(path);
        return f.exists() && !f.isDirectory();
    }

    @Override
    public Food[] loadFoods() {
        if (readFoods != null) {
            return readFoods; // We've read the foods before, don't read them again.
        }
        if (checkFile(FOODS_CSV)) { // If the file exists
            try {
                List<String[]> foodStrings = read(FOODS_CSV);
                Food[] foods = new Food[foodStrings.size()];
                for (int i = 0; i < foods.length; i++){
                    // initialize the array
                    foods[i] = new BasicFood("", -1, -1, -1, -1);
                }
                for (int i = 0; i < foodStrings.size(); i++) {
                    String[] strings = foodStrings.get(i);
                    if (strings[1].equals("")){
                        System.err.println("Empty string is an invalid food name. This is a bug.");
                        continue; // Just don't add this entire food. There'll be a BasicFood("", -1, -1, -1, -1) at this index
                    }
                    if (strings[0].equals("b")) { // Is it a basic food?
                        // b,name,calories,fat,carb,protein
                        foods[i] = new BasicFood(
                                strings[1],
                                Double.parseDouble(strings[2]),
                                Double.parseDouble(strings[3]),
                                Double.parseDouble(strings[4]),
                                Double.parseDouble(strings[5]));
                    } else { // If it's a recipe
                        // r,name,f1name,f1count,f2name, f2count,...,fNname,fNcount
                        Map<Food, Double> baseFoods = new HashMap<Food, Double>();
                        for (int j = 2; j < strings.length; j += 2){ // Only even strings are names of Foods
                            boolean foundFood = false; // to keep track if we need to print an error if the Food we'e trying to add doesn't exist
                            // Find the food in the foods array
                            // Then, add it to the recipe
                            for (Food food : foods) {
                                if (food.getName().equals(strings[j])){
                                    foundFood = true;
                                    baseFoods.put(food, Double.parseDouble(strings[j + 1]));
                                    break;
                                }
                            }
                            if (!foundFood) {
                                System.err.println("Food " + strings[j] + " could not be found. This is a bug.");
                                // Just don't add this food
                            }
                        }
                        foods[i] = new Recipe(strings[1], baseFoods);
                    }
                }
                readFoods = foods;
                return foods;
            } catch (IOException | CsvException e) {
                e.printStackTrace();
                System.exit(1); // We have no real good way of recovering from here.
                return null;
            }
        } else {
            // the file does not exist, so just return an empty array
            Food[] ret = new Food[0];
            readFoods = ret;
            return ret;
        }
        
    }

    @Override
    public boolean saveFoods(Food[] foods) {
        try {
            // Create a list of lists of strings to hold the foods strings
            // (each list will be a row in the csv later)
            List<List<String>> foodsLists = new ArrayList<List<String>>(foods.length);
            for (int i = 0; i < foods.length; i++) {
                List<String> row = new ArrayList<String>(6); // most foods have 6 elements
                if (foods[i] instanceof BasicFood) {
                    // b,name,calories,fat,carb,protein
                    row.add("b");
                    row.add(foods[i].getName());
                    Map<String, Double> all = foods[i].getAll();
                    row.add(all.get("calories").toString());
                    row.add(all.get("fat").toString());
                    row.add(all.get("carbs").toString());
                    row.add(all.get("protein").toString());
                } else {
                    // r,name,f1name,f1count,f2name, f2count,...,fNname,fNcount
                    row.add("r");
                    row.add(foods[i].getName());
                    Map<Food, Double> baseFoods = ((Recipe) foods[i]).getBaseFoods();
                    for (Map.Entry<Food, Double> entry : baseFoods.entrySet()) {
                        row.add(entry.getKey().getName());
                        row.add(entry.getValue().toString());
                    }
                }
                foodsLists.add(row);
            }
            write(foodsLists, FOODS_CSV);
        } catch (IOException e) {
            e.printStackTrace();
            readFoods = null; // The state of the foods is totally unknown.
            return false;
        }
        readFoods = foods; // Update this to the foods that just got saved
        return true;
    }

    @Override
    public Log[] loadDailyLog() {
        loadFoods(); // We need to have the foods at this point
        if (checkFile(LOG_CSV)) { // If the file exists
            try {
                List<String[]> logStrings = read(LOG_CSV);
                Log[] logs = new Log[logStrings.size()];
                for(int i = 0; i < logStrings.size(); i++){
                    String[] strings = logStrings.get(i);
                    int[] date = new int[3];
                    for (int j = 0; j < date.length; j++) { // Convert the first three to integers for the LocalDate
                        date[j] = Integer.parseInt(strings[j]);
                    }
                    switch (strings[3]) {
                        case "w":
                            logs[i] = new WeightLog(LocalDate.of(date[0], date[1], date[2]), Double.parseDouble(strings[4]));
                            break;
                        case "c":
                            logs[i] = new CalorieLog(LocalDate.of(date[0], date[1], date[2]), Double.parseDouble(strings[4]));
                            break;
                        case "f":
                            Food loggedFood = null;
                            for(Food food : readFoods){
                                if (food.getName().equals(strings[4])){
                                    loggedFood = food;
                                    break;
                                }
                            }
                            if (loggedFood == null) {
                                System.err.println("Food " + strings[4] + " not found. This is a bug.");
                            }
                            logs[i] = new FoodLog(LocalDate.of(date[0], date[1], date[2]), loggedFood ,Double.parseDouble(strings[5]));
                            break;
                    
                        default:
                            System.err.println("Invalid Log. This is a bug.");
                            break;
                    }

                }
                return logs;
            } catch (IOException | CsvException e) {
                e.printStackTrace();
                System.exit(1); // We have no real good way of recovering from here.
                return null;
            }
        } else { // If the file doesn't exist, then there's nothistrings[2]ng to load.
            return new Log[0];
        }
        
    }

    @Override
    public boolean saveDailyLog(Log[] logs) {
        // Create a list of lists of strings to hold the foods strings
        // (each list will be a row in the csv later)
        List<List<String>> logLists = new ArrayList<List<String>>(logs.length);
        for (Log log : logs){
            logLists.add(log.getAll());
        }
        try {
            write(logLists, LOG_CSV);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Exercise[] loadExercises() {
        if (checkFile(EXERCISE_CSV)) {
            try {
                List<String[]> exerciseStrings = read(EXERCISE_CSV);
                Exercise[] exercises = new Exercise[exerciseStrings.size()];
                for (int i = 0; i < exerciseStrings.size(); i++) {
                    String[] strings = exerciseStrings.get(i);
                    exercises[i] = new BasicExercise(strings[1], Double.parseDouble(strings[2]));
                }
                return exercises;
            } catch (IOException | CsvException e) {
                e.printStackTrace();
                System.exit(1); // We have no real good way of recovering from here.
                return null;
            }
        } else {
            // If the file doesn't exist, just return an empty array
            return new Exercise[0];
        }
    }

    @Override
    public boolean saveExercises(Exercise[] exercises) {
        List<List<String>> exerciseLists = new ArrayList<List<String>>(exercises.length);
        for (Exercise exercise : exercises) {
            // e,name,calories
            List<String> row = new ArrayList<String>(3);
            row.add("e");
            row.add(exercise.getName());
            row.add(Double.toString(exercise.getCalories()));
            exerciseLists.add(row);
        }
        try {
            write(exerciseLists, EXERCISE_CSV);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
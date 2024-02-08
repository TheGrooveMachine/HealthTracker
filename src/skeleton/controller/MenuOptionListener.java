package skeleton.controller;
import java.util.HashMap;
import java.util.Scanner;

import skeleton.model.DailyLog;
import skeleton.model.Data;
import skeleton.model.FoodCollection;
import skeleton.view.CLI;

public class MenuOptionListener {
    private FoodCollection fc;
    private DailyLog dl;
    private Data type;
    private CLI view;

    public MenuOptionListener(FoodCollection fc, DailyLog dl, Data type, CLI view, String menu) {
        this.fc = fc;
        this.dl = dl;
        this.type = type;
        this.view = view;

        switch (menu) {
            case "main":
                this.mainMenuListener();
                break;

            case "food":
                this.foodMenuListener();
                break;

            case "updateBasic":
                this.basicFoodUpdateListener();
                break;

            case "updateRecipe":
                this.recipeUpdateListener();
                break;

            case "dailylog":
                this.dailyLogMenuListener();
                break;

            case "updateDailyLog":
                this.dailyLogUpdateListener();
                break;
            }
        }

        public void mainMenuListener() {
            Scanner in = new Scanner(System.in);
            int input = in.nextInt();
    
            switch (input) {
    
                // Move to Food Menu in view
                case 0:
                    view.foodMenu();
                    break;
    
                // Move to Daily Log Menu in view
                case 1:
                    view.dailylogMenu();
                    break;
    
                // Save to CSV
                case 2:
                    new FoodController(fc, type).saveToFile();
                    view.mainMenu();
                    break;
    
                // Exit and save to CSV
                case 3:
                    new FoodController(fc, type).saveToFile(); // save on exit
                    System.exit(0);
                    break;
            }
            in.close();
        }

        public void foodMenuListener() {

            Scanner in = new Scanner(System.in);
            int input = in.nextInt();
    
            switch (input) {
    
                // Add basic food and return to food menu
                case 0:
                    view.prompt("Please enter the name of the food you would like to add: ");
                    in.nextLine();
                    String foodName = in.nextLine();
                    view.prompt("Please enter the amount of calories in the food: ");
                    double calories = in.nextDouble();
                    view.prompt("Please enter the amount of fat in the food: ");
                    double fat = in.nextDouble();
                    view.prompt("Please enter the amount of carbs in the food: ");
                    double carbs = in.nextDouble();
                    view.prompt("Please enter the amount of protein in the food: ");
                    double protein = in.nextDouble();
    
                    new FoodController(fc, type).addBasicFood(foodName, calories, fat, carbs, protein);
    
                    view.foodMenu();
                    break;
    
                // Remove basic food and return to food menu
                case 1:
                    view.prompt("Please enter the name of the food you would like to remove: ");
                    in.nextLine();
                    foodName = in.nextLine();
    
                    new FoodController(fc, type).removeFood(foodName);
    
                    view.foodMenu();
                    break;
    
                // Move to basic food update menu in view
                case 2:
                    view.basicFoodUpdateMenu();
                    break;
    
                // Add recipe and retun to food menu
                case 3:
                    view.prompt("Please enter the name of the recipe you would like to add: ");
                    in.nextLine();
                    String recipeName = in.nextLine();
                    String baseFood;
                    Double serving;
                    HashMap<String, Double> baseFoods = new HashMap<String, Double>();
    
                    boolean loop = true;
                    while (loop) {
                        view.prompt(
                                "Please enter the name of the base you would like to add to the recipe (hit enter to continue): ");
                        baseFood = in.nextLine();
    
                        if (!baseFood.isBlank()) {
                            view.prompt("Please enter the number of servings of " + baseFood + " in recipe: ");
                            serving = in.nextDouble();
                            baseFoods.put(baseFood, serving);
                            in.nextLine();
                        } else {
                            loop = false;
                        }
                    }
    
                    new FoodController(fc, type).addRecipe(recipeName, baseFoods);
    
                    view.foodMenu();
                    break;
    
                // Remove repipe and return to food menu
                case 4:
                    view.prompt("Please enter the name of the recipe you would like to remove: ");
                    in.nextLine();
                    foodName = in.nextLine();
    
                    new FoodController(fc, type).removeFood(foodName);
    
                    view.foodMenu();
                    break;
    
                // Move to recipe update menu in view
                case 5:
                    view.recipeUpdateMenu();
                    break;
    
                // Search for a food and display it in the view then return to food menu
                case 6:
                    view.prompt("Please enter the name of the food you would like look for: ");
                    in.nextLine();
                    foodName = in.nextLine();
    
                    view.prompt(new FoodController(fc, type).find(foodName));
    
                    view.prompt("Hit enter to return");
                    in.nextLine();
                    view.foodMenu();
                    break;
    
                // Display all foods in the view then return to food menu
                case 7:
                    view.prompt(new FoodController(fc, type).showAllFoods());
    
                    view.prompt("Hit enter to return");
                    in.nextLine();
                    in.nextLine();
                    view.foodMenu();
                    break;
    
                // Go back to main menu
                case 8:
                    view.mainMenu();
                    break;
            }
            in.close();
        }

        public void basicFoodUpdateListener() {

            Scanner in = new Scanner(System.in);
            int input = in.nextInt();
    
            switch (input) {
    
                // Update the name of a basic food and return to basic food update menu
                case 0:
                    view.prompt("Please enter the name of the food to update: ");
                    in.nextLine();
                    String foodName = in.nextLine();
                    view.prompt("Please enter the new name of " + foodName + ": ");
                    String newName = in.nextLine();
    
                    new FoodController(fc, type).setName(foodName, newName);
    
                    view.basicFoodUpdateMenu();
                    break;
    
                // Update the calorie value of a basic food and return to basic food update menu
                case 1:
                    view.prompt("Please enter the name of the food to update: ");
                    in.nextLine();
                    foodName = in.nextLine();
                    view.prompt("Please enter the new calorie value for " + foodName + ": ");
                    Double cal = in.nextDouble();
    
                    new FoodController(fc, type).setCalories(foodName, cal);
    
                    view.basicFoodUpdateMenu();
                    break;
    
                // Update the fat value of a basic food and return to basic food update menu
                case 2:
                    view.prompt("Please enter the name of the food to update: ");
                    in.nextLine();
                    foodName = in.nextLine();
                    view.prompt("Please enter the new fat value for " + foodName + ": ");
                    Double fat = in.nextDouble();
    
                    new FoodController(fc, type).setFat(foodName, fat);
    
                    view.basicFoodUpdateMenu();
                    break;
    
                // Update the carb value of a basic food and return to basic food update menu
                case 3:
                    view.prompt("Please enter the name of the food to update: ");
                    in.nextLine();
                    foodName = in.nextLine();
                    view.prompt("Please enter the new carb value for " + foodName + ": ");
                    Double carb = in.nextDouble();
    
                    new FoodController(fc, type).setCarbs(foodName, carb);
    
                    view.basicFoodUpdateMenu();
                    break;
    
                // Update the protein value of a basic food and return to basic food update menu
                case 4:
                    view.prompt("Please enter the name of the food to update: ");
                    in.nextLine();
                    foodName = in.nextLine();
                    view.prompt("Please enter the new protein value for " + foodName + ": ");
                    Double protein = in.nextDouble();
    
                    new FoodController(fc, type).setProtein(foodName, protein);
    
                    view.basicFoodUpdateMenu();
                    break;
    
                // Go back to the food menu
                case 5:
                    view.foodMenu();
                    break;
            }
            in.close();
        }


        public void recipeUpdateListener() {

            Scanner in = new Scanner(System.in);
            int input = in.nextInt();
    
            switch (input) {
    
                // Update the name of a recipe and return to recipe update menu
                case 0:
                    view.prompt("Please enter the name of the recipe to update: ");
                    in.nextLine();
                    String foodName = in.nextLine();
                    view.prompt("Please enter the new name of " + foodName + ": ");
                    String newName = in.nextLine();
    
                    new FoodController(fc, type).setName(foodName, newName);
    
                    view.recipeUpdateMenu();
                    break;
    
                // Display the base foods in a recipe then return to recipe update menu
                case 1:
                    view.prompt("Please enter the name of the recipe to check base foods: ");
                    in.nextLine();
                    foodName = in.nextLine();
    
                    view.prompt(new FoodController(fc, type).getBaseFoods(foodName));
    
                    view.recipeUpdateMenu();
                    break;
    
                // Add a base food to a recipe then return to recipe update menu
                case 2:
                    view.prompt("Please enter the name of the recipe to add a base food to: ");
                    in.nextLine();
                    String recipeName = in.nextLine();
                    view.prompt("Please enter the name of the food to add as base food of " + recipeName + ": ");
                    foodName = in.nextLine();
                    view.prompt("Please enter the number of servings of " + foodName + " that are in " + recipeName + ": ");
                    Double servings = in.nextDouble();
    
                    new FoodController(fc, type).addBaseFood(recipeName, foodName, servings);
    
                    view.recipeUpdateMenu();
                    break;
    
                // Remove a base food to a recipe then return to recipe update menu
                case 3:
                    view.prompt("Please enter the name of the recipe to remove a base food from: ");
                    in.nextLine();
                    recipeName = in.nextLine();
                    view.prompt("Please enter the name of the food to remove as a base food of " + recipeName + ": ");
                    foodName = in.nextLine();
    
                    new FoodController(fc, type).removeBaseFood(recipeName, foodName);
    
                    view.recipeUpdateMenu();
                    break;
    
                // Update the serving size of a base food in a recipe then return to recipe update menu
                case 4:
                    view.prompt(
                            "Please enter the name of the recipe that you want to change the serving size of a base food: ");
                    in.nextLine();
                    recipeName = in.nextLine();
                    view.prompt("Please enter the name of the base food to chnage the servings of in " + recipeName + ": ");
                    foodName = in.nextLine();
                    view.prompt("Please enter the new number of servings for " + foodName + " in " + recipeName + ": ");
                    servings = in.nextDouble();
    
                    new FoodController(fc, type).setServings(recipeName, foodName, servings);
    
                    view.recipeUpdateMenu();
                    break;
    
                // Go back to the food menu
                case 5:
                    view.foodMenu();
                    break;
            }
            in.close();
        }

        public void dailyLogUpdateListener() {

            Scanner in = new Scanner(System.in);
            int input = in.nextInt();
    
            switch (input) {
    
                // Add food to daily log then return to daily log update menu
                case 0:
                    view.prompt("Please enter the date of the daily log you want to update: ");
                    in.nextLine();
                    String date = in.nextLine();
                    view.prompt("Please enter the name of the food that was consumed on " + date + ": ");
                    String foodName = in.nextLine();
    
                    new DailyLogController(fc, dl);
    
                    view.dailyLogUpdateMenu();
                    break;
    
                // Remove food from daily log then return to daily log update menu
                case 1:
                    view.prompt("Please enter the date of the daily log you want to update: ");
                    in.nextLine();
                    date = in.nextLine();
                    view.prompt("Please enter the name of the food that you want to remove form the log on " + date + ": ");
                    foodName = in.nextLine();
    
                    new DailyLogController(fc, dl);
    
                    view.dailyLogUpdateMenu();
                    break;
    
                // Update servings consumed of a food in a daily log then return to daily log update menu
                case 2:
                    view.prompt("Please enter the date of the daily log you want to update: ");
                    in.nextLine();
                    date = in.nextLine();
                    view.prompt("Please enter the name of the food that you want to change the servings consumed on " + date
                            + ": ");
                    foodName = in.nextLine();
                    view.prompt("Please enter the new number of servings for " + foodName + " consumed on" + date + ": ");
                    double newServings = in.nextDouble();
    
                    new DailyLogController(fc, dl);
    
                    view.dailyLogUpdateMenu();
                    break;
    
                // Update the calorie limit of a daily log then return to daily log update menu
                case 3:
                    view.prompt("Please enter the date of the daily log you want to update: ");
                    in.nextLine();
                    date = in.nextLine();
                    view.prompt("Please enter the new calorie limit for " + date + ": ");
                    double calories = in.nextDouble();
    
                    new DailyLogController(fc, dl);
    
                    view.dailyLogUpdateMenu();
                    break;
    
                // Update the weight in a daily log then return to the daily log update menu
                case 4:
                    view.prompt("Please enter the date of the daily log you want to update: ");
                    in.nextLine();
                    date = in.nextLine();
                    view.prompt("Please enter the new weight for " + date + ": ");
                    double newWeight = in.nextDouble();
    
                    new DailyLogController(fc, dl);
    
                    view.dailyLogUpdateMenu();
                    break;
    
                // Go back to the daily log menu
                case 5:
                    view.dailylogMenu();
                    break;
            }
            in.close();
        }
    
        public void dailyLogMenuListener() {

            Scanner in = new Scanner(System.in);
            int input = in.nextInt();
            switch (input) {
    
                // Create a new daily log then return to daily log menu
                case 0:
                    view.prompt("Please enter the date of the daily log you would like to create: ");
                    in.nextLine();
                    String date = in.nextLine();
                    view.prompt("Please enter the calorie limit for " + date + ": ");
                    double calories = in.nextDouble();
                    view.prompt("Please your weight on " + date + ": ");
                    double weight = in.nextDouble();
    
                    new DailyLogController(fc, dl);
    
                    view.dailylogMenu();
                    break;
    
                // Remove a  daily log then return to daily log menu
                case 1:
                    view.prompt("Please enter the date of the daily log you would like to remove: ");
                    in.nextLine();
                    date = in.nextLine();
    
                    new DailyLogController(fc, dl);//.addDailyLogToLog(date);
    
                    view.dailylogMenu();
                    break;
    
                // Move to daily log update menu
                case 2:
                    view.dailyLogUpdateMenu();
                    break;
    
                // Display daily log then return to daily log menu
                case 3:
                    view.prompt("Please enter the date of the daily log you would like to see: ");
                    in.nextLine();
                    date = in.nextLine();
    
                    new DailyLogController(fc, dl);
    
                    view.dailylogMenu();
                    break;
    
                // Go back to main menu
                case 4:
                    view.mainMenu();
                    break;
            }
            in.close();
        }

}

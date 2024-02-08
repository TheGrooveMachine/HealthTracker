package skeleton.view;

import skeleton.model.DailyLog;
import skeleton.model.FoodCollection;
import skeleton.model.Data;
import skeleton.controller.MenuOptionListener;

public class CLI {
    private FoodCollection fc;
    private DailyLog dl;
    private Data dataFileType;

    public CLI(FoodCollection fc, DailyLog dl, Data dataFileType) {
        this.fc = fc;
        this.dl = dl;
        this.dataFileType = dataFileType;
        System.out.println("Hello! Welcome to your wellness manager!/n");
    }

    // Home menu
    public void mainMenu() {
        System.out.println("\nMenu options: ");
        System.out.println("[0] Food Options");
        System.out.println("[1] DailyLog Options");
        System.out.println("[2] Save");
        System.out.println("[3] Exit");

        new MenuOptionListener(fc, dl, dataFileType, this, "main");
    }

    // Food menu
    public void foodMenu() {
        System.out.println("\nMenu options: ");
        System.out.println("[0] Add a new basic food");
        System.out.println("[1] Delete a basic food");
        System.out.println("[2] Update a basic food");
        System.out.println("[3] Add a new recipe");
        System.out.println("[4] Delete a recipe");
        System.out.println("[5] Update a recipe");
        System.out.println("[6] Search for a specific food");
        System.out.println("[7] Show all foods");
        System.out.println("[8] Back");

        new MenuOptionListener(fc, dl, dataFileType, this, "food");
    }

    // Update options for basic food
    public void basicFoodUpdateMenu() {
        System.out.println("\nBasic food update options: ");
        System.out.println("[0] Change name");
        System.out.println("[1] Change calories");
        System.out.println("[2] Change fat");
        System.out.println("[3] Change carbs");
        System.out.println("[4] Change protein");
        System.out.println("[5] Back");

        new MenuOptionListener(fc, dl, dataFileType, this, "updateBasic");
    }

    // Update options for recipe
    public void recipeUpdateMenu() {
        System.out.println("\nRecipe update options: ");
        System.out.println("[0] Change name");
        System.out.println("[1] Show base foods");
        System.out.println("[2] Add base food");
        System.out.println("[3] Remove base food");
        System.out.println("[4] Change Servings of base food");
        System.out.println("[5] Back");

        new MenuOptionListener(fc, dl, dataFileType, this, "updateRecipe");

    }

    // DailyLog menu
    public void dailylogMenu() {
        System.out.println("\nDailyLog menu options: ");
        System.out.println("[0] Add a new daily log");
        System.out.println("[1] Delete a daily log");
        System.out.println("[2] Update a daily log");
        System.out.println("[3] Show a daily log");
        System.out.println("[4] Back");

        new MenuOptionListener(fc, dl, dataFileType, this, "dailylog");
    }

    // Update options for a daily log
    public void dailyLogUpdateMenu() {
        System.out.println("\nDailyLog update options: ");
        System.out.println("[0] Add food that was consumed");
        System.out.println("[1] Delete food from log");
        System.out.println("[2] Change servings consumed for a food");
        System.out.println("[3] Change daily calorie limit");
        System.out.println("[4] Change weight in the log");
        System.out.println("[5] Back");

        new MenuOptionListener(fc, dl, dataFileType, this, "updateDailyLog");
    }

    // Prompts
    public void prompt(String prompt) {
        System.out.println(prompt);
    }

    //
    // }else{
    // System.out.println("Please enter the name of the food you wish to delete: ");
    // // scanner.nextLine();
    // String foodName = scanner.nextLine();

    // fc.removeFood(foodName);
    // fc.saveToFile();
    // System.out.println("The food '" + foodName + "' has been deleted!");

    // }

    // System.out.println("Press 'y' to add a DailyLog and press 'n' to delete a
    // DailyLog");
    // scanner.nextLine();
    // String log = scanner.nextLine();
    // DailyLog dl = new DailyLog(fc);
    // if (log.equals("y")) {
    // System.out.println("Please enter the date of the log: ");
    // String date = scanner.nextLine();
    // System.out.println("Please enter the calorie limit: ");
    // double limit = scanner.nextDouble();
    // System.out.println("Please enter the weight for the log: ");
    // double weight = scanner.nextDouble();

    // Log newLog = new Log(date, limit, weight, fc);
    // dl.addDailyLog(date, limit, weight);

    // System.out.println("Info from your log");
    // System.out.println("Date: " + newLog.getDate());
    // System.out.println("Calorie Limit: " + newLog.getCalorieLimit());
    // System.out.println("weight: " + newLog.getWeight());

    // System.out.println("Add a food to your new log!");
    // System.out.println("enter the date of the log to add your food to:");
    // scanner.nextLine();
    // String logDate = scanner.nextLine();
    // System.out.println("enter the name of the food:");
    // String logFood = scanner.nextLine();
    // System.out.println("enter the weight of the food:");
    // double logWeight = scanner.nextDouble();

    // // dl.searchDailyDate("10 10 10").addFoodIntake(logFood, logWeight);
    // // System.out.println(dl.toString());

    // }else{
    // System.out.println("Have a nice day");
    // }

    // scanner.close();

}
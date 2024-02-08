package skeleton.controller;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import skeleton.Food;
import skeleton.model.FoodCollection;
import skeleton.model.Recipe;
import skeleton.model.BasicFood;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ItemEvent;
import java.awt.CardLayout;

public class FoodMenuListener implements ActionListener, ItemListener {

    private JPanel inputs;
    private FoodCollection fc;

    public FoodMenuListener(JPanel inputs, FoodCollection fc) {
        this.inputs = inputs;
        this.fc = fc;
    }

    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            // Add basic food and return to food menu
            case "Add Basic Food":

                JTextField foodName = (JTextField) inputs.getComponent(1);

                JTextField calories = (JTextField) inputs.getComponent(3);

                JTextField fat = (JTextField) inputs.getComponent(5);

                JTextField carbs = (JTextField) inputs.getComponent(7);

                JTextField protein = (JTextField) inputs.getComponent(9);

                fc.addBasicFood(foodName.getText(), Double.parseDouble(calories.getText()),
                        Double.parseDouble(fat.getText()), Double.parseDouble(carbs.getText()),
                        Double.parseDouble(protein.getText()));

                break;

            // Remove basic food and return to food menu
            case "Remove Food":
                JComboBox name = (JComboBox) inputs.getComponent(1);
                String removedFood = ((Food) name.getSelectedItem()).getName();

                fc.removeFood(removedFood);

                break;

            // Move to basic food update menu in view
            case "Update Basic Food":
                JTextField updatedFood = (JTextField) inputs.getComponent(1);

                JTextField updatedCalories = (JTextField) inputs.getComponent(3);

                JTextField updatedFat = (JTextField) inputs.getComponent(5);

                JTextField updatedCarbs = (JTextField) inputs.getComponent(7);

                JTextField updatedProtein = (JTextField) inputs.getComponent(9);

                String updates = ((BasicFood) ((JComboBox) inputs.getParent().getParent().getComponent(0))
                        .getSelectedItem()).getName();

                ((BasicFood) fc.find(updates)).setCalories(Double.parseDouble(updatedCalories.getText()));
                ((BasicFood) fc.find(updates)).setFat(Double.parseDouble(updatedFat.getText()));
                ((BasicFood) fc.find(updates)).setCarbs(Double.parseDouble(updatedCarbs.getText()));
                ((BasicFood) fc.find(updates)).setProtein(Double.parseDouble(updatedProtein.getText()));
                ((BasicFood) fc.find(updates)).setName(updatedFood.getText());

                break;

            // Add Recipe
            case "Add Recipe":
                foodName = (JTextField) inputs.getComponent(1);
                String base = ((BasicFood) ((JComboBox) inputs.getComponent(3)).getSelectedItem()).getName();
                Double servings = Double.parseDouble(((JTextField) inputs.getComponent(5)).getText());
                HashMap<String, Double> baseFood = new HashMap<String, Double>();
                baseFood.put(base, servings);
                fc.addRecipe(foodName.getText(), baseFood);

                break;

            // Update name of Recipe
            case "Update Recipe":
                updates = ((Recipe) ((JComboBox) inputs.getParent().getParent().getComponent(0))
                        .getSelectedItem()).getName();

                foodName = (JTextField) inputs.getComponent(1);

                ((Recipe) fc.find(updates)).setName(foodName.getText());

                break;

            // Add base food to recipe
            case "Add base Food":
                updates = ((Recipe) ((JComboBox) inputs.getParent().getParent().getComponent(0))
                        .getSelectedItem()).getName();

                Food addBase = ((Food) ((JComboBox) inputs.getComponent(3)).getSelectedItem());

                servings = Double.parseDouble(((JTextField) inputs.getComponent(5)).getText());

                ((Recipe) fc.find(updates)).addBaseFood(addBase, servings);

                ((JComboBox) inputs.getComponent(8)).addItem(addBase);

                break;

            // Remove base food from recipe
            case "Remove base Food":
                updates = ((Recipe) ((JComboBox) inputs.getParent().getParent().getComponent(0))
                        .getSelectedItem()).getName();

                Food removeBase = ((Food) ((JComboBox) inputs.getComponent(8)).getSelectedItem());

                ((Recipe) fc.find(updates)).removeBaseFood(removeBase);

                ((JComboBox) inputs.getComponent(8)).removeItem(removeBase);
                break;

        }
    }

    public void itemStateChanged(ItemEvent e) {
        // Check for state change
        if (e.getStateChange() == 1) {

            // Check if basic food or recipe
            if (e.getItem().getClass().getSimpleName().equals("BasicFood")) {

                // If Basic Food show basic food
                CardLayout cl = (CardLayout) (inputs.getLayout());
                cl.show(inputs, "Basic Food");

                // Get Basic Food data and put it in fields
                JPanel fields = (JPanel) inputs.getComponent(1);
                Food food = ((Food) e.getItem());

                JTextField name = (JTextField) fields.getComponent(1);
                name.setText(food.getName());

                JTextField calories = (JTextField) fields.getComponent(3);
                calories.setText(String.valueOf(food.getCalories()));

                JTextField fat = (JTextField) fields.getComponent(5);
                fat.setText(String.valueOf(food.getFat()));

                JTextField carbs = (JTextField) fields.getComponent(7);
                carbs.setText(String.valueOf(food.getCarbs()));

                JTextField protein = (JTextField) fields.getComponent(9);
                protein.setText(String.valueOf(food.getProtein()));

            } else if (e.getItem().getClass().getSimpleName().equals("Recipe")) {

                // If Recipe show recipe
                CardLayout cl = (CardLayout) (inputs.getLayout());
                cl.show(inputs, "Recipe");

                // Get Recipe data and put it in fields
                JPanel fields = (JPanel) inputs.getComponent(2);
                Recipe food = ((Recipe) e.getItem());

                JTextField name = (JTextField) fields.getComponent(1);
                name.setText(food.getName());

                JComboBox<Food> bases = (JComboBox<Food>) fields.getComponent(8);

                bases.removeAllItems();

                Map<Food, Double> list = food.getBaseFoods();

                for (Map.Entry<Food, Double> foods : list.entrySet()) {
                    bases.addItem(foods.getKey());
                }
            } else {

            }
        }
    }
}

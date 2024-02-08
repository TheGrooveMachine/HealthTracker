package skeleton.view;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import skeleton.Food;
import skeleton.controller.FoodMenuListener;
import skeleton.controller.MenuNavListener;
import skeleton.model.CSV;
import skeleton.model.Data;
import skeleton.model.FoodCollection;
import skeleton.view.util.FoodRenderer;

public class UpdateDailyLogMenu extends JPanel {
    JPanel cards; // a panel that uses CardLayoutnel{

    public UpdateDailyLogMenu() {

        // Set layout
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        GridBagConstraints cons = new GridBagConstraints();

        // Create panel with CardLayout for food type cardsion
        CardLayout cLayout = new CardLayout();
        cards = new JPanel(cLayout);

        // Create panels (cards) for the food types
        JPanel blank = new JPanel();
        JPanel updateBasic = new JPanel();
        JPanel updateRecipe = new JPanel();

        // Test data
        Data data = new CSV();
        FoodCollection fc = new FoodCollection(data.loadFoods());
        ArrayList<Food> foodList = new ArrayList<Food>(fc.getFoodList());

        // Create and add Combo Box to select food to add to recipe
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = 0;
        JComboBox<Food> fList = new JComboBox<Food>();
        fList.setRenderer(new FoodRenderer());
        for (Food obj : foodList) {
            fList.addItem(obj);
        }
        //fList.addItemListener(new FoodMenuListener(cards));
        fList.setSelectedIndex(-1);
        this.add(fList, cons);

        /*
         * 
         * Update Basic Food
         * 
         */

        // Set updateBasic layout
        updateBasic.setLayout(layout);

        // Add input fields, labels and set the layout constraints for each

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = 1;
        updateBasic.add(new JLabel("Number of Calories: "), cons);

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 1;
        updateBasic.add(inputField(16), cons);

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = 2;
        updateBasic.add(new JLabel("Enter your weight: "), cons);

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 2;
        updateBasic.add(inputField(16), cons);

        // Add submit button
        JButton addBtn = new JButton("Update a DailyLog");
        //addBtn.addActionListener(new FoodMenuListener(updateBasic));

        // Add submit button to card
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 5;
        updateBasic.add(addBtn, cons);

        /*
         * 
         * Update Recipe
         * 
         */

        // Set updateRecipe layout
        updateRecipe.setLayout(layout);

        // Add inputs for updateRecipe
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = 0;
        updateRecipe.add(new JLabel("Recipe Name: "), cons);

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 0;
        updateRecipe.add(inputField(16), cons);

        /*
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 1;
        updateRecipe.add(new JLabel("Add Food to Recipe "), cons);

        // Create and add Combo Box to select food to add to recipe
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = 2;
        //JComboBox<Food> fList = new JComboBox<Food>();
        fList.setRenderer(new FoodRenderer());
        for (Food obj : foodList) {
            fList.addItem(obj);
        }
        // fList.addItemListener();
        fList.setSelectedIndex(-1);
        updateRecipe.add(fList, cons);

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 2;
        updateRecipe.add(new JLabel("Servings: "), cons);

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 2;
        cons.gridy = 2;
        updateRecipe.add(inputField(5), cons);
        */

        // Add submit button
        addBtn = new JButton("Update Recipe");
        // addBtn.addActionListener(new FoodMenuListener(updateRecipe));

        // Add submit button to card
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 3;
        updateRecipe.add(addBtn, cons);

        // Add food type cards to cards (cardlayout)
        cards.add(blank);
        cards.add(updateBasic, "Basic Food");
        cards.add(updateRecipe, "Recipe");

        // Add food type options to addFood
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = 1;
        this.add(cards, cons);
    }

    private JTextField inputField(int col) {
        JTextField inputField = new JTextField(col);
        return inputField;
    }
}
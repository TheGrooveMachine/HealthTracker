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

import skeleton.Food;
import skeleton.controller.FoodMenuListener;
import skeleton.model.FoodCollection;
import skeleton.view.util.FoodRenderer;

public class UpdateFoodMenu extends JPanel {
    JPanel cards; // a panel that uses CardLayoutnel
    private FoodCollection fc;

    public UpdateFoodMenu(FoodCollection fc) {
        this.fc = fc;

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

        // Create and add Combo Box to select food to add to recipe
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = 0;
        JComboBox<Food> fList = new JComboBox<Food>();
        fList.setRenderer(new FoodRenderer());

        // Test data
        ArrayList<Food> foodList = new ArrayList<Food>(fc.getFoodList());
        for (Food obj : foodList) {
            fList.addItem(obj);
        }
        fList.addItemListener(new FoodMenuListener(cards, fc));
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
        cons.gridy = 0;
        updateBasic.add(new JLabel("Food Name: "), cons);

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 0;
        updateBasic.add(inputField(16), cons);

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
        updateBasic.add(new JLabel("Amount of fat: "), cons);

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 2;
        updateBasic.add(inputField(16), cons);

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = 3;
        updateBasic.add(new JLabel("Amount of carbs: "), cons);

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 3;
        updateBasic.add(inputField(16), cons);

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = 4;
        updateBasic.add(new JLabel("Amount of protein: "), cons);

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 4;
        updateBasic.add(inputField(16), cons);

        // Add submit button
        JButton addBtn = new JButton("Update Basic Food");
        addBtn.addActionListener(new FoodMenuListener(updateBasic, fc));

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

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 1;
        updateRecipe.add(new JLabel("Add Food to Recipe "), cons);

        // Create and add Combo Box to select food to add to recipe
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = 2;
        JComboBox<Food> flist = new JComboBox<Food>();

        // Test Data
        flist.setRenderer(new FoodRenderer());
        for (Food obj : foodList) {
            flist.addItem(obj);
        }
        // fList.addItemListener();
        flist.setSelectedIndex(-1);
        updateRecipe.add(flist, cons);

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 2;
        updateRecipe.add(new JLabel("Servings: "), cons);

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 2;
        cons.gridy = 2;
        updateRecipe.add(inputField(5), cons);

        // Add submit button
        addBtn = new JButton("Add base Food");
        addBtn.addActionListener(new FoodMenuListener(updateRecipe, fc));

        // Add submit button to card
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 3;
        updateRecipe.add(addBtn, cons);

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 4;
        updateRecipe.add(new JLabel("Remove Food from Recipe "), cons);

        JComboBox<Food> baseList = new JComboBox<Food>();
        baseList.setRenderer(new FoodRenderer());

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 5;
        updateRecipe.add(baseList, cons);

        // Add submit button
        addBtn = new JButton("Remove base Food");
        addBtn.addActionListener(new FoodMenuListener(updateRecipe, fc));

        // Add submit button to card
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 6;
        updateRecipe.add(addBtn, cons);

        // Add submit button
        addBtn = new JButton("Update Recipe");
        addBtn.addActionListener(new FoodMenuListener(updateRecipe, fc));

        // Add submit button to card
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 7;
        updateRecipe.add(addBtn, cons);

        // Add food type cards to cards (cardlayout)
        cards.add(blank, "Blank");
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
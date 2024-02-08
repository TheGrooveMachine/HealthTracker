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
import skeleton.model.FoodCollection;
import skeleton.view.util.FoodRenderer;

public class AddFoodMenu extends JPanel {

    JPanel cards; // a panel that uses CardLayout
    private FoodCollection fc;

    public AddFoodMenu(FoodCollection fc) {
        this.fc = fc;

        // Set layout
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        GridBagConstraints cons = new GridBagConstraints();

        // Create panel with CardLayout for food type cardsion
        cards = new JPanel(new CardLayout());

        // Create panels (cards) for the food types
        JPanel addBasic = new JPanel();
        JPanel addRecipe = new JPanel();

        // Create panel to cards type of food to add
        JPanel radPanel = new JPanel();

        // Create the radio buttons and put them in a group
        JRadioButton radBasicFood = new JRadioButton("Basic Food");
        JRadioButton radRecipe = new JRadioButton("Recipe");
        ButtonGroup typecards = new ButtonGroup();
        typecards.add(radBasicFood);
        typecards.add(radRecipe);

        // Add ActionListenrs to radio buttons
        radBasicFood.addActionListener(new MenuNavListener(cards));
        radRecipe.addActionListener(new MenuNavListener(cards));

        // Precards radBasicFood
        radBasicFood.setSelected(true);

        // Add radio buttons to radPanel
        radPanel.add(radBasicFood);
        radPanel.add(radRecipe);

        // Add radPanel to addFood
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = 0;
        this.add(radPanel, cons);

        /*
         * 
         * Add Basic Food
         * 
         */

        // Set addBasic layout
        addBasic.setLayout(layout);

        // Add input fields, labels and set the layout constraints for each
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = 0;
        addBasic.add(new JLabel("Food Name: "), cons);

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 0;
        addBasic.add(inputField(16), cons);

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = 1;
        addBasic.add(new JLabel("Number of Calories: "), cons);

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 1;
        addBasic.add(inputField(16), cons);

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = 2;
        addBasic.add(new JLabel("Amount of fat: "), cons);

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 2;
        addBasic.add(inputField(16), cons);

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = 3;
        addBasic.add(new JLabel("Amount of carbs: "), cons);

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 3;
        addBasic.add(inputField(16), cons);

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = 4;
        addBasic.add(new JLabel("Amount of protein: "), cons);

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 4;
        addBasic.add(inputField(16), cons);

        // Add submit button
        JButton addBtn = new JButton("Add Basic Food");
        addBtn.addActionListener(new FoodMenuListener(addBasic, fc));

        // Add submit button to card
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 5;
        addBasic.add(addBtn, cons);

        /*
         * 
         * Add Recipe
         * 
         */

        // Set addRecipe layout
        addRecipe.setLayout(layout);

        // Add inputs for addRecipe
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = 0;
        addRecipe.add(new JLabel("Recipe Name: "), cons);

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 0;
        addRecipe.add(inputField(16), cons);

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 1;
        addRecipe.add(new JLabel("Add Food to Recipe "), cons);

        // Create and add Combo Box to select food to add to recipe
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = 2;
        JComboBox<Food> fList = new JComboBox<Food>();
        fList.setRenderer(new FoodRenderer());
        
        // Test data
        ArrayList<Food> foodList = new ArrayList<Food>(fc.getFoodList());
        for (Food obj : foodList) {
            fList.addItem(obj);
        }
        // fList.addItemListener();
        fList.setSelectedIndex(-1);
        addRecipe.add(fList, cons);

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 2;
        addRecipe.add(new JLabel("Servings: "), cons);

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 2;
        cons.gridy = 2;
        addRecipe.add(inputField(5), cons);

        // Add submit button
        addBtn = new JButton("Add Recipe");
        addBtn.addActionListener(new FoodMenuListener(addRecipe, fc));

        // Add submit button to card
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 3;
        addRecipe.add(addBtn, cons);

        // Add food type cards to cards (cardlayout)
        cards.add(addBasic, "Basic Food");
        cards.add(addRecipe, "Recipe");

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

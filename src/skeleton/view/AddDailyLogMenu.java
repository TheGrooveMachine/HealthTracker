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
import skeleton.controller.SwingUIDailyLogListener;
import skeleton.model.CSV;
import skeleton.model.Data;
import skeleton.model.FoodCollection;
import skeleton.view.util.FoodRenderer;

public class AddDailyLogMenu extends JPanel {

    JPanel cards; // a panel that uses CardLayout

    public AddDailyLogMenu() {
        // Set layout
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        GridBagConstraints cons = new GridBagConstraints();

        // Create panel with CardLayout for food type cardsion
        cards = new JPanel(new CardLayout());

        // Create panels (cards) for the food types
        JPanel addBasic = new JPanel();

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
        addBasic.add(new JLabel("Enter the date of the log: "), cons);

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
        cons.gridy = 3;
        addBasic.add(new JLabel("Enter your weight: "), cons);

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 3;
        addBasic.add(inputField(16), cons);

        // Add submit button
        JButton addBtn = new JButton("Add a DailyLog");
        addBtn.addActionListener(new SwingUIDailyLogListener(addBasic));

        // Add submit button to card
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 5;
        addBasic.add(addBtn, cons);

        cards.add(addBasic, "Basic Food");
        // cards.add(addRecipe, "Recipe");

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

package skeleton.view;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import skeleton.model.ExerciseCollection;
import skeleton.controller.SwingUIExerciseMenuListener;

public class AddExerciseMenu extends JPanel {

    JPanel cards; // a panel that uses CardLayout
    ExerciseCollection ec;

    public AddExerciseMenu(ExerciseCollection ec) {
        this.ec = ec;
        // Set layout
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        GridBagConstraints cons = new GridBagConstraints();

        // Create panel with CardLayout for food type cardsion
        cards = new JPanel(new CardLayout());

        // Create panels (cards) for the food types
        JPanel addBasic = new JPanel();
        // JPanel addRecipe = new JPanel();

        // Create panel to cards type of food to add
        JPanel radPanel = new JPanel();

        // Create the radio buttons and put them in a group
        JRadioButton radBasicFood = new JRadioButton("Exercise");

        ButtonGroup typecards = new ButtonGroup();
        typecards.add(radBasicFood);

        // Add radPanel to addFood
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = 0;
        this.add(radPanel, cons);

        /*
         * 
         * Add an Exercise
         * 
         */

        // Set addBasic layout
        addBasic.setLayout(layout);

        // Add input fields, labels and set the layout constraints for each
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = 0;
        addBasic.add(new JLabel("Enter the exercise name: "), cons);

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

        // Add submit button
        JButton addBtn = new JButton("Add an Exercise");
        addBtn.addActionListener(new SwingUIExerciseMenuListener(addBasic, ec));

        // Add submit button to card
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 5;
        addBasic.add(addBtn, cons);

        cards.add(addBasic, "Exercise");

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

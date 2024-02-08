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

import skeleton.Exercise;
import skeleton.controller.SwingUIExerciseMenuListener;
import skeleton.model.ExerciseCollection;
import skeleton.view.util.ExerciseRenderer;

public class UpdateExerciseMenu extends JPanel {
    JPanel cards; // a panel that uses CardLayoutnel{
    private ExerciseCollection ec;

    public UpdateExerciseMenu(ExerciseCollection ec) {
        this.ec = ec; 
        
        JPanel updateBasic = new JPanel();

        // Set layout
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        GridBagConstraints cons = new GridBagConstraints();

        // Create and add Combo Box to select food to add to recipe
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = 0;
        // Test data
        ArrayList<Exercise> exercises = new ArrayList<Exercise>(ec.getExerciseList());
        JComboBox<Exercise> fList = new JComboBox<Exercise>();
        fList.setRenderer(new ExerciseRenderer());
        for (Exercise obj : exercises) {
            fList.addItem(obj);
        }
        fList.addItemListener(new SwingUIExerciseMenuListener(updateBasic, ec));
        fList.setSelectedIndex(-1);
        this.add(fList, cons);

        /*
         * 
         * Update Exercise
         * 
         */

       

        // Set updateBasic layout
        updateBasic.setLayout(layout);

        // Add input fields, labels and set the layout constraints for each
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = 0;
        updateBasic.add(new JLabel("Name: "), cons);

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

        // Add submit button
        JButton addBtn = new JButton("Update a Exercise");
        addBtn.addActionListener(new SwingUIExerciseMenuListener(updateBasic, ec));
        // addBtn.addActionListener(new FoodMenuListener(updateBasic));

        // Add submit button to card
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 5;
        updateBasic.add(addBtn, cons);

        // Add food type options to addFood
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = 1;
        this.add(updateBasic, cons);
    }

    private JTextField inputField(int col) {
        JTextField inputField = new JTextField(col);
        return inputField;
    }
}
package skeleton.controller;

import javax.swing.JPanel;
import javax.swing.JTextField;

import skeleton.model.BasicExercise;
import skeleton.model.ExerciseCollection;
import skeleton.Exercise;

import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class SwingUIExerciseMenuListener implements ActionListener, ItemListener {

    private JPanel inputs;
    private ExerciseCollection ec;

    public SwingUIExerciseMenuListener(JPanel inputs, ExerciseCollection ec) {
        this.inputs = inputs;
        this.ec = ec;
    }

    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            // Add basic food and return to food menu
            case "Add an Exercise":

                JTextField exerciseName = (JTextField) inputs.getComponent(1);

                JTextField calories = (JTextField) inputs.getComponent(3);

                ec.addExercise(exerciseName.getText(), Double.parseDouble(calories.getText()));

                break;

            // Remove basic food and return to food menu
            case "Remove Exercise":
                JComboBox name = (JComboBox) inputs.getComponent(1);
                String removedExercise = ((Exercise) name.getSelectedItem()).getName();

                ec.removeExercise(removedExercise);

                break;

            case "Update a Exercise":
                JTextField updatedExercise = (JTextField) inputs.getComponent(1);

                JTextField updatedCalories = (JTextField) inputs.getComponent(3);

                String updates = ((BasicExercise) ((JComboBox) inputs.getParent().getComponent(0))
                        .getSelectedItem()).getName();

                
                ((BasicExercise) ec.find(updates)).setCalories(Double.parseDouble(updatedCalories.getText()));
                ((BasicExercise) ec.find(updates)).setName(updatedExercise.getText());

                break;

            // Move to basic food update menu in view
            case "Show Exercises":
                System.out.println("This case worked for show all");

                break;
        }

    }

    // Listeners for the exercise menu
    public void itemStateChanged(ItemEvent e) {
        // Check for state change
        if (e.getStateChange() == 1) {

            // Get Exercise data and put it in fields
            JPanel fields = (JPanel) inputs;
            Exercise exercise = ((Exercise) e.getItem());

            JTextField name = (JTextField) fields.getComponent(1);
            name.setText(exercise.getName());

            JTextField calories = (JTextField) fields.getComponent(3);
            calories.setText(String.valueOf(exercise.getCalories()));
        }
    }

}

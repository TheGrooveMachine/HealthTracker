package skeleton.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import skeleton.Exercise;
import skeleton.controller.SwingUIExerciseMenuListener;
import skeleton.model.ExerciseCollection;
import skeleton.view.util.ExerciseRenderer;

public class RemoveExerciseMenu extends JPanel {
    private ExerciseCollection ec;

    public RemoveExerciseMenu(ExerciseCollection ec) {
        this.ec = ec;
        // Set layout
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        GridBagConstraints cons = new GridBagConstraints();

        // Set layout
        setLayout(layout);

        // Create and add label
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = 1;
        this.add(new JLabel("Select Exercise: "), cons);

        // Create and add Combo Box to select food to delete
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 1;

        // Test data
        ArrayList<Exercise> exercises = new ArrayList<Exercise>(ec.getExerciseList());
        JComboBox<Exercise> fList = new JComboBox<Exercise>();
        fList.setRenderer(new ExerciseRenderer());
        for (Exercise obj : exercises) {
            fList.addItem(obj);
        }
        // fList.addItemListener();
        fList.setSelectedIndex(-1);
        this.add(fList, cons);

        // Add submit button
        JButton remove = new JButton("Remove Exercise");
        remove.addActionListener(new SwingUIExerciseMenuListener(this, ec));
        // addBtn.addActionListener(new FoodMenuListener(addRecipe));

        // Add remove button
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 2;
        this.add(remove, cons);
    }
}

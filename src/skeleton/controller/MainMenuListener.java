package skeleton.controller;

import skeleton.model.Data;
import skeleton.model.ExerciseCollection;
import skeleton.model.FoodCollection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class MainMenuListener implements ActionListener, ItemListener {

    private FoodCollection fc;
    private ExerciseCollection ec;
    private Data type;

    public MainMenuListener(String label, FoodCollection fc, ExerciseCollection ec, Data type) {
        this.fc = fc;
        this.ec = ec;
        this.type = type;
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            // Add basic food and return to food menu
            case "Save":
                fc.saveToFile(type);

                ec.saveToFile(type);
                break;

            // Remove basic food and return to food menu
            case "Exit":
                System.exit(0);
                break;

        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub

    }

}

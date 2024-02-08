package skeleton.view;

import javax.swing.JButton;
import javax.swing.JPanel;

import skeleton.controller.MainMenuListener;
import skeleton.model.Data;
import skeleton.model.ExerciseCollection;
import skeleton.model.FoodCollection;

public class MainMenuPanel extends JPanel {

    private FoodCollection fc;
    private ExerciseCollection ec;
    private Data type;

    public MainMenuPanel(FoodCollection fc, ExerciseCollection ec, Data type) {
        this.fc = fc;
        this.ec = ec;
        this.type = type;

        this.add(menuButton("Save", fc));
        this.add(menuButton("Exit", fc));
    }

    private JButton menuButton(String label, FoodCollection fc) {
        JButton button = new JButton(label);
        button.addActionListener(new MainMenuListener(label, fc, ec, type));
        return button;
    }

}

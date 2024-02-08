package skeleton.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import skeleton.Food;
import skeleton.controller.FoodMenuListener;
import skeleton.model.CSV;
import skeleton.model.Data;
import skeleton.model.FoodCollection;
import skeleton.view.util.FoodRenderer;

public class RemoveFoodMenu extends JPanel {

    private FoodCollection fc;

    public RemoveFoodMenu(FoodCollection fc) {
        this.fc = fc;
        
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
        this.add(new JLabel("Food Select: "), cons);

        // Create and add Combo Box to select food to delete
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 1;
        JComboBox<Food> fList = new JComboBox<Food>();

        // Test data
        ArrayList<Food> foodList = new ArrayList<Food>(fc.getFoodList());
        fList.setRenderer(new FoodRenderer());
        for (Food obj : foodList) {
            fList.addItem(obj);
        }
        //fList.addItemListener();
        fList.setSelectedIndex(-1);
        this.add(fList, cons);

        // Add submit button
        JButton remove = new JButton("Remove Food");
        remove.addActionListener(new FoodMenuListener(this, fc));

        // Add remove button
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 2;
        this.add(remove, cons);
    }
}

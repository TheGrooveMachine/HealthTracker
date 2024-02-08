package skeleton.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import skeleton.model.DailyLog;

import skeleton.Food;
import skeleton.model.CSV;
import skeleton.model.Data;
import skeleton.model.FoodCollection;
import skeleton.model.Log;
import skeleton.view.util.FoodRenderer;
import skeleton.view.util.LogRenderer;

public class RemoveDailyLogMenu extends JPanel {

    public RemoveDailyLogMenu() {
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
        this.add(new JLabel("Select Date: "), cons);

        // Test data
        Data data = new CSV();
        DailyLog dl = new DailyLog();
        ArrayList<Log> logList = new ArrayList<Log>();

        // Create and add Combo Box to select food to delete
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 1;
        JComboBox<Log> fList = new JComboBox<Log>();
        fList.setRenderer(new LogRenderer());
        for (Log obj : logList) {
            fList.addItem(obj);
        }
        //fList.addItemListener();
        fList.setSelectedIndex(-1);
        this.add(fList, cons);

        // Add submit button
        JButton remove = new JButton("Remove DailyLog");
        // addBtn.addActionListener(new FoodMenuListener(addRecipe));

        // Add remove button
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 2;
        this.add(remove, cons);
    }
}

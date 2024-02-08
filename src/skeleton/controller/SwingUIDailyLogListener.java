package skeleton.controller;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import skeleton.Food;
import skeleton.view.AddDailyLogMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.awt.event.ItemEvent;
import java.awt.CardLayout;
import java.awt.Component;

import skeleton.controller.DailyLogController;
import skeleton.model.CalorieLog;
import skeleton.model.DailyLog;
import skeleton.model.FoodLog;
import skeleton.model.Log;
import skeleton.model.WeightLog;

public class SwingUIDailyLogListener implements ActionListener{

    private JPanel inputs;
    private DailyLog dl;

    public SwingUIDailyLogListener(JPanel inputs) {
        this.inputs = inputs;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            // Add basic food and return to food menu
            case "Add A DailyLog":

                JTextField date = (JTextField) inputs.getComponent(1);
                
                JTextField calories = (JTextField) inputs.getComponent(3);

                JTextField weight = (JTextField) inputs.getComponent(5);

                
                WeightLog wl = new WeightLog(LocalDate.parse(date.getText()), Double.parseDouble(weight.getText()));
                CalorieLog cl = new CalorieLog(LocalDate.parse(date.getText()), Double.parseDouble(calories.getText()));

                dl.addDailyLog(wl);
                dl.addDailyLog(cl);

                // fc.addBasicFood(foodName.getText(), Double.parseDouble(calories.getText()),
                //         Double.parseDouble(fat.getText()), Double.parseDouble(carbs.getText()),
                //         Double.parseDouble(protein.getText()));

                break;

            case "Remove DailyLog":
                JComboBox name = (JComboBox) inputs.getComponent(1);
                String removedDate = ((Component) name.getSelectedItem()).getName();



                dl.removeDailyLogDate(LocalDate.parse(removedDate));

                break;

            // // Move to basic food update menu in view
            case "Update A DailyLog":
                JTextField updatedFood = (JTextField) inputs.getComponent(1);
                System.out.println(updatedFood.getText());

                JTextField updatedCalories = (JTextField) inputs.getComponent(3);
                System.out.println(updatedCalories.getText());

                JTextField updatedFat = (JTextField) inputs.getComponent(5);
                System.out.println(updatedFat.getText());

                JTextField updatedCarbs = (JTextField) inputs.getComponent(7);
                System.out.println(updatedCarbs.getText());

                JTextField updatedProtein = (JTextField) inputs.getComponent(9);
                System.out.println(updatedProtein.getText());

                // String updates = ((BasicFood) ((JComboBox) inputs.getParent().getParent().getComponent(0))
                //         .getSelectedItem()).getName();

                // System.out.println(updates);

                // ((BasicFood) fc.find(updates)).setName(updatedFood.getText());
                // ((BasicFood) updates.getSelectedItem()).setName(update1);

                break;

           
        }

        
    }
}

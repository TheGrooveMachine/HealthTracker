package skeleton.view.util;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import java.awt.Component;

import skeleton.Food;

public class FoodRenderer extends BasicComboBoxRenderer {
    public Component getListCellRendererComponent(
            JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value instanceof Food) {
            Food food = (Food) value;
            setText(food.getName());
        }

        return this;
    }
}
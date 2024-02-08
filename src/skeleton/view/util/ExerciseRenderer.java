package skeleton.view.util;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import java.awt.Component;

import skeleton.Exercise;

public class ExerciseRenderer extends BasicComboBoxRenderer {
    public Component getListCellRendererComponent(
            JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value instanceof Exercise) {
            Exercise exercise = (Exercise) value;
            setText(exercise.getName());
        }

        return this;
    }
}
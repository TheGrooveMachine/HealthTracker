package skeleton.view.util;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import java.awt.Component;

import skeleton.model.Log;

public class LogRenderer extends BasicComboBoxRenderer {
    public Component getListCellRendererComponent(
            JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value instanceof Log) {
            Log log = (Log) value;
            setText(log.getDate().toString());
        }

        return this;
    }
}
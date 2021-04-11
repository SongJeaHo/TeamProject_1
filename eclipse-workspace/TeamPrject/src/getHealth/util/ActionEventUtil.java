package getHealth.util;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ActionEventUtil {
    public static JComboBox getSource(ActionEvent event) {
        if (event.getSource() instanceof JComboBox) {
            return (JComboBox) event.getSource();
        } else {
            throw new ClassCastException();
        }
    }
}

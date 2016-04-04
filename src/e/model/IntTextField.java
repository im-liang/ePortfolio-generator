/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.model;

/**
 *
 * @author jieliang
 */
import javafx.scene.control.TextField;

public class IntTextField extends TextField {
    @Override
    public void replaceText(int start, int end, String text) {
        if (validate(text)) {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String text) {
        if (validate(text)) {
            super.replaceSelection(text);
        }
    }

    private boolean validate(String text) {
        return ("".equals(text) || text.matches("[0-9]"));
    }

}

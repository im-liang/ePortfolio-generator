/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.view;

/**
 *
 * @author jieliang
 */
import e.error.ErrorHandler;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class DoubleTextField extends TextField {

    public DoubleTextField() {
        try {
        this.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent t) {
                char ar[] = t.getCharacter().toCharArray();
                char ch = ar[t.getCharacter().toCharArray().length - 1];
                if (!(ch >= '0' && ch <= '9')) {
                    ErrorHandler eh = new ErrorHandler(null);
                    eh.inValidNumber();
                    t.consume();
                }
            }
        });
        } catch (Exception e) {
            
        }
    }

}

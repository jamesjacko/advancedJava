
package calculator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;

public class MemButtonListener implements ActionListener  {
    private CalculatorFrame calcFrame;
    public MemButtonListener(CalculatorFrame calc){
        this.calcFrame = calc;
    }
    public void actionPerformed(ActionEvent e){
        JButton pressed = (JButton) e.getSource();
        String operation = pressed.getText();
        if(operation.equals("C")){
            calcFrame.reset();
        }
        if(operation.equals("Backspace")){
            calcFrame.backspace();
        }
        if(operation.equals("CE")){
            calcFrame.clearEntry();
        }
    }
}

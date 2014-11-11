
package calculator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;

public class MemButtonListener implements ActionListener  {
    private CalculatorFrame calc;
    public MemButtonListener(CalculatorFrame calc){
        this.calc = calc;
    }
    public void actionPerformed(ActionEvent e){
        JButton pressed = (JButton) e.getSource();
        String operation = pressed.getText();
        if(operation.equals("C")){
            calc.reset();
        }
        if(operation.equals("Backspace")){
            calc.backspace();
        }
        if(operation.equals("CE")){
            calc.clearEntry();
        }
    }
}

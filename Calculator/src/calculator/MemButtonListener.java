
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
        
        switch(operation){
            case "C":
                calcFrame.reset();
                break;
            case "Backspace":
                calcFrame.backspace();
                break;
            case "CE":
                calcFrame.clearEntry();
                break;
            case "MC":
                calcFrame.memoryClear();
                break;
            case "MR":
                calcFrame.memoryRecall();
                break;
            case "MS":
                calcFrame.memoryStore();
                break;
            case "M+":
                calcFrame.memoryPlus();
                break;
        }
    }
}

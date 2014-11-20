
package calculator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;

/**
 *
 * @author James Jackson
 * Listener class for the memory buttons
 * @date 20/11/2014
 */
public class MemButtonListener implements ActionListener  {
    // the frame to be operated on
    private CalculatorFrame calcFrame;
    /**
     * Constructor for a new listener
     * @param calc the Calculator Frame for operations
     */
    public MemButtonListener(CalculatorFrame calc){
        this.calcFrame = calc;
    }
    /**
     * Method for handling click
     * @param e the event that triggered the method
     */
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

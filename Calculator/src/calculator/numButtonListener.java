
package calculator;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author James Jackson
 * Listener class for the number buttons including . and +/-
 * @date 20/11/2014
 */
public class numButtonListener implements ActionListener {
    CalculatorFrame frame;
    /**
     * Constructor for a new listener
     * @param calc the Calculator Frame for operations
     */
    public numButtonListener(CalculatorFrame frame){
        this.frame = frame;
    }
    /**
     * Method for handling click
     * @param e the event that triggered the method
     */
    public void actionPerformed(ActionEvent e){
        JButton pressed = (JButton) e.getSource();
        String comp = pressed.getText();
        if(comp.equalsIgnoreCase("+/-"))
            frame.negate();
            
        else if(comp.equalsIgnoreCase("."))
            frame.addDecimal();
        else
            frame.addToOperand(pressed.getText());
        frame.setTextValue();
    }
}

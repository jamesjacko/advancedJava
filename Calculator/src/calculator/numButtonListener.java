/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author jacko
 */
public class numButtonListener implements ActionListener {
    CalculatorFrame frame;
    public numButtonListener(CalculatorFrame frame){
        this.frame = frame;
    }
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

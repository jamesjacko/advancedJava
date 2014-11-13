/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
/**
 *
 * @author jacko
 */
public class CalculatorMenu extends JMenuBar {
    
    public CalculatorMenu(){
        add(editMenu());
        add(viewMenu());
        add(helpMenu());
    }
    
    private JMenu editMenu(){
        JMenu menu = new JMenu("Edit");
        menu.add(createMenuItem("Copy"));
        menu.add(createMenuItem("Paste", false));
        return menu;
    }
    private JMenu viewMenu(){
        JMenu menu = new JMenu("Edit");
        menu.add(createMenuItem("Standard"));
        menu.add(createMenuItem("Scientific"));
        menu.addSeparator();
        menu.add(createMenuItem("Digit grouping"));
        return menu;
    }
    private JMenu helpMenu(){
        JMenu menu = new JMenu("Help");
        menu.add(createMenuItem("Help Topics"));
        menu.addSeparator();
        menu.add(createMenuItem("About Calculator"));
        return menu;
    }
    private JMenuItem createMenuItem(String name){
        JMenuItem item = new JMenuItem(name);
        item.addActionListener(new menuListener());
        return item;
    }
    private JMenuItem createMenuItem(String name, Boolean enabled){
        JMenuItem item = new JMenuItem(name);
        item.addActionListener(new menuListener());
        item.setEnabled(enabled);
        return item;
    }
    
    private class menuListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            JMenuItem clicked = (JMenuItem) e.getSource();
            JOptionPane.showMessageDialog(null, clicked.getText());
        }
    }
    
}

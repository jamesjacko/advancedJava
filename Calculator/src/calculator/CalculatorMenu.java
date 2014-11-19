/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author jacko
 */
public class CalculatorMenu extends JMenuBar {
    private CalculatorFrame frame;
    public CalculatorMenu(CalculatorFrame frame){
        this.frame = frame;
        add(editMenu());
        add(viewMenu());
        add(helpMenu());
    }
    
    private JMenu editMenu(){
        JMenu menu = new JMenu("Edit");
        menu.add(createMenuItem("Copy"));
        menu.add(createMenuItem("Paste"));
        return menu;
    }
    private JMenu viewMenu(){
        JMenu menu = new JMenu("View");
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
            switch(clicked.getText()){
                case "Digit grouping":
                    frame.setGrouping();
                    break;
                case "Help Topics":
                    JFrame helpFrame = new JFrame("Help");
                    JEditorPane helpPane = new JEditorPane();
                    try {
                        helpPane.setPage("file:///Users/jacko/Documents/uni/advancedJava/Calculator/src/calculator/help.html");
                    } catch (IOException ex) {
                        Logger.getLogger(CalculatorMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    helpPane.setEditable(false);
                    helpPane.setBackground(Color.WHITE);
                    JScrollPane scrollPane = new JScrollPane(helpPane);
                    scrollPane.setBackground(Color.WHITE);
                    helpFrame.add(scrollPane);
                    helpFrame.setSize(500, 500);
                    helpFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    helpFrame.setVisible(true);
                    break;
                case "Copy":
                    frame.copy();
                    break;
                case "Paste":
                    frame.paste();
                    break;
            } 
        }
    }
    
}

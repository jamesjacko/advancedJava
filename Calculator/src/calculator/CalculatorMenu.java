
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
 * @author James Jackson
 * Calculator menu class to build the menu for the calculator, subclass 
 * of the JMenuBar
 * @date 20/11/2014
 */
public class CalculatorMenu extends JMenuBar {
    private CalculatorFrame frame;
    private JMenuItem paste;
    /**
     * Constructs the class instance
     * @param frame the CalculatorFram instance for operations
     */
    public CalculatorMenu(CalculatorFrame frame){
        this.frame = frame;
        add(editMenu());
        add(viewMenu());
        add(helpMenu());
    }
    
    /**
     * Build the edit menu
     * @return the edit menu
     */
    private JMenu editMenu(){
        JMenu menu = new JMenu("Edit");
        menu.add(createMenuItem("Copy"));
        paste = createMenuItem("Paste", false);
        menu.add(paste);
        return menu;
    }
    
    /**
     * Build the view menu
     * @return the view menu
     */
    private JMenu viewMenu(){
        JMenu menu = new JMenu("View");
        menu.add(createMenuItem("Standard"));
        menu.add(createMenuItem("Scientific"));
        menu.addSeparator();
        JCheckBoxMenuItem grouping = new JCheckBoxMenuItem("Digit grouping");
        grouping.addActionListener(new checkBoxListener());
        menu.add(grouping);
        return menu;
    }
    
    /**
     * Build the help menu
     * @return the help menu
     */
    private JMenu helpMenu(){
        JMenu menu = new JMenu("Help");
        menu.add(createMenuItem("Help Topics"));
        menu.addSeparator();
        menu.add(createMenuItem("About Calculator"));
        return menu;
    }
    
    /**
     * Build a menu item
     * @param name the name for the item
     * @return a menu item
     */
    private JMenuItem createMenuItem(String name){
        JMenuItem item = new JMenuItem(name);
        item.addActionListener(new menuListener());
        return item;
    }
    
    /**
     * Build a menu item
     * @param name the name for the item
     * @param enabled whether it is enabled or not
     * @return a menu item
     */
    private JMenuItem createMenuItem(String name, Boolean enabled){
        JMenuItem item = new JMenuItem(name);
        item.addActionListener(new menuListener());
        item.setEnabled(enabled);
        return item;
    }
    
    /**
     * Inner class for the checkbox menu item which implements the ActionListener interface
     */
    private class checkBoxListener implements ActionListener{
        /**
         * method to catch an action performed on the menu
         * @param e the action event
         */
        public void actionPerformed(ActionEvent e){
            JCheckBoxMenuItem item = (JCheckBoxMenuItem) e.getSource();
            if(item.isSelected())
                frame.setGrouping(true);
            else
                frame.setGrouping(false);
        }
        
    }
    
    /**
     * Inner class for the menu buttons which implements the ActionListener interface
     */
    private class menuListener implements ActionListener{
        private JFrame helpFrame;
        private JEditorPane helpPane;
        
        /**
         * Builds the help screen frame
         */
        private void buildHelpScreen(){
            helpFrame = new JFrame("Help");
            helpPane = new JEditorPane();
            helpPane.setEditable(false);
            helpPane.setBackground(Color.WHITE);
            JScrollPane scrollPane = new JScrollPane(helpPane);
            scrollPane.setBackground(Color.WHITE);
            helpFrame.add(scrollPane);
            helpFrame.setSize(500, 500);
            helpFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        }
        
        /**
         * method to catch an action performed on the menu
         * @param e the action event
         */
        public void actionPerformed(ActionEvent e){
            // get the source of the event and cast it to a JMenuItem
            JMenuItem clicked = (JMenuItem) e.getSource();
            
            //build help frame
            buildHelpScreen();
            switch(clicked.getText()){
                case "Help Topics":
                    try {
                        helpPane.setPage("file:///Users/jacko/Documents/uni/advancedJava/Calculator/src/calculator/help.html");
                    } catch (IOException ex) {
                        Logger.getLogger(CalculatorMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    helpFrame.setVisible(true);
                    break;
                case "About Calculator":
                    try {
                        helpPane.setPage("file:///Users/jacko/Documents/uni/advancedJava/Calculator/src/calculator/about.html");
                    } catch (IOException ex) {
                        Logger.getLogger(CalculatorMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    helpFrame.setSize(500,350);
                    helpFrame.setVisible(true);
                    break;
                case "Copy":
                    frame.copy();
                    paste.setEnabled(true);
                    break;
                case "Paste":
                    frame.paste();
                    break;
            } 
        }
    }
    
}

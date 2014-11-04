/**
 * @title Craps Game Frame
 * @description Uses the CrapsGame and creates a GUI for it.
 * @author James Jackson
 * @date 04/11/2014
 */
package CrapsGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CrapsGameFrame extends JFrame {
    
    private final int FRAME_HEIGHT = 300;
    private final int DIE_SIZE = 100;
    private final int FRAME_WIDTH = DIE_SIZE * 5;
    private JLabel lblSum, lblPoint;
    private final CrapsGame game;
    private JButton roll, reset;

    /**
     * Constructor builds the game using various helper methods
     * @param title the title of the frame
     */
    public CrapsGameFrame(String title){
        //We are extending JFrame so need to call JFrame's construtor
        super(title);   
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        
        game = new CrapsGame(100);
        JPanel layout = createLayoutPanel();
        add(layout);
        pack();
        setVisible(true);
    }
    
    /**
     * Creates the dice panel and adds the game's dice to it
     * @return the dice panel
     */
    private JPanel createDicePanel(){
        JPanel dicePanel = new JPanel(new GridLayout(1,2));
        DieComponent[] dice = game.getDice();
        dicePanel.add(dice[0]);
        dicePanel.add(dice[1]);
        return dicePanel;
    }
    
    /**
     * Creates the control panel and adds buttons to it
     * @return the control panel
     */
    private JPanel createControlPanel(){
        JPanel controlPanel = new JPanel();
        roll = new JButton("Roll 'em!");
        reset = new JButton("Reset");
        reset.setEnabled(false);
        roll.addActionListener(new RollListener());
        reset.addActionListener(new resetListener());
        controlPanel.add(roll);
        controlPanel.add(reset);
        return controlPanel;
        
    }
    
    /**
     * Creates the Label panel and adds labels to it
     * @return the label panel
     */
    private JPanel createLabelPanel(){
        JPanel labelPanel = new JPanel();
        lblSum = new JLabel("Sum = 0");
        lblPoint = new JLabel("Point = 0");
        labelPanel.add(lblSum);
        labelPanel.add(lblPoint);
        return labelPanel;
    }
    
    /**
     * Creates the overall layout panel and invokes the other panel methods
     * to add other panels to it
     * @return the layout panel
     */
    private JPanel createLayoutPanel(){
        JPanel layout = new JPanel(new BorderLayout());
        layout.add(createLabelPanel(), BorderLayout.NORTH);
        layout.add(createDicePanel(), BorderLayout.CENTER);
        layout.add(createControlPanel(), BorderLayout.SOUTH);
        return layout;
    }
    
    /**
     * updates the labels on the frame
     */
    private void updateLabels(){
        lblSum.setText("Sum = " + game.getSum());
        lblPoint.setText("Point = " + game.getPoint());
    }
    
    /**
     * Action listener for the roll button
     */
    class RollListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event){
            String message;
            if(game.isFirstRoll())
                message = game.doFirstRoll();
            else
                message = game.rollAgain();

            System.out.println(message);
            updateLabels();
            if(game.isOver()){
                if(game.isWon())
                    JOptionPane.showMessageDialog(null, "You win, big man!");
                else
                    JOptionPane.showMessageDialog(null, "Loser ...");
                // game is over so enable rest button
                reset.setEnabled(true);
            }
        }    
    }
    /**
     * action listener for the rest button
     */
    class resetListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            game.reset();
            // disable the reset buttons
            reset.setEnabled(false);
            // update the labels will zero them
            updateLabels();
        }
    }
}

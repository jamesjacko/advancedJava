/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CrapsGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author User
 */
public class CrapsGameFrame extends JFrame {
    
    private final int FRAME_HEIGHT = 300;
    private final int DIE_SIZE = 100;
    private final int FRAME_WIDTH = DIE_SIZE * 5;
    public CrapsGameFrame(String title){
        super(title);   
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        
        CrapsGame game = new CrapsGame(100);
        DieComponent[] dice = game.getDice();
        //DieComponent die2 = new DieComponent(100, 0, 0, 4, Color.RED);
        JPanel dicePanel = new JPanel(new GridLayout(1,2));
        dicePanel.add(dice[0]);
        dicePanel.add(dice[1]);
        //dicePanel.add(die2, BorderLayout.WEST);
        JPanel controlPanel = new JPanel();
        JButton roll = new JButton("Roll 'em!");
        
        class RollListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent event){
                if(game.isFirstRoll())
                    game.doFirstRoll();
                else
                    game.rollAgain();
                if(game.isOver()){
                    if(game.isWon())
                        JOptionPane.showMessageDialog(null, "You win, big man!");
                    else
                        JOptionPane.showMessageDialog(null, "Loser ...");
                }
                    
            }
               
           
        }
        
        roll.addActionListener(new RollListener());
        
        controlPanel.add(roll);
        JPanel layout = new JPanel(new BorderLayout());
        
        
        layout.add(dicePanel, BorderLayout.NORTH);
        layout.add(controlPanel, BorderLayout.SOUTH);
        add(layout);
        pack();
        setVisible(true);
    }
    private void drawDice(){
        
        
    }
}

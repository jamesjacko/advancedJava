/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CrapsGame;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author User
 */
public class CrapsGameFrame extends JFrame {
    private final int FRAME_WIDTH = 500;
    private final int FRAME_HEIGHT = 300;
    public CrapsGameFrame(String title){
        super(title);   
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        
        DieComponent die1 = new DieComponent(100, 10, 10, 4, Color.BLACK);
        DieComponent die2 = new DieComponent(100, 300, 10, 4, Color.RED);
        add(die1);
        add(die2);
        setVisible(true);
    }
    
    private void drawDice(){
        
        
    }
}

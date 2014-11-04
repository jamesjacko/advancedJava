/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CrapsGame;
import javax.swing.JComponent;
import java.awt.*;
import java.util.Random;
/**
 *
 * @author User
 */
public class DieComponent extends JComponent {
    private DiceFace die;
    private int value;
    private Random rand = new Random();
    
    public DieComponent(int side, int x, int y, Color faceColor){
        die = new DiceFace(side,x,y,6,faceColor);
        setPreferredSize(new Dimension(side*2, side*2));
    }
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        die.draw(g2);
    }
    public void setCoords(int x, int y){
        die.setCoords(x, y);
        repaint();
    }
    
    public void roll(){
        this.value = rand.nextInt(6) + 1;
        die.setDiceFace(this.value);
        repaint();
    }
    
    public int getFaceValue(){
        return this.value;
    }
    
    
    
    
}

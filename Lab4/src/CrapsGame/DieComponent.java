/**
 * @title Die Component
 * @description Creates and instance of DiceFace and draws it as well as
 *              providing methods for interaction.
 * @author James Jackson
 * @date 04/11/2014
 */
package CrapsGame;
import javax.swing.JComponent;
import java.awt.*;
import java.util.Random;

public class DieComponent extends JComponent {
    private DiceFace die;
    private int value;
    private Random rand = new Random();
    
    /**
     * DieComponent Constructor
     * @param side the size of the die in pixels
     * @param x the x position of the die
     * @param y the y position of the die
     * @param faceColor the face color of the die
     */
    public DieComponent(int side, int x, int y, Color faceColor){
        die = new DiceFace(side,x,y,6,faceColor);
        setPreferredSize(new Dimension(side*2, side + 40));
    }
    /**
     * Override the paintComponent method
     * @param g graphics object to draw with
     */
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        die.draw(g2);
    }

    /**
     * Sets the coordinates of the die
     * @param x the x position
     * @param y the y positon
     */
    public void setCoords(int x, int y){
        die.setCoords(x, y);
        repaint();
    }
    
    /**
     * Rolls the die and repaints the object
     */
    public void roll(){
        this.value = rand.nextInt(6) + 1;
        die.setDiceFace(this.value);
        repaint();
    }
    
    /**
     * gets the face value of the die
     * @return the face value
     */
    public int getFaceValue(){
        return this.value;
    }
    
    /**
     * sets the face value of the die and repaints
     * @param val the value to set the die to
     */
    public void setFaceValue(int val){
        this.value = val;
        die.setDiceFace(this.value);
        repaint();
    }
}

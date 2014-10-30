/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CrapsGame;
import javax.swing.JComponent;
import java.awt.*;
/**
 *
 * @author User
 */
public class DieComponent extends JComponent {
    private DiceFace die;
    
    public DieComponent(int side, int x, int y, int face, Color faceColor){
        die = new DiceFace(side,x,y,face,faceColor);

    }
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        die.draw(g2);
    }
    public void setCoords(int x, int y){
        die.setCoords(x, y);
    }
}

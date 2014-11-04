package CrapsGame;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/*
   Revised and tested 10/06/2010
*/



public class DiceFace
{
   // Holds the seven possible dot positions on a standard die
   private Ellipse2D.Double[] dots = new Ellipse2D.Double[7];
   
	private Rectangle box;
   private int xLeft;
   private int yTop;
   private int side;
   private int diceValue;
   
   //added faceColor to allow user to color the die
   private Color faceColor;

    /**
     * Dice Face constructor
     * @param s the dimension of the face
     * @param x the x position
     * @param y y position
     * @param v face value
     * @param faceColor color of the face
     */
    public DiceFace(int s, int x, int y, int v, Color faceColor)
   {
      side = s;		// dimension of dice face
		xLeft = x;		// position
		yTop = y;		// position
		diceValue = v; // pip value
                this.faceColor = faceColor;
   }
   
    /**
     * set the face value of the die 
     * @param v the value to set to
     */
    public void setDiceFace(int v)
   {
      diceValue = v;
   }   
    
    /**
     * Set the coordinates of the die
     * @param x the x position
     * @param y the y position 
     */
   public void setCoords(int x, int y){
       xLeft = x;
       yTop = y;
   }

    /**
     * draw the die
     * @param g2 the graphics object used to draw
     */
    public void draw(Graphics2D g2)
   {
		box = new Rectangle(xLeft, yTop, side, side);
      makeDots();

      // set the facecolor of the die
		g2.setColor(faceColor);
      g2.fill(box);
      
		// White dots on black
      g2.setColor(Color.WHITE);
      
		// Draw dots
      if (diceValue == 1) 
			g2.fill(dots[0]);
      else if (diceValue == 2)
      {
         g2.fill(dots[1]); g2.fill(dots[2]);
      }
      else if (diceValue == 3)
      {
         g2.fill(dots[0]); g2.fill(dots[1]); g2.fill(dots[2]);
      }
      else if (diceValue == 4)
      {
         g2.fill(dots[1]); g2.fill(dots[2]);
         g2.fill(dots[3]); g2.fill(dots[4]);
      }
      else if (diceValue == 5)
      {
         g2.fill(dots[0]); g2.fill(dots[1]);
         g2.fill(dots[2]); g2.fill(dots[3]); g2.fill(dots[4]);
       }
      else if (diceValue == 6)
      {
         g2.fill(dots[1]); g2.fill(dots[2]); g2.fill(dots[3]);
         g2.fill(dots[4]); g2.fill(dots[5]); g2.fill(dots[6]);
       }
   }

    /**
     *
     */
    public void makeDots()
   {
      int w = side/6;   // dot width
      int h = side/6;   // dot height

      dots[0] =  new Ellipse2D.Double(xLeft + (2.5 * side)/6,
                                         yTop + (2.5 * side)/6, h, w);
                                         
      dots[1] = new Ellipse2D.Double(xLeft + (3.75 * side)/6,
                                        yTop + (3.75 * side)/6, h, w);
                                       
      dots[2] = new Ellipse2D.Double(xLeft + (1.25 * side)/6,
                                        yTop + (1.25 * side)/6, h, w);
                             
      dots[3] = new Ellipse2D.Double(xLeft + (1.25 * side)/6,
                                        yTop + (3.75 * side)/6, h, w);
                                        
      dots[4] = new Ellipse2D.Double(xLeft + (3.75 * side)/6,
                                        yTop + (1.25 * side)/6, h, w);
                                 
      dots[5] =  new Ellipse2D.Double(xLeft + (1.25 * side)/6,
                                         yTop + (2.5 * side)/6, h, w);
                                         
      dots[6] =  new Ellipse2D.Double(xLeft + (3.75 * side)/6,
                                         yTop + (2.5 * side)/6, h, w);
    }
 }
/**
 * @title Craps Game
 * @description Logic for the game of craps
 * @author James Jackson
 * @date 04/11/2014
 */
package CrapsGame;


import java.awt.Color;
import java.util.Random;
import java.util.Arrays;


public class CrapsGame {
    // winning combinations in first sub array and losing in second
      private int[][] testValues = {{7,11}, {2,3,12}};
      private int turn, sum, point;
      private DieComponent die1, die2;
      private boolean go, winner;
      private Random rand = new Random();
      
    /**
     * Constructor initializes the instance variables
     */
    public CrapsGame(){
          this.turn = 0;
          this.sum = 0;
          this.point = 0;
          this.go = true;
          this.die1 = new DieComponent(10, 10, 20, Color.BLACK);
          this.die2 = new DieComponent(10, 10, 20, Color.RED);
      }
    /**
     * Constructor initializes the instance variables
     * @param dieSize the size of the required dice
     */
    public CrapsGame(int dieSize){
          this.turn = 0;
          this.sum = 0;
          this.point = 0;
          this.go = true;
          this.die1 = new DieComponent(dieSize, dieSize/2, 20, Color.BLACK);
          this.die2 = new DieComponent(dieSize, dieSize/2, 20, Color.RED);
      }
    /**
     * Roll the 2 dice
     */
      private void roll(){
          this.die1.roll();
          this.die2.roll();
      }
      
    /**
     * Take the first turn of the game
     * @return the current status of the game
     */
    public String doFirstRoll(){
        roll();
        this.sum = this.die1.getFaceValue() + this.die2.getFaceValue();
        String message = "d1 = " + this.die1.getFaceValue() + " d2 = " + this.die2.getFaceValue();
        // total present in the winning array
        if(Arrays.binarySearch(testValues[0], this.sum) > -1){
            win();
        // total present in losing array
        }else if(Arrays.binarySearch(testValues[1], this.sum) > -1){
            lose();
        } else {
            this.point = this.sum;
        }
        this.turn ++;
        return message;
      }
      
    /**
     * take further turns using the rollAgain method
     * @return the game status
     */
    public String rollAgain(){
        roll();
        this.sum = this.die1.getFaceValue() + this.die2.getFaceValue();
        String message = "d1 = " + this.die1.getFaceValue() + " d2 = " + this.die2.getFaceValue();
        message += " sum = " + this.sum + " point = " + this.point;
        if(this.sum == this.point){
            win();
        } else if (this.sum == 7){
            lose();
        } 
        return message;
      }
      
    /**
     * Stops play continuing and indicates a win
     */
    public void win(){
          this.go = false;
          this.winner = true;
      }

    /**
     * Stops play and indicates a loss
     */
    public void lose(){
          this.go = false;
          this.winner = false;
      }
      
    /**
     * method to determine if the game has finished
     * @return is the game over
     */
    public boolean isOver(){
          return !this.go;
      }
      
    /**
     * method to determine if the player has won
     * @return the player has won or lost
     */
    public boolean isWon(){
          return this.winner;
      }
      
    /**
     * Method to check if the roll is first or not
     * @return this is or is not the first roll
     */
    public boolean isFirstRoll(){
          return this.turn == 0;
      }
      
    /**
     * Returns the dice within the game to be added to the panel
     * @return array of DieComponents to be used on a frame
     */
    public DieComponent[] getDice(){
          DieComponent[] dice = {this.die1, this.die2};
          return dice;
      }
      
    /**
     * Resets the game state to beginning including setting each die to 6
     */
    public void reset(){
          this.turn = this.sum = this.point = 0;
          this.go = true;
          this.winner = false;
          this.die1.setFaceValue(6);
          this.die2.setFaceValue(6);
      }
      
    /**
     * Get the value of sum
     * @return the value of sum
     */
    public int getSum(){
          return this.sum;
      }

    /**
     * get the value of point
     * @return the value of point
     */
    public int getPoint(){
          return this.point;
      }
}

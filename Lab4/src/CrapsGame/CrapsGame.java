/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CrapsGame;


import java.awt.Color;
import java.util.Random;
import java.util.Arrays;

/**
 *
 * @author User
 */
public class CrapsGame {
      private int[][] testValues = {{7,11}, {2,3,12}};
      private int turn, sum, point;
      private DieComponent die1, die2;
      private boolean go, winner;
      private Random rand = new Random();
      
      public CrapsGame(int dieSize){
          this.turn = 0;
          this.sum = 0;
          this.point = 0;
          this.go = true;
          die1 = new DieComponent(dieSize, dieSize/2, dieSize/2, Color.BLACK);
          die2 = new DieComponent(dieSize, dieSize/2, dieSize/2, Color.RED);
      }
      private void roll(){
          this.die1.roll();
          this.die2.roll();
      }
      
      public String doFirstRoll(){
        roll();
        this.sum = this.die1.getFaceValue() + this.die2.getFaceValue();
        String message = "d1 = " + this.die1.getFaceValue() + " d2 = " + this.die2.getFaceValue();
        if(Arrays.binarySearch(testValues[0], this.sum) > -1){
            win();
        }else if(Arrays.binarySearch(testValues[1], this.sum) > -1){
            lose();
        } else {
            this.point = this.sum;
        }
        this.turn ++;
        return message;
      }
      
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
      
      public void win(){
          this.go = false;
          winner = true;
      }
      public void lose(){
          this.go = false;
          winner = false;
      }
      
      public boolean isOver(){
          return !this.go;
      }
      
      public boolean isWon(){
          return this.winner;
      }
      
      public boolean isFirstRoll(){
          return this.turn == 0;
      }
      
      public DieComponent[] getDice(){
          DieComponent[] dice = {this.die1, this.die2};
          return dice;
      }
        
}

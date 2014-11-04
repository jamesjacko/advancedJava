/**
 * @title Craps Game Tester
 * @description Tests the logic of the CrapsGame Class
 * @author James Jackson
 * @date 04/11/2014
 */
package CrapsGame;

import java.util.Arrays;

public class CrapsGameTester {

    public static void main(String[] args){
        CrapsGame game = new CrapsGame();
        
        String message = game.doFirstRoll();
        System.out.println(message);

        while(!game.isOver())
        { 
        message = game.rollAgain();
        System.out.println(message);
        }

        if(game.isWon())
        System.out.println("You win, big man!");
        else
        System.out.println("Loser ..."); 
    }
}

package calculator;

import java.text.DecimalFormat;

/**
 *
 * @author James Jackson
 * Calculator class contains the logic of a calculator 
 * @date 20/11/2014
 */
public class Calculator {
    /* operands used in calculation
     * operands[0] - first operand
     * operands[1] - second operand
     * operands[2] - latest operand to be set
     */
    private double[] operands = {0.0, 0.0 ,0.0};
    private int opPointer;
    private String curOperator;
    private double answer;
    private double memory;
    private boolean first;
    
    /**
     * Calculator constructor sets the initial values of the instance variables
     */
    public Calculator(){
        opPointer = 0;
        answer = 0.0;
        memory = 0.0;
        first = true;
        curOperator = "";
    }
    
    /**
     * Calculate the current calculation in the calculator, will return the first
     * operand if the operator isn't set ie operator and second operand not set
     * @return the answer of the current calculation
     */
    private double calculate(){
        String op = curOperator == null ? "" : curOperator;
        switch(curOperator){
            case "+":
                answer = operands[0] + operands[1];
                break;
            case "-":
                answer = operands[0] - operands[1];
                break;
            case "*":
                answer = operands[0] * operands[1];
                break;
            case "/":
                if(operands[1] == 0)
                    answer = 0;
                else
                    answer = operands[0] / operands[1];
                break;
            default:
                answer = operands[0];
        }
        return answer;               
    }
    
    /**
     * Reset the state of the calculator
     */
    public void reset(){
       this.opPointer = 0;
       this.operands[0] = 0.0;
       this.operands[1] = 0.0;
       this.operands[2] = 0.0;
       this.answer = 0;
       this.first = true;
       
    }
    
    /**
     * Set the current operand of the calculator to the supplied number
     * @param num number to set the operand to.
     */
    public void setOperand(Double num){
        operands[opPointer] = num;
        operands[2] = num;
    }
    
    /**
     * Set the current operator for the calculator
     * @param op the operator to set
     */
    public void setOperator(String op){
        curOperator = op;
        first = false;
        opPointer = 1;
    }
    
    /**
     * get the square root of the current operand
     */
    public void sqrt(){
        if(operands[opPointer] == -1)
            operands[2] = -1;
        else
            if(first){
                operands[2] = Math.sqrt(operands[0]);
                operands[0] = operands[2];
            } else {
                operands[2] = Math.sqrt(operands[1]);
                operands[1] = operands[2];
            }
            
        
    }
    
    /**
     * Sets the current operand to a percentage of the previous
     */
    public void percent(){
        if(opPointer == 0){
            operands[0] = 0;
        } else if(operands[1] == 0.0) {
            operands[1] = operands[0] * operands[0] / 100;
        } else {
            operands[1] = operands[0] * operands[1] / 100;
        }
    }
    
    /**
     * Sets the current operand to it's reciprocal
     */
    public void reciprocal(){
        int point = (first)? 0 : 1;
        operands[point] = (operands[point] == 0.0)? 0.0 : 1.0 / operands[point];
    }
    
    
    
    /**
     * Set the calculator's memory
     */
    public void memoryStore(){
        memory = operands[2];
    }
    
    /**
     * Clear the calculator memory
     */
    public void memoryClear(){
        memory = 0.0;
    }
    
    /**
     * Add the current operand to the memory value
     */
    public void memoryPlus(){
        memory += operands[2];
    }
    
    /**
     * Returns an array of the current operands
     * @return the operands in the calculator
     */
    public double[] getOperands(){
        if(first){
            double[] ret = {operands[0], 0.0};
            return ret; 
        }
        return operands;
    }
    
    /**
     * Get the calculator's memory
     * @return the current number in memory
     */
    public double memoryRecall(){
        return memory;
    }
    
    /**
     * Works out the current calculation and places the answer in the the first 
     * operand, sets the second operand to 0 and places focus on the second 
     * operand
     * @return the current answer.
     */
    public void equals(){
        answer = calculate();
        operands[0] = answer;
        operands[2] = answer;
        opPointer = 1;
        first = true;
    }
    
    /**
     * Gets the current answer from the calculator
     * @return the current answer
     */
    public double getAnswer(){
        return answer;
    }
    
    /**
     * Gets the current calculation held in the calculator
     * @param grouping group numbers or not
     * @return the current calculation, removing trailing 0s for whole numbers
     */
    public String toString(boolean grouping){
        if(first){
            return noZero(operands[0] , grouping); 
        }
        return noZero(operands[0] , grouping) + " " + curOperator + " " + noZero(operands[1] , grouping);
    }
    
    /**
     * Helper method to strip of zeros for whole numbers
     * @param num the number to be formatted
     * @param grouping group numbers or not
     * @return the zero stripped number
     */
    private String noZero(Double num, boolean grouping){
        DecimalFormat d = new DecimalFormat("0.#########");
        d.setGroupingUsed(grouping);
        d.setGroupingSize(3);
        String strNum;
        strNum = d.format(num);
        return strNum;
    }
}

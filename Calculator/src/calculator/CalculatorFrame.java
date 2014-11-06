/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
public class CalculatorFrame extends JFrame {
    private JTextField input;
    private String curValue = "";
    private String[] basicOperatorSymbols = {"/", "sqrt", "*", "%", "-", "1/x", "+", "="};
    private String[] operands = {"", ""};
    private int opPointer = 0;
    private String curOperator;
    private Double answer = 0.0;
    public CalculatorFrame(){
        super("Calculator");
        add(buildLayout());
        setVisible(true);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        
    }
    private void buildMenu(){
        
    }
    private JPanel buildTop(){
        JPanel pnlTop = new JPanel(new BorderLayout());
             input = new JTextField();
             input.setPreferredSize(new Dimension(50,50));
             input.setHorizontalAlignment(JTextField.RIGHT);
             pnlTop.add(input, BorderLayout.NORTH);
        return pnlTop;
    }
    private JPanel buildMemoryButtons(){
        JPanel pnlMemoryButtons = new JPanel(new GridLayout(4, 1));
            String[] memLabels = {"MC", "MR", "MS", "M+"};
            JButton[] memButtons = new JButton[memLabels.length];
            for(int i = 0; i < memLabels.length; i++){
                memButtons[i] = new JButton(memLabels[i]);
                memButtons[i].setPreferredSize(new Dimension(50, 50));
                memButtons[i].setForeground(Color.BLUE);
                memButtons[i].setFont(new Font("Dialog", Font.BOLD, 12));
                pnlMemoryButtons.add(memButtons[i]);
            }
        return pnlMemoryButtons;
    }
    private JPanel buildNumberPad(){
        JPanel pnlNumberPad = new JPanel(new BorderLayout());
            JPanel pnlNumberButtons = new JPanel(new GridLayout(4,3));
            JButton[] numberButtons = new JButton[12];
            for(int i = 12; i > 0; i--){
                String btnLabel = Integer.toString(i - 3);
                if(i < 3){
                    btnLabel = (i == 2)? "+/-"  : ".";
                }
                numberButtons[i-1] = new JButton(btnLabel);
                numberButtons[i-1].setPreferredSize(new Dimension(50, 50));
                numberButtons[i-1].setForeground(Color.BLUE);
                numberButtons[i-1].setFont(new Font("Dialog", Font.BOLD, 12));
                numberButtons[i-1].addActionListener(new numButtonListener());
                pnlNumberButtons.add(numberButtons[i-1]);
            }
            pnlNumberPad.add(pnlNumberButtons, BorderLayout.WEST);
            JPanel operators = new JPanel(new GridLayout(4, 2));
                JButton[] basicOperators = new JButton[basicOperatorSymbols.length];
                for(int i = 0; i < basicOperatorSymbols.length; i++){
                    basicOperators[i] = new JButton(basicOperatorSymbols[i]);
                    basicOperators[i].setPreferredSize(new Dimension(50, 50));
                    Color foreGround = (i == 1 || i == 3 || i == 5)? Color.blue: Color.red;
                    basicOperators[i].setForeground(foreGround);
                    basicOperators[i].setFont(new Font("Dialog", Font.BOLD, 12));
                    basicOperators[i].addActionListener(new operatorListener());
                    operators.add(basicOperators[i]);
                }
            pnlNumberPad.add(operators, BorderLayout.EAST);
                
        return pnlNumberPad;
    }
    private JPanel buildLayout(){
        JPanel pnlLayout = new JPanel(new BorderLayout());
        pnlLayout.add(buildTop(), BorderLayout.NORTH);
        pnlLayout.add(buildMemoryButtons(), BorderLayout.WEST);
        pnlLayout.add(buildNumberPad(), BorderLayout.EAST);
        return pnlLayout;
    }
    
    private double calculate(String op, String num1, String num2){
        switch(op){
            case "+":
                return Double.parseDouble(num1) + Double.parseDouble(num2);
            case "-":
                return Double.parseDouble(num1) - Double.parseDouble(num2);
            case "*":
                return Double.parseDouble(num1) * Double.parseDouble(num2);
            case "/":
                return Double.parseDouble(num1) / Double.parseDouble(num2);
        }
        return 0.0;               
        
    }
    private class numButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            JButton pressed = (JButton) e.getSource();
            if(pressed.getText() == "+/-")
                operands[opPointer] = (operands[opPointer].charAt(0) == '-')? operands[opPointer].substring(1) : "-" + operands[opPointer];
            else if(pressed.getText() == ".")
                operands[opPointer] = (operands[opPointer].contains("."))? operands[opPointer] : operands[opPointer] + "." ;
            else
                operands[opPointer] += pressed.getText();
            
            curValue = operands[opPointer];
            input.setText(curValue);
        }
    }
    
    private class operatorListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            JButton pressed = (JButton) e.getSource();
            // Sorted list of operators
            String[] coreOperators = {"*", "+", "-", "/"};
            String operator = pressed.getText();
            curValue = input.getText();
            if(Arrays.binarySearch(coreOperators, operator) > -1){
                if(opPointer == 0){
                    operands[0] = input.getText();
                    curValue = curValue + operator;
                    input.setText(curValue);
                    opPointer = 1;
                    answer = Double.parseDouble(operands[0]);
                }else{
                    String[] numbers = input.getText().split("\\"+operator);
                    answer = calculate(curOperator, operands[0], operands[1]);
                    String strAnswer = Double.toString(answer);
                    operands[0] = strAnswer;
                    operands[1] = "";
                    curValue = strAnswer + operator;
                    input.setText(curValue + curOperator);
                    opPointer = 1;
                    answer = Double.parseDouble(strAnswer);
                }
                curOperator = pressed.getText();
                
            }else if(operator.equals("=")){
                if(operands[1] != ""){
                    System.out.println(operands[0] + " " + curOperator + " " + operands[1] );
                    answer = calculate(curOperator, operands[0], operands[1]);
                    String strAnswer = Double.toString(answer);
                   
                    operands[0] = strAnswer;
                    
                    curValue = strAnswer;
                    input.setText(curValue);
                    opPointer = 1;
                    answer = Double.parseDouble(strAnswer);
                }else{
                    input.setText(curValue);
                    answer = Double.parseDouble(curValue);
                }
            }else if(operator.equals("sqrt")){
                answer = Math.sqrt(Double.parseDouble(input.getText()));
                curValue = Double.toString(answer);
                operands[0] = curValue;
                operands[1] = "";
                input.setText(curValue);
            }else if(operator.equals("%")){
                operands[opPointer] = Double.toString(Double.parseDouble(operands[0]) * Double.parseDouble(operands[opPointer]) / 100.0);
                curValue = operands[opPointer];
                
                input.setText(curValue);
            }
            
            
            
        }
    }
}

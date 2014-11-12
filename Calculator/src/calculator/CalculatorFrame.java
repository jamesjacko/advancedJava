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
import javax.swing.border.*;
public class CalculatorFrame extends JFrame {
    private JTextField input;
    private String curValue = "";
    private String[] basicOperatorSymbols = {"/", "sqrt", "*", "%", "-", "1/x", "+", "="};
    private String[] operands = {"", ""};
    private int opPointer = 0;
    private String curOperator;
    private Double answer = 0.0;
    private Boolean operation = false;
    public CalculatorFrame(){
        super("Calculator");
        add(buildLayout());
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        
    }

    private void buildMenu(){
        
    }
    private JPanel buildTop(){
        JPanel pnlTop = new JPanel(new BorderLayout());
            input = new JTextField("0");
            input.setPreferredSize(new Dimension(50,50));
            input.setHorizontalAlignment(JTextField.RIGHT);
            input.setEditable(false);
            pnlTop.add(input, BorderLayout.NORTH);
            JPanel ctrlPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 0,0));
                String[] ctrlBtnLabels = {"Backspace", "CE", "C"};
                JButton[] ctrlButtons = new JButton[ctrlBtnLabels.length];
                for(int i = 0; i < ctrlBtnLabels.length; i++){
                   ctrlButtons[i] = new JButton(ctrlBtnLabels[i]);
                   ctrlButtons[i].setPreferredSize(new Dimension(83, 50));
                   ctrlButtons[i].setForeground(Color.BLUE);
                   ctrlButtons[i].setFont(new Font("Dialog", Font.BOLD, 12));
                   ctrlButtons[i].addActionListener(new MemButtonListener(this));
                   ctrlPanel.add(ctrlButtons[i]);
                }
            pnlTop.add(ctrlPanel, BorderLayout.SOUTH);
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
            for(int h = 10; h > 0; h = h-3){
                for(int i = 0; i < 3; i++){
                    
                    String btnLabel = Integer.toString(i+h - 3);
                    if(i+h == 1)
                        btnLabel = "0";
                    else if(i+h < 4)
                        btnLabel = (i+h == 2)? "+/-"  : ".";
                    numberButtons[i+h-1] = new JButton(btnLabel);
                    numberButtons[i+h-1].setPreferredSize(new Dimension(50, 50));
                    numberButtons[i+h-1].setForeground(Color.BLUE);
                    numberButtons[i+h-1].setFont(new Font("Dialog", Font.BOLD, 12));
                    numberButtons[i+h-1].addActionListener(new numButtonListener());
                    pnlNumberButtons.add(numberButtons[i+h-1]);
                }
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
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        pnlLayout.setBorder(padding);
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
    private void setTextValue(){
        if(opPointer == 0){
            String operator = (operation) ? curOperator : "";
            curValue = operands[0].equals("")? "0" + operator: operands[0] + operator;
        } else {
            String secOperand = operands[1].equals("")? "0": operands[1];
            curValue = operands[0] + curOperator + secOperand;
        }
        input.setText(curValue);
    }
    public void backspace(){
        if(operands[opPointer].length() > 0){
            operands[opPointer] = operands[opPointer].substring(0, operands[opPointer].length() - 1);
            setTextValue();
        }
    }
    public void clearEntry(){
        if(opPointer == 1 && !operands[1].equals("")){
            operands[1] = "";
            operation = true;
        } else if (operation){
            opPointer = 0;
            operands[1] = "";
            operation = false;
        }else{
            operands[0] = "";
        }
        setTextValue();
        
    }
    public void reset(){
       this.curValue = "";
       this.opPointer = 0;
       this.operands[0] = "";
       this.operands[1] = "";
       this.input.setText("0");
    }
    
    public void operatorPress(String operator){
        operation = true;
        // Sorted array of operators, needed for binary search
        String[] coreOperators = {"*", "+", "-", "/"};
        curValue = input.getText();
        if(Arrays.binarySearch(coreOperators, operator) > -1){
            if(opPointer == 0){
                operands[0] = input.getText();
                curValue = curValue + operator;
                input.setText(curValue);
                opPointer = 1;
                answer = Double.parseDouble(operands[0]);
            }else{
                equals(operator);
            }
            curOperator = operator;

        }else if(operator.equals("=")){
            if(operands[1] != ""){
                equals("");
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
    
    private void equals(String operator){
        answer = calculate(curOperator, operands[0], operands[1]);
        String strAnswer;
        if ((answer == Math.floor(answer)) && !Double.isInfinite(answer)) {
            strAnswer = Integer.toString((int)Math.round(answer));
        }else{
            strAnswer = Double.toString(answer);
        }
        operands[0] = strAnswer;
        operands[1] = "";
        curValue = strAnswer + operator;
        input.setText(curValue);
        opPointer = 1;
        answer = Double.parseDouble(strAnswer);
    }
    
    private class numButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            operation = false;
            JButton pressed = (JButton) e.getSource();
            String comp = pressed.getText();
           
            if(comp.equalsIgnoreCase("+/-"))
                operands[opPointer] = (operands[opPointer].charAt(0) == '-')? operands[opPointer].substring(1) : "-" + operands[opPointer];
            else if(comp.equalsIgnoreCase("."))
                operands[opPointer] = (operands[opPointer].contains("."))? operands[opPointer] : operands[opPointer] + "." ;
            else
                operands[opPointer] += pressed.getText();
            setTextValue();
        }
    }
    
    private class operatorListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            
            JButton pressed = (JButton) e.getSource();            
            String operator = pressed.getText();
            operatorPress(operator);
            
            
            
        }
        
        
    }
    
    
}

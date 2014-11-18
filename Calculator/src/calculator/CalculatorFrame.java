/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.Arrays;
import javax.swing.border.*;
public class CalculatorFrame extends JFrame {
    private JTextField input;
    private String curValue = "";
    private String[] basicOperatorSymbols = {"/", "sqrt", "*", "%", "-", "1/x", "+", "="};
    private String[] operands = {"", ""};
    private int opPointer = 0;
    private String curOperator;
    private double answer = 0.0;
    private boolean operation = false, grouping = false;
    private Calculator calc = new Calculator();
    public CalculatorFrame(){
        super("Calculator");
        add(buildLayout());
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setJMenuBar(new CalculatorMenu(this));
        pack();
        
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
                memButtons[i].addActionListener(new MemButtonListener(this));
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
                    numberButtons[i+h-1].addActionListener(new numButtonListener(this));
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
    
    
    public void setTextValue(){
        //calc.setOperand(operands[opPointer]);
        if(opPointer == 0){
            String operator = (operation && curOperator != null) ? curOperator : "";
            curValue = operands[0].equals("")? "0" + operator: noZero(Double.parseDouble(operands[0].replace(",", ""))) + operator;
        } else {
            String secOperand = operands[1].equals("")? "0": noZero(Double.parseDouble(operands[1].replace(",", "")));
            curValue = noZero(Double.parseDouble(operands[0].replace(",", ""))) + curOperator + secOperand;
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
       this.curValue = this.curOperator = this.operands[0] = this.operands[1] = "";
       this.opPointer = 0;
       this.input.setText("0");
       calc.reset();
    }
    
    public void memoryClear(){
        calc.memoryClear();
    }
    
    public void memoryRecall(){
        double mem = calc.memoryRecall();
        calc.setOperand(mem);
        operands[opPointer] = noZero(mem);
        setTextValue();
    }
    
    public void memoryStore(){
        if(operands[opPointer] == "")
            calc.setOperand(Double.parseDouble(operands[0]));
        else
            calc.setOperand(Double.parseDouble(operands[opPointer]));
        calc.memoryStore();
        System.out.println("here: " + calc.memoryRecall());
    }
    
    public void memoryPlus(){
        if(operands[opPointer] == "")
            calc.setOperand(Double.parseDouble(operands[0]));
        else
            calc.setOperand(Double.parseDouble(operands[opPointer]));
        calc.memoryPlus();
    }
    
    public void operatorPress(String operator){
        
        operation = true;
        // Sorted array of operators, needed for binary search
        String[] coreOperators = {"*", "+", "-", "/"};
        curValue = input.getText().replace(",", "");
        
        if(Arrays.binarySearch(coreOperators, operator) > -1){
            if(opPointer == 0){
                operands[0] = input.getText().replace(",", "");
                curValue = curValue + operator;
                input.setText(curValue);
                calc.setOperand(Double.parseDouble(operands[opPointer]));
                calc.setOperator(operator);
                opPointer = 1;
            }else{
                calc.setOperator(curOperator);
                if(!operands[1].equals("")){
                    calc.setOperand(Double.parseDouble(operands[opPointer]));
                    equals(operator);
                    setTextValue();
                } else {
                    curValue += operator;
                    setTextValue();
                }
            }
            curOperator = operator;

        }else if(operator.equals("=")){
            calc.setOperand(Double.parseDouble(operands[opPointer]));
            equals("");
        }else if(operator.equals("sqrt")){
            
            calc.setOperand(Double.parseDouble(operands[opPointer]));
            calc.sqrt();
            equals("");
        }else if(operator.equals("%")){
            calc.setOperand(Double.parseDouble(operands[opPointer]));
            calc.percent();
            equals("");
        }else if(operator.equals("1/x")){
            operands[opPointer] = Double.toString(1.0 / Double.parseDouble(operands[opPointer]));
            setTextValue();
        }
    }
    
    private void equals(String operator){
        String strAnswer;
        calc.equals();
        calc.toString(true);
        answer = calc.getAnswer();
        System.out.println("" + answer);
        strAnswer = noZero(answer);
        operands[0] = strAnswer;
        operands[1] = "";
        curValue = strAnswer + operator;
        input.setText(curValue);
        opPointer = 1;
    }
    
    public void negate(){
        if(opPointer == 1 && operands[1] == "")
            operands[0] = (operands[0].charAt(0) == '-')? operands[0].substring(1) : "-" + operands[0];
        else
           operands[opPointer] = (operands[opPointer].charAt(0) == '-')? operands[opPointer].substring(1) : "-" + operands[opPointer];
    }
    
    public void addDecimal(){
        operands[opPointer] = (operands[opPointer].contains("."))? operands[opPointer] : operands[opPointer] + "." ;
    }
    
    public void addToOperand(String num){
        operands[opPointer] += num;
        operands[opPointer] = operands[opPointer];
        setTextValue();
    }
    
    public void setGrouping(){
        grouping = !grouping;
        setTextValue();
    }
    
    /**
     * Helper method to strip of zeros for whole numbers
     * @param num the number to be formatted
     * @return the zero stripped number
     */
    private String noZero(Double num){
        DecimalFormat d = new DecimalFormat("0.#########");
        d.setGroupingUsed(this.grouping);
        d.setGroupingSize(3);
        String strNum;
        strNum = d.format(num);
        return strNum;
    }
    
    private class operatorListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            JButton pressed = (JButton) e.getSource();            
            String operator = pressed.getText();
            operatorPress(operator);
        }
        
        
    }
    
    
}

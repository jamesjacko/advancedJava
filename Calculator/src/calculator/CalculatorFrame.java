
package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.Arrays;
import javax.swing.border.*;


/**
 *
 * @author James Jackson
 * Calculator Frame class which extend JFrame, uses the calculator class and offers
 * a GUI for it
 * @date 20/11/2014
 */
public class CalculatorFrame extends JFrame {
    private JTextField input;
    private String curValue = "";
    private String[] basicOperatorSymbols = {"/", "sqrt", "*", "%", "-", "1/x", "+", "="};
    private String[] operands = {"", ""};
    private String[] copiedState = {"", "", "", "", ""};
    private int opPointer = 0;
    private String curOperator;
    private double answer = 0.0;
    private boolean operation = false, grouping = false, first = false;
    private Calculator calc = new Calculator();
    
    /**
     * Frame constructor builds the layout and sets up the frame
     */
    public CalculatorFrame(){
        super("Calculator");
        add(buildLayout());
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setJMenuBar(new CalculatorMenu(this));
        pack();
        
    }

    /**
     * Builds the top panel of the calculator including text field and top
     * control buttons
     * @return the top panel (border layout)
     */
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
    
    /**
     * Builds the memory button panel
     * @return the memory button panel (grid layout)
     */
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
    
    /**
     * Build the number pad panel including the decimal and negative
     * @return the number pad panel (grid layout)
     */
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
    
    /**
     * Using the other helper methods build the overall calculator layout
     * @return the calculator layout
     */
    private JPanel buildLayout(){
        JPanel pnlLayout = new JPanel(new BorderLayout());
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        pnlLayout.setBorder(padding);
        pnlLayout.add(buildTop(), BorderLayout.NORTH);
        pnlLayout.add(buildMemoryButtons(), BorderLayout.WEST);
        pnlLayout.add(buildNumberPad(), BorderLayout.EAST);
        return pnlLayout;
    }
    
    /**
     * Updates the text field value to that of the calculator's current state.
     */
    public void setTextValue(){
        //calc.setOperand(operands[opPointer]);
        if(opPointer == 0){
            String operator = (operation && curOperator != null) ? curOperator : "";
            curValue = operands[0].equals("")? "0" + operator: noZero(Double.parseDouble(operands[0].replace(",", ""))) + operator;
        } else {
            String secOperand = "";
            String op = "";
            if(!first){
                secOperand = operands[1].equals("")? "0": noZero(Double.parseDouble(operands[1].replace(",", "")));
                op = curOperator;
            }
            curValue = noZero(Double.parseDouble(operands[0].replace(",", ""))) + op + secOperand;
        }
        input.setText(curValue);
    }
    
    /**
     * Processes a backspace on the current operand
     */
    public void backspace(){
        if(operands[opPointer].length() > 0){
            operands[opPointer] = operands[opPointer].substring(0, operands[opPointer].length() - 1);
            setTextValue();
        }
    }
    
    /**
     * clears the last entry into the calculator
     */
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
    
    /**
     * resets the calculator, does not clear memory of clipboard
     */
    public void reset(){
       this.curValue = this.curOperator = this.operands[0] = this.operands[1] = "";
       this.opPointer = 0;
       this.input.setText("0");
       calc.reset();
    }
    
    /**
     * clears the calculators memory
     */
    public void memoryClear(){
        calc.memoryClear();
    }
    
    /**
     * Recalls the calculator memory into the current operand
     */
    public void memoryRecall(){
        double mem = calc.memoryRecall();
        calc.setOperand(mem);
        operands[opPointer] = noZero(mem);
        setTextValue();
    }
    
    /**
     * Stores the current value of the operand into memory, overwriting
     */
    public void memoryStore(){
        calc.setOperand(Double.parseDouble(getOperand()));
        calc.memoryStore();
        System.out.println("here: " + calc.memoryRecall());
    }
    
    /**
     * Adds the current operand value to the memory value
     */
    public void memoryPlus(){
        calc.setOperand(Double.parseDouble(getOperand()));
        calc.memoryPlus();
    }
    
    /**
     * process an operator button being pressed
     * @param operator the operator that was pressed
     */
    public void operatorPress(String operator){
        operation = true;
        // Sorted array of operators, needed for binary search
        String[] coreOperators = {"*", "+", "-", "/"};
        curValue = input.getText().replace(",", "");
        
        //if this is a core operator
        if(Arrays.binarySearch(coreOperators, operator) > -1){
            coreOps(operator);
        // any other operator
        } else {
            otherOps(operator);   
        }
    }
    
    /**
     * Process for the core operators ( + , - , / , *)
     * NB: Division by 0 give 0 as output
     * @param operator the operator to be processed
     */
    private void coreOps(String operator){
        // first operand is enterered (only run once at the begining)
        if(opPointer == 0){
            calc.setOperand(Double.parseDouble(getOperand()));
            calc.setOperator(operator);
            curOperator = operator;
            setTextValue();
            opPointer = 1;
        // if we are on the second operand but not just after equals has processed
        }else if(!first){
            if(operands[1] != ""){
                calc.setOperand(Double.parseDouble(getOperand()));
                equals(curOperator);
            }
            curOperator = operator;
            setTextValue();
        // equals has just been pressed so we need to work slightly diferently
        } else {
            calc.setOperator(operator);
            curOperator = operator;
            first = false;
            setTextValue();
        }
        first = false;
    }
    
    /**
     * Processes for all other operators (sqrt , % , 1/x , =)
     * NB Reciprocal 0 give 0 as output
     * @param operator the operator to be processed
     */
    private void otherOps(String operator){
        double ops[];
        calc.setOperand(Double.parseDouble(getOperand()));
        switch(operator){
            case "sqrt":
                if(Double.parseDouble(getOperand()) > 0)
                    calc.sqrt();
                else
                    JOptionPane.showMessageDialog(null, "Sorry you can't square root a negative number");
                break;
            case "%":
                calc.percent();
                break;
            case "1/x":
                calc.reciprocal();
                System.out.println(calc.toString(false));
                break;
            case "=":
                equals("");
                break;
        }
        if(!operator.equals("=")){
            ops = calc.getOperands();
            operands[0] = noZero(ops[0]);
            operands[1] = noZero(ops[1]);
            setTextValue();
        }
    }
    
    /**
     * Equals method to work out the current calculation
     * @param operator the operator to be written, this keeps the old operator
     * when an operator invokes the method.
     */
    private void equals(String operator){
        String strAnswer;
        calc.equals();
        answer = calc.getAnswer();
        System.out.println("" + answer);
        strAnswer = noZero(answer);
        operands[0] = strAnswer;
        operands[1] = "";
        // equals is called on operator press as well as = button so we must
        // ensure that the operator is the old one not the new one pressed.
        curValue = strAnswer + operator;
        input.setText(curValue);
        opPointer = 1;
        // first var to show that a new operator press will need a second operand
        first = true;
    }
    
    /**
     * Negates the value of the operand
     */
    public void negate(){
        if(opPointer == 1 && operands[1] == "")
            operands[0] = (operands[0].charAt(0) == '-')? operands[0].substring(1) : "-" + operands[0];
        else
           operands[opPointer] = (operands[opPointer].charAt(0) == '-')? operands[opPointer].substring(1) : "-" + operands[opPointer];
    }
    
    /**
     * Adds a decimal point tot the current operand
     */
    public void addDecimal(){
        operands[opPointer] = (operands[opPointer].contains("."))? operands[opPointer] : operands[opPointer] + "." ;
    }
    
    /**
     * Adds a new character (number) to the current operand
     * @param num the String representation of an integer to be added.
     */
    public void addToOperand(String num){
        operands[opPointer] += num;
        operands[opPointer] = operands[opPointer];
        setTextValue();
    }
    
    /**
     * Set the grouping for display to on or off
     * @param group whether to group or not
     */
    public void setGrouping(boolean group){
        grouping = group;
        setTextValue();
    }
    
    /**
     * Copies the current calculator state to the clipboard
     */
    public void copy(){
        copiedState[0] = operands[0];
        copiedState[1] = operands[1];
        copiedState[2] = curOperator;
        copiedState[3] = "" + opPointer;
        copiedState[4] = Boolean.toString(operation);
    }
    
    /**
     * pastes the copied state into the calculator
     */
    public void paste(){
        calc.reset();
        operands[0] = copiedState[0];
        calc.setOperand(Double.parseDouble(operands[0]));
        if(!copiedState[2].equals("")){
            curOperator = copiedState[2];
            calc.setOperator(curOperator);
        }
        if(!copiedState[2].equals("")){
            operands[1] = copiedState[1];
            calc.setOperand(Double.parseDouble(operands[1]));
        }
        opPointer = Integer.parseInt(copiedState[3]);
        operation = Boolean.parseBoolean(copiedState[4]);
        setTextValue();
        System.out.println(copiedState[0] + " " + copiedState[1] + " " + copiedState[2] + " " + copiedState[3] + " " + copiedState[4]);
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
    
    /**
     * operator press inner class
     */
    private class operatorListener implements ActionListener{
        /**
         * Method to run when a button is clicked
         * @param e the event that triggered the method
         */
        public void actionPerformed(ActionEvent e){
            JButton pressed = (JButton) e.getSource();            
            String operator = pressed.getText();
            operatorPress(operator);
        }
        
        
    }
    
    /**
     * Gets the sanitized current operand
     * @return 
     */
    private String getOperand(){
        
        if(opPointer == 1 && operands[1] == "")
           return operands[0].replace(",", "");

        return (operands[opPointer] == "") ? "0" : operands[opPointer].replace(",", "");
    
    }
    
    
}

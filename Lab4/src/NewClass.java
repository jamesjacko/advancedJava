

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class NewClass {

    public static final String[] otherButtons = {
        "+/-", ".", "Backspace", "CE", "C",
        "MC", "MR", "MS", "M+", "/", "sqrt",
        "*", "%", "-", "1/x", "+", "="
    };

    private static JPanel topButtons = new JPanel(); // top 3 buttons panel.
    private static JPanel bigDog = new JPanel();
    private static JButton[] allButtons = new JButton[27];
    private static JPanel buttonsPanel = new JPanel();
    private static JPanel memButtonsPanel = new JPanel();
    private static JPanel operatorsButtonsPanel = new JPanel();
    private static JTextField outputScreen = new JTextField("0");
    private static String operator = "";
    private static String operandStringValue = "";
    private static int whichOperand = 0;
    private static double valueToAdd = 0.0;
    private static int getValueToAdd = 0;
    private static double[] leftRightOps = {0, 0};
    private static boolean pressedEquals = false;

    public static void main(String[] args) {

        // setting the frame attribute;
        JFrame frame = new JFrame();
        frame.setTitle("Calculator"); // title of frame.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // RED 'x' stops program.
       // Menu topMenu = new Menu(frame); // set menu at top of the frame.
        outputScreenConfigure(outputScreen); // set attributes of the text field.

        //Creating all of the buttons
        for (int i = 0; i <= 9; i++) {
            allButtons[i] = new JButton(String.valueOf(i)); // 0-9 formed as buttons.
            allButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton buttonClicked = (JButton) e.getSource(); // get value of the button
                    newNumberAdded(Integer.parseInt(buttonClicked.getText())); // convert string to int and use value as param.
                }
            });
        }
        int otherButtonsArrayIndex = 0;
        for (int i = 10; i < 27; i++) {
            allButtons[i] = new JButton(otherButtons[otherButtonsArrayIndex]); // others formed as buttons.
            allButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    operationButtonPressed(e);
                }
            });
            otherButtonsArrayIndex++;
        }

        // Adding all buttons to appropriate panels.
        buttonsPanel.setLayout(new GridLayout(4, 3, 5, 5));
        buttonsPanel.setPreferredSize(new Dimension(150, 150));
        for (int i = 9; i >= 0; i--) {
            buttonsPanel.add(allButtons[i]); // 0-9, 
            if (i == 0) {
                buttonsPanel.add(allButtons[10]); //+/-
                buttonsPanel.add(allButtons[11]); //.
            }
        }
        topButtons.setLayout(new GridLayout(1, 3, 5, 5));
        topButtons.setPreferredSize(new Dimension(400, 30));
        for (int i = 12; i < 15; i++) {
            topButtons.add(allButtons[i]); // Backspace, CE and C
        }
        memButtonsPanel.setLayout(new GridLayout(4, 1, 5, 5));
        memButtonsPanel.setPreferredSize(new Dimension(70, 150));
        for (int i = 15; i < 19; i++) {
            memButtonsPanel.add(allButtons[i]); // MC, MR, MS, M+   
        }
        operatorsButtonsPanel.setLayout(new GridLayout(4, 2, 5, 5));
        operatorsButtonsPanel.setPreferredSize(new Dimension(150, 150));
        for (int i = 19; i < 27; i++) {
            operatorsButtonsPanel.add(allButtons[i]); // /, sqrt, *, %, -, 1/x
        }

        // colouring of buttons.
        for (int i = 0; i < allButtons.length; i++) {
            allButtons[i].setForeground(Color.blue);
            if ((i > 11 && i <= 19) || (i == 21) || (i == 23) || (i == 25) || (i == 26)) {
                allButtons[i].setForeground(Color.red);
            }
        }

        // adding stuff to main container.
        bigDog.setLayout(new BorderLayout());
        bigDog.add(topButtons, BorderLayout.NORTH);
        bigDog.add(operatorsButtonsPanel, BorderLayout.EAST);
        bigDog.add(memButtonsPanel, BorderLayout.WEST);
        bigDog.add(buttonsPanel, BorderLayout.CENTER);

        // Add components to frame
        frame.add(outputScreen, BorderLayout.NORTH);
        frame.add(bigDog, BorderLayout.SOUTH);

        // Make frame not resizeable, visible and of an appropriate size for the content.
        frame.setResizable(false); // fixed size.
        frame.setVisible(true); //  true means that the frame is visible to te eye.
        frame.pack(); // make appropriate size frame for components.

    }

    public static void newNumberAdded(int value) {
        String textFieldValue = outputScreen.getText(); // get current value on screen.

        if (textFieldValue.indexOf("0") == 0) { // if first element has current value of 0
            textFieldValue = textFieldValue.substring(1);
        }
        if ((!textFieldValue.equals("0")) || (value > 0)) { // if already changed the text field beforehand or about to change the value to something more than 0.
            outputScreen.setText(textFieldValue + value); // add value to output screen.
        }
        operandStringValue += value;
    }

    public static void operationButtonPressed(ActionEvent e) {
        for (int i = 10; i < allButtons.length; i++) {
            if (e.getSource() == allButtons[i]) {
                switch (i) {
                    case 10: // +/-
                        // function call here!
                        break;
                    case 11: // . // fix!
                        if(!outputScreen.getText().contains(".")){ // if there isn't a decimal place on screen. (initial values)
                            String newLeftOpValue = outputScreen.getText() + ".";
                            leftRightOps[0] = Double.parseDouble(newLeftOpValue); // put value of textField in left operand.
                            System.out.println(newLeftOpValue);
//                            outputScreen.setText(Double.toString(leftRightOps[0]));
                        }
                        break;
                    case 12:
                        // function call here!
                        break;
                    case 13:
                        // function call here!
                        break;
                    case 14: // C
                        clearValues();
                        break;
                    case 15:
                        // function call here!
                        break;
                    case 16:
                        // function call here!
                        break;
                    case 17:
                        // function call here!
                        break;
                    case 18:
                        // function call here!
                        break;
                    case 19:
                        getValueToAdd = 0;
                        operator = "/";
                        processCalculation(operator);
                        break;
                    case 20:
                        // function call here!
                        break;
                    case 21:
                        getValueToAdd = 0;
                        operator = "*";
                        processCalculation(operator);
                        break;
                    case 22:
                        // function call here!
                        break;
                    case 23:
                        getValueToAdd = 0;
                        operator = "-";
                        processCalculation(operator);
                        break;
                    case 24:
                        // function call here!
                        break;
                    case 25:
                        getValueToAdd = 0;
                        operator = "+";
                        processCalculation(operator);
                        break;
                    case 26:
                        pressedEquals = true;
                        equals();
                        break;
                }
            }
        }
    }

    static void processCalculation(String operation) {

        if (!operandStringValue.equals("")) {
            leftRightOps[whichOperand] = Double.parseDouble(operandStringValue); // initially store in left operand.
            outputScreen.setText(leftRightOps[whichOperand] + operation);
            operandStringValue = "";
            whichOperand = 1; // after this, store values in right operand from now on until end of program or pressed C.

        }
        if (leftRightOps[1] == 0.0) {
            outputScreen.setText(leftRightOps[0] + operation);
        }

        if (leftRightOps[0] != 0.0 && leftRightOps[1] != 0.0 && pressedEquals != true) {
            if ("+".equals(operation)) {
                leftRightOps[0] = leftRightOps[0] + leftRightOps[1]; // get result and store as new init val.
            }
            if ("-".equals(operation)) {
                leftRightOps[0] = leftRightOps[0] - leftRightOps[1]; // get result and store as new init val.
            }
            if ("*".equals(operation)) {
                leftRightOps[0] = leftRightOps[0] * leftRightOps[1]; // get result and store as new init val.
            }
            if ("/".equals(operation)) {
                leftRightOps[0] = leftRightOps[0] / leftRightOps[1]; // get result and store as new init val.
            }
            leftRightOps[1] = 0.0; // revert right operand back to 0 so it can be reconfigured.
            outputScreen.setText(leftRightOps[0] + operation);
        }
        if ("+".equals(operation) && pressedEquals == true) {
            leftRightOps[1] = 0.0;
            outputScreen.setText(leftRightOps[0] + operation);
            pressedEquals = false;
        }
        pressedEquals = false;
    }

    static void equals() {
        if (!operandStringValue.equals("")) {
            leftRightOps[whichOperand] = Double.parseDouble(operandStringValue); // stores second op val
            operandStringValue = "";
        }

        if (operator.equals("-")) {
            leftRightOps[0] = leftRightOps[0] - leftRightOps[1]; // gets result and stores in left operand.
            if (leftRightOps[1] == 0.0) { // doing the basic operation using the same value as the initial value set on pressing enter a lot.
                if (getValueToAdd == 0) {
                    valueToAdd = leftRightOps[0];
                    getValueToAdd = -1; // this makes it stop getting a new value from the total of the previous.
                }
                leftRightOps[0] -= valueToAdd;
            }
            outputScreen.setText(Double.toString(leftRightOps[0]));
        }

        if (operator.equals("+")) {
            leftRightOps[0] = leftRightOps[0] + leftRightOps[1]; // gets result and stores in left operand.

            if (leftRightOps[1] == 0.0) { // doing the basic operation using the same value as the initial value set on pressing enter a lot.
                if (getValueToAdd == 0) {
                    valueToAdd = leftRightOps[0];
                    getValueToAdd = -1; // this makes it stop getting a new value from the total of the previous.
                }
                leftRightOps[0] += valueToAdd;
            }
            outputScreen.setText(Double.toString(leftRightOps[0]));
        }

        if (operator.equals("/")) {
            if (leftRightOps[1] == 0.0) { // doing the basic operation using the same value as the initial value set on pressing enter a lot.
                if (getValueToAdd == 0) {
                    valueToAdd = leftRightOps[0];
                    getValueToAdd = -1; // this makes it stop getting a new value from the total of the previous.
                }
                leftRightOps[0] = leftRightOps[0] / valueToAdd;
                outputScreen.setText("" + leftRightOps[0]);
            } else {
                leftRightOps[0] = leftRightOps[0] / leftRightOps[1]; // gets result and stores in left operand.
            }
            outputScreen.setText(Double.toString(leftRightOps[0]));
        }

        if (operator.equals("*")) {
            if(leftRightOps[1] == 0.0){
               leftRightOps[1] = leftRightOps[0];
            }
            leftRightOps[0] = leftRightOps[0] * leftRightOps[1]; // gets result and stores in left operand.
            outputScreen.setText(Double.toString(leftRightOps[0]));
        }
    }

    static void clearValues() {
        outputScreen.setText("0");
        operandStringValue = "";
        leftRightOps[0] = 0;
        leftRightOps[1] = 0;
        whichOperand = 0;
        operator = "";
        pressedEquals = false;
    }

    static void outputScreenConfigure(JTextField outputScreen) {
        outputScreen.setHorizontalAlignment(SwingConstants.RIGHT);
        outputScreen.setEditable(false); // User cannot enter their own values using keyboard.
        outputScreen.setBackground(Color.WHITE);
    }
}
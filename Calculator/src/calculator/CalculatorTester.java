
package calculator;

/**
 *
 * @author James Jackson
 * Calculator Tester for testing the calculator logic
 * @date 20/11/2014
 */
public class CalculatorTester {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.setOperand(423423542.0);
        System.out.println("" + calc.toString(true));
        calc.setOperator("*");
        calc.setOperand(2.0);
        calc.memoryStore();
        System.out.println("" + calc.toString(true));
        calc.equals();
        System.out.println("" + calc.getAnswer());
        System.out.println("NOW: " + calc.toString(true));

        calc.memoryStore();
        System.out.println("" + calc.memoryRecall());
        
        
        calc.reset();
        
        calc.setOperand(100.0);
        calc.percent();
        System.out.println("" + calc.getAnswer());
        calc.setOperand(100.0);
        calc.setOperator("+");
        calc.setOperand(10.0);
        calc.percent();
        calc.equals();
        System.out.println("" + calc.getAnswer());
        
        calc.reset();
        calc.setOperand(21.0);
        calc.setOperator("+");
        calc.percent();
        calc.equals();
        System.out.println("Should be 8: " + calc.getAnswer());
        
        
        calc.reset();
        calc.setOperand(10.0);
        
        calc.setOperator("+");
        calc.setOperand(10.0);
        calc.equals();
        
        
        System.out.println(calc.toString(false));
        
        
        calc.equals();
        System.out.println(calc.toString(false));
        
        
        
    }
}

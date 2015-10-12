import java.util.ArrayList;
import java.util.Stack;

/*
 * Calculates an postfix expression
 * 
 * 
 * Input: postfix expression (ArrayList)
 * Output: value of function for a given x
 */

public class PostfixInterpreter {

	private ArrayList<String> expression;

	public PostfixInterpreter(ArrayList<String> in) {
		expression = new ArrayList<String>(in);
	}

	/*
	 * Calculates the value of function for a given argument (x) 
	 */
	public double calculatePostfix(double x) {
		Stack<Double> stack = new Stack<Double>();

		for (String token : expression) {
			// Replace variable with the value of x
			if (token.equals("x")) {
				stack.push(x);
			}

			// Perform operator
			else if (Operator.isOperator(token)) {
				double a = stack.pop();
				double b = stack.pop();

				stack.push(Operator.performOperator(token, b, a));
			}
			
			// Perform function
			else if(Function.isFunction(token)){
				stack.push(Function.performFuntion(token, stack.pop()));
			}
			
			// Add number to stack
			else {
				stack.push(Double.parseDouble(token));
			}

		}

		// Return the result
		return stack.pop();
	}
}

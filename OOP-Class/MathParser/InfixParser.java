import java.util.ArrayList;
import java.util.Stack;

/*
 * Infix to Postfix parser
 * 
 * Uses Dijkstra's Shunting-yard algorithm
 * 
 * Input: mathematical expression (String)
 * Output: the same expression in the postfix form (ArrayList)
 */

public class InfixParser {
	private String input;
	private ArrayList<String> output;

	public InfixParser(String expression) {
		input = new String(expression);
		output = new ArrayList<String>();
	}

	/*
	 * Convert to Postfix using Shunting-Yard algorithm
	 */
	public void convertInfixToPostfix() {
		// Create operator stack
		Stack<String> op_stack = new Stack<String>();

		// Prepare the input string
		prepareInput();
		
		// Split the expression 
		String[] tokens  = input.split("(?<=[^\\.a-zA-Z\\d])|(?=[^\\.a-zA-Z\\d])");
		
		//For each token
		for (String t : tokens) {
			// If the token is empty, skip it
			if(t.equals("") || t.equals(" ")) continue;
			
			// If operator (o1)
			if (Operator.isOperator(t)) {

				// While there is an operator (o2) an the stack and
				// (o1 is left associative AND o2 precedence is higher or equal to o1 precedence)
				// OR
				// (o1 is right associative AND o2 precedence is higher than o1 precedence)
				while (!op_stack.empty()
						&& Operator.isOperator(op_stack.peek())
						&& (
							((Operator.getAssociativity(t) == Operator.LEFT) && Operator.comparePrec(op_stack.peek(), t) >= 0) 
							|| 
							((Operator.getAssociativity(t) == Operator.RIGHT) && Operator.comparePrec(op_stack.peek(), t) > 0)
						   )
				      ) 
				{
					// add o2 to the output
					output.add(op_stack.pop());
				}
				
				// Push o1 on to the stack.
				op_stack.push(t);
			}

			else if (t.equals("(")) {
				op_stack.push(t);
			}

			else if (t.equals(")")) {
				// While there is not a left parenthesis, add the top token from the stack to the output
				while (!op_stack.peek().equals("(")) {
					output.add(op_stack.pop());
				}

				// Pop out the left parenthesis
				op_stack.pop();
				
				// While there is a function on the top of the stack, add it to the output
				if(!op_stack.empty() && Function.isFunction(op_stack.peek())) output.add(op_stack.pop());
			}
			else if(Function.isFunction(t)){
				op_stack.push(t);
			}
			else if(isNumeric(t) || isVariable(t)){
				output.add(t);
			}
		}

		// Finally empty the stack, by adding all remaining tokens to the output.
		while (!op_stack.empty()) {
			output.add(op_stack.pop());
		}
	}

	/*
	 * Handle unary minus operator
	 */
	private void prepareInput() {
		if(input.charAt(0) == '-') input = "0" + input;
		for(int i = 0; i < input.length() - 1; i++) {
			if(input.charAt(i) == '(' && input.charAt(i+1) == '-') input = input.substring(0, i+1) + "0" + input.substring(i+1); 
		}
	}
	
	private boolean isNumeric(String pattern){
		return pattern.matches("^[-+]?\\d+(\\.\\d+)?$");
	}
	
	private boolean isVariable(String pattern){
		return pattern.matches("x");
	}

	public ArrayList<String> getOutput() {
		return output;
	}
}

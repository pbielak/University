import java.util.HashMap;

/*
 * Class with operator handling stuff
 * 
 */


public class Operator {
	// Left, right associative constants
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	
	// Hashmap with all informations about an operator 
	private static HashMap<String, int[]> operators = new HashMap<String, int[]>();
	
	// Fill the map with operators
	static {
		operators.put("+", new int[]{LEFT, 2});
		operators.put("-", new int[]{LEFT, 2});
		operators.put("*", new int[]{LEFT, 3});
		operators.put("/", new int[]{LEFT, 3});
		operators.put("^", new int[]{RIGHT, 4});
	}

	/*
	 *  Get associativity
	 */
	public static int getAssociativity(String op){	
		return operators.get(op)[0];
	}
	
	/*
	 * Get precedence 
	 */ 
	public static int getPrecedence(String op){	
		return operators.get(op)[1];
	}
	
	/*
	 * Compare precedences of 2 operators
	 */
	public static int comparePrec(String op1, String op2){
		return operators.get(op1)[1] - operators.get(op2)[1];
	}
	
	/*
	 * Check is a given string is an operator
	 */ 
	public static boolean isOperator(String op){
		return operators.containsKey(op);
	}
	
	/*
	 * Perform the operator
	 */
	public static double performOperator(String op, double lval, double rval) {
		switch(op){
			case "+":
				return lval + rval;
			
			case "-":
				return lval - rval;
				
			case "*":
				return lval * rval;
				
			case "/":
				if(rval != 0) return lval / rval;
				else return lval>0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
				
			case "^":
				return Math.pow(lval, rval);
				
		}
		
		return 0;
	}
}

import java.util.ArrayList;

/*
 * Class with mathematical functions handling stuff
 * 
 */


public class Function {
	private static ArrayList<String> functions = new ArrayList<String>();
	
	// Fill the list with functions
	static{
		functions.add("sin");
		functions.add("cos");
		functions.add("tan");
		functions.add("exp");
		functions.add("log");
		functions.add("sqrt");
	}
	
	/*
	 * Check if the given string is a function
	 */
	public static boolean isFunction(String fun){
		return functions.contains(fun);
	}
	
	/*
	 * Perform the given function
	 */
	public static double performFuntion(String fun, double val){
		switch(fun){
			case "sin":
				return Math.sin(val);
				
			case "cos":
				return Math.cos(val);
			
			case "tan":
				return Math.tan(val);
			
			case "exp":
				return Math.exp(val);
			
			case "log":
				return Math.log(val);
			
			case "sqrt":
				return Math.sqrt(val);
		}
		
		return 0;
	}
}

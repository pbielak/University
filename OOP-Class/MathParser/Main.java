import java.awt.EventQueue;


/**
 * @author Piotr Bielak
 * 
 * Draws functions given by user.
 * Application supports: 
 * 			numbers
 * 			mathematical operators (like +, -, *, /, ^), where ^ means power
 * 			mathematical functions (sin, cos, tan, log, sqrt, exp)
 *
 */
public class Main {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new WindowFrame();
			}
		});
	}
}

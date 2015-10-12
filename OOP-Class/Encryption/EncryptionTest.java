import javax.swing.SwingUtilities;

/**
 * @author Piotr Bielak
 * This application encrypts files using a user chosen algorithm (Caesar Cipher, DES or AES)
 */
public class EncryptionTest {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new WindowFrame();
			}
		});
	}
}

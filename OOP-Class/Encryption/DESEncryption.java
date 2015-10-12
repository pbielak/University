import java.awt.Component;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;

/*
 * DES encrypting algorithm class 
 */

public class DESEncryption implements Encryptable{
	private SecretKey key; // Encrypting key
	private Cipher cipher; // Cipher variable
	
	public DESEncryption() throws Exception{
		this.cipher = Cipher.getInstance("DES");			
	}
	
	public String encrypt(String in) throws Exception{
		cipher.init(Cipher.ENCRYPT_MODE, this.key);
		
		byte[] utf8 = in.getBytes("UTF-8");
		
		byte[] encoded = this.cipher.doFinal(utf8);
		
		return new String(DatatypeConverter.printBase64Binary(encoded));
	}
	
	public String decrypt(String in) throws Exception{
		cipher.init(Cipher.DECRYPT_MODE, this.key);
		
		byte[] decrypt = DatatypeConverter.parseBase64Binary(in);
		
		byte[] utf8 = cipher.doFinal(decrypt);
		
		return new String(utf8, "UTF-8");
	}

	/*
	 * Get the file extension
	 */
	@Override
	public String getExtension() {
		return "des";
	}

	/*
	 * Get the encryption key (minimum 16 chars) 
	 */
	@Override
	public void getKey(Component parent) throws Exception{
		String str = null; 
		
		do{
			str = JOptionPane.showInputDialog((Component)parent, "Enter a key: \n" + "(Minimum 16 chars)", "Key", JOptionPane.PLAIN_MESSAGE);
			if(str == null) str = "";
		}while(str.length() < 16);
		
		
		this.key = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(str.getBytes()));
	}
}

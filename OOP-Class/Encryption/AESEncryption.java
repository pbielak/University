import java.awt.Component;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;

public class AESEncryption implements Encryptable {

	private SecretKey key; // Encryption key
	private Cipher cipher; // Cipher variable

	public AESEncryption() throws Exception {
		this.cipher = Cipher.getInstance("AES");
	}

	@Override
	public String encrypt(String in) throws Exception {
		cipher.init(Cipher.ENCRYPT_MODE, this.key);

		byte[] utf8 = in.getBytes("UTF-8");

		byte[] encoded = this.cipher.doFinal(utf8);

		return new String(DatatypeConverter.printBase64Binary(encoded));
	}

	@Override
	public String decrypt(String in) throws Exception {
		cipher.init(Cipher.DECRYPT_MODE, this.key);

		byte[] decrypt = DatatypeConverter.parseBase64Binary(in);

		byte[] utf8 = cipher.doFinal(decrypt);

		return new String(utf8, "UTF-8");
	}

	/*
	 * Get the encrypted file extension 
	 */
	@Override
	public String getExtension() {
		return "aes";
	}

	/*
	 * Get the encrypting key (exactly 16 chars) 
	 */
	@Override
	public void getKey(Component parent) throws Exception{
		String str = null; 
		
		do{
			str = JOptionPane.showInputDialog((Component)parent, "Enter a key: \n" + "(Exactly 16 chars)", "Key", JOptionPane.PLAIN_MESSAGE);
			if(str == null) str = "";
		}while(str.length() != 16);
		
		this.key = new SecretKeySpec(str.getBytes(), "AES");
		
	}
}

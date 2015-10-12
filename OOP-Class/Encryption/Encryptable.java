import java.awt.Component;

/*
 * Interface for encrypting algorithms 
 */

public interface Encryptable {
	public String encrypt(String in) throws Exception;
	public String decrypt(String in) throws Exception;
	public String getExtension();
	public void getKey(Component parent) throws Exception;
}

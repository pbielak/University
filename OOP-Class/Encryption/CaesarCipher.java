import java.awt.Component;

import javax.swing.JOptionPane;

/*
 * Caesar cipher encrypting algorithm class 
 */

public class CaesarCipher implements Encryptable{
	private int offset = -1; // Offset 

	public CaesarCipher() {
	}
	
	@Override
	public String encrypt(String in) throws Exception {
		char[] txt = in.toLowerCase().toCharArray();
		String out = "";
		
		for(char k : txt){
			if((k >= 'a') && (k <= 'z')){
				char c = (char)(k + offset);
			
				if( c > 'z') 
					out += (char)(k - ( 26 - offset));
				else
					out += (char)(k + offset);
			}
			else out += k;
		}
		
		return out;
	}

	@Override
	public String decrypt(String in) throws Exception {
		char[] txt = in.toCharArray();
		String out = "";
		
		
		for(char k : txt){
			if((k >= 'a') && (k <= 'z')){
				char c = (char)(k - offset);
			
				if( c < 'a') 
					out += (char)(k + ( 26 - offset));
				else
					out += (char)(k - offset);
			}
			else out += k;
		}
		
		return out;
	}

	/*
	 * Get encrypted file extension 
	 */
	@Override
	public String getExtension() {
		return "caesar";
	}

	/*
	 * Get a numeric key (in range from 0 to 26) 
	 */
	@Override
	public void getKey(Component parent) {
		boolean goodInput = false;
		int shift = -1;
		
		while(!goodInput){
			try{
				String str = JOptionPane.showInputDialog((Component)parent, "Enter a key: \n" + "(Number in range [0;26])", "Key", JOptionPane.PLAIN_MESSAGE);
				shift = Integer.parseInt(str);
				
				if((shift >= 0) && (shift <= 26)) goodInput = true;
			}
			catch(NumberFormatException e){}
		}
		
		this.offset = shift;
	}
}

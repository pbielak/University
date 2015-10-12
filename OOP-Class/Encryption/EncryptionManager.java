import java.awt.Component;
import java.io.File;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFrame;

public class EncryptionManager{
	private ArrayList<String> inputLines; // Input file lines
	private ArrayList<String> encryptedLines; // Encrypted lines
	private Encryptable encryptAlgo; // Encrypting algorithm
	private boolean isDecrypting; // Encrypting mode
	
	public EncryptionManager(){
		inputLines = new ArrayList<String>();
		encryptedLines = new ArrayList<String>();
		encryptAlgo = null;
		isDecrypting = false;
	}
	
	/*
	 * Set the algorithm or throw an Exception
	 */
	public void setAlgorithm(EncryptionAlgorithms algorithm) throws Exception{
		if(algorithm == EncryptionAlgorithms.CAESAR){
			encryptAlgo = new CaesarCipher();
		}
		else if(algorithm == EncryptionAlgorithms.DES){
			encryptAlgo = new DESEncryption();
		}
		else if(algorithm == EncryptionAlgorithms.AES){
			encryptAlgo = new AESEncryption();
		}
		else throw new NoSuchAlgorithmException();
	}
	
	/*
	 * Load a file
	 */
	public void loadFile(JFrame parent, JTextArea area) throws Exception{
		
		// Choose a file
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Choose file ... ");
		fileChooser.setFileFilter(new FileNameExtensionFilter("ENCRYPTABLE FILES", "txt", "des", "aes", "caesar"));
		
		// If the OK button was clicked
		if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			
			// clear the arraylists and the textarea
			inputLines.clear();
			encryptedLines.clear();
			area.setText(null);
			
			File file = fileChooser.getSelectedFile();
			
			// check the file extension and decide if we are encrypting or decrypting
			if(file.getName().endsWith(".txt")) isDecrypting = false;
			else isDecrypting = true;
			
			Scanner in = new Scanner(file);
			
			// Set the line counter to zero
			int counter = 0;
			
			// Read all lines
			while(in.hasNextLine()){
				String line = in.nextLine();
				// Add the line to the arraylist ...
				inputLines.add(line);
				// ... and to the textarea ...
				area.append(line + "\n");
				area.setRows(inputLines.size());
				// ... and set the frame title
				parent.setTitle(String.format("Loading file (%d lines)", counter++));
			}
			
			parent.setTitle("Enigma v. 2015");
			in.close();
		}
	}
	
	/*
	 * Save a file
	 */
	public void saveFile(JFrame parent) throws Exception{
		
		// Select a filename
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save file ... ");
		
		// If decrypting set the file extension to txt  
		String extension = (isDecrypting == false) ? encryptAlgo.getExtension() : "txt";
		
		fileChooser.setFileFilter(new FileNameExtensionFilter("ENCRYPTABLE FILES", extension));
		
		// If the OK button was clicked
		if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
			File file = fileChooser.getSelectedFile();
			
			PrintWriter out = new PrintWriter(file + ((!file.getName().endsWith("." + extension))? ("."+extension) : ""));
			
			// Create a counter
			int counter = 0;
			
			// Write to file
			for(String str : encryptedLines){
				out.println(str);
				// Update the frame title
				parent.setTitle(String.format("Saved %d / %d linii", counter++, encryptedLines.size()));
			}
			
			parent.setTitle("Enigma v. 2015");
			
			out.close();
		}
	}
	
	/*
	 * Perform the algorithm 
	 */
	public void performAlgorithm(JFrame parent, JTextArea area) throws Exception{
		// Clear the textarea and create a counter
		area.setText(null);
		int counter = 0;
		
		// Get the encrypting key
		encryptAlgo.getKey((Component) parent);
		
		// For each line 
		for(String txt : inputLines){
			String line;
			
			// Encrypt or decrypt
			if(isDecrypting == false)
				line = encryptAlgo.encrypt(txt);
			else
				line = encryptAlgo.decrypt(txt);
			
			
			encryptedLines.add(line);
			
			// Set the title of the frame
			parent.setTitle(String.format("Encrypting... %d %% ", Math.round(100*(counter++)/(double)inputLines.size())));
			
			// Add encrypted line to the textarea
			area.append(line + "\n");
			area.setRows(encryptedLines.size());
		}
		
		parent.setTitle("Enigma v. 2015");
	}
}

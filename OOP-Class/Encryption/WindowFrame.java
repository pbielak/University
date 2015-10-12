import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * @author Piotr Bielak
 * Most of this file was generated using NetBeans (faster way to create a GUI)
 */
public class WindowFrame extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = -865679021172573158L;
	
	private ButtonGroup buttonGroup1;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JPanel jPanel1;
    private JRadioButton jRadioButton1;
    private JRadioButton jRadioButton2;
    private JRadioButton jRadioButton3;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JTextArea jTextArea2;
    private JTextArea jTextArea3;
    
    private boolean isInputLoaded;
    private boolean isOutputGenerated;
    
    private EncryptionManager manager;
    
    public WindowFrame() {
    	manager = new EncryptionManager(); 
    	
    	initComponents();
        setResizable(false);
        setVisible(true);
        
        isInputLoaded = false;
        isOutputGenerated = false;
        
        jButton1.addActionListener(this);
        jButton2.addActionListener(this);
        jButton3.addActionListener(this);
        
    }

    private void initComponents() {

        buttonGroup1 = new ButtonGroup();
        jPanel1 = new JPanel();
        jButton1 = new JButton();
        jLabel1 = new JLabel();
        jScrollPane2 = new JScrollPane();
        jTextArea2 = new JTextArea();
        jLabel2 = new JLabel();
        jScrollPane3 = new JScrollPane();
        jTextArea3 = new JTextArea();
        jButton2 = new JButton();
        jRadioButton1 = new JRadioButton();
        jRadioButton2 = new JRadioButton();
        jRadioButton3 = new JRadioButton();
        jLabel3 = new JLabel();
        jButton3 = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Enigma v. 2015");
        
        jScrollPane2.createHorizontalScrollBar();
        jScrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane2.createVerticalScrollBar();
        jScrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setMaximumSize(new Dimension(300, 300));

        jScrollPane3.createHorizontalScrollBar();
        jScrollPane3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane2.createVerticalScrollBar();
        jScrollPane3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane3.setMaximumSize(new Dimension(300, 300));
        
        jButton1.setText("Open file ...");

        jLabel1.setText("Input:");

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(20);
        jTextArea2.setMinimumSize(new Dimension(300, 300));
        jTextArea2.setPreferredSize(new Dimension(300, 300));
        jScrollPane2.setViewportView(jTextArea2);

        jLabel2.setText("Output:");

        jTextArea3.setEditable(false);
        jTextArea3.setColumns(20);
        jTextArea3.setRows(20);
        jTextArea3.setMinimumSize(new java.awt.Dimension(300, 300));
        jTextArea3.setPreferredSize(new java.awt.Dimension(300, 300));
        jScrollPane3.setViewportView(jTextArea3);

        jButton2.setText("Save output ...");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Caesar");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("DES");

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("AES");

        jLabel3.setText("Choose encrypting algorithm");

        jButton3.setText("Encrypt");
        jButton3.setEnabled(false);
        

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton2)
                                    .addComponent(jLabel2)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jRadioButton1)
                                            .addComponent(jRadioButton2)
                                            .addComponent(jRadioButton3)
                                            .addComponent(jLabel3)
                                            .addComponent(jButton3))))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(12, 12, 12)
                                .addComponent(jRadioButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))
                        .addGap(25, 25, 25))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		//Load a file
		if(obj == jButton1){
			try {
				manager.loadFile(this, jTextArea2);
			} catch (Exception ee) {
				System.out.println("Error while loading file !!!");
			} finally{
				isInputLoaded = true;
				jButton3.setEnabled(true);
				jTextArea3.setText(null);		
			}
		}
		//Save a file
		else if(obj == jButton2){
			if(isOutputGenerated){
				try {
					manager.saveFile(this);
				} catch (Exception ee) {
					System.out.println("Error while saving file !!!");
				}
			}
		}
		//Perform encryption algorithm 
		else if(obj == jButton3){
			if(isInputLoaded){
				try {
					if(jRadioButton1.isSelected())
						manager.setAlgorithm(EncryptionAlgorithms.CAESAR);
					else if(jRadioButton2.isSelected())
						manager.setAlgorithm(EncryptionAlgorithms.DES);
					else
						manager.setAlgorithm(EncryptionAlgorithms.AES);
					
					manager.performAlgorithm(this, jTextArea3);
				} catch (Exception ee) {
					ee.printStackTrace();
					System.out.println("Error while encrypting file !!!");
				} finally{
					isOutputGenerated = true;
					jButton3.setEnabled(false);							
				}
			}
		}
		
	}
}

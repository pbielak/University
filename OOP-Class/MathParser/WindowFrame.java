import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WindowFrame extends JFrame{
	/**
	 * Window frame class
	 */
	private static final long serialVersionUID = -3931411052921182633L;
	
	private FunctionChart canva;
	private JTextField function_input;
	private JButton update;
	private ActionListener action;
	private JTextField[] parameters;

	public WindowFrame() {
		//Set default parameters
		setTitle("Wykresy");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		//Add new container
		JPanel container = new JPanel();
		container.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		//Add significant items (canvas, input, update button)
		addSignificantPanels(container, c);
		
		//Add other stuff (parameter labels and text fields)
		addParametersPanel(container, c);
		
		//Add the container to the frame and pack everything together
		add(container);
		pack();
		
		//Set and add an action listener 
		setActionListener();
		update.addActionListener(action);
		
		//Finally show the window
		setVisible(true);
	}

	/*
	 * Set the action listener 
	 */
	private void setActionListener() {
		action = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == update)
				{
					//Update all parameters ...
					canva.updateExpression(function_input.getText());
					canva.updateXRange(Integer.parseInt(parameters[0].getText()), Integer.parseInt(parameters[1].getText()));
					canva.updateYRange(Integer.parseInt(parameters[2].getText()), Integer.parseInt(parameters[3].getText()));
					canva.updateStep(Double.parseDouble(parameters[4].getText()));
					
					// ... and repaint the function
					canva.repaint();
				}
			}
		};
	}

	/*
	 * Add the canvas, function input and update button
	 */
	private void addSignificantPanels(JPanel container, GridBagConstraints c) {
		canva = new FunctionChart();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 500;
		c.ipady = 500;
		container.add(canva, c);

		function_input = new JTextField("x");
		c.gridx = 0;
		c.gridy = 1;
		c.ipadx = 0;
		c.ipady = 0;
		container.add(function_input, c);
		
		update = new JButton("RYSUJ!");
		c.gridx = 1;
		c.gridy = 1;
		c.ipadx = 0;
		c.ipady = 0;
		container.add(update, c);
	}

	/*
	 * Add parameter panel ( = parameter labels and text fields)
	 */
	private void addParametersPanel(JPanel container, GridBagConstraints c) {
		JPanel options = new JPanel();
		options.setLayout(new GridBagLayout());
		
		parameters = new JTextField[5];
		
		//MinX
		c.gridx = 0;
		c.gridy = 0;
		options.add(new JLabel("MinX:"), c);
		
		parameters[0] = new JTextField("-20");
		c.gridx = 1;
		c.gridy = 0;
		options.add(parameters[0], c);
		
		
		//MaxX
		c.gridx = 0;
		c.gridy = 1;
		options.add(new JLabel("MaxX:"), c);
		
		parameters[1] = new JTextField("20");
		c.gridx = 1;
		c.gridy = 1;
		options.add(parameters[1], c);

		
		//MinY
		c.gridx = 0;
		c.gridy = 2;
		options.add(new JLabel("MinY:"), c);
		
		parameters[2] = new JTextField("-20");
		c.gridx = 1;
		c.gridy = 2;
		options.add(parameters[2], c);
		
		
		//MaxY
		c.gridx = 0;
		c.gridy = 3;
		options.add(new JLabel("MaxY:"), c);
		
		parameters[3] = new JTextField("20");
		c.gridx = 1;
		c.gridy = 3;
		options.add(parameters[3], c);
		
		
		//Step
		c.gridx = 0;
		c.gridy = 4;
		options.add(new JLabel("Step:"), c);
		
		parameters[4] = new JTextField("0.01");
		c.gridx = 1;
		c.gridy = 4;
		options.add(parameters[4], c);
		
		
		c.gridx = 1;
		c.gridy = 0;
		container.add(options);
	}

}

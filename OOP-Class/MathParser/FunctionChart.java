import java.awt.Graphics;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.Range;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


/*
 * Panel with the function chart
 */

public class FunctionChart extends JPanel{
	private static final long serialVersionUID = -2114663063155825167L;
	
	private String expression;
	private Range xRange, yRange;
	private double step;
	
	private JFreeChart chart;
	
	// Set "default" values
	public FunctionChart(){
		this.expression = new String("x");
		
		this.xRange = new Range(-20, 20);
		this.yRange = new Range(-20, 20);
		this.step = 0.1;
		
		calculate();
	}
	
	/*
	 * Update x range 
	 */
	public void updateXRange(int lower, int upper){
		this.xRange = new Range(lower, upper);
		calculate();
	}
	
	/*
	 * Update y range 
	 */
	public void updateYRange(int lower, int upper){
		this.yRange = new Range(lower, upper);
		calculate();
	}
	
	/*
	 * Update step 
	 */
	public void updateStep(double step){
		this.step = step;
		calculate();
	}
	
	/*
	 * Update function expression 
	 */
	public void updateExpression(String expr){
		this.expression = new String(expr);
		calculate();
	}
	
	/*
	 * Calculate all function points (argument, function(argument))
	 * in a given range
	 */
	public void calculate(){
		// Parse the expression
		InfixParser infix = new InfixParser(this.expression);
		infix.convertInfixToPostfix();
		PostfixInterpreter postfix = new PostfixInterpreter(infix.getOutput());
		
		
		// Create new XYSeries
		XYSeries series = new XYSeries("XYGraph");
		
		// Calculate and add all points to the series
		for (double i = this.xRange.getLowerBound(); i <= this.xRange.getUpperBound(); i += this.step) {
			double result = postfix.calculatePostfix(i);
			series.add(i, result);
		}

		// Create a dataset and add our series to it
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);

		// Finally create the chart ...
		chart = ChartFactory.createXYLineChart(
				"Funkcja y = " + this.expression, "Oœ X", "Oœ Y", dataset,
				PlotOrientation.VERTICAL, false, false, false);
		
		// ... and adjust the axes
		chart.getXYPlot().getDomainAxis().setRange(xRange.getLowerBound(), xRange.getUpperBound());
		chart.getXYPlot().getRangeAxis().setRange(yRange.getLowerBound(), yRange.getUpperBound());
	
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		calculate();
		g.drawImage(chart.createBufferedImage(500, 500), 0, 0, null);
	}
}

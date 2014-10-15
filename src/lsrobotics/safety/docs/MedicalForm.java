package lsrobotics.safety.docs;

/**
 * @author connordevitt
 * 
 * this file creates the user interface to gather all information regarding a medical event required by first
 * 
 * Questions and TODOs
 * 	1.TODO finish
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class MedicalForm extends JFrame implements ActionListener {
	Dimension dimension;
	
	//the JPanels to hold various groups of components
	JPanel JPEvent, JPIncident, JPInjured, JPInjury, JPMedicalCare, JPParDescription,
		   JPWitness, JPWitDescription, JPReporter;
	
	//All components for the JPEvent group
	
	MedicalForm(String title) {
		super(title);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		drawWindow();
		this.pack();
		this.setSize(900, 650);
	}
	void drawWindow() {
		dimension = getSize();
		int width = dimension.width;
		int height = dimension.height;
	}
	public void actionPerformed(ActionEvent ae) {
		
	}
}


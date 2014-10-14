package lsrobotics.safety.docs;

/**
 * @author connordevitt
 * 
 * This creates the main window for the application base on the designs fom Nick. 
 * 
 * Quesiotns and TODOs:
 * 	1. Use tabs or stay with the buttons
 * 	2. keep the background? what color? image/logo?
 * 	3. TODO add functionality to the buttons when the other windows are programmed
 * 	4. TODO update catches to accomodate for missing images and replce with some standaridzed image
 * 	5. to run: make an object and provide the window's title as well as the path to the image files
 * 
 * 
 * if you feel something is missing please change/fix it
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

public class mainWindow extends JFrame implements ActionListener {
	JButton JBreport, JBmanuel, JBmembers; 	//buttons for he different functions
	ImageIcon IIreport, IImanuel, IImembers;
	String location; 						//the directory to the resources (not to a file)
	JPanel JPbackground, JPbuttons; 		//panels to hold the background and the buttons
	Dimension dimensions;					//the dimensions of the window
	
	mainWindow(String title, String location) {
		super(title); //sets the title of the window
		this.location = location; //the location of all images and resources
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); //allows the window to be closed
		this.setVisible(true); //shows the window
		this.setSize(900, 650); //sets the preferred size
		this.setResizable(false); //size cannot be changed
		drawWindow(); //makes the window
		this.pack();
	}
	void drawWindow() {
		dimensions = getSize();
		int width = dimensions.width;
		int height = dimensions.height;
		try {
			// the buttons {
				//the panel to hold the buttons
				JPbuttons = new JPanel();
				
				//makes icons for the buttons
				IIreport = new ImageIcon(location + "report.png");
				IImanuel = new ImageIcon(location + "manuel.png");
				IImembers = new ImageIcon(location + "members.png");
				
				//makes the buttons and action listeners
				JBreport = new JButton(IIreport);
				JBreport.addActionListener(this);
				JBmanuel = new JButton(IImanuel);
				JBmanuel.addActionListener(this);
				JBmembers = new JButton(IImembers);
				JBmembers.addActionListener(this);
				
				//sets the layout
				JPbuttons.setLayout(new GridLayout(1, 3, 50, 0)); //puts the buttons next to each other 
				JPbuttons.setBorder(new EmptyBorder(0, 150, 0, 150)); //adds a gap between the canvas component
				
				//adds the buttons 
				JPbuttons.add(JBreport);
				JPbuttons.add(JBmanuel);
				JPbuttons.add(JBmembers); 		
			// }
			//the background {
				Background background = new Background(dimensions.width, dimensions.height - 200); //adds the canas containing the app's logo
				background.setSize(dimensions.width, dimensions.height - 200); //makes room for the buttons underneath the canvas
				background.setVisible(true); //shows the canvas
				JPbuttons.setBackground(Color.red); //makes the canvas seem seamless
			// }
			
			//add everything
			this.setLayout(new BorderLayout());
			this.add(background, BorderLayout.CENTER); //background at the top
			this.add(JPbuttons, BorderLayout.SOUTH); //buttons at the bottom
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == JBreport) {
			//when the report button is pressed do...
		}
		else if(ae.getSource() == JBmanuel) {
			//when the manuel button is pressed do...
		}
		else if(ae.getSource() == JBmembers) {
			//when the members button is pressed do...
		}
	}
	class Background extends Canvas {
		int width, height; //the dimensions of the canvas
		
		public Background(int w, int h) {
                        this.width = w;
                        this.height = h;
			setBackground(Color.RED); //background is red
		}
		public void paint(Graphics g) {
			Dimension dimension = getSize();
			int width = dimension.width;
			int height = dimension.height;
			try {
				BufferedImage logo = ImageIO.read(new File(location + "background.png")); //imports the logo
	
				g.drawImage(logo, 0, 0, width, height, null); //draws the logo
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}


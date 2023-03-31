package ie.atu.sw;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;

public class MorseWindow {
	private Colour[] colours = Colour.values(); //This might come in handy
	private ThreadLocalRandom rand = ThreadLocalRandom.current(); //This will definitely come in handy
	private JFrame win; //The GUI Window
	private String encodeFile = "encoded.txt";
	private String decodeFile = "decoded.txt";//.\src\decoded.txt
	
	public MorseWindow(){
		/*
		 * Create a window for the application. Building a GUI is an example of 
		 * "divide and conquer" in action. A GUI is really a tree. That is why
		 * we are able to create and configure GUIs in XML.
		 */
		win = new JFrame();
		win.setTitle("Data Structures & Algorithms 2023 - Morse Encoder/Decoder");
		win.setSize(650, 400);
		win.setResizable(false);
		win.setLayout(new FlowLayout());
		
        /*
         * The top panel will contain the file chooser and encode / decode buttons
         */
        var top = new JPanel(new FlowLayout(FlowLayout.LEADING));
        top.setBorder(new javax.swing.border.TitledBorder("Select File"));
        top.setPreferredSize(new Dimension(600, 80));

        var txtFilePath =  new JTextField(20);
		txtFilePath.setPreferredSize(new Dimension(100, 30));

		var chooser = new JButton("Browse...");
		chooser.addActionListener((e) -> {
			var fc = new JFileChooser(System.getProperty("user.dir"));
			var val = fc.showOpenDialog(win);
			if (val == JFileChooser.APPROVE_OPTION) {
				var file = fc.getSelectedFile().getAbsoluteFile();
				txtFilePath.setText(file.getAbsolutePath());
			}
		});
		
		var notes = new JTextArea();//moved to appened in buttons
		var formatter = new DecimalFormat("#,###.00"); //Formats the time output
		
		var btnEncodeFile = new JButton("Encode");
		btnEncodeFile.addActionListener(e -> {
			//Start your encoding here, but put the logic in another class
			System.out.println("Encode.....");
			var start = System.currentTimeMillis(); //Start the clock
			MorseCoder coder = new MorseCoder();
			try {
				if(txtFilePath.getText() != null) coder.encodeFile(txtFilePath.getText(), encodeFile);
				else coder.decodeFile(decodeFile, encodeFile);
			} catch (IOException e1) {
				System.out.println("File not found");
				e1.printStackTrace();
			}
			notes.setText("[ENCODED]"+coder.fileToString(encodeFile));
			double searchTime = ((System.currentTimeMillis() - start)); //Stop the clock and print out the result
			System.out.println("ENCODED in " + formatter.format(searchTime) + "ms.");
		});
		
		var btnDecodeFile = new JButton("Decode");
		btnDecodeFile.addActionListener(e -> {
			//Start your decoding here, but put the logic in another class
			System.out.println("Decoding.....");
			var start = System.currentTimeMillis(); //Start the clock
			MorseCoder coder = new MorseCoder();
			try {
				if(txtFilePath.getText() != null) coder.decodeFile(txtFilePath.getText(), decodeFile);
				else coder.decodeFile(encodeFile, decodeFile);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			notes.setText("[DECODED]"+coder.fileToString(decodeFile));
			double searchTime = ((System.currentTimeMillis() - start)); //Stop the clock and print out the result
			System.out.println("DECODED in " + formatter.format(searchTime) + "ms.");
		});
		
		//Add all the components to the panel and the panel to the window
        top.add(txtFilePath);
        top.add(chooser);
        top.add(btnEncodeFile);
        top.add(btnDecodeFile);
        win.getContentPane().add(top); //Add the panel to the window
        
        
        /*
         * The middle panel contains the coloured square and the text
         * area for displaying the outputted text. 
         */
        var middle = new JPanel(new FlowLayout(FlowLayout.LEADING));
        middle.setPreferredSize(new Dimension(600, 200));

        var dot = new JPanel();
        dot.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        dot.setBackground(getRandomColour());
        dot.setPreferredSize(new Dimension(140, 150));
        dot.addMouseListener(new MouseAdapter() { 
        	//Can't use a lambda against MouseAdapter because it is not a SAM
        	public void mousePressed( MouseEvent e ) {  
        		dot.setBackground(getRandomColour());
        	}
        });
        
        //Add the text area
        
		notes.setLineWrap(true);
		notes.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		notes.append("[DECODED]THIS IS LONDON CALLING. COME IN TOBRUK.\n\n");
		notes.append("[ENCODED]- .... .. ... / .. ... / .-.. --- -.");
		notes.setPreferredSize(new Dimension(440, 150));
		
		//Add all the components to the panel and the panel to the window
		middle.add(dot);
		middle.add(notes);
		win.getContentPane().add(middle);
		
		
		/*
		 * The bottom panel contains the clear and quit buttons.
		 */
		var bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.setPreferredSize(new java.awt.Dimension(500, 50));

        //Create and add Clear and Quit buttons
        var clear = new JButton("Clear");
        clear.addActionListener((e) -> notes.setText(""));
        
        var quit = new JButton("Quit");
        quit.addActionListener((e) -> System.exit(0));
        
        //Add all the components to the panel and the panel to the window
        bottom.add(clear);
        bottom.add(quit);
        win.getContentPane().add(bottom);       
        
        
        /*
         * All done. Now show the configured Window.
         */
		win.setVisible(true);
	}
	
	private Color getRandomColour() {
		Colour c = colours[rand.nextInt(0, colours.length)];
		return Color.decode(c.hex() + "");
	}
}
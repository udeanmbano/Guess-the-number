/*
Author-Udean Mbano
MGI2012-3244
Author-Mathew Bate
MGI2012-0983
Date-28/03/2014
Version 14.2
/Purpose: A simple GUI program that accepts a user input number to check if it matches with a random number to win the game
 */
package mgi;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class GuessNumber extends JFrame
{
	//Creating global variables
	int randomNumber = (int)(Math.random() * (20+1)); 
	int numberOfGuesses = 0;
        int option=0;
        String holder;
	JTextField guessField;
	JButton submitButton, tryAgainButton,exitButton;
	JPanel  topPanel, bottomPanel ,middlePanel;
        JLabel  yourGuess, infoLabel;
	
	//Making constructor
GuessNumber ()
	{
		//Setting layout GridLayout
		this.setLayout(new GridLayout(2,0,0,0) );
		//Creating top,middle and bottom panels
		topPanel = new JPanel();
		bottomPanel = new JPanel();
                middlePanel= new JPanel();
		//Setting layouts and adding to this Panel
		topPanel.setLayout(new FlowLayout());
		bottomPanel.setLayout(new FlowLayout());
                middlePanel.setLayout(new BorderLayout());
		this.add(topPanel);
		this.add(bottomPanel);
		this.add(middlePanel);
		//Setting what each label needs to have in it and adding to topPanel
		infoLabel = new JLabel("Guess a number between 0 and 20");
		yourGuess = new JLabel("");
		//Setting guessField size to 10 characters
		guessField = new JTextField (10);
                //Adding JComponents to the top panel
		 topPanel.add(infoLabel);
		 topPanel.add(guessField);
                 // Adding JTextField to the middle panel
		middlePanel.add(yourGuess, BorderLayout.CENTER);
                //Setting buttons to their respective name, adding to the bottomPanel and adding Action Listeners
		submitButton   = new JButton("Submit");
		tryAgainButton = new JButton("Try Again");
                exitButton     = new JButton("Exit");
		//Adding JComponents to the bottom panel
                bottomPanel.add(submitButton);
		bottomPanel.add(tryAgainButton);
                bottomPanel.add(exitButton);
                //registering buttons with their listeners
                submitButton  .addActionListener(new buttonHandler());
                exitButton    .addActionListener(new buttonHandler());
		tryAgainButton.addActionListener(new buttonHandler());
				
	}//end constructor
	
	//Creating class to handle buttonevents
	public class buttonHandler implements ActionListener
	{
            
		public void actionPerformed(ActionEvent e)
		{
			//If user hits submit button get the text from guessField, parse it into a integer and send it to
				//guessDeterminator() to do the rest of the work
                    
			if(e.getSource().equals(submitButton))
			{
				int tempHolder = 0;
				holder = guessField.getText();
				tempHolder = Integer.parseInt(holder);
				guessDeterminator(tempHolder);
				
			}//end if
			//If user hits tryAgainButton, set topPanel bak to gray, guesses back to zero, generate a new random number and
				//set text fields back to nothing
			else if (e.getSource().equals(tryAgainButton))
			{
                               	topPanel.setBackground(Color.GRAY);
				numberOfGuesses = 0;
				randomNumber = (int)(Math.random() * (20+1));
				yourGuess.setText("");
			 	guessField.setText("0");
			}//end if statement
                   else if (e.getSource().equals(exitButton))
                   {
                       option = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit Game" ,JOptionPane.YES_NO_OPTION);
			if(option == JOptionPane.YES_OPTION)
				System.exit(0);
                   }
                                    
		}//end actionPerformed
	}//end buttonhandler
	
	//Name: guessDeterminator
	//Args: one int
	//Purpose: To take in the users guess and determine whether its  right one matching the random number
	// generated
	public void guessDeterminator(int guess)
	{
		//Emptying all guess labels to empty
	 	yourGuess.setText("");
		//If guess is right then display to the user their correct, the number of guesses it took them and
	 	//set background to green
             	if(guess == randomNumber)
	 	{
	 		yourGuess.setText("Your guess of " + guess + " is correct!");
	                   yourGuess.setText("It took you about  " + numberOfGuesses + " attempts in the game CONGRAGULATIONS!");
	 		topPanel.setBackground(Color.GREEN);
                }//end if
	 	//If guess is lower than random number, make background blue and tell user guess is low
                else if (guess != randomNumber)
	 	{
	 		numberOfGuesses++;
	 	        JOptionPane.showMessageDialog(null," Wrong Guess");
                        yourGuess.setText("Attempts :" + numberOfGuesses);
	 		topPanel.setBackground(Color.RED);
	 	}//end else if
	 	if(numberOfGuesses==4)
                {
                 JOptionPane.showMessageDialog(null,"Game over you had four attempts your last number was  "+ guess +" the correct number is " +randomNumber + " please click try another game or exit");
                topPanel.setBackground(Color.GRAY);
		numberOfGuesses = 0;
	        int randomNumber = (int)(Math.random() * (20+1));
		yourGuess.setText("");
		guessField.setText("0");
                }
      	 }//end guessDeterminator

 public static void main(String args[]) {
    // instantiate GuessNumberGame object
                GuessNumber frame = new GuessNumber();
    	 	//Setting default close operation
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		 //Setting size
		frame.setSize(800,200);
		//Setting location
		frame.setLocationRelativeTo(null);
		//Setting visibility
		frame.setVisible(true);
     }// End main method
}//end class
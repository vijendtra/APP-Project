package edit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * PlayerOpponentChoose class is the starting of the game where the selection of
 * the player or the opponent is done Constructor consist of the frame with two
 * buttons to choose the player and opponent PlayerActionListener class which
 * implements the action listener to select player OpponentActionListener class
 * which implements the action listener to select the opponent MainFrame object
 * is called to select the player and opponent with different features
 * 
 * @author Team3
 * 
 */
// The first frame, allows for choosing of Player or Opponent Character to be
// created, edited or saved
public class PlayerOpponentChoose {

	MainFrame t3;
	int choosen;
	JFrame frame;
	/*
	 * Creates the frame where two buttons are displayed
	 * Player and Opponent
	 * The player button which leads the user to choose the player character
	 * The opponent button which allows the user to choose the opponent character
	 */
	public void choosenType() {
		// Creates the frame and set up the button
		frame = new JFrame();
		JPanel panel = new JPanel();
		JButton player = new JButton("Player");
		JButton opponent = new JButton("Opponent");
		player.addActionListener(new PlayerListener());
		opponent.addActionListener(new OpponentListener());
		panel.add(player);
		panel.add(opponent);
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setSize(300, 70);
		frame.setVisible(true);
	}

	/*
	 * An action listener for the player button which passes the value in the choosen variable 
	 * and calls the mainframe class where the user can create or edit or save a character.
	 * A default option is also present in the mainframe
	 */
	class PlayerListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			choosen = 1;
			
			frame.setVisible(false);
			frame.dispose();
			// Calls MainFrame class with the variable choosen set for player
			t3 = new MainFrame(choosen);

		}
	}

	/*
	 * An action listener for the Opponent button which passes the value in the choosen variable 
	 * and calls the mainframe class where the user can create or edit or save a character.
	 * A default option is also present in the mainframe
	 */
	class OpponentListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			choosen = 0;
			
			frame.setVisible(false);
			frame.dispose();
			// Calls MainFrame class with the variable choosen set for opponent
			t3 = new MainFrame(choosen);
		}
	}

	/* 
	 * Function to return what was choosen as an integer which declares if it is a player or an opponent
	 */
	public int getChoosen() {
		return choosen;
	}

	// public static void main(String[] args) {
	// PlayerOpponentChoose t = new PlayerOpponentChoose();
	// t.choosenType();

	// }

}

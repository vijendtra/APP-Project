package ui;



import javax.swing.*;

import edit.PlayerOpponentChoose;

import java.awt.BorderLayout;
import java.awt.event.*;

/**
 * The GameLauncher class is the class which contains the main function.
 * This has the starting frame when the game is started.
 * The frame displays three buttons to the user which are named as Start game, New game and Exit.
 *  The action listeners for each button are also written below.
 *  @author team3
 */
public class GameLauncher {

	JFrame frame;
	public GameLauncher() {
		frame = new JFrame();
		JPanel north = new JPanel();
		JPanel center = new JPanel();
		JPanel south = new JPanel();
		JPanel panel = new JPanel();
		JButton startGame = new JButton("Start Game");
		JButton newGame = new JButton("New Game");
	
		JButton ex = new JButton("Exit");
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		north.add(startGame);
		south.add(ex);
		center.add(newGame);
		panel.add(north);
		panel.add(center);
		panel.add(south);
		
		startGame.addActionListener(new startGameListener());
		newGame.addActionListener(new newGameListener());

		ex.addActionListener(new exListener());
		
		frame.getContentPane().add(BorderLayout.CENTER, panel);

		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
	/*
	 * Action listener for the start game button is written below which will call the filelist class
	 * which will show the list of files from where we can pick a character which is already existing.
	 */
	class startGameListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			FileList fl = new FileList();
			frame.setVisible(false);
			frame.dispose();
		}
	}
	/*
	 * Action listener for the new game button is written below which will call the playeropponentchoose class
	 * which will show the user another frame where he can choose the player or the opponent.
	 */
	class newGameListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			frame.setVisible(false);
			frame.dispose();
			PlayerOpponentChoose poc = new PlayerOpponentChoose();
			poc.choosenType();
		}
	}
	/*
	 * Action listener for the exit game button is written below which will show a JOptionPane which shows 
	 * an alert message stating that you have exited the game.
	 */

	
	class exListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			frame.dispose();
			int mc = JOptionPane.INFORMATION_MESSAGE;
			String msg = "Exit Game complete";
			JOptionPane.showMessageDialog(null, msg, "Alert", mc);
		}
	}
	/*
	 * The main method is written below which will call the gamelauncher
	 */
	public static void main(String[] args) {
		GameLauncher gl = new GameLauncher();
		
	}

}

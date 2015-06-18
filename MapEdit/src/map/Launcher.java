package map;

//import DrawPanel;

import javax.swing.*;

import race.Launch;

import java.awt.event.*;
import java.awt.*;


/**
 * class Launcher is the map editor that provides the buttons to edit the map
 * Launcher consists of two constructors with different arguments based on the type of map either existing map or new map
 * Method go opens the frame with the map and buttons to modify the map
 * Inner Class WallListener is to perform the ActionListener of the button wall
 * Inner class SaveListener to save the edited map
 * Inner class ReseListener to reset the map to the original position 
 * Launcher calls the DrawPanel to draw the map to the frame and to perform the mouse listener to edit the map
 * @author Team3
 *
 */

// Frame for holding the map editor
public class Launcher {

	boolean pselect, wselect, oselect, chestselect;
	JFrame frame;
	DrawPanel dp;
	JButton player, wall, opponent, reset, save, chest;
	String mapName, charName;
	int set = 0;
	public static int height,width;
    Launch l;
 //   public static void main(String[] args) {
 //       Launch l = new Launch();
//		l.go();
//	}
    /*
     * The constructor of the Launcher class which sets the map name and the character name which the user selected.
     */
    public Launcher(String s, String cFile) { this.mapName = s; this.charName = cFile; }
    /*
     * The constructor of the Launcher class which takes the height, width of the map and the map name and the chracter name and
     * calls the drawpanel class to draw this map onto the frame
     */
    public Launcher(String s, int h, int w, int t, String cFile) {
		this.mapName = s;
		height=h;
		width=w;
		dp = new DrawPanel(mapName, h, w, t);
		set = 1;
		this.charName = cFile;
		go();
	}
    /*
	 * The go method displays the frame where the editable map is displayed along with the buttons where you can add the wall,
	 * chest, opponents onto the editable map. This map can be saved by clicking the save button.
	 */
	public void go() {
		frame = new JFrame();
        dp = new DrawPanel(mapName);
        JPanel south = new JPanel();
        chest = new JButton("Chest");
        player = new JButton("Player");
        wall = new JButton("Wall");
        opponent = new JButton("Opponent");
		reset = new JButton("Reset");
		save = new JButton("Save");
        south.add(player);
        south.add(wall);
        south.add(opponent);
        south.add(chest);
		south.add(reset);
		south.add(save);
        player.addActionListener(new playerListener());
        wall.addActionListener(new wallListener());
        opponent.addActionListener(new opponentListener());
		reset.addActionListener(new resetListener());
		save.addActionListener(new saveListener());
		chest.addActionListener(new chestListener());
		dp.setPreferredSize(new Dimension(700, 700));
		JScrollPane dpScroll =  new JScrollPane(dp);
		//dpScroll.setPreferredSize(new Dimension(300, 300));
        frame.getContentPane().add(BorderLayout.CENTER, dpScroll);
        frame.getContentPane().add(BorderLayout.SOUTH, south);
        if(dp.getPlayerXPos() >= 0 && dp.getPlayerYPos() >= 0)
            player.setEnabled(false);
		JPanel east = new JPanel();
		String list = "<html><div style=\"width:100px;height:10px;border:1px solid #000;\"><center>Legend</center></div><br>"
				+ "<div style=\"width:100px;height:10px;border:1px solid #000;\"><center>Color : Piece</center></div><br>"
				+ "<div style=\"width:100px;height:10px;border:1px solid #000;\"> Green : Player<br> Red : Opponent<br> Yellow : Chest<br> Black : Wall<br> White : Floor</div><br>"
				+ "<div style=\"width:100px;height:10px;border:1px solid #000;\"><center>Hint</center>To delete, click on tile</div><br></html>";

		JLabel html = new JLabel(list);
		east.add(html);
		frame.getContentPane().add(BorderLayout.EAST, east);
        frame.setSize(540, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (set == 1) player.setEnabled(true);
    }
	/*
	 * The methods to return the position of the player, walls and the opponent are given below
	 */
    public boolean getPSelect() { return pselect; }
    public boolean getWSelect() { return wselect; }
    public boolean getOSelect() { return oselect; }
    /*
     * action listener for the chest button is given below by which you can add the chest onto the map
     */
    class chestListener implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		chestselect = true;
    		System.out.println("Launch chestselect: " + chestselect);
    		dp.setChestselect(chestselect);
    		chestselect = false;
    	}
    }
    /*
     * action listener for the player button is given below by which you can add the position of the player onto the map
     */
    class playerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
     //   	if(dp.getPlayerXPos() >= 0 && dp.getPlayerYPos() >= 0)
     //   		player.setEnabled(false);
            pselect = true;
			System.out.println("Launch pselect: " + pselect);
			dp.setPSelect(pselect);
			pselect = false;
		//	dp.setPSelect(pselect);
			player.setEnabled(false);
        }
    }
    /*
     * action listener for the wall button is given below by which you can add the wall onto the map
     */
    class wallListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            wselect = true;
			System.out.println("Launch wselect: " + wselect);
			dp.setWSelect(wselect);
			wselect = false;
		//	dp.setWSelect(wselect);
        }
    }
    /*
     * action listener for the opponent button is given below by which you can add the opponent onto the map
     */
    class opponentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            oselect = true;
			System.out.println("Launch oselect: " + oselect);
			dp.setOSelect(oselect);
			oselect = false;
		//	dp.setOSelect(oselect);
        } 
    }
    /*
	 * Action listener for the reset button where you can reset the map to make changes all over again.
	 */
	class resetListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int i = dp.reset();
			if (i != 1)
				player.setEnabled(true);
		}
	}
	/*
	 * Save listener for the save button which will save the edited map.
	 */
	class saveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dp.saveMap();
			frame.dispose();
			int mc = JOptionPane.INFORMATION_MESSAGE;
			String message = "File saved: "+mapName;
			JOptionPane.showMessageDialog(null, message, "Alert", mc);
			
		 l = new Launch(charName, mapName);

		}
	}
	/*
	 * The Height and width of the map are returned in the below methods.
	 */
	public int Height()
	{
		return height;
	}
	public int Width()
	{
		return width;
	}
}

package map;

import header.MapHandler;

import java.awt.*;

import javax.swing.*;

//import DrawPanel.arrayListener;

import java.awt.event.*;


/**
 * DrawPanel class extends JPanel paints map to frame by calling the MapHandler constructor
 * DrawPanel consist of the two constructors for the new map and the existing map based on the arguments
 * method paint is to paint the map method of the mapHandler
 * methods pSelect,oSelect,wSelect which respond to the button to edit the map
 * ArrayLister class which implements the mouseListener on the map to make the change based on the button selected
 * mothed save and reset which save and reset the map to the file by calling the methods of the MapHandler
 * @author Team3
 *
 */

// The class where Maps is displayed (actually a JPanel)
public class DrawPanel extends JPanel{

	MapHandler m;// = new MapHandler("testmap.txt", 20);
	MapHandler clone;
	int i , j, val;
//	int x, y;
	boolean pSelection, oSelection, wSelection, cSelection;
	/*
	 * The DrawPanel calls the maphandler class and sends the value of the mapname along with the tilesize
	 * The parameter taken is a String
	 */
    public DrawPanel(String s) {
        m = new MapHandler(s, 20);
        addMouseListener(new arrayListener());
        clone = new MapHandler(s, 20);
    }
    /*
     * The DrawPanel calls the maphandler class and sends the value of the height, width, mapname along with the tilesize
     * The parameter taken is a String, three integers
     */
    public DrawPanel(String name, int h, int w, int tileSize) {
		m = new MapHandler(h, w, name, tileSize);
	
		addMouseListener(new arrayListener());
		clone = new MapHandler(h, w, name, tileSize);
	}
    /*
     * These methods are used for setting the position of the player, opponent, chest and the walls.
     */
	public void setPSelect(boolean i) { pSelection = i; }
	public void setOSelect(boolean i) { oSelection = i; }
	public void setWSelect(boolean i) { wSelection = i; }
	public void setChestselect(boolean i) { cSelection = i; }
	/*
	 * The saveMap method saves the map as the player edits it.
	 */
	public void saveMap() { m.save(); }
	public int reset() {
		m = clone;
		repaint();
		return 1;
		
	}
	/*
	 * These are the methods where we return the player's position
	 */
	public int getPlayerXPos() { return m.getPlayerX(); }
	public int getPlayerYPos() { return m.getPlayerY(); }
	/*
	 *The paint method which draws the map onto the frame for the player 
	 */
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        m.drawMap(g2d);

		pSelection = false;
		oSelection = false;
		wSelection = false;
    }
    /*
     * A mouse listener is implemented below where we check if the player is adding the wall or the chest item or the opponent onto 
     * the map which is being edited.
     */	
    class arrayListener implements MouseListener {
        public void mouseClicked(MouseEvent e) {
            System.out.println("x: " + e.getY() + " y: " + e.getX());
			i = e.getY() / m.getTileSize();
			j = e.getX() / m.getTileSize();
			System.out.println("pSelection: " + pSelection);
			System.out.println("oSelection: " + oSelection);
			System.out.println("wSelection: " + wSelection);
			if(pSelection)
				val = 2;
			else if (oSelection)
				val = 3;
			else if (wSelection)
				val = 0;
			else if (cSelection)
				val = 5;
			else
				val = 1;
			System.out.println("val: " + val);
			m.setMap(i, j, val);
			repaint();
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }
    }

}

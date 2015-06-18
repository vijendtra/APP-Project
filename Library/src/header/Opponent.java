package header;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Opponent class extends JPanel implements Runnable where opponent is displayed
 * on the map Opponent consist of the constructor which collects the MapHandler
 * object to known the map in which the player is placed Method getX,getY
 * provide the x and y location of the opponent on the map method rectangle will
 * draw a rectangle to represent opponent on map Method SetOpponent which
 * specifies the starting location of the opponent run which provide the random
 * values to the opponent to move randomly in the ma.
 * 
 * @author Team3
 * 
 */

public class Opponent extends JPanel {

	public int x, y;
	int wis, dex, inte, cha, str, con;
	int conmod, dexmod, strengthmod, armormod, shieldmod;
	int armorclass, attackbonus, bab;
	MapHandler mH;
	public BufferedImage img = null;
	int xTemp, yTemp, counter;
	public boolean isWall = false;
	int lvl;
	int hp;
	int typeOfFighter; // Bully = 0, Nimble = 1, Tank = 2
	int armor, shield, weapon;
	boolean isAlive;

	public int getArmorclass() {
		return armorclass;
	}

	public int getWis() {
		return wis;
	}

	public int getDex() {
		return dex;
	}

	public int getInte() {
		return inte;
	}

	public int getStr() {
		return str;
	}

	public int getCon() {
		return con;
	}

	public int getCha() {
		return cha;
	}

	public int getAttackbonus() {
		return attackbonus;
	}

	public int getWeapon() {
		return weapon;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int i) {
		hp = i;
	}

	public int getStrengthmod() {
		return strengthmod;
	}

	public void takeDamage(int d) {
		hp -= d;
		
	}

	public void setIsAlive(boolean b) {
		isAlive = b;

		if (!isAlive) {
			wis = 0;
			dex = 0;
			inte = 0;
			cha = 0;
			str = 0;
			con = 0;
			conmod = 0;
			dexmod = 0;
			strengthmod = 0;
			armormod = 0;
			shieldmod = 0;
			armorclass = 0;
			attackbonus = 0;
			bab = 0;
			armor = 0;
			weapon = 0;
			shield = 0;
		}
	}

	public void setLvl(int i) {
		lvl = i;

		switch (lvl) {
		case 1:
			bab = lvl;
			break;
		case 2:
			bab = lvl;
			break;
		case 3:
			bab = lvl;
			break;
		case 4:
			bab = lvl;
			break;
		case 5:
			bab = lvl;
			break;
		case 6:
			bab = 7;
			break;
		case 7:
			bab = 9;
			break;
		case 8:
			bab = 11;
			break;
		case 9:
			bab = 13;
			break;
		case 10:
			bab = 15;
			break;
		case 11:
			bab = 17;
			break;
		case 12:
			bab = 21;
			break;
		case 13:
			bab = 24;
			break;
		case 14:
			bab = 27;
			break;
		case 15:
			bab = 30;
			break;
		case 16:
			bab = 33;
			break;
		case 17:
			bab = 36;
			break;
		case 18:
			bab = 39;
			break;
		case 19:
			bab = 42;
			break;
		case 20:
			bab = 45;
			break;
		}

		if (weapon == 0)
			attackbonus = bab + dexmod;
		else
			attackbonus = bab + strengthmod;

	}

	public void setOpponent(int row, int col) {

		x = row;
		y = col;
		xTemp = x;
		yTemp = y;
		isAlive = true;

	}

	public void paint(Graphics g) {

		if (isAlive) {
			g.setColor(Color.red);
			g.fillRect(y * 20, x * 20, 20, 20);
		//	g.drawRect((y * 20) - 20, (x * 20) - 20, 60, 60);
		} else {
			
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 0, 0);
		}

	}

	public Rectangle bounds() {

		return (new Rectangle((y * 20) - 20, (x * 20) - 20, 60, 60));

	}

	public Opponent(MapHandler map) {
		mH = map;
		counter = 0;
		// hp = (int) (Math.ceil(Math.random() * 100));
		// hp = 1;
		
		typeOfFighter = (int) (Math.ceil(Math.random() * 2));

		double strength[] = new double[4];
		double constitution[] = new double[4];
		double dexterity[] = new double[4];
		double intelligence[] = new double[4];
		double charisma[] = new double[4];
		double wisdom[] = new double[4];

		for (int i = 0; i < 4; i++)
			strength[i] = Math.random() * 6;

		for (int i = 0; i < 4; i++)
			constitution[i] = Math.random() * 6;

		for (int i = 0; i < 4; i++)
			dexterity[i] = Math.random() * 6;

		for (int i = 0; i < 4; i++)
			intelligence[i] = Math.random() * 6;

		for (int i = 0; i < 4; i++)
			charisma[i] = Math.random() * 6;

		for (int i = 0; i < 4; i++)
			wisdom[i] = Math.random() * 6;

		Arrays.sort(strength);
		Arrays.sort(constitution);
		Arrays.sort(dexterity);
		Arrays.sort(intelligence);
		Arrays.sort(charisma);
		Arrays.sort(wisdom);

		double ability[] = new double[6];
		ability[0] = Math.ceil(strength[3] + strength[2] + strength[1]);
		ability[1] = Math.ceil(constitution[3] + constitution[2]
				+ constitution[1]);
		ability[2] = Math.ceil(dexterity[3] + dexterity[2] + dexterity[1]);
		ability[3] = Math.ceil(intelligence[3] + intelligence[2]
				+ intelligence[1]);
		ability[4] = Math.ceil(charisma[3] + charisma[2] + charisma[1]);
		ability[5] = Math.ceil(wisdom[3] + wisdom[2] + wisdom[1]);

		Arrays.sort(ability);

		if (typeOfFighter == 0) {
			str = (int) ability[5];
			con = (int) ability[4];
			dex = (int) ability[3];
			inte = (int) ability[2];
			cha = (int) ability[1];
			wis = (int) ability[0];
		}
		if (typeOfFighter == 1) {
			dex = (int) ability[5];
			con = (int) ability[4];
			str = (int) ability[3];
			inte = (int) ability[2];
			cha = (int) ability[1];
			wis = (int) ability[0];
		}
		if (typeOfFighter == 2) {
			con = ((int) ability[5]);
			dex = ((int) ability[4]);
			str = ((int) ability[3]);
			inte = ((int) ability[2]);
			cha = ((int) ability[1]);
			wis = ((int) ability[0]);
		}

		switch (dex) {
		case 0:
		case 1:
			dexmod = -5;
			break;
		case 2:
		case 3:
			dexmod = -4;
			break;
		case 4:
		case 5:
			dexmod = (-3);
			break;
		case 6:
		case 7:
			dexmod = (-2);
			break;
		case 8:
		case 9:
			dexmod = (-1);
			break;
		case 10:
		case 11:
			dexmod = (0);
			break;
		case 12:
		case 13:
			dexmod = (1);
			break;
		case 14:
		case 15:
			dexmod = (2);
			break;
		case 16:
		case 17:
			dexmod = (3);
			break;
		case 18:
			dexmod = (4);

		}

		switch (str) {
		case 0:
		case 1:
			strengthmod = (-5);
			break;
		case 2:
		case 3:
			strengthmod = (-4);
			break;
		case 4:
		case 5:
			strengthmod = (-3);
			break;
		case 6:
		case 7:
			strengthmod = (-2);
			break;
		case 8:
		case 9:
			strengthmod = (-1);
			break;
		case 10:
		case 11:
			strengthmod = (0);
			break;
		case 12:
		case 13:
			strengthmod = (1);
			break;
		case 14:
		case 15:
			strengthmod = (2);
			break;
		case 16:
		case 17:
			strengthmod = (3);
			break;
		case 18:
			strengthmod = (4);

		}

		switch (con) {
		case 0:
		case 1:
			conmod = (-5);
			break;
		case 2:
		case 3:
			conmod = (-4);
			break;
		case 4:
		case 5:
			conmod = (-3);
			break;
		case 6:
		case 7:
			conmod = (-2);
			break;
		case 8:
		case 9:
			conmod = (-1);
			break;
		case 10:
		case 11:
			conmod = (0);
			break;
		case 12:
		case 13:
			conmod = (1);
			break;
		case 14:
		case 15:
			conmod = (2);
			break;
		case 16:
		case 17:
			conmod = (3);
			break;
		case 18:
			conmod = (4);

		}

		armor = (int) (Math.ceil(Math.random() * 7));
		shield = (int) (Math.ceil(Math.random() * 2));
		weapon = (int) (Math.ceil(Math.random() * 1));

		switch (armor) {
		case 0:
			armormod = 1;
			break;
		case 1:
			armormod = 2;
			break;
		case 2:
			armormod = 3;
			break;
		case 3:
			armormod = 4;
			break;
		case 4:
			armormod = 5;
			break;
		case 5:
			armormod = 6;
			break;
		case 6:
			armormod = 7;
			break;
		case 7:
			armormod = 8;
			break;
		}

		switch (shield) {
		case 0:
			shieldmod = 1;
			break;
		case 1:
			shieldmod = 2;
			break;
		case 2:
			shieldmod = 4;
			break;
		}

		armorclass = 10 + dexmod + armormod + shieldmod;

		

	}

	public double iO() {
		double j = (Math.random() * 20) + dexmod;

		return j;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void move() {

		double j = Math.random() * 3;
		int i = (int) Math.ceil(j);
		i += 1;
		if (i == 1) {
			xTemp = xTemp + 1;

		}
		if (i == 2) {
			yTemp = yTemp + 1;
			counter++;

		}
		if (i == 3) {
			xTemp = xTemp - 1;
			counter++;

		}
		if (i == 4) {
			yTemp = yTemp - 1;
			counter++;

		}
		if (counter == 3) {
			counter = 0;
			xTemp = xTemp + 1;

		}

		isWall = mH.isBlack(xTemp, yTemp);
		/*
		 * if (isWall == true) {
		 * 
		 * xTemp = x; yTemp = y;
		 * 
		 * }
		 */
		int ran = 0;
		while (isWall) {
			ran = (int) Math.random() * 3;
			if (ran == 0) {
				xTemp++;
				yTemp++;
			} else if (ran == 1) {
				xTemp--;
				yTemp--;
			} else if (ran == 2) {
				xTemp++;
				yTemp--;
			} else if (ran == 3) {
				xTemp--;
				yTemp++;
			}
			isWall = mH.isBlack(xTemp, yTemp);
		}
		if (isWall == false) {
			x = xTemp;
			y = yTemp;

		}

	}

	public void followPlayer(int px, int py) {
		// System.out.println("Latest Player x: " + px);
		// System.out.println("Latest Player y: " + py);

		xTemp = x;
		yTemp = y;

		if (px > x && py > y) {
			xTemp = xTemp + 1;
			yTemp = yTemp + 1;
		} else if (px < x && py < y) {
			xTemp = xTemp - 1;
			yTemp = yTemp - 1;
		} else if (px < x && py > y) {
			xTemp = xTemp - 1;
			yTemp = yTemp + 1;
		} else if (px > x && py < y) {
			xTemp = xTemp + 1;
			yTemp = yTemp - 1;
		} else if (px == x && py > y) {
			yTemp = yTemp + 1;
		} else if (px == x && py < y) {
			yTemp = yTemp - 1;
		} else if (px > x && py == y) {
			xTemp = xTemp + 1;
		} else if (px < x && py == y) {
			xTemp = xTemp - 1;
		}

		isWall = mH.isBlack(xTemp, yTemp);
		if (isWall) {
			xTemp = x;
			yTemp = y;
		} else {

			// System.out.println("opponent x: " + xTemp);
			// System.out.println("opponent y: " + yTemp);
			x = xTemp;
			y = yTemp;
		}
	

		try {
			Thread.sleep(1000);
		} catch (Exception ex) {
		}

	}
	
	public boolean getIsAlive() { return isAlive; }

}

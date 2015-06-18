package race;

import header.*;

import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
/**
 * class Launch which extends panel and implements keyListener show a window with player and enemy to start the play
 * Launch constructor will initialize the MapHandler object to draw map and RaceType to draw the player
 * method paint is to draw the map and player to the frame
 * Launch implements keyListeners for the player 
 * Inner class InvAction is to handle the button actions
 * method collision1 and collision2 are to provide the collision of player and opponent
 * @param map file that provides the map
 * @param player file name that provides the player details
 * @version 1.11
 * @author Team3
 *
 */
public class Launch extends JPanel implements KeyListener, ActionListener {
	static JFrame frame;
	MapHandler m;
	RaceTypeFactory r2;
	RaceType r;
	static HashMap<String,ItemsSave> btoi=new HashMap<String,ItemsSave>();
	static Opponent o;
	Graphics gt;
	boolean collision1 = false;
	boolean collision2 = false;
	static JPanel log;
	static JLabel phitpoints, pstrength, pdexerity, pconstitution,
			pintelligence, pcharisma, pwisdom, parmorclass, pattacmod,pstrmod,pdexmod,pconmod,pintelmod,pchamod,pwismod,pshldmod,parmrmod,pwepmod,
			ohitpoints, ostrength, odexerity, oconstitution, ointelligence,
			ocharisma, owisdom, oarmorclass, oattacmod,ostrmod,odexmod,oconmod,ointelmod,ochamod,owismod,oshldmod,oarmrmod,owepmod;
	int lastpick = 0;
	static Launch l;
	int noOfMoves, dieRoll;
	int damage;
	int hp;
	JTextPane tp;

	boolean playerMove;
	boolean gotHit;

	String s;

	Chest cc;
	ArrayList<ChestItems> ci = null;
	ArrayList<ChestItems> rach = null;
	ArrayList<Items> pi = null;
	static JTextArea logger;

	static JButton inv;
	Items ti = null;
	ArrayList<Items> ii = null;
	static String player, dmap, phitpts, pstr, pdex, pcons, pchar, pwis, pint,pstrmd, pdexmd, pconsmd, pcharmd, pwismd, pintmd,pshmod,pammmod,pwwmod,
			pattmod, parmorcls, ohitpts, ostr, odex, ocons, ochar, owis, oint,ostrmd, odexmd, oconsmd, ocharmd, owismd, ointmd,oshmod,oammmod,owwmod,
			oattmod, oarmorcls;
	static JPanel inven;
	ChestItems ite;

	// String player="player stable";

	public Launch(String s, String mapname) {
		this.s = s;
		
		m = new MapHandler(mapname, 20);
		m.findExits();
		try {
			FileInputStream fin = new FileInputStream(s);
			ObjectInputStream ois = new ObjectInputStream(fin);
			r = (RaceType) ois.readObject();
			ois.close();

		} catch (Exception ev) {
			// ev.printStackTrace();
			System.out.println(ev.getMessage());
		}

		System.out.println(m.getPlayerX() + " " + m.getPlayerY());
		r.setPlayer(m.getPlayerX(), m.getPlayerY());
		o = new Opponent(m);
		o.setOpponent(m.getoX(), m.getoY());
		noOfMoves = 1;
		m.playerLvl = r.getLvl();
		o.setLvl(m.playerLvl);
		damage = 0;
		hp = 20;
		o.setHp(r.getHp()-95);

		playerMove = false;
		gotHit = false;

		ci = new ArrayList<ChestItems>();
		pi = new ArrayList<Items>();
		r.setup();
		go();
	}

	/*
	 * public Launch() { m = new MapHandler("testmap.txt", 20); m.findExits();
	 * 
	 * r2 = new RaceTypeFactory("Bully"); Items armor = new
	 * Items.Builder().hp(0).name("Leather").build(); Items weapons = new
	 * Items.Builder().hp(0).name("LongSword").build(); Items shield = new
	 * Items.Builder().hp(0).name("Buckler").build();
	 * 
	 * r = r2.setup("Vijender", true, armor, weapons, shield, "Bully", "Human");
	 * System.out.println(m.getPlayerX() + " " + m.getPlayerY());
	 * r.setPlayer(m.getPlayerX(), m.getPlayerY()); o = new Opponent(m);
	 * o.setOpponent(m.getoX(), m.getoY()); noOfMoves = 1; m.playerLvl =
	 * r.getLvl(); o.setLvl(m.playerLvl); damage = 0; hp = 10;
	 * 
	 * playerMove = false; gotHit = false;
	 * 
	 * }
	 */

	public void paint(Graphics g) {
		Graphics2D gg = (Graphics2D) g;
		m.draw(gg);
		r.paint(gg);
		o.paint(gg);
		if (collision1) {
			System.out.println("Collision1 one Done");
			logger.append("\nCollision occured ");
		}
		if (collision2) {
			System.out.println("Collision two  Done");
			logger.append("\nCollision occured ");
		}

	}

	// public static void main(String[] args) {
	public void go() {
		frame = new JFrame();

		// l = new Launch();

		frame.add(this);
		logger = new JTextArea(8, 40);
		Font f = new Font("Times New Roman", Font.BOLD, 14);
		logger.setFont(f);
		logger.setForeground(Color.WHITE);
		logger.setBackground(Color.DARK_GRAY);
		logger.setEditable(false);
		logger.setFocusable(false);
		JScrollPane s = new JScrollPane(logger);
		logger.setLineWrap(true);
		DefaultCaret c = (DefaultCaret) logger.getCaret();
		c.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		s.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		JPanel p = new JPanel();
		p.add(s);
		s.setAutoscrolls(true);
		frame.getContentPane().add(BorderLayout.SOUTH, p);

		inv = new JButton("inventory");

		inven = new JPanel();
		inven.setLayout(new BoxLayout(inven, BoxLayout.Y_AXIS));
		String list = "<html><body><table><tr><TD></td><td>Name:</td><td>"
				+ r.getName()
				+ "</td></tr>"
				+ "<tr><td></td>Race<td>"
				+ r.getRace()
				+ "</td></tr>"
				+ "<tr><td></td>Class<td>"
				+ r.getClassType()
				+ "</td></tr>"
				+ "<tr><td></td>Player color : <td>Green</td></tr>"
				+ "<tr><td></td>ChestItem color : <td>Yellow</td></tr>"
				+ "<tr><td></td>Opponent color : <td>Red</td></tr></table></body><html>";
		JLabel Details = new JLabel(list);
		inven.add(Details);
		charviewer();
		JButton jb = new JButton("Roll Dies");
		inven.add(jb);
		JLabel gap = new JLabel("  ");
		inven.add(gap);
		inven.add(inv);
		frame.getContentPane().add(BorderLayout.EAST, inven);

		// frame.add(l);

		frame.setSize(1000, 700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.requestFocusInWindow();
		// jb.addActionListener(l);
		jb.addActionListener(this);
		inv.addActionListener(new InvAction());
		jb.setFocusable(false);
		inv.setFocusable(false);

	}

	public void charviewer() {
		tp = new JTextPane();
		tp.setSize(5, 15);
		JScrollPane sc = new JScrollPane(tp);
		DefaultCaret dc = (DefaultCaret) tp.getCaret();
		dc.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		sc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		StyledDocument sd = tp.getStyledDocument();
		SimpleAttributeSet att = new SimpleAttributeSet();
		tp.setCaretPosition(tp.getDocument().getLength());

		try {
			sd.insertString(sd.getLength(), "Player View : ", att);
			logger.append("\nplayer details :");
			sd.insertString(sd.getLength(), "\nName : " + r.getName(), att);
			sd.insertString(sd.getLength(), "\nRace : " + r.getRace(), att);
			sd.insertString(sd.getLength(), "\nClass : " + r.getClassType(),
					att);

			sd.insertString(sd.getLength(), "\nStrength : ", null);
			pstr = Integer.toString(r.getStr());
			logger.append("\nName : " + r.getName());
			logger.append("\nRolled 4d6 for each ability score.");
			logger.append("\nStrength :" + pstr);

			pstrength = new JLabel(pstr);
			tp.insertComponent(pstrength);
			sd.insertString(sd.getLength(), "\nDexerity : ", null);
			pdex = Integer.toString(r.getDex());
			logger.append("\ndexerity :" + pdex);
			pdexerity = new JLabel(pdex);
			tp.insertComponent(pdexerity);
			sd.insertString(sd.getLength(), "\nConstitution : ", null);
			pcons = Integer.toString(r.getCon());
			logger.append("\nCostitution :" + pcons);
			pconstitution = new JLabel(pcons);
			tp.insertComponent(pconstitution);
			sd.insertString(sd.getLength(), "\nIntelligence : ", null);
			pint = Integer.toString(r.getInte());
			logger.append("\nIntelligence :" + pint);
			pintelligence = new JLabel(pint);
			tp.insertComponent(pintelligence);
			sd.insertString(sd.getLength(), "\nWisdom : ", null);
			pwis = Integer.toString(r.getWis());
			logger.append("\nWisdom :" + pwis);
			pwisdom = new JLabel(pwis);
			tp.insertComponent(pwisdom);
			sd.insertString(sd.getLength(), "\nCharisma : ", null);
			pchar = Integer.toString(r.getCha());
			logger.append("\nCharisma :" + pchar);
			pcharisma = new JLabel(pchar);
			tp.insertComponent(pcharisma);
			sd.insertString(sd.getLength(), "\nHitPoints : ", att);
			phitpts = Integer.toString(r.getHp());
			logger.append("\nHitPoints :" + phitpts);
			phitpoints = new JLabel(phitpts);
			tp.insertComponent(phitpoints);
			sd.insertString(sd.getLength(), "\nArmorClass : ", att);
			parmorcls = Integer.toString(r.getArmorclass());
			logger.append("\nArmorClass :" + parmorcls);
			parmorclass = new JLabel(parmorcls);
			tp.insertComponent(parmorclass);
			sd.insertString(sd.getLength(), "\nAttack Modifier : ", att);
			pattmod = Integer.toString(r.getAttackbonus());
			pattacmod = new JLabel(pattmod);
			logger.append("\nAttack Modifier :" + pattmod);
			tp.insertComponent(pattacmod);
			sd.insertString(sd.getLength(), "\nStrength Modifier : ", att);
			pstrmd = Integer.toString(r.getStrengthmod());
			pstrmod = new JLabel(pstrmd);
			logger.append("\nStrength Modifier :" + pstrmd);
			tp.insertComponent(pstrmod);
			sd.insertString(sd.getLength(), "\nDexterity Modifier : ", att);
			pdexmd = Integer.toString(r.getDexmod());
			pdexmod = new JLabel(pdexmd);
			logger.append("\nDexterity Modifier :" + pdexmd);
			tp.insertComponent(pdexmod);
			sd.insertString(sd.getLength(), "\nConstitution Modifier : ", att);
			pconsmd= Integer.toString(r.getConmod());
			pconmod = new JLabel(pconsmd);
			logger.append("\nConstitution Modifier :" + pconsmd);
			tp.insertComponent(pconmod);
			sd.insertString(sd.getLength(), "\nShield Modifier : ", att);
			pshmod= Integer.toString(r.getShieldmod());
			pshldmod = new JLabel(pshmod);
			logger.append("\nShield Modifier :" + pshmod);
			tp.insertComponent(pshldmod);
			sd.insertString(sd.getLength(), "\nArmor Modifier : ", att);
			pammmod= Integer.toString(r.getArmormod());
			parmrmod = new JLabel(pammmod);
			logger.append("\nArmor Modifier :" + pammmod);
			tp.insertComponent(parmrmod);
			sd.insertString(sd.getLength(), "\nWeapon Modifier : ", att);
			pwwmod= Integer.toString(r.getShieldmod());
			pwepmod = new JLabel(pwwmod);
			logger.append("\nWeapon Modifier :" + pwwmod);
			tp.insertComponent(pwepmod);
			

		} catch (BadLocationException e) {

			e.printStackTrace();
		}
		try {
			sd.insertString(sd.getLength(), "\n------------------------------------------------ ", null);
			sd.insertString(sd.getLength(), "\nMonster View : ", att);
			logger.append("\nMonster Details :");
			sd.insertString(sd.getLength(), "\nStrength : ", null);
			ostr = Integer.toString(o.getStr());
			logger.append("\nStrength :" + ostr);
			ostrength = new JLabel(ostr);
			tp.insertComponent(ostrength);
			sd.insertString(sd.getLength(), "\nDexerity : ", null);
			odex = Integer.toString(o.getDex());
			logger.append("\ndexerity :" + odex);
			odexerity = new JLabel(odex);
			tp.insertComponent(odexerity);
			sd.insertString(sd.getLength(), "\nConstitution : ", null);
			ocons = Integer.toString(o.getCon());
			logger.append("\nCostitution :" + ocons);
			oconstitution = new JLabel(ocons);
			tp.insertComponent(oconstitution);
			sd.insertString(sd.getLength(), "\nIntelligence : ", null);
			oint = Integer.toString(o.getInte());
			logger.append("\nIntelligence :" + oint);
			ointelligence = new JLabel(pint);
			tp.insertComponent(ointelligence);
			sd.insertString(sd.getLength(), "\nWisdom : ", null);
			owis = Integer.toString(o.getWis());
			logger.append("\nWisdom :" + owis);
			owisdom = new JLabel(owis);
			tp.insertComponent(owisdom);
			sd.insertString(sd.getLength(), "\nCharisma : ", null);
			ochar = Integer.toString(o.getCha());
			logger.append("\nCharisma :" + ochar);
			ocharisma = new JLabel(ochar);
			tp.insertComponent(ocharisma);
			sd.insertString(sd.getLength(), "\nHitPoints : ", att);
			ohitpts = Integer.toString(o.getHp());
			logger.append("\nHitPoints :" + ohitpts);
			ohitpoints = new JLabel(ohitpts);
			tp.insertComponent(ohitpoints);
			sd.insertString(sd.getLength(), "\nArmorClass : ", att);
			oarmorcls = Integer.toString(o.getArmorclass());
			oarmorclass = new JLabel(oarmorcls);
			logger.append("\nArmorClass :" + oarmorcls);
			tp.insertComponent(oarmorclass);
			sd.insertString(sd.getLength(), "\nAttack Modifier : ", att);
			oattmod = Integer.toString(o.getAttackbonus());
			oattacmod = new JLabel(oattmod);
			logger.append("\nAttack Modifier :" + oattmod);
			tp.insertComponent(oattacmod);
			sd.insertString(sd.getLength(), "\nStrength Modifier : ", att);
			ostrmd = Integer.toString(o.getStrengthmod());
			ostrmod = new JLabel(ostrmd);
			logger.append("\nStrength Modifier :" + ostrmd);
			tp.insertComponent(ostrmod);
			
		

		} catch (BadLocationException e) {

			e.printStackTrace();
		}

		tp.setEditable(false);

		inven.add(sc);
		return;
	}

	@Override
	public void keyPressed(KeyEvent e) {

		Invent inventory = null;

		int xArray = r.getX();
		int yArray = r.getY();

		if (e.getKeyCode() == KeyEvent.VK_UP) {

			xArray -= 1;

			noOfMoves++;
			logger.append("\nPlayer moved up");
			frame.repaint();
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {

			xArray += 1;

			noOfMoves++;
			logger.append("\nPlayer moved Down");
			frame.repaint();

		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {

			yArray -= 1;

			noOfMoves++;
			logger.append("\nPlayer moved left");
			frame.repaint();
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

			yArray += 1;

			noOfMoves++;
			logger.append("\nPlayer moved Right");
			frame.repaint();
		}

		if (e.getKeyCode() == 80) {
			if (m.map[xArray][yArray] != 1) {
				lastpick = m.map[xArray][yArray];
				
				 System.out.println(ite.getName());
				if(ci.size()!=0)
				{
					System.out.println("ci");
					Iterator<ChestItems> ia=ci.iterator();
					
					try{
						while(ia.hasNext())
						{
						String itenam=ite.getName();
					ChestItems a= ia.next();
					if(itenam.equals(a.getName()))
					{
						logger.append("\nPlayer already have "+ite.getName());
						
						//m.map[xArray][yArray] = 1;
					}
					else{
						ci.add(ite);
						m.map[xArray][yArray] = 1;
						logger.append("\nPlayer collected  "+ite.getName());
					}
						}
					}
				     catch(Exception eu){
				    	
				     }
					
					
				

				}
				else
				{
					//System.out.println("else");
					ci.add(ite);
					m.map[xArray][yArray] = 1;
					logger.append("\nPlayer collected  "+ite.getName());
				}

			}

			else

			{
				logger.append("\nNo things to collect");
			}
		}
		
		if (m.isExit(xArray, yArray) && !o.getIsAlive()) {
			r.update();
		}
	
		if (noOfMoves == 7) {
			frame.removeKeyListener(this);
			noOfMoves = 0;
			JOptionPane.showMessageDialog(null, "6 Moves completed");
			if (playerMove == true) {
				int x = o.getX(), y = o.getY();
				System.out.println("x o run after:"+x+"y:"+y);
				for (int i = 0; i < 6; i++) {
					if (!gotHit) {
						o.move();
						logger.requestFocusInWindow();
						if (x == o.getX() && y == o.getY())
							
							logger.append("\nOpponent is stable ");

						if (x < o.getX() && y == o.getY())
							
							logger.append("\nOpponent moved Down ");
						

						if (x > o.getX() && y == o.getY())
							
							logger.append("\nOpponent moved up ");
						

						if (x == o.getX() && y > o.getY())
							
							logger.append("\nOpponent moved right ");
						

						if (x == o.getX() && y < o.getY())
							
							logger.append("\nOpponent moved left ");
						x = o.getX();
						y = o.getY();
						try {
							Thread.sleep(500);
						} catch (Exception ex) {
						}
						

					} else {
						o.followPlayer(r.getX(), r.getY());
						if (x == o.getX() && y == o.getY()) 
							logger.append("\nOpponent is stable ");

						
						if (x < o.getX() && y == o.getY()) 
							logger.append("\nOpponent moved Down ");
							
						if (x > o.getX() && y == o.getY()) 
							logger.append("\nOpponent moved up ");
							
						if (x == o.getX() && y > o.getY()) 
							logger.append("\nOpponent moved right ");
							
						if (x == o.getX() && y < o.getY()) 
							logger.append("\nOpponent moved left ");
							x = o.getX();
							y = o.getY();

						
						
					}
					collision2();
					paintImmediately(0, 0, 1000, 700);
					try {
						Thread.sleep(500);
					} catch (Exception ex) {
					}
				}
				playerMove = false;
			}
		} else {
			// System.out.println("xArray and yArray: " + xArray + " " +
			// yArray);
			if (!m.isBlack(xArray, yArray) || m.map[xArray][yArray] == 5) {
				if (m.map[xArray][yArray] == 5) {
					ChestDirector cd = new ChestDirector();
					ChestBuilder playerchest = new PlayerChest(r.getLvl());
					cd.setChestBuilder(playerchest);
					cd.ConstructChest();
					cc = cd.getChest();
					// System.out.print(cc.armor.getName());
					int select = (int) (Math.random() * 7);
					// System.out.println(select);

					if (select == 3) {
						ite = cc.helmet;
					}
					if (select == 0) {
						ite = cc.helmet;
					}
					if (select == 6) {
						ite = cc.armor;
					}
					if (select == 10) {
						ite = cc.shield;
					}
					if (select == 8) {
						ite = cc.boots;
					}
					if (select == 4) {
						ite = cc.bracers;
					}
					if (select == 11) {
						ite = cc.swordandbow;
					}
					if (select == 1) {
						ite = cc.ring;
					}
					if (select == 2) {
						ite = cc.belt;
					}
					
					logger.append("\nPress 'p' to collect item "+ ite.getName());
				}

				r.setPlayer(xArray, yArray);

				// o.move();
				collision1();
				repaint();

			}

			else {
				logger.append("\nPlayer can't move");
				frame.repaint();
			}
		}
		m.save();
		try {
			FileOutputStream fout = new FileOutputStream(s);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(r);
			oos.close();
			System.out.println("Object Written");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	// when player attacks
	public void collision1() {

		Rectangle rectangle1 = r.bounds();
		Rectangle rectangle2 = o.bounds();

		if (rectangle1.contains(o.getY() * 20, o.getX() * 20)) {
			collision1 = true;
			int pattack = (int) (Math.ceil(Math.random() * 20))
					+ r.getAttackbonus();
			System.out.println("Player attacks: " + pattack);
			System.out.println("Opponent armorclass: " + o.getArmorclass());
			logger.append("\n1d20 is rolled and added with attack bonus of player:"
					+ pattack);

			if (pattack > o.getArmorclass() || pattack == o.getArmorclass()) {
				logger.append("\n1d20+attack bonus of player>= armor class of opponent");

				if (r.getWeapon().getName().equals("LongSword")) {
					

					damage = (int) (Math.ceil(Math.random() * 8))
							+ r.getStrengthmod();
					logger.append("\nDamage of opponent when Melee weapon is used(1d8 is rolled+Strength modifier): "
							+ damage);
				} else {
					damage = (int) (Math.ceil(Math.random() * 8));
					logger.append("\nDamage of opponent when Ranged weapon is used(1d8 is rolled):"
							+ damage);
				}
				System.out.println("damage: " + damage);
				o.takeDamage(damage);
				String hh=Integer.toString(o.getHp());
				ohitpoints.setText(hh);
				logger.append("Opponent new HitPoints :"+hh);
			}

			if (r.getHp() == 0 || r.getHp() < 0) {
				// if(hp == 0 || hp < 0){
				JOptionPane.showMessageDialog(null, "Player dead, Game Over");
				frame.dispose();
				frame.setVisible(false);
			}

			if (o.getHp() == 0 || o.getHp() < 0) {
				System.out.println("Opponent dead");
				o.setIsAlive(false);
				logger.append("\nOpponent is dead");

				// Remove Roll Dice, Player can move freely
			}
			gotHit = true;

		} else {
			collision1 = false;

			// System.out.println("Player X: " + r.getX());
			// System.out.println("Player Y: " + r.getY());
		}
	}

	// when opponent attacks
	public void collision2() {

		Rectangle rectangle1 = r.bounds();
		Rectangle rectangle2 = o.bounds();
		int oattack;

		if (rectangle2.contains(r.getY() * 20, r.getX() * 20)) {
			collision2 = true;
			if(o.getIsAlive()) {
				oattack = (int) (Math.ceil(Math.random() * 20))
					+ o.getAttackbonus();
				System.out.println("Opponent attacks: " + oattack);
				System.out.println("Player armorclass: " + r.getArmorclass());
				logger.append("\n1d20 is rolled and added with attack bonus of opponent: "
					+ oattack);
			} else { oattack = 0; }
			if (oattack > r.getArmorclass() || oattack == r.getArmorclass()) {
				logger.append("\n1d20+attack bonus of opponent>= armor class of player");

				if (o.getWeapon() == 1) {
					damage = (int) (Math.ceil(Math.random() * 8))
							+ o.getStrengthmod();
					logger.append("\nDamage of player when Melee weapon is used(1d8 is rolled+Strength modifier): "
							+ damage);

				} else {
					damage = (int) (Math.ceil(Math.random() * 8));
					logger.append("\nDamage of player when Ranged weapon is used(1d8 is rolled):"
							+ damage);

				}
				System.out.println("damage: " + damage);
				r.takeDamage(damage);
				String hh=Integer.toString(r.getHp());
				phitpoints.setText(hh);
				logger.append("Player new HitPoints :"+hh);
			}

			if (r.getHp() == 0 || r.getHp() < 0) {
				// if(hp == 0 || hp < 0){
				JOptionPane.showMessageDialog(null, "Player dead, Game Over");
				frame.dispose();
				frame.setVisible(false);
			}

			if (o.getHp() == 0 || o.getHp() < 0) {
				System.out.println("Opponent dead");
				o.setIsAlive(false);
				logger.append("\nOpponent is dead");

				// Remove Roll Dice, Player can move freely
			}
			gotHit = true;

		} else {
			collision2 = false;

			// System.out.println("Player X: " + r.getX());
			// System.out.println("Player Y: " + r.getY());
		}
	}

	public void actionPerformed(ActionEvent arg0) {

		double p = r.iR();
		double q = o.iO();
		DecimalFormat df = new DecimalFormat("#.##");
		logger.append("\nDies rolled 1D20 + dexterity modifier of player is: "
				+ df.format(p));
		logger.append("\nDies rolled 1D20 + dexterity modifier of opponent is: "
				+ df.format(q));
		System.out.println(p);
		System.out.println(q);
		if (p > q) {

			noOfMoves = 0;
			playerMove = true;
			frame.requestFocusInWindow();

			// frame.addKeyListener(l);
			frame.addKeyListener(this);

		} else {
			int x = o.getX(), y = o.getY();
			System.out.println("xin loop when o run first:"+x+","+"Y in loop:"+y);
			for (int i = 0; i < 6; i++) {
				if (!gotHit) {
					o.move();
					logger.requestFocusInWindow();
					if (x == o.getX() && y == o.getY())
						
						logger.append("\nOpponent is stable ");

					if (x < o.getX() && y == o.getY())
						
						logger.append("\nOpponent moved Down ");

					if (x > o.getX() && y == o.getY())

						logger.append("\nOpponent moved up ");

					if (x == o.getX() && y > o.getY())

						logger.append("\nOpponent moved right ");

					if (x == o.getX() && y < o.getY())

						logger.append("\nOpponent moved left ");
					x = o.getX();
					y = o.getY();
					try {
						Thread.sleep(500);
					} catch (Exception ex) {
					}

				} else {
					o.followPlayer(r.getX(), r.getY());
					if (x == o.getX() && y == o.getY()) 
						logger.append("\nOpponent is stable ");

					
					if (x < o.getX() && y == o.getY()) 
						logger.append("\nOpponent moved Down ");
						
					
					if (x > o.getX() && y == o.getY()) 
						logger.append("\nOpponent moved up ");
						
					if (x == o.getX() && y > o.getY())
						logger.append("\nOpponent moved right ");
						
					if (x == o.getX() && y < o.getY()) 
						logger.append("\nOpponent moved left ");
					x = o.getX();
					y = o.getY();

					
				}
				collision2();
				paintImmediately(0, 0, 1000, 700);
				try {
					Thread.sleep(500);
				} catch (Exception ex) {
				}
			}
			frame.requestFocusInWindow();

			// frame.addKeyListener(l);
			frame.addKeyListener(this);
			repaint();

		}
	}

	public class InvAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			Invent inventory = new Invent(r, ci, pi, s);

		}
		
	}
	

}

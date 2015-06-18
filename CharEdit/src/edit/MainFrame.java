package edit;

import header.*;

import javax.swing.*;

import map.Launcher;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
/**
 * The MainFrame class displays a frame where the user can choose the characteristics of the player or the opponent
 * Such as the Race type which has options like Human, Elves, Dwarves and all.
 * Even the Class type which has options such as Bully, Nimble, Tank
 * Also Armor and Weapon and shield. These are also of various types 
 * @author team3
 */
// The frame for creating, editing and saving character
public class MainFrame {

	FileHandler t;
	RaceTypeFactory r2;
	RaceType r;
	int option;
	String[] rtype, ctype, shield, weaps, ran, stype;
	JFrame frame;
	JTextField nameField;
	JRadioButton male, female;
	JComboBox raceCombo, classCombo, weapCombo, sCombo, shieldCombo;
	/*
	 * The MainFrame constructor is written below which takes the racetype object as a parameter
	 * The options for the player is displayed in this frame such his weapon type, shield type, race type and all
	 * The user can choose these characteristics and or set them to default. He can enter his name or choose the default 
	 * option again. He can save the player or select the default player and continue playing.
	 */
	public MainFrame(RaceType r3) {

		r = r3;

		String[] comp = new String[] { "Human", "Elves", "Dwarves", "Halflings" };
		String name = r.getRace();
		
		for (String val : comp) {
			if (val.equals(name)) {
				option = 1;
				break;
			} else
				option = 0;
		}
		
		ctype = new String[] { "Bully", "Nimble", "Tank" };
		shield = new String[] { "Padded", "Leather", "Studded Leather",
				"Chain Shirt", "Breast Plate", "Banded Mail", "Half Plate",
				"Full Plate" };
		weaps = new String[] { "LongBow", "LongSword" };
		stype = new String[] { "Buckler", "Heavy Shield", "Tower Shield" };
		ran = new String[] { "Akuto", "William", "Lorenzo" };
		if (option == 1) {
			// Player is selected
			rtype = new String[] { "Human", "Elves", "Dwarves", "Halflings" };
		} else {
			// Opponent is selected
			rtype = new String[] { "Ghoul", "Medusa", "Mummy", "Serpent" };
		}
		setup();
	}
	/*
	 * The MainFrame constructor is written below which takes the choice variable as a parameter
	 * The options for the player is displayed in this frame such his weapon type, shield type, race type and all
	 * The user can choose these characteristics and or set them to default. He can enter his name or choose the default 
	 * option again. He can save the player or select the default player and continue playing.
	 */
	public MainFrame(int choice) {

		option = choice;
		
		ctype = new String[] { "Bully", "Nimble", "Tank" };
		shield = new String[] { "Padded", "Leather", "Studded Leather",
				"Chain Shirt", "Breast Plate", "Banded Mail", "Half Plate",
				"Full Plate" };
		weaps = new String[] { "LongBow", "LongSword" };
		stype = new String[] { "Buckler", "Heavy Shield", "Tower Shield" };
		ran = new String[] { "Akuto", "William", "Lorenzo" };
		if (option == 1) {
			// Player is selected
			rtype = new String[] { "Human", "Elves", "Dwarves", "Halflings" };
		} else {
			// Opponent is selected
			rtype = new String[] { "Ghoul", "Medusa", "Mummy", "Serpent" };
		}
		setup();
	}
	/*
	 * The fix method will take the options that the user selects and checks which value is selected
	 * and sets the value to the racetype that is the player.
	 * If male or female is selected in the gender category or which class type is selected is checked
	 * and those values are captured and passed to the racetype.
	 */
	public void fix() {
		if (r != null) {
			String cname = r.getName().replace(".xml", "");
			nameField.setText(cname);
			if (r.isGender())
				male.setSelected(true);
			else
				female.setSelected(true);
			// Race
			int counter = 0;
			String val;
			for (int i = 0; i < rtype.length; i++) {
				val = rtype[i];
				if (val.equals(r.getRace()))
					counter = i;
			}
			raceCombo.setSelectedIndex(counter);
			// Class
			for (int i = 0; i < ctype.length; i++) {
				val = ctype[i];
				if (val.equals(r.getClassType()))
					counter = i;
			}
			classCombo.setSelectedIndex(counter);
			// Shield
			for (int i = 0; i < shield.length; i++) {
				val = shield[i];
				if (val.equals(r.getArmor().getName()))
					counter = i;
			}
			sCombo.setSelectedIndex(counter);
			// Weapon
			for (int i = 0; i < weaps.length; i++) {
				val = weaps[i];
				if (val.equals(r.getWeapon().getName()))
					counter = i;
			}
			weapCombo.setSelectedIndex(counter);
			// Shield
			for (int i = 0; i < stype.length; i++) {
				val = stype[i];
				if (val.equals(r.getShield().getName()))
					counter = i;
			}
			shieldCombo.setSelectedIndex(counter);
		}

	}

	/*
	 * The setup is the frame where we have the name field and the radio buttons for the gender selection
	 * and the drop boxes for the class type and race type and the weapon type and shield type 
	 * and the remaining options.
	 * The buttons are also embedded here named edit save default
	 */
	public void setup() {
		frame = new JFrame();
		// Name and Text Field
		JPanel north = new JPanel();
		JLabel name = new JLabel("Name: ");
		nameField = new JTextField(11);
		north.add(name);
		north.add(nameField);
		frame.getContentPane().add(BorderLayout.NORTH, north);

		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		// Radio Buttons for gender
		JPanel c1 = new JPanel();
		male = new JRadioButton("Male");
		female = new JRadioButton("Female");
		ButtonGroup b1 = new ButtonGroup();
		b1.add(male);
		b1.add(female);
		c1.add(male);
		c1.add(female);
		center.add(c1);

		// ComboBox for Race, Class, Weapons and Armor
		// Race
		JPanel c2 = new JPanel();
		c2.setLayout(new BoxLayout(c2, BoxLayout.Y_AXIS));
		JPanel c2a = new JPanel();
		JLabel race = new JLabel("Race: ");
		DefaultComboBoxModel r1 = new DefaultComboBoxModel();
		for (String ract : rtype)
			r1.addElement(ract);
		raceCombo = new JComboBox(r1);
		JScrollPane raceScroll = new JScrollPane(raceCombo);
		c2a.add(race);
		c2a.add(raceScroll);

		// Class
		JPanel c2b = new JPanel();
		JLabel clas = new JLabel("Class: ");
		DefaultComboBoxModel r2 = new DefaultComboBoxModel();
		for (String cltype : ctype)
			r2.addElement(cltype);
		classCombo = new JComboBox(r2);
		JScrollPane classScroll = new JScrollPane(classCombo);
		c2b.add(clas);
		c2b.add(classScroll);

		// Weapons
		JPanel c2c = new JPanel();
		JLabel w = new JLabel("Weapons: ");
		DefaultComboBoxModel r3 = new DefaultComboBoxModel();
		for (String weaptype : weaps)
			r3.addElement(weaptype);
		weapCombo = new JComboBox(r3);
		JScrollPane weapScroll = new JScrollPane(weapCombo);
		c2c.add(w);
		c2c.add(weapScroll);

		// Armor
		JPanel c2d = new JPanel();
		JLabel s = new JLabel("Armor: ");
		DefaultComboBoxModel r4 = new DefaultComboBoxModel();
		for (String styp : shield)
			r4.addElement(styp);
		sCombo = new JComboBox(r4);
		JScrollPane sScroll = new JScrollPane(sCombo);
		c2d.add(s);
		c2d.add(sScroll);

		// Shield
		JPanel c2e = new JPanel();
		JLabel st = new JLabel("Shield: ");
		DefaultComboBoxModel r5 = new DefaultComboBoxModel();
		for (String sh : stype)
			r5.addElement(sh);
		shieldCombo = new JComboBox(r5);
		JScrollPane shieldScroll = new JScrollPane(shieldCombo);
		c2e.add(st);
		c2e.add(shieldScroll);

		c2.add(c2a);
		c2.add(c2b);
		c2.add(c2c);
		c2.add(c2d);
		c2.add(c2e);

		// center.add(c1);
		center.add(c2);
		frame.getContentPane().add(BorderLayout.CENTER, center);

		// Buttons
		JPanel south = new JPanel();
		JButton edit = new JButton("Edit");
		JButton save = new JButton("Save");
		JButton def = new JButton("Default");
		south.add(edit);
		south.add(save);
		south.add(def);
		edit.addActionListener(new editListener());
		save.addActionListener(new saveListener());
		def.addActionListener(new defListener());
		frame.getContentPane().add(BorderLayout.SOUTH, south);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 500);
		frame.setVisible(true);
		// fix();
	}

	// Class for edit button. Calls FileHanlder
	class editListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			t = new FileHandler();
			frame.setVisible(false);
			frame.dispose();
		}
	}

	/*
	 * Save button handler. Records all information and saves it in a file
	 *  specified by Name JTextField
	 */
	class saveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String name = nameField.getText();
			boolean gender = male.isSelected() ? true : false;
			String race = (String) raceCombo.getItemAt(raceCombo
					.getSelectedIndex());
			String cl = (String) classCombo.getItemAt(classCombo
					.getSelectedIndex());
			String wea = (String) weapCombo.getItemAt(weapCombo
					.getSelectedIndex());
			Items w = new Items.Builder().hp(0).name(wea).build();
			String s = (String) sCombo.getItemAt(sCombo.getSelectedIndex());
			Items sh = new Items.Builder().hp(0).name(s).build();
			String shield = (String) shieldCombo.getItemAt(shieldCombo
					.getSelectedIndex());
			Items shi = new Items.Builder().hp(0).name(shield).build();
			// d2.makeRaceType(name, gender, sh, w, shi, cl, race);
			// r = d2.getRaceType();
			r2 = new RaceTypeFactory(name);
			r = r2.setup(name, gender, sh, w, shi, cl, race);
			

			try {
				FileOutputStream fout = new FileOutputStream(
						name.concat(".xml"));
				ObjectOutputStream oos = new ObjectOutputStream(fout);
				oos.writeObject(r);
				oos.close();
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			frame.dispose();

			int mc = JOptionPane.INFORMATION_MESSAGE;
			String message1 = name.concat(".xml");
			String message2 = "File saved: ";
			String message3= "\n Strength:\t"+Integer.toString(r.getStr());
			String message4= "\n Dexterity:\t"+Integer.toString(r.getDex());
			String message5= "\n Constitution:\t"+Integer.toString(r.getCon());
			String message6= "\n Intelligence:\t"+Integer.toString(r.getInte());
			String message7= "\n Charisma:\t"+Integer.toString(r.getCha());
			String message8= "\n Wisdom:\t"+Integer.toString(r.getWis());
			String message9= "\n Hit points:\t"+Integer.toString(r.getHp());
			
			String message = message2 +message1+message3+message4+message5+message6+message7+message8+message9;
		
			JOptionPane.showMessageDialog(null, message, "Alert", mc);

			String charfile = name.concat(".xml");
			EditNew en = new EditNew(charfile);
		}
	}

	/*
	 * Default button listener. When clicks changes all to random, but fixes
	class to Fighter
	 */
	class defListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int idx = new Random().nextInt(ran.length);
			nameField.setText(ran[idx]);
			idx = new Random().nextInt(2) - 1;
			if (idx == 1)
				male.setSelected(true);
			else
				female.setSelected(true);

			idx = new Random().nextInt(rtype.length);
			raceCombo.setSelectedIndex(idx);

			classCombo.setSelectedIndex(1);

			idx = new Random().nextInt(weaps.length);
			weapCombo.setSelectedIndex(idx);

			idx = new Random().nextInt(shield.length);
			sCombo.setSelectedIndex(idx);
		}
	}

}

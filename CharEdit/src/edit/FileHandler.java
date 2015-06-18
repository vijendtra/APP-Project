package edit;

import header.Items;
import header.RaceType;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * FileHandler class lists list of the files of player to select and edit
 * FileHandler constructor consist of the frame with lists consisting set of the
 * files and button load to file to edit Inner class LoadActionListener
 * implements action to load file and call the MainFrame class to edit the
 * player method action performed calls the predefined events when button
 * pressed and class MainFrame object by passing the RaceType object
 * 
 * @param RaceType
 *            r passed with player details to MainFrame
 * @author Team3
 * 
 */
// Displays file name and allows for choosing the character to be edited
public class FileHandler {

	public static String rName = null, rName1 = null, rClass = null;
	MainFrame t3;
	RaceType r3;
	JFrame frame;
	JScrollPane listScroll;
	public static Items weaponName;
	JList list;
	public static String fName;
	public static Items Shield, armor;

	// Frame is created here and the list is loaded with file names
	/*
	 * FileHandler constructor is given below where a frame is created.
	 * A list is displayed in the frame of the file names.
	 * A load button is also embedded in the frame which will take the file name and 
	 * loads the the file selected.
	 */
	public FileHandler() {
		frame = new JFrame();
		JPanel south = new JPanel();
		JLabel name = new JLabel("Select file");
		DefaultListModel listModel = new DefaultListModel();
		list = new JList(listModel);
		listScroll = new JScrollPane(list);
		frame.getContentPane().add(BorderLayout.NORTH, name);
		// String workDir = System.getProperty("user.dir");
		// File dir = new File(workDir);
		File dir = new File(".");
		File[] flist = dir.listFiles();
		String fileName;
		for (File f : flist) {
			fileName = f.getName();
			if (f.isFile())
				if (fileName.contains(".xml"))
					listModel.addElement(f);
		}
		frame.getContentPane().add(BorderLayout.CENTER, listScroll);
		JButton load = new JButton("Load");
		south.add(load);
		load.addActionListener(new loadActionListener());
		frame.getContentPane().add(BorderLayout.SOUTH, south);
		frame.setSize(250, 250);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	/*
	 * An action listener for the load button is written below which takes the file name of the file selected
	 * and reads the object based on the file name using the fileinputstream and passes it to the mainframe 
	 * and the frame is closed.
	 */
	class loadActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			String fileName = list.getSelectedValue().toString();
			fName = fileName.replace(".\\", "");
			String cFile = fName.replace(".xml", "");
			
			try {
				FileInputStream fin = new FileInputStream(fName);
				ObjectInputStream ois = new ObjectInputStream(fin);
				r3 = (RaceType) ois.readObject();
				ois.close();
				weaponName = r3.getWeapon();
				Shield = r3.getShield();
			} catch (Exception ev) {
				// ev.printStackTrace();
				
			}
			
			t3 = new MainFrame(r3);
			frame.setVisible(false);
			frame.dispose();
		}
	}

	public String FileName() {
		return fName;
	}

	public Items WeaponName() {
		return weaponName;
	}

	public String RaceName() {

		return rName;
	}

	public String RaceNames() {

		return rName1;
	}

	public String RaceClass() {
		// TODO Auto-generated method stub
		return null;
	}

	public Items ShieldName() {
		// TODO Auto-generated method stub
		return Shield;
	}

	public Items ArmorName() {
		// TODO Auto-generated method stub
		return armor;
	}

}

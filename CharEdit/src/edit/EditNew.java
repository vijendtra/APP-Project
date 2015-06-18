package edit;

import header.RaceType;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import map.Launcher;

import edit.FileHandler.loadActionListener;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
/**
 * The EditNew class gives the player two options using two buttons saying Edit Map and New Map.
 * When the player chooses New Map then, a New Map is generated with size based on the user's choice.
 * If the player chooses Edit Map by choosing a previously existing map, then the user can edit the map by adding the treasure
 * and monsters and walls. He can even change the player's position.
 * 
 */
public class EditNew {

	MainFrame t3;
	RaceType r3;
	JFrame frame;
	JScrollPane listScroll;
	JList list;
	public String name;
	String cFile;

	// Frame is created here and the list is loaded with file names
	/*
	 * EditNew Constructor displays a frame which has the list of file names
	 * Two buttons are also in the frame named Edit map and New map
	 * The functionalities of the two buttons are as the names are given
	 * 
	 */
	public EditNew(String charfile) {
		cFile = charfile;
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
		String mapName;
		for (File f : flist) {
			mapName = f.getName();
			if (f.isFile())
				if (mapName.contains(".txt"))
					listModel.addElement(f);
		}
		list.addListSelectionListener(new listSelect());
		frame.getContentPane().add(BorderLayout.CENTER, listScroll);
		JButton edit = new JButton("Edit Map");
		south.add(edit);
		JButton newMap = new JButton("New Map");
		south.add(newMap);
		edit.addActionListener(new editActionListener());
		newMap.addActionListener(new newMapActionListener());
		frame.getContentPane().add(BorderLayout.SOUTH, south);
		frame.setSize(250, 250);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	// Action Listener for load button. Reads the object, creates a RaceType
	// object and passes it to MainFrame
	/*
	 * Action Listener is made for the edit button. 
	 * It reads the name and sends it to the Launcher
	 * 
	 */
	class editActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
			frame.dispose();
			Launcher l = new Launcher(name, cFile);
			l.go();

		}
	}
	/*
	 * An action listener for the newMap is written here.
	 * It asks the user for the width and the height of the map
	 * Takes the width and the height of the map from the user and saves it and passes it to the launcher class
	 * along with the file name of the name which is again entered by the user.
	 */
	class newMapActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			frame.setVisible(false);
			frame.dispose();

			String filename, h, wi;
			

			filename = JOptionPane.showInputDialog("Enter name");
			h = JOptionPane.showInputDialog("Enter width");
			wi = JOptionPane.showInputDialog("Enter height");
			
			int height = 0, width = 0;
			try {
			height = Integer.parseInt(h);
			width = Integer.parseInt(wi);
			} catch (Exception ex) { JOptionPane.showMessageDialog(null, "You missed a value, Please try again"); System.exit(1); }

			frame.setVisible(false);
			frame.dispose();

			Launcher l = new Launcher(filename, height, width, 20, cFile);

		}
	}
	/*
	 * A list selection listener for the list select is written here.
	 * This action listener takes the selected value from the list and converts it to a String value
	 * 
	 */
	
	class listSelect implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent ev) {
			if (!ev.getValueIsAdjusting()) {
				
				name = list.getSelectedValue().toString();
				name = name.substring(2);
				
			}
		}
	}
}

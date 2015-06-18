package ui;

import race.*;
import header.MapHandler;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.event.*;

import race.Launch;
// Shows file list for loading maps and starting the game.
/**
 * Filelist class displays the file list where we can select the character file or the map file 
 * which are already existing.
 * @author team3
 */
public class FileList {

	JFrame frame;
	JScrollPane listScroll;
	JList list;
	MapHandler map;
	String name;
	String mapName;
	boolean mapHandle;
	Launch l;
	// Game game;
	/*
	 * Below is the constructor of the Filelist where the frame is displayed 
	 * which has a list of file names from where we can choose the character file from the existing 
	 * character files.
	 */
	public FileList() {
		frame = new JFrame();
		JPanel south = new JPanel();
		JLabel name = new JLabel("Select Character");
		DefaultListModel listModel = new DefaultListModel();
		list = new JList(listModel);
		listScroll = new JScrollPane(list);
		frame.getContentPane().add(BorderLayout.NORTH, name);
		// String workDir = System.getProperty("user.dir");
		// File dir = new File(workDir);
		File dir = new File(".");
		File[] flist = dir.listFiles();
		String charName;
		for (File f : flist) {
			charName = f.getName();
			if (f.isFile())
				if (charName.contains(".xml"))
					listModel.addElement(f);
		}
		list.addListSelectionListener(new listSelect());
		frame.getContentPane().add(BorderLayout.CENTER, listScroll);
		JButton next = new JButton("Next");
		south.add(next);
		next.addActionListener(new nextListener());
		frame.getContentPane().add(BorderLayout.SOUTH, south);
		frame.setSize(350, 350);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		mapHandle = false;
	}
	/*
	 * Below is the constructor of the Filelist where the frame is displayed 
	 * which has a list of file names from where we can choose the map file from the existing 
	 * map files.
	 * parameters: String name
	 */
	public FileList(String name) {
		this.name = name;
		mapHandle = true;
		frame = new JFrame();
		JPanel south = new JPanel();
		JLabel nameMap = new JLabel("Select Map");
		DefaultListModel listModel = new DefaultListModel();
		list = new JList(listModel);
		listScroll = new JScrollPane(list);
		frame.getContentPane().add(BorderLayout.NORTH, nameMap);
		// String workDir = System.getProperty("user.dir");
		// File dir = new File(workDir);
		File dir = new File(".");
		File[] flist = dir.listFiles();
		String mName;
		for (File f : flist) {
			mName = f.getName();
			if (f.isFile())
				if (mName.contains(".txt"))
					listModel.addElement(f);
		}
		list.addListSelectionListener(new listSelect());
		frame.getContentPane().add(BorderLayout.CENTER, listScroll);
		JButton next = new JButton("Next");
		south.add(next);
		next.addActionListener(new nextListener());
		frame.getContentPane().add(BorderLayout.SOUTH, south);
		frame.setSize(350, 350);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	/*
	 * Action listener for the next button which will pass the file name to the filelist if the character is selected
	 * else the mapname is passed to the launch if the mapname is choosen
	 */
	class nextListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
			frame.dispose();
			FileList fl;
			if(mapHandle == false)
				fl = new FileList(name);
			else
				l = new Launch(name, mapName);
			
		}
	}
	/*
	 * List selection listener for the selected items in the list is written below where the string of the selected item is 
	 * captured and adjusted.
	 */
	class listSelect implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent ev) {
			if (!ev.getValueIsAdjusting()) {
				
				if(mapHandle == false) {
					name = list.getSelectedValue().toString();
					name = name.substring(2);

				} else {
					mapName = list.getSelectedValue().toString();
					mapName = mapName.substring(2);

				}
				
			
			}
		}
	}
}

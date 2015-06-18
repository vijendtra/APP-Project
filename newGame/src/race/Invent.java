package race;



import header.ChestItems;
import header.Items;
import header.Items.Builder;
import header.RaceType;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;

import javax.swing.event.*;

import race.ItemsSave;

/**
 * Invent class provides the player inventory
 * Invent constructor display the list of the items player consist of and provide buttons to drop items
 * method savePlayer will save the player details into RaceType object and to the file after modification
 * method readPlayer will read the contents of the file to the RaceType object
 * inner class dropActionListener will implement the actionEvents of button
 * inner class ListSelect to implement the listSelectionEvents of lists
 * @param player file name to read and save player details to and form file
 * @author Team3
 *
 */
	public class Invent implements Serializable {
		String pnm,prac,pcl;
		JFrame frame;
		JScrollPane listScroll;
		JList list;
		HashMap<String,ItemsSave> hashm;
		String name,player,select,selectedinv;
		RaceType r;
		Items pwn,parm,pshd;
		ArrayList<ChestItems>  ci;
		ArrayList<Items> pi;
		ArrayList<Items> ii;
		ArrayList<ChestItems>  cis;
		/*
		 * The player is saved in a file by using the saveplayer method
		 */
		public  void savePlayer(String pl)
		{
			try {
				FileOutputStream fout = new FileOutputStream(pl);
				ObjectOutputStream oos = new ObjectOutputStream(fout);
				oos.writeObject(r);
				oos.close();
				System.out.println("Object Written");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
		}
		/*
		 * The player is read from a file by this method
		 */
		public void readPlayer(String pl)
		{
			try {
				FileInputStream fin = new FileInputStream(pl);
				ObjectInputStream ois = new ObjectInputStream(fin);
				r = (RaceType) ois.readObject();
				ois.close();
			} catch (Exception ev) {
				System.out.println(ev.getMessage());
			}
			return;
		}
		
		public void Inventa(HashMap a)
		{
			//System.out.println("got hashmap"+a);
			hashm=a; 
			
		}
		/*
		 * The constructor of the Invent class is written below which takes the race type, chest item array list, items array list
		 * and the player's name as the parameters
		 */
		public Invent(RaceType ra,ArrayList<ChestItems> cii,ArrayList<Items> i,String playe) {
			player=playe;
			
			//readPlayer(player);
			r=ra;
			this.ci=cii;
			this.pi=i;
			pnm=r.getName();
			prac=r.getRace();
			pcl=r.getClassType();
			pwn=r.getWeapon();
			parm=r.getArmor();
			pshd=r.getShield();
			frame = new JFrame();
			JPanel south = new JPanel();
			JPanel play =new JPanel();
			play.setLayout(new BoxLayout(play,BoxLayout.Y_AXIS));
			JLabel pname = new JLabel("Name :"+r.getName());
			JLabel prace=new JLabel("Race :" +r.getRace());
			JLabel pclass=new JLabel("Class"+r.getClassType());
			JLabel name = new JLabel("Select Item");
			play.add(pname);
			play.add(prace);
			play.add(pclass);
			play.add(name);
			Hashtable<String,Integer> map=new Hashtable<String,Integer>();
			if(r.getArmor()!=null)
			map.put(r.getArmor().getName(), 1);
			if(r.getShield()!=null)
				map.put(r.getShield().getName(), 1);
			if(r.getWeapon()!=null)
				map.put(r.getWeapon().getName(), 1);
			if(r.getInventory()!=null)
			{
				//System.out.println("inventory"+r.getInventory());
				ii=r.getInventory();
				for(Items pitem:ii)
				{
					if(pitem.getName()!=null)
					{
						selectedinv= pitem.getName();
						map.put(pitem.getName(), 1);
				}
				}
			}
			
			if(r.getChestItems()!=null)
			{
				//System.out.println("inventory"+r.getInventory());
				cis=r.getChestItems();
				for(ChestItems pitem:cis)
				{
					if(pitem.getName()!=null)
					{
						
						map.put(pitem.getName(), 1);
						
				}
				}
			}
			
		
			list = new JList(map.keySet().toArray());
			listScroll = new JScrollPane(list);
			frame.getContentPane().add(BorderLayout.NORTH,play);
			//frame.getContentPane().add(BorderLayout.NORTH, name);
			list.addListSelectionListener(new listSelect());
			
			frame.getContentPane().add(BorderLayout.CENTER, listScroll);
			//JButton drop = new JButton("drop");
			JButton backpack = new JButton("backpack");
			JButton unequip = new JButton("unequip");
			//south.add(drop);
			south.add(backpack);
			south.add(unequip);
			backpack.addActionListener(new equipListener());
			//backpack.addActionListener(new equipListener());
			unequip.addActionListener(new unequipListener());
			frame.getContentPane().add(BorderLayout.SOUTH, south);
			frame.setSize(350, 350);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		}
		
		
		
		/*
		 * Action listener for the equip button which will check which item is selected in from the list of items and
		 * equips it and stores it in the bagpack
		 */
		class equipListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				BackPack bp= new BackPack(player,r,ci,pi);
				frame.setVisible(false);
				frame.dispose();
						
			}
		}
		/*
		 * Action listener for the unequip button which will check which item is selected in from the list of items and
		 * unequips it and stores it in the backpack class
		 */
		class unequipListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				
				String[] weaps = new String[]{"LongBow", "LongSword"};
				String[] armor = new String[]{"Padded","Leather", "Studded Leather", "Chaio Shirt", "Breastplate", "Banded Mail", "Half Plate", "Full Plate"};
				String[] shld = new String[]{"Buckler", "Heavy Shield", "Tower Shield"};
				String[] chesti = new String[]{"Helmet", "Padded","Leather", "Studded Leather", "Chaio Shirt", "Breastplate", "Banded Mail", "Half Plate", "Full Plate", "Belt","Boots", "Bracers", "Ring","LongBow", "LongSword","Buckler", "Heavy Shield", "Tower Shield"};
				
				
				for(String ss:armor)
				{								
					if(select.equals(ss))
					{		
						pi.add(r.getArmor());
						r.setArmor(null);
						Launch.parmrmod.setText("0");
						
						savePlayer(player);
						Launch.logger.append("\nPlayer released shield and Armor modifier is made zero");
						frame.setVisible(false);	
						frame.dispose();
					}
				}
				for(String ss:weaps)
				{								
					if(select.equals(ss))
					{						
						pi.add(r.getWeapon());
						r.setWeapon(null);
						Launch.pwepmod.setText("0");
						savePlayer(player);
						Launch.logger.append("\nPlayer released shield and Weapon modifier is made zero");
						frame.setVisible(false);	
						frame.dispose();
					}
				}
				for(String ss:shld)
				{							
					if(select.equals(ss))
					{						
						pi.add(r.getShield());
						Launch.pshldmod.setText("0");
						r.setShield(null);
						savePlayer(player);
						Launch.logger.append("\nPlayer released shield and Shield modifier is made zero");
						frame.setVisible(false);	
						frame.dispose();
					}
				}
				for(String ss:chesti)
				{
					if(select.equals(ss))
					{
						Iterator<ChestItems> ia=r.getChestItems().iterator();
						while(ia.hasNext()) 
						{
							ChestItems a= ia.next();
							
							if(select.equals(a.getName()))
							{
								if(a.getName().equals("LongBow")||a.getName().equals("LongSword")||a.getName().equals("Buckler")||a.getName().equals("Heavy Shield")||a.getName().equals("Tower Shield")||a.getName().equals("Padded")||a.getName().equals("Leather")||a.getName().equals("Studded Leather")||a.getName().equals("Chaio Shirt"))
								{
									ci.add(a);
									ia.remove();
								}
								else{
									
								if(Launch.btoi.size()==0)
								{
									ci.add(a);
									ia.remove();
								}
									
								else
								{
								Iterator hmi=Launch.btoi.keySet().iterator();
								while(hmi.hasNext())
								{
								String nofi=(String)hmi.next();
								 
								if(nofi.equals(a.getName()))
								{
									Launch.logger.append("\nPlayer released "+nofi);
									ItemsSave isa=null;
									isa=Launch.btoi.get(nofi);
									String ch=isa.change;
									 
									if(ch.equals("armorclass"))
									{
										int arm=r.getArmorclass();
										int qt=isa.qty;
										arm=arm-qt;
										r.setArmorclass(arm);
										 Launch.logger.append("\nPlayer ArmorClass decreased by"+qt);
										Launch.parmorclass.setText(Integer.toString(arm));
									}
									if(ch.equals("constitution"))
									{
										int arm=r.getCon();
										int qt=isa.qty;
										arm=arm-qt;
										r.setCon(arm);
										 Launch.logger.append("\nPlayer constitution decreased by"+qt);
										Launch.pconstitution.setText(Integer.toString(arm));
									}
									if(ch.equals("strength"))
									{
										int arm=r.getStr();
										int qt=isa.qty;
										arm=arm-qt;
										r.setStr(arm);
										 Launch.logger.append("\nPlayer strength decreased by"+qt);
										Launch.pstrength.setText(Integer.toString(arm));
									}
									if(ch.equals("dexterity"))
									{
										int arm=r.getDex();
										int qt=isa.qty;
										arm=arm-qt;
										r.setDex(arm);
										 Launch.logger.append("\nPlayer dexterity decreased by"+qt);
										Launch.pdexerity.setText(Integer.toString(arm));
									}
									if(ch.equals("intelligence"))
									{
										int arm=r.getInte();
										int qt=isa.qty;
										arm=arm-qt;
										r.setInte(arm);
										 Launch.logger.append("\nPlayer intelligence decreased by"+qt);
										Launch.pintelligence.setText(Integer.toString(arm));
									}
									if(ch.equals("wisdom"))
									{
										int arm=r.getWis();
										int qt=isa.qty;
										arm=arm-qt;
										r.setWis(arm);
										 Launch.logger.append("\nPlayer wisdom decreased by"+qt);
										Launch.pwisdom.setText(Integer.toString(arm));
									}
									if(ch.equals("charisma"))
									{
										int arm=r.getCha();
										int qt=isa.qty;
										arm=arm-qt;
										r.setCha(arm);
										 Launch.logger.append("\nPlayer charisma decreased by"+qt);
										Launch.pcharisma.setText(Integer.toString(arm));
									}
								}
								}
								
								ci.add(a);
								ia.remove();
							}
								}
							}
						}
						
					}
				}
				frame.setVisible(false);
				frame.dispose();
				
						
			}
		}
		/*
		 * the list selection listener is written here where the selected item is captured as string 
		 */
		class listSelect implements ListSelectionListener {
			public void valueChanged(ListSelectionEvent ev) {
			
				if(!ev.getValueIsAdjusting()) {
					select =list.getSelectedValue().toString();
					
				}
			}
		}
		
	
		

}

package race;

import header.ChestItems;
import header.Items;
import header.RaceType;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import race.ItemsSave;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 * The BackPack class contains the items which the player has but did not equip it
 * From the backpack the player can equip items which will save in the inventory
 * As the player equips and unequips the items the related values are increased and decreased respectively 
 * in the player's modifiers and related characteristics.
 * @author team3 
 */



public class BackPack implements Serializable{
	
	
	static ArrayList<ChestItems>  ci;
	static ArrayList<Items> i;
	
	JFrame frame;
	JList listItems;
	JScrollPane listScroll;
	String sel,player;
	RaceType r;
	ArrayList<ChestItems> sam;
	ItemsSave itemsave;
	
	/*
	 * The constructor of the backpack which has displays a frame showing the list of items which the player picks up or unequips
	 */
	public BackPack(String playe,RaceType ra,ArrayList<ChestItems> cii,ArrayList<Items> ii)
	{
		sam=new ArrayList<ChestItems>();
		player=playe;
		r=ra;
		ci=cii;
		i=ii;
		frame = new JFrame();
		//JPanel options = new JPanel();
		JPanel select =new JPanel();
		select.setLayout(new BoxLayout(select,BoxLayout.Y_AXIS));
		JLabel bpack = new JLabel("BackPack");
		JLabel sel = new JLabel("Select Items:");
		select.add(bpack);
		select.add(sel);
		frame.getContentPane().add(BorderLayout.NORTH,select);
		Hashtable<String,Integer> map=new Hashtable<String,Integer>();
		if(ci.size()!=0)
		{
			for(ChestItems pitem:ci)
			{
				if(pitem.getName()!=null)
				{
					//selectedinv= pitem.getName();
					map.put(pitem.getName(), 1);
			}
			}
		}
		if(i.size()!=0)
		{
			for(Items pitem:i)
			{
				if(pitem.getName()!=null)
				{
					//selectedinv= pitem.getName();
					map.put(pitem.getName(), 1);
			}
			}
		}
		//DefaultListModel mainlist = new DefaultListModel();
		listItems = new JList(map.keySet().toArray());
		listScroll = new JScrollPane(listItems);
		frame.getContentPane().add(BorderLayout.CENTER, listScroll);
		JPanel south = new JPanel();
		JButton drop=new JButton("drop");
		JButton equip = new JButton("equip");
		south.add(drop);
		south.add(equip);
		listItems.addListSelectionListener(new ListSelect());
		drop.addActionListener(new dropActionListener());
		equip.addActionListener(new equipListener());
		frame.getContentPane().add(BorderLayout.SOUTH, south);
		frame.setSize(350, 350);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	/*
	 * Action listener for the drop button which will check which item is selected in from the list of items and drops it
	 */
	class dropActionListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			String[] weaps = new String[]{"LongBow", "LongSword"};
			String[] armor = new String[]{"Padded","Leather", "Studded Leather", "Chaio Shirt", "Breastplate", "Banded Mail", "Half Plate", "Full Plate"};
			String[] shld = new String[]{"Buckler", "Heavy Shield", "Tower Shield"};
			String[] chesti = new String[]{"Helmet","Padded","Leather", "Studded Leather", "Chaio Shirt", "Breastplate", "Banded Mail", "Half Plate", "Full Plate", "Belt","Boots", "Bracers", "Ring", "LongBow", "LongSword", "Buckler", "Heavy Shield", "Tower Shield"};
		try{	
			//The armor types are checked here
			for(String ss:armor)
			{								
				if(sel.equals(ss))
				{	
					
					Iterator<Items> ia=i.iterator();
					while(ia.hasNext())
					{
						Items a= ia.next();
						if(sel.equals(a.getName()))
						{
							ia.remove();
						}
					}
				
				}
			}
			//The weapon types are checked here
			for(String ss:weaps)
			{								
				if(sel.equals(ss))
				{		
					Iterator<Items> ia=i.iterator();
					while(ia.hasNext())
					{
						Items a= ia.next();
						if(sel.equals(a.getName()))
						{
							ia.remove();
						}
					}
					
				
				}
			}
			//The shield types are checked here
			for(String ss:shld)
			{							
				if(sel.equals(ss))
				{		
					
					Iterator<Items> ia=i.iterator();
					while(ia.hasNext())
					{
						Items a= ia.next();
						if(sel.equals(a.getName()))
						{
							ia.remove();
						}
					}
				
					}
			}
			//The chest item types are checked here
			for(String ss:chesti)
			{
				if(sel.equals(ss))
				{
					Iterator<ChestItems> ia=ci.iterator();
					while(ia.hasNext())
					{
						ChestItems a= ia.next();
						if(sel.equals(a.getName()))
						{
							ia.remove();
						}
					}
				
				}
			}
			Invent inventory=new Invent(r,ci,i,player);
			//savePlayer(player);
			frame.setVisible(false);
			frame.dispose();
		}catch(Exception ea)
		{
			JOptionPane.showMessageDialog(null, "Please Select an Item ");
		}
		}
	}
	/*
	 * Action listener for the equip button which will check which item is selected in from the list of items and
	 * equips it and stores it in the inventory
	 */
	class equipListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			String nameofitem=null,nameofchange = null;
			int qty = 0;
			String[] weaps = new String[]{"LongBow", "LongSword"};
			String[] armor = new String[]{"Padded","Leather", "Studded Leather", "Chaio Shirt", "Breastplate", "Banded Mail", "Half Plate", "Full Plate"};
			String[] shld = new String[]{"Buckler", "Heavy Shield", "Tower Shield"};
			String[] chesti = new String[]{"Helmet", "Padded","Leather", "Studded Leather", "Chaio Shirt", "Breastplate", "Banded Mail", "Half Plate", "Full Plate", "Belt", "Boots", "Bracers", "Ring","LongBow", "LongSword","Buckler", "Heavy Shield", "Tower Shield"};
			try{
				
				//The armor item types are checked here
				for(String ss:armor)
			
			{								
				if(sel.equals(ss))
				{	
					Iterator<Items> ia=i.iterator();
					while(ia.hasNext())
					{
						Items a= ia.next();
						if(sel.equals(a.getName()))
						{
							r.setArmor(a);
							if(a.getName().equals("Padded"))
							{
								String qt=Integer.toString(1);
								Launch.parmrmod.setText(qt);
								Launch.logger.append("\nArmor modifier change "+qt);
							}
							if(a.getName().equals("Leather"))
							{
								String qt=Integer.toString(2);
								Launch.parmrmod.setText(qt);
								Launch.logger.append("\nArmor modifier change "+qt);
							}
							if(a.getName().equals("Studded Leather"))
							{
								String qt=Integer.toString(3);
								Launch.parmrmod.setText(qt);
								Launch.logger.append("\nArmor modifier change "+qt);
							}
							if(a.getName().equals("Chaio Shirt"))
							{
								String qt=Integer.toString(4);
								Launch.parmrmod.setText(qt);
								Launch.logger.append("\nArmor modifier change "+qt);
							}
							if(a.getName().equals("Banded Mail"))
							{
								String qt=Integer.toString(5);
								Launch.parmrmod.setText(qt);
								Launch.logger.append("\nArmor modifier change "+qt);
							}
							if(a.getName().equals("Tower Shield"))
							{
								String qt=Integer.toString(6);
								Launch.parmrmod.setText(qt);
								Launch.logger.append("\nArmor modifier change "+qt);
							}
							if(a.getName().equals("Half Plate"))
							{
								String qt=Integer.toString(7);
								Launch.parmrmod.setText(qt);
								Launch.logger.append("\nArmor modifier change "+qt);
							}
							if(a.getName().equals("Full Plate"))
							{
								String qt=Integer.toString(8);
								Launch.parmrmod.setText(qt);
								Launch.logger.append("\nArmor modifier change "+qt);
							}
							
							Launch.logger.append("\nPlayer collected "+a.getName());
							
							ia.remove();
							
						}
					}
					
			
				}
			}
				//The weapon item types are checked here
			for(String ss:weaps)
			{								
				if(sel.equals(ss))
				{		
					Iterator<Items> ia=i.iterator();
					while(ia.hasNext())
					{
						Items a= ia.next();
						if(sel.equals(a.getName()))
						{
							r.setWeapon(a);
							if(a.getName().equals("LongBow"))
							{
								String qt=Integer.toString(r.getBab()+r.getStrengthmod());
								Launch.pwepmod.setText(qt);
								Launch.logger.append("\nWeapon modifier change "+qt);
							}
							if(a.getName().equals("LongSword"))
							{
								
								String qt=Integer.toString(r.getBab()+r.getStrengthmod());
								Launch.pwepmod.setText(qt);
								Launch.logger.append("\nWeapon modifier change "+qt);
							}
							Launch.logger.append("\nPlayer collected "+a.getName());
							ia.remove();
						}
					}
					
				
				}
			}
			//The shield item types are checked here
			for(String ss:shld)
			{							
				if(sel.equals(ss))
				{			
					Iterator<Items> ia=i.iterator();
					while(ia.hasNext())
					{
						Items a= ia.next();
						if(sel.equals(a.getName()))
						{
							r.setShield(a);
							if(a.getName().equals("Buckler"))
							{
								String qt=Integer.toString(1);
								Launch.pshldmod.setText(qt);
								Launch.logger.append("\nShield modifier change "+qt);
							}
							if(a.getName().equals("Heavy Shield"))
							{
								String qt=Integer.toString(2);
								Launch.pshldmod.setText(qt);
								Launch.logger.append("\nShield modifier change "+qt);
							}
							if(a.getName().equals("Tower Shield"))
							{
								String qt=Integer.toString(4);
								Launch.pshldmod.setText(qt);
								Launch.logger.append("\nShield modifier change "+qt);
							}
							
							Launch.logger.append("\nPlayer collected "+a.getName());
							ia.remove();
						}
					}
					
				
				}
			}
			//The chest item types are checked here
			for(String ss:chesti)
			{
				if(sel.equals(ss))
				{
					Iterator<ChestItems> ia=ci.iterator();
					while(ia.hasNext())
					{
						ChestItems a= ia.next();
						if(sel.equals(a.getName()))
						{
							int x=0;
							 int armorclass,constitution,strength, dexterity, intelligence,wisdom,charisma,damage,attack,carmorclass,cconstitution,cstrength, cdexterity, cintelligence,cwisdom,ccharisma,cdamage,cattack;
							 armorclass=r.getArmorclass();
							 constitution=r.getCon();
							 strength=r.getStr();
							 dexterity=r.getDex();
							 intelligence=r.getInte();
							 wisdom=r.getWis();
							 charisma=r.getCha();
							 carmorclass=a.armorCalss();
							 cconstitution=a.constitution();
							 cstrength=a.strength();
							 cdexterity=a.dexterity();
							 cintelligence=a.intelligence();
							 cwisdom=a.wisdom();
							 ccharisma=a.charisma();
							 cattack=a.attack();
							 cdamage=a.damage();
							
							
							//for(ChestItems rc: r.getChestItems())
							//{
							//	if(sel.equals(rc.getName()))
							//	{
							//		JOptionPane.showMessageDialog(null,
							//				"Similar Item is already equipped");
							//	}
							//}
							for(String ssa:armor)
							{
							if(a.getName().equals(ssa))
							{
								if(r.getArmor()!=null)
								{
									i.add(r.getArmor());
									r.setArmor(null);
									r.setChestItems(a);
									nameofitem=a.getName();
									ia.remove();
									x=1;
									Launch.logger.append("\nPlayer Armor is replaced with chestItems Armor");
								}
								else
								{
									r.setChestItems(a);
									nameofitem=a.getName();
									ia.remove();
									x=1;
									 if(carmorclass!=0)
									 {
										 armorclass=armorclass+carmorclass;
										 nameofchange="armorclass";
										 qty=carmorclass;
										 r.setArmorclass(armorclass);
										 Launch.logger.append("\nPlayer ArmorClass increased by"+carmorclass);
										 Launch.parmorclass.setText(Integer.toString(armorclass));
									 }
									 if(cconstitution!=0)
									 {
										 constitution=constitution+cconstitution;
										 nameofchange="constitution";
										 qty=cconstitution;
										 r.setCon(constitution);
										 Launch.logger.append("\nPlayer constitution increased by"+cconstitution);
										 Launch.pconstitution.setText(Integer.toString(constitution));
									 }
									 if(cstrength!=0)
									 {
										 strength=strength+cstrength;
										 nameofchange="strength";
										 qty=cstrength;
										 r.setStr(strength);
										 Launch.logger.append("\nPlayer strength increased by"+cstrength);
										 Launch.pstrength.setText(Integer.toString(strength));
									 }
									 if(cdexterity!=0)
									 {
										 dexterity=dexterity+cdexterity;
										 nameofchange="dexterity";
										 qty=cdexterity;
										 r.setDex(dexterity);
										 Launch.logger.append("\nPlayer dexterity increased by"+cdexterity);
										 Launch.pdexerity.setText(Integer.toString(dexterity));
									 }
									 if(cintelligence!=0)
									 {
										 intelligence=intelligence+cintelligence;
										 nameofchange="intelligence";
										 qty=cintelligence;
										 r.setInte(intelligence);
										 Launch.logger.append("\nPlayer intelligence increased by"+cintelligence);
										 Launch.pintelligence.setText(Integer.toString(intelligence));
									 }
									 if(cwisdom!=0)
									 {
										 wisdom=wisdom+cwisdom;
										 nameofchange="wisdom";
										 qty=cwisdom;
										 r.setWis(wisdom);
										 Launch.logger.append("\nPlayer wisdom increased by"+cwisdom);
										 Launch.pwisdom.setText(Integer.toString(wisdom));
									 }
									 if(ccharisma!=0)
									 {
										 charisma=charisma+ccharisma;
										 nameofchange="charisma";
										 qty=ccharisma;
										r.setCha(charisma);
										 Launch.logger.append("\nPlayer charisma increased by"+ccharisma);
										 Launch.pcharisma.setText(Integer.toString(charisma));
										
									 }
									// itemsave=new ItemsSave(nameofchange,qty);
									 Launch.btoi.put(nameofitem,new ItemsSave(nameofchange,qty));
								}
							}
							
						}
							for(String ssa:weaps)
							{
							if(a.getName().equals(ssa))
							{
								if(r.getWeapon()!=null)
								{
									i.add(r.getWeapon());
									r.setWeapon(null);
									r.setChestItems(a);
									nameofitem=a.getName();
									ia.remove();
									x=1;
									Launch.logger.append("\nPlayer Weapon is replaced with chestItems Weaopen");
								}
								else
								{
									r.setChestItems(a);
									nameofitem=a.getName();
									ia.remove();
									x=1;
									 if(carmorclass!=0)
									 {
										 armorclass=armorclass+carmorclass;
										 nameofchange="armorclass";
										 qty=carmorclass;
										 r.setArmorclass(armorclass);
										 Launch.logger.append("\nPlayer ArmorClass increased by"+carmorclass);
										 Launch.parmorclass.setText(Integer.toString(armorclass));
									 }
									 if(cconstitution!=0)
									 {
										 constitution=constitution+cconstitution;
										 nameofchange="constitution";
										 qty=cconstitution;
										 r.setCon(constitution);
										 Launch.logger.append("\nPlayer constitution increased by"+cconstitution);
										 Launch.pconstitution.setText(Integer.toString(constitution));
									 }
									 if(cstrength!=0)
									 {
										 strength=strength+cstrength;
										 nameofchange="strength";
										 qty=cstrength;
										 r.setStr(strength);
										 Launch.logger.append("\nPlayer strength increased by"+cstrength);
										 Launch.pstrength.setText(Integer.toString(strength));
									 }
									 if(cdexterity!=0)
									 {
										 dexterity=dexterity+cdexterity;
										 nameofchange="dexterity";
										 qty=cdexterity;
										 r.setDex(dexterity);
										 Launch.logger.append("\nPlayer dexterity increased by"+cdexterity);
										 Launch.pdexerity.setText(Integer.toString(dexterity));
									 }
									 if(cintelligence!=0)
									 {
										 intelligence=intelligence+cintelligence;
										 nameofchange="intelligence";
										 qty=cintelligence;
										 r.setInte(intelligence);
										 Launch.logger.append("\nPlayer intelligence increased by"+cintelligence);
										 Launch.pintelligence.setText(Integer.toString(intelligence));
									 }
									 if(cwisdom!=0)
									 {
										 wisdom=wisdom+cwisdom;
										 nameofchange="wisdom";
										 qty=cwisdom;
										 r.setWis(wisdom);
										 Launch.logger.append("\nPlayer wisdom increased by"+cwisdom);
										 Launch.pwisdom.setText(Integer.toString(wisdom));
									 }
									 if(ccharisma!=0)
									 {
										 charisma=charisma+ccharisma;
										 nameofchange="charisma";
										 qty=ccharisma;
										r.setCha(charisma);
										 Launch.logger.append("\nPlayer charisma increased by"+ccharisma);
										 Launch.pcharisma.setText(Integer.toString(charisma));
										
									 }
									 Launch.btoi.put(nameofitem,new ItemsSave(nameofchange,qty));
								}
							}
							
						}
							for(String ssa:shld)
							{
							if(a.getName().equals(ssa))
							{
								if(r.getShield()!=null)
								{
									i.add(r.getShield());
									r.setShield(null);
									r.setChestItems(a);
									nameofitem=a.getName();
									ia.remove();
									x=1;
									Launch.logger.append("\nPlayer Shield is replaced with chestItems Shield");
								}
								else
								{
									r.setChestItems(a);
									nameofitem=a.getName();
									ia.remove();
									x=1;
									 if(carmorclass!=0)
									 {
										 armorclass=armorclass+carmorclass;
										 nameofchange="armorclass";
										 qty=carmorclass;
										 r.setArmorclass(armorclass);
										 Launch.logger.append("\nPlayer ArmorClass increased by"+carmorclass);
										 Launch.parmorclass.setText(Integer.toString(armorclass));
									 }
									 if(cconstitution!=0)
									 {
										 constitution=constitution+cconstitution;
										 nameofchange="constitution";
										 qty=cconstitution;
										 r.setCon(constitution);
										 Launch.logger.append("\nPlayer constitution increased by"+cconstitution);
										 Launch.pconstitution.setText(Integer.toString(constitution));
									 }
									 if(cstrength!=0)
									 {
										 strength=strength+cstrength;
										 nameofchange="strength";
										 qty=cstrength;
										 r.setStr(strength);
										 Launch.logger.append("\nPlayer strength increased by"+cstrength);
										 Launch.pstrength.setText(Integer.toString(strength));
									 }
									 if(cdexterity!=0)
									 {
										 dexterity=dexterity+cdexterity;
										 nameofchange="dexterity";
										 qty=cdexterity;
										 r.setDex(dexterity);
										 Launch.logger.append("\nPlayer dexterity increased by"+cdexterity);
										 Launch.pdexerity.setText(Integer.toString(dexterity));
									 }
									 if(cintelligence!=0)
									 {
										 intelligence=intelligence+cintelligence;
										 nameofchange="intelligence";
										 qty=cintelligence;
										 r.setInte(intelligence);
										 Launch.logger.append("\nPlayer intelligence increased by"+cintelligence);
										 Launch.pintelligence.setText(Integer.toString(intelligence));
									 }
									 if(cwisdom!=0)
									 {
										 wisdom=wisdom+cwisdom;
										 nameofchange="wisdom";
										 qty=cwisdom;
										 r.setWis(wisdom);
										 Launch.logger.append("\nPlayer wisdom increased by"+cwisdom);
										 Launch.pwisdom.setText(Integer.toString(wisdom));
									 }
									 if(ccharisma!=0)
									 {
										 charisma=charisma+ccharisma;
										 nameofchange="charisma";
										 qty=ccharisma;
										r.setCha(charisma);
										 Launch.logger.append("\nPlayer charisma increased by"+ccharisma);
										 Launch.pcharisma.setText(Integer.toString(charisma));
										
									 }
									 Launch.btoi.put(nameofitem,new ItemsSave(nameofchange,qty));
								}
							}
							
						}
							if(x==0)
							{
								r.setChestItems(a);
								nameofitem=a.getName();
								ia.remove();
								 if(carmorclass!=0)
								 {
									 armorclass=armorclass+carmorclass;
									 nameofchange="armorclass";
									 qty=carmorclass;
									 r.setArmorclass(armorclass);
									 Launch.logger.append("\nPlayer ArmorClass increased by"+carmorclass);
									 Launch.parmorclass.setText(Integer.toString(armorclass));
								 }
								 if(cconstitution!=0)
								 {
									 constitution=constitution+cconstitution;
									 nameofchange="constitution";
									 qty=cconstitution;
									 r.setCon(constitution);
									 Launch.logger.append("\nPlayer constitution increased by"+cconstitution);
									 Launch.pconstitution.setText(Integer.toString(constitution));
								 }
								 if(cstrength!=0)
								 {
									 strength=strength+cstrength;
									 nameofchange="strength";
									 qty=cstrength;
									 r.setStr(strength);
									 Launch.logger.append("\nPlayer strength increased by"+cstrength);
									 Launch.pstrength.setText(Integer.toString(strength));
								 }
								 if(cdexterity!=0)
								 {
									 dexterity=dexterity+cdexterity;
									 nameofchange="dexterity";
									 qty=cdexterity;
									 r.setDex(dexterity);
									 Launch.logger.append("\nPlayer dexterity increased by"+cdexterity);
									 Launch.pdexerity.setText(Integer.toString(dexterity));
								 }
								 if(cintelligence!=0)
								 {
									 intelligence=intelligence+cintelligence;
									 nameofchange="intelligence";
									 qty=cintelligence;
									 r.setInte(intelligence);
									 Launch.logger.append("\nPlayer intelligence increased by"+cintelligence);
									 Launch.pintelligence.setText(Integer.toString(intelligence));
								 }
								 if(cwisdom!=0)
								 {
									 wisdom=wisdom+cwisdom;
									 nameofchange="wisdom";
									 qty=cwisdom;
									 r.setWis(wisdom);
									 Launch.logger.append("\nPlayer wisdom increased by"+cwisdom);
									 Launch.pwisdom.setText(Integer.toString(wisdom));
								 }
								 if(ccharisma!=0)
								 {
									 charisma=charisma+ccharisma;
									 nameofchange="charisma";
									 qty=ccharisma;
									r.setCha(charisma);
									 Launch.logger.append("\nPlayer charisma increased by"+ccharisma);
									 Launch.pcharisma.setText(Integer.toString(charisma));
									
								 }
								 Launch.btoi.put(nameofitem,new ItemsSave(nameofchange,qty));
							}
							
							
					}
				
					
					
				}
			}
			
			
					
			
		}
			}catch(Exception ea)
			{
				JOptionPane.showMessageDialog(null, "Please Select an Item ");
			}
				
			
			Invent inventory=new Invent(r,ci,i,player);
			//inventory.Inventa(btoi);
			//savePlayer(player);
			frame.setVisible(false);
			frame.dispose();
		}
	}
	/*
	 * the list selection listener is written here where the selected item is captured as string 
	 */
	class ListSelect implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent ev) {
		
			if(!ev.getValueIsAdjusting()) {
				sel =listItems.getSelectedValue().toString();
				
			}
		}
}
	

}

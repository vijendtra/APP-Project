package header;

import java.io.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
/**
 * This class is the class of the player which is the product in the builder pattern
 * It initializes the player's values
 * Die rolls are performed in this class to generate random values which are assigned to ability scores, modifiers 
 * as and when they are required to be assigned.
 * @author team3
 */
// The concrete RaceType class based on RaceTypeInterface
public class RaceType implements Serializable, RaceTypeInterface {

	private int wis, dex, inte, cha, str, con;
	private String name;
	private boolean gender;
	private Items armor;
	private Items weapon;
	private Items shield;
	private ArrayList<Items> inventory;
	private ArrayList<ChestItems> chestitem;// =new ArrayList<ChestItems>();

	private String classType;
	private String race;
	private int lvl;
	private int exp;
	private int hp;
	int x, y;
	private int dexmod, armormod, strengthmod, shieldmod;
	private int armorclass;
	private int conmod;
	private int bab;
	private int attackbonus;
	public BufferedImage img = null;
	/*
	 * The below methods are the get methods and the set methods of the various characteristics of 
	 * the player such as wisdom, charisma and all
	 */
	public void setup() {
		chestitem = new ArrayList<ChestItems>();
	}

	public ArrayList<Items> getInventory() {
		return inventory;
	}

	public void setInventory(Items i) {
		inventory.add(i);
	}

	public ArrayList<ChestItems> getChestItems() {
		return chestitem;
	}

	public void setChestItems(ChestItems i) {
		chestitem.add(i);
	}

	public void setWeapon(Items wp) {
		this.weapon = wp;
	}

	public void setPlayer(int row, int col) {
		x = row;
		y = col;

	}

	public void takeDamage(int i) {
		hp -= i;
	}

	public void paint(Graphics g) {
		g.setColor(Color.green);

		/*
		 * Graphics2D g2=(Graphics2D)g; try { img = ImageIO.read(new
		 * File("player.png")); } catch (IOException e)
		 * {System.out.println("file not found"); // TODO Auto-generated }
		 * g2.drawImage(img, y*20,x*20,null);
		 */

		g.fillRect(y * 20, x * 20, 20, 20);
		// g.drawRect((y*20)-20,(x*20)-20, 60, 60);

		// t.start();
	}

	public Rectangle bounds() {

		return (new Rectangle((y * 20) - 20, (x * 20) - 20, 60, 60));

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public double iR() {
		double j = (Math.random() * 20) + dexmod;

		return j;
	}

	@Override
	public void setWis(int wis) {
		this.wis = wis;

	}

	@Override
	public void setDex(int dex) {
		this.dex = dex;

	}

	@Override
	public void setInte(int inte) {
		this.inte = inte;

	}

	@Override
	public void setCha(int cha) {
		this.cha = cha;

	}

	@Override
	public void setStr(int str) {
		this.str = str;

	}

	@Override
	public void setCon(int con) {
		this.con = con;

	}

	@Override
	public void setDexmod(int dexmod) {
		this.dexmod = dexmod;

	}

	@Override
	public void setArmormod(int armormod) {
		this.armormod = armormod;

	}

	@Override
	public void setStrengthmod(int strengthmod) {
		this.strengthmod = strengthmod;

	}

	@Override
	public void setShieldmod(int shieldmod) {
		this.shieldmod = shieldmod;

	}

	@Override
	public void setConmod(int conmod) {
		this.conmod = conmod;
	}

	@Override
	public void setLvl(int lvl) {
		this.lvl = lvl;

	}

	@Override
	public void setExp(int exp) {
		this.exp = exp;

	}

	@Override
	public void setHp(int hp) {
		this.hp = hp;

	}

	@Override
	public void setArmorclass(int armorclass) {
		this.armorclass = armorclass;

	}

	@Override
	public void setBab(int bab) {
		this.bab = bab;

	}

	@Override
	public void setAttackbonus(int attackbonus) {
		this.attackbonus = attackbonus;

	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setGender(boolean gender) {

		this.gender = gender;
	}

	@Override
	public void setArmor(Items armor) {
		this.armor = armor;

	}

	@Override
	public void setWeapons(Items weapons) {
		this.weapon = weapons;

	}

	@Override
	public void setShield(Items shield) {
		this.shield = shield;
	}

	@Override
	public void setClasstype(String classtype) {
		this.classType = classtype;
	}

	@Override
	public void setRace(String race) {
		this.race = race;
	}

	public Items getWeapon() {
		return weapon;
	}

	public String getClassType() {
		return classType;
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

	public int getCha() {
		return cha;
	}

	public int getStr() {
		return str;
	}

	public int getCon() {
		return con;
	}

	public String getName() {
		return name;
	}

	public boolean isGender() {
		return gender;
	}

	public Items getArmor() {
		return armor;
	}

	public Items getShield() {
		return shield;
	}

	public String getRace() {
		return race;
	}

	public int getLvl() {
		return lvl;
	}

	public int getExp() {
		return exp;
	}

	public int getHp() {
		return hp;
	}

	public int getDexmod() {
		return dexmod;
	}

	public int getArmormod() {
		return armormod;
	}

	public int getStrengthmod() {
		return strengthmod;
	}

	public int getShieldmod() {
		return shieldmod;
	}

	public int getArmorclass() {
		return armorclass;
	}

	public int getConmod() {
		return conmod;
	}

	public int getBab() {
		return bab;
	}

	public int getAttackbonus() {
		return attackbonus;
	}
	
	public void update() {
		lvl++;
	}

}

package header;
/**
 * This class is the builder interface which sets the abilities and modifiers and game variables of the player
 * @author team3
 *
 */
public interface RaceTypeInterface {

	// Ability
	public void setWis(int wis);

	public void setDex(int dex);

	public void setInte(int inte);

	public void setCha(int cha);

	public void setStr(int str);

	public void setCon(int con);

	// Modifier
	public void setDexmod(int dexmod);

	public void setArmormod(int armormod);

	public void setStrengthmod(int strengthmod);

	public void setShieldmod(int shieldmod);

	public void setConmod(int conmod);

	// Game variables
	public void setLvl(int lvl);

	public void setExp(int exp);

	public void setHp(int hp);

	public void setArmorclass(int armorclass);

	public void setBab(int bab);

	public void setAttackbonus(int attackbonus);

	// CharEdit
	public void setName(String name);

	public void setGender(boolean gender);

	public void setArmor(Items armor);

	public void setWeapons(Items weapons);

	public void setShield(Items shield);

	public void setClasstype(String classtype);

	public void setRace(String race);

}

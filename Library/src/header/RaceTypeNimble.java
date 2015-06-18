package header;

import java.io.Serializable;
import java.util.Arrays;
/**
 * This is the concrete builder of the builder pattern which implements the 
 * racetypebuilderinterface
 * @author team3
 *
 */
public class RaceTypeNimble implements RaceTypeBuilderInterface, Serializable {

	private RaceType r;

	public RaceTypeNimble() {
		this.r = new RaceType();
	}

	@Override
	public void setAbility() {
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

		r.setStr((int) ability[5]);
		r.setCon((int) ability[4]);
		r.setDex((int) ability[3]);
		r.setInte((int) ability[2]);
		r.setCha((int) ability[1]);
		r.setWis((int) ability[0]);

	}

	@Override
	public void setModifier() {
		int diff = 0;
		double s = 0.0;
		int step = 0;

		// dexmod
		
		diff = 10 - r.getDex();
		
		s = diff / 2.0;
	
		step = (int) (Math.ceil(s));
		
		r.setDexmod((-step));

		// strengthmod
		
		diff = 10 - r.getStr();
		
		s = diff / 2.0;
		
		step = (int) (Math.ceil(s));
	
		r.setStrengthmod((-step));

		// conmod
		
		diff = 10 - r.getCon();
	
		s = diff / 2.0;
		
		step = (int) (Math.ceil(s));
		
		r.setConmod((-step));

		// armormod
		switch (r.getArmor().getName()) {
		case "Padded":
			r.setArmormod(1);
			break;
		case "Leather":
			r.setArmormod(2);
			break;
		case "Studded Leather":
			r.setArmormod(3);
			break;
		case "Chain Shirt":
			r.setArmormod(4);
			break;
		case "Breast Plate":
			r.setArmormod(5);
			break;
		case "Banded Mail":
			r.setArmormod(6);
			break;
		case "Half Plate":
			r.setArmormod(7);
			break;
		case "Full Plate":
			r.setArmormod(8);
			break;
		}
		

		// shieldmod
		switch (r.getShield().getName()) {
		case "Buckler":
			r.setShieldmod(1);
			break;
		case "Heavy Shield":
			r.setShieldmod(2);
			break;
		case "Tower Shield":
			r.setShieldmod(4);
			break;
		}
		

	}

	@Override
	public void setGameVariable() {
		r.setLvl(1);
		r.setHp(100);
		r.setExp(0);
		r.setArmorclass(10 + r.getDexmod() + r.getArmormod() + r.getShieldmod());

		// bab
		int sum = 0;
		int i = 5;
		int diff = r.getLvl();
		while (diff >= 0) {
			sum += diff;
			diff -= i;
		}
		
		r.setBab(sum);

		// attackbonus
		if (r.getWeapon().getName().equals("Bow n Arrow"))
			r.setAttackbonus(r.getBab() + r.getDexmod());
		else
			r.setAttackbonus(r.getBab() + r.getStrengthmod());

	}

	@Override
	public void setName(String name) {
		r.setName(name);

	}

	@Override
	public void setGender(boolean gender) {
		r.setGender(gender);

	}

	@Override
	public void setArmor(Items armor) {
		r.setArmor(armor);

	}

	@Override
	public void setWeapons(Items weapons) {
		r.setWeapons(weapons);

	}

	@Override
	public void setShield(Items shield) {
		r.setShield(shield);

	}

	@Override
	public void setClasstype(String classtype) {
		r.setClasstype(classtype);

	}

	@Override
	public void setRace(String race) {
		r.setRace(race);

	}

	@Override
	public RaceType getRaceType() {
		return r;
	}

}

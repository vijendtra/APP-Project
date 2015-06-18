package header;

import java.io.Serializable;
/**
 * The ring class defines the types of the ring which are based on the level value of the player
 * We can see the ring constructor below
 * This is one the Derived classes of the ChestItemFactory
 * @author team3
 * @param Interger l
 */
public class Ring extends ChestItems implements Serializable {

	public Ring(int l) {
		name = "Ring";
		int i = (int) (Math.random() * 5);
		int j = (int) (Math.random() * 5);
		if (j == 0) {
			j = 1;
		}
		if (i == 0) {
			constitution = 0;
			wisdom = 0;
			armorclass = j;
			charisma = 0;
			strength = 0;
		}
		if (i == 1) {
			constitution = 0;
			wisdom = 0;
			armorclass = j;
			charisma = 0;
			strength = 0;
		}
		if (i == 2) {
			constitution = 0;
			wisdom = j;
			armorclass = 0;
			charisma = 0;
			strength = 0;
		}
		if (i == 3) {
			constitution = j;
			wisdom = 0;
			armorclass = 0;
			charisma = 0;
			strength = 0;
		}
		if (i == 4) {
			constitution = 0;
			wisdom = 0;
			armorclass = 0;
			charisma = 0;
			strength = j;
		}
		if (i == 5) {
			constitution = 0;
			wisdom = 0;
			armorclass = 0;
			charisma = j;
			strength = 0;
		}
	}

	public int armorCalss() {

		return armorclass;
	}

	public int strength() {

		return strength;
	}

	public int charisma() {

		return charisma;
	}

	public int wisdom() {

		return wisdom;
	}

	public int constitution() {

		return constitution;
	}

	@Override
	public int intelligence() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int dexterity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int attack() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int damage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
}

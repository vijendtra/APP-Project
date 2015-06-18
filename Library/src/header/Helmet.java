package header;

import java.io.Serializable;
/**
 * The helmet class defines the types of the helmet which are based on the level value of the player
 * We can see the helmet constructor below
 * This is one the Derived classes of the ChestItemFactory
 * @author team3
 * @param Interger l
 */
public class Helmet extends ChestItems implements Serializable {

	public Helmet(int l) {
		name = "Helmet";
		int i = (int) (Math.random() * 3);
		int j = (int) (Math.random() * 5);
		//The characteristics of the player is changed randomly within 1 to 5 values.
		if (j == 0) {
			j = 1;
		}
		if (i == 0) {
			intelligence = 0;
			wisdom = 0;
			armorclass = j;
		}
		if (i == 1) {
			intelligence = j;
			wisdom = 0;
			armorclass = 0;
		}
		if (i == 2) {
			intelligence = 0;
			wisdom = j;
			armorclass = 0;
		}
		if (i == 3) {
			intelligence = j;
			wisdom = 0;
			armorclass = 0;
		}
	}

	public int armorCalss() {

		return armorclass;
	}

	public int wisdom() {

		return wisdom;
	}

	public int intelligence() {

		return intelligence;
	}

	@Override
	public int strength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int constitution() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int charisma() {
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

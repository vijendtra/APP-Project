package header;

import java.io.Serializable;
/**
 * The bracers class defines the types of the bracers which are based on the level value of the player
 * We can see the bracers constructor below
 * This is one the Derived classes of the ChestItemFactory
 * @author team3
 * @param Interger l
 */
public class Bracers extends ChestItems implements Serializable {

	public Bracers(int l) {
		name = "Bracers";
		int i = (int) (Math.random() * 2);
		int j = (int) (Math.random() * 5);
		//The characteristics of the player is changed randomly within 1 to 5 values.
		if (j == 0) {
			j = 1;
		}
		if (i == 0) {

			this.strength = 0;
			this.armorclass = j;
		}
		if (i == 1) {

			this.strength = 0;
			this.armorclass = j;
		}
		if (i == 2) {

			this.strength = j;
			this.armorclass = 0;
		}

	}

	public int armorCalss() {

		return armorclass;
	}

	public int strength() {

		return strength;
	}

	@Override
	public int wisdom() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int intelligence() {
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

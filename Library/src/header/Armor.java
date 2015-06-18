package header;

import java.io.Serializable;
/**
 * The armor class defines the types of the armor which are based on the level value of the player
 * We can see the different types of armors below
 * This is one the Derived classes of the ChestItemFactory
 * @author team3
 * @param Interger l
 */
public class Armor extends ChestItems implements Serializable {

	public Armor(int l) {

		int i = (int) (Math.random() * l);
		int j = (int) (Math.random() * 5);
		//Checks which level the player is in and gives the armor type
		if (j == 0) {
			j = 1;
		}
		if (i == 0)
			name = "Padded";
		if (i == 1)
			name = "Leather";
		if (i == 2)
			name = "Studded Leather";
		if (i == 3)
			name = "Chaio Shirt";
		if (i == 4)
			name = "Breastplate";
		if (i == 5)
			name = "Banded Mail";
		if (i == 6)
			name = "Half Plate";
		if (i == 7)
			name = "Full Plate";
		if (i == 8)
			name = "Full Plate";

		// System.out.println("random valu"+j);
		if (j > 5) {
			j = (int) j / 4;
		}
		armorclass = j;
	}

	public int armorCalss() {

		return armorclass;
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

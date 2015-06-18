package header;

import java.io.Serializable;
/**
 * The Shield class defines the types of the shields which are based on the level value of the player
 * We can see the shield constructor below with the different types of shields
 * This is one the Derived classes of the ChestItemFactory
 * @author team3
 * @param Interger l
 */
public class Shield extends ChestItems implements Serializable {

	public Shield(int l) {
		int j = (int) (Math.random() * 5);
		if (j == 0) {
			j = 1;
		}
		int i = (int) (Math.random() * l);
		if (i == 0)
			name = "Buckler";
		if (i == 1)
			name = "Heavy Shield";
		if (i == 2)
			name = "Tower Shield";
		if (i == 3)
			name = "Tower Shield";

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

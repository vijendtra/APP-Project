package header;

import java.io.Serializable;
/**
 * The belt class defines the types of the belt which are based on the level value of the player
 * We can see the belt constructor below
 * This is one the Derived classes of the ChestItemFactory
 * @author team3
 * @param Interger l
 */
public class Belt extends ChestItems implements Serializable {

	public Belt(int l) {
		name = "Belt";
		int i = (int) (Math.random() * 2);
		int j = (int) (Math.random() * 5);
		//The characteristics of the player is changed randomly within 1 to 5 values.
		if (j == 0) {
			j = 1;
		}
		if (i == 0) {

			this.constitution = 0;
			this.strength = j;
		}
		if (i == 1) {

			this.constitution = 0;
			this.strength = j;
		}
		if (i == 2) {

			this.strength = 0;
			this.constitution = j;
		}

	}

	public int constitution() {

		return constitution;
	}

	public int strength() {

		return strength;
	}

	@Override
	public int armorCalss() {
		// TODO Auto-generated method stub
		return 0;
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

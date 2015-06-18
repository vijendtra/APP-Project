package header;

import java.io.Serializable;
/**
 * The Sword and bow class defines the types of the weapons which are based on the level value of the player
 * We can see the constructor below with the different types of weapons
 * This is one the Derived classes of the ChestItemFactory
 * @author team3
 * @param Interger l
 */
public class SwordAndBow extends ChestItems implements Serializable {

	public SwordAndBow(int l) {
		int j = (int) (Math.random() * 5);
		if (j == 0) {
			j = 1;
		}
		int i = (int) (Math.random() * l);
		if (i == 0)
			name = "LongBow";
		if (i == 1)
			name = "LongBow";
		if (i == 2)
			name = "LongSword";

		int k = (int) (Math.random() * 2);
		if (k == 0) {

			attack = 0;
			damage = j;
		}
		if (k == 1) {

			attack = 0;
			damage = j;
		}
		if (k == 2) {

			damage = 0;
			attack = j;
		}

	}

	public int damage() {

		return damage;
	}

	public int attack() {

		return attack;
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
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

}

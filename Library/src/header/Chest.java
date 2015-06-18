package header;
/**
 * The Chest class is part of the builder pattern
 * It is the Product class of Chest builder
 * @author team3
 */
public class Chest {

	public ChestItems helmet, armor, shield, boots, ring, belt, bracers,
			swordandbow;
	/*
	 * The set methods for all the items in the chest are given below
	 */
	public void setHelmet(ChestItems helmet) {
		this.helmet = helmet;
	}

	public void setArmor(ChestItems armor) {
		this.armor = armor;
	}

	public void setShield(ChestItems shield) {
		this.shield = shield;
	}

	public void setBoots(ChestItems boots) {
		this.boots = boots;
	}

	public void setBelt(ChestItems belt) {
		this.belt = belt;
	}

	public void setRing(ChestItems ring) {
		this.ring = ring;
	}

	public void setBracers(ChestItems bracers) {
		this.bracers = bracers;
	}

	public void setSwordAndBow(ChestItems swordandbow) {
		this.swordandbow = swordandbow;
	}
}

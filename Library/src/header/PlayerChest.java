package header;
/**
 * This class is the Concrete Builder of the builder pattern
 * It will build the player chest items
 * @author team3
 */
public class PlayerChest extends ChestBuilder {
	ChestItemFactory r = new ChestItemFactory();
	int j;

	public PlayerChest(int i) {
		j = i;
	}

	public void buildHelmet() {
		c.setHelmet(r.setItem("Helmet", j));

	}

	public void buildArmor() {

		c.setArmor(r.setItem("Armor", j));

	}

	public void buildShield() {
		c.setShield(r.setItem("Shield", j));

	}

	public void buildBoots() {
		c.setBoots(r.setItem("Boots", j));

	}

	public void buildBelt() {
		c.setBelt(r.setItem("Belt", j));

	}

	public void buildRing() {
		c.setRing(r.setItem("Ring", j));

	}

	public void buildBracers() {
		c.setBracers(r.setItem("Bracers", j));

	}

	public void buildSwordAndBow() {
		c.setSwordAndBow(r.setItem("SwordAndBow", j));

	}

}

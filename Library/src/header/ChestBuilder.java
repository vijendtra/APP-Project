package header;
/**
 * The ChestBuilder class is part of the builder pattern
 * It is the Abstract builder class of Chest builder
 * @author team3
 */
public abstract class ChestBuilder {
	Chest c;
	//get method to get the chest
	public Chest getChest() {
		return c;
	}
	//this will create a new chest
	public void createChest() {
		c = new Chest();
	}
	// the abstract methods which will be implemented later are given below
	public abstract void buildHelmet();

	public abstract void buildArmor();

	public abstract void buildShield();

	public abstract void buildBoots();

	public abstract void buildBelt();

	public abstract void buildRing();

	public abstract void buildBracers();

	public abstract void buildSwordAndBow();
}

package header;
/**
 * The ChestDirector class is part of the builder pattern
 * It is the Director class of Chest builder
 * @author team3
 */
public class ChestDirector {
	ChestBuilder cb;
	//set method to set the chest builder
	public void setChestBuilder(ChestBuilder chestbuilder) {
		cb = chestbuilder;
	}
	//get method to get the chest
	public Chest getChest() {
		return cb.getChest();
	}
	//this method constructs the builder
	public void ConstructChest() {
		cb.createChest();
		cb.buildHelmet();
		cb.buildArmor();
		cb.buildShield();
		cb.buildRing();
		cb.buildBelt();
		cb.buildBoots();
		cb.buildBracers();
		cb.buildSwordAndBow();
	}
}

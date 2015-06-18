package header;
/**
 * The chestitems class is the base class in the factory pattern
 * @author team3
 */
public abstract class ChestItems {
	int armorclass, constitution, strength, dexterity, intelligence, wisdom,
			charisma, damage, attack;
	String name = null;

	public abstract String getName();

	public abstract int armorCalss();

	public abstract int wisdom();

	public abstract int intelligence();

	public abstract int strength();

	public abstract int constitution();

	public abstract int charisma();

	public abstract int dexterity();

	public abstract int attack();

	public abstract int damage();
}

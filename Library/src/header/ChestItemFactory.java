package header;
/**
 * This is the ChestItemFactory class which is the Item factory which 
 * will return an instance of one chest item or the other
 * 
 */
public class ChestItemFactory {
	public static ChestItems setItem(String i, int j) {
		ChestItems selected = null;
		if (i.equals("Helmet")) {
			selected = new Helmet(j);
		} else if (i.equals("Ring"))
			selected = new Ring(j);
		else if (i.equals("Shield"))
			selected = new Shield(j);
		else if (i.equals("Armor")) {

			selected = new Armor(j);
			// System.out.println(selected.getName());
		} else if (i.equals("Bracers"))
			selected = new Bracers(j);
		else if (i.equals("Belt"))
			selected = new Belt(j);
		else if (i.equals("Boots"))
			selected = new Boots(j);
		else if (i.equals("SwordAndBow"))
			selected = new SwordAndBow(j);

		return selected;
	}

}

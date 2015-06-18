package header;
/**
 * This is the director class of the builder pattern
 * @author team3
 *
 */
public class RaceTypeDirector {

	private RaceTypeBuilderInterface rtbi;

	public RaceTypeDirector(RaceTypeBuilderInterface r) {
		this.rtbi = r;
	}

	public RaceType getRaceType() {
		return this.rtbi.getRaceType();
	}

	public void makeRaceType(String name, boolean gender, Items armor,
			Items weapon, Items shield, String classtype, String race) {
		this.rtbi.setAbility();
		this.rtbi.setName(name);
		this.rtbi.setClasstype(classtype);
		this.rtbi.setRace(race);
		this.rtbi.setGender(gender);
		this.rtbi.setArmor(armor);
		this.rtbi.setWeapons(weapon);
		this.rtbi.setShield(shield);
		this.rtbi.setModifier();
		this.rtbi.setGameVariable();
	}
}

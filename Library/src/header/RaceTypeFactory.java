package header;
/**
 * This class is the factory for buliding the class by choosing the concrete class
 * @author team3
 *
 */
public class RaceTypeFactory {

	RaceTypeBuilderInterface r;
	RaceTypeDirector d;
	RaceType r2;

	public RaceTypeFactory(String option) {
		if (option.equals("Bully"))
			r = new RaceTypeBully();
		else if (option.equals("Nimble"))
			r = new RaceTypeNimble();
		else
			r = new RaceTypeTank();
		d = new RaceTypeDirector(r);
	}

	public RaceType setup(String name, boolean gender, Items armor,
			Items weapons, Items shield, String classtype, String race) {
		d.makeRaceType(name, gender, armor, weapons, shield, classtype, race);
		r2 = d.getRaceType();
		return r2;
	}

}

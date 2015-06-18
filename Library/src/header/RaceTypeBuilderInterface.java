package header;
/**
 * this is the racetype builder interface which is part of the builder pattern
 * which is the interface which is implemented by the concrete builders
 * @author team3
 *
 */
// Defines the methods needed to create Bully, Nimble and Tank
public interface RaceTypeBuilderInterface {

	public void setAbility();

	public void setModifier();

	public void setGameVariable();

	public void setName(String name);

	public void setGender(boolean gender);

	public void setArmor(Items armor);

	public void setWeapons(Items weapons);

	public void setShield(Items shield);

	public void setClasstype(String classtype);

	public void setRace(String race);

	public RaceType getRaceType();
}

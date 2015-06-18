package tests;
import static org.junit.Assert.*;
import header.Items;
import junit.framework.TestCase;
import org.junit.Test;
import edit.FileHandler;


public class TestWeapon extends TestCase {

	Items name;
	FileHandler file=new FileHandler();
	
	@Test
	public void test() {
		name=file.WeaponName();
		assertEquals(FileHandler.weaponName,name );
	}

}

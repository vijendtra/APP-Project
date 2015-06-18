package tests;
import static org.junit.Assert.*;

import org.junit.Test;

import edit.*;
public class TestRace {
	String name;
	FileHandler file=new FileHandler();
	
	@Test
	public void testRaceName() {
		name=file.RaceName();
		assertEquals(FileHandler.rName,name );
		
	}

}

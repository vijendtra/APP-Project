package tests;
import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;

import edit.FileHandler;


public class TestRaceName extends TestCase{
	String name;
	FileHandler file=new FileHandler();
	@Test
	public void test() {
		name=file.RaceNames();
		assertEquals(FileHandler.rName1,name );
		
	}

}

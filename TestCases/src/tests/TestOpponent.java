package tests;

import static org.junit.Assert.*;
import junit.framework.TestCase;
import header.Items;

import org.junit.Test;

import edit.FileHandler;

public class TestOpponent extends TestCase{

	Items name;
	String name1;
	FileHandler file=new FileHandler();
	@Test
	public void test() {
		name=file.ArmorName();
		assertEquals(FileHandler.armor,name );
		
	}
	
	@Test
	public void test1() {
		name=file.ShieldName();
		assertEquals(FileHandler.Shield,name );
	}

	@Test
	public void testRaceName() {
		name1=file.RaceName();
		assertEquals(FileHandler.rName,name );
		
	}

}

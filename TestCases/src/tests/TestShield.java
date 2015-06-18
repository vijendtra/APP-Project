package tests;
import static org.junit.Assert.*;
import header.Items;

import org.junit.Test;

import edit.FileHandler;
import junit.framework.TestCase;

public class TestShield extends TestCase{
	
	Items name;
	FileHandler file=new FileHandler();
	
	@Test
	public void test() {
		name=file.ShieldName();
		assertEquals(FileHandler.Shield,name );
	}

}

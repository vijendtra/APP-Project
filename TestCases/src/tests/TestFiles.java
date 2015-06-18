package tests;

import static org.junit.Assert.*;
import junit.framework.TestCase;
import header.Items;

import org.junit.Test;

import edit.FileHandler;

public class TestFiles extends TestCase{

	Items name, name1;
	FileHandler file=new FileHandler();
	@Test
	public void test() {
		name=file.ArmorName();
		assertEquals(FileHandler.armor,name );
		
	}
	
	@Test
	public void test1() {
		name1=file.ShieldName();
		assertEquals(FileHandler.Shield,name );
	}

}

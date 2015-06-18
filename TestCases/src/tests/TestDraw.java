package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import edit.FileHandler;

public class TestDraw extends TestClass{

	String name;
	FileHandler file=new FileHandler();
	
	
	@Test
	public void testFileName() {
	name=file.FileName();
	assertEquals(FileHandler.fName,name );
	
	}
}

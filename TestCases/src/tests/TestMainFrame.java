package tests;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;

import edit.FileHandler;

public class TestMainFrame extends TestCase{

	String name;
	FileHandler file=new FileHandler();
	
	
	@Test
	public void testFileName() {
	name=file.FileName();
	assertEquals(FileHandler.fName,name );
	
	}
	
}

package tests;
import static org.junit.Assert.*;

import org.junit.Test;

import edit.FileHandler;
import junit.framework.TestCase;

public class TestClass extends TestCase{

	String name;
	FileHandler file=new FileHandler();
	
	@Test
	public void test() {
		name=file.RaceClass();
		assertEquals(FileHandler.rClass,name );
	}

}

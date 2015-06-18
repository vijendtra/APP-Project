package tests;
import header.*;
import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;

public class TestChestHelmet extends TestCase{
ChestItemFactory chest= new ChestItemFactory();
ChestItems item=null;
	@Test
	public void test() {
		item=chest.setItem("Helmet", 3);
		assertEquals(item.getName(), "Helmet");
		
	}

}

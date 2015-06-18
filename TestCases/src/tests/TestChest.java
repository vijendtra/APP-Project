package tests;

import static org.junit.Assert.*;
import junit.framework.TestCase;
import header.ChestItemFactory;
import header.ChestItems;

import org.junit.Test;

public class TestChest extends TestCase{

	ChestItemFactory chest= new ChestItemFactory();
	ChestItems item=null;
		@Test
		public void testArmor() {
			item=chest.setItem("Armor", 2);
			String name=item.getName();
			assertEquals(item.getName(), name);
			
		}
		@Test
		public void testBelt() {
			item=chest.setItem("Belt", 2);
			assertEquals(item.getName(), "Belt");
			
		}


}

package tests;

import static org.junit.Assert.*;
import junit.framework.TestCase;
import header.ChestItemFactory;
import header.ChestItems;

import org.junit.Test;

public class TestChestBoots extends TestCase {

	ChestItemFactory chest= new ChestItemFactory();
	ChestItems item=null;
		@Test
		public void test() {
			item=chest.setItem("Boots", 3);
			assertEquals(item.getName(), "Boots");
			
		}

}

package tests;

import static org.junit.Assert.*;
import header.ChestItemFactory;
import header.ChestItems;
import junit.framework.TestCase;

import org.junit.Test;

public class TestChestShield extends TestCase{

	ChestItemFactory chest= new ChestItemFactory();
	ChestItems item=null;
		@Test
		public void test() {
			item=chest.setItem("Shield", 3);
			String name=item.getName();
			assertEquals(item.getName(), name);
			
		}

}

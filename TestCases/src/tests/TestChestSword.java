package tests;

import static org.junit.Assert.*;
import junit.framework.TestCase;
import header.ChestItemFactory;
import header.ChestItems;

import org.junit.Test;

public class TestChestSword extends TestCase{

	ChestItemFactory chest= new ChestItemFactory();
	ChestItems item=null;
		@Test
		public void test() {
			item=chest.setItem("SwordAndBow", 3);
			String name=item.getName();
			assertEquals(item.getName(), name);
			
		}

}

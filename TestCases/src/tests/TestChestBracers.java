package tests;

import static org.junit.Assert.*;
import junit.framework.TestCase;
import header.ChestItemFactory;
import header.ChestItems;

import org.junit.Test;

public class TestChestBracers extends TestCase{
	ChestItemFactory chest= new ChestItemFactory();
	ChestItems item=null;
		@Test
		public void test() {
			item=chest.setItem("Bracers", 3);
			assertEquals(item.getName(), "Bracers");
			
		}

}

package tests;

import static org.junit.Assert.*;
import header.Chest;
import header.ChestBuilder;
import header.ChestDirector;
import header.PlayerChest;

import org.junit.Test;

public class TestTypeItem {

	ChestDirector cd=new ChestDirector();
	ChestBuilder playerchest=new PlayerChest(3);
	Chest cc;
	@Test
	public void testShield() {
		cd.setChestBuilder(playerchest);
		cd.ConstructChest();
		cc=cd.getChest();
		String name=cc.shield.getName();
		assertEquals(cc.shield.getName(), name);
	
		
	}
	public void testArmor() {
		cd.setChestBuilder(playerchest);
		cd.ConstructChest();
		cc=cd.getChest();
		String name=cc.armor.getName();
		assertEquals(cc.armor.getName(), name);
	
		
	}

}

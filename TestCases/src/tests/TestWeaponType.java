package tests;

import static org.junit.Assert.*;
import header.Chest;
import header.ChestBuilder;
import header.ChestDirector;
import header.PlayerChest;

import org.junit.Test;

public class TestWeaponType {

	ChestDirector cd=new ChestDirector();
	ChestBuilder playerchest=new PlayerChest(3);
	Chest cc;
	@Test
	public void test() {
		cd.setChestBuilder(playerchest);
		cd.ConstructChest();
		cc=cd.getChest();
		String name=cc.swordandbow.getName();
		assertEquals(cc.swordandbow.getName(), name);
	
		
	}

}
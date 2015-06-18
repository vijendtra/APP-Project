package tests;
import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;

import header.MapHandler;

public class TestMap extends TestCase{

	int width,height;
	String s="testmap.txt";
	MapHandler map=new MapHandler(s, 200);
	
	
	
	@Test
	public void test() {
		width=map.mapWidth;
		height=map.mapHeight;
		assertEquals(MapHandler.mapWidth, width);
		assertEquals(MapHandler.mapHeight,height);
	}

}

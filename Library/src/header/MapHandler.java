package header;

import java.io.*;
import java.awt.*;
import java.util.*;

/**
 * Class MapHandler is to display the map by reading the file Constructor of the
 * MapHandler takes the file name and tileSize to draw map on the desired frame.
 * MapHandler consist of other constructor to draw the new map with user defined
 * size. Draw Method is designed to draw the map with graphics according to the
 * values provided isBlack method is used to locate the walls in the map for
 * player and opponent. save method is used to save the map into a file. The
 * MapHandler uses the buffered Reader to Read the file and assign values to the
 * variables declared
 * 
 * @param x
 *            ,y-coordinates of x and y of map
 * @param mapHeight
 *            -height of the map
 * @param mapWidth
 *            - width of the map
 * @return map
 * @author Team3
 * 
 */

public class MapHandler {

	public int[][] map;
	int x, y;
	int tileSize;
	public static int mapWidth;
	public static int mapHeight;
	String name;
	int playerX, playerY;
	int oX, oY;
	public int playerLvl;
	Map<Integer, Integer> exits; // = new HashMap<int, int>();

	public int getTileSize() {
		return tileSize;
	}

	public void setMap(int i, int j, int val) {
		map[i][j] = val;
	}

	public void findExits() {
		exits = new HashMap<Integer, Integer>();

		// First row
		for (int i = 0; i < mapWidth; i++)
			if (map[0][i] == 1)
				exits.put(0, i);

		// Last row
		for (int i = 0; i < mapWidth; i++)
			if (map[mapHeight - 1][i] == 1)
				exits.put(mapHeight - 1, i);

		// First column
		for (int j = 0; j < mapHeight; j++)
			if (map[j][0] == 1)
				exits.put(j, 0);

		// Last column
		for (int j = 0; j < mapHeight; j++)
			if (map[j][mapWidth - 1] == 1)
				exits.put(j, mapWidth - 1);

		
	}

	public boolean isExit(int x, int y) {
		if (exits.containsKey(x) && exits.containsValue(y))
			return true;
		else
			return false;
	}
	//The maphandler takes the clone of the map and sets the height, width, tilesize of the map
		// and the player position
	public MapHandler(MapHandler clone) {
		this.map = clone.map;
		this.mapHeight = clone.mapHeight;
		this.mapWidth = clone.mapWidth;
		this.name = clone.name;
		this.playerX = clone.playerX;
		this.playerY = clone.playerY;
		this.tileSize = clone.tileSize;
		this.x = clone.x;
		this.y = clone.y;
	}
	//Maphandler sets the height, width, tilesize of the map
	public MapHandler(int h, int w, String name, int tile) {
		this.mapHeight = w;
		this.mapWidth = h;
		this.name = name;
		this.tileSize = tile;
		

		map = new int[mapHeight][mapWidth];
		// Arrays.fill(map, 1);
		for (int row = 0; row < mapHeight; row++) {
			for (int col = 0; col < mapWidth; col++)
				map[row][col] = 1;
		}

		try {
			File f = new File(name);
			if (!f.exists())
				f.createNewFile();
		} catch (Exception e) {
		}

		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(name));
			String output = "" + mapWidth;
			br.write(output);
			br.newLine();
			output = "" + mapHeight;
			br.write(output);
			br.newLine();
			for (int row = 0; row < mapHeight; row++) {
				for (int col = 0; col < mapWidth; col++) {
					output = "" + map[row][col] + " ";
					br.write(output);
				}
				br.newLine();
			}
			br.close();
		} catch (Exception e) {
		}
	}
	//checks if the tile is a wall for the opponent
	public boolean isOpponentisBlack(int x, int y) {

		if (map[x][y] == 0)
			return true;
		else
			return false;

	}
	//checks if the tile is a wall
	public boolean isBlack(int x, int y) {
		if (map[x][y] == 0)
			return true;
		else
			return false;
	}
	//Maphandler constructor which will set the tilesize
	public MapHandler(String s, int tileSize) {
		this.name = s;
		this.tileSize = tileSize;

		try {
			BufferedReader br = new BufferedReader(new FileReader(s));
			mapWidth = Integer.parseInt(br.readLine());
			mapHeight = Integer.parseInt(br.readLine());
			map = new int[mapHeight][mapWidth];

			String delimiters = " ";
			for (int row = 0; row < mapHeight; row++) {
				String line = br.readLine();
				String[] tokens = line.split(delimiters);
				for (int col = 0; col < mapWidth; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
					if (map[row][col] == 2) {
						playerX = row;
						playerY = col;
					}
					if (map[row][col] == 3) {
						oX = row;
						oY = col;
						
					}
				}
			}
			br.close();
		} catch (Exception e) {
		}
	}
	// this method will save the map
	public void save() {
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(name));
			String output = "" + mapWidth;
			br.write(output);
			br.newLine();
			output = "" + mapHeight;
			br.write(output);
			br.newLine();
			for (int row = 0; row < mapHeight; row++) {
				for (int col = 0; col < mapWidth; col++) {
					output = "" + map[row][col] + " ";
					br.write(output);
				}
				br.newLine();
			}
			br.close();
		} catch (Exception e) {
		}
	}

	public int getPlayerX() {
		return playerX;
	}

	public int getPlayerY() {
		return playerY;
	}

	public int getoX() {
		return oX;
	}

	public int getoY() {
		return oY;
	}

	public void update() {
	}
	//the draw method draws the map onto the frame
	public void draw(Graphics2D g) {
		for (int row = 0; row < mapHeight; row++) {
			for (int col = 0; col < mapWidth; col++) {
				int rc = map[row][col];
				if (rc == 0)
					g.setColor(Color.BLACK);
				if (rc == 1)
					g.setColor(Color.WHITE);
				if (rc == 5)
					g.setColor(Color.YELLOW);

				g.fillRect(x + col * tileSize, y + row * tileSize, tileSize,
						tileSize);

			}
		}

		for (int row = 0; row < mapHeight; row++) {
			for (int col = 0; col < mapWidth; col++) {
				g.setColor(g.getColor().darker());
				g.drawRect(x + col * tileSize, y + row * tileSize, tileSize,
						tileSize);
			}
		}
	}
	//the draw method draws the map onto the frame
	public void drawMap(Graphics2D g) {
		for (int row = 0; row < mapHeight; row++) {
			for (int col = 0; col < mapWidth; col++) {
				int rc = map[row][col];
				if (rc == 0)
					g.setColor(Color.BLACK);
				if (rc == 1)
					g.setColor(Color.WHITE);

				if (rc == 2)
					g.setColor(Color.GREEN);

				if (rc == 3)
					g.setColor(Color.RED);
				if (rc == 5)
					g.setColor(Color.YELLOW);
				g.fillRect(x + col * tileSize, y + row * tileSize, tileSize,
						tileSize);

			}
		}

		for (int row = 0; row < mapHeight; row++) {
			for (int col = 0; col < mapWidth; col++) {
				g.setColor(g.getColor().darker());
				g.drawRect(x + col * tileSize, y + row * tileSize, tileSize,
						tileSize);
			}
		}
	}

}

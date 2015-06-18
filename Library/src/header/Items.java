package header;

import java.io.*;

/**
 * Items class implements serialize where it consist of the properties of the
 * player items method getName, getHitPoints will return the name and hit points
 * Inner class builder is the design pattern where different types of the items
 * are defined with the builder methods hitPoints,name are used to set the
 * values of the items using the builder
 * 
 * @return name ,HitPoints
 * @author Team3
 * 
 */
// General Properties of every object (can be extended)
public class Items implements Serializable {

	private int hp;
	private String name;

	public String getName() {
		return name;
	}

	public static class Builder {
		private int hp;
		private String name;

		public Builder hp(int hp) {
			this.hp = hp;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Items build() {
			return new Items(this);
		}
	}

	private Items(Builder builder) {
		this.hp = builder.hp;
		this.name = builder.name;
	}

}

package sort.graphics;

public class Colors {

	private static String[] names = { "BACKGROUND", "GRID", "GRID_CELL", "SCORE", "NEW_GAME", "TRY_AGAIN", "DARK_FONT",
			"MEDIUM_FONT", "LIGHT_FONT", "TILE_2", "TILE_4", "TILE_8", "TILE_16", "TILE_32", "TILE_64", "TILE_128",
			"TILE_256", "TILE_512", "TILE_1024", "TILE_2048" };
	private static int[] colors = { 0xfffaf8ef, 0xff776e65, 0xffbbada0, 0xffbbada0, 0xff8f7a66, 0, 0, 0, 0, 0xffeee4da,
			0xffede0c8, 0xfff2b179, 0xfff59563, 0xfff67c5f, 0xfff65e3b, 0xffedcf72, 0xffedcc61, 0xffedc850, 0xffedc53f,
			0xffedc22e };

	public Colors() {
	}

	//    WARNING:  SEARCH ALGORITHM   
	// --------------------------------
	// TO BE CALLED IN CONSTRUCTOR ONLY
	// --------------------------------
	// ELSEWHERE ACCESS STRING DIRECTLY

	public static int getColor(String object) {
		for (int i = 0; i < names.length; i++)
			if (names[i].equals(object)) return colors[i];
		return 0xffff00ff;
	}

	public static int getColor(int index) {
		return colors[index];
	}

}

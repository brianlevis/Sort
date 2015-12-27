package com.brianlevis.sort;

import java.util.Random;

import com.brianlevis.sort.graphics.Screen;
import com.brianlevis.sort.graphics.tile.Tile;
import com.brianlevis.sort.input.Keyboard;

public class FrameManager {

    Random random = new Random();

    private Tile[] tiles;

    public static final int xOffset = 50;
    public static final int yOffset = 220;

    public static final int TILE_SIZE = 100;

    public static final int TILE_GAP = 10;
    public FrameManager(Keyboard keyboard) {
	generateTiles(8, new String[] { "insertion", "selection", "insertion", "selection", "insertion", "insertion",
	        "selection", "insertion" });
	// generateTiles(1, new String[] { "insertion" });

    }

    public void update() {
    }

    private void generateTiles(int tileCount, String[] names) {
	tiles = new Tile[tileCount];
	int hScale = 1, wScale = 1;

	if (2 <= tileCount && tileCount <= 9) {
	    hScale = tileCount < 7 ? 2 : 3;
	    wScale = tileCount < 5 ? 2 : 3;
	}

	int height = Frame.getWindowHeight() / hScale;
	int width = Frame.getWindowWidth() / wScale;
	for (int i = 0; i < tileCount; i++) {
	    tiles[i] = new Tile(height, width, i % hScale * width, i / hScale * height, names[i]);
	}
	Screen.generateBackground(hScale, wScale);
    }

}
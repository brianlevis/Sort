package com.brianlevis.sort;

import java.util.Random;

import com.brianlevis.sort.graphics.Screen;
import com.brianlevis.sort.graphics.Whooper;
import com.brianlevis.sort.graphics.tile.Tile;
import com.brianlevis.sort.input.Mouse;

public class FrameManager {

    Random random = new Random();

    private Tile[] tiles;

    public static final int xOffset = 50;
    public static final int yOffset = 220;

    private final String[] names = { "insertion", "selection", "selection", "selection", "selection", "selection",
            "selection", "selection" };
    private Whooper whooper;

    public FrameManager(Mouse mouse) {
        whooper = new Whooper(mouse);
        generateTiles();
    }

    public void update() {
        Tile.update();
        whooper.whoop();
    }

    private void generateTiles() {
        int tileCount = names.length;
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
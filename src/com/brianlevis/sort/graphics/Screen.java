package com.brianlevis.sort.graphics;

import com.brianlevis.sort.Frame;
import com.brianlevis.sort.graphics.tile.Element;
import com.brianlevis.sort.graphics.tile.Tile;

public class Screen {

	public int width, height;
	public int[] pixels;
	public static int[] background;

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		background = new int[width * height];
	}

	public void clear() {
		pixels = background.clone();
	}

	public static void generateBackground(int hScale, int wScale) {
		int height = Frame.getWindowHeight();
		int width = Frame.getWindowWidth();

		for (int i = 0; i < width * height; i++)
			background[i] = 0xff000000;

		for (int y = 0; y < hScale; y++)
			for (int x = 0; x < width; x++) {
				background[y * height / hScale * width + x] = 0xffffffff;
				background[y * height / hScale * width + (height / hScale - 1) * width + x] = 0xffffffff;
			}
		for (int x = 0; x < wScale; x++)
			for (int y = 0; y < height; y++) {
				background[x * width / wScale + y * width] = 0xffffffff;
				background[(x + 1) * width / wScale - 1 + y * width] = 0xffffffff;
			}
	}
	
	// Draws rectangle from bottom up
	public void drawBar(int height, int width, int xPosition, int yPosition, int color) {
		for(int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				pixels[xPosition + x + (yPosition - y) * this.width] = color;
	}

	public void renderTile(Tile tile) {
		int control = 0;
		//drawBar(300, 20, 0, height - 1, 0xffff00ff);
		
		for (Element element : tile.algorithm.elements) {
			drawBar(5 * element.value, 10, tile.xPosition + control * 11 + 5, tile.yPosition + tile.height - 1, element.color);
			control++;
		}
		/*
		 * int height = tile.height; int width = tile.width; int xPosition =
		 * tile.xPosition; int yPosition = tile.yPosition; for (int y = 0; y <
		 * height; y++) { for (int x = 0; x < width; x++) { int col =
		 * tile.pixels[width * y + x]; if (col != 0xffff00ff) pixels[this.width
		 * * (yPosition + y) + xPosition + x] = col; } }
		 */
	}

}

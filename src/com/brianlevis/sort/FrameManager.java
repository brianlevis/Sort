package com.brianlevis.sort;

import java.util.Random;

import com.brianlevis.sort.graphics.Colors;
import com.brianlevis.sort.graphics.Screen;
import com.brianlevis.sort.graphics.tile.Location;
import com.brianlevis.sort.graphics.tile.NumberTile;
import com.brianlevis.sort.graphics.tile.ScoreTile;
import com.brianlevis.sort.graphics.tile.Tile;
import com.brianlevis.sort.graphics.tile.VoidTile;
import com.brianlevis.sort.input.Keyboard;

public class FrameManager {

	Random random = new Random();

	private Keyboard keyboard;

	private Tile[] tiles;

	public static final int xOffset = 50;
	public static final int yOffset = 220;

	public static final int TILE_SIZE = 100;

	public static final int TILE_GAP = 10;
	private final int TILE_BORDER = 15;

	private boolean keyReleased = true;
	private boolean hasTileMoved = false;

	public FrameManager(Keyboard keyboard) {
		this.keyboard = keyboard;

		generateTiles(8, new String[] { "insertion", "selection", "insertion", "selection", "insertion", "insertion",
			"selection", "insertion" });
		//generateTiles(1, new String[] { "insertion" });

	}

	public void update() {
		/*
		 * int direction = keyboard.direction; if (direction >= 0 && keyReleased
		 * && isDoneMoving() && !hasTileMoved) { keyReleased = false; if
		 * (!canMove()) reset(); else makeMove(direction); } else { if
		 * (direction == -1) keyReleased = true; if (isDoneMoving()) { if
		 * (hasTileMoved) newTile(); hasTileMoved = false; } }
		 */

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

	public NumberTile getTile(int xGrid, int yGrid) {
		for (NumberTile tile : NumberTile.numberTiles)
			if (tile.xGrid == xGrid && tile.yGrid == yGrid)
				return tile;
		return null;
	}

	public NumberTile getNextTile(int xGrid, int yGrid, int direction) {
		switch (direction) {
			case 0:
				for (int i = yGrid - 1; i >= 0; i--)
					if (getTile(xGrid, i) != null)
						return getTile(xGrid, i);
				break;
			case 1:
				for (int i = xGrid + 1; i < 4; i++)
					if (getTile(i, yGrid) != null)
						return getTile(i, yGrid);
				break;
			case 2:
				for (int i = yGrid + 1; i < 4; i++)
					if (getTile(xGrid, i) != null)
						return getTile(xGrid, i);
				break;
			case 3:
				for (int i = xGrid - 1; i >= 0; i--)
					if (getTile(i, yGrid) != null)
						return getTile(i, yGrid);
				break;
		}
		return null;
	}

	public Location getLastSpace(int xGrid, int yGrid, int direction) {
		Location location = new Location();
		switch (direction) {
			case 0:
				for (int i = yGrid - 1; i >= 0; i--)
					if (getTile(xGrid, i) == null)
						location.setLocation(xGrid, i);
				break;
			case 1:
				for (int i = xGrid + 1; i < 4; i++)
					if (getTile(i, yGrid) == null)
						location.setLocation(i, yGrid);
				break;
			case 2:
				for (int i = yGrid + 1; i < 4; i++)
					if (getTile(xGrid, i) == null)
						location.setLocation(xGrid, i);
				break;
			case 3:
				for (int i = xGrid - 1; i >= 0; i--)
					if (getTile(i, yGrid) == null)
						location.setLocation(i, yGrid);
				break;
		}
		if (location.xPosition < 0 || location.yPosition < 0)
			return null;
		else
			return location;
	}

	public void newTile() {
		System.out.print("Placing new tile...");
		int x, y;
		do {
			int number = random.nextInt(16);
			x = number % 4;
			y = number / 4;
		} while (getTile(x, y) != null);
		int number = 2;
		if (random.nextInt(10) == 9)
			number = 4;
		placeTile(x, y, number);
		System.out.println("Success!");
	}

	public void placeTile(int xGrid, int yGrid, int number) {
		new NumberTile(xGrid, yGrid, number);
	}

	public void doubleTile(NumberTile tile) {
		tile.selfDestruct = true;
		new NumberTile(tile.xGrid, tile.yGrid, tile.number * 2);
	}

	public void moveTile(NumberTile tile, int xGrid, int yGrid) {
		tile.setPosition(xGrid, yGrid);
		hasTileMoved = true;
	}

	public void moveTile(NumberTile tile, int direction) {
		if (tile == null)
			return;
		NumberTile nextTile = getNextTile(tile.xGrid, tile.yGrid, direction);
		Location lastSpace = getLastSpace(tile.xGrid, tile.yGrid, direction);
		if (nextTile != null && tile.number == nextTile.number) {
			tile.selfDestruct = true;
			moveTile(tile, nextTile.xGrid, nextTile.yGrid);
			doubleTile(tile);
			return;
		}
		if (lastSpace != null)
			moveTile(tile, lastSpace.xPosition, lastSpace.yPosition);
	}

	public void makeMove(int direction) {
		for (int i = 0; i < 16; i++) {
			int x = 0, y = 0;
			switch (direction) {
				case 0:
					x = i / 4;
					y = i % 4;
					break;
				case 1:
					x = 3 - i % 4;
					y = i / 4;
					break;
				case 2:
					x = i / 4;
					y = 3 - i % 4;
					break;
				case 3:
					x = i % 4;
					y = i / 4;
					break;
			}

			moveTile(getTile(x, y), direction);

		}

	}

	public boolean isDoneMoving() {
		for (NumberTile tile : NumberTile.numberTiles)
			if (tile.selfDestruct) {
				System.out.println("is not done moving");
				return false;
			}
		return true;
	}

	public boolean canMove() {
		if (NumberTile.numberTiles.size() < 16)
			return true;
		else
			for (NumberTile tile : NumberTile.numberTiles)
				System.out.println(tile.selfDestruct);
		int test = NumberTile.numberTiles.size();
		for (int x = 0; x < 4; x++)
			for (int y = 0; y < 3; y++)
				if (getTile(x, y).number == getTile(x, y + 1).number)
					return true;
		for (int y = 0; y < 4; y++)
			for (int x = 0; x < 3; x++)
				if (getTile(x, y).number == getTile(x + 1, y).number)
					return true;
		return false;
	}

	public void reset() {
		// done to avoid throwing a ConcurrentModificationException
		int n = NumberTile.numberTiles.size();
		for (int i = 0; i < n; i++) {
			NumberTile.numberTiles.get(0).remove();
		}
		newTile();
	}
}
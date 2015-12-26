package sort.graphics.tile;

import java.util.ArrayList;
import java.util.List;

import sort.FrameManager;
import sort.graphics.Colors;

public class NumberTile extends Tile {

	public static List<NumberTile> numberTiles = new ArrayList<NumberTile>();

	public int xGrid, yGrid, number;

	public NumberTile(int xGrid, int yGrid, int number) {
		super(FrameManager.TILE_SIZE, FrameManager.TILE_SIZE, xGrid * (FrameManager.TILE_SIZE + FrameManager.TILE_GAP)
				+ FrameManager.xOffset, yGrid * (FrameManager.TILE_SIZE + FrameManager.TILE_GAP) + FrameManager.yOffset,
				"TILE_" + Integer.toString(number));
		int n = -1;
		if (numberTiles.isEmpty())
			n = 0;
		else
			do
				n++;
			while (numberTiles.get(n).yPosition * 4 + numberTiles.get(n).xPosition > yPosition * 4 + xPosition
					&& n < numberTiles.size() - 1);

		numberTiles.add(n, this);
		this.xGrid = xGrid;
		this.yGrid = yGrid;
		this.number = number;
	}

	public void remove() {
		NumberTile.numberTiles.remove(this);
		Tile.tiles.remove(this);
	}

	public void setPosition(int xGrid, int yGrid) {
		this.xGrid = xGrid;
		this.yGrid = yGrid;
		this.xPosition = xGrid * (FrameManager.TILE_SIZE + FrameManager.TILE_GAP) + FrameManager.xOffset;
		this.yPosition = yGrid * (FrameManager.TILE_SIZE + FrameManager.TILE_GAP) + FrameManager.yOffset;
	}

}
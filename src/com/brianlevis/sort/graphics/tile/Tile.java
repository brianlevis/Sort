package com.brianlevis.sort.graphics.tile;

import java.util.ArrayList;
import java.util.List;

import com.brianlevis.sort.algorithms.Algorithm;
import com.brianlevis.sort.algorithms.Insertion;
import com.brianlevis.sort.algorithms.Selection;
import com.brianlevis.sort.graphics.Screen;

public class Tile {

	public static List<Tile> tiles = new ArrayList<Tile>();

	public int[] pixels;
	
	public Algorithm algorithm;

	public String name;
	public int height, width, xPosition, yPosition;
	private int bar = 10;
	
	public boolean selfDestruct = false;

	// **********************************
	
	public static int[] sample = {6, 10, 9, 4, 3, 8, 7, 2, 1, 3, 9, 4, 3, 8, 7};
	
	// **********************************
	
	public Tile(int height, int width, int xPosition, int yPosition, String name) {
		tiles.add(this);
		this.height = height;
		this.width = width;
		this.name = name;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		
		switch(name){
			case "insertion":
				algorithm = new Insertion();
			case "selection":
				algorithm = new Selection();
		}

		pixels = new int[height * width];

		
	}

	public static void update() {
		for (Tile tile : tiles) {
			tile.algorithm.next();
		}
	}

	public static void render(Screen screen) {
		for (Tile tile : tiles)
			screen.renderTile(tile);
	}

}
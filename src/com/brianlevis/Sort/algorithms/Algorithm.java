package com.brianlevis.src.sort.algorithms;

import java.util.ArrayList;
import java.util.List;

import com.brianlevis.src.sort.graphics.tile.Element;
import com.brianlevis.src.sort.graphics.tile.Tile;

public class Algorithm {

	private static int COLOR_WHITE = 0xffffffff;
	private static int COLOR_GREEN = 0xff00ff00;
	private static int COLOR_RED = 0xffff0000;

	private int currentIndex, action = 0, sortCount = 1;

	private String title;
	private int[] data;
	private int iteration = 0;
	private Element element;
	public List<Element> elements = new ArrayList<Element>();
	private boolean check = true;

	public Algorithm(String title) {
		this.title = title;
		data = Tile.sample.clone();
		for (int i = 0; i < data.length; i++) {
			elements.add(new Element(data[i]));
		}
		element = elements.get(0);
		element.setColor(COLOR_GREEN);
	}

	boolean check() {
		for (int i = 1; i < data.length; i++) {
			if (data[i] < data[i - 1])
				return false;
		}
		return true;
	}

	public void moveForward(int pos) {

	}

	public int get(int index) {
		element.setColor(COLOR_WHITE);
		element = elements.get(index);
		element.setColor(COLOR_RED);
	}
	
	

	public void moveLeft(int index) {
		element.setColor(COLOR_RED);
		elements.remove(element);
		//element.setColor(COLOR_WHITE);
		elements.add(index - 1, element);
	}

	public void update() {
		if (check && !check()) {
			switch (action) {
				case 0: // Find next element
					lookAt(sortCount);
					currentIndex = sortCount;
					System.out.println("Looking at element " + currentIndex);
					action = 1;
					break;
				case 1: // Move element
					System.out.println("Comparing index " + currentIndex + " to index " + (currentIndex + 1));
					element = elements.get(currentIndex + 1);
					if (elements.get(currentIndex).value < element.value) {
						moveLeft(currentIndex);
						System.out.println("Moving element " + currentIndex + " left");
						currentIndex--;
					} else {
						action = 0;
						sortCount++;
					}
			}
			iteration++;
		}
	}
}

package com.brianlevis.sort.algorithms;

import java.util.ArrayList;
import java.util.List;

import com.brianlevis.sort.graphics.tile.Element;
import com.brianlevis.sort.graphics.tile.Tile;

public class Algorithm {

	private static int COLOR_WHITE = 0xffffffff;
	private static int COLOR_GREEN = 0xff00ff00;
	private static int COLOR_RED = 0xffff0000;

	protected boolean sorted;
	
	private String title;
	private int[] data;
	protected int iteration = 0;
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
	
	protected int get(int index) {
		return elements.get(index).value;
	}
	
	protected int select(int index) {
		elements.get(index).setColor(COLOR_RED);
		return get(index);
	}
	
	protected void deselect(int index) {
		elements.get(index).setColor(COLOR_WHITE);
	}
	
	protected void highlight(int index) {
		elements.get(index).setColor(COLOR_GREEN);
	}
	
	public void next(){
	}
}

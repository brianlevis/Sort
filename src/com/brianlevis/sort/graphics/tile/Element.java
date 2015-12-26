package com.brianlevis.sort.graphics.tile;


public class Element {

	public int value;
	//public int position;
	public int color = 0xffffffff;

	public Element(int value) {
		this.value = value;
	}

	public void setColor(int color) {
		//this.value = value;
		//this.position = position;
		this.color = color;
	}

}

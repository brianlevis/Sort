package com.brianlevis.sort.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.brianlevis.sort.graphics.tile.Element;

public class Algorithm {

    private static int COLOR_WHITE = 0xffffffff;
    private static int COLOR_GREEN = 0xff00ff00;
    private static int COLOR_RED = 0xffff0000;

    public boolean sorted;

    private Random random = new Random();
    protected int iteration = 0;
    public List<Element> elements = new ArrayList<Element>();

    public Algorithm() {
        int[] data = generateData(50, 50);
        for (int i = 0; i < data.length; i++) {
            elements.add(new Element(data[i]));
        }
    }

    private int[] generateData(int size, int range) {
        int[] data = new int[size];
        for (int i = 0; i < size; i++)
            data[i] = random.nextInt(range + 1);
        return data;
    }

    protected void move(int origin, int destination) {
        elements.add(destination, elements.remove(origin));
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

    public void next() {
    }
}

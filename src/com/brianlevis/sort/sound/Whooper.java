package com.brianlevis.sort.sound;

import java.util.Random;

import com.brianlevis.sort.graphics.tile.Tile;

public class Whooper extends Player {

    private Random random = new Random();

    public Whooper() {
    }

    public void whoop() {
        if (Tile.whoop)
            playClip("/whoop" + Integer.toString(random.nextInt(11)) + ".wav");
    }
}
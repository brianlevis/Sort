package com.brianlevis.sort.graphics;

import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.brianlevis.sort.input.Mouse;

public class Whooper {

    private Random random = new Random();
    private Mouse mouse;
    private int whoopCount = 0;

    public Whooper(Mouse mouse) {
        this.mouse = mouse;
    }

    public void whoop() {
        /*
        if (whoopCount == 500) {
            System.out.print("Garbage Collecting...");
            System.gc();
            System.out.println(" Done!");
            whoopCount = 0;
        }*/
        
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(
                    getClass().getResource(("/whoop" + Integer.toString(random.nextInt(11)) + ".wav"))));
            clip.start();
            whoopCount++;
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
}
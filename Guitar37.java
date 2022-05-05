// Jordan White
// 1/21/2020
// Take Home Assessment 2: Guitar Hero
//
// Guitar37 creates a representation of a 37 string guitar
// and allows frequencies to be played using keys of a keyboard

import java.util.*;

public class Guitar37 implements Guitar {
    private static final String KEYBOARD =
        "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout
    private GuitarString[] samples;
    private int time;
    private static final int OFFSET = -24;

    // Sets up GuitarStrings data to have data spots for each keyboard key
    // and fills data spots with frequencies for each key
    public Guitar37() {
        samples = new GuitarString[KEYBOARD.length()];
        for (int i = 0; i < samples.length; i++) {
            samples[i] = new GuitarString(440.0 * Math.pow(2, (i - 24.0) / 12.0));
        }
    }

    // Takes pitch as a parameter, and samples the pitch if it can be played
    // but ignores pitch if it otherwise cannot be played
    public void playNote(int pitch) {
        if (pitch >= OFFSET && pitch < OFFSET + KEYBOARD.length()) {
            samples[pitch - OFFSET].pluck();
        }
    }

    // Takes char string as a parameter and returns true or false depending on if
    // string is a valid key
    public boolean hasString(char string) {
        return KEYBOARD.contains(string + "");
    }

    // Takes char string as a parameter, plays that string with pluck
    public void pluck(char string) throws IllegalArgumentException {
        if (!KEYBOARD.contains(string + "")) {
            throw new IllegalArgumentException();
        }
        samples[KEYBOARD.indexOf(string)].pluck();
    }

    // Adds up and returns sum of all sample values from strings
    public double sample() {
        double sampleTotal = 0.0;
        for (int i = 0; i < KEYBOARD.length(); i ++) {
            sampleTotal += samples[i].sample();
        }
        return sampleTotal;
    }

    // Iterates through each string through tic in the sample collection and increases time by 1
    // each time
    public void tic() {
        time ++;
        for (int i = 0; i < KEYBOARD.length(); i ++) {
            samples[i].tic();
        }
    }

    // Returns time resultant from tics
    public int time() {
        return time;
    }
}

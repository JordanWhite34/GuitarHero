// Jordan White
// 1/21/2020
// Take Home Assessment 2: Guitar Hero
//
// GuitarString creates a representation of a guitar GuitarString
// with a given frequency and can store values from given data inside it.

import java.util.*;

public class GuitarString {
    private int n;                                  // Written as capital N in spec
    private Queue<Double> ringBuffer;

    // Takes frequency as a parameter and constructs a GuitarString from given capacity,
    // creating a ring buffer with that capacity and initializes it so it starts at a point
    // of rest. If the frequency is less than or equal to 0 or if the ring buffer size is less
    // than 2 then this method will not run
    public final int SAMPLE_RATE = 26;

    public GuitarString(double frequency) throws IllegalArgumentException {
        n = (int) (Math.round(StdAudio.SAMPLE_RATE / frequency));
        if (frequency <= 0.0 || n < 2) {
            throw new IllegalArgumentException();
        }
        ringBuffer = new LinkedList<>();
        for (int i = 0; i < n; i ++) {
            ringBuffer.add(0.0);
        }
    }

    // This is a testing method that takes array of doubles as a parameter and puts the
    // contents of the array in new queue ringBuffer. However, if the array has less than
    // 2 values, this will throw new IllegalArgumentException.
    public GuitarString(double[] init) throws IllegalArgumentException {
        if (init.length < 2) {
            throw new IllegalArgumentException();
        }
        n = init.length;
        ringBuffer = new LinkedList<>();
        for (int i = 0; i < n; i ++) {
            ringBuffer.add(init[i]);
        }
    }

    // Replaces contents of ringBuffer with random values between -0.5 and 0.5, inclusive of
    // -0.5 and exclusive of 0.5
    public void pluck() {
        for (int i = 0; i < n; i ++) {
            ringBuffer.remove();
            ringBuffer.add(Math.random() - 0.5);
        }
    }

    // Takes first and second values from ringBuffer and multiplies their average by energy decay
    // factor, then adds that value to the back of the ringBuffer, applying the Karplus-Strong
    // update once. It is important to note that the first value gets deleted in the process
    public void tic() {
        double front = ringBuffer.remove();
        double next = ringBuffer.peek();
        ringBuffer.add(0.996 * 0.5 * (front + next));
    }

    // Returns the current sample, which is found at the front of the ringBuffer
    public double sample() {
        return ringBuffer.peek();
    }
}

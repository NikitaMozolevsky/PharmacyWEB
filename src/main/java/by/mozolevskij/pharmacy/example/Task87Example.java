package by.mozolevskij.pharmacy.example;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class Task87Example {
    public static void main(String[] args) {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(5);

        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                }
            }).start();
        }
    }

    public synchronized int numberHandler(int number) {
        return number + 1;
    }
}

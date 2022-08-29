package by.mozolevskij.pharmacy.example;

public class ThreadExample extends Thread {

    public ThreadExample() {
        this.start();
    }

    public void run() {

        System.out.println(Thread.currentThread().getName() + " уступает свое место другим");
        Thread.yield();
        System.out.println(Thread.currentThread().getName() + " has finished executing.");
    }

    public static void main(String[] args) {
        new ThreadExample();
        new ThreadExample();
        new ThreadExample();
    }
}

    /*Вывод в консоль будет выглядеть так:

        Thread-0 уступает свое место другим
        Thread-1 уступает свое место другим
        Thread-2 уступает свое место другим
        Thread-1 закончил выполнение.
        Thread-0 закончил выполнение.
        Thread-2 закончил выполнение.*/
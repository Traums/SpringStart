package ru.mart.Practice.Tasks;

public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("This is executed in a separate thread.");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable());
        thread.start();
    }
}

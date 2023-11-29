package ru.mart.Practice.Thread;

public class SyncThreadMain {
    private static int counter;
    static Runnable runnable = new Runnable() {
        @Override
        public void run() {
                //counter = 0;
                for (int i = 0;i<5;i++){
                    synchronized (this) {
                        counter++;
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + counter);
                }
        }
    };
    public static void main(String[] args) {
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread1.start();
        thread2.start();
    }
}

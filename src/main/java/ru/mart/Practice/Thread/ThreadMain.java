package ru.mart.Practice.Thread;

import ru.mart.Practice.Thread.Notifier.MyThread;

import static java.util.stream.IntStream.range;

public class ThreadMain {
    private static final int INCREMENT_AMOUNT = 500;
    private static int counter = 0;
    public static void main(String[] args) {
        final Thread firstThread = createIncrementingConcyrrentThread(INCREMENT_AMOUNT);
        final Thread secondThread = createIncrementingConcyrrentThread(INCREMENT_AMOUNT);
        firstThread.start();
        secondThread.start();

        try {
            firstThread.join();
            secondThread.join();
        } catch (InterruptedException e) {
            System.out.println("Ошибка: Поток прерван");;
        }
        System.out.println(counter);

//        new MyThread().start();
//        new MyThread().start();
//        new MyThread().start();
    }

    private static Thread createIncrementingConcyrrentThread(final int incrementAmount) {
        return new Thread(()->range(0,incrementAmount).forEach(i->counter++));
    }
}

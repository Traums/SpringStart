package ru.mart.Practice.Tasks;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("This is executed in a separate thread and returns a result.");
        return 42;
    }

    public static void main(String[] args) throws Exception {
        MyCallable myCallable = new MyCallable();
        FutureTask<Integer> futureTask = new FutureTask<>(myCallable);

        Thread thread = new Thread(futureTask);
        thread.start();

        Integer result = futureTask.get(); // blocking call to get the result
        System.out.println("Result: " + result);
    }
}

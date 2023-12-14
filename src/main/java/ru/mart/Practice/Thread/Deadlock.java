package ru.mart.Practice.Thread;

public class Deadlock {
    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();
        //4.5.4 Что такое лямбда выражение? Зачем они нужны? Область видимости в лямбда выражениях? Что такое ссылка на метод?
        Thread thread1 = new Thread(()->{
            System.out.println(Thread.currentThread().getName() + " start");
            synchronized (lock1){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock2){

                }
            }
            System.out.println(Thread.currentThread().getName() + " end");
        },"Thread1");

        Thread thread2 = new Thread(()->{
            System.out.println(Thread.currentThread().getName() + " start");
            synchronized (lock2){
                synchronized (lock1){

                }
            }
            System.out.println(Thread.currentThread().getName() + " end");
        },"Thread2");

        thread1.start();
        thread2.start();
    }
}

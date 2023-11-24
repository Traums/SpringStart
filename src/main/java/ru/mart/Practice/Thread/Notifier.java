package ru.mart.Practice.Thread;

public class Notifier implements Runnable {
    private Message msg;

    public Notifier(Message m) {
        this.msg = m;
    }

    public void run() {
        try {
            Thread.sleep(1000);
            synchronized (msg) {
                msg.setMsg(Thread.currentThread().getName() + " Notifier work done");
                msg.notify();
                //msg.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

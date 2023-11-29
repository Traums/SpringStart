package ru.mart.Practice;

import lombok.extern.slf4j.Slf4j;
import ru.mart.Practice.Thread.Notifier.Message;
import ru.mart.Practice.Thread.Notifier.Notifier;
import ru.mart.Practice.Thread.Notifier.Waiter;
import ru.mart.Review.ClientStorage.ClientsStorage;
import ru.mart.Review.ClientStorage.Extended.ClientsStorageExtended;
import ru.mart.Review.ClientStorage.StorageFactory;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.LockSupport;

@Slf4j
public class PracticeApp2 {
    public void run(){
        //demoHeritation();
        demoThreads();
        //demoWriteArray();
        //demoRunnableAndCallable();
    }

    private void demoRunnableAndCallable() {
        //Runnable
        Runnable task = () -> {
            //Запаркуем текущий поток
            System.err.println("Will be Parked");
            LockSupport.park();
            // Как только нас распаркуют - начнём действовать
            System.err.println("Unparked");
        };
        try{
            Thread th = new Thread(task);
            th.start();
            Thread.sleep(2000);
            System.err.println("Thread state: " + th.getState());

            LockSupport.unpark(th);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void demoWriteArray() {
        CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>();

        list.add("A");
        list.add("B");
        list.add("C");

// Итерация без опасности ConcurrentModificationException даже при одновременном изменении списка
        for (Object element : list) {
            System.out.println(element);
        }
    }

    private void demoThreads() {
        Message msg = new Message("Обработка");
        Waiter waiter = new Waiter(msg);
        Notifier notifier = new Notifier(msg);

        Thread t1 = new Thread(waiter,"waiter");
        Thread t2 = new Thread(notifier,"notifier");

        t1.start();
        t2.start();

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            log.warn(e.getMessage());
        }
    }

    private void demoHeritation() {
        ClientsStorageExtended clientsStorageExtended = StorageFactory.getInstance().createObject(ClientsStorageExtended.class);
        clientsStorageExtended.clients.forEach(System.out::println);
        log.warn("\n\n");
        ClientsStorage clientsStorage = StorageFactory.getInstance().createObject(ClientsStorage.class);
        clientsStorage.clients.forEach(System.out::println);
        clientsStorage.getClient(1);
    }
}

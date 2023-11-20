package ru.mart.Practice;

import ru.mart.Practice.Compare.ClientComparator;
import ru.mart.Practice.Objects.Box;
import ru.mart.Review.Client.Client;
import ru.mart.Review.ClientStorage.ClientsStorage;
import ru.mart.Review.ClientStorage.StorageFactory;

import java.util.*;

public class PracticeApp {
    void run(){
        //demoCompare();// 4.5.2 Отличие Comparator и Comparable. Какой контракт Comparator должен соблюдать?
        //demoMatch();
        //demoPrimitiveStream();//4.6.11 Stream. Преобразование объектов
        demoLimitStream();// 4.6.13 Stream. Ограничение выборки
//        demoHashSet();//4.7.2 В чем отличия TreeSet от HashSet?
//        demoTreeSet();//4.7.2 В чем отличия TreeSet от HashSet?
        //demoStringSub();4.9.2 Как работает метод substring() класса String. Будет ли утечка памяти?
        //demoGenerics();//4.10.1 что такое Generics и зачем они появились в Java? Рассказать про стирание типов в java
    }

    private static void demoCompare() {
        ClientsStorage clientsStorage = StorageFactory.getInstance().createObject(10);
        //4.5.2 Отличие Comparator и Comparable. Какой контракт Comparator должен соблюдать?
        //Client реализует Comparable
        clientsStorage.clients.sort(Client::compareTo);
        clientsStorage.clients.forEach(System.out::println);
        //С помощью Comparator
        clientsStorage.clients.sort(new ClientComparator());
        clientsStorage.clients.forEach(System.out::println);
    }
    void demoMatch(){
        ClientsStorage clientsStorage = StorageFactory.getInstance().createObject(10);
        Boolean anyActive  = clientsStorage.clients.stream().anyMatch(Client::isActive);
        System.out.println(anyActive);

        clientsStorage.clients.stream().forEach(System.out::println);
        List<String> allOrders = clientsStorage.clients.stream().flatMap(client -> client.getOrders().stream()).toList();
        System.out.println(allOrders);
    }
    void demoPrimitiveStream(){
        ClientsStorage clientsStorage = StorageFactory.getInstance().createObject(10);
        long goldSum = clientsStorage.clients.stream().filter(client -> client.getGold() > 0).mapToLong(Client::getGold).sum();
    }
    void demoLimitStream(){
        ClientsStorage clientsStorage = StorageFactory.getInstance().createObject(10);
        //clientsStorage.clients.stream().limit(2).forEach(System.out::println);

        clientsStorage.clients.stream().sorted(new ClientComparator()).forEach(System.out::println);
        System.out.println("Результат\n");
        //clientsStorage.clients.stream().filter(client -> client.getGold() > 0).sorted(new ClientComparator()).takeWhile(client -> client.getGold() < 100).forEach(System.out::println);
        //clientsStorage.clients.stream().filter(client -> client.getGold() > 0).sorted(new ClientComparator()).dropWhile(client -> client.getGold() < 50).forEach(System.out::println);
    }
    void demoHashSet(){
        HashSet<Double> hashSet = new HashSet<>();
        // Пример использования HashSet
        System.out.println("HashSet");
        process(hashSet);
    }
    void demoTreeSet(){
        // Пример использования TreeSet
        TreeSet<Double> treeSet = new TreeSet<>();
        System.out.println("TreeSet");
        process(treeSet);
    }

    private static void process(Set<Double> set) {
        long startTime = System.currentTimeMillis();
        fillSet(set);
        long endTimeFill = System.currentTimeMillis() - startTime;
        startTime = System.currentTimeMillis();
        find(set);
        long endTimeSearch = System.currentTimeMillis() - startTime;
        startTime = System.currentTimeMillis();
        endTimeDelete(set);
        long endTimeDelete = System.currentTimeMillis() - startTime;
        System.out.println("Время заполнения(мс):" + endTimeFill + " Время поиска: " + endTimeSearch + " Время удаления: " + endTimeDelete);
        System.out.println("Размер:" + set.size());
    }
    private static void endTimeDelete(Set<Double> set){
        set.removeIf(element -> element % 2 == 0);
    }
    private static void find(Set<Double> set) {
        double random = Math.random() * 100;
        for(int i = 0;i< 100000000;i++){
            findDouble(set,random);
        }
    }

    private static void findDouble(Set<Double> set, Double searchNumber) {
        if(set.contains(searchNumber)){
            System.out.println("Элемент найден");
        }
    }

    private static void fillSet(Set<Double> set) {
        double random;
        for (int i = 0; i < 1000000; i++){
            random = Math.random() * 10000;
            set.add(random);
        }
    }
    void demoStringSub(){
        String example1 = "123000000000000000000";
        String example2 = new String("123000000000000000000");

        String substring1 = example1.substring(1);
        String substring2 = new String(example1.substring(1));
        System.out.println(substring1);
    }
    void demoGenerics(){
        Box integerBox = new Box<>(10);
        Box stringBox = new Box<>("string");
        System.out.println("Integer value: " + integerBox.getValue()); // Вывод содержимого integerBox
        System.out.println("String value: " + stringBox.getValue()); // Вывод содержимого stringBox
    }
}

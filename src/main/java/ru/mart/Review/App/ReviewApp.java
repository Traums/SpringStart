package ru.mart.Review.App;

import com.github.javafaker.Faker;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mart.Review.Client.Client;
import ru.mart.Review.ClientStorage.ClientsStorage;
import ru.mart.Review.ClientStorage.StorageFactory;
import ru.mart.Review.ExampleObjects.Phone;
import ru.mart.Review.Exceptions.FactorialException;
import ru.mart.Review.FunctionalInterface.*;
import ru.mart.Review.FunctionalInterface.Exception.MyExceptionFunction;
import ru.mart.Review.Printer.Printer;
import ru.mart.Review.Utill.ApplicationContextProvider;
import ru.mart.Review.Utill.FileAsCharSequence;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.mart.Review.Utill.Factorial.getFactorial;
@Slf4j
@Data
@Service
public class ReviewApp {
    //4.5.1 Что такое функциональные интерфейсы? В каких случаях вы можете подставить лямбда выражение вместо интерфейса?
    //4.5.3 Расскажите про встроенные функциональные интерфейсы Predicate, Consumer, Function, Supplier, UnaryOperator, BinaryOperator
    MyExceptionFunction<Integer, Integer,Exception> divideByFiveException = x -> {
        try {
            return x / 10;
        } catch (ArithmeticException e) {
            // обработка исключения
            log.error("Ошибка при вычислении: " + e.getMessage());
            return 0;
        }
    };
    MyFunction<Integer,Integer> divideByTen = x->{
        try{
            return x /10;
        }catch (ArithmeticException ex){
            log.error("Арифметическая ошибка: " + ex.getMessage());
            return 0;
        }
    };
    MyPredicate<Integer> predicate = x-> x > 5;
    MyConsumer<Integer> consumer = System.out::println;
    MyFunction<Integer,Integer> function = (x)-> Integer.valueOf(x.toString());
    MySupplier<Client> supplier = () -> {
        new Client();
        return Client.builder()
                .id( new Faker().number().randomDigit())
                .name(new Faker().name().firstName())
                .gold(new Faker().number().randomDigit())
                .isActive(new Faker().bool().bool())
                .build();
    };
    UnaryOperator<Integer> unaryOperator = x -> x * x;
    BinaryOperator<Integer> binaryOperator = (x,y)-> x * y;


    public void run() {
        //demoCalculate();//4.3.2 Собственное исключение
        //demoFunctionalInterfaceException(10);//4.3.3 Работа с исключениями при использовании лямбда-выражений
        //demoFunctionInterface();//4.5.1 Что такое функциональные интерфейсы? В каких случаях вы можете подставить лямбда выражение вместо интерфейса?
        //demoAllFunctionalInterface();//4.5.3 Расскажите про встроенные функциональные интерфейсы Predicate, Consumer, Function, Supplier, UnaryOperator, BinaryOperator
        //demoLambda();
        //demoCapture(); //4.5.5 Non/capture lambda чем отличаются?
        //demoNonCapture(); //4.5.5 Non/capture lambda чем отличаются?
        //readFileAsStream(); //4.6.2 Stream. Способы создания потока строк и потока из файла
        //demoCollectors();// 4.6.5 Stream. Конверсия  Collectors в список, множество, карту, коллекцию
        //demoStreamCollect();// 4.6.6 Stream. Операторы collect()
        demoReduce(); // 4.6.7 Stream. Операторы reduce()
        // | 4.6.8 Stream. Операторы поиска
        // | 4.6.9 Stream. Оператор sorted()
        // 4.6.12 Рассказать про StreamApi примитивных типов.
    }

    private void demoCalculate() {
        try{
            showFactorial(5);
        } catch (FactorialException e) {
            log.info("Ошибка вычисления факториала");
        }
    }
    void showFactorial(int number) throws FactorialException {
        System.out.println(getFactorial(number));
    }

    void demoFunctionInterface(){
        //Что такое функциональные интерфейсы?
        System.out.println(divideByTen.apply(34));
        //В каких случаях вы можете подставить лямбда выражение вместо интерфейса?
        //Лябмда
        Printer lambdaPrinter = m -> System.out.println(m);
        lambdaPrinter.print("Вывод лямбда");
        //FI
        Printer methodReferencePrinter = System.out::println;
        methodReferencePrinter.print("Вывод интерфейс");
        //Второй пример
        Map<String,Printer> printerHub = new HashMap<>();
        printerHub.put("lambda",lambdaPrinter);
        printerHub.put("FI",msg -> {
            System.out.println(msg);
        });
        printerHub.get("lambda").print("Map Print Lambda");
        printerHub.get("FI").print("FI Print Lambda");
    }
    void demoFunctionalInterfaceException(int number) {
        try{
            System.out.println("Результат деления: " + divideByFiveException.apply(number));
        }catch (ArithmeticException ex){
            log.info("Вызвано исключение ArithmeticException: " + ex.getMessage());
        }catch (Exception ex){
            log.info("Необработанная ошибка при вычислении" + ex.getMessage());
        }
    }
    private void demoAllFunctionalInterface() {
        predicate.test(4);//bool
        consumer.accept(5);//Pring integer
        function.apply(6);//Integer toString Output
        supplier.get();//return random filled Client
        unaryOperator.apply(4);//16
        binaryOperator.apply(6,4);//24
    }
    void demoLambda(){
        ClientsStorage clientsStorage = StorageFactory.getInstance().createObject(2);
        clientsStorage.clients.stream().forEach(System.out::println);
        System.out.println("\n\n");
        clientsStorage = StorageFactory.getInstance().createObject(2);
        clientsStorage.clients.stream().forEach(System.out::println);

        Client testClient = ApplicationContextProvider.getApplicationContext().getBean("Client",Client.class);
        System.out.println(testClient.getData());
    }
    void demoCapture(){
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        //Захватываем х из окружения
        list.forEach(x->{
            if (x ==3){
                System.out.println("Found 3");
            }
        });

    }
    void demoNonCapture(){
        int[] numbers = {1,2,3,4,5};
        //Просто передаем numbers
        Arrays.stream(numbers).forEach((number)->{
            System.out.println(number);
        });
    }
    void readFileAsStream() {
        //1 Пример с помощью регулярок и splitAsStream
        Pattern p = Pattern.compile("[,\\.\\-;]");
        try{
            final CharSequence splitIt =
                    new FileAsCharSequence(
                            new File("path_to_source\\SplitFileAsStream.java"));
            p.splitAsStream(splitIt).forEach(System.out::println);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        //2 Создание при помощи разбиения построчно Files.lines
        String currentDirectoryPath = "./src/main/java/ru/mart/Review/FileStorage/";
        String filePath = currentDirectoryPath + "text.txt";
        String filePath3 = currentDirectoryPath + "example.txt";

        File newFile= new File(currentDirectoryPath + "text.txt");
        try(Stream<String> lineStream = Files.lines(newFile.toPath(), StandardCharsets.UTF_8)){
            lineStream.forEach(System.out::println);
        }catch (IOException ex){
            log.error(ex.getMessage());
        }

        //Потоковое копирование с буфером
        try(FileInputStream fileInputStream = new FileInputStream("./src/main/java/ru/mart/Review/FileStorage/text.txt");
            FileOutputStream fileOutputStream = new FileOutputStream("./src/main/java/ru/mart/Review/FileStorage/text3.txt"))
        {
            while (fileInputStream.available() > 0 ){
                int oneByte = fileInputStream.read();
                fileOutputStream.write(oneByte);
                log.warn("пошла запись");
            }
        }catch (FileNotFoundException ex){
            System.out.println(ex.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    void demoCollectors(){
        //List
        List<String> phonesList = new ArrayList<String>();
        Collections.addAll(phonesList, "iPhone 8", "HTC U12", "Huawei Nexus 6P",
                "Samsung Galaxy S9", "LG G6", "Xiaomi MI6", "ASUS Zenfone 2",
                "Sony Xperia Z5", "Meizu Pro 6", "Lenovo S850");

        List<String> filteredPhones = phonesList.stream()
                .filter(s->s.length()<10)
                .collect(Collectors.toList());
        //Map
        Stream<Phone> phoneStream = Stream.of(new Phone("iPhone 8", 54000),
                new Phone("Nokia 9", 45000),
                new Phone("Samsung Galaxy S9", 40000),
                new Phone("LG G6", 32000));

        Map<String, Integer> phonesMap = phoneStream
                .collect(Collectors.toMap(Phone::getName, Phone::getPrice));

        //Collection
        Stream<String> phonesStream = Stream.of("iPhone 8", "HTC U12", "Huawei Nexus 6P",
                "Samsung Galaxy S9", "LG G6", "Xiaomi MI6", "ASUS Zenfone 2",
                "Sony Xperia Z5", "Meizu Pro 6", "Lenovo S850");

        HashSet<String> filteredPhonesSet = phonesStream.filter(s->s.length()<12).
                collect(Collectors.toCollection(HashSet::new));

        ArrayList<String> result = phonesStream.collect(Collectors.toCollection(ArrayList::new));
        //2 Вариант
        Stream<String> phones2 = Stream.of("iPhone 8", "HTC U12", "Huawei Nexus 6P",
                "Samsung Galaxy S9", "LG G6", "Xiaomi MI6", "ASUS Zenfone 2",
                "Sony Xperia Z5", "Meizu Pro 6", "Lenovo S850");

        ArrayList<String> filteredPhones2 = phones2.filter(s->s.length()<12)
                .collect(
                        ()->new ArrayList<String>(), // создаем ArrayList
                        (list, item)->list.add(item), // добавляем в список элемент
                        (list1, list2)-> list1.addAll(list2)); // добавляем в список другой список
        filteredPhones2.forEach(s->System.out.println(s));
    }
    private void demoStreamCollect() {
        ClientsStorage clientsStorage = StorageFactory.getInstance().createObject(10);
        //filter+collect
        List<Client> activeClients = clientsStorage.clients.stream().filter(client -> !client.isActive()).collect(Collectors.toList());
        clientsStorage.clients.forEach(System.out::println);
        //reduce
        //4.16.6 Рассказать про класс Optional? Как создавать? В каких случаях использовать? Какие приемущества даст его использование?
        Optional<Client> clientMaxGoldOptional = clientsStorage.clients.stream().reduce(((client1, client2) -> client1.getGold() > client2.getGold() ? client1 : client2));
        if(clientMaxGoldOptional.isPresent()){
            System.out.println("Max:" + clientMaxGoldOptional);
        }
        //sorted
        System.out.println("Сортировка:");
        Collection<Client> sortedClientsCollection = clientsStorage.clients.stream().sorted(Comparator.comparing(Client::getGold)).collect(Collectors.toCollection(TreeSet::new));
        sortedClientsCollection.forEach(System.out::println);
        //Поиск
        Client gold150 = sortedClientsCollection.stream().filter(client -> client.getGold() > 150).findFirst().orElse(null);
        assert gold150 != null;
        System.out.println("Первый клиент с золотом больше 150: " + gold150.getName() + " " + gold150.getGold());
        Client nameLengthGrater5 = sortedClientsCollection.stream().filter(client -> client.getName().length() > 5).findAny().orElse(null);
        assert nameLengthGrater5 != null;
        System.out.println("Любой клиент у которого имя длинее 5 символов:" + nameLengthGrater5.getName());
        //List<String> names = clientsStorage.clients.stream().map(Client::getName).toList();
    }
    void demoReduce(){
        //        ClientsStorage storage = StorageFactory.getInstance().createObject();
//
//        Integer result = storage.clients.stream().reduce(0,(goldSum,client)->goldSum + client.getGold(),Integer::sum);
//        List<Integer> allOrders = storage.clients.stream().map(Client::getGold).collect(Collectors.toList());
//        System.out.println(allOrders);
//        System.out.println(allOrders.stream().reduce(0,Integer::sum));
//        storage.clients.forEach(System.out::println);
//        System.out.println(result);


//        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
//        int initial = 1;
//        int sum = numbers.stream().reduce(initial,(sumVal,val)->sumVal * val, Integer::sum);
//        System.out.println(sum);
//        System.out.println(numbers.stream().reduce(0,Integer::sum));

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // 1*10 + 2*10 + 3*10
        Integer sum = numbers.stream().parallel()
                .reduce(10, (identity, val) -> identity * val, Integer::sum);
        String all = numbers.stream().reduce("",(x,y)->x.toString() + y.toString(),(x,y)->x.toString()+"," + y.toString());
        System.out.println(all);
        System.out.println(sum); //output 60

        int c = 4;
        Integer b = c;

    }

    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        names.add("John");
        names.add("Boris");
        names.add("Vasya");

        names.stream().map(s -> s.substring(1)).forEach(System.out::println);

    }
}

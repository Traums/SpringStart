package ru.mart.Practice.Serialization;

public class App {
    public static void main(String[] args) {
        SerializeJob serializeJob = new SerializeJob("DB");
        serializeJob.serializePudge(new Pudge(2,"Pudge",2,"+"));
        serializeJob.output(serializeJob.deserialize());
    }
}

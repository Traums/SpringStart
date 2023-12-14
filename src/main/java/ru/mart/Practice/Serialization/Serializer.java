package ru.mart.Practice.Serialization;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Serializer {
    String filename;
    Serializer(String filename){
        this.filename = filename;
    }
    void serialize( Pudge pud) {
//        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./serialDB/" + filename + ".data")))
//        {
//            oos.writeObject(pud);
//            oos.flush();
//        }
//        catch(Exception ex){
//            System.out.println(ex.getMessage());
//        }
        try{
            FileWriter writer = new FileWriter("./serialDB/" + this.filename + ".txt",true);
            String data = String.join(" ", String.valueOf(pud.getId()),pud.getName(),String.valueOf(pud.getLevel()),pud.getUltimate());
            data += "\n";
            writer.write(data);
            writer.close();
        }catch (IOException e){
            System.out.println("Ошибка сериализации: " + e.getMessage());;
        }
    }
    List<Pudge> deserialize(){
        List<Pudge> pudgeList = new ArrayList<>();
//        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./serialDB/" + filename + ".data")))
//        {
//            Pudge pudTemp = (Pudge) ois.readObject();
//            pudgeCol.add(pudTemp);
//        }
//        catch(Exception ex){
//
//            System.out.println(ex.getMessage());
//        }
        try{
            BufferedReader reader = new BufferedReader(new FileReader("./serialDB/" + this.filename + ".txt"));
            String lineRecord = reader.readLine();
            while (lineRecord != null){
                Pudge pud = mapToPudge(lineRecord);
                pudgeList.add(pud);
                lineRecord = reader.readLine();
            }
        }
        catch ( IOException e) {
            System.out.println("Ошибка десерелизации: " + e.getMessage());
        }
        return pudgeList;
    }

    private  Pudge mapToPudge(String lineRecord) {
        Pudge pud = new Pudge();
        String[] parts = lineRecord.split(" ");
        pud.setId(Long.parseLong(parts[0]));
        pud.setName(parts[1]);
        pud.setLevel(Integer.parseInt(parts[2]));
        pud.setUltimate(parts[3]);
        return pud;
    }
}

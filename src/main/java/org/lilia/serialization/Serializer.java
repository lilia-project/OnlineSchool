package org.lilia.serialization;

import java.io.*;

public class Serializer {

    public static <T> void serialize(T type, FilePath filePath) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath.getPath());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object deserialize(String filePath) {
        Object newObject;
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            newObject = objectInputStream.readObject();
        } catch (IOException|ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return newObject;
    }

}

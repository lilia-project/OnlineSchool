package org.lilia.models;

public class BaseClass {
    private static int counter = 0;

    private final int id;
    private String name;

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        BaseClass.counter = counter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BaseClass(String name) {
        this.name = name;
        counter++;
        id = counter;
    }
}

package org.lilia.models;

public class AdditionalMaterial {
    private static int counter;

    private final int id;

    public int getId() {
        return id;
    }
    public AdditionalMaterial(){
        counter++;
        id = counter;
    }
}

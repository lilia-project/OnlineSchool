package org.lilia.models;

public class AdditionalMaterial {
    private final int id;
    private static int counter;
    public AdditionalMaterial(){
        counter++;
        id = counter;
    }
}

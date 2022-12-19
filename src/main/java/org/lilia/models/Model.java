package org.lilia.models;

public abstract class Model {
    private String name;
    private final int id;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Model(String name,int id) {
        this.name = name;
        this.id = id;
    }
}

package org.lilia.models;

public class AdditionalMaterial extends Model {
    private static int counter = 0;

    private int idLecture;

    public void setIdLecture(int idLecture) {
        this.idLecture = idLecture;
    }

    public int getIdLecture() {
        return idLecture;
    }

    public AdditionalMaterial(String name, int idLecture) {
        super(name, ++counter);
        this.idLecture = idLecture;
    }
}

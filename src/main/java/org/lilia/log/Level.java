package org.lilia.log;

public enum Level {
    OFF("OFF"),
    ERROR("ERROR"),
    WARNING("WARNING"),
    INFO("INFO"),
    DEBUG("DEBUG");

    private final String levelValue;

    Level(String levelValue){
        this.levelValue = levelValue;
    }

    public String getLevelValue() {
        return levelValue;
    }




}

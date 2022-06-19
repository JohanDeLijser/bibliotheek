package com.jdelijser.bibliotheek.storage;

public final class ActiveSource {

    private static ActiveSource INSTANCE;
    private String source;

    private ActiveSource() {
    }

    public static ActiveSource getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ActiveSource();
        }

        return INSTANCE;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return this.source;
    }
}

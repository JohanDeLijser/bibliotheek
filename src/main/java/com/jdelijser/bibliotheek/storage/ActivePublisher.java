package com.jdelijser.bibliotheek.storage;

import com.jdelijser.bibliotheek.model.Publisher;

public final class ActivePublisher {

    private static ActivePublisher INSTANCE;
    private Publisher activePublisher;

    private ActivePublisher() {
    }

    public static ActivePublisher getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ActivePublisher();
        }

        return INSTANCE;
    }

    public void setPublisher(Publisher publisher) {
        this.activePublisher = publisher;
    }

    public Publisher getPublisher() {
        return this.activePublisher;
    }
}

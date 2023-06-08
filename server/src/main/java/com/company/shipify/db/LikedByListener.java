package com.company.shipify.db;

import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;

public class LikedByListener implements PostInsertEventListener, PostUpdateEventListener {

    @Override
    public void onPostInsert(PostInsertEvent event) {
        System.out.println("ONINSERTTEST");
    }

    @Override
    public void onPostUpdate(PostUpdateEvent event) {
        // Wywołaj kod dodający wartości do tabeli user_matches
        // na podstawie zmian w tabeli liked_songs
    }

    @Override
    public boolean requiresPostCommitHandling(EntityPersister persister) {
        return false;
    }
}

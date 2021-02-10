package com.example.todoapp.backstage.main_activity;

import android.os.Bundle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

//Event Bus pattern
//link: https://www.techyourchance.com/event-bus/
//link: https://github.com/chermehdi/event-bus/blob/master/src/main/java/io/github/chermehdi/guavabus/EventBus.java
public class PresenterEventBus {

    public enum EventType {
        SELECTED_TASK,
        UPDATED_TASK,
        CREATED_TASK
    }

    private interface Subscriber {

    }

    public interface SubscriberSelectedEvent extends Subscriber {
        void onSelectedEvent(Bundle bundle);
    }

    public interface SubscriberUpdatedEvent extends Subscriber {
        void onUpdatedEvent(Bundle bundle);
    }

    public interface SubscriberCreatedEvent extends Subscriber {
        void onCreatedEvent(Bundle bundle);
    }

    private final Map<EventType, Set<Subscriber>> eventSubscriberMap = new HashMap<>();

    public PresenterEventBus() {
        for (EventType eventType : EventType.values()) {
            eventSubscriberMap.put(eventType, new HashSet<>());
        }
    }

    public void publish(EventType eventType, Bundle bundle) {
        for (Subscriber subscriber : Objects.requireNonNull(eventSubscriberMap.get(eventType))) {
            switch (eventType){
                case UPDATED_TASK:
                    ((SubscriberUpdatedEvent)subscriber).onUpdatedEvent(bundle);
                    break;
                case SELECTED_TASK:
                    ((SubscriberSelectedEvent)subscriber).onSelectedEvent(bundle);
                    break;
                case CREATED_TASK:
                    ((SubscriberCreatedEvent)subscriber).onCreatedEvent(bundle);
                    break;
            }
        }
    }

    public void subscribe(Subscriber subscriber, EventType... eventTypes) {
        for (EventType eventType : eventTypes)
            Objects.requireNonNull(eventSubscriberMap.get(eventType))
                    .add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber) {
        for (Map.Entry<EventType, Set<Subscriber>> typeSetEntry: eventSubscriberMap.entrySet())
            typeSetEntry.getValue().remove(subscriber);
    }

}

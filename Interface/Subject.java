package com.example.marketwatchsystem.Interface;

import com.google.firebase.events.Subscriber;

public interface Subject {
    void subscribe(Subscriber s);

    void unsubscribe(Observer s);

    void notifySubscriber();

    void upload(String title);
}

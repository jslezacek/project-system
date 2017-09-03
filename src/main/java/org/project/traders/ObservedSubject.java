package org.project.traders;

import org.project.messages.FeedMessage;

public interface ObservedSubject {
    public void addObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers(FeedMessage feedMessage);
}

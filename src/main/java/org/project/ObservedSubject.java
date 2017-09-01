package org.project;

import org.project.Observer;

public interface ObservedSubject {
    public void addObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers(FeedMessage feedMessage);
}

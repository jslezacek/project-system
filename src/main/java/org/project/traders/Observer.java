package org.project.traders;

import org.project.messages.FeedMessage;

public interface Observer {
    public void update(FeedMessage feedMessage);
}

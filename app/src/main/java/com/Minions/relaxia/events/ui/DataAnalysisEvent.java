package com.Minions.relaxia.events.ui;

import com.Minions.relaxia.events.AbstractEvent;
import com.Minions.relaxia.events.EventObserver;

/**
 * Created by JJ on 9/4/2017.
 */

public class DataAnalysisEvent extends AbstractEvent{
    public static final String TYPE = DataAnalysisEvent.class.getName();
    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    protected void fire(EventObserver eventObserver) {
        eventObserver.onEvent(this);
    }
}

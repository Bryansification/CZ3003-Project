package com.snatik.matches.events.ui;

import com.snatik.matches.events.AbstractEvent;
import com.snatik.matches.events.EventObserver;

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

package com.Minions.relaxia.events.engine;

import com.Minions.relaxia.events.AbstractEvent;
import com.Minions.relaxia.events.EventObserver;

/**
 * When the 'back to menu' was pressed.
 */
public class FlipDownCardsEvent extends AbstractEvent {

	public static final String TYPE = FlipDownCardsEvent.class.getName();

	public FlipDownCardsEvent() {
	}
	
	@Override
	protected void fire(EventObserver eventObserver) {
		eventObserver.onEvent(this);
	}

	@Override
	public String getType() {
		return TYPE;
	}

}

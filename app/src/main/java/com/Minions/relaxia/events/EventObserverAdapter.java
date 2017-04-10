package com.Minions.relaxia.events;

import com.Minions.relaxia.events.engine.FlipDownCardsEvent;
import com.Minions.relaxia.events.engine.GameWonEvent;
import com.Minions.relaxia.events.engine.HidePairCardsEvent;
import com.Minions.relaxia.events.ui.BackGameEvent;
import com.Minions.relaxia.events.ui.DataAnalysisEvent;
import com.Minions.relaxia.events.ui.FlipCardEvent;
import com.Minions.relaxia.events.ui.NextGameEvent;
import com.Minions.relaxia.events.ui.ResetBackgroundEvent;
import com.Minions.relaxia.events.ui.ThemeSelectedEvent;
import com.Minions.relaxia.events.ui.DifficultySelectedEvent;
import com.Minions.relaxia.events.ui.StartEvent;


public class EventObserverAdapter implements EventObserver {

	public void onEvent(FlipCardEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(DifficultySelectedEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(HidePairCardsEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(FlipDownCardsEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(StartEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(ThemeSelectedEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(GameWonEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(BackGameEvent event) {
		throw new UnsupportedOperationException();		
	}

	@Override
	public void onEvent(NextGameEvent event) {
		throw new UnsupportedOperationException();		
	}

	@Override
	public void onEvent(ResetBackgroundEvent event) {
		throw new UnsupportedOperationException();		
	}

	@Override
	public void onEvent(DataAnalysisEvent event) { throw new UnsupportedOperationException(); }

}

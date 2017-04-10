package com.Minions.relaxia.events;

import com.Minions.relaxia.events.engine.FlipDownCardsEvent;
import com.Minions.relaxia.events.engine.GameWonEvent;
import com.Minions.relaxia.events.engine.HidePairCardsEvent;
import com.Minions.relaxia.events.ui.BackGameEvent;
import com.Minions.relaxia.events.ui.DataAnalysisEvent;
import com.Minions.relaxia.events.ui.DifficultySelectedEvent;
import com.Minions.relaxia.events.ui.FlipCardEvent;
import com.Minions.relaxia.events.ui.NextGameEvent;
import com.Minions.relaxia.events.ui.ResetBackgroundEvent;
import com.Minions.relaxia.events.ui.StartEvent;
import com.Minions.relaxia.events.ui.ThemeSelectedEvent;


public interface EventObserver {

	void onEvent(FlipCardEvent event);

	void onEvent(DifficultySelectedEvent event);

	void onEvent(HidePairCardsEvent event);

	void onEvent(FlipDownCardsEvent event);

	void onEvent(StartEvent event);

	void onEvent(ThemeSelectedEvent event);

	void onEvent(GameWonEvent event);

	void onEvent(BackGameEvent event);

	void onEvent(NextGameEvent event);

	void onEvent(ResetBackgroundEvent event);

	void onEvent(DataAnalysisEvent event);

}

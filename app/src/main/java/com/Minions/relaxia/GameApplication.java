package com.Minions.relaxia;

import android.app.Application;

import com.Minions.relaxia.utils.FontLoader;

public class GameApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		FontLoader.loadFonts(this);

	}
}

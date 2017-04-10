package com.Minions.relaxia.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Memory {

	private static final String SHARED_PREFERENCES_NAME = "com.Minions.relaxia";
	private static String highStartKey = "theme_%d_difficulty_%d";
	private static ScoreDatabaseHandler dbHandler;

	public static void save(int themeId, int difficulty, int stars, int time) {
		int highStars = getHighStars(themeId, difficulty);
		if (stars > highStars) {
			SharedPreferences sharedPreferences = Shared.context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
			Editor edit = sharedPreferences.edit();
			String key = String.format(highStartKey, themeId, difficulty);
			edit.putInt(key, stars).commit();
		}
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		String dateTimeString = df.format(date);
		String game_type = "";
		if(themeId ==1 ) {
			game_type = ScoreDatabaseHandler.MEMORY_GAME_NUMBERS;
		}
		else if(themeId == 2){
			game_type = ScoreDatabaseHandler.MEMORY_GAME_ALPHABETS;
		}

		dbHandler = ScoreDatabaseHandler.getInstance(Shared.context);
		dbHandler.insertIntoTable(game_type, difficulty, time, dateTimeString, stars);

	}

	public static int getHighStars(int theme, int difficulty) {
		SharedPreferences sharedPreferences = Shared.context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
		String key = String.format(highStartKey, theme, difficulty);
		return sharedPreferences.getInt(key, 0);
	}
}

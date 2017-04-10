package com.Minions.relaxia.themes;

import android.graphics.Bitmap;

import com.Minions.relaxia.common.Shared;
import com.Minions.relaxia.utils.Utils;

import java.util.ArrayList;

public class Themes {

	public static String URI_DRAWABLE = "drawable://";

	public static Theme createAlphabetTheme() {
		Theme theme = new Theme();
		theme.id = 1;
		theme.name = "Alphabet";
		theme.name = "Number";
		theme.backgroundImageUrl = URI_DRAWABLE + "back_blackboard";
		theme.tileImageUrls = new ArrayList<String>();
		// 40 drawables
		for (int i = 1; i <= 10; i++) {
			theme.tileImageUrls.add(URI_DRAWABLE + String.format("number_%d", i));
		}
		return theme;
	}

	public static Theme createNumberTheme() {
		Theme theme = new Theme();
		theme.id = 2;
		theme.name = "Alphabet";
		theme.backgroundImageUrl = URI_DRAWABLE + "back_blackboard";
		theme.tileImageUrls = new ArrayList<String>();
		// 40 drawables
		for (int i = 1; i <= 26; i++) {
			theme.tileImageUrls.add(URI_DRAWABLE + String.format("alphabets_%d", i));
		}
		return theme;
	}


	public static Bitmap getBackgroundImage(Theme theme) {
		String drawableResourceName = theme.backgroundImageUrl.substring(Themes.URI_DRAWABLE.length());
		int drawableResourceId = Shared.context.getResources().getIdentifier(drawableResourceName, "drawable", Shared.context.getPackageName());
		Bitmap bitmap = Utils.scaleDown(drawableResourceId, Utils.screenWidth(), Utils.screenHeight());
		return bitmap;
	}

}
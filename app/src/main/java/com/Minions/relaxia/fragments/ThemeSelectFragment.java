package com.Minions.relaxia.fragments;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import com.Minions.relaxia.R;
import com.Minions.relaxia.common.Shared;
import com.Minions.relaxia.events.ui.ThemeSelectedEvent;
import com.Minions.relaxia.themes.Theme;
import com.Minions.relaxia.themes.Themes;

public class ThemeSelectFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = LayoutInflater.from(Shared.context).inflate(R.layout.theme_select_fragment, container, false);
		View alphabet = view.findViewById(R.id.theme_alphabet_container);
		View number = view.findViewById(R.id.theme_number_container);

		final Theme themealphabet = Themes.createAlphabetTheme();

		final Theme themenumber = Themes.createNumberTheme();


		alphabet.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Shared.eventBus.notify(new ThemeSelectedEvent(themealphabet));
			}
		});

		number.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Shared.eventBus.notify(new ThemeSelectedEvent(themenumber));
			}
		});

		/**
		 * Imporove performance first!!!
		 */
		animateShow(alphabet);
		animateShow(number);

		return view;
	}

	private void animateShow(View view) {
		ObjectAnimator animatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", 0.5f, 1f);
		ObjectAnimator animatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", 0.5f, 1f);
		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.setDuration(300);
		animatorSet.playTogether(animatorScaleX, animatorScaleY);
		animatorSet.setInterpolator(new DecelerateInterpolator(2));
		view.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		animatorSet.start();
	}

}

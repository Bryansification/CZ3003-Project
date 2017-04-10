package com.Minions.relaxia.fragments;

import android.animation.AnimatorSet;
import android.animation.AnimatorSet.Builder;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;

import com.Minions.relaxia.R;
import com.Minions.relaxia.common.Memory;
import com.Minions.relaxia.common.ScoreDatabaseHandler;
import com.Minions.relaxia.common.Shared;
import com.Minions.relaxia.events.ui.DifficultySelectedEvent;
import com.Minions.relaxia.themes.Theme;
import com.Minions.relaxia.ui.DifficultyView;

public class DifficultySelectFragment extends Fragment {
	private static ScoreDatabaseHandler dbHandler = ScoreDatabaseHandler.getInstance(Shared.context);
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = LayoutInflater.from(Shared.context).inflate(R.layout.difficulty_select_fragment, container, false);
		Theme theme = Shared.engine.getSelectedTheme();
		int totalStars = dbHandler.getTotalStars(theme.id);


		DifficultyView difficulty1 = (DifficultyView) view.findViewById(R.id.select_difficulty_1);
		difficulty1.setDifficulty(1, Memory.getHighStars(theme.id, 1));
		setOnClick(difficulty1, 1);
		animate(difficulty1);
		if (totalStars>2) {
			DifficultyView difficulty2 = (DifficultyView) view.findViewById(R.id.select_difficulty_2);
			difficulty2.setDifficulty(2, Memory.getHighStars(theme.id, 2));
			setOnClick(difficulty2, 2);
			animate(difficulty2);
		}
		else{
			DifficultyView nodifficulty2 = (DifficultyView) view.findViewById(R.id.select_difficulty_2);
			nodifficulty2.setnoDifficulty(2, Memory.getHighStars(theme.id, 2));
			animate(nodifficulty2);
		}
		if (totalStars>5) {
			DifficultyView difficulty3 = (DifficultyView) view.findViewById(R.id.select_difficulty_3);
			difficulty3.setDifficulty(3, Memory.getHighStars(theme.id, 3));
			setOnClick(difficulty3, 3);
			animate(difficulty3);
		}
		else{
			DifficultyView nodifficulty3 = (DifficultyView) view.findViewById(R.id.select_difficulty_3);
			nodifficulty3.setnoDifficulty(3, Memory.getHighStars(theme.id, 3));
			animate(nodifficulty3);
		}
		if (totalStars>8) {
			DifficultyView difficulty4 = (DifficultyView) view.findViewById(R.id.select_difficulty_4);
			difficulty4.setDifficulty(4, Memory.getHighStars(theme.id, 4));
			setOnClick(difficulty4, 4);
			animate(difficulty4);
		}
		else{
			DifficultyView nodifficulty4 = (DifficultyView) view.findViewById(R.id.select_difficulty_4);
			nodifficulty4.setnoDifficulty(4, Memory.getHighStars(theme.id, 4));
			animate(nodifficulty4);
		}
		if(totalStars>11) {
			DifficultyView difficulty5 = (DifficultyView) view.findViewById(R.id.select_difficulty_5);
			difficulty5.setDifficulty(5, Memory.getHighStars(theme.id, 5));
			setOnClick(difficulty5, 5);
			animate(difficulty5);
		}
		else{
			DifficultyView nodifficulty5 = (DifficultyView) view.findViewById(R.id.select_difficulty_5);
			nodifficulty5.setnoDifficulty(5, Memory.getHighStars(theme.id, 5));
			animate(nodifficulty5);
		}
		if(totalStars>14) {
			DifficultyView difficulty6 = (DifficultyView) view.findViewById(R.id.select_difficulty_6);
			difficulty6.setDifficulty(6, Memory.getHighStars(theme.id, 6));
			setOnClick(difficulty6, 6);
			animate(difficulty6);
		}
		else{
			DifficultyView nodifficulty6 = (DifficultyView) view.findViewById(R.id.select_difficulty_6);
			nodifficulty6.setnoDifficulty(6, Memory.getHighStars(theme.id, 6));
			animate(nodifficulty6);
		}

		return view;
	}

	private void animate(View... view) {
		AnimatorSet animatorSet = new AnimatorSet();
		Builder builder = animatorSet.play(new AnimatorSet());
		for (int i = 0; i < view.length; i++) {
			ObjectAnimator scaleX = ObjectAnimator.ofFloat(view[i], "scaleX", 0.8f, 1f);
			ObjectAnimator scaleY = ObjectAnimator.ofFloat(view[i], "scaleY", 0.8f, 1f);
			builder.with(scaleX).with(scaleY);
		}
		animatorSet.setDuration(500);
		animatorSet.setInterpolator(new BounceInterpolator());
		animatorSet.start();
	}

	private void setOnClick(View view, final int difficulty) {
		view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Shared.eventBus.notify(new DifficultySelectedEvent(difficulty));
			}
		});
	}
}

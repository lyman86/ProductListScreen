package com.ly.commodityclassficationlist.animation;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;

import com.example.productlistscreen.R;

public class MenuAnim {
	public static final int RIGHT_LEFT = 0;
	public static final int UP_DOWN = 1;

	
	public static void openMenu(int type, final Activity context,final View layout, final int id) {
		Animation anim = null;
		switch (type) {
		case RIGHT_LEFT:
			anim = AnimationUtils.loadAnimation(context,R.anim.right_to_left_menu_show);
			break;
		case UP_DOWN:
			anim = AnimationUtils.loadAnimation(context,R.anim.up_to_down_menu_show);
			break;
		}
		if (layout != null) {
			final View left = layout.findViewById(id);
			layout.setVisibility(View.VISIBLE);
			left.setVisibility(View.INVISIBLE);
			layout.startAnimation(anim);
			layout.setClickable(true);
			layout.setFocusable(true);
			setEnable(false,context);
			anim.setAnimationListener(new AnimationListener() {

				@Override
				public void onAnimationStart(Animation arg0) {
				}

				@Override
				public void onAnimationRepeat(Animation arg0) {
				}
				@Override
				public void onAnimationEnd(Animation arg0) {
					left.setVisibility(View.VISIBLE);
					setEnable(true,context);
				}
			});
		}
	}

	
	public static void closeMenu(int type,final Activity context,
			final View layout, int id) {
		Animation anim = null;
		switch (type) {
		case RIGHT_LEFT:
			 anim = AnimationUtils.loadAnimation(context,R.anim.left_to_right_menu_exit);
			break;
		case UP_DOWN:
			 anim = AnimationUtils.loadAnimation(context,R.anim.down_to_up_menu_exit);
			break;
		}
		if (layout != null) {
			layout.setVisibility(View.GONE);
			context.findViewById(id).setVisibility(View.INVISIBLE);
			layout.startAnimation(anim);
			setEnable(false,context);
			anim.setAnimationListener(new AnimationListener() {
				
				@Override
				public void onAnimationStart(Animation arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onAnimationRepeat(Animation arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onAnimationEnd(Animation arg0) {
					setEnable(true,context);
					
				}
			});
		}
	}

	public static void openUpDownMenu(Activity activity, final View layout,
			int id) {
		if (layout != null) {

		}
	}
	
	private static void setEnable(boolean enable,Activity context){
				context.findViewById(R.id.type_llayout).setClickable(enable);
				context.findViewById(R.id.brand_llayout).setClickable(enable);
				context.findViewById(R.id.screen_llayout).setClickable(enable);
				context.findViewById(R.id.sort_llayout).setClickable(enable);
	}
}

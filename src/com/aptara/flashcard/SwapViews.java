package com.aptara.flashcard;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
public final class SwapViews implements Runnable {
	private boolean mIsFirstView;
	ImageView image1;
	ImageView image2;
	private ViewActivity flip;
	public MediaPlayer mPlayer;
	private Handler handler = new Handler()	;
	Utility util;
	
	public SwapViews(boolean isFirstView, ImageView image1, ImageView image2, ViewActivity flip) {

		mIsFirstView = isFirstView;

		this.image1 = image1;

		this.image2 = image2;
		
		this.flip = flip;
	}
	public void run() {

		final float centerX = image1.getWidth() / 2.0f;

		final float centerY = image1.getHeight() / 2.0f;

		Flip3dAnimation rotation;

		if (mIsFirstView) {

			image1.setVisibility(View.GONE);

			image2.setVisibility(View.VISIBLE);

			image2.requestFocus();			

			rotation = new Flip3dAnimation(90, 0, centerX, centerY);
		
		} else {

			image2.setVisibility(View.GONE);

			image1.setVisibility(View.VISIBLE);

			image1.requestFocus();

			rotation = new Flip3dAnimation(-90, 0, centerX, centerY);
			
		}

		rotation.setDuration(1000);

		rotation.setFillAfter(true);

		rotation.setInterpolator(new DecelerateInterpolator());
		
		if(flip.flag){
		rotation.setAnimationListener(new DisplayNextView(mIsFirstView, image2, image1, flip));
		flip.flag = false;
		}

		if (mIsFirstView) {
			util = new Utility();
			//util.playMusic(ViewActivity.tempSoundObject[ImageCard.imgNumber], flip);
			image2.startAnimation(rotation);
			
			try {
				handler.postDelayed(new Runnable(){
					public void run() {
						util.playMusic(ViewActivity.tempSoundObject[ImageCard.imgNumber], flip);
					}

				}, util.SPLASH_DISPLAY_LENGHT);
			} catch (Exception e) {
				e.printStackTrace();
			}
			

		} else {

			image1.startAnimation(rotation);

		}		
		
	}
	}
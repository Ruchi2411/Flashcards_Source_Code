package com.aptara.flashcard;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
public final class SwapView2 implements Runnable {
	private boolean mIsFirstView;
	ImageView image1;
	ImageView image2;
	private ViewActivity flip;
	public SwapView2(boolean isFirstView, ImageView image1, ImageView image2, ViewActivity flip) {

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

			rotation = new Flip3dAnimation( 90,0, centerX, centerY);

		} else {

			image2.setVisibility(View.GONE);

			image1.setVisibility(View.VISIBLE);

			image1.requestFocus();

			rotation = new Flip3dAnimation( -90,0, centerX, centerY);

		}

		rotation.setDuration(500);

		rotation.setFillAfter(true);

		rotation.setInterpolator(new DecelerateInterpolator());

		if(flip.flag2){
			rotation.setAnimationListener(new DisplayNextView(mIsFirstView, image2, image1, flip));
			flip.flag2 = false;
		}

		if (mIsFirstView) {

			image2.startAnimation(rotation);

			flip.animationSyncFlag = true;
		} else {

			image1.startAnimation(rotation);

		}		

	}
}
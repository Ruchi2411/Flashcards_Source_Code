package com.aptara.flashcard;
import android.view.animation.Animation;
import android.widget.ImageView;

public final class DisplayNextView implements Animation.AnimationListener {
	private boolean mCurrentView;
	ImageView image1;
	ImageView image2;
	ViewActivity flip;
	public DisplayNextView(boolean currentView, ImageView image1, ImageView image2, ViewActivity flip) {
		mCurrentView = currentView;
		this.image1 = image1;
		this.image2 = image2;
		this.flip = flip;
	}
	public void onAnimationStart(Animation animation) {
	}
	public void onAnimationEnd(Animation animation) {
		if(flip.flag){
		image1.post(new SwapViews(mCurrentView, image1, image2, flip));
		}else if(flip.flag1){
			image2.post(new SwapViews1(mCurrentView, image2, image1, flip));
		}else if(flip.flag2){
			image1.post(new SwapView2(mCurrentView, image2, image1, flip));
		}
	}
	public void onAnimationRepeat(Animation animation) {
	}
}

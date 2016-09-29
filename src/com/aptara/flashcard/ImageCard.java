package com.aptara.flashcard;

import java.io.IOException;
import java.util.Queue;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ImageCard extends LinearLayout implements OnClickListener { 

	static int imgNumber = 0;
	ImageView imgView;
	MediaPlayer mPlayer;
	public  Integer[] imgName;
	public  Integer[] imgObject;
	public FlipActivity animation;
	public static LongOperation _opr = null;
	public static LongOperation _opr1 = null;
	int cx = 0;
	int cy = 0;
	Utility util;
	ImageView quesView=null;
	static Context _context;
	static LinearLayout layout;
	ImageCard image = null;
	ViewActivity view;

	public ImageCard(Context context, int answer, Integer[] imgName, Integer[] imgObject) {
		super(context);

		_context = context;
		ImageCard.imgNumber = answer;
		try
		{
			LayoutInflater.from(context).inflate(R.layout.flashcard, this, true); 
			setOnClickListener(this);
			this.imgName = imgName;
			this.imgObject = imgObject;
			layout = (LinearLayout)findViewById(R.id.answerView);
			util = new Utility();
			imgView = (ImageView)findViewById(R.id.answerText);
			Drawable toRecycle= imgView.getDrawable();
			if (toRecycle != null) 
			{			
			    ((BitmapDrawable)imgView.getDrawable()).getBitmap().recycle();
			}
			imgView.setImageResource(this.imgObject[ImageCard.imgNumber]);
			//((BitmapDrawable)imgView.getDrawable()).getBitmap().recycle();
			quesView = (ImageView)findViewById(R.id.questionText);
			Drawable toRecycle1= quesView.getDrawable();
			if (toRecycle1 != null) 
			{
			    ((BitmapDrawable)quesView.getDrawable()).getBitmap().recycle();
			}
			quesView.setImageResource(this.imgName[ImageCard.imgNumber]);
			//((BitmapDrawable)quesView.getDrawable()).getBitmap().recycle();
			System.gc();
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		cx = this.getWidth() / 2; 
		cy = this.getHeight() / 2;

		view = new ViewActivity();
		showQuestion(); 
	}

	

	public void onClick(View v) 
	{ 	
		if(view.animationSyncFlag==true)
		{
			view.animationSyncFlag = false;
			ImageCard.imgNumber = ViewActivity.cardPos;
			_opr = new LongOperation();
			_opr.execute("1");
			
		}

	} 

	public void showQuestion() { 
		findViewById(R.id.questionText).setVisibility(VISIBLE); 
		findViewById(R.id.answerText).setVisibility(GONE);                       
	} 

	public void showAnswer() { 
		findViewById(R.id.questionText).setVisibility(GONE); 
		findViewById(R.id.answerText).setVisibility(VISIBLE);                    
	} 
	public void playanim()
	{
		int cx = this.getWidth() / 2; 
		int cy = this.getHeight() / 2; 

		Log.i("Question Visible:",""+quesView.getVisibility());
		Log.i("Anse Visible:",""+imgView.getVisibility());
		animation = new FlipActivity(quesView,imgView, cx, cy); 
		if (quesView.getVisibility() == View.GONE) 
		{ 
			animation.reverse(); 
		}
		if(view.animationSyncFlag == false){
			view.animationSyncFlag = true;
		}
	}
	public class LongOperation extends AsyncTask<Object, Object, Object> 
	{
		@Override
		protected Object doInBackground(Object... params) {
			publishProgress("flip");
			try{
				Thread.sleep(700);
			}catch(Exception e){

			}
			return "";  
		}  
		@Override   
		protected void onPreExecute()
		{   
		}    
		protected void onProgressUpdate(Object... values) {
			try{
				String token = (String)values[0];
				if(token.equals("flip"))
				{
					playanim();
					startAnimation(animation);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			super.onProgressUpdate(values);
		}
		protected void onPostExecute(Object result) {
			try {

				// Things to be done before execution of long running operation. For example showing ProgessDialog
				if (findViewById(R.id.questionText).getVisibility() == View.GONE) 
				{ 
					if(MyApp.soundFlag){
						if(util.mPlayer!= null){
							util.mPlayer = null;
						}
						util.playMusic(ViewActivity.tempSoundObject[ImageCard.imgNumber], ImageCard.this.getContext());
					}
				}
				else
				{
					if(MyApp.soundFlag)
					{
						if(util.mPlayer!= null)
						{
							util.mPlayer = null;
						}
						util.playMusic(ViewActivity.tempSoundName[ImageCard.imgNumber], ImageCard.this.getContext());
					}
				}
				view.animationSyncFlag = true;
				
			} catch (Exception e) {

			}
			super.onPostExecute(result);

		}
	} 
}



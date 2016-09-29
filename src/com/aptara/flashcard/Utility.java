package com.aptara.flashcard;

import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;


public class Utility extends Application{

	public static MediaPlayer mPlayer =null;
	private Utility app;
	public static Dialog mDialog = null;
	public final int SPLASH_DISPLAY_LENGHT = 200;
	public void onCreate(Bundle savedInstanceState) {
		app = this;    
	}
	public static boolean isOnline(Context context) {
	    ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}

	public void playMusic(int soundNumber, Context context) {
		if(MyApp.soundFlag)			
		{
			if(mPlayer == null){
				mPlayer = MediaPlayer.create(context, soundNumber);  			
				mPlayer.start(); 
				mPlayer.setOnCompletionListener(new OnCompletionListener() {

					@Override
					public void onCompletion(MediaPlayer mp) {
						if(mPlayer != null){
							mPlayer.release();
							mPlayer = null;
						}
					}
				});
			}
		}
	}

	public void showDialog(Context context)
	{
		Utility.mDialog = new Dialog(context)
		{
			public boolean onKeyDown(int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				try {
					if(keyCode == KeyEvent.KEYCODE_BACK)
					{
						try 
						{
							if(Utility.mDialog!=null)
								Utility.mDialog.dismiss();
							Utility.mDialog=null;								
						}catch (Exception e) {
							e.printStackTrace();
						}			
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				return super.onKeyDown(keyCode, event);
			}
		};
		Utility.mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		Utility.mDialog.setContentView(R.layout.customdialog);
		//TextView text = (TextView)mDialog.findViewById(R.id.text1);
		Button okButton = (Button)Utility.mDialog.findViewById(R.id.okbutton);
		okButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(Utility.mDialog!=null)
					Utility.mDialog.dismiss();
				Utility.mDialog=null;
			}
		});
		Utility.mDialog.setCancelable(true);
		Utility.mDialog.show();
	}
}

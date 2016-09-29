package com.aptara.flashcard;

import com.aptara.flashcard.dragdrop.GameSetActivity;
import com.aptara.flashcard.dragdrop.SampleDragDropDemoActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.Toast;

public class MyApp extends Activity implements OnClickListener{
	public ImageButton flashcardButton;
	public ImageButton gameButton;
	//public ImageButton infoButton;
	public ImageButton helpButton;
	public ImageButton soundButton;
	public ImageButton iconButton;
	public static boolean soundFlag = true;
	private Utility _utility;
	public WebView webview;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);

		flashcardButton = (ImageButton)findViewById(R.id.flashcardbutton);
		gameButton = (ImageButton)findViewById(R.id.gamebutton);
		soundButton = (ImageButton)findViewById(R.id.soundbutton);

		if(soundFlag){
			soundButton.setImageResource(R.drawable.audio_on_main);
		}else{
			soundButton.setImageResource(R.drawable.audio_off_main);
		}
		//infoButton = (ImageButton)findViewById(R.id.infobutton);
		helpButton = (ImageButton)findViewById(R.id.helpbutton);

		iconButton = (ImageButton)findViewById(R.id.iconButton);
		iconButton.setOnClickListener(new OnClickListener() {                        
			@Override 
			public void onClick(View v) { 
				if(Utility.isOnline(MyApp.this))
				{
					Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse("http://www.carsondellosa.com"));
					startActivity(i);
				}
				else
				{
					Toast.makeText(MyApp.this, "Please check the connectivity and try again.", Toast.LENGTH_SHORT).show();
				}
			}        
		});                
		_utility = new Utility();

		flashcardButton.setOnClickListener(this);
		gameButton.setOnClickListener(this);
		soundButton.setOnClickListener(this);
		//infoButton.setOnClickListener(this);
		helpButton.setOnClickListener(this);
	}
	protected void onResume(){
		super.onResume();
		if(soundFlag){
			soundButton.setImageResource(R.drawable.audio_on_main);
		}else{
			soundButton.setImageResource(R.drawable.audio_off_main);
		}
		try 
		{
			if(Utility.mDialog!=null)
			{
				Utility.mDialog = null;
				_utility.showDialog(this);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}	
	}
	protected void onDestroy()
	{
		super.onDestroy();
		soundFlag = true;
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.flashcardbutton:
			//startActivity(new Intent(this, ViewActivity.class));
			Intent playintent = new Intent(this, PlayVideo.class);
			playintent.putExtra("Video", 1);
			startActivity(playintent);
			
			break;
		case R.id.gamebutton:
			//startActivity(new Intent (this, GameSetActivity.class));
			Intent gameintent = new Intent(this, PlayVideo.class);
			gameintent.putExtra("Video", 2);
			startActivity(gameintent);
 			
			break;
		case R.id.helpbutton:
			 _utility.showDialog(this);
			/*Intent intent =new Intent(this, HelpScreen.class);
			intent.putExtra("help", 1);
			startActivity(intent);*/
			break;
		case R.id.soundbutton:
			if(soundFlag == true)
			{
				soundButton.setImageResource(R.drawable.audio_off_main);
				soundFlag = false;
			}
			else{
				soundButton.setImageResource(R.drawable.audio_on_main);
				soundFlag = true;
			}
			break;
		}

	}
}
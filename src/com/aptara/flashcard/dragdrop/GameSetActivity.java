package com.aptara.flashcard.dragdrop;

import java.lang.ref.WeakReference;

import com.aptara.flashcard.HelpScreen;
import com.aptara.flashcard.MyApp;
import com.aptara.flashcard.R;
import com.aptara.flashcard.Utility;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class GameSetActivity extends Activity implements OnClickListener {

	private ImageButton set1;
	private ImageButton set2;
	private ImageButton set3;
	private ImageButton set4;
	private ImageButton set5;
	public static WeakReference<GameSetActivity> appinstance = null;
	GameSetActivity drag;
	private ImageButton soundbtn;
	private ImageButton helpbtn;
	private ImageButton homebtn;
	private ImageView iconview;
	private Utility _utility;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.gameset);
		
		appinstance = new WeakReference<GameSetActivity>(this);
		set1 = (ImageButton)findViewById(R.id.set1);
		set2 = (ImageButton)findViewById(R.id.set2);
		set3 = (ImageButton)findViewById(R.id.set3);
		set4 = (ImageButton)findViewById(R.id.set4);
		set5 = (ImageButton)findViewById(R.id.set5);
		
		set1.setOnClickListener(this);
		set2.setOnClickListener(this);
		set3.setOnClickListener(this);
		set4.setOnClickListener(this);
		set5.setOnClickListener(this);
		 _utility = new Utility();
		
//		infobtn = (ImageButton)findViewById(R.id.infobutton);
//		infobtn.setOnClickListener(this);
		helpbtn = (ImageButton)findViewById(R.id.helpbutton);
		helpbtn.setOnClickListener(this);
		homebtn = (ImageButton)findViewById(R.id.homebutton);
		homebtn.setOnClickListener(this);
		soundbtn = (ImageButton)findViewById(R.id.soundbutton);
	    soundbtn.setOnClickListener(this);
	    if(MyApp.soundFlag){
	    	soundbtn.setImageResource(R.drawable.audio_on_main);
		}else{
			soundbtn.setImageResource(R.drawable.audio_off_main);
		}
		iconview = (ImageView)findViewById(R.id.icon);
		iconview.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(Utility.isOnline(GameSetActivity.this))
				{
					Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse("http://www.carsondellosa.com"));
					startActivity(i);
				}
				else
				{
					Toast.makeText(GameSetActivity.this, "Please check the connectivity and try again.", Toast.LENGTH_SHORT).show();
				}
			}
		});	
	}
	protected void onResume(){
		super.onResume();
		if(MyApp.soundFlag){
			soundbtn.setImageResource(R.drawable.audio_on_main);
		}else{
			soundbtn.setImageResource(R.drawable.audio_off_main);
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.set1:
			 Intent st1 = new Intent(this, SampleDragDropDemoActivity.class);
			 st1.putExtra("set", 1);
			 startActivity(st1);
			 break;
		case R.id.set2:
			Intent st2 = new Intent(this, SampleDragDropDemoActivity.class);
			st2.putExtra("set", 2);
			startActivity(st2);
			break;
		case R.id.set3:
			Intent st3 = new Intent(this, SampleDragDropDemoActivity.class);
			st3.putExtra("set", 3);
			startActivity(st3);
			break;
		case R.id.set4:
			Intent st4 = new Intent(this, SampleDragDropDemoActivity.class);
			st4.putExtra("set", 4);
			startActivity(st4);
			break;
		case R.id.set5:
			Intent st5 = new Intent(this, SampleDragDropDemoActivity.class);
			st5.putExtra("set", 5);
			startActivity(st5);
			break;				
		case R.id.helpbutton:
			 
			 _utility.showDialog(this);
			/*Intent helpintent = new Intent(this, HelpScreen.class);
			helpintent.putExtra("help", 0);
			startActivity(helpintent);*/
			break;
		case R.id.homebutton:
			this.finish();
			break;
		case R.id.soundbutton:
			if(MyApp.soundFlag){
				soundbtn.setImageResource(R.drawable.audio_off_main);
				MyApp.soundFlag = false;
			}
			else{
				soundbtn.setImageResource(R.drawable.audio_on_main);
				MyApp.soundFlag = true;
			}
			break;
		}
	}
}

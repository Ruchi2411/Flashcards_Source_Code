package com.aptara.flashcard;

import com.aptara.flashcard.dragdrop.GameSetActivity;
import com.aptara.flashcard.dragdrop.SampleDragDropDemoActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;

public class HelpScreen extends Activity implements OnClickListener{

	  /** Called when the activity is first created. */
	int value=0;
//	private ImageButton infobtn;
	private ImageButton homeButton;
	private ImageButton iconButton;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.help);
        
        Bundle extras = getIntent().getExtras();
		if (extras == null) {
			return;
		}
		value = extras.getInt("help");
		
//		infobtn = (ImageButton)findViewById(R.id.infobutton);
//		infobtn.setOnClickListener(this);
		homeButton = (ImageButton)findViewById(R.id.homebutton);
		homeButton.setOnClickListener(this);
		iconButton = (ImageButton)findViewById(R.id.iconbutton);
		iconButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse("http://www.carsondellosa.com"));
				startActivity(i);
			}
		});		
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==homeButton)
		{
			if(value==1)
			{
				this.finish();
			}
			else
			{
				this.finish();
				if(SampleDragDropDemoActivity.appinstance!=null)
				{
					SampleDragDropDemoActivity.appinstance.get().finish();
				}
				if(GameSetActivity.appinstance!=null)
				{
					GameSetActivity.appinstance.get().finish();
				}
				if(ViewActivity.appinstance!=null)
				{
					ViewActivity.appinstance.get().finish();
				}
			}
		}
		/*if(v==infobtn)
		{
			this.finish();
			Intent infointent =new Intent(this, InfoScreen.class);
			infointent.putExtra("info", 0);
			startActivity(infointent);
		}*/
	}
}

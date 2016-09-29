/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aptara.flashcard;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.aptara.flashcard.dragdrop.GameSetActivity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.VideoView;

public class PlayVideo extends Activity {
	private static final String TAG = "VideoViewDemo";

	private VideoView mVideoView;
	private EditText mPath;
	private String current;
	private ImageButton _skipbtn;
	String _video = "";
	int value1;
	public boolean isplayed = false;
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.video);
		
		mVideoView = (VideoView) findViewById(R.id.surface_view);

		Bundle extras = getIntent().getExtras();
		if (extras == null) {
			return;
		}
		value1 = extras.getInt("Video");
		mPath = (EditText) findViewById(R.id.path);
		//mPath.setText(_video);
		if(value1 == 1)
		{
			mPath.setText("android.resource://" + getPackageName() + "/" + R.raw.cards_1);
		}
		else if(value1 == 2)
		{
			mPath.setText("android.resource://" + getPackageName() + "/" + R.raw.game_1);
		}
		
		_skipbtn = (ImageButton)findViewById(R.id.skip);
		_skipbtn.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				
				if(isplayed == false)
				{
					
				
				if(value1==1)
				{	finish();
					mVideoView.stopPlayback();
					Intent intent = new Intent(PlayVideo.this, ViewActivity.class);
					startActivity(intent);
				}
				else if(value1==2)
				{
					finish();
					mVideoView.stopPlayback();
					Intent intent = new Intent(PlayVideo.this, GameSetActivity.class);
					startActivity(intent);
				}
				}
				else
				{
					playVideo();
					isplayed = false;
					_skipbtn.setImageResource(R.drawable.skip);
				}
				
			
			}
		});
		
		runOnUiThread(new Runnable(){
			public void run() {
				playVideo();
				
			}
			
		});
	
		mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
            	finish();
            	if(value1==1)
				{
					Intent intent = new Intent(PlayVideo.this, ViewActivity.class);
					startActivity(intent);
				}
				else if(value1==2)
				{
					Intent intent = new Intent(PlayVideo.this, GameSetActivity.class);
					startActivity(intent);
				}
            }
        });

	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		try {
			if(keyCode == KeyEvent.KEYCODE_HOME || keyCode==KeyEvent.KEYCODE_POWER){
				try {
					if(mVideoView.isPlaying())
					{
						mVideoView.pause();
					}
					else
					{
						mVideoView.start();
					}
				} catch (Exception e) {					
				
				}	
				return true;
			}
		} catch (Exception e) {
			
		}

		return super.onKeyDown(keyCode, event);
	}
	
	public void onResume(){
		super.onResume();
		try 
		{
			//PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
		    //boolean isScreenOn = powerManager.isScreenOn();
		    
		    //if (isScreenOn) {
		    //if(powerManager.FULL_WAKE_LOCK==2)
		    //{
		        // The screen has been locked 
		        // do stuff...
			//if(ScreenReceiver.wasScreenOn)
			//{
				/*if(!mVideoView.isPlaying())
				{
					_skipbtn.setImageResource(R.drawable.skip);
				}*/
		    //}
			if(isplayed==true)
			{
				_skipbtn.setImageResource(R.drawable.play);
				
			}
			else
			{
				_skipbtn.setImageResource(R.drawable.skip);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
	public void playPause()
	{
		
	}
	public void onPause()
	{
		super.onPause();
		try {
    		
    		if(mVideoView.isPlaying())
			{
				mVideoView.pause();
				isplayed = true;
				//_skipbtn.setImageResource(R.drawable.play);
			}
			} catch (Exception e) {					
			e.printStackTrace();
		}		
	}
	/*@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
	    if (event.getKeyCode() == KeyEvent.KEYCODE_POWER) {
	    	try {
	    		
	    		if(mVideoView.isPlaying())
				{
					mVideoView.pause();
				}
				else
				{
					mVideoView.start();
				}
			} catch (Exception e) {					
				e.printStackTrace();
			}		
	    	return true;
	    }

	    return super.dispatchKeyEvent(event);
	}*/


	private void playVideo() {
		try {
			final String path = mPath.getText().toString();//"android.resource://" + getPackageName() + "/" + R.raw.game_1;//mPath.getText().toString();//"/sdcard/game_1.mp4";//
			Log.v(TAG, "path: " + path);
			if (path == null || path.length() == 0) {
				Toast.makeText(PlayVideo.this, "File URL/path is empty",
						Toast.LENGTH_LONG).show();

			} else {
				// If the path has not changed, just start the media player
				if (path.equals(current) && mVideoView != null) {
					mVideoView.start();
					mVideoView.requestFocus();
					return;
				}
				current = path;
				mVideoView.setVideoPath(getDataSource(path));
				mVideoView.start();
				mVideoView.requestFocus();
				
			}
		} catch (Exception e) {
			Log.e(TAG, "error: " + e.getMessage(), e);
			if (mVideoView != null) {
				mVideoView.stopPlayback();
			}
		}
	}

	private String getDataSource(String path) throws IOException {
		if (!URLUtil.isNetworkUrl(path)) {
			return path;
		} else {
			URL url = new URL(path);
			URLConnection cn = url.openConnection();
			cn.connect();
			InputStream stream = cn.getInputStream();
			if (stream == null)
				throw new RuntimeException("stream is null");
			File temp = File.createTempFile("mediaplayertmp", "dat");
			temp.deleteOnExit();
			String tempPath = temp.getAbsolutePath();
			FileOutputStream out = new FileOutputStream(temp);
			byte buf[] = new byte[128];
			do {
				int numread = stream.read(buf);
				if (numread <= 0)
					break;
				out.write(buf, 0, numread);
			} while (true);
			try {
				stream.close();
			} catch (IOException ex) {
				Log.e(TAG, "error: " + ex.getMessage(), ex);
			}
			return tempPath;
		}
	}
}

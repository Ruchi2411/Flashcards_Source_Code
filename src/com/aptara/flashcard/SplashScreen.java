package com.aptara.flashcard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.Window;

public class SplashScreen extends Activity {
    protected boolean _active = true;
    protected int _splashTime = 3000;
    
    private final int SPLASH_DISPLAY_LENGHT = 1000;
	private static Handler handler = new Handler();	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);
        
        // thread for displaying the SplashScreen
      /*  Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while(_active && (waited < _splashTime)) {
                        sleep(100);
                        if(_active) {
                            waited += 100;
                        }
                    }
                } catch(InterruptedException e) {
                    // do nothing
                } finally {
                    finish();
                    startActivity(new Intent("com.aptara.flashcard.splashscreen.MyApp"));
                    stop();
                }
            }
        };
        splashTread.start();*/
        try {
			handler.postDelayed(new Runnable(){
				public void run() {
					Intent categ_intent = new Intent(SplashScreen.this,MyApp.class);
			    	startActivity(categ_intent);
			    	SplashScreen.this.finish();
				}
				
			}, SPLASH_DISPLAY_LENGHT);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            _active = false;
        }
        return true;
    }
}
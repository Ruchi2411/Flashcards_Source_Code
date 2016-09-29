package com.aptara.flashcard.dragdrop;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Vector;

import com.aptara.flashcard.MyApp;
import com.aptara.flashcard.R;
import com.aptara.flashcard.Utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class SampleDragDropDemoActivity extends Activity implements OnClickListener
{
    private DragAndDropView mView1;
    private DragAndDropView mView2;
    private DragAndDropView mView3;
    private DragAndDropView mView4;
    private DragAndDropView mView5;
    private DragAndDropView mView6;
    private DragAndDropView mView7;
    private MyAdapter mAdapter1;
    private MyAdapter mAdapter2;
    private MyAdapter mAdapter3;
    private MyAdapter mAdapter4;
    private MyAdapter mAdapter5;
    private MyAdapter mAdapter6;
    private MyAdapter mAdapter7;
    private ArrayList<Item> mItems1 = new ArrayList<Item>();
    private ArrayList<Item> mItems2 = new ArrayList<Item>();
    private ArrayList<Item> mItems3 = new ArrayList<Item>();
    private ArrayList<Item> mItems4 = new ArrayList<Item>();
    private ArrayList<Item> mItems5 = new ArrayList<Item>();
    private ArrayList<Item> mItems6 = new ArrayList<Item>();
    private ArrayList<Item> mItems7 = new ArrayList<Item>();
    private ImageButton submit;
    private ImageButton helpbtn;
    private ImageButton soundbtn;
    private ImageButton homebtn;
    boolean iscorrect = false;
    //public static boolean iset1 = false; 
    private boolean _firstdrop = false;
    private boolean _seconddrop = false;
    private boolean _thirddrop = false;
    private boolean _fourthdrop = false;
    private boolean _fifthdrop = false;
    private boolean _sixthdrop = false;
    
    int ht;
    int wt;
    int height = 0;
    int width = 0;
    TextView title;
     
     int orientation = 0;
     int lheight = 0;
     int lwidth = 0;
     int nheight = 150;
     int value1 = 0;
     int i=0;
     int j =0;
     ImageButton next;
     ImageButton previous;
     ImageButton slidebtn;
     ImageView iconview;
     
     private MediaPlayer mPlayer;
     private static Dialog mDialog = null;
     private Utility _utility ;
     public static WeakReference<SampleDragDropDemoActivity> appinstance = null;
     @Override
     public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.game);
        previous = (ImageButton)findViewById(R.id.previousbtn);
        
        previous.setOnClickListener(this);
        next = (ImageButton)findViewById(R.id.nextbtn);
        next.setOnClickListener(this);
        slidebtn = (ImageButton)findViewById(R.id.slidebtn);
        slidebtn.setOnClickListener(this);
        
        mView1 = (DragAndDropView) findViewById(R.id.list);
        mView2 = (DragAndDropView) findViewById(R.id.list2);
        mView3 = (DragAndDropView) findViewById(R.id.list3);
        mView4 = (DragAndDropView) findViewById(R.id.list4);
        mView5 = (DragAndDropView) findViewById(R.id.list5);
        mView6 = (DragAndDropView) findViewById(R.id.list6);
        mView7 = (DragAndDropView) findViewById(R.id.list7);
        _utility = new Utility();
        Bundle extras = getIntent().getExtras();
		if (extras == null) {
			return;
		}
		 orientation = getResources().getConfiguration().orientation;
		value1 = extras.getInt("set");
		if(value1 == 1)
		{
			previous.setBackgroundResource(R.drawable.previous_disable);
			mItems1.add(new Item(R.drawable.vertical_small_a_main));
	        mItems1.add(new Item(R.drawable.vertical_small_b_main));
	        mItems1.add(new Item(R.drawable.vertical_small_c_main));
	        mItems1.add(new Item(R.drawable.vertical_small_d_main));
	        mItems1.add(new Item(R.drawable.vertical_small_e_main));
	        
		}
		
		if(value1 == 2)
		{
			mItems1.add(new Item(R.drawable.vertical_small_f_main));
	        mItems1.add(new Item(R.drawable.vertical_small_g_main));
	        mItems1.add(new Item(R.drawable.vertical_small_h_main));
	        mItems1.add(new Item(R.drawable.vertical_small_i_main));
	        mItems1.add(new Item(R.drawable.vertical_small_j_main));
	        
	        mView2.setBackgroundResource(R.drawable.game_visual_h);
	        mView3.setBackgroundResource(R.drawable.game_visual_j);
	        mView4.setBackgroundResource(R.drawable.game_visual_f);
	        mView5.setBackgroundResource(R.drawable.game_visual_g);
	        mView6.setBackgroundResource(R.drawable.game_visual_i);
		}
		if(value1 == 3)
		{
			mItems1.add(new Item(R.drawable.vertical_small_k_main));
	        mItems1.add(new Item(R.drawable.vertical_small_l_main));
	        mItems1.add(new Item(R.drawable.vertical_small_m_main));
	        mItems1.add(new Item(R.drawable.vertical_small_n_main));
	        mItems1.add(new Item(R.drawable.vertical_small_o_main));
			
	        mView2.setBackgroundResource(R.drawable.game_visual_o);
	        mView3.setBackgroundResource(R.drawable.game_visual_m);
	        mView4.setBackgroundResource(R.drawable.game_visual_n);
	        mView5.setBackgroundResource(R.drawable.game_visual_l);
	        mView6.setBackgroundResource(R.drawable.game_visual_k);
		}
		if(value1 == 4)
		{
			mItems1.add(new Item(R.drawable.vertical_small_p_main));
	        mItems1.add(new Item(R.drawable.vertical_small_q_main));
	        mItems1.add(new Item(R.drawable.vertical_small_r_main));
	        mItems1.add(new Item(R.drawable.vertical_small_s_main));
	        mItems1.add(new Item(R.drawable.vertical_small_t_main));
			
	        mView2.setBackgroundResource(R.drawable.game_visual_p);
	        mView3.setBackgroundResource(R.drawable.game_visual_t);
	        mView4.setBackgroundResource(R.drawable.game_visual_s);
	        mView5.setBackgroundResource(R.drawable.game_visual_r);
	        mView6.setBackgroundResource(R.drawable.game_visual_q);
		}
		if(value1 == 5)
		{
			if(orientation == 2)
			{
				mView1.setNumColumns(6);
			}
			next.setBackgroundResource(R.drawable.next_disable);
			mView7.setVisibility(View.VISIBLE);
			mItems1.add(new Item(R.drawable.u_small_resized));
	        mItems1.add(new Item(R.drawable.v_small_resized));
	        mItems1.add(new Item(R.drawable.w_small_resized));
	        mItems1.add(new Item(R.drawable.x_small_resized));
	        mItems1.add(new Item(R.drawable.y_small_resized));
	        mItems1.add(new Item(R.drawable.z_small_resized));
	        
	        mView2.setBackgroundResource(R.drawable.game_visual_w);
	        mView3.setBackgroundResource(R.drawable.game_visual_v);
	        mView4.setBackgroundResource(R.drawable.game_visual_u);
	        mView5.setBackgroundResource(R.drawable.game_visual_z);
	        mView6.setBackgroundResource(R.drawable.game_visual_x);
	        mView7.setBackgroundResource(R.drawable.game_visual_y);
		}
        iconview = (ImageView)findViewById(R.id.icon);
		iconview.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(Utility.isOnline(SampleDragDropDemoActivity.this))
				{
					Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse("http://www.carsondellosa.com"));
					startActivity(i);
				}
				else
				{
					Toast.makeText(SampleDragDropDemoActivity.this, "Please check the connectivity and try again.", Toast.LENGTH_SHORT).show();
				}
			}
		});	
        submit = (ImageButton)findViewById(R.id.submitbtn);
        submit.setOnClickListener(new OnClickListener() {
	  @Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(appinstance.get().mAdapter2.getCount()!=0 && appinstance.get().mAdapter3.getCount()!=0 && appinstance.get().mAdapter4.getCount()!=0 && appinstance.get().mAdapter5.getCount()!=0 && appinstance.get().mAdapter6.getCount()!=0)
			completegame();
			}
       });
       
       helpbtn = (ImageButton)findViewById(R.id.helpbutton);
       helpbtn.setOnClickListener(this);
     //  infobtn = (ImageButton)findViewById(R.id.infobutton);
       homebtn = (ImageButton)findViewById(R.id.homebutton);
       homebtn.setOnClickListener(this);
       soundbtn = (ImageButton)findViewById(R.id.soundbutton);
       soundbtn.setOnClickListener(this);
       if(MyApp.soundFlag){
			soundbtn.setImageResource(R.drawable.audio_on_main);
		}else{
			soundbtn.setImageResource(R.drawable.audio_off_main);
		}
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        ht = displaymetrics.heightPixels;
        wt = displaymetrics.widthPixels;
        height = ht/5;
        width = wt/2;

        lheight = ht/2;
        lwidth = (wt-120)/5;

		final Object data = getLastNonConfigurationInstance();	        	
    	
		if(data != null)
		{
			//Vector vect = (Vector)data;
			appinstance.get().mAdapter1.notifyDataSetChanged();
			appinstance.get().mAdapter2.notifyDataSetChanged();
			appinstance.get().mAdapter3.notifyDataSetChanged();
			appinstance.get().mAdapter4.notifyDataSetChanged();
			appinstance.get().mAdapter5.notifyDataSetChanged();
			appinstance.get().mAdapter6.notifyDataSetChanged();
			appinstance.get().mAdapter7.notifyDataSetChanged();
			mView1.setAdapter(appinstance.get().mAdapter1);
			mView2.setAdapter(appinstance.get().mAdapter2);
			mView3.setAdapter(appinstance.get().mAdapter3);
			mView4.setAdapter(appinstance.get().mAdapter4);
			mView5.setAdapter(appinstance.get().mAdapter5);
			mView6.setAdapter(appinstance.get().mAdapter6);
			mView7.setAdapter(appinstance.get().mAdapter7);
						
			//mView1.invalidateViews();
			//mView2.invalidateViews();
		}
		else
			{
			appinstance = new WeakReference<SampleDragDropDemoActivity>(this);
			appinstance.get().mAdapter1 = new MyAdapter(this, mItems1);
            mView1.setAdapter(appinstance.get().mAdapter1);
            
            appinstance.get().mAdapter2 = new MyAdapter(this, mItems2);
            mView2.setAdapter(appinstance.get().mAdapter2);
            
            appinstance.get().mAdapter3 = new MyAdapter(this, mItems3);
            mView3.setAdapter(appinstance.get().mAdapter3);
            
            appinstance.get().mAdapter4 = new MyAdapter(this, mItems4);
            mView4.setAdapter(appinstance.get().mAdapter4);
            
            appinstance.get().mAdapter5 = new MyAdapter(this, mItems5);
            mView5.setAdapter(appinstance.get().mAdapter5);
            
            appinstance.get().mAdapter6 = new MyAdapter(this, mItems6);
            mView6.setAdapter(appinstance.get().mAdapter6);
            
            appinstance.get().mAdapter7 = new MyAdapter(this, mItems7);
            mView7.setAdapter(appinstance.get().mAdapter7);
            appinstance.get().iscorrect = false;
            appinstance.get().i=0;
            
            mDialog = null;
            appinstance.get()._firstdrop = false;
            appinstance.get()._seconddrop = false;
            appinstance.get()._thirddrop = false;
            appinstance.get()._firstdrop = false;
            appinstance.get()._fifthdrop = false;
            appinstance.get()._sixthdrop = false;
			}
		
		if(appinstance.get().iscorrect==false)
            mView1.setOnDragnDropListener(new DragAndDropListener() {
           	@Override
            public void dropped(int from, int x, int y) {
               // Rect rect = new Rect();
                //mView1.getHitRect(rect);
                //Log.i("X and Y >>>",+x+""+y);
                if(value1!=5)
                {
                if(appinstance.get().mAdapter2.getCount()==0)
                {
                	//rect = new Rect();
                	//appinstance.get().mView2.getHitRect(rect);
                
                	if(orientation == 1)
                	{
                		//if(x>width+10 && y>5 && y<=height)
                		if(x>width+10 && y>nheight && y<=2*nheight)
                		{
                			if(MyApp.soundFlag){
                			_utility.playMusic(R.raw.click, SampleDragDropDemoActivity.this);
                			}
                			Item item = appinstance.get().mAdapter1.getItem(from);
                			appinstance.get().mAdapter1.remove(item);
                			appinstance.get().mAdapter2.add(item);
                		}
                	}
                	if(orientation == 2)
                	{
                		//if(x>5 && x<lwidth-10 && y>=lheight+20)
                		if(x>17 && x<lwidth+17 && y>=lheight+20 && y<480)
                		{
                			if(MyApp.soundFlag){
                			_utility.playMusic(R.raw.click, SampleDragDropDemoActivity.this);
                			}
                			Item item = appinstance.get().mAdapter1.getItem(from);
                			appinstance.get().mAdapter1.remove(item);
                			appinstance.get().mAdapter2.add(item);
                		}
                   	}
                	}
                 	if(appinstance.get().mAdapter3.getCount()==0)
              		{
                 		if(orientation == 1)
                 		{
                 			//if(x>width+10 && y>height+10 && y<=2*height)
                 			if(x>width+10 && y>2*nheight+20 && y<=3*nheight+20)
                 			{
                 				if(MyApp.soundFlag){
                 				_utility.playMusic(R.raw.click, SampleDragDropDemoActivity.this);
                 				}
                 				Item item = appinstance.get().mAdapter1.getItem(from);
                 				appinstance.get().mAdapter1.remove(item);
                 				appinstance.get().mAdapter3.add(item);
                 			}
                  		}
                 		if(orientation == 2)
                    	{
                 			if(x>lwidth+40 && x<2*lwidth+40 && y>=lheight+20 && y<480)
                    		//if(x>lwidth && x<2*lwidth && y>=lheight+20)
                    		{
                 				if(MyApp.soundFlag){
                 				_utility.playMusic(R.raw.click, SampleDragDropDemoActivity.this);
                 				}
                    			Item item = appinstance.get().mAdapter1.getItem(from);
                    			appinstance.get().mAdapter1.remove(item);
                    			appinstance.get().mAdapter3.add(item);
                    		}
                       	}
              		}
                   	if(appinstance.get().mAdapter4.getCount()==0)
               		{
                    	if(orientation == 1)
                   		{
                   			//if(x>width+10 && y>2*height+10 && y<=3*height)
                    		if(x>width+10 && y>3*nheight+20 && y<=4*nheight+20)
                   			{
                    			if(MyApp.soundFlag){
                    			_utility.playMusic(R.raw.click, SampleDragDropDemoActivity.this);
                    			}
                   				Item item = appinstance.get().mAdapter1.getItem(from);
                   				appinstance.get().mAdapter1.remove(item);
                   				appinstance.get().mAdapter4.add(item);
                   			}
                   		}
                   		if(orientation == 2)
                    	{
                    		//if(x>2*lwidth && x<3*lwidth && y>=lheight+20)
                   			if(x>2*lwidth+60 && x<3*lwidth+60 && y>=lheight+20 && y<480)
                    		{
                   				if(MyApp.soundFlag){
                   				_utility.playMusic(R.raw.click, SampleDragDropDemoActivity.this);
                   				}
                    			Item item = appinstance.get().mAdapter1.getItem(from);
                    			appinstance.get().mAdapter1.remove(item);
                    			appinstance.get().mAdapter4.add(item);
                    		}
                       	}
                  	}
                   	if(appinstance.get().mAdapter5.getCount()==0)
                   	{
                      	if(orientation == 1)
                   		{
                   			//if(x>width+10 && y>3*height+10 && y<=4*height)
                      		if(x>width+10 && y>4*nheight+20 && y<=5*nheight+20)
                   			{
                      			if(MyApp.soundFlag){
                      			_utility.playMusic(R.raw.click, SampleDragDropDemoActivity.this);
                      			}
                   				Item item = appinstance.get().mAdapter1.getItem(from);
                   				appinstance.get().mAdapter1.remove(item);
                   				appinstance.get().mAdapter5.add(item);
                   			}
                   		}
                   		if(orientation == 2)
                    	{
                    		//if(x>3*lwidth && x<4*lwidth && y>=lheight+20)
                   			if(x>3*lwidth+80 && x<4*lwidth+80 && y>=lheight+20 && y<480)
                    		{
                   				if(MyApp.soundFlag){
                   				_utility.playMusic(R.raw.click, SampleDragDropDemoActivity.this);
                   				}
                    			Item item = appinstance.get().mAdapter1.getItem(from);
                    			appinstance.get().mAdapter1.remove(item);
                    			appinstance.get().mAdapter5.add(item);
                    		}
                       	}
                   	}
                    if(appinstance.get().mAdapter6.getCount()==0)
                    {
                    	if(orientation == 1)
                    	{
                    		//if(x>width+10 && y>4*height+10 && y<=5*height)
                    		if(x>width+10 && y>5*nheight+20 && y<=6*nheight+20)
                    		{
                    			if(MyApp.soundFlag){
                    			_utility.playMusic(R.raw.click, SampleDragDropDemoActivity.this);
                    			}
                    			Item item = appinstance.get().mAdapter1.getItem(from);
                    			appinstance.get().mAdapter1.remove(item);
                    			appinstance.get().mAdapter6.add(item);
                    		}
                    	}
                    	if(orientation == 2)
                    	{
                    		//if(x>4*lwidth && x<5*lwidth && y>=lheight+20)
                    		if(x>4*lwidth+100 && x<5*lwidth+100 && y>=lheight+20 && y<480)
                    		{
                    			if(MyApp.soundFlag){
                    			_utility.playMusic(R.raw.click, SampleDragDropDemoActivity.this);
                    			}
                    			Item item = appinstance.get().mAdapter1.getItem(from);
                    			appinstance.get().mAdapter1.remove(item);
                    			appinstance.get().mAdapter6.add(item);
                    		}
                       	}
                     }
               }
                else
                {
                	//////for 6 letters
                	nheight = 125;
                	lwidth = (wt-140)/6;
                	if(appinstance.get().mAdapter2.getCount()==0)
                    {
                    	//rect = new Rect();
                    	//mView2.getHitRect(rect);
                    
                    	if(orientation == 1)
                    	{
                    		//if(x>width+10 && y>5 && y<=height)
                    		if(x>width+10 && y>nheight && y<=2*nheight)
                    		{
                    			if(MyApp.soundFlag){
                    			_utility.playMusic(R.raw.click, SampleDragDropDemoActivity.this);
                    			}
                    			Item item = appinstance.get().mAdapter1.getItem(from);
                    			appinstance.get().mAdapter1.remove(item);
                    			appinstance.get().mAdapter2.add(item);
                    		}
                    	}
                    	if(orientation == 2)
                    	{
                    		//if(x>5 && x<lwidth-10 && y>=lheight+20)
                    		if(x>17 && x<lwidth+17 && y>=lheight+20 && y<480)
                    		{
                    			if(MyApp.soundFlag){
                    			_utility.playMusic(R.raw.click, SampleDragDropDemoActivity.this);
                    			}
                    			Item item = appinstance.get().mAdapter1.getItem(from);
                    			appinstance.get().mAdapter1.remove(item);
                    			appinstance.get().mAdapter2.add(item);
                    		}
                       	}
                    	}
                     	if(appinstance.get().mAdapter3.getCount()==0)
                  		{
                     		if(orientation == 1)
                     		{
                     			//if(x>width+10 && y>height+10 && y<=2*height)
                     			if(x>width+10 && y>2*nheight+20 && y<=3*nheight+20)
                     			{
                     				if(MyApp.soundFlag){
                     				_utility.playMusic(R.raw.click, SampleDragDropDemoActivity.this);
                     				}
                     				Item item = appinstance.get().mAdapter1.getItem(from);
                     				appinstance.get().mAdapter1.remove(item);
                     				appinstance.get().mAdapter3.add(item);
                     			}
                      		}
                     		if(orientation == 2)
                        	{
                        		//if(x>lwidth && x<2*lwidth && y>=lheight+20)
                     			if(x>lwidth+40 && x<2*lwidth+40 && y>=lheight+20 && y<480)
                     			{
                     				if(MyApp.soundFlag){
                     				_utility.playMusic(R.raw.click, SampleDragDropDemoActivity.this);
                     				}
                        			Item item = appinstance.get().mAdapter1.getItem(from);
                        			appinstance.get().mAdapter1.remove(item);
                        			appinstance.get().mAdapter3.add(item);
                        		}
                           	}
                  		}
                       	if(appinstance.get().mAdapter4.getCount()==0)
                   		{
                        	if(orientation == 1)
                       		{
                       			//if(x>width+10 && y>2*height+10 && y<=3*height)
                        		if(x>width+10 && y>3*nheight+20 && y<=4*nheight+20)
                       			{
                        			if(MyApp.soundFlag){
                        			_utility.playMusic(R.raw.click, SampleDragDropDemoActivity.this);
                        			}
                        			Item item = appinstance.get().mAdapter1.getItem(from);
                       				appinstance.get().mAdapter1.remove(item);
                       				appinstance.get().mAdapter4.add(item);
                       			}
                       		}
                       		if(orientation == 2)
                        	{
                        		//if(x>2*lwidth && x<3*lwidth && y>=lheight+20)
                       			if(x>2*lwidth+60 && x<3*lwidth+60 && y>=lheight+20 && y<480)
                       			{
                       				if(MyApp.soundFlag){
                       				_utility.playMusic(R.raw.click, SampleDragDropDemoActivity.this);
                       				}
                        			Item item = appinstance.get().mAdapter1.getItem(from);
                        			appinstance.get().mAdapter1.remove(item);
                        			appinstance.get().mAdapter4.add(item);
                        		}
                           	}
                      	}
                       	if(appinstance.get().mAdapter5.getCount()==0)
                       	{
                          	if(orientation == 1)
                       		{
                       			//if(x>width+10 && y>3*height+10 && y<=4*height)
                          		if(x>width+10 && y>4*nheight+20 && y<=5*nheight+20)
                       			{
                          			if(MyApp.soundFlag){
                          			_utility.playMusic(R.raw.click, SampleDragDropDemoActivity.this);
                          			}
                       				Item item = appinstance.get().mAdapter1.getItem(from);
                       				appinstance.get().mAdapter1.remove(item);
                       				appinstance.get().mAdapter5.add(item);
                       			}
                       		}
                       		if(orientation == 2)
                        	{
                        		//if(x>3*lwidth && x<4*lwidth && y>=lheight+20)
                       			if(x>3*lwidth+80 && x<4*lwidth+80 && y>=lheight+20 && y<480)
                        		{
                       				if(MyApp.soundFlag){
                       				_utility.playMusic(R.raw.click, SampleDragDropDemoActivity.this);
                       				}
                        			Item item = appinstance.get().mAdapter1.getItem(from);
                        			appinstance.get().mAdapter1.remove(item);
                        			appinstance.get().mAdapter5.add(item);
                        		}
                           	}
                       	}
                        if(appinstance.get().mAdapter6.getCount()==0)
                        {
                        	if(orientation == 1)
                        	{
                        		//if(x>width+10 && y>4*height+10 && y<=5*height)
                        		if(x>width+10 && y>5*nheight+20 && y<=6*nheight+20)
                        		{
                        			if(MyApp.soundFlag){
                        			_utility.playMusic(R.raw.click, SampleDragDropDemoActivity.this);
                        			}
                        			Item item = appinstance.get().mAdapter1.getItem(from);
                        			appinstance.get().mAdapter1.remove(item);
                        			appinstance.get().mAdapter6.add(item);
                        		}
                        	}
                        	if(orientation == 2)
                        	{
                        		//if(x>4*lwidth && x<5*lwidth && y>=lheight+20)
                        		if(x>4*lwidth+100 && x<5*lwidth+100 && y>=lheight+20 && y<480)
                        		{
                        			if(MyApp.soundFlag){
                        			_utility.playMusic(R.raw.click, SampleDragDropDemoActivity.this);
                        			}
                        			Item item = appinstance.get().mAdapter1.getItem(from);
                        			appinstance.get().mAdapter1.remove(item);
                        			appinstance.get().mAdapter6.add(item);
                        		}
                           	}
                         }
                        if(appinstance.get().mAdapter7.getCount()==0)
                        {
                        	if(orientation == 1)
                        	{
                        		//if(x>width+10 && y>4*height+10 && y<=5*height)
                        		if(x>width+10 && y>6*nheight+20 && y<=7*nheight+20)
                        		{
                        			if(MyApp.soundFlag){
                        			_utility.playMusic(R.raw.click, SampleDragDropDemoActivity.this);
                        			}
                        			Item item = appinstance.get().mAdapter1.getItem(from);
                        			appinstance.get().mAdapter1.remove(item);
                        			appinstance.get().mAdapter7.add(item);
                        		}
                        	}
                        	if(orientation == 2)
                        	{
                        		if(x>5*lwidth+120 && x<6*lwidth+120 && y>=lheight+20 && y<480)
                        		//if(x>5*lwidth && x<6*lwidth && y>=lheight+20)
                        		{
                        			if(MyApp.soundFlag){
                        			_utility.playMusic(R.raw.click, SampleDragDropDemoActivity.this);
                        			}
                        			Item item = appinstance.get().mAdapter1.getItem(from);
                        			appinstance.get().mAdapter1.remove(item);
                        			appinstance.get().mAdapter7.add(item);
                        		}
                           	}
                         }
                	/////end
                }
           	}
        });
        
		if(appinstance.get().iscorrect==false && appinstance.get()._firstdrop==false)
        mView2.setOnDragnDropListener(new DragAndDropListener() {
            @Override
            public void dropped(int from, int x, int y) {
                    Item item = appinstance.get().mAdapter2.getItem(from);
                    appinstance.get().mAdapter2.remove(item);
                    appinstance.get().mAdapter1.add(item);
            }
        });
		if(appinstance.get().iscorrect==false && appinstance.get()._seconddrop==false)
        mView3.setOnDragnDropListener(new DragAndDropListener() {
            @Override
            public void dropped(int from, int x, int y) {
                    Item item = appinstance.get().mAdapter3.getItem(from);
                    appinstance.get().mAdapter3.remove(item);
                    appinstance.get().mAdapter1.add(item);  
            }
        });
		if(appinstance.get().iscorrect==false && appinstance.get()._thirddrop==false)
        mView4.setOnDragnDropListener(new DragAndDropListener() {
            @Override
            public void dropped(int from, int x, int y) {
           
                    Item item = appinstance.get().mAdapter4.getItem(from);
                    appinstance.get().mAdapter4.remove(item);
                    appinstance.get().mAdapter1.add(item);
     
            }
        });
		if(appinstance.get().iscorrect==false && appinstance.get()._fourthdrop==false)
         mView5.setOnDragnDropListener(new DragAndDropListener() {
            @Override
            public void dropped(int from, int x, int y) {
            
                    Item item = appinstance.get().mAdapter5.getItem(from);
                    appinstance.get().mAdapter5.remove(item);
                    appinstance.get(). mAdapter1.add(item);
        
            }
        });
		if(appinstance.get().iscorrect==false && appinstance.get()._fifthdrop==false)
        mView6.setOnDragnDropListener(new DragAndDropListener() {
            @Override
            public void dropped(int from, int x, int y) {
     
                    Item item = appinstance.get().mAdapter6.getItem(from);
                    appinstance.get().mAdapter6.remove(item);
                    appinstance.get().mAdapter1.add(item);
                }  
        });
		if(appinstance.get().iscorrect==false && appinstance.get()._sixthdrop==false)
       mView7.setOnDragnDropListener(new DragAndDropListener() {
            @Override
            public void dropped(int from, int x, int y) {
     
                    Item item = appinstance.get().mAdapter7.getItem(from);
                    appinstance.get().mAdapter7.remove(item);
                    appinstance.get().mAdapter1.add(item);
                }  
        });
     }
				

     @Override
     public Object onRetainNonConfigurationInstance() {
     	// TODO Auto-generated method stub
    	 Vector vect = new Vector();
 		try{
 			vect.add(appinstance.get().mAdapter1);
 			vect.add(appinstance.get().mAdapter2);
 		}catch(Exception e){
 			e.printStackTrace();
 			return vect;
 		}
 		return vect;
 	}
     public void showDialog()
     {
    	 mDialog = new Dialog(SampleDragDropDemoActivity.this)
			{
				public boolean onKeyDown(int keyCode, KeyEvent event) {
					// TODO Auto-generated method stub
					try {
						if(keyCode == KeyEvent.KEYCODE_BACK)
						{
							try 
							{
								if(mDialog!=null)
									mDialog.dismiss();
									mDialog=null;								
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
			mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			mDialog.setContentView(R.layout.customdialog);
			//TextView text = (TextView)mDialog.findViewById(R.id.text1);
			Button okButton = (Button)mDialog.findViewById(R.id.okbutton);
			okButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(mDialog!=null)
						mDialog.dismiss();
					mDialog=null;
				}
			});
			mDialog.setCancelable(true);
			mDialog.show();
     }
     public void onResume()
     {
     	super.onResume();
     	/*if(appinstance.get().builder!=null)
     	{
     		appinstance.get().builder.show();
     	}*/
     	try 
		{
			if(mDialog!=null)
			{
				mDialog = null;
				showDialog();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}	
		
     }
   
     public void remove()
     {
    	 if(!appinstance.get()._firstdrop)
    	 {
    		 appinstance.get().mAdapter1.add(appinstance.get().mAdapter2.getItem(0));
    		 appinstance.get().mAdapter2.clear();
    	 }
    	 if(!appinstance.get()._seconddrop)
    	 {
    		 appinstance.get().mAdapter1.add(appinstance.get().mAdapter3.getItem(0));
    		 appinstance.get().mAdapter3.clear();
    	 }
    	 if(!appinstance.get()._thirddrop)
    	 {
    		 appinstance.get().mAdapter1.add(appinstance.get().mAdapter4.getItem(0));
    		 appinstance.get().mAdapter4.clear();
    	 }
    	 if(!appinstance.get()._fourthdrop)
    	 {
    		 appinstance.get().mAdapter1.add(appinstance.get().mAdapter5.getItem(0));
    		 appinstance.get().mAdapter5.clear();
    	 }
    	 if(!appinstance.get()._fifthdrop)
    	 {
    		 appinstance.get().mAdapter1.add(appinstance.get().mAdapter6.getItem(0));
    		 appinstance.get().mAdapter6.clear();
    	 }
    	 if(value1 == 5)
    	 {
    		 if(!appinstance.get()._sixthdrop)
    		 {
    			 appinstance.get().mAdapter1.add(appinstance.get().mAdapter7.getItem(0));
    			 appinstance.get().mAdapter7.clear();
    		 }
    	 }
     }
     public void removeAll()
     {
    	 appinstance.get().mAdapter1.clear();
    	 appinstance.get().mAdapter2.clear();
    	 appinstance.get().mAdapter3.clear();
    	 appinstance.get().mAdapter4.clear();
    	 appinstance.get().mAdapter5.clear();
    	 appinstance.get().mAdapter6.clear();
    	 if(value1 == 5)
    	 {
    		 appinstance.get().mAdapter7.clear();
    	 }
     }
     
    public void completegame()
    {
    	
    	if(appinstance.get().mAdapter2.getCount()!=0 && appinstance.get().mAdapter3.getCount()!=0 && appinstance.get().mAdapter4.getCount()!=0 && appinstance.get().mAdapter5.getCount()!=0 && appinstance.get().mAdapter6.getCount()!=0)
    	{		
    		if(value1 == 1)
    	   	{
    			appinstance.get().i++;
    			
    			if(appinstance.get().mAdapter2.getItem(0).icon == R.drawable.vertical_small_b_main && appinstance.get().mAdapter3.getItem(0).icon == R.drawable.vertical_small_e_main && appinstance.get().mAdapter4.getItem(0).icon == R.drawable.vertical_small_a_main && appinstance.get().mAdapter5.getItem(0).icon == R.drawable.vertical_small_d_main && appinstance.get().mAdapter6.getItem(0).icon == R.drawable.vertical_small_c_main)
    			{
    				if(appinstance.get().iscorrect ==false)
    				{
    					if(MyApp.soundFlag){
    					_utility.playMusic(R.raw.direction_05, SampleDragDropDemoActivity.this);
    					}
    					appinstance.get().iscorrect = true;
		                appinstance.get().i = 0;
		                mView1.setOnDragnDropListener(null);
		                mView2.setOnDragnDropListener(null);
		                mView3.setOnDragnDropListener(null);
		                mView4.setOnDragnDropListener(null);
		                mView5.setOnDragnDropListener(null);
		                mView6.setOnDragnDropListener(null);
		                
    				}
    			}
    			else
    				if(appinstance.get().i==4)
        			{
    					
		                appinstance.get().iscorrect = true;
		                removeAll();
		                if(MyApp.soundFlag){
    						_utility.playMusic(R.raw.right_way, SampleDragDropDemoActivity.this);
    						}
		                appinstance.get().mAdapter2.add(new Item(R.drawable.vertical_small_a_main));
		                appinstance.get().mAdapter3.add(new Item(R.drawable.vertical_small_b_main));
		                appinstance.get().mAdapter4.add(new Item(R.drawable.vertical_small_c_main));
		                appinstance.get().mAdapter5.add(new Item(R.drawable.vertical_small_d_main));
		                appinstance.get().mAdapter6.add(new Item(R.drawable.vertical_small_e_main));
		                
		                	mView1.setOnDragnDropListener(null);
			               	mView2.setOnDragnDropListener(null);
			               	mView3.setOnDragnDropListener(null);
			               	mView4.setOnDragnDropListener(null);
			               	mView5.setOnDragnDropListener(null);
			               	mView6.setOnDragnDropListener(null);
           			}
    				else if(appinstance.get().i==1 || appinstance.get().i==2 || appinstance.get().i==3)
    				{
    					j = 4-appinstance.get().i;
    					if(MyApp.soundFlag){
    					_utility.playMusic(R.raw.try_again, SampleDragDropDemoActivity.this);
    					}
    					if(appinstance.get().mAdapter2.getItem(0).icon == R.drawable.vertical_small_b_main)
		                {
		                	appinstance.get()._firstdrop =true;
		                	mView2.setOnDragnDropListener(null);
		                }
		                if(appinstance.get().mAdapter3.getItem(0).icon == R.drawable.vertical_small_e_main)
		                {
		                	appinstance.get()._seconddrop = true;
		                	mView3.setOnDragnDropListener(null);
		                }
		                if(appinstance.get().mAdapter4.getItem(0).icon == R.drawable.vertical_small_a_main)
		                {
		                	appinstance.get()._thirddrop = true;
		                	mView4.setOnDragnDropListener(null);
		                }
		                if(appinstance.get().mAdapter5.getItem(0).icon == R.drawable.vertical_small_d_main)
		                {
		                	appinstance.get()._fourthdrop = true;
		                	mView5.setOnDragnDropListener(null);
		                }
		                if(appinstance.get().mAdapter6.getItem(0).icon == R.drawable.vertical_small_c_main)
		                {
		                	appinstance.get()._fifthdrop = true;
		                	mView6.setOnDragnDropListener(null);
		                }
		                remove();
    					
    				}
    		}
    		if(value1 == 2)
    	   	{
    			appinstance.get().i++;
    			if(appinstance.get().mAdapter2.getItem(0).icon == R.drawable.vertical_small_h_main && appinstance.get().mAdapter3.getItem(0).icon == R.drawable.vertical_small_j_main && appinstance.get().mAdapter4.getItem(0).icon == R.drawable.vertical_small_f_main && appinstance.get().mAdapter5.getItem(0).icon == R.drawable.vertical_small_g_main && appinstance.get().mAdapter6.getItem(0).icon == R.drawable.vertical_small_i_main)
    			{
    			if(appinstance.get().iscorrect ==false)
    			{
    				if(MyApp.soundFlag){
    				_utility.playMusic(R.raw.direction_05, SampleDragDropDemoActivity.this);
    				}
    				appinstance.get().iscorrect =true;
	                appinstance.get().i = 0;
	                mView1.setOnDragnDropListener(null);
	                mView2.setOnDragnDropListener(null);
		            mView3.setOnDragnDropListener(null);
		            mView4.setOnDragnDropListener(null);
		            mView5.setOnDragnDropListener(null);
		            mView6.setOnDragnDropListener(null);
    				
    			}
    			}
    			else
    				if(appinstance.get().i==4)
        			{
    					appinstance.get().iscorrect =true;
		                removeAll();
		                if(MyApp.soundFlag){
    						_utility.playMusic(R.raw.right_way, SampleDragDropDemoActivity.this);
    						}
		                appinstance.get().mAdapter2.add(new Item(R.drawable.vertical_small_f_main));
		                appinstance.get().mAdapter3.add(new Item(R.drawable.vertical_small_g_main));
		                appinstance.get().mAdapter4.add(new Item(R.drawable.vertical_small_h_main));
		                appinstance.get().mAdapter5.add(new Item(R.drawable.vertical_small_i_main));
		                appinstance.get().mAdapter6.add(new Item(R.drawable.vertical_small_j_main));
		                mView1.setOnDragnDropListener(null);
			            mView2.setOnDragnDropListener(null);
			            mView3.setOnDragnDropListener(null);
			            mView4.setOnDragnDropListener(null);
			            mView5.setOnDragnDropListener(null);
			            mView6.setOnDragnDropListener(null);
        				       				
        			}
    				else if(appinstance.get().i==1 || appinstance.get().i==2 || appinstance.get().i==3)
    				{
    					j = 4-appinstance.get().i;
    					if(MyApp.soundFlag){
    					_utility.playMusic(R.raw.try_again, SampleDragDropDemoActivity.this);
    					}
    					if(appinstance.get().mAdapter2.getItem(0).icon == R.drawable.vertical_small_h_main)
		                {
    						appinstance.get()._firstdrop =true;
		                	mView2.setOnDragnDropListener(null);
		                }
		                if(appinstance.get().mAdapter3.getItem(0).icon == R.drawable.vertical_small_j_main)
		                {
		                	appinstance.get()._seconddrop = true;
		                	mView3.setOnDragnDropListener(null);
		                }
		                if(appinstance.get().mAdapter4.getItem(0).icon == R.drawable.vertical_small_f_main)
		                {
		                	appinstance.get()._thirddrop = true;
		                	mView4.setOnDragnDropListener(null);
		                }
		                if(appinstance.get().mAdapter5.getItem(0).icon == R.drawable.vertical_small_g_main)
		                {
		                	appinstance.get()._fourthdrop = true;
		                	mView5.setOnDragnDropListener(null);
		                }
		                if(appinstance.get().mAdapter6.getItem(0).icon == R.drawable.vertical_small_i_main)
		                {
		                	appinstance.get()._fifthdrop = true;
		                	mView6.setOnDragnDropListener(null);
		                }
		                remove();
    				}
    		}
    		if(value1 == 3)
    	   	{
    			appinstance.get().i++;
    			if(appinstance.get().mAdapter2.getItem(0).icon == R.drawable.vertical_small_o_main && appinstance.get().mAdapter3.getItem(0).icon == R.drawable.vertical_small_m_main && appinstance.get().mAdapter4.getItem(0).icon == R.drawable.vertical_small_n_main && appinstance.get().mAdapter5.getItem(0).icon == R.drawable.vertical_small_l_main && appinstance.get().mAdapter6.getItem(0).icon == R.drawable.vertical_small_k_main)
    			{
    				if(appinstance.get().iscorrect ==false)
    				{
    					if(MyApp.soundFlag){
    					_utility.playMusic(R.raw.direction_05, SampleDragDropDemoActivity.this);
    					}
    					 appinstance.get().iscorrect =true;
    					 appinstance.get().i = 0;
			                mView1.setOnDragnDropListener(null);
			               	mView2.setOnDragnDropListener(null);
			               	mView3.setOnDragnDropListener(null);
			               	mView4.setOnDragnDropListener(null);
			               	mView5.setOnDragnDropListener(null);
			               	mView6.setOnDragnDropListener(null);
    					
    				}
    			}
    			else
    				if(appinstance.get().i==4)
        			{
    					appinstance.get().iscorrect =true;
		                removeAll();
		                if(MyApp.soundFlag){
    						_utility.playMusic(R.raw.right_way, SampleDragDropDemoActivity.this);
    						}
		                appinstance.get().mAdapter2.add(new Item(R.drawable.vertical_small_k_main));
		                appinstance.get().mAdapter3.add(new Item(R.drawable.vertical_small_l_main));
		                appinstance.get().mAdapter4.add(new Item(R.drawable.vertical_small_m_main));
		                appinstance.get().mAdapter5.add(new Item(R.drawable.vertical_small_n_main));
		                appinstance.get().mAdapter6.add(new Item(R.drawable.vertical_small_o_main));
		                mView1.setOnDragnDropListener(null);
			               	mView2.setOnDragnDropListener(null);
			               	mView3.setOnDragnDropListener(null);
			               	mView4.setOnDragnDropListener(null);
			               	mView5.setOnDragnDropListener(null);
			               	mView6.setOnDragnDropListener(null);
			            
        			}
    				else if(appinstance.get().i==1 || appinstance.get().i==2 || appinstance.get().i==3)
    				{
    					j = 4-appinstance.get().i;
    					if(MyApp.soundFlag){
    					_utility.playMusic(R.raw.try_again, SampleDragDropDemoActivity.this);
    					}
    					if(appinstance.get().mAdapter2.getItem(0).icon == R.drawable.vertical_small_o_main)
		                {
    						appinstance.get()._firstdrop =true;
		                	mView2.setOnDragnDropListener(null);
		                }
		                if(appinstance.get().mAdapter3.getItem(0).icon == R.drawable.vertical_small_m_main)
		                {
		                	appinstance.get()._seconddrop = true;
		                	mView3.setOnDragnDropListener(null);
		                }
		                if(appinstance.get().mAdapter4.getItem(0).icon == R.drawable.vertical_small_n_main)
		                {
		                	appinstance.get()._thirddrop = true;
		                	mView4.setOnDragnDropListener(null);
		                }
		                if(appinstance.get().mAdapter5.getItem(0).icon == R.drawable.vertical_small_l_main)
		                {
		                	appinstance.get()._fourthdrop = true;
		                	mView5.setOnDragnDropListener(null);
		                }
		                if(appinstance.get().mAdapter6.getItem(0).icon == R.drawable.vertical_small_k_main)
		                {
		                	appinstance.get()._fifthdrop = true;
		                	mView6.setOnDragnDropListener(null);
		                }
		                remove();
     				}
    		}
    		if(value1 == 4)
    	   	{
    			appinstance.get().i++;
    			if(appinstance.get().mAdapter2.getItem(0).icon == R.drawable.vertical_small_p_main && appinstance.get().mAdapter3.getItem(0).icon == R.drawable.vertical_small_t_main && appinstance.get().mAdapter4.getItem(0).icon == R.drawable.vertical_small_s_main && appinstance.get().mAdapter5.getItem(0).icon == R.drawable.vertical_small_r_main && appinstance.get().mAdapter6.getItem(0).icon == R.drawable.vertical_small_q_main)
    			{
    				if(appinstance.get().iscorrect ==false)
    				{
    					if(MyApp.soundFlag){
    					_utility.playMusic(R.raw.direction_05, SampleDragDropDemoActivity.this);
    					}
    					 	appinstance.get().iscorrect =true;
			                appinstance.get().i = 0;
			                mView1.setOnDragnDropListener(null);
			               	mView2.setOnDragnDropListener(null);
			               	mView3.setOnDragnDropListener(null);
			               	mView4.setOnDragnDropListener(null);
			               	mView5.setOnDragnDropListener(null);
			               	mView6.setOnDragnDropListener(null);
    					}
    			}
    			else
    				if(appinstance.get().i==4)
        			{
    					 removeAll();
			             if(MyApp.soundFlag){
	    					_utility.playMusic(R.raw.right_way, SampleDragDropDemoActivity.this);
	    				}
			                appinstance.get().iscorrect =true;
			                appinstance.get().mAdapter2.add(new Item(R.drawable.vertical_small_p_main));
			                appinstance.get().mAdapter3.add(new Item(R.drawable.vertical_small_q_main));
			                appinstance.get().mAdapter4.add(new Item(R.drawable.vertical_small_r_main));
			                appinstance.get().mAdapter5.add(new Item(R.drawable.vertical_small_s_main));
			                appinstance.get().mAdapter6.add(new Item(R.drawable.vertical_small_t_main));
			                mView1.setOnDragnDropListener(null);
			               	mView2.setOnDragnDropListener(null);
			               	mView3.setOnDragnDropListener(null);
			               	mView4.setOnDragnDropListener(null);
			               	mView5.setOnDragnDropListener(null);
			               	mView6.setOnDragnDropListener(null);
			            }
    				else if(appinstance.get().i==1 || appinstance.get().i==2 || appinstance.get().i==3)
    				{
    					j = 4-appinstance.get().i;
    					if(MyApp.soundFlag){
    					_utility.playMusic(R.raw.try_again, SampleDragDropDemoActivity.this);
    					}
    					if(appinstance.get().mAdapter2.getItem(0).icon == R.drawable.vertical_small_p_main)
		                {
    						appinstance.get()._firstdrop =true;
		                	mView2.setOnDragnDropListener(null);
		                }
		                if(appinstance.get().mAdapter3.getItem(0).icon == R.drawable.vertical_small_t_main)
		                {
		                	appinstance.get()._seconddrop = true;
		                	mView3.setOnDragnDropListener(null);
		                }
		                if(appinstance.get().mAdapter4.getItem(0).icon == R.drawable.vertical_small_s_main)
		                {
		                	appinstance.get()._thirddrop = true;
		                	mView4.setOnDragnDropListener(null);
		                }
		                if(appinstance.get().mAdapter5.getItem(0).icon == R.drawable.vertical_small_r_main)
		                {
		                	appinstance.get()._fourthdrop = true;
		                	mView5.setOnDragnDropListener(null);
		                }
		                if(appinstance.get().mAdapter6.getItem(0).icon == R.drawable.vertical_small_q_main)
		                {
		                	appinstance.get()._fifthdrop = true;
		                	mView6.setOnDragnDropListener(null);
		                }
		                remove();
    					
    				}
    		}
    		
    		if(value1 == 5)
    	   	{
    			if(appinstance.get().mAdapter7.getCount()!=0)
    			{
    				appinstance.get().i++;
    				if(appinstance.get().mAdapter2.getItem(0).icon == R.drawable.w_small_resized && appinstance.get().mAdapter3.getItem(0).icon == R.drawable.v_small_resized && appinstance.get().mAdapter4.getItem(0).icon == R.drawable.u_small_resized && appinstance.get().mAdapter5.getItem(0).icon == R.drawable.z_small_resized && appinstance.get().mAdapter6.getItem(0).icon == R.drawable.x_small_resized && appinstance.get().mAdapter7.getItem(0).icon == R.drawable.y_small_resized)
    				{
    					if(appinstance.get().iscorrect ==false)
    					{
    						if(MyApp.soundFlag){
    						_utility.playMusic(R.raw.direction_05, SampleDragDropDemoActivity.this);
    						}
    						appinstance.get().iscorrect =true;
			                appinstance.get().i = 0;
			                mView1.setOnDragnDropListener(null);
				            mView2.setOnDragnDropListener(null);
				            mView3.setOnDragnDropListener(null);
				            mView4.setOnDragnDropListener(null);
				            mView5.setOnDragnDropListener(null);
				            mView6.setOnDragnDropListener(null);
				            mView7.setOnDragnDropListener(null);
				           
    					}
    				}
    				else
        				if(appinstance.get().i==4)
            			{
        					appinstance.get().iscorrect =true;
			                removeAll();
			                
			                if(MyApp.soundFlag){
	    						_utility.playMusic(R.raw.right_way, SampleDragDropDemoActivity.this);
	    						}
			                appinstance.get().mAdapter2.add(new Item(R.drawable.u_small_resized));
			                appinstance.get().mAdapter3.add(new Item(R.drawable.v_small_resized));
			                appinstance.get().mAdapter4.add(new Item(R.drawable.w_small_resized));
			                appinstance.get().mAdapter5.add(new Item(R.drawable.x_small_resized));
			                appinstance.get().mAdapter6.add(new Item(R.drawable.y_small_resized));
			                appinstance.get().mAdapter7.add(new Item(R.drawable.z_small_resized));
			                	mView1.setOnDragnDropListener(null);
				               	mView2.setOnDragnDropListener(null);
				               	mView3.setOnDragnDropListener(null);
				               	mView4.setOnDragnDropListener(null);
				               	mView5.setOnDragnDropListener(null);
				               	mView6.setOnDragnDropListener(null);
				               	mView7.setOnDragnDropListener(null);
				               	           				
            			}
        				else if(appinstance.get().i==1 || appinstance.get().i==2 || appinstance.get().i==3)
        				{
        					j = 4-appinstance.get().i;
        					if(MyApp.soundFlag){
        						_utility.playMusic(R.raw.try_again, SampleDragDropDemoActivity.this);
        					}
        					if(appinstance.get().mAdapter2.getItem(0).icon == R.drawable.w_small_resized)
    		                {
        						appinstance.get()._firstdrop =true;
    		                	mView2.setOnDragnDropListener(null);
    		                }
    		                if(appinstance.get().mAdapter3.getItem(0).icon == R.drawable.v_small_resized)
    		                {
    		                	appinstance.get()._seconddrop = true;
    		                	mView3.setOnDragnDropListener(null);
    		                }
    		                if(appinstance.get().mAdapter4.getItem(0).icon == R.drawable.u_small_resized)
    		                {
    		                	appinstance.get()._thirddrop = true;
    		                	mView4.setOnDragnDropListener(null);
    		                }
    		                if(appinstance.get().mAdapter5.getItem(0).icon == R.drawable.z_small_resized)
    		                {
    		                	appinstance.get()._fourthdrop = true;
    		                	mView5.setOnDragnDropListener(null);
    		                }
    		                if(appinstance.get().mAdapter6.getItem(0).icon == R.drawable.x_small_resized)
    		                {
    		                	appinstance.get()._fifthdrop = true;
    		                	mView6.setOnDragnDropListener(null);
    		                }
    		                if(appinstance.get().mAdapter7.getItem(0).icon == R.drawable.y_small_resized)
    		                {
    		                	appinstance.get()._sixthdrop = true;
    		                	mView7.setOnDragnDropListener(null);
    		                }
    		                remove();
        					
        				}
    			}
    		}
    	}
    }
    private class Item {
        public int icon;         
      //  public String name;
        Item(int icon) {
            this.icon = icon;
           
        }
    }
   
    
    private class MyAdapter extends ArrayAdapter<Item> {
        private LayoutInflater mInflater;
        private ArrayList<Item> items;
       
        public MyAdapter(Context context, ArrayList<Item> items) {
            super(context, R.layout.item, items);
            mInflater = LayoutInflater.from(context);
            this.items = items;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item, null);
                holder = new ViewHolder();
                holder.ivIcon = (ImageView) convertView.findViewById(R.id.icon);
              //holder.tvName = (TextView) convertView.findViewById(R.id.name);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.ivIcon.setImageResource(items.get(position).icon);
            //holder.tvName.setText(items.get(position).name);
            
            return convertView;
        }
    }

    class ViewHolder {
        ImageView ivIcon;
       // TextView tvName;
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==helpbtn)
		{
			/*Intent helpintent=new Intent(this,HelpScreen.class);
			helpintent.putExtra("help", 0);
			startActivity(helpintent);*/
			
			showDialog();
		}
		if(v == next)
		{
			if(value1==1)
			{
				this.finish();
				Intent st1 = new Intent(this, SampleDragDropDemoActivity.class);
				st1.putExtra("set", 2);
				startActivity(st1);
			}
			if(value1==2)
			{
				this.finish();
				Intent st2 = new Intent(this, SampleDragDropDemoActivity.class);
				st2.putExtra("set", 3);
				startActivity(st2);
			}
			if(value1==3)
			{
				this.finish();
				Intent st3 = new Intent(this, SampleDragDropDemoActivity.class);
				st3.putExtra("set", 4);
				startActivity(st3);
			}
			if(value1==4)
			{
				this.finish();
				Intent st4 = new Intent(this, SampleDragDropDemoActivity.class);
				st4.putExtra("set", 5);
				startActivity(st4);
			}
			/*if(value1==5)
			{
				Intent st5 = new Intent(this, SampleDragDropDemoActivity.class);
				st5.putExtra("set", 5);
				startActivity(st5);
			}*/
		}
		
		if(v==previous)
		{
			/*if(value1==1)
			{
				
			}*/
			if(value1==2)
			{
				this.finish();
				Intent in1 = new Intent(this, SampleDragDropDemoActivity.class);
				in1.putExtra("set", 1);
				startActivity(in1);
			}
			if(value1==3)
			{
				this.finish();
				Intent in2 = new Intent(this, SampleDragDropDemoActivity.class);
				in2.putExtra("set", 2);
				startActivity(in2);
			}
			if(value1==4)
			{
				this.finish();
				Intent in3 = new Intent(this, SampleDragDropDemoActivity.class);
				in3.putExtra("set", 3);
				startActivity(in3);
			}
			if(value1==5)
			{
				this.finish();
				Intent in4 = new Intent(this, SampleDragDropDemoActivity.class);
				in4.putExtra("set", 4);
				startActivity(in4);
			}
		}
		if(v==slidebtn)
		{
			this.finish();
		}
		/*if(v==infobtn)
		{
			Intent infointent =new Intent(this,InfoScreen.class);
			infointent.putExtra("info", 0);
			startActivity(infointent);
		}*/
		if(v==homebtn)
		{
			this.finish();
			GameSetActivity.appinstance.get().finish();
		}
		if(v==soundbtn)
		{
			if(MyApp.soundFlag){
				soundbtn.setImageResource(R.drawable.audio_off_main);
				MyApp.soundFlag = false;
			}
			else{
				soundbtn.setImageResource(R.drawable.audio_on_main);
				MyApp.soundFlag = true;
			}
		}
	}


}
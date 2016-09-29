/*
 * Class to initilized variable and to call ImageCard.java 
 */

package com.aptara.flashcard;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Vector;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.AdapterView.OnItemClickListener;

public class ViewActivity extends Activity implements OnClickListener{
	/** Called when the activity is first created. */

	private static int iCardCount = 26;
	public boolean randomFlag;
	private boolean isFirstImage = true;

	//public ImageButton infoButton;
	public ImageButton helpButton;
	public ImageButton soundButton;
	public ImageButton mixButton;
	public ImageButton homeButton;
	public ImageButton iconButton;
	public ImageButton autoButton;
	public static boolean autoFlag = false;
	public static Integer[] tempImgObject;
	public static Integer[] tempImgName ;//= Integer[5];
	public static Integer[] tempSoundObject;
	public static Integer[] tempSoundName ;//= Integer[5];
	public static Integer[] tempImgNameID;
	public static Integer[] tempImgObjectID;
	public static Integer[] tempSoundLetterID;
	public static Integer[] tempSoundObjID;
	private Vector random;
	public Gallery gallery;
	private HashMap<String, Integer[]> list;
	private Vector<Object> storeVec;
	private ImageAdapter adapterInst;
	private MyViewFlipper cardFlipper;
	public static Integer[] soundObject= null;
	public static Integer[] imgLetterName;
	public static Integer[] imgObjectName; 
	public static Integer[] soundLetter;
	public static int cardPos = 0;

	private Handler handler = new Handler()	;
	private final int SPLASH_DISPLAY_LENGHT = 200;
	Utility util = null;
	AlertDialog.Builder builder;
	private MyViewFlipper viewFlipper1;
	private MyViewFlipper viewFlipper3;
	private ImageView imageleft;
	private ImageView imageright;
	private int leftcardPos =-1;
	private int rightcardPos =0;
	float downXValue =0;
	long downTime = 0;
	
	RelativeLayout relativeLayout3;
	RelativeLayout animation;
	public static ImageCard card1 ;
	ImageView image1;
	ImageView image2;
	boolean auto=false;
	public boolean flag = true;
	public boolean flag1 = true;
	public boolean flag2 = true;
	private ImageView _backswipe;
	private ImageView _nextswipe;
	public static WeakReference<ViewActivity> appinstance = null;
	public static boolean animationSyncFlag = true;
	public int childCount = 0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.flashcard_main);
		imgLetterName = new Integer[]{
				R.drawable.a_card_big,
				R.drawable.b_card_big,			
				R.drawable.c_card_big,
				R.drawable.d_card_big,
				R.drawable.e_card_big,
				R.drawable.f_card_big,
				R.drawable.g_card_big,			
				R.drawable.h_card_big,
				R.drawable.i_card_big,
				R.drawable.j_card_big,
				R.drawable.k_card_big,
				R.drawable.l_card_big,			
				R.drawable.m_card_big,
				R.drawable.n_card_big,
				R.drawable.o_card_big,
				R.drawable.p_card_big,
				R.drawable.q_card_big,			
				R.drawable.r_card_big,
				R.drawable.s_card_big,
				R.drawable.t_card_big,
				R.drawable.u_card_big,
				R.drawable.v_card_big,			
				R.drawable.w_card_big,
				R.drawable.x_card_big,
				R.drawable.y_card_big,
				R.drawable.z_card_big

		};
		imgObjectName = new Integer[] {
				R.drawable.apple,
				R.drawable.barn,			
				R.drawable.cow,
				R.drawable.dog,
				R.drawable.egg,
				R.drawable.fence,
				R.drawable.goat,			
				R.drawable.hay,
				R.drawable.insect,
				R.drawable.jar,
				R.drawable.kitten,
				R.drawable.lamb,			
				R.drawable.mouse,
				R.drawable.nest,
				R.drawable.overalls,
				R.drawable.pig,
				R.drawable.quack,			
				R.drawable.rooster,
				R.drawable.seeds,
				R.drawable.tractor,
				R.drawable.ugly_duckling,
				R.drawable.vegetables,			
				R.drawable.wheelbarrow,
				R.drawable.x_block,
				R.drawable.yam,
				R.drawable.zipper
		};

		soundLetter = new Integer[] {
				R.raw.a_letter,
				R.raw.b_letter,			
				R.raw.c_letter,
				R.raw.d_letter,
				R.raw.e_letter,
				R.raw.f_letter,
				R.raw.g_letter,			
				R.raw.h_letter,
				R.raw.i_letter,
				R.raw.j_letter,
				R.raw.k_letter,
				R.raw.l_letter,			
				R.raw.m_letter,
				R.raw.n_letter,
				R.raw.o_letter,
				R.raw.p_letter,
				R.raw.q_letter,			
				R.raw.r_letter,
				R.raw.s_letter,
				R.raw.t_letter,
				R.raw.u_letter,			
				R.raw.v_letter,
				R.raw.w_letter,
				R.raw.x_letter,
				R.raw.y_letter,
				R.raw.z_letter
		};
		soundObject = new Integer[]{
				R.raw.a_image,
				R.raw.b_image,			
				R.raw.c_image,
				R.raw.d_image,
				R.raw.e_image,
				R.raw.f_image,
				R.raw.g_image,			
				R.raw.h_image,
				R.raw.i_image,
				R.raw.j_image,
				R.raw.k_image,
				R.raw.l_image,			
				R.raw.m_image,
				R.raw.n_image,
				R.raw.o_image,
				R.raw.p_image,
				R.raw.q_image,			
				R.raw.r_image,
				R.raw.s_image,
				R.raw.t_image,
				R.raw.u_image,
				R.raw.v_image,			
				R.raw.w_image,
				R.raw.x_letter,
				R.raw.y_image,
				R.raw.z_image
		};

		relativeLayout3 = (RelativeLayout)findViewById(R.id.viewflip);
		animation = (RelativeLayout)findViewById(R.id.animation);

		util = new Utility();
		soundButton = (ImageButton)findViewById(R.id.soundbutton);
		if(MyApp.soundFlag){
			soundButton.setImageResource(R.drawable.audio_on_main);
		}else{
			soundButton.setImageResource(R.drawable.audio_off_main);
		}
		soundButton.setOnClickListener(this);
		helpButton = (ImageButton)findViewById(R.id.helpbutton);
		helpButton.setOnClickListener(this);
		homeButton = (ImageButton)findViewById(R.id.homebutton);
		homeButton.setOnClickListener(this);
		mixButton = (ImageButton)findViewById(R.id.button1);
		mixButton.setOnClickListener(this);
		iconButton = (ImageButton)findViewById(R.id.iconButton);
		iconButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(Utility.isOnline(ViewActivity.this))
				{
					Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse("http://www.carsondellosa.com"));
					startActivity(i);
				}
				else
				{
					Toast.makeText(ViewActivity.this, "Please check the connectivity and try again.", Toast.LENGTH_SHORT).show();
				}
			}
		});
		image1 = (ImageView) findViewById(R.id.ImageView01);
		image2 = (ImageView) findViewById(R.id.ImageView02);
		image2.setVisibility(View.GONE);
		gallery = (Gallery) findViewById(R.id.gallery1);
		_backswipe = (ImageView)findViewById(R.id.backswipe);
		_nextswipe = (ImageView)findViewById(R.id.nextswipe);
		_backswipe.setOnClickListener(this);
		_nextswipe.setOnClickListener(this);
		viewFlipper1 = (MyViewFlipper)findViewById(R.id.cardFlipper1);
		/// TouchListener for viewflipper1 
		viewFlipper1.setOnTouchListener(new OnTouchListener() 
		{
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				final int action = event.getAction(); 

				final float x = event.getX(); 
				final float y = event.getY(); 

				switch (action) { 
				case MotionEvent.ACTION_MOVE: 
					break; 

				case MotionEvent.ACTION_DOWN: 
					// Remember location of down touch 
					downXValue = event.getX();
					downTime = event.getEventTime();
					break; 

				case MotionEvent.ACTION_CANCEL: 
				case MotionEvent.ACTION_UP:
				{
					if(animationSyncFlag == true)
					{
						animationSyncFlag = false;

						float currentX = event.getX();
						long currentTime = event.getEventTime();
						float difference = Math.abs(downXValue - currentX);
						long time = currentTime - downTime;

						Log.i("Touch Event:",  "Distance: " + difference + "px Time: " + time + "ms");
						Log.i("X","downXValue: " +downXValue+ "currextX: "+currentX);
						//// for other than 0 child postion and back swipe i.e show previous
						if(viewFlipper1.getDisplayedChild()!=0)
						{  
							if ( (downXValue < currentX) && (time > 50) && (difference > 30) ) 
							{
								Log.i("right Pos","pos: " +appinstance.get().rightcardPos);
								if(appinstance.get().rightcardPos==0 || appinstance.get().rightcardPos ==25)
								{	
									_nextswipe.setImageResource(R.drawable.next_btn);
									viewFlipper3.setVisibility(View.VISIBLE);
								}

								//showPrevious();
								viewFlipper1.setInAnimation(ViewActivity.this, R.anim.view_transition_in_right);
								viewFlipper1.setOutAnimation(ViewActivity.this, R.anim.view_transition_out_right);
								viewFlipper1.showPrevious();

								appinstance.get().cardPos--;
								getCard(appinstance.get().cardPos,1,1);
								cardFlipper.showPrevious();		                
								viewFlipper3.setInAnimation(ViewActivity.this, R.anim.view_transition_in_left);
								viewFlipper3.setOutAnimation(ViewActivity.this, R.anim.view_transition_out_left);
								viewFlipper3.showPrevious();
								image1.setImageResource(tempImgName[appinstance.get().cardPos]);
								image2.setImageResource(tempImgObject[appinstance.get().cardPos]);

								if(appinstance.get().autoFlag == false && animationSyncFlag == false){
									animationSyncFlag = true;
								}

								if(appinstance.get().autoFlag)
								{
									flag = true;
									flag1 = true;
									flag2 = true;
									isFirstImage = true;
									start();

								}
							}
							else
							{
								animationSyncFlag = true;
							}
							
						}
						//// for 0th position previous
						else
						{
							if ( (downXValue < currentX) && (time > 50) && (difference > 30) ) 
							{
								viewFlipper1.setDisplayedChild(-1);
								viewFlipper1.setVisibility(View.INVISIBLE);
								appinstance.get().cardPos--;
								getCard(appinstance.get().cardPos,1,1);
								cardFlipper.showPrevious();		                
								viewFlipper3.setInAnimation(ViewActivity.this, R.anim.view_transition_in_left);
								viewFlipper3.setOutAnimation(ViewActivity.this, R.anim.view_transition_out_left);
								viewFlipper3.showPrevious();
								image1.setImageResource(tempImgName[appinstance.get().cardPos]);
								image2.setImageResource(tempImgObject[appinstance.get().cardPos]);
								_backswipe.setImageResource(R.drawable.back_btn_inactive);
								if(appinstance.get().autoFlag == false && animationSyncFlag == false){
									animationSyncFlag = true;
								}
								if(appinstance.get().autoFlag)
								{
									flag = true;
									flag1 = true;
									flag2 = true;
									isFirstImage = true;
									start();

								}
							}
						}
						//// for less than 25th position next swipe i.e shownext
						if(viewFlipper3.getDisplayedChild()!=25)
						{
							if(viewFlipper3.getVisibility()!=View.INVISIBLE)
							{
								if ( (downXValue > currentX) && (time > 50) && (difference > 30) ) {
									//showNext();
									if(appinstance.get().leftcardPos==-1)
										viewFlipper1.setVisibility(View.VISIBLE);
									viewFlipper1.setInAnimation(ViewActivity.this, R.anim.view_transition_in_left);
									viewFlipper1.setOutAnimation(ViewActivity.this, R.anim.view_transition_out_left);
									viewFlipper1.showNext();

									appinstance.get().cardPos++;
									getCard(appinstance.get().cardPos,2,1);
									cardFlipper.showNext(); 	                
									viewFlipper3.setInAnimation(ViewActivity.this, R.anim.view_transition_in_left);
									viewFlipper3.setOutAnimation(ViewActivity.this, R.anim.view_transition_out_left);
									viewFlipper3.showNext();
									image1.setImageResource(tempImgName[appinstance.get().cardPos]);
									image2.setImageResource(tempImgObject[appinstance.get().cardPos]);
									if(appinstance.get().autoFlag == false && animationSyncFlag == false){
										animationSyncFlag = true;
									}
									if(appinstance.get().autoFlag)
									{
										flag = true;
										flag1 = true;
										flag2 = true;
										isFirstImage = true;
										start();

									}
								}
							}
						}
						/// for 25th position
						else 
							/*if(viewFlipper3.getDisplayedChild()==25)*/
							{
							if ( (downXValue > currentX) && (time > 50) && (difference > 30)  ) 
							{
								viewFlipper3.setDisplayedChild(0);
								viewFlipper3.setVisibility(View.INVISIBLE);
								appinstance.get().rightcardPos = 0;
								_nextswipe.setImageResource(R.drawable.next_btn_inactive);
								appinstance.get().cardPos++;
								getCard(appinstance.get().cardPos,1,1);
								cardFlipper.showNext();		                
								viewFlipper1.setInAnimation(ViewActivity.this, R.anim.view_transition_in_left);
								viewFlipper1.setOutAnimation(ViewActivity.this, R.anim.view_transition_out_left);
								viewFlipper1.showNext();
								image1.setImageResource(tempImgName[appinstance.get().cardPos]);
								image2.setImageResource(tempImgObject[appinstance.get().cardPos]);

								if(appinstance.get().autoFlag == false && animationSyncFlag == false){
									animationSyncFlag = true;
								}
								if(appinstance.get().autoFlag)
								{
									flag = true;
									flag1 = true;
									flag2 = true;
									isFirstImage = true;
									start();

								}
							}
						}
					}
					break;
				}
				} 
				return true;
			}
		});
		viewFlipper3 = (MyViewFlipper)findViewById(R.id.cardFlipper3);
		viewFlipper3.setOnTouchListener(new OnTouchListener() {
			/// touchListener for viewflipper 3
			public boolean onTouch(View view, MotionEvent event) {

				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
				{
					downXValue = event.getX();
					downTime = event.getEventTime();
					break;
				}

				case MotionEvent.ACTION_UP:
				{
					if(animationSyncFlag == true)
					{
						animationSyncFlag = false;
						float currentX = event.getX();
						long currentTime = event.getEventTime();
						float difference = Math.abs(downXValue - currentX);
						long time = currentTime - downTime;

						Log.i("Touch Event:",  "Distance: " + difference + "px Time: " + time + "ms");
						Log.i("X","downXValue: " +downXValue+ "currextX: "+currentX);
						Log.i("disoplay child >>>>>>>>>>>>>>> ","display child: " +viewFlipper3.getChildCount());
						/// for other than 0 position backswipe i.e showprevious
						if(viewFlipper1.getDisplayedChild()!=0)
						{  
							if ( (downXValue < currentX) && (time > 50) && (difference > 30) ) 
							{
								//showPrevious();
								if(viewFlipper1.getVisibility()!=View.INVISIBLE)
								{

									viewFlipper1.setInAnimation(ViewActivity.this, R.anim.view_transition_in_right);
									viewFlipper1.setOutAnimation(ViewActivity.this, R.anim.view_transition_out_right);
									viewFlipper1.showPrevious();

									appinstance.get().cardPos--;
									getCard(appinstance.get().cardPos,1,1);
									cardFlipper.showPrevious();	                

									viewFlipper3.setInAnimation(ViewActivity.this, R.anim.view_transition_in_left);
									viewFlipper3.setOutAnimation(ViewActivity.this, R.anim.view_transition_out_left);
									viewFlipper3.showPrevious();
									image1.setImageResource(tempImgName[appinstance.get().cardPos]);
									image2.setImageResource(tempImgObject[appinstance.get().cardPos]);

									if(appinstance.get().autoFlag == false && animationSyncFlag == false){
										animationSyncFlag = true;
									}
									if(appinstance.get().autoFlag )
									{
										flag = true;
										flag1 = true;
										flag2 = true;
										isFirstImage = true;
										start();

									}
								}
							}
						}
						else // 0th condition
						{
							if ( (downXValue < currentX) && (time > 50) && (difference > 30) ) 
							{
								if(viewFlipper1.getVisibility()!=View.INVISIBLE)
								{
									viewFlipper1.setDisplayedChild(-1);
									viewFlipper1.setVisibility(View.INVISIBLE);
									_backswipe.setImageResource(R.drawable.back_btn_inactive);
									appinstance.get().cardPos--;
									getCard(appinstance.get().cardPos,1,1);
									cardFlipper.showPrevious();		                
										
									viewFlipper3.setInAnimation(ViewActivity.this, R.anim.view_transition_in_left);
									viewFlipper3.setOutAnimation(ViewActivity.this, R.anim.view_transition_out_left);
									viewFlipper3.showPrevious();
									image1.setImageResource(tempImgName[appinstance.get().cardPos]);
									image2.setImageResource(tempImgObject[appinstance.get().cardPos]);
									if(appinstance.get().autoFlag == false && animationSyncFlag == false){
										animationSyncFlag = true;
									}
									if(appinstance.get().autoFlag)
									{
										flag = true;
										flag1 = true;
										flag2 = true;
										isFirstImage = true;
										start();

									}

								}
							}
						}
						//// for less than 25th position nextswipe i.e shownext
						if(viewFlipper3.getDisplayedChild()!=25)
						{
							if ( (downXValue > currentX) && (time > 50) && (difference > 30) ) {
								//showNext();

								if(appinstance.get().leftcardPos==-1)
								{
									_backswipe.setImageResource(R.drawable.backswipe);
									viewFlipper1.setVisibility(View.VISIBLE);
								}
								else
								{
									viewFlipper1.setVisibility(View.VISIBLE);
									_backswipe.setImageResource(R.drawable.backswipe);
								}

								Log.i("ViewFlipper1 Child: ",""+viewFlipper1.getDisplayedChild());
								viewFlipper1.setInAnimation(ViewActivity.this, R.anim.view_transition_in_left);
								viewFlipper1.setOutAnimation(ViewActivity.this, R.anim.view_transition_out_left);
								viewFlipper1.showNext();

								appinstance.get().cardPos++;
								getCard(appinstance.get().cardPos,2,1);
								cardFlipper.showNext(); 	                

								viewFlipper3.setInAnimation(ViewActivity.this, R.anim.view_transition_in_left);
								viewFlipper3.setOutAnimation(ViewActivity.this, R.anim.view_transition_out_left);
								viewFlipper3.showNext();
								image1.setImageResource(tempImgName[appinstance.get().cardPos]);
								image2.setImageResource(tempImgObject[appinstance.get().cardPos]);
								if(appinstance.get().autoFlag == false && animationSyncFlag == false){
									animationSyncFlag = true;
								}
								if(appinstance.get().autoFlag)
								{
									flag = true;
									flag1 = true;
									flag2 = true;
									isFirstImage = true;
									start();

								}
							}
							else
							{
								animationSyncFlag = true;
							}
						}
						else////for 25th positon nextswipe 
							{
							if ( (downXValue > currentX) && (time > 50) && (difference > 30)  ) 
							{
								viewFlipper3.setDisplayedChild(0);
								viewFlipper3.setVisibility(View.INVISIBLE);
								appinstance.get().rightcardPos = 0;
								appinstance.get().cardPos++;
								getCard(appinstance.get().cardPos,1,1);
								cardFlipper.showNext();		                
								viewFlipper1.setInAnimation(ViewActivity.this, R.anim.view_transition_in_left);
								viewFlipper1.setOutAnimation(ViewActivity.this, R.anim.view_transition_out_left);
								viewFlipper1.showNext();
								image1.setImageResource(tempImgName[appinstance.get().cardPos]);
								image2.setImageResource(tempImgObject[appinstance.get().cardPos]);
								_nextswipe.setImageResource(R.drawable.next_btn_inactive);
								if(appinstance.get().autoFlag == false && animationSyncFlag == false){
									animationSyncFlag = true;
								}
								if(appinstance.get().autoFlag)
								{
									flag = true;
									flag1 = true;
									flag2 = true;
									isFirstImage = true;
									start();

								}
							}
						}
					}
					break;

				}
				}
				return true;
			}
		});



		cardFlipper = (MyViewFlipper) findViewById(R.id.cardFlipper); 
		list =  (HashMap<String, Integer[]>)getLastNonConfigurationInstance();
		if(list != null){

			tempImgName = list.get("tempImgName");
			tempImgObject = list.get("tempImgObject");
			tempSoundName = list.get("tempSoundName");
			tempSoundObject = list.get("tempSoundObject");
			cardFlipper = (MyViewFlipper) findViewById(R.id.cardFlipper);
			//cardFlipper = appinstance.get().cardFlipper;
			for(int i=0;i<=appinstance.get().childCount;i++)
			{
				ImageCard card1 = new ImageCard(getApplicationContext(),i,appinstance.get().tempImgName, appinstance.get().tempImgObject);
				cardFlipper.addView(card1);
				
			}
			cardFlipper.setDisplayedChild(appinstance.get().cardPos);
			if(tempImgNameID == null)
			{
				tempImgNameID = new Integer[iCardCount];
				tempImgObjectID = new Integer[iCardCount];
				tempSoundLetterID = new Integer[iCardCount];
				tempSoundObjID = new Integer[iCardCount];
			}
				for(int i = 0; i < iCardCount; i++){
					tempImgNameID[i] = tempImgName[i];
					tempImgObjectID[i] = tempImgObject[i];
					tempSoundLetterID[i] = tempSoundName[i];
					tempSoundObjID[i] = tempSoundObject[i];
				}
			
			appinstance.get().adapterInst.notifyDataSetChanged();
			gallery.setAdapter(appinstance.get().adapterInst);
			//getCard(appinstance.get().cardPos,1,1);

			if(appinstance.get().leftcardPos == -1 || appinstance.get().leftcardPos == 25)
			{
				_backswipe.setImageResource(R.drawable.back_btn_inactive);
				viewFlipper1.setVisibility(View.INVISIBLE);
			}
			else
			{
				_backswipe.setImageResource(R.drawable.backswipe);
				viewFlipper1.setVisibility(View.VISIBLE);
			}
			if(appinstance.get().rightcardPos == 0 || appinstance.get().rightcardPos == 25)
			{
				_nextswipe.setImageResource(R.drawable.next_btn_inactive);
				viewFlipper3.setVisibility(View.INVISIBLE);
			}
			else
			{
				_nextswipe.setImageResource(R.drawable.nextswipe);
				viewFlipper3.setVisibility(View.VISIBLE);
			}
			if(appinstance.get().autoFlag==true)
			{
				image1.setImageResource(tempImgName[appinstance.get().cardPos]);
				image2.setImageResource(tempImgObject[appinstance.get().cardPos]);
				animation.setVisibility(View.VISIBLE);
				relativeLayout3.setVisibility(View.INVISIBLE);
			}
			else
			{
				animation.setVisibility(View.INVISIBLE);
				relativeLayout3.setVisibility(View.VISIBLE);
			}
			appinstance.get().leftcardPos = appinstance.get().leftcardPos;
		}
		else{
			appinstance = new WeakReference<ViewActivity>(this);
			tempImgName = imgLetterName;
			tempImgObject = imgObjectName;
			tempSoundName = soundLetter;
			tempSoundObject = soundObject;

			tempImgNameID = new Integer[iCardCount];
			tempImgObjectID = new Integer[iCardCount];
			tempSoundLetterID = new Integer[iCardCount];
			tempSoundObjID = new Integer[iCardCount];

			for(int i = 0; i < iCardCount; i++){
				tempImgNameID[i] = imgLetterName[i];
				tempImgObjectID[i] = imgObjectName[i];
				tempSoundLetterID[i] = soundLetter[i];
				tempSoundObjID[i] = soundObject[i];
			}

			appinstance.get().adapterInst = new ImageAdapter(this);
			gallery.setAdapter(appinstance.get().adapterInst);
			
			appinstance.get().leftcardPos = -1;
			appinstance.get().cardPos = 0;
			appinstance.get().rightcardPos = 1;
			if(appinstance.get().leftcardPos == -1 )
			{
				_backswipe.setImageResource(R.drawable.back_btn_inactive);
				viewFlipper1.setVisibility(View.INVISIBLE);
			}
			else
			{
				viewFlipper1.setVisibility(View.VISIBLE);
			}
			
			getCard(appinstance.get().cardPos,2,1);
			appinstance.get().autoFlag = false;
			appinstance.get().randomFlag = true;
			appinstance.get().childCount = 0;
		}	
		//getCard(appinstance.get().cardPos,2,1);
		gallery.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView parent, View v, int position, long id) 
			{
				appinstance.get().cardPos = position;
				if(util.mPlayer != null){
					util.mPlayer.release();
				}
				getCard(position,2,1);				
			} 
		});	
		SwipeView();
		autoButton = (ImageButton)findViewById(R.id.autobutton);
		if(appinstance.get().autoFlag==true)
		{
			autoButton.setImageResource(R.drawable.auto_on);
		}else{
			autoButton.setImageResource(R.drawable.auto_off);
		}
		autoButton.setOnClickListener(this);
	}

	public void SwipeView()
	{
		imgLetterName = tempImgName ;
		for (int i = 0; i < tempImgName.length; i++)
		{
			imageleft = new ImageView(this);
			imageleft.setLayoutParams(new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			imageleft.setBackgroundResource(imgLetterName[i]);
			viewFlipper1.addView(imageleft);
		}
		for (int i = 0; i < imgLetterName.length; i++)
		{
			imageright = new ImageView(this);
			imageright.setLayoutParams(new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			imageright.setBackgroundResource(imgLetterName[i]);
			viewFlipper3.addView(imageright);
		}

		if(appinstance.get().leftcardPos==-1)
		{
			viewFlipper1.setVisibility(View.INVISIBLE);
		}
		viewFlipper1.setDisplayedChild(appinstance.get().leftcardPos);
		viewFlipper3.setDisplayedChild(appinstance.get().rightcardPos);
	}	
	public void onResume(){
		super.onResume();
		try 
		{
			if(Utility.mDialog!=null)
			{
				Utility.mDialog = null;
				util.showDialog(this);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}	
		if(appinstance.get().builder != null){
			appinstance.get().builder.show();
		}
	}
	/*public void onPause()
	{
		super.onPause();
		if(Utility.mPlayer!=null)
		{
			if(Utility.mPlayer.isPlaying())
			{
				Utility.mPlayer.stop();
				Utility.mPlayer.release();
				Utility.mPlayer = null;
			}
		}
	}*/
	
	private void getCard(int position , int anim,int ishow) {
		/*cardFlipper = (ViewFlipper) findViewById(R.id.cardFlipper);
		for(int i=0;i<tempImgName.length;i++)
		{
			card1 = new ImageCard(getApplicationContext(),i,appinstance.get().tempImgName, appinstance.get().tempImgObject);
			cardFlipper.addView(card1);
		}*/
		//nextCard(anim);
		if(ishow!=0)
		{
			cardFlipper = (MyViewFlipper) findViewById(R.id.cardFlipper);
			if(position>=cardFlipper.getChildCount())
			{
				card1 = new ImageCard(getApplicationContext(),position,appinstance.get().tempImgName, appinstance.get().tempImgObject);
				cardFlipper.addView(card1/*,appinstance.get().cardPos*/);
				appinstance.get().childCount = cardFlipper.getChildCount();
			}
			else
			{
				//card1.imgView.setVisibility(View.GONE);
				//ImageCard card1 = new ImageCard(getApplicationContext(),position,appinstance.get().tempImgName, appinstance.get().tempImgObject);
				//cardFlipper.removeViewAt(position);
				//cardFlipper.addView(card1,position);
				for(int x=0;x<cardFlipper.getChildCount();x++)
				{
					ImageCard cad = (ImageCard)cardFlipper.getChildAt(x);
					cad.quesView.setVisibility(View.VISIBLE);
					cad.imgView.setVisibility(View.GONE);
					cardFlipper.invalidate();
				}
			}
		}
		nextCard(anim);
	}

	public Object onRetainNonConfigurationInstance() {
		list = new HashMap<String, Integer[]>();
		list.put("tempImgName", tempImgName);
		list.put("tempImgObject", tempImgObject);
		list.put("tempSoundName", tempSoundName);
		list.put("tempSoundObject",tempSoundObject);

		/*storeVec = new Vector<Object>();
		storeVec.addElement(appinstance);
		storeVec.addElement(appinstance.get().cardPos);
		storeVec.addElement(appinstance.get().leftcardPos);
		storeVec.addElement(appinstance.get().rightcardPos);*/
		appinstance.get().leftcardPos = viewFlipper1.getDisplayedChild();
		appinstance.get().rightcardPos = viewFlipper3.getDisplayedChild();
		
		return list;
	}
	protected void onDestroy(){
		super.onDestroy();
		tempImgObject = null;
		tempImgName = null;//= Integer[5];
		tempImgNameID = null;
		tempImgObjectID = null;
		tempSoundLetterID = null;
		tempSoundName = null;
		tempSoundObject = null;
		tempSoundObjID = null;
		imgLetterName = null;
		imgObjectName = null;
		soundLetter = null;
		soundObject = null;
		animationSyncFlag = true;
		System.gc();
		//MyApp.soundFlag = true;
		//appinstance.get().randomFlag = true;

	}
	private Animation inFromRightAnimation() { 
		Animation anim = new TranslateAnimation( 
				Animation.RELATIVE_TO_PARENT, +1.0f, 
				Animation.RELATIVE_TO_PARENT, 0.0f, 
				Animation.RELATIVE_TO_PARENT, 0.0f, 
				Animation.RELATIVE_TO_PARENT, 0.0f); 
		anim.setDuration(500); 
		anim.setInterpolator(new AccelerateInterpolator()); 
		return anim; 
	} 

	private Animation outToLeftAnimation() { 
		Animation anim = new TranslateAnimation( 
				Animation.RELATIVE_TO_PARENT, 0.0f, 
				Animation.RELATIVE_TO_PARENT, -1.0f, 
				Animation.RELATIVE_TO_PARENT, 0.0f, 
				Animation.RELATIVE_TO_PARENT, 0.0f); 
		anim.setDuration(500); 
		anim.setInterpolator(new AccelerateInterpolator()); 
		return anim; 
	} 

	private void nextCard(int anim) { 
		//ViewFlipper cardFlipper = (ViewFlipper) findViewById(R.id.cardFlipper); 
		//cardFlipper.setOutAnimation(outToLeftAnimation()); 
		//cardFlipper.setInAnimation(inFromRightAnimation()); 
		if(anim==1)
		{
			cardFlipper.setInAnimation(ViewActivity.this, R.anim.view_transition_in_right);
			cardFlipper.setOutAnimation(ViewActivity.this, R.anim.view_transition_out_right);
		}
		else if(anim==2)
		{
			cardFlipper.setInAnimation(ViewActivity.this, R.anim.view_transition_in_left);
			cardFlipper.setOutAnimation(ViewActivity.this, R.anim.view_transition_out_left);
		}
		int currentIndex = cardFlipper.getDisplayedChild(); 
		int nextIndex = (currentIndex ) /*% cardFlipper.getChildCount()*/;
		try {
			handler.postDelayed(new Runnable(){
				public void run() {
					//playMusic(tempSoundName[cardPos]);
					util.playMusic(tempSoundName[appinstance.get().cardPos], ViewActivity.this);
				}

			}, SPLASH_DISPLAY_LENGHT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 	
	public class ImageAdapter extends BaseAdapter{
		private Context context;
		private int iImageCount;

		public ImageAdapter(Context c){
			context = c;

			TypedArray a = obtainStyledAttributes(R.styleable.Gallery1);
			iImageCount = a.getResourceId(R.styleable.Gallery1_android_galleryItemBackground, 0);
			a.recycle();
		}

		public int getCount(){
			return imgLetterName.length;
		}

		public Object getItem(int position){
			return position;
		}	  
		public View getView(int position, View convertView, ViewGroup parent)
		{
			ImageView imgview = new ImageView(context);
			imgview.setImageResource(tempImgName[position]);
			imgview.setScaleType(ImageView.ScaleType.FIT_XY);
			imgview.setBackgroundColor(Color.WHITE);
			imgview.setLayoutParams(new Gallery.LayoutParams(165, 170));
			imgview.setBackgroundResource(iImageCount);
			return imgview;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
	}
	private Vector getRandomNumber() {
		if(random == null){
			random = new Vector();
		}
		for(int i = 0; i < iCardCount;){
			int temp = (int)Math.ceil(Math.random()*iCardCount);
			if(!(random.contains(temp))){
				random.addElement(temp); 
				i++;
			}
		}		return random;
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.soundbutton:
			if(MyApp.soundFlag){
				soundButton.setImageResource(R.drawable.audio_off_main);
				MyApp.soundFlag = false;
			}
			else{
				soundButton.setImageResource(R.drawable.audio_on_main);
				MyApp.soundFlag = true;
			}
			break;		
		case R.id.helpbutton:
			if(util.mPlayer != null){
				util.mPlayer.release();
			}
			if(util != null)
				util.showDialog(this);
			break;
		case R.id.autobutton:
			if(appinstance.get().autoFlag==true)
			{
				animation.setVisibility(View.INVISIBLE);
				relativeLayout3.setVisibility(View.VISIBLE);
				appinstance.get().autoFlag = false;
				autoButton.setImageResource(R.drawable.auto_off);
			}
			else
			{
				animation.setVisibility(View.VISIBLE);
				relativeLayout3.setVisibility(View.INVISIBLE);
				autoButton.setImageResource(R.drawable.auto_on);
				appinstance.get().autoFlag = true;
			}
			if(animationSyncFlag == false){
				animationSyncFlag = true;
			}
			break;
		case R.id.homebutton:
			if(util.mPlayer != null){
				util.mPlayer.release();
			}
			this.finish();
			//startActivity(new Intent(this, MyApp.class));
			break;
		case R.id.backswipe:
			if(viewFlipper1.getVisibility()!=View.INVISIBLE)
			{
				if(animationSyncFlag == true){
					animationSyncFlag = false;
					if(viewFlipper1.getDisplayedChild()!=0 )
					{	
						Log.i("Right Card Pos:", appinstance.get().rightcardPos+"");
						if(appinstance.get().rightcardPos == 0 || appinstance.get().rightcardPos == 25)
						{
							viewFlipper3.setVisibility(View.VISIBLE);
							_nextswipe.setImageResource(R.drawable.next_btn);
						}
						//showPrevious();
						viewFlipper1.setInAnimation(ViewActivity.this, R.anim.view_transition_in_right);
						viewFlipper1.setOutAnimation(ViewActivity.this, R.anim.view_transition_out_right);
						viewFlipper1.showPrevious();

						appinstance.get().cardPos--;
						getCard(appinstance.get().cardPos,1,1);
						cardFlipper.showPrevious();		                
						viewFlipper3.setInAnimation(ViewActivity.this, R.anim.view_transition_in_left);
						viewFlipper3.setOutAnimation(ViewActivity.this, R.anim.view_transition_out_left);
						viewFlipper3.showPrevious();
						image1.setImageResource(tempImgName[appinstance.get().cardPos]);
						image2.setImageResource(tempImgObject[appinstance.get().cardPos]);

						if(animationSyncFlag == false && appinstance.get().autoFlag == false)
						{
							animationSyncFlag = true;
						}
						if(appinstance.get().autoFlag)
						{
							flag = true;
							flag1 = true;
							flag2 = true;
							isFirstImage = true;
							start();
						}
					}
					else
					{
						viewFlipper1.setDisplayedChild(-1);
						viewFlipper1.setVisibility(View.INVISIBLE);
						_backswipe.setImageResource(R.drawable.back_btn_inactive);
						appinstance.get().cardPos--;
						getCard(appinstance.get().cardPos,1,1);
						cardFlipper.showPrevious();		                
						viewFlipper3.setInAnimation(ViewActivity.this, R.anim.view_transition_in_left);
						viewFlipper3.setOutAnimation(ViewActivity.this, R.anim.view_transition_out_left);
						viewFlipper3.showPrevious();
						image1.setImageResource(tempImgName[appinstance.get().cardPos]);
						image2.setImageResource(tempImgObject[appinstance.get().cardPos]);

						if(animationSyncFlag == false && appinstance.get().autoFlag == false)
						{
							animationSyncFlag = true;
						}
						if(appinstance.get().autoFlag)
						{
							flag = true;
							flag1 = true;
							flag2 = true;
							isFirstImage = true;
							start();
						}
					}
				}
			}
			else
			{
				_backswipe.setImageResource(R.drawable.back_btn_inactive);
			}
			break;
		case R.id.nextswipe:
			if(viewFlipper3.getVisibility()!=View.INVISIBLE)
			{
				if( animationSyncFlag == true){
					animationSyncFlag = false;
					if(viewFlipper3.getDisplayedChild()!=25)
					{
						//showNext();
						if(appinstance.get().leftcardPos==-1)
						{
							_backswipe.setImageResource(R.drawable.backswipe);
							viewFlipper1.setVisibility(View.VISIBLE);
						}
						else
						{
							viewFlipper1.setVisibility(View.VISIBLE);
							_backswipe.setImageResource(R.drawable.backswipe);
						}
						Log.i("ViewFlipper1 Child: ",""+viewFlipper1.getDisplayedChild());
						viewFlipper1.setInAnimation(ViewActivity.this, R.anim.view_transition_in_left);
						viewFlipper1.setOutAnimation(ViewActivity.this, R.anim.view_transition_out_left);
						viewFlipper1.showNext();

						appinstance.get().cardPos++;
						getCard(appinstance.get().cardPos,2,1);
						cardFlipper.showNext(); 	                

						viewFlipper3.setInAnimation(ViewActivity.this, R.anim.view_transition_in_left);
						viewFlipper3.setOutAnimation(ViewActivity.this, R.anim.view_transition_out_left);
						viewFlipper3.showNext();
						image1.setImageResource(tempImgName[appinstance.get().cardPos]);
						image2.setImageResource(tempImgObject[appinstance.get().cardPos]);

						if(appinstance.get().autoFlag == false && animationSyncFlag == false){
							animationSyncFlag = true;
						}
						if(appinstance.get().autoFlag )
						{

							flag = true;
							flag1 = true;
							flag2 = true;
							isFirstImage = true;
							start();
						}
					}
					else{
						viewFlipper3.setDisplayedChild(0);
						viewFlipper3.setVisibility(View.INVISIBLE);
						appinstance.get().rightcardPos = 0;
						_nextswipe.setImageResource(R.drawable.next_btn_inactive);
						appinstance.get().cardPos++;
						getCard(appinstance.get().cardPos,1,1);
						cardFlipper.showNext();		                
						viewFlipper1.setInAnimation(ViewActivity.this, R.anim.view_transition_in_left);
						viewFlipper1.setOutAnimation(ViewActivity.this, R.anim.view_transition_out_left);
						viewFlipper1.showNext();
						image1.setImageResource(tempImgName[appinstance.get().cardPos]);
						image2.setImageResource(tempImgObject[appinstance.get().cardPos]);

						if(appinstance.get().autoFlag == false && animationSyncFlag == false){
							animationSyncFlag = true;
						}
						if(appinstance.get().autoFlag)
						{
							flag = true;
							flag1 = true;
							flag2 = true;
							isFirstImage = true;
							start();
						}
					}
				}
			}
			else
			{
				_nextswipe.setImageResource(R.drawable.next_btn_inactive);
			}

			break;
		case R.id.button1:
			if(appinstance.get().randomFlag==true)
			{
				Vector tempRandom = getRandomNumber();

				for(int i =0; i< iCardCount; i++){
					int tempIndx = Integer.parseInt(tempRandom.elementAt(i).toString());
					//System.out.println(" tempInd >>>>>>>"+tempIndx + "img name >>"+imgName[tempIndx -1]);
					tempImgName[i]= tempImgNameID[tempIndx - 1];
					tempImgObject[i]= tempImgObjectID[tempIndx - 1];
					tempSoundName[i]= tempSoundLetterID[tempIndx - 1];
					tempSoundObject[i]= tempSoundObjID[tempIndx - 1];
				}
				gallery.setAdapter(/*new ImageAdapter(this)*/appinstance.get().adapterInst);
				appinstance.get().randomFlag = false;
				appinstance.get().leftcardPos = -1;
				appinstance.get().cardPos = 0;
				appinstance.get().rightcardPos = 1;
				if(util.mPlayer != null){
					util.mPlayer.release();
				}
				cardFlipper.removeAllViews();
				getCard(appinstance.get().cardPos,2,1);
				viewFlipper1.removeAllViews();
				viewFlipper3.removeAllViews();
				image1.setImageResource(tempImgName[appinstance.get().cardPos]);
				image2.setImageResource(tempImgObject[appinstance.get().cardPos]);
				if(animationSyncFlag == false){
					animationSyncFlag = true;
				}
				SwipeView();
			}
			break;
		}
	}
	public void start()
	{
		if (isFirstImage) 
		{       
			applyRotation(0, 90);
			isFirstImage = !isFirstImage;
		} else {    
			applyRotation(0, 90);
			isFirstImage = !isFirstImage;
		}
	}
	private void applyRotation(float start, float end) {
		// Find the center of image
		final float centerX = image1.getWidth() / 2.0f;
		final float centerY = image1.getHeight() / 2.0f;
		// Create a new 3D rotation with the supplied parameter
		// The animation listener is used to trigger the next animation
		final Flip3dAnimation rotation = new Flip3dAnimation(start, end, centerX, centerY);
		rotation.setDuration(1000);
		rotation.setFillAfter(true);
		rotation.setInterpolator(new AccelerateInterpolator());
		rotation.setAnimationListener(new DisplayNextView(isFirstImage, image1, image2,this));

		if (isFirstImage)
		{
			image1.startAnimation(rotation);
			//image2.startAnimation(rotation);
		} else {
			image2.startAnimation(rotation);
			//image1.startAnimation(rotation);
		}		
	}

}

package com.aptara.flashcard.dragdrop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

public class DragAndDropView extends GridView {
    
    private DragAndDropListener mListener;
    private WindowManager mWm;
    private WindowManager.LayoutParams mWindowParams;
    private ImageView mDragView = null;
    private Bitmap mDragBitmap = null;
    private int mFromPosition = AdapterView.INVALID_POSITION;
    int mDragPointOffset;
    int mDragXPointOffset;
    boolean dragmode;
    public DragAndDropView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

        mWm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
       
        mWindowParams = new WindowManager.LayoutParams();
        mWindowParams.gravity = Gravity.TOP | Gravity.LEFT;
        
        mWindowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mWindowParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mWindowParams.flags =
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE |
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN|
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
        mWindowParams.format = PixelFormat.TRANSLUCENT;
        mWindowParams.windowAnimations = 0;
        mWindowParams.x = 0;
        mWindowParams.y = 0;
        
    }

    public DragAndDropView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setOnDragnDropListener(DragAndDropListener listener) {
        mListener = listener;
    }
   
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int x = (int) event.getX();
        final int y = (int) event.getY();

        int action = event.getAction();
        int startpos = pointToPosition(x, y);
        if(startpos!=INVALID_POSITION)
        {
        	View v1 = getChildByPosition(startpos);
          	//if(action == MotionEvent.ACTION_DOWN && x>v1.getLeft()+55 && x<this.getWidth()-v1.getWidth()+v1.getRight() ) //working
        	int orientation = v1.getResources().getConfiguration().orientation;
        	if(orientation==1)
        	{
        		/*if(action == MotionEvent.ACTION_DOWN && x>v1.getLeft()+104 && x<v1.getRight()-100 )
        		{
           			dragmode=true;
        		}*/
        		if(action == MotionEvent.ACTION_DOWN && x>v1.getLeft()+30 && x<v1.getRight()-50 )
        		{
           			dragmode=true;
        		}
        	}
        	if(orientation==2)
        	{
        		if(action == MotionEvent.ACTION_DOWN && x>v1.getLeft()+55 && x<v1.getRight()-50 )
        		{
           			dragmode=true;
        		}
        	}
        }
        if(!dragmode)
        	 	 return super.onTouchEvent(event);
        if(action == MotionEvent.ACTION_DOWN) 
        {
             	mFromPosition = pointToPosition(x, y);
             	if (mFromPosition == AdapterView.INVALID_POSITION)
                return false;
            mDragPointOffset = y - getChildAt(mFromPosition).getTop();
            mDragPointOffset -= ((int)event.getRawY()) - y;
            mDragXPointOffset = x - getChildAt(mFromPosition).getLeft();
            mDragXPointOffset -= ((int)event.getRawX())-x;
            startDrag();
            updateLayout(x, y);
            return true;
         
        } else if (action == MotionEvent.ACTION_MOVE) {
            updateLayout(x, y);
            return true;

        } else if (action == MotionEvent.ACTION_UP) {
            if (mListener != null) {
                mListener.dropped(mFromPosition, (int)event.getRawX(), (int)event.getRawY());   // Callback
            }
            endDrag();
            return true;
         
        } else if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_OUTSIDE) {
            endDrag();
            return true;
        }

        return super.onTouchEvent(event);
    }
   
    private void updateLayout(int x, int y) {
        mWindowParams.x = x-mDragXPointOffset;//getLeft() - getPaddingLeft() + x-80;
        mWindowParams.y = y-mDragPointOffset;//getTop() - getPaddingTop() + y+70;
        //Log.i("update Layout:",""+mWindowParams.x+" "+mWindowParams.y);
        mWm.updateViewLayout(mDragView, mWindowParams);
    }

    private void startDrag() {
        View view = getChildByPosition(mFromPosition);
        mDragBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.setBitmap(mDragBitmap);
        view.draw(canvas);

        if (mDragView != null) {
            mWm.removeView(mDragView);
        }

        mDragView = new ImageView(getContext());
        mDragView.setImageBitmap(mDragBitmap);
       // mDragView.setBackgroundColor(Color.GRAY);
        
        mWm.addView(mDragView, mWindowParams);
    }

    private void endDrag() {
        mWm.removeView(mDragView);
        mDragView = null;
        mDragBitmap = null;
        dragmode = false;
    }

    private View getChildByPosition(int position) {
        return getChildAt(position - getFirstVisiblePosition());
    }
}
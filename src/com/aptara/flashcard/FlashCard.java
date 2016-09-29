package com.aptara.flashcard;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;


class Flashcard extends LinearLayout implements OnClickListener { 
    // public Flashcard(Context context, OnClickListener buttonListener, String question, Bitmap bm /*String answer*/) {
	 public Flashcard(Context context, String question, String answer, int type) {
             super(context); 
             LayoutInflater.from(context).inflate(R.layout.flashcard, this, true); 
             setOnClickListener(this); 
              
             TextView answerText = (TextView)findViewById(R.id.questionText);
              TextView questionText = (TextView)findViewById(R.id.text); 
              
             questionText.setText(question); 
             findViewById(R.id.questionText).setVisibility(View.GONE);
             answerText.setText(answer); 
                      

             showQuestion(); 
     } 

     public void onClick(View v) { 
     	System.out.println( " in flash card click >?>>>>>>");
             TextView questionText = (TextView)findViewById(R.id.text); 
             View answerView = findViewById(R.id.answerView); 
             int cx = this.getWidth() ; 
             int cy = this.getHeight() ; 
             FlipActivity animation = new FlipActivity(questionText, 
                             answerView, cx, cy); 
             if (findViewById(R.id.text).getVisibility() == View.GONE) { 
                     animation.reverse(); 
             } 
             startAnimation(animation); 
           //  Log.v(LOG_TAG, "Qustion: " + questionText.getText()); 
     } 
      
     public void showQuestion() { 
             findViewById(R.id.text).setVisibility(VISIBLE); 
             findViewById(R.id.answerView).setVisibility(GONE);                       
     } 
      
     public void showAnswer() { 
             findViewById(R.id.text).setVisibility(GONE); 
             findViewById(R.id.answerView).setVisibility(VISIBLE);                    
     } 
} 

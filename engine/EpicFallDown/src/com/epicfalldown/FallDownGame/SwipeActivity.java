package com.epicfalldown.FallDownGame;


import com.epicfalldown.FallDownGame.SwipeGestureFilter.SimpleGestureListener;
import com.epicfalldown.objects.Ball;
import com.example.epicfalldown.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

public class SwipeActivity extends Activity implements SimpleGestureListener{
	private SwipeGestureFilter detector;
    
    @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);
      
            // Detect touched area 
            detector = new SwipeGestureFilter(this,this);
    }
      
@Override
public boolean dispatchTouchEvent(MotionEvent me){
    // Call onTouchEvent of SimpleGestureFilter class
     this.detector.onTouchEvent(me);
   return super.dispatchTouchEvent(me);
}
@Override
 public void onSwipe(int direction) {
  
  
  switch (direction) {
  
  case SwipeGestureFilter.SWIPE_RIGHT : 
	  Toast.makeText(this, "Right", Toast.LENGTH_SHORT).show();
	  //Ball. = Ball.getPositionX() +1 ;
	  break;
  case SwipeGestureFilter.SWIPE_LEFT :
	  Toast.makeText(this, "Left", Toast.LENGTH_SHORT).show();
	  //Ball.getPositionX() = Ball.getPositionX() -1;
	  break;
  
  }
   
 }
  
 @Override
 public void onDoubleTap() {
    Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
 }
      

}

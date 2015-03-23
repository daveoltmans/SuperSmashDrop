package nl.jellehoffman.swipetest;


import nl.jellehoffman.swipetest.SimpleGestureFilter.SimpleGestureListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

public class Test2 extends Activity implements SimpleGestureListener{
	private SimpleGestureFilter detector;
    
    @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_swipe_test);
      
            // Detect touched area 
            detector = new SimpleGestureFilter(this,this);
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
  
  case SimpleGestureFilter.SWIPE_RIGHT : Ball.getPositionX = Ball.getPositionX +1 ;
                                           break;
  case SimpleGestureFilter.SWIPE_LEFT :  Ball.getPositionX = Ball.getPositionX -1;
                                                 break;
  
  }
   
 }
  
 @Override
 public void onDoubleTap() {
    Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
 }
      

}

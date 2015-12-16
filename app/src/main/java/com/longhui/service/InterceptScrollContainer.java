package com.longhui.service;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/*
 * 
 * һ����ͼ�����ؼ�
 * ��ֹ ���� ontouch�¼����ݸ����ӿؼ�
 * */
public class InterceptScrollContainer extends LinearLayout {

	public InterceptScrollContainer(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public InterceptScrollContainer(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
//	
//	@Override
//	public boolean dispatchTouchEvent(MotionEvent ev) {
//		// TODO Auto-generated method stub
//		//back_pic super.dispatchTouchEvent(ev);
//		Log.i("pdwy","ScrollContainer dispatchTouchEvent");
//		back_pic true;
//	}

	/*@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		//back_pic super.onInterceptTouchEvent(ev);
		Log.i("pdwy","ScrollContainer onInterceptTouchEvent");
		back_pic true;
		
		//back_pic super.onInterceptTouchEvent(ev);
	}*/
	
	private float xDistance, yDistance, xLast, yLast;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
         switch (ev.getAction()) {  
         case MotionEvent.ACTION_DOWN:  
             xDistance = yDistance = 0f;  
             xLast = ev.getX();  
             yLast = ev.getY();  
             break;  
         case MotionEvent.ACTION_MOVE:  
             final float curX = ev.getX();  
             final float curY = ev.getY();  
               
             xDistance += Math.abs(curX - xLast);  
             yDistance += Math.abs(curY - yLast);  
             xLast = curX;  
             yLast = curY;  
               
             if(xDistance > yDistance){  
                 return false;  
             }    
     }  

        return super.onInterceptTouchEvent(ev);
    }
	
//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		// TODO Auto-generated method stub
//		Log.i("pdwy","ScrollContainer onTouchEvent");
//		back_pic true;
//	}
}

package com.longhui.entity;

import java.util.List;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.longhui.service.AsyncImageLoader;
import com.longhui.service.AsyncImageLoader.imageCallback;
import com.longhui.service.ImageAndText;
import com.mobile.appPre.R;

public class ImageAndTextListAdapter extends ArrayAdapter<ImageAndText> {

	private GridView gridView;
	private AsyncImageLoader asyncImageLoader ;
	public ImageAndTextListAdapter(Activity activity, List<ImageAndText> imageAndTexts, GridView gridView1) {  
		    super(activity, 0, imageAndTexts);  
		    this.gridView = gridView1;  
		    asyncImageLoader = new AsyncImageLoader();  
		    
	}  

	
    public View getView(int position, View convertView, ViewGroup parent) {  
            Activity activity = (Activity) getContext();  
  
            // Inflate the views from XML   
            View rowView = convertView;  
            ViewCache viewCache;  
            if (rowView == null) {  
               LayoutInflater inflater = activity.getLayoutInflater();  
               rowView = inflater.inflate(R.layout.night_item_pig, null);  
               viewCache = new ViewCache(rowView);  
               rowView.setTag(viewCache);  
           } else {  
                viewCache = (ViewCache) rowView.getTag();  
            }  
            ImageAndText imageAndText = getItem(position);  
 
           // Load the image and set it on the ImageView   
            String urls = imageAndText.getUrls();  
            ImageView imageView = viewCache.getImageView();  
           imageView.setTag(urls);  
           Drawable cachedImage = asyncImageLoader.loadDrawable(urls, new imageCallback(){
        	   public void imageLoaded(Drawable imageDrawable, String urls){
        		   ImageView imageViewByTag = (ImageView) gridView.findViewWithTag(urls);
        		   if (imageViewByTag!=null) {
					imageViewByTag.setImageDrawable(imageDrawable);
				}
        	   }
           });
           
           
            if (cachedImage == null) {  
               imageView.setImageResource(R.drawable.icon);
               Log.e("Adapter", "null");
            }else{  
                imageView.setImageDrawable(cachedImage);  
                
            }  
            // Set the text on the TextView   
            TextView textView = viewCache.getTextView();  
            textView.setText(imageAndText.getTime()); 
            
            
            return rowView;  
        }


   

	
  
	  	
}

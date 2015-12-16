package com.mobile.publiclass;

import java.util.List;

import com.mobile.appPre.R;
import com.mobile.publiclass.ViewHolder;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

public class ImageAdapter extends BaseAdapter {

	int mGalleryItemBackground;
	private Context mContext;
	private List<String> mImageIds;
	private ImageView[] mImages;
	private LayoutInflater mInflater;
	public ImageAdapter(Context c, List<String> ImageIds) {
		mContext = c;
		mImageIds = ImageIds;
		mImages = new ImageView[mImageIds.size()];
		mInflater=LayoutInflater.from(mContext);
	}

	public boolean createReflectedImages() {
		final int reflectionGap = 4;
		int index = 0;

		for (String imageId : mImageIds) {
			
//			Bitmap originalImage = BitmapFactory.decodeResource(mContext.getResources(), imageId);
			Bitmap bitmap = getBitmap(imageId);
			Bitmap originalImage = extractMiniThumb(bitmap,400,600,true);		
			int width = originalImage.getWidth();
			int height = originalImage.getHeight();
			
			Matrix matrix = new Matrix();
			matrix.preScale(1, -1);

			Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0,height / 2, width, height / 2, matrix, false);

			Bitmap bitmapWithReflection = Bitmap.createBitmap(width,(height + height / 2), Config.ARGB_8888);
//*
			Canvas canvas = new Canvas(bitmapWithReflection);

			canvas.drawBitmap(originalImage, 0, 0, null);

			Paint deafaultPaint = new Paint();
			canvas.drawRect(0, height, width, height + reflectionGap,
					deafaultPaint);

			canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);
//*/
			Paint paint = new Paint();
			LinearGradient shader = new LinearGradient(0, originalImage
					.getHeight(), 0, bitmapWithReflection.getHeight()
					+ reflectionGap, 0x70ffffff, 0x00ffffff, TileMode.CLAMP);
			paint.setShader(shader);

			paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));

			canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()+ reflectionGap, paint);

			ImageView imageView = new ImageView(mContext);
			imageView.setImageBitmap(bitmapWithReflection);
			imageView.setLayoutParams(new GalleryFlow.LayoutParams(350, 800));
//			imageView.setScaleType(ScaleType.MATRIX);
			mImages[index++] = imageView;
		}
		return true;
	}

	private Resources getResources() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getCount() {
		return mImageIds.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.image_item, null);
		
			holder.Name = (TextView) convertView.findViewById(R.id.ItemName);
			holder.Time = (TextView) convertView.findViewById(R.id.time);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.Name.setText("名称为："+position);
		holder.Time.setText(" 时间为："+position);
		return mImages[position];
	}

	public float getScale(boolean focused, int offset) {
		return Math.max(0, 1.0f / (float) Math.pow(2, Math.abs(offset)));
	}
	
	public static Bitmap getBitmap(String imgBase64Str){
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(imgBase64Str, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

	}
	 public static Bitmap extractMiniThumb(Bitmap source, int width, int height,boolean recycle) {  
         if (source == null) {  
             return null;  
         }  
         float scale;  
         if (source.getWidth() < source.getHeight()) {  
            scale = width / (float) source.getWidth();  
         } else {  
             scale = height / (float) source.getHeight();  
         }  
         Matrix matrix = new Matrix();  
         matrix.setScale(scale, scale);  
         Bitmap miniThumbnail = transform(matrix, source, width, height, true,  
                 recycle);  
         return miniThumbnail;  
	 }
	 public static Bitmap transform(Matrix scaler, Bitmap source,  
             	int targetWidth, int targetHeight, boolean scaleUp, boolean recycle) {  
				int deltaX = source.getWidth() - targetWidth;  
				int deltaY = source.getHeight() - targetHeight;  
				if (!scaleUp && (deltaX < 0 || deltaY < 0)) {  
				 /* 
				  * In this case the bitmap is smaller, at least in one dimension, 
				  * than the target. Transform it by placing as much of the image as 
				  * possible into the target and leaving the top/bottom or left/right 
				  * (or both) black. 
				  */  
				 Bitmap b2 = Bitmap.createBitmap(targetWidth, targetHeight,  
				         Bitmap.Config.ARGB_8888);  
				 Canvas c = new Canvas(b2);  
				 int deltaXHalf = Math.max(0, deltaX / 2);  
				 int deltaYHalf = Math.max(0, deltaY / 2);  
				 Rect src = new Rect(deltaXHalf, deltaYHalf, deltaXHalf  
				         + Math.min(targetWidth, source.getWidth()), deltaYHalf  
				         + Math.min(targetHeight, source.getHeight()));  
				 int dstX = (targetWidth - src.width()) / 2;  
				 int dstY = (targetHeight - src.height()) / 2;  
				 Rect dst = new Rect(dstX, dstY, targetWidth - dstX, targetHeight  
				         - dstY);  
				 c.drawBitmap(source, src, dst, null);  
				if (recycle) {  
				     source.recycle();  
				 }  
				 return b2;  
				}  
				float bitmapWidthF = source.getWidth();  
				float bitmapHeightF = source.getHeight();  
				float bitmapAspect = bitmapWidthF / bitmapHeightF;  
				float viewAspect = (float) targetWidth / targetHeight;  
				if (bitmapAspect > viewAspect) {  
				 float scale = targetHeight / bitmapHeightF;  
				 if (scale < .9F || scale > 1F) {  
				     scaler.setScale(scale, scale);  
				 } else {  
				     scaler = null;  
				 }  
				} else {  
				 float scale = targetWidth / bitmapWidthF;  
				 if (scale < .9F || scale > 1F) {  
				     scaler.setScale(scale, scale);  
				 } else {  
				     scaler = null;  
				 }  
				}  
				Bitmap b1;  
				if (scaler != null) {  
				 // this is used for minithumb and crop, so we want to filter here.   
				 b1 = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source  
				         .getHeight(), scaler, true);  
				} else {  
				 b1 = source;  
				}  
				if (recycle && b1 != source) {  
				 source.recycle();  
				}  
				int dx1 = Math.max(0, b1.getWidth() - targetWidth);  
				int dy1 = Math.max(0, b1.getHeight() - targetHeight);  
				Bitmap b2 = Bitmap.createBitmap(b1, dx1 / 2, dy1 / 2, targetWidth,  
				     targetHeight);  
				if (b2 != b1) {  
				 if (recycle || b1 != source) {  
				     b1.recycle();  
				 }  
				}  
				return b2;  
	}  

}

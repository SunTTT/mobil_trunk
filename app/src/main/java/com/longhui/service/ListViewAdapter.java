package com.longhui.service;

import java.util.ArrayList;
import java.util.List;
import com.longhui.service.MyHScrollView.OnScrollChangedListener;
import com.mobile.appPre.R;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter{
	public List<ViewHolder> mHolderList = new ArrayList<ViewHolder>();

	int id_row_layout;
	LayoutInflater mInflater;
	RelativeLayout mHead;
	Context context;
	private  ArrayList<TableRow> table=new  ArrayList<TableRow>();
	private TextView[] list=new TextView[]{};
	private int[] colors = new int[] {  0x300000FF,0x30FF0000 };
	int size;
	
	//FlingListeber listener;
	//public static GestureDetector detector;


	public ListViewAdapter(Context context, int id_row_layout,ArrayList<TableRow> table,TextView[] list,RelativeLayout mHead,int size) {
		super();
		this.id_row_layout = id_row_layout;
		this.context=context;
		mInflater = LayoutInflater.from(context);
		this .table = table;
		this.list=list;
		this.mHead =mHead;
		this.size=size;
		//detector = new GestureDetector(listener);

		Log.i("Lists", "list="+list);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return  table.size(); 
	}

	@Override
	public  TableRow getItem( int position) {  
        return  table.get(position);  
    }  

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}	
	
	int num=0;
	@Override
	public View getView(int position, View convertView, ViewGroup parentView) {
		ViewHolder holder = null;
		if (convertView == null) {
			synchronized (context) {				
				convertView = mInflater.inflate(id_row_layout, null);
				holder = new ViewHolder();

				MyHScrollView scrollView1 = (MyHScrollView) convertView
						.findViewById(R.id.horizontalScrollView1);

				holder.scrollView = scrollView1;
				holder.txt1 = (TextView) convertView.findViewById(R.id.textView1);
				for(int i=1;i<size+1;i++){
					TextView t=(TextView)convertView.findViewWithTag("textView"+i+1);
					//list[i];
					Log.i("tag","tag="+ list[i]);
					//holder.txt2.add((TextView)convertView.findViewWithTag("textView"+i+1));
				}
				/*for(int i=0;i<size;i++){
				
					holder.txt2.add(list.get(i));
					Log.i("gg", "gg="+holder.txt2);
				}	*/	
				holder.txt2=list;
				//holder.list1=(ListView)convertView.findViewById(R.id.listviewHead);
				MyHScrollView headSrcrollView = (MyHScrollView) mHead
						.findViewById(R.id.horizontalScrollView1);
				headSrcrollView
						.AddOnScrollChangedListener(new OnScrollChangedListenerImp(
								scrollView1));

				convertView.setTag(holder);
				mHolderList.add(holder);
			}
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		TableRow tableRow = table.get(position);  
		holder.txt1.setText(tableRow.cell[0].value.toString());
		for(int i=0;i<size-1;i++){
			Log.i("text", "Textlist="+holder.txt2[i]);
			holder.txt2[i].setText(tableRow.cell[i+1].value.toString());
		}
		
		 //tableRow.cell[0].toString();
		//listener.setItem(table);
		
        //int colorPos = position % colors.length; 
		//convertView= new TableRowView( this .context, tableRow,colorPos); 
		return convertView;
	}
	
	class OnScrollChangedListenerImp implements OnScrollChangedListener {
		MyHScrollView mScrollViewArg;

		public OnScrollChangedListenerImp(MyHScrollView scrollViewar) {
			mScrollViewArg = scrollViewar;
		}

		@Override
		public void onScrollChanged(int l, int t, int oldl, int oldt) {
			mScrollViewArg.smoothScrollTo(l, t);
		}
	};
	
	class ViewHolder {
		TextView txt1;
		TextView[] txt2;		
		ListView list1;
		HorizontalScrollView scrollView;
	}
	
	public class FlingListeber implements GestureDetector.OnGestureListener{

		ArrayList<TableRow> item;
		ViewHolder holder;
        
        public ArrayList<TableRow> getItem() {
            return item;
        }

        public void setItem(ArrayList<TableRow> item) {
            this.item = item;
        }
        
        public ViewHolder getHolder() {
            return holder;
        }

        public void setHolder(ViewHolder holder) {
            this.holder = holder;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                float velocityY) {
            // TODO Auto-generated method stub
            if(e2.getX()-e1.getX()>20){
                //Toast.makeText(ctx, "左滑"+item.areaName, 3000).show();
                
            }else if(e1.getX()-e2.getX()>20){
                //Toast.makeText(ctx, "右滑"+item.areaName, 3000).show();
            }
            
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                float distanceX, float distanceY) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            // TODO Auto-generated method stub
            //Toast.makeText(ctx, "点击item", 3000).show();
            return false;
        }
        
    }

	
	/**  
     * TableRowView 实现表格行的样式  
     * @author hellogv  
     */   
    class  TableRowView  extends  LinearLayout {  
        public  TableRowView(Context context, TableRow tableRow,int colorPos) {  
            super (context);  
              
            this.setOrientation(LinearLayout.HORIZONTAL);
            
            for  ( int  i =  0 ; i < tableRow.getSize(); i++) { //逐个格单元添加到行   
            	
                TableCell tableCell = tableRow.getCellValue(i);  
                LinearLayout.LayoutParams layoutParams = new  LinearLayout.LayoutParams(  
                        tableCell.width, tableCell.height);//按照格单元指定的大小设置空间   
                layoutParams.setMargins(0 ,  0 ,  1 ,  1 ); //预留空隙制造边框   
                
                if  (tableCell.type == TableCell.STRING) { //如果格单元是文本内容   
                    TextView textCell = new  TextView(context);
                    textCell.setTextColor(Color.BLACK);
                    textCell.setLines(1);  
                    
                    textCell.setGravity(Gravity.CENTER);  
                    
                    if(colorPos==1){  
      			      textCell.setBackgroundResource(R.drawable.odd_row_shape);      		        	
      		      	}else{  
      		      		 textCell.setBackgroundResource(R.drawable.even_row_shape);
      		      		
      		      	} 
           //         textCell.setBackgroundColor(Color.BLACK);//背景黑色   
                    textCell.setTextSize(14);
                    textCell.setText(String.valueOf(tableCell.value));  
                    textCell.setHeight(60);
                    addView(textCell, layoutParams);  
                } else   if  (tableCell.type == TableCell.IMAGE) { //如果格单元是图像内容   
                    ImageView imgCell = new  ImageView(context);
                    if(colorPos==1){  
                    	imgCell.setBackgroundResource(R.drawable.odd_row_shape);      		        	
        		    }else{  
        		    	imgCell.setBackgroundResource(R.drawable.even_row_shape);
        		      		
        		     } 
        //          imgCell.setBackgroundColor(Color.BLACK);//背景黑色   
                    imgCell.setImageResource((Integer) tableCell.value);  
                    addView(imgCell, layoutParams);  
                }  
            }  
            this .setBackgroundColor(Color.GRAY); //背景白色，利用空隙来实现边框   
        }  
    }  
    /**  
     * TableRow 实现表格的行  
     * @author hellogv  
     */   
    static   public   class  TableRow {  
        private  TableCell[] cell;  
        public  TableRow(TableCell[] cell) {  
            this .cell = cell;  
        }  
        public   int  getSize() {  
            return  cell.length;  
        }  
        public  TableCell getCellValue( int  index) {  
            if  (index >= cell.length)  
                return   null ;  
            return  cell[index];  
        }  
    }  
    /**  
     * TableCell 实现表格的格单元  
     * @author hellogv  
     */   
    static   public   class  TableCell {  
        static   public   final   int  STRING =  0 ;  
        static   public   final   int  IMAGE =  1 ;  
        public  Object value;  
        public   int  width;  
        public   int  height;  
        private   int  type;  
        public  TableCell(Object value,  int  width,  int  height,  int  type) {  
            this .value = value;  
            this .width = width;  
            this .height = height;  
            this .type = type;  
            
        }  
    }  
}

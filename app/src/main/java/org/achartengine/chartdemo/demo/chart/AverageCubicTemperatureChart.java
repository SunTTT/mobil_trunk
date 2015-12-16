/**
 * Copyright (C) 2009, 2010 SC 4ViewSoft SRL
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.achartengine.chartdemo.demo.chart;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.longhui.entity.Monitor;
import com.longhui.entity.MonitorList;
import com.mobile.appPre.AbstractDemoChart;
import com.mobile.appPre.ChartDemo;
import com.mobile.appPre.R;
import com.mobile.appPre.SiteTabHost;
/**
 * Average temperature demo chart.
 */
public class AverageCubicTemperatureChart extends AbstractDemoChart {
	
	
	
	static List<MonitorList> monitorlist = null;
	
  /**
   * Returns the chart name.
   * 
   * @return the chart name
   */
	
	
  public String getName() {
    return "";
  }

  /**
   * Returns the chart description.
   * 
   * @return the chart description
   */
  public String getDesc() {
    return "The average temperature in 4 Greek islands (cubic line chart)";
  }

  /**
   * Executes the chart demo.
   * 
   * @param context the context
   * @return the built intent
   */
  public GraphicalView  execute(FragmentActivity context, Monitor monitor, String key1) {
	  GraphicalView  view = null ;
	  Intent intent = null ;
	  MonitorList mon = null;
	  List<double[]> x = new ArrayList<double[]>();//点集的x坐标
	 
	  ArrayList arraylist = new ArrayList();
	  monitorlist = monitor.getDetails();
	  mon = monitorlist.get(0); 
	
	  double maxdata = 0;
	  double mindata =0;
	  
	  double[] data = new double[48];
	  String[] detaildata = new String[48];
	
	  //定义曲线的形状
	  PointStyle[] chartstyles = new PointStyle[] { PointStyle.CIRCLE, PointStyle.DIAMOND,
		        PointStyle.TRIANGLE, PointStyle.SQUARE,PointStyle.CIRCLE,PointStyle.DIAMOND, 
		        PointStyle.TRIANGLE,PointStyle.SQUARE,PointStyle.CIRCLE,PointStyle.DIAMOND};
	  //定义曲线的颜色
	  int[] chartcolors = new int[] { Color.BLUE, Color.GREEN,Color.RED,Color.YELLOW,Color.GREEN,Color.CYAN,Color.BLUE, Color.GREEN,Color.RED,Color.YELLOW,Color.CYAN};
	  
	  String[] airTemprature = mon.getAirTemprature();//空气温度	[℃]
	  String[] soilTemprature = mon.getSoilTemprature();//土壤温度	[℃]
	  String[] airHumidity = mon.getAirHumidity();//空气湿度	[%HR]
	  String[] soilHumidity = mon.getSoilHumidity();//土壤湿度	[%HR]
	  String[] radiation = mon.getRadiation();	//总辐射	[wm-2]
	  String[] CO2 = mon.getCO2();	//CO2浓度	[ppm]
	  String[] pressure = mon.getPressure();	//相对气压	[Pa]
	  String[] SoilHumiditys = mon.getSoilHumiditys();	//土壤水分	[%HR]
	  String[] PAR = mon.getPAR();		//光量子	[μmols-1m-2] 
	  String[] CAnemometer = mon.getCAnemometer();	//风速	[ms-1]	
	  String[] dogvane = mon.getDogvane();		//风向	[度]
	  String[] rainfall = mon.getRainfall();	//降雨量	[mm]
	  String[] fengshipc = mon.getFengshipc();//风蚀pc	[次]
	  String[] AAnemometer = mon.getAAnemometer();	//平均风速	[ms-1]
	  String[] Manemometer = mon.getMAnemometer();	//最大风速	[ms-1]
	  String[] fengshiKE = mon.getFengshiKE();	//风蚀KE	[J]
	  String[] jingfushe = mon.getJingfushe();	//净辐射	[wm-2]
	  String[] guangliangdu = mon.getGuangliangdu();	//光亮度	[klux]
	  String[] oilPH = mon.getOilpH();	//土壤pH	[未定义T]
	  String[] SoilHeatFlux = mon.getSoilHeatFlux();	//土壤热通量	[w/m-2]
	  
	  if (key1=="AirTemprature") {
		  String[] titles = new String[airTemprature.length];
		  for (int i = 0; i < airTemprature.length; i++) {
			  titles[i] = "空气温度"+(i+1);
		  }
		 // String[] titles = new String[] { "空气温度"};//每条曲线的名称
		  int count = 0;
		 
	      for (int i = 0; i < monitorlist.size(); i++) {
				mon = monitorlist.get(i); 
				
				
				//monitorlist.remove(i);
				if (monitorlist.get(0).getTime().substring(0, 10).equals(monitorlist.get(i).getTime().substring(0, 10))) {
					int h = Integer.valueOf(mon.getTime().substring(11, 13));//小时
					int m = Integer.valueOf(mon.getTime().substring(14, 15));//分钟：0
					
					 Calendar c = Calendar.getInstance(Locale.CHINA);
					 
					  /*int mDay = c.get(Calendar.DAY_OF_MONTH);//获取当前月份的日期号码 
					  int d = Integer.valueOf(mon.getTime().substring(8, 10));*/
				
					if(1 == h && m == 0){
						data[count]=1;
						
						arraylist.add(mon.getAirTemprature());
						
						count++;
					}else if(2 == h && m == 0){
						data[count]=2;
						arraylist.add(mon.getAirTemprature());
						count++;
					}else if(3 == h && m == 0){
						data[count]=3;
						arraylist.add(mon.getAirTemprature());
						count++;
					}else if(4 == h && m == 0){
						data[count]=4;
						arraylist.add(mon.getAirTemprature());
						count++;
					}else if(5 == h && m == 0){
						data[count]=5;
						arraylist.add(mon.getAirTemprature());
						count++;
					}else if(6 == h && m == 0){
						data[count]=6;
						arraylist.add(mon.getAirTemprature());
						count++;
					}else if(7 == h && m == 0){
						data[count]=7;
						arraylist.add(mon.getAirTemprature());
						count++;
					}else if(8 == h && m == 0){
						data[count]=8;
						arraylist.add(mon.getAirTemprature());
						count++;
					}else if(9 == h && m == 0){
						data[count]=9;
						arraylist.add(mon.getAirTemprature());
						count++;
					}else if(10 == h && m == 0){
						data[count]=10;
						arraylist.add(mon.getAirTemprature());
						count++;
					}else if(11 == h && m == 0){
						data[count]=11;
						arraylist.add(mon.getAirTemprature());
						count++;
					}else if(12 == h && m == 0){
						data[count]=12;
						arraylist.add(mon.getAirTemprature());
						count++;
					}else if(13 == h && m == 0){
						data[count]=13;
						arraylist.add(mon.getAirTemprature());
						count++;
					}else if(14 == h && m == 0){
						data[count]=14;
						arraylist.add(mon.getAirTemprature());
						count++;
					}else if(15 == h && m == 0){
						data[count]=15;
						arraylist.add(mon.getAirTemprature());
						count++;
					}else if(16 == h && m == 0){
						data[count]=16;
						arraylist.add(mon.getAirTemprature());
						count++;
					}else if(17 == h && m == 0){
						data[count]=17;
						arraylist.add(mon.getAirTemprature());
						count++;
					}else if(18 == h && m == 0){
						data[count]=18;
						arraylist.add(mon.getAirTemprature());
						count++;
					}else if(19 == h && m == 0){
						data[count]=19;
						arraylist.add(mon.getAirTemprature());
						count++;
					}else if(20 == h && m == 0){
						data[count]=20;
						arraylist.add(mon.getAirTemprature());
						count++;
					}else if(21 == h && m == 0){
						data[count]=21;
						arraylist.add(mon.getAirTemprature());
						count++;
					}else if(22 == h && m == 0){
						data[count]=22;
						arraylist.add(mon.getAirTemprature());
						count++;
					}else if(23 == h && m == 0){
						data[count]=23;
						arraylist.add(mon.getAirTemprature());
						count++;
					}else if(0 == h && m == 0){
						data[count]=0;
						arraylist.add(mon.getAirTemprature());
						
						count++;
						
					}
				}
				
			
			
				
				
	      }	
	      
	      List<double[]> values = new ArrayList<double[]>();//点集的y坐标
	      for (int i = 0; i < titles.length; i++) {
	    	  x.add(data);
	    	  double[] detaildatadata = new double[arraylist.size()];
	    	  for (int j = 0; j < arraylist.size(); j++) {
	    		  detaildata = (String[]) arraylist.get(j);
	    		  detaildatadata[j] = Double.parseDouble(detaildata[i]);
	    		 
	    	  }
	    	  values.add(detaildatadata);
	    	  
	    	  /*maxdata = detaildatadata[i];
	    	  mindata = detaildatadata[i];*/
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]>maxdata){

	    			  maxdata=detaildatadata[j];

	    		  }
              }
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]<mindata){

	    			  mindata=detaildatadata[j];

	    		  }
              }
	    	  
	    	  
	      }
	   /* for (int i = 0; i < titles.length; i++) {
	    	double[] data = new double[24];
	    	for (int j = 0; j <= 23; j++) {
	    		
	    		data[j] = j;
	      //x.add(new double[] { j });
	      }
	    	x.add(data);
	    }*/
	      
	    //List<double[]> values = new ArrayList<double[]>();//点集的y坐标
	    
	    //values.add(new double[] { 1,2,3,4,5,6,7,8,9});
	    //values.add(new double[] { 1,2,3,4,5,6,7,8,9});
	      
	      
	      
	    //每条曲线点的颜色
	    int[] colors = new int[titles.length];
	    for (int i = 0; i < titles.length; i++) {
	    	colors[i]=chartcolors[i];
	    }
	    //每条曲线点的形状
	    PointStyle[] styles = new PointStyle[titles.length] ;
	    for (int i = 0; i < titles.length; i++) {
	    	styles[i]=chartstyles[i];
	    }
	    
	    
	    
	    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
	    int length = renderer.getSeriesRendererCount();
	    for (int i = 0; i < length; i++) {
	      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
	    }
	    
	    
	    setChartSettings(renderer, "", "时间", "℃", 0.5, 24, -10, 40,
	            Color.BLACK, Color.BLACK);
	    renderer.setMarginsColor(Color.WHITE);
	   // setChartSettings(renderer, "黄淮海麦区平均温度", "月", "温度", 0.5, 12.5, 0, 32,
	    //    Color.LTGRAY, Color.LTGRAY);
	    renderer.setLabelsTextSize(20);    //数轴刻度字体大小
	    renderer.setAxisTitleTextSize(20); 
	    renderer.setXLabels(12);
	    renderer.setYLabels(10);
	    renderer.setShowGrid(true);
	    renderer.setZoomEnabled(false, false);//设置不允许放大缩小. 
	    renderer.setPanEnabled(false, false);//设置是否可以平移

	    //renderer.setBackgroundColor(Color.GREEN);
	   
	    renderer.setXLabelsAlign(Align.RIGHT);
	    renderer.setYLabelsAlign(Align.RIGHT);
	    //renderer.setZoomButtonsVisible(true);
	    //renderer.setPanLimits(new double[] { 0, 25, -10,  40});
	    //renderer.setZoomLimits(new double[] { 0, 25, -10, 40 });
	    
	   // intent = ChartFactory.getCubicLineChartIntent(context, buildDataset(titles, x, values),
	    //    renderer, 0.33f, "空气温度");
	    
	    
	    view = ChartFactory.getCubeLineChartView(context, buildDataset(titles, x, values),
	        renderer, 0.33f);
	    Log.i("AverageCubicTemperatureChart/view", "back_pic view ");
	} else if (key1=="SoilTemprature"){
		String[] titles = new String[soilTemprature.length];
		  for (int i = 0; i < soilTemprature.length; i++) {
			  titles[i] = "土壤温度"+(i+1);
		  }
		
		  int count = 0;
		 
	      for (int i = 0; i < monitorlist.size(); i++) {
				mon = monitorlist.get(i); 
				if (monitorlist.get(0).getTime().substring(0, 10).equals(monitorlist.get(i).getTime().substring(0, 10))) {

					int h = Integer.valueOf(mon.getTime().substring(11, 13));//小时
					int m = Integer.valueOf(mon.getTime().substring(14, 15));//分钟：0
					
					 Calendar c = Calendar.getInstance(Locale.CHINA);
					  /*int mDay = c.get(Calendar.DAY_OF_MONTH);//获取当前月份的日期号码 
					  
					  int d = Integer.valueOf(mon.getTime().substring(8, 10));*/
				
					if(1 == h && m == 0){
						data[count]=1;
						
						arraylist.add(mon.getSoilTemprature());
						
						count++;
					}else if(2 == h && m == 0){
						data[count]=2;
						arraylist.add(mon.getSoilTemprature());
						count++;
					}else if(3 == h && m == 0){
						data[count]=3;
						arraylist.add(mon.getSoilTemprature());
						count++;
					}else if(4 == h && m == 0){
						data[count]=4;
						arraylist.add(mon.getSoilTemprature());
						count++;
					}else if(5 == h && m == 0){
						data[count]=5;
						arraylist.add(mon.getSoilTemprature());
						count++;
					}else if(6 == h && m == 0){
						data[count]=6;
						arraylist.add(mon.getSoilTemprature());
						count++;
					}else if(7 == h && m == 0){
						data[count]=7;
						arraylist.add(mon.getSoilTemprature());
						count++;
					}else if(8 == h && m == 0){
						data[count]=8;
						arraylist.add(mon.getSoilTemprature());
						count++;
					}else if(9 == h && m == 0){
						data[count]=9;
						arraylist.add(mon.getSoilTemprature());
						count++;
					}else if(10 == h && m == 0){
						data[count]=10;
						arraylist.add(mon.getSoilTemprature());
						count++;
					}else if(11 == h && m == 0){
						data[count]=11;
						arraylist.add(mon.getSoilTemprature());
						count++;
					}else if(12 == h && m == 0){
						data[count]=12;
						arraylist.add(mon.getSoilTemprature());
						count++;
					}else if(13 == h && m == 0){
						data[count]=13;
						arraylist.add(mon.getSoilTemprature());
						count++;
					}else if(14 == h && m == 0){
						data[count]=14;
						arraylist.add(mon.getSoilTemprature());
						count++;
					}else if(15 == h && m == 0){
						data[count]=15;
						arraylist.add(mon.getSoilTemprature());
						count++;
					}else if(16 == h && m == 0){
						data[count]=16;
						arraylist.add(mon.getSoilTemprature());
						count++;
					}else if(17 == h && m == 0){
						data[count]=17;
						arraylist.add(mon.getSoilTemprature());
						count++;
					}else if(18 == h && m == 0){
						data[count]=18;
						arraylist.add(mon.getSoilTemprature());
						count++;
					}else if(19 == h && m == 0){
						data[count]=19;
						arraylist.add(mon.getSoilTemprature());
						count++;
					}else if(20 == h && m == 0){
						data[count]=20;
						arraylist.add(mon.getSoilTemprature());
						count++;
					}else if(21 == h && m == 0){
						data[count]=21;
						arraylist.add(mon.getSoilTemprature());
						count++;
					}else if(22 == h && m == 0){
						data[count]=22;
						arraylist.add(mon.getSoilTemprature());
						count++;
					}else if(23 == h && m == 0){
						data[count]=23;
						arraylist.add(mon.getSoilTemprature());
						count++;
					}else if(0 == h && m == 0){
						data[count]=0;
						arraylist.add(mon.getSoilTemprature());
						count++;
					}
				
					
				}
				
				
				
	      }	
	      
	      List<double[]> values = new ArrayList<double[]>();//点集的y坐标
	      for (int i = 0; i < titles.length; i++) {
	    	  x.add(data);
	    	  double[] detaildatadata = new double[arraylist.size()];
	    	  for (int j = 0; j < arraylist.size(); j++) {
	    		  detaildata = (String[]) arraylist.get(j);
	    		  detaildatadata[j] = Double.parseDouble(detaildata[i]);
	    		 
	    	  }
	    	  values.add(detaildatadata);
	    	  
	    	 /* maxdata = detaildatadata[0];
	    	  mindata = detaildatadata[0];*/
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]>maxdata){

	    			  maxdata=detaildatadata[j];

	    		  }
              }
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]<mindata){

	    			  mindata=detaildatadata[j];

	    		  }
              }
	      }
	   
	      
	    //每条曲线点的颜色
		    int[] colors = new int[titles.length];
		    for (int i = 0; i < titles.length; i++) {
		    	colors[i]=chartcolors[i];
		    }
		    //每条曲线点的形状
		    PointStyle[] styles = new PointStyle[titles.length] ;
		    for (int i = 0; i < titles.length; i++) {
		    	styles[i]=chartstyles[i];
		    }
	      
	    
	    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
	    int length = renderer.getSeriesRendererCount();
	    for (int i = 0; i < length; i++) {
	      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
	    }
	    
	    setChartSettings(renderer, "", "时间", "℃", 0, 24,mindata, maxdata+10,
	    		 Color.BLACK, Color.BLACK);
	    renderer.setMarginsColor(Color.WHITE);
	    renderer.setZoomEnabled(false, false);//设置不允许放大缩小. 
	    renderer.setPanEnabled(false, false);//设置是否可以平移
	    renderer.setLabelsTextSize(20);    //数轴刻度字体大小
	    renderer.setAxisTitleTextSize(20); 
	    renderer.setXLabels(12);
	    renderer.setYLabels(10);
	    renderer.setShowGrid(true);
	    renderer.setXLabelsAlign(Align.RIGHT);
	    renderer.setYLabelsAlign(Align.RIGHT);
	    //renderer.setZoomButtonsVisible(true);
	    //renderer.setPanLimits(new double[] { 0, 25, -10,  40});
	    //renderer.setZoomLimits(new double[] { 0, 25, -10, 40 });
	    
	    view = ChartFactory.getCubeLineChartView(context, buildDataset(titles, x, values),
	        renderer, 0.33f);
	    
	    
	}else if(key1=="AirHumidity"){
		String[] titles = new String[airHumidity.length];
		  for (int i = 0; i < airHumidity.length; i++) {
			  titles[i] = "空气湿度"+(i+1);
		  }
		
		  int count = 0;
		 
	      for (int i = 0; i < monitorlist.size(); i++) {
				mon = monitorlist.get(i); 
				
				if (monitorlist.get(0).getTime().substring(0, 10).equals(monitorlist.get(i).getTime().substring(0, 10))) {
					int h = Integer.valueOf(mon.getTime().substring(11, 13));//小时
					int m = Integer.valueOf(mon.getTime().substring(14, 15));//分钟：0
					
					 Calendar c = Calendar.getInstance(Locale.CHINA);
					  /*int mDay = c.get(Calendar.DAY_OF_MONTH);//获取当前月份的日期号码 
					  
					  int d = Integer.valueOf(mon.getTime().substring(8, 10));*/
				//如果当前日期与取得日期相同则显示正半轴上、否则显示到负半轴	  
				
					if(1 == h && m == 0){
						data[count]=1;
						
						arraylist.add(mon.getAirHumidity());
						
						count++;
					}else if(2 == h && m == 0){
						data[count]=2;
						arraylist.add(mon.getAirHumidity());
						count++;
					}else if(3 == h && m == 0){
						data[count]=3;
						arraylist.add(mon.getAirHumidity());
						count++;
					}else if(4 == h && m == 0){
						data[count]=4;
						arraylist.add(mon.getAirHumidity());
						count++;
					}else if(5 == h && m == 0){
						data[count]=5;
						arraylist.add(mon.getAirHumidity());
						count++;
					}else if(6 == h && m == 0){
						data[count]=6;
						arraylist.add(mon.getAirHumidity());
						count++;
					}else if(7 == h && m == 0){
						data[count]=7;
						arraylist.add(mon.getAirHumidity());
						count++;
					}else if(8 == h && m == 0){
						data[count]=8;
						arraylist.add(mon.getAirHumidity());
						count++;
					}else if(9 == h && m == 0){
						data[count]=9;
						arraylist.add(mon.getAirHumidity());
						count++;
					}else if(10 == h && m == 0){
						data[count]=10;
						arraylist.add(mon.getAirHumidity());
						count++;
					}else if(11 == h && m == 0){
						data[count]=11;
						arraylist.add(mon.getAirHumidity());
						count++;
					}else if(12 == h && m == 0){
						data[count]=12;
						arraylist.add(mon.getAirHumidity());
						count++;
					}else if(13 == h && m == 0){
						data[count]=13;
						arraylist.add(mon.getAirHumidity());
						count++;
					}else if(14 == h && m == 0){
						data[count]=14;
						arraylist.add(mon.getAirHumidity());
						count++;
					}else if(15 == h && m == 0){
						data[count]=15;
						arraylist.add(mon.getAirHumidity());
						count++;
					}else if(16 == h && m == 0){
						data[count]=16;
						arraylist.add(mon.getAirHumidity());
						count++;
					}else if(17 == h && m == 0){
						data[count]=17;
						arraylist.add(mon.getAirHumidity());
						count++;
					}else if(18 == h && m == 0){
						data[count]=18;
						arraylist.add(mon.getAirHumidity());
						count++;
					}else if(19 == h && m == 0){
						data[count]=19;
						arraylist.add(mon.getAirHumidity());
						count++;
					}else if(20 == h && m == 0){
						data[count]=20;
						arraylist.add(mon.getAirHumidity());
						count++;
					}else if(21 == h && m == 0){
						data[count]=21;
						arraylist.add(mon.getAirHumidity());
						count++;
					}else if(22 == h && m == 0){
						data[count]=22;
						arraylist.add(mon.getAirHumidity());
						count++;
					}else if(23 == h && m == 0){
						data[count]=23;
						arraylist.add(mon.getAirHumidity());
						count++;
					}else if(0 == h && m == 0){
						data[count]=0;
						arraylist.add(mon.getAirHumidity());
						count++;
					}
				}
				
			
				
	      }	
	      
	      List<double[]> values = new ArrayList<double[]>();//点集的y坐标
	      for (int i = 0; i < titles.length; i++) {
	    	  x.add(data);
	    	  double[] detaildatadata = new double[arraylist.size()];
	    	  for (int j = 0; j < arraylist.size(); j++) {
	    		  detaildata = (String[]) arraylist.get(j);
	    		  detaildatadata[j] = Double.parseDouble(detaildata[i]);
	    		  
	    	  }
	    	  values.add(detaildatadata);
	    	  
	    	  /*maxdata = detaildatadata[0];
	    	  mindata = detaildatadata[0];*/
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]>maxdata){

	    			  maxdata=detaildatadata[j];

	    		  }
              }
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]<mindata){

	    			  mindata=detaildatadata[j];

	    		  }
              }
	      }
	   
	      
	    //每条曲线点的颜色
		    int[] colors = new int[titles.length];
		    for (int i = 0; i < titles.length; i++) {
		    	colors[i]=chartcolors[i];
		    }
		    //每条曲线点的形状
		    PointStyle[] styles = new PointStyle[titles.length] ;
		    for (int i = 0; i < titles.length; i++) {
		    	styles[i]=chartstyles[i];
		    }
	      
	    
	    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
	    int length = renderer.getSeriesRendererCount();
	    for (int i = 0; i < length; i++) {
	      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
	    }
	    
	    setChartSettings(renderer, "", "时间", "%HR", 0, 24, mindata, maxdata+10,
	    		 Color.BLACK, Color.BLACK);
	    renderer.setMarginsColor(Color.WHITE);
	    renderer.setZoomEnabled(false, false);//设置不允许放大缩小. 
	    renderer.setPanEnabled(false, false);//设置是否可以平移
	    renderer.setLabelsTextSize(20);    //数轴刻度字体大小
	    renderer.setAxisTitleTextSize(20); 
	    renderer.setXLabels(12);
	    renderer.setYLabels(10);
	    renderer.setShowGrid(true);
	    renderer.setXLabelsAlign(Align.RIGHT);
	    renderer.setYLabelsAlign(Align.RIGHT);
	    //renderer.setZoomButtonsVisible(true);
	    //renderer.setPanLimits(new double[] { 0, 25, -10,  120});
	    //renderer.setZoomLimits(new double[] { 0, 25, -10, 120 });
	    
	    view = ChartFactory.getCubeLineChartView(context, buildDataset(titles, x, values),
	        renderer, 0.33f);
		
	    
	}else if(key1=="SoilHumidity"){
		String[] titles = new String[soilHumidity.length];
		  for (int i = 0; i < soilHumidity.length; i++) {
			  titles[i] = "土壤湿度"+(i+1);
		  }
		
		  int count = 0;
		 
	      for (int i = 0; i < monitorlist.size(); i++) {
				mon = monitorlist.get(i); 
				
				if (monitorlist.get(0).getTime().substring(0, 10).equals(monitorlist.get(i).getTime().substring(0, 10))) {
					int h = Integer.valueOf(mon.getTime().substring(11, 13));//小时
					int m = Integer.valueOf(mon.getTime().substring(14, 15));//分钟：0
					
					 Calendar c = Calendar.getInstance(Locale.CHINA);
					  
				//如果当前日期与取得日期相同则显示正半轴上、否则显示到负半轴	  
				
					if(1 == h && m == 0){
						data[count]=1;
						
						arraylist.add(mon.getSoilHumidity());
						
						count++;
					}else if(2 == h && m == 0){
						data[count]=2;
						arraylist.add(mon.getSoilHumidity());
						count++;
					}else if(3 == h && m == 0){
						data[count]=3;
						arraylist.add(mon.getSoilHumidity());
						count++;
					}else if(4 == h && m == 0){
						data[count]=4;
						arraylist.add(mon.getSoilHumidity());
						count++;
					}else if(5 == h && m == 0){
						data[count]=5;
						arraylist.add(mon.getSoilHumidity());
						count++;
					}else if(6 == h && m == 0){
						data[count]=6;
						arraylist.add(mon.getSoilHumidity());
						count++;
					}else if(7 == h && m == 0){
						data[count]=7;
						arraylist.add(mon.getSoilHumidity());
						count++;
					}else if(8 == h && m == 0){
						data[count]=8;
						arraylist.add(mon.getSoilHumidity());
						count++;
					}else if(9 == h && m == 0){
						data[count]=9;
						arraylist.add(mon.getSoilHumidity());
						count++;
					}else if(10 == h && m == 0){
						data[count]=10;
						arraylist.add(mon.getSoilHumidity());
						count++;
					}else if(11 == h && m == 0){
						data[count]=11;
						arraylist.add(mon.getSoilHumidity());
						count++;
					}else if(12 == h && m == 0){
						data[count]=12;
						arraylist.add(mon.getSoilHumidity());
						count++;
					}else if(13 == h && m == 0){
						data[count]=13;
						arraylist.add(mon.getSoilHumidity());
						count++;
					}else if(14 == h && m == 0){
						data[count]=14;
						arraylist.add(mon.getSoilHumidity());
						count++;
					}else if(15 == h && m == 0){
						data[count]=15;
						arraylist.add(mon.getSoilHumidity());
						count++;
					}else if(16 == h && m == 0){
						data[count]=16;
						arraylist.add(mon.getSoilHumidity());
						count++;
					}else if(17 == h && m == 0){
						data[count]=17;
						arraylist.add(mon.getSoilHumidity());
						count++;
					}else if(18 == h && m == 0){
						data[count]=18;
						arraylist.add(mon.getSoilHumidity());
						count++;
					}else if(19 == h && m == 0){
						data[count]=19;
						arraylist.add(mon.getSoilHumidity());
						count++;
					}else if(20 == h && m == 0){
						data[count]=20;
						arraylist.add(mon.getSoilHumidity());
						count++;
					}else if(21 == h && m == 0){
						data[count]=21;
						arraylist.add(mon.getSoilHumidity());
						count++;
					}else if(22 == h && m == 0){
						data[count]=22;
						arraylist.add(mon.getSoilHumidity());
						count++;
					}else if(23 == h && m == 0){
						data[count]=23;
						arraylist.add(mon.getSoilHumidity());
						count++;
					}else if(0 == h && m == 0){
						data[count]=0;
						arraylist.add(mon.getSoilHumidity());
						count++;
					}
				
					
				}
				
				
	      }	
	      
	      List<double[]> values = new ArrayList<double[]>();//点集的y坐标
	      for (int i = 0; i < titles.length; i++) {
	    	  x.add(data);
	    	  double[] detaildatadata = new double[arraylist.size()];
	    	  for (int j = 0; j < arraylist.size(); j++) {
	    		  detaildata = (String[]) arraylist.get(j);
	    		  detaildatadata[j] = Double.parseDouble(detaildata[i]);
	    		  
	    	  }
	    	  values.add(detaildatadata);
	    	  
	    	  /*maxdata = detaildatadata[0];
	    	  mindata = detaildatadata[0];*/
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]>maxdata){

	    			  maxdata=detaildatadata[j];

	    		  }
              }
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]<mindata){

	    			  mindata=detaildatadata[j];

	    		  }
              }
	      }
	   
	      
	    //每条曲线点的颜色
		    int[] colors = new int[titles.length];
		    for (int i = 0; i < titles.length; i++) {
		    	colors[i]=chartcolors[i];
		    }
		    //每条曲线点的形状
		    PointStyle[] styles = new PointStyle[titles.length] ;
		    for (int i = 0; i < titles.length; i++) {
		    	styles[i]=chartstyles[i];
		    }
	      
	    
	    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
	    int length = renderer.getSeriesRendererCount();
	    for (int i = 0; i < length; i++) {
	      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
	    }
	    
	    setChartSettings(renderer, "", "时间", "%HR", 0, 24, mindata-10, maxdata+20,
	    		 Color.BLACK, Color.BLACK);
	    renderer.setMarginsColor(Color.WHITE);
	    renderer.setZoomEnabled(false, false);//设置不允许放大缩小. 
	    renderer.setPanEnabled(false, false);//设置是否可以平移
	    renderer.setLabelsTextSize(20);    //数轴刻度字体大小
	    renderer.setAxisTitleTextSize(20); 
	    renderer.setXLabels(12);
	    renderer.setYLabels(10);
	    renderer.setShowGrid(true);
	    renderer.setXLabelsAlign(Align.RIGHT);
	    renderer.setYLabelsAlign(Align.RIGHT);
	   // renderer.setZoomButtonsVisible(true);
	    //renderer.setPanLimits(new double[] { 0, 25, -10,  120});
	    //renderer.setZoomLimits(new double[] { 0, 25, -10, 120 });
	    
	    view = ChartFactory.getCubeLineChartView(context, buildDataset(titles, x, values),
	        renderer, 0.33f);
	}else if(key1=="Radiation"){
		String[] titles = new String[radiation.length];
		  for (int i = 0; i < radiation.length; i++) {
			  titles[i] = "总辐射"+(i+1);
		  }
		
		  int count = 0;
		 
	      for (int i = 0; i < monitorlist.size(); i++) {
				mon = monitorlist.get(i); 
				
				if (monitorlist.get(0).getTime().substring(0, 10).equals(monitorlist.get(i).getTime().substring(0, 10))) {
					int h = Integer.valueOf(mon.getTime().substring(11, 13));//小时
					int m = Integer.valueOf(mon.getTime().substring(14, 15));//分钟：0
					
					 Calendar c = Calendar.getInstance(Locale.CHINA);
					  
				
					if(1 == h && m == 0){
						data[count]=1;
						
						arraylist.add(mon.getRadiation());
						
						count++;
					}else if(2 == h && m == 0){
						data[count]=2;
						arraylist.add(mon.getRadiation());
						count++;
					}else if(3 == h && m == 0){
						data[count]=3;
						arraylist.add(mon.getRadiation());
						count++;
					}else if(4 == h && m == 0){
						data[count]=4;
						arraylist.add(mon.getRadiation());
						count++;
					}else if(5 == h && m == 0){
						data[count]=5;
						arraylist.add(mon.getRadiation());
						count++;
					}else if(6 == h && m == 0){
						data[count]=6;
						arraylist.add(mon.getRadiation());
						count++;
					}else if(7 == h && m == 0){
						data[count]=7;
						arraylist.add(mon.getRadiation());
						count++;
					}else if(8 == h && m == 0){
						data[count]=8;
						arraylist.add(mon.getRadiation());
						count++;
					}else if(9 == h && m == 0){
						data[count]=9;
						arraylist.add(mon.getRadiation());
						count++;
					}else if(10 == h && m == 0){
						data[count]=10;
						arraylist.add(mon.getRadiation());
						count++;
					}else if(11 == h && m == 0){
						data[count]=11;
						arraylist.add(mon.getRadiation());
						count++;
					}else if(12 == h && m == 0){
						data[count]=12;
						arraylist.add(mon.getRadiation());
						count++;
					}else if(13 == h && m == 0){
						data[count]=13;
						arraylist.add(mon.getRadiation());
						count++;
					}else if(14 == h && m == 0){
						data[count]=14;
						arraylist.add(mon.getRadiation());
						count++;
					}else if(15 == h && m == 0){
						data[count]=15;
						arraylist.add(mon.getRadiation());
						count++;
					}else if(16 == h && m == 0){
						data[count]=16;
						arraylist.add(mon.getRadiation());
						count++;
					}else if(17 == h && m == 0){
						data[count]=17;
						arraylist.add(mon.getRadiation());
						count++;
					}else if(18 == h && m == 0){
						data[count]=18;
						arraylist.add(mon.getRadiation());
						count++;
					}else if(19 == h && m == 0){
						data[count]=19;
						arraylist.add(mon.getRadiation());
						count++;
					}else if(20 == h && m == 0){
						data[count]=20;
						arraylist.add(mon.getRadiation());
						count++;
					}else if(21 == h && m == 0){
						data[count]=21;
						arraylist.add(mon.getRadiation());
						count++;
					}else if(22 == h && m == 0){
						data[count]=22;
						arraylist.add(mon.getRadiation());
						count++;
					}else if(23 == h && m == 0){
						data[count]=23;
						arraylist.add(mon.getRadiation());
						count++;
					}else if(0 == h && m == 0){
						data[count]=0;
						arraylist.add(mon.getRadiation());
						count++;
					}
				
				}
				
				
				
	      }	
	      
	      List<double[]> values = new ArrayList<double[]>();//点集的y坐标
	      for (int i = 0; i < titles.length; i++) {
	    	  x.add(data);
	    	  double[] detaildatadata = new double[arraylist.size()];
	    	  for (int j = 0; j < arraylist.size(); j++) {
	    		  detaildata = (String[]) arraylist.get(j);
	    		  detaildatadata[j] = Double.parseDouble(detaildata[i]);
	    		  
	    	  }
	    	  values.add(detaildatadata);
	    	  
	    	  /*maxdata = detaildatadata[0];
	    	  mindata = detaildatadata[0];*/
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]>maxdata){

	    			  maxdata=detaildatadata[j];

	    		  }
              }
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]<mindata){

	    			  mindata=detaildatadata[j];

	    		  }
              }
	      }
	   
	      
	    //每条曲线点的颜色
		    int[] colors = new int[titles.length];
		    for (int i = 0; i < titles.length; i++) {
		    	colors[i]=chartcolors[i];
		    }
		    //每条曲线点的形状
		    PointStyle[] styles = new PointStyle[titles.length] ;
		    for (int i = 0; i < titles.length; i++) {
		    	styles[i]=chartstyles[i];
		    }
	      
	    
	    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
	    int length = renderer.getSeriesRendererCount();
	    for (int i = 0; i < length; i++) {
	      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
	    }
	    
	    setChartSettings(renderer, "", "时间", "wm-2", 0.5, 24, mindata-10, maxdata+20,
	    		 Color.BLACK, Color.BLACK);
	    renderer.setMarginsColor(Color.WHITE);
	    renderer.setZoomEnabled(false, false);//设置不允许放大缩小. 
	    renderer.setPanEnabled(false, false);//设置是否可以平移
	    renderer.setLabelsTextSize(20);    //数轴刻度字体大小
	    renderer.setAxisTitleTextSize(20); 
	    renderer.setXLabels(12);
	    renderer.setYLabels(10);
	    renderer.setShowGrid(true);
	    renderer.setXLabelsAlign(Align.RIGHT);
	    renderer.setYLabelsAlign(Align.RIGHT);
	    //renderer.setZoomButtonsVisible(true);
	    //renderer.setPanLimits(new double[] { 0, 25, -10,  2000});
	    //renderer.setZoomLimits(new double[] { 0, 25, -10, 2000 });
	    
	    view = ChartFactory.getCubeLineChartView(context, buildDataset(titles, x, values),
	        renderer, 0.33f);
	}else if(key1=="CO2"){
		String[] titles = new String[CO2.length];
		  for (int i = 0; i < CO2.length; i++) {
			  titles[i] = "CO2浓度"+(i+1);
		  }		
		  int count = 0;
		 
	      for (int i = 0; i < monitorlist.size(); i++) {
				mon = monitorlist.get(i); 
				
				if (monitorlist.get(0).getTime().substring(0, 10).equals(monitorlist.get(i).getTime().substring(0, 10))) {
					int h = Integer.valueOf(mon.getTime().substring(11, 13));//小时
					int m = Integer.valueOf(mon.getTime().substring(14, 15));//分钟：0
					
					 Calendar c = Calendar.getInstance(Locale.CHINA);
					 /* int mDay = c.get(Calendar.DAY_OF_MONTH);//获取当前月份的日期号码 
					  
					  int d = Integer.valueOf(mon.getTime().substring(8, 10));*/
				
					if(1 == h && m == 0){
						data[count]=1;
						
						arraylist.add(mon.getCO2());
						
						count++;
					}else if(2 == h && m == 0){
						data[count]=2;
						arraylist.add(mon.getCO2());
						count++;
					}else if(3 == h && m == 0){
						data[count]=3;
						arraylist.add(mon.getCO2());
						count++;
					}else if(4 == h && m == 0){
						data[count]=4;
						arraylist.add(mon.getCO2());
						count++;
					}else if(5 == h && m == 0){
						data[count]=5;
						arraylist.add(mon.getCO2());
						count++;
					}else if(6 == h && m == 0){
						data[count]=6;
						arraylist.add(mon.getCO2());
						count++;
					}else if(7 == h && m == 0){
						data[count]=7;
						arraylist.add(mon.getCO2());
						count++;
					}else if(8 == h && m == 0){
						data[count]=8;
						arraylist.add(mon.getCO2());
						count++;
					}else if(9 == h && m == 0){
						data[count]=9;
						arraylist.add(mon.getCO2());
						count++;
					}else if(10 == h && m == 0){
						data[count]=10;
						arraylist.add(mon.getCO2());
						count++;
					}else if(11 == h && m == 0){
						data[count]=11;
						arraylist.add(mon.getCO2());
						count++;
					}else if(12 == h && m == 0){
						data[count]=12;
						arraylist.add(mon.getCO2());
						count++;
					}else if(13 == h && m == 0){
						data[count]=13;
						arraylist.add(mon.getCO2());
						count++;
					}else if(14 == h && m == 0){
						data[count]=14;
						arraylist.add(mon.getCO2());
						count++;
					}else if(15 == h && m == 0){
						data[count]=15;
						arraylist.add(mon.getCO2());
						count++;
					}else if(16 == h && m == 0){
						data[count]=16;
						arraylist.add(mon.getCO2());
						count++;
					}else if(17 == h && m == 0){
						data[count]=17;
						arraylist.add(mon.getCO2());
						count++;
					}else if(18 == h && m == 0){
						data[count]=18;
						arraylist.add(mon.getCO2());
						count++;
					}else if(19 == h && m == 0){
						data[count]=19;
						arraylist.add(mon.getCO2());
						count++;
					}else if(20 == h && m == 0){
						data[count]=20;
						arraylist.add(mon.getCO2());
						count++;
					}else if(21 == h && m == 0){
						data[count]=21;
						arraylist.add(mon.getCO2());
						count++;
					}else if(22 == h && m == 0){
						data[count]=22;
						arraylist.add(mon.getCO2());
						count++;
					}else if(23 == h && m == 0){
						data[count]=23;
						arraylist.add(mon.getCO2());
						count++;
					}else if(0 == h && m == 0){
						data[count]=0;
						arraylist.add(mon.getCO2());
						count++;
					}
				
					
				}
				
				
	      }	
	      
	      List<double[]> values = new ArrayList<double[]>();//点集的y坐标
	      for (int i = 0; i < titles.length; i++) {
	    	  x.add(data);
	    	  double[] detaildatadata = new double[arraylist.size()];
	    	  for (int j = 0; j < arraylist.size(); j++) {
	    		  detaildata = (String[]) arraylist.get(j);
	    		  detaildatadata[j] = Double.parseDouble(detaildata[i]);
	    		  
	    	  }
	    	  values.add(detaildatadata);
	    	  
	    	  /*maxdata = detaildatadata[0];
	    	  mindata = detaildatadata[0];*/
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]>maxdata){

	    			  maxdata=detaildatadata[j];

	    		  }
              }
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]<mindata){

	    			  mindata=detaildatadata[j];

	    		  }
              }
	      }
	   
	      
	    //每条曲线点的颜色
		    int[] colors = new int[titles.length];
		    for (int i = 0; i < titles.length; i++) {
		    	colors[i]=chartcolors[i];
		    }
		    //每条曲线点的形状
		    PointStyle[] styles = new PointStyle[titles.length] ;
		    for (int i = 0; i < titles.length; i++) {
		    	styles[i]=chartstyles[i];
		    }
	      
	    
	    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
	    int length = renderer.getSeriesRendererCount();
	    for (int i = 0; i < length; i++) {
	      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
	    }
	    
	    setChartSettings(renderer, "", "时间", "ppm", 0.5, 24, mindata-10, maxdata+20,
	    		 Color.BLACK, Color.BLACK);
	    renderer.setMarginsColor(Color.WHITE);
	    renderer.setZoomEnabled(false, false);//设置不允许放大缩小. 
	    renderer.setPanEnabled(false, false);//设置是否可以平移
	    renderer.setLabelsTextSize(20);    //数轴刻度字体大小
	    renderer.setAxisTitleTextSize(20); 
	    renderer.setXLabels(12);
	    renderer.setYLabels(10);
	    renderer.setShowGrid(true);
	    renderer.setXLabelsAlign(Align.RIGHT);
	    renderer.setYLabelsAlign(Align.RIGHT);
	    //renderer.setZoomButtonsVisible(true);
	    //renderer.setPanLimits(new double[] { 0, 25, -10,  2000});
	    //renderer.setZoomLimits(new double[] { 0, 25, -10, 2000 });
	    
	    view = ChartFactory.getCubeLineChartView(context, buildDataset(titles, x, values),
	        renderer, 0.33f);
	}else if(key1=="Pressure"){
		String[] titles = new String[pressure.length];
		  for (int i = 0; i < pressure.length; i++) {
			  titles[i] = "相对气压"+(i+1);
		  }
		 
		  int count = 0;
		 
	      for (int i = 0; i < monitorlist.size(); i++) {
				mon = monitorlist.get(i); 
				
				if (monitorlist.get(0).getTime().substring(0, 10).equals(monitorlist.get(i).getTime().substring(0, 10))) {
					int h = Integer.valueOf(mon.getTime().substring(11, 13));//小时
					int m = Integer.valueOf(mon.getTime().substring(14, 15));//分钟：0
					
					 Calendar c = Calendar.getInstance(Locale.CHINA);
					  /*int mDay = c.get(Calendar.DAY_OF_MONTH);//获取当前月份的日期号码 
					  
					  int d = Integer.valueOf(mon.getTime().substring(8, 10));*/
				
					if(1 == h && m == 0){
						data[count]=1;
						
						arraylist.add(mon.getPressure());
						
						count++;
					}else if(2 == h && m == 0){
						data[count]=2;
						arraylist.add(mon.getPressure());
						count++;
					}else if(3 == h && m == 0){
						data[count]=3;
						arraylist.add(mon.getPressure());
						count++;
					}else if(4 == h && m == 0){
						data[count]=4;
						arraylist.add(mon.getPressure());
						count++;
					}else if(5 == h && m == 0){
						data[count]=5;
						arraylist.add(mon.getPressure());
						count++;
					}else if(6 == h && m == 0){
						data[count]=6;
						arraylist.add(mon.getPressure());
						count++;
					}else if(7 == h && m == 0){
						data[count]=7;
						arraylist.add(mon.getPressure());
						count++;
					}else if(8 == h && m == 0){
						data[count]=8;
						arraylist.add(mon.getPressure());
						count++;
					}else if(9 == h && m == 0){
						data[count]=9;
						arraylist.add(mon.getPressure());
						count++;
					}else if(10 == h && m == 0){
						data[count]=10;
						arraylist.add(mon.getPressure());
						count++;
					}else if(11 == h && m == 0){
						data[count]=11;
						arraylist.add(mon.getPressure());
						count++;
					}else if(12 == h && m == 0){
						data[count]=12;
						arraylist.add(mon.getPressure());
						count++;
					}else if(13 == h && m == 0){
						data[count]=13;
						arraylist.add(mon.getPressure());
						count++;
					}else if(14 == h && m == 0){
						data[count]=14;
						arraylist.add(mon.getPressure());
						count++;
					}else if(15 == h && m == 0){
						data[count]=15;
						arraylist.add(mon.getPressure());
						count++;
					}else if(16 == h && m == 0){
						data[count]=16;
						arraylist.add(mon.getPressure());
						count++;
					}else if(17 == h && m == 0){
						data[count]=17;
						arraylist.add(mon.getPressure());
						count++;
					}else if(18 == h && m == 0){
						data[count]=18;
						arraylist.add(mon.getPressure());
						count++;
					}else if(19 == h && m == 0){
						data[count]=19;
						arraylist.add(mon.getPressure());
						count++;
					}else if(20 == h && m == 0){
						data[count]=20;
						arraylist.add(mon.getPressure());
						count++;
					}else if(21 == h && m == 0){
						data[count]=21;
						arraylist.add(mon.getPressure());
						count++;
					}else if(22 == h && m == 0){
						data[count]=22;
						arraylist.add(mon.getPressure());
						count++;
					}else if(23 == h && m == 0){
						data[count]=23;
						arraylist.add(mon.getPressure());
						count++;
					}else if(0 == h && m == 0){
						data[count]=0;
						arraylist.add(mon.getPressure());
						count++;
					}
				
					
				}
				
				
				
	      }	
	      
	      List<double[]> values = new ArrayList<double[]>();//点集的y坐标
	      for (int i = 0; i < titles.length; i++) {
	    	  x.add(data);
	    	  double[] detaildatadata = new double[arraylist.size()];
	    	  for (int j = 0; j < arraylist.size(); j++) {
	    		  detaildata = (String[]) arraylist.get(j);
	    		  detaildatadata[j] = Double.parseDouble(detaildata[i]);
	    		 
	    	  }
	    	  values.add(detaildatadata);
	    	  
	    	  /*maxdata = detaildatadata[0];
	    	  mindata = detaildatadata[0];*/
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]>maxdata){

	    			  maxdata=detaildatadata[j];

	    		  }
              }
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]<mindata){

	    			  mindata=detaildatadata[j];

	    		  }
              }
	      }
	   
	      
	    //每条曲线点的颜色
		    int[] colors = new int[titles.length];
		    for (int i = 0; i < titles.length; i++) {
		    	colors[i]=chartcolors[i];
		    }
		    //每条曲线点的形状
		    PointStyle[] styles = new PointStyle[titles.length] ;
		    for (int i = 0; i < titles.length; i++) {
		    	styles[i]=chartstyles[i];
		    }
	      
	    
	    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
	    int length = renderer.getSeriesRendererCount();
	    for (int i = 0; i < length; i++) {
	      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
	    }
	    
	    setChartSettings(renderer, "", "时间", "Pa", 0.5, 24, mindata-10, maxdata+20,
	    		 Color.BLACK, Color.BLACK);
	    renderer.setMarginsColor(Color.WHITE);
	    renderer.setZoomEnabled(false, false);//设置不允许放大缩小. 
	    renderer.setPanEnabled(false, false);//设置是否可以平移
	    renderer.setLabelsTextSize(20);    //数轴刻度字体大小
	    renderer.setAxisTitleTextSize(20); 
	    renderer.setXLabels(12);
	    renderer.setYLabels(10);
	    renderer.setShowGrid(true);
	    renderer.setXLabelsAlign(Align.RIGHT);
	    renderer.setYLabelsAlign(Align.RIGHT);
	    //renderer.setZoomButtonsVisible(true);
	    //renderer.setPanLimits(new double[] { 0, 25, -10,  1000});
	    //renderer.setZoomLimits(new double[] { 0, 25, -10, 1000});
	    
	    view = ChartFactory.getCubeLineChartView(context, buildDataset(titles, x, values),
	        renderer, 0.33f);
	}else if(key1=="SoilHumiditys"){
		String[] titles = new String[SoilHumiditys.length];
		  for (int i = 0; i < SoilHumiditys.length; i++) {
			  titles[i] = "土壤水分"+(i+1);
		  }
		
		  int count = 0;
		 
	      for (int i = 0; i < monitorlist.size(); i++) {
				mon = monitorlist.get(i); 
				
				if (monitorlist.get(0).getTime().substring(0, 10).equals(monitorlist.get(i).getTime().substring(0, 10))) {
					int h = Integer.valueOf(mon.getTime().substring(11, 13));//小时
					int m = Integer.valueOf(mon.getTime().substring(14, 15));//分钟：0
					
					 Calendar c = Calendar.getInstance(Locale.CHINA);
					  /*int mDay = c.get(Calendar.DAY_OF_MONTH);//获取当前月份的日期号码 
					  
					  int d = Integer.valueOf(mon.getTime().substring(8, 10));*/
				
					if(1 == h && m == 0){
						data[count]=1;
						
					    arraylist.add(mon.getSoilHumiditys());
						
						count++;
					}else if(2 == h && m == 0){
						data[count]=2;
						arraylist.add(mon.getSoilHumiditys());
						count++;
					}else if(3 == h && m == 0){
						data[count]=3;
						arraylist.add(mon.getSoilHumiditys());
						count++;
					}else if(4 == h && m == 0){
						data[count]=4;
						arraylist.add(mon.getSoilHumiditys());
						count++;
					}else if(5 == h && m == 0){
						data[count]=5;
						arraylist.add(mon.getSoilHumiditys());
						count++;
					}else if(6 == h && m == 0){
						data[count]=6;
						arraylist.add(mon.getSoilHumiditys());
						count++;
					}else if(7 == h && m == 0){
						data[count]=7;
						arraylist.add(mon.getSoilHumiditys());
						count++;
					}else if(8 == h && m == 0){
						data[count]=8;
						arraylist.add(mon.getSoilHumiditys());
						count++;
					}else if(9 == h && m == 0){
						data[count]=9;
						arraylist.add(mon.getSoilHumiditys());
						count++;
					}else if(10 == h && m == 0){
						data[count]=10;
						arraylist.add(mon.getSoilHumiditys());
						count++;
					}else if(11 == h && m == 0){
						data[count]=11;
						arraylist.add(mon.getSoilHumiditys());
						count++;
					}else if(12 == h && m == 0){
						data[count]=12;
						arraylist.add(mon.getSoilHumiditys());
						count++;
					}else if(13 == h && m == 0){
						data[count]=13;
						arraylist.add(mon.getSoilHumiditys());
						count++;
					}else if(14 == h && m == 0){
						data[count]=14;
						arraylist.add(mon.getSoilHumiditys());
						count++;
					}else if(15 == h && m == 0){
						data[count]=15;
						arraylist.add(mon.getSoilHumiditys());
						count++;
					}else if(16 == h && m == 0){
						data[count]=16;
						arraylist.add(mon.getSoilHumiditys());
						count++;
					}else if(17 == h && m == 0){
						data[count]=17;
						arraylist.add(mon.getSoilHumiditys());
						count++;
					}else if(18 == h && m == 0){
						data[count]=18;
						arraylist.add(mon.getSoilHumiditys());
						count++;
					}else if(19 == h && m == 0){
						data[count]=19;
						arraylist.add(mon.getSoilHumiditys());
						count++;
					}else if(20 == h && m == 0){
						data[count]=20;
						arraylist.add(mon.getSoilHumiditys());
						count++;
					}else if(21 == h && m == 0){
						data[count]=21;
						arraylist.add(mon.getSoilHumiditys());
						count++;
					}else if(22 == h && m == 0){
						data[count]=22;
						arraylist.add(mon.getSoilHumiditys());
						count++;
					}else if(23 == h && m == 0){
						data[count]=23;
						arraylist.add(mon.getSoilHumiditys());
						count++;
					}else if(0 == h && m == 0){
						data[count]=0;
						arraylist.add(mon.getSoilHumiditys());
						count++;
					}
				
				}
				
				
				
	      }	
	      
	      List<double[]> values = new ArrayList<double[]>();//点集的y坐标
	      for (int i = 0; i < titles.length; i++) {
	    	  x.add(data);
	    	  double[] detaildatadata = new double[arraylist.size()];
	    	  for (int j = 0; j < arraylist.size(); j++) {
	    		  detaildata = (String[]) arraylist.get(j);
	    		  detaildatadata[j] = Double.parseDouble(detaildata[i]);
	    		 
	    	  }
	    	  values.add(detaildatadata);
	    	  
	    	 /* maxdata = detaildatadata[0];
	    	  mindata = detaildatadata[0];*/
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]>maxdata){

	    			  maxdata=detaildatadata[j];

	    		  }
              }
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]<mindata){

	    			  mindata=detaildatadata[j];

	    		  }
              }
	      }
	   
	      
	    //每条曲线点的颜色
		    int[] colors = new int[titles.length];
		    for (int i = 0; i < titles.length; i++) {
		    	colors[i]=chartcolors[i];
		    }
		    //每条曲线点的形状
		    PointStyle[] styles = new PointStyle[titles.length] ;
		    for (int i = 0; i < titles.length; i++) {
		    	styles[i]=chartstyles[i];
		    }
	      
	    
	    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
	    int length = renderer.getSeriesRendererCount();
	    for (int i = 0; i < length; i++) {
	      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
	    }
	    
	    setChartSettings(renderer, "", "时间", "%HR", 0, 24, mindata-5, maxdata+10,
	    		 Color.BLACK, Color.BLACK);
	    renderer.setMarginsColor(Color.WHITE);
	    renderer.setZoomEnabled(false, false);//设置不允许放大缩小. 
	    renderer.setPanEnabled(false, false);//设置是否可以平移
	    renderer.setLabelsTextSize(20);    //数轴刻度字体大小
	    renderer.setAxisTitleTextSize(20); 
	    renderer.setXLabels(12);
	    renderer.setYLabels(10);
	    renderer.setShowGrid(true);
	    renderer.setXLabelsAlign(Align.RIGHT);
	    renderer.setYLabelsAlign(Align.RIGHT);
	    //renderer.setZoomButtonsVisible(true);
	    //renderer.setPanLimits(new double[] { 0, 25, -10,  100});
	    //renderer.setZoomLimits(new double[] { 0, 25, -10, 100});
	    
	    view = ChartFactory.getCubeLineChartView(context, buildDataset(titles, x, values),
		        renderer, 0.33f);
	}else if(key1=="PAR"){
		String[] titles = new String[PAR.length];
		  for (int i = 0; i < PAR.length; i++) {
			  titles[i] = "光量子"+(i+1);
		  }
		
		  int count = 0;
		 
	      for (int i = 0; i < monitorlist.size(); i++) {
				mon = monitorlist.get(i); 
				
				if (monitorlist.get(0).getTime().substring(0, 10).equals(monitorlist.get(i).getTime().substring(0, 10))) {
					int h = Integer.valueOf(mon.getTime().substring(11, 13));//小时
					int m = Integer.valueOf(mon.getTime().substring(14, 15));//分钟：0
					
					 Calendar c = Calendar.getInstance(Locale.CHINA);
					  /*int mDay = c.get(Calendar.DAY_OF_MONTH);//获取当前月份的日期号码 
					  
					  int d = Integer.valueOf(mon.getTime().substring(8, 10));*/
				
					if(1 == h && m == 0){
						data[count]=1;
						
						arraylist.add(mon.getPAR());
						
						count++;
					}else if(2 == h && m == 0){
						data[count]=2;
						arraylist.add(mon.getPAR());
						count++;
					}else if(3 == h && m == 0){
						data[count]=3;
						arraylist.add(mon.getPAR());
						count++;
					}else if(4 == h && m == 0){
						data[count]=4;
						arraylist.add(mon.getPAR());
						count++;
					}else if(5 == h && m == 0){
						data[count]=5;
						arraylist.add(mon.getPAR());
						count++;
					}else if(6 == h && m == 0){
						data[count]=6;
						arraylist.add(mon.getPAR());
						count++;
					}else if(7 == h && m == 0){
						data[count]=7;
						arraylist.add(mon.getPAR());
						count++;
					}else if(8 == h && m == 0){
						data[count]=8;
						arraylist.add(mon.getPAR());
						count++;
					}else if(9 == h && m == 0){
						data[count]=9;
						arraylist.add(mon.getPAR());
						count++;
					}else if(10 == h && m == 0){
						data[count]=10;
						arraylist.add(mon.getPAR());
						count++;
					}else if(11 == h && m == 0){
						data[count]=11;
						arraylist.add(mon.getPAR());
						count++;
					}else if(12 == h && m == 0){
						data[count]=12;
						arraylist.add(mon.getPAR());
						count++;
					}else if(13 == h && m == 0){
						data[count]=13;
						arraylist.add(mon.getPAR());
						count++;
					}else if(14 == h && m == 0){
						data[count]=14;
						arraylist.add(mon.getPAR());
						count++;
					}else if(15 == h && m == 0){
						data[count]=15;
						arraylist.add(mon.getPAR());
						count++;
					}else if(16 == h && m == 0){
						data[count]=16;
						arraylist.add(mon.getPAR());
						count++;
					}else if(17 == h && m == 0){
						data[count]=17;
						arraylist.add(mon.getPAR());
						count++;
					}else if(18 == h && m == 0){
						data[count]=18;
						arraylist.add(mon.getPAR());
						count++;
					}else if(19 == h && m == 0){
						data[count]=19;
						arraylist.add(mon.getPAR());
						count++;
					}else if(20 == h && m == 0){
						data[count]=20;
						arraylist.add(mon.getPAR());
						count++;
					}else if(21 == h && m == 0){
						data[count]=21;
						arraylist.add(mon.getPAR());
						count++;
					}else if(22 == h && m == 0){
						data[count]=22;
						arraylist.add(mon.getPAR());
						count++;
					}else if(23 == h && m == 0){
						data[count]=23;
						arraylist.add(mon.getPAR());
						count++;
					}else if(0 == h && m == 0){
						data[count]=0;
						arraylist.add(mon.getPAR());
						count++;
					}
				
				}
				
				
	      }	
	      
	      List<double[]> values = new ArrayList<double[]>();//点集的y坐标
	      for (int i = 0; i < titles.length; i++) {
	    	  x.add(data);
	    	  double[] detaildatadata = new double[arraylist.size()];
	    	  for (int j = 0; j < arraylist.size(); j++) {
	    		  detaildata = (String[]) arraylist.get(j);
	    		  detaildatadata[j] = Double.parseDouble(detaildata[i]);
	    		  
	    	  }
	    	  values.add(detaildatadata);
	    	  
	    	  /*maxdata = detaildatadata[0];
	    	  mindata = detaildatadata[0];*/
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]>maxdata){

	    			  maxdata=detaildatadata[j];

	    		  }
              }
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]<mindata){

	    			  mindata=detaildatadata[j];

	    		  }
              }
	      }
	   
	      
	    //每条曲线点的颜色
		    int[] colors = new int[titles.length];
		    for (int i = 0; i < titles.length; i++) {
		    	colors[i]=chartcolors[i];
		    }
		    //每条曲线点的形状
		    PointStyle[] styles = new PointStyle[titles.length] ;
		    for (int i = 0; i < titles.length; i++) {
		    	styles[i]=chartstyles[i];
		    }
	      
	    
	    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
	    int length = renderer.getSeriesRendererCount();
	    for (int i = 0; i < length; i++) {
	      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
	    }
	    
	    setChartSettings(renderer, "", "时间", "μmols-1m-2", 0, 24, mindata-10, maxdata+20,
	    		 Color.BLACK, Color.BLACK);
	    renderer.setMarginsColor(Color.WHITE);
	    renderer.setZoomEnabled(false, false);//设置不允许放大缩小. 
	    renderer.setPanEnabled(false, false);//设置是否可以平移
	    renderer.setLabelsTextSize(20);    //数轴刻度字体大小
	    renderer.setAxisTitleTextSize(20); 
	    renderer.setXLabels(12);
	    renderer.setYLabels(10);
	    renderer.setShowGrid(true);
	    renderer.setXLabelsAlign(Align.RIGHT);
	    renderer.setYLabelsAlign(Align.RIGHT);
	    //renderer.setZoomButtonsVisible(true);
	    //renderer.setPanLimits(new double[] { 0, 25, -10,  500});
	    //renderer.setZoomLimits(new double[] { 0, 25, -10, 500});
	    
	    view = ChartFactory.getCubeLineChartView(context, buildDataset(titles, x, values),
		        renderer, 0.33f);
	}else if(key1=="CAnemometer"){
		
		String[] titles = new String[CAnemometer.length];
		
		  for (int i = 0; i < CAnemometer.length; i++) {
			  titles[i] = "风速"+(i+1);
		  }
		
		  int count = 0;
		 
	      for (int i = 0; i < monitorlist.size(); i++) {
				mon = monitorlist.get(i); 
				if (monitorlist.get(0).getTime().substring(0, 10).equals(monitorlist.get(i).getTime().substring(0, 10))) {
					int h = Integer.valueOf(mon.getTime().substring(11, 13));//小时
					int m = Integer.valueOf(mon.getTime().substring(14, 15));//分钟：0
					
					 Calendar c = Calendar.getInstance(Locale.CHINA);
					  /*int mDay = c.get(Calendar.DAY_OF_MONTH);//获取当前月份的日期号码 
					  
					  int d = Integer.valueOf(mon.getTime().substring(8, 10));*/
				
					if(1 == h && m == 0){
						data[count]=1;
						
						arraylist.add(mon.getCAnemometer());
						
						count++;
					}else if(2 == h && m == 0){
						data[count]=2;
						arraylist.add(mon.getCAnemometer());
						count++;
					}else if(3 == h && m == 0){
						data[count]=3;
						arraylist.add(mon.getCAnemometer());
						count++;
					}else if(4 == h && m == 0){
						data[count]=4;
						arraylist.add(mon.getCAnemometer());
						count++;
					}else if(5 == h && m == 0){
						data[count]=5;
						arraylist.add(mon.getCAnemometer());
						count++;
					}else if(6 == h && m == 0){
						data[count]=6;
						arraylist.add(mon.getCAnemometer());
						count++;
					}else if(7 == h && m == 0){
						data[count]=7;
						arraylist.add(mon.getCAnemometer());
						count++;
					}else if(8 == h && m == 0){
						data[count]=8;
						arraylist.add(mon.getCAnemometer());
						count++;
					}else if(9 == h && m == 0){
						data[count]=9;
						arraylist.add(mon.getCAnemometer());
						count++;
					}else if(10 == h && m == 0){
						data[count]=10;
						arraylist.add(mon.getCAnemometer());
						count++;
					}else if(11 == h && m == 0){
						data[count]=11;
						arraylist.add(mon.getCAnemometer());
						count++;
					}else if(12 == h && m == 0){
						data[count]=12;
						arraylist.add(mon.getCAnemometer());
						count++;
					}else if(13 == h && m == 0){
						data[count]=13;
						arraylist.add(mon.getCAnemometer());
						count++;
					}else if(14 == h && m == 0){
						data[count]=14;
						arraylist.add(mon.getCAnemometer());
						count++;
					}else if(15 == h && m == 0){
						data[count]=15;
						arraylist.add(mon.getCAnemometer());
						count++;
					}else if(16 == h && m == 0){
						data[count]=16;
						arraylist.add(mon.getCAnemometer());
						count++;
					}else if(17 == h && m == 0){
						data[count]=17;
						arraylist.add(mon.getCAnemometer());
						count++;
					}else if(18 == h && m == 0){
						data[count]=18;
						arraylist.add(mon.getCAnemometer());
						count++;
					}else if(19 == h && m == 0){
						data[count]=19;
						arraylist.add(mon.getCAnemometer());
						count++;
					}else if(20 == h && m == 0){
						data[count]=20;
						arraylist.add(mon.getCAnemometer());
						count++;
					}else if(21 == h && m == 0){
						data[count]=21;
						arraylist.add(mon.getCAnemometer());
						count++;
					}else if(22 == h && m == 0){
						data[count]=22;
						arraylist.add(mon.getCAnemometer());
						count++;
					}else if(23 == h && m == 0){
						data[count]=23;
						arraylist.add(mon.getCAnemometer());
						count++;
					}else if(0 == h && m == 0){
						data[count]=0;
						arraylist.add(mon.getCAnemometer());
						count++;
					}
				
					
				}
				
	      }	
	      
	      List<double[]> values = new ArrayList<double[]>();//点集的y坐标
	      for (int i = 0; i < titles.length; i++) {
	    	  x.add(data);
	    	  double[] detaildatadata = new double[arraylist.size()];
	    	  for (int j = 0; j < arraylist.size(); j++) {
	    		  detaildata = (String[]) arraylist.get(j);
	    		  detaildatadata[j] = Double.parseDouble(detaildata[i]);
	    		  
	    	  }
	    	  values.add(detaildatadata);
	    	  
	    	  /*maxdata = detaildatadata[0];
	    	  mindata = detaildatadata[0];*/
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]>maxdata){

	    			  maxdata=detaildatadata[j];

	    		  }
              }
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]<mindata){

	    			  mindata=detaildatadata[j];

	    		  }
              }
	      }
	   
	      
	    //每条曲线点的颜色
		    int[] colors = new int[titles.length];
		    for (int i = 0; i < titles.length; i++) {
		    	colors[i]=chartcolors[i];
		    }
		    //每条曲线点的形状
		    PointStyle[] styles = new PointStyle[titles.length] ;
		    for (int i = 0; i < titles.length; i++) {
		    	styles[i]=chartstyles[i];
		    }
	      
	    
	    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
	    int length = renderer.getSeriesRendererCount();
	    for (int i = 0; i < length; i++) {
	      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
	    }
	    
	    setChartSettings(renderer, "", "时间", "ms-1", 0, 24, mindata-10, maxdata+20,
	    		 Color.BLACK, Color.BLACK);
	    renderer.setMarginsColor(Color.WHITE);
	    renderer.setZoomEnabled(false, false);//设置不允许放大缩小. 
	    renderer.setPanEnabled(false, false);//设置是否可以平移
	    renderer.setLabelsTextSize(20);    //数轴刻度字体大小
	    renderer.setAxisTitleTextSize(20); 
	    renderer.setXLabels(12);
	    renderer.setYLabels(10);
	    renderer.setShowGrid(true);
	    renderer.setXLabelsAlign(Align.RIGHT);
	    renderer.setYLabelsAlign(Align.RIGHT);
	    //renderer.setZoomButtonsVisible(true);
	    //renderer.setPanLimits(new double[] { 0, 25, -10,  100});
	    //renderer.setZoomLimits(new double[] { 0, 25, -10, 100 });
	    
	    view = ChartFactory.getCubeLineChartView(context, buildDataset(titles, x, values),
		        renderer, 0.33f);
	}else if(key1=="Dogvane"){
		String[] titles = new String[dogvane.length];
		  for (int i = 0; i < dogvane.length; i++) {
			  titles[i] = "风向"+(i+1);
		  }
		
		  int count = 0;
		 
	      for (int i = 0; i < monitorlist.size(); i++) {
				mon = monitorlist.get(i); 
				
				if (monitorlist.get(0).getTime().substring(0, 10).equals(monitorlist.get(i).getTime().substring(0, 10))) {
					int h = Integer.valueOf(mon.getTime().substring(11, 13));//小时
					int m = Integer.valueOf(mon.getTime().substring(14, 15));//分钟：0
					
					 Calendar c = Calendar.getInstance(Locale.CHINA);
					 /* int mDay = c.get(Calendar.DAY_OF_MONTH);//获取当前月份的日期号码 
					  
					  int d = Integer.valueOf(mon.getTime().substring(8, 10));*/
				
					if(1 == h && m == 0){
						data[count]=1;
						
						arraylist.add(mon.getDogvane());
						
						count++;
					}else if(2 == h && m == 0){
						data[count]=2;
						arraylist.add(mon.getDogvane());
						count++;
					}else if(3 == h && m == 0){
						data[count]=3;
						arraylist.add(mon.getDogvane());
						count++;
					}else if(4 == h && m == 0){
						data[count]=4;
						arraylist.add(mon.getDogvane());
						count++;
					}else if(5 == h && m == 0){
						data[count]=5;
						arraylist.add(mon.getDogvane());
						count++;
					}else if(6 == h && m == 0){
						data[count]=6;
						arraylist.add(mon.getDogvane());
						count++;
					}else if(7 == h && m == 0){
						data[count]=7;
						arraylist.add(mon.getDogvane());
						count++;
					}else if(8 == h && m == 0){
						data[count]=8;
						arraylist.add(mon.getDogvane());
						count++;
					}else if(9 == h && m == 0){
						data[count]=9;
						arraylist.add(mon.getDogvane());
						count++;
					}else if(10 == h && m == 0){
						data[count]=10;
						arraylist.add(mon.getDogvane());
						count++;
					}else if(11 == h && m == 0){
						data[count]=11;
						arraylist.add(mon.getDogvane());
						count++;
					}else if(12 == h && m == 0){
						data[count]=12;
						arraylist.add(mon.getDogvane());
						count++;
					}else if(13 == h && m == 0){
						data[count]=13;
						arraylist.add(mon.getDogvane());
						count++;
					}else if(14 == h && m == 0){
						data[count]=14;
						arraylist.add(mon.getDogvane());
						count++;
					}else if(15 == h && m == 0){
						data[count]=15;
						arraylist.add(mon.getDogvane());
						count++;
					}else if(16 == h && m == 0){
						data[count]=16;
						arraylist.add(mon.getDogvane());
						count++;
					}else if(17 == h && m == 0){
						data[count]=17;
						arraylist.add(mon.getDogvane());
						count++;
					}else if(18 == h && m == 0){
						data[count]=18;
						arraylist.add(mon.getDogvane());
						count++;
					}else if(19 == h && m == 0){
						data[count]=19;
						arraylist.add(mon.getDogvane());
						count++;
					}else if(20 == h && m == 0){
						data[count]=20;
						arraylist.add(mon.getDogvane());
						count++;
					}else if(21 == h && m == 0){
						data[count]=21;
						arraylist.add(mon.getDogvane());
						count++;
					}else if(22 == h && m == 0){
						data[count]=22;
						arraylist.add(mon.getDogvane());
						count++;
					}else if(23 == h && m == 0){
						data[count]=23;
						arraylist.add(mon.getDogvane());
						count++;
					}else if(0 == h && m == 0){
						data[count]=0;
						arraylist.add(mon.getDogvane());
						count++;
					}
				
				}
				
				
	      }	
	      
	      List<double[]> values = new ArrayList<double[]>();//点集的y坐标
	      for (int i = 0; i < titles.length; i++) {
	    	  x.add(data);
	    	  double[] detaildatadata = new double[arraylist.size()];
	    	  for (int j = 0; j < arraylist.size(); j++) {
	    		  detaildata = (String[]) arraylist.get(j);
	    		  detaildatadata[j] = Double.parseDouble(detaildata[i]);
	    		  
	    	  }
	    	  values.add(detaildatadata);
	    	  
	    	  /*maxdata = detaildatadata[0];
	    	  mindata = detaildatadata[0];*/
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]>maxdata){

	    			  maxdata=detaildatadata[j];

	    		  }
              }
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]<mindata){

	    			  mindata=detaildatadata[j];

	    		  }
              }
	      }
	   
	      
	    //每条曲线点的颜色
		    int[] colors = new int[titles.length];
		    for (int i = 0; i < titles.length; i++) {
		    	colors[i]=chartcolors[i];
		    }
		    //每条曲线点的形状
		    PointStyle[] styles = new PointStyle[titles.length] ;
		    for (int i = 0; i < titles.length; i++) {
		    	styles[i]=chartstyles[i];
		    }
	      
	    
	    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
	    int length = renderer.getSeriesRendererCount();
	    for (int i = 0; i < length; i++) {
	      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
	    }
	    
	    setChartSettings(renderer, "", "时间", "度", 0, 24, mindata-10, maxdata+20,
	    		 Color.BLACK, Color.BLACK);
	    renderer.setMarginsColor(Color.WHITE);
	    renderer.setZoomEnabled(false, false);//设置不允许放大缩小. 
	    renderer.setPanEnabled(false, false);//设置是否可以平移
	    renderer.setLabelsTextSize(20);    //数轴刻度字体大小
	    renderer.setAxisTitleTextSize(20); 
	    renderer.setXLabels(12);
	    renderer.setYLabels(10);
	    renderer.setShowGrid(true);
	    renderer.setXLabelsAlign(Align.RIGHT);
	    renderer.setYLabelsAlign(Align.RIGHT);
	    //renderer.setZoomButtonsVisible(true);
	    //renderer.setPanLimits(new double[] { 0, 25, -10,  360});
	    //renderer.setZoomLimits(new double[] { 0, 25, -10, 360 });
	    
	    view = ChartFactory.getCubeLineChartView(context, buildDataset(titles, x, values),
		        renderer, 0.33f);
	}else if(key1=="Rainfall"){
		String[] titles = new String[rainfall.length];
		  for (int i = 0; i < rainfall.length; i++) {
			  titles[i] = "降雨量"+(i+1);
		  }
		
		  int count = 0;
		 
	      for (int i = 0; i < monitorlist.size(); i++) {
				mon = monitorlist.get(i); 
				
				if (monitorlist.get(0).getTime().substring(0, 10).equals(monitorlist.get(i).getTime().substring(0, 10))) {
					int h = Integer.valueOf(mon.getTime().substring(11, 13));//小时
					int m = Integer.valueOf(mon.getTime().substring(14, 15));//分钟：0
					
					 Calendar c = Calendar.getInstance(Locale.CHINA);
					 /* int mDay = c.get(Calendar.DAY_OF_MONTH);//获取当前月份的日期号码 
					  
					  int d = Integer.valueOf(mon.getTime().substring(8, 10));*/
				
					if(1 == h && m == 0){
						data[count]=1;
						
						arraylist.add(mon.getRainfall());
						
						count++;
					}else if(2 == h && m == 0){
						data[count]=2;
						arraylist.add(mon.getRainfall());
						count++;
					}else if(3 == h && m == 0){
						data[count]=3;
						arraylist.add(mon.getRainfall());
						count++;
					}else if(4 == h && m == 0){
						data[count]=4;
						arraylist.add(mon.getRainfall());
						count++;
					}else if(5 == h && m == 0){
						data[count]=5;
						arraylist.add(mon.getRainfall());
						count++;
					}else if(6 == h && m == 0){
						data[count]=6;
						arraylist.add(mon.getRainfall());
						count++;
					}else if(7 == h && m == 0){
						data[count]=7;
						arraylist.add(mon.getRainfall());
						count++;
					}else if(8 == h && m == 0){
						data[count]=8;
						arraylist.add(mon.getRainfall());
						count++;
					}else if(9 == h && m == 0){
						data[count]=9;
						arraylist.add(mon.getRainfall());
						count++;
					}else if(10 == h && m == 0){
						data[count]=10;
						arraylist.add(mon.getRainfall());
						count++;
					}else if(11 == h && m == 0){
						data[count]=11;
						arraylist.add(mon.getRainfall());
						count++;
					}else if(12 == h && m == 0){
						data[count]=12;
						arraylist.add(mon.getRainfall());
						count++;
					}else if(13 == h && m == 0){
						data[count]=13;
						arraylist.add(mon.getRainfall());
						count++;
					}else if(14 == h && m == 0){
						data[count]=14;
						arraylist.add(mon.getRainfall());
						count++;
					}else if(15 == h && m == 0){
						data[count]=15;
						arraylist.add(mon.getRainfall());
						count++;
					}else if(16 == h && m == 0){
						data[count]=16;
						arraylist.add(mon.getRainfall());
						count++;
					}else if(17 == h && m == 0){
						data[count]=17;
						arraylist.add(mon.getRainfall());
						count++;
					}else if(18 == h && m == 0){
						data[count]=18;
						arraylist.add(mon.getRainfall());
						count++;
					}else if(19 == h && m == 0){
						data[count]=19;
						arraylist.add(mon.getRainfall());
						count++;
					}else if(20 == h && m == 0){
						data[count]=20;
						arraylist.add(mon.getRainfall());
						count++;
					}else if(21 == h && m == 0){
						data[count]=21;
						arraylist.add(mon.getRainfall());
						count++;
					}else if(22 == h && m == 0){
						data[count]=22;
						arraylist.add(mon.getRainfall());
						count++;
					}else if(23 == h && m == 0){
						data[count]=23;
						arraylist.add(mon.getRainfall());
						count++;
					}else if(0 == h && m == 0){
						data[count]=0;
						arraylist.add(mon.getRainfall());
						count++;
					}
				
				}
				
				
				
	      }	
	      
	      List<double[]> values = new ArrayList<double[]>();//点集的y坐标
	      for (int i = 0; i < titles.length; i++) {
	    	  x.add(data);
	    	  double[] detaildatadata = new double[arraylist.size()];
	    	  for (int j = 0; j < arraylist.size(); j++) {
	    		  detaildata = (String[]) arraylist.get(j);
	    		  detaildatadata[j] = Double.parseDouble(detaildata[i]);
	    		  
	    	  }
	    	  values.add(detaildatadata);
	    	  
	    	  /*maxdata = detaildatadata[0];
	    	  mindata = detaildatadata[0];*/
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]>maxdata){

	    			  maxdata=detaildatadata[j];

	    		  }
              }
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]<mindata){

	    			  mindata=detaildatadata[j];

	    		  }
              }
	      }
	   
	      
	    //每条曲线点的颜色
		    int[] colors = new int[titles.length];
		    for (int i = 0; i < titles.length; i++) {
		    	colors[i]=chartcolors[i];
		    }
		    //每条曲线点的形状
		    PointStyle[] styles = new PointStyle[titles.length] ;
		    for (int i = 0; i < titles.length; i++) {
		    	styles[i]=chartstyles[i];
		    }
	      
	    
	    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
	    int length = renderer.getSeriesRendererCount();
	    for (int i = 0; i < length; i++) {
	      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
	    }
	    
	    setChartSettings(renderer, "", "时间", "mm", 0, 24, mindata-20, maxdata+50,
	    		 Color.BLACK, Color.BLACK);
	    renderer.setMarginsColor(Color.WHITE);
	    renderer.setZoomEnabled(false, false);//设置不允许放大缩小. 
	    renderer.setPanEnabled(false, false);//设置是否可以平移
	    renderer.setLabelsTextSize(20);    //数轴刻度字体大小
	    renderer.setAxisTitleTextSize(20); 
	    renderer.setXLabels(12);
	    renderer.setYLabels(10);
	    renderer.setShowGrid(true);
	    renderer.setXLabelsAlign(Align.RIGHT);
	    renderer.setYLabelsAlign(Align.RIGHT);
	    //renderer.setZoomButtonsVisible(true);
	    //renderer.setPanLimits(new double[] { 0, 25, -10,  3000});
	    //renderer.setZoomLimits(new double[] { 0, 25, -10, 3000 });
	    
	    view = ChartFactory.getCubeLineChartView(context, buildDataset(titles, x, values),
		        renderer, 0.33f);
	}else if(key1=="Fengshipc"){
		String[] titles = new String[fengshipc.length];
		  for (int i = 0; i < fengshipc.length; i++) {
			  titles[i] = "风腐蚀PC"+(i+1);
		  }
		
		  int count = 0;
		 
	      for (int i = 0; i < monitorlist.size(); i++) {
				mon = monitorlist.get(i); 
				
				if (monitorlist.get(0).getTime().substring(0, 10).equals(monitorlist.get(i).getTime().substring(0, 10))) {
					int h = Integer.valueOf(mon.getTime().substring(11, 13));//小时
					int m = Integer.valueOf(mon.getTime().substring(14, 15));//分钟：0
					
					 Calendar c = Calendar.getInstance(Locale.CHINA);
					 /* int mDay = c.get(Calendar.DAY_OF_MONTH);//获取当前月份的日期号码 
					  
					  int d = Integer.valueOf(mon.getTime().substring(8, 10));*/
				
					if(1 == h && m == 0){
						data[count]=1;
						
						arraylist.add(mon.getFengshipc());
						
						count++;
					}else if(2 == h && m == 0){
						data[count]=2;
						arraylist.add(mon.getFengshipc());
						count++;
					}else if(3 == h && m == 0){
						data[count]=3;
						arraylist.add(mon.getFengshipc());
						count++;
					}else if(4 == h && m == 0){
						data[count]=4;
						arraylist.add(mon.getFengshipc());
						count++;
					}else if(5 == h && m == 0){
						data[count]=5;
						arraylist.add(mon.getFengshipc());
						count++;
					}else if(6 == h && m == 0){
						data[count]=6;
						arraylist.add(mon.getFengshipc());
						count++;
					}else if(7 == h && m == 0){
						data[count]=7;
						arraylist.add(mon.getFengshipc());
						count++;
					}else if(8 == h && m == 0){
						data[count]=8;
						arraylist.add(mon.getFengshipc());
						count++;
					}else if(9 == h && m == 0){
						data[count]=9;
						arraylist.add(mon.getFengshipc());
						count++;
					}else if(10 == h && m == 0){
						data[count]=10;
						arraylist.add(mon.getFengshipc());
						count++;
					}else if(11 == h && m == 0){
						data[count]=11;
						arraylist.add(mon.getFengshipc());
						count++;
					}else if(12 == h && m == 0){
						data[count]=12;
						arraylist.add(mon.getFengshipc());
						count++;
					}else if(13 == h && m == 0){
						data[count]=13;
						arraylist.add(mon.getFengshipc());
						count++;
					}else if(14 == h && m == 0){
						data[count]=14;
						arraylist.add(mon.getFengshipc());
						count++;
					}else if(15 == h && m == 0){
						data[count]=15;
						arraylist.add(mon.getFengshipc());
						count++;
					}else if(16 == h && m == 0){
						data[count]=16;
						arraylist.add(mon.getFengshipc());
						count++;
					}else if(17 == h && m == 0){
						data[count]=17;
						arraylist.add(mon.getFengshipc());
						count++;
					}else if(18 == h && m == 0){
						data[count]=18;
						arraylist.add(mon.getFengshipc());
						count++;
					}else if(19 == h && m == 0){
						data[count]=19;
						arraylist.add(mon.getFengshipc());
						count++;
					}else if(20 == h && m == 0){
						data[count]=20;
						arraylist.add(mon.getFengshipc());
						count++;
					}else if(21 == h && m == 0){
						data[count]=21;
						arraylist.add(mon.getFengshipc());
						count++;
					}else if(22 == h && m == 0){
						data[count]=22;
						arraylist.add(mon.getFengshipc());
						count++;
					}else if(23 == h && m == 0){
						data[count]=23;
						arraylist.add(mon.getFengshipc());
						count++;
					}else if(0 == h && m == 0){
						data[count]=0;
						arraylist.add(mon.getFengshipc());
						count++;
					}
				
				}
				
				
	      }	
	      
	      List<double[]> values = new ArrayList<double[]>();//点集的y坐标
	      for (int i = 0; i < titles.length; i++) {
	    	  x.add(data);
	    	  double[] detaildatadata = new double[arraylist.size()];
	    	  for (int j = 0; j < arraylist.size(); j++) {
	    		  detaildata = (String[]) arraylist.get(j);
	    		  detaildatadata[j] = Double.parseDouble(detaildata[i]);
	    		  
	    	  }
	    	  values.add(detaildatadata);
	    	  
	    	  /*maxdata = detaildatadata[0];
	    	  mindata = detaildatadata[0];*/
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]>maxdata){

	    			  maxdata=detaildatadata[j];

	    		  }
              }
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]<mindata){

	    			  mindata=detaildatadata[j];

	    		  }
              }
	      }
	   
	      
	    //每条曲线点的颜色
		    int[] colors = new int[titles.length];
		    for (int i = 0; i < titles.length; i++) {
		    	colors[i]=chartcolors[i];
		    }
		    //每条曲线点的形状
		    PointStyle[] styles = new PointStyle[titles.length] ;
		    for (int i = 0; i < titles.length; i++) {
		    	styles[i]=chartstyles[i];
		    }
	      
	    
	    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
	    int length = renderer.getSeriesRendererCount();
	    for (int i = 0; i < length; i++) {
	      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
	    }
	    
	    setChartSettings(renderer, "", "时间", "次", 0, 24.5, mindata-100, maxdata+500,
	    		 Color.BLACK, Color.BLACK);
	    renderer.setMarginsColor(Color.WHITE);
	    renderer.setZoomEnabled(false, false);//设置不允许放大缩小. 
	    renderer.setPanEnabled(false, false);//设置是否可以平移
	    renderer.setLabelsTextSize(20);    //数轴刻度字体大小
	    renderer.setAxisTitleTextSize(20); 
	    renderer.setXLabels(12);
	    renderer.setYLabels(10);
	    renderer.setShowGrid(true);
	    renderer.setXLabelsAlign(Align.RIGHT);
	    renderer.setYLabelsAlign(Align.RIGHT);
	    //renderer.setZoomButtonsVisible(true);
	    //renderer.setPanLimits(new double[] { 0, 25, -10,  100000});
	    //renderer.setZoomLimits(new double[] { 0, 25, -10, 100000 });
	    
	    view = ChartFactory.getCubeLineChartView(context, buildDataset(titles, x, values),
		        renderer, 0.33f);
	}else if(key1=="AAnemometer"){
		String[] titles = new String[AAnemometer.length];
		  for (int i = 0; i < AAnemometer.length; i++) {
			  titles[i] = "平均风速"+(i+1);
		  }
		
		  int count = 0;
		 
	      for (int i = 0; i < monitorlist.size(); i++) {
				mon = monitorlist.get(i); 
				
				if (monitorlist.get(0).getTime().substring(0, 10).equals(monitorlist.get(i).getTime().substring(0, 10))) {
					int h = Integer.valueOf(mon.getTime().substring(11, 13));//小时
					int m = Integer.valueOf(mon.getTime().substring(14, 15));//分钟：0
					
					 Calendar c = Calendar.getInstance(Locale.CHINA);
					  /*int mDay = c.get(Calendar.DAY_OF_MONTH);//获取当前月份的日期号码 
					  
					  int d = Integer.valueOf(mon.getTime().substring(8, 10));*/
				
					if(1 == h && m == 0){
						data[count]=1;
						
						arraylist.add(mon.getAAnemometer());
						
						count++;
					}else if(2 == h && m == 0){
						data[count]=2;
						arraylist.add(mon.getAAnemometer());
						count++;
					}else if(3 == h && m == 0){
						data[count]=3;
						arraylist.add(mon.getAAnemometer());
						count++;
					}else if(4 == h && m == 0){
						data[count]=4;
						arraylist.add(mon.getAAnemometer());
						count++;
					}else if(5 == h && m == 0){
						data[count]=5;
						arraylist.add(mon.getAAnemometer());
						count++;
					}else if(6 == h && m == 0){
						data[count]=6;
						arraylist.add(mon.getAAnemometer());
						count++;
					}else if(7 == h && m == 0){
						data[count]=7;
						arraylist.add(mon.getAAnemometer());
						count++;
					}else if(8 == h && m == 0){
						data[count]=8;
						arraylist.add(mon.getAAnemometer());
						count++;
					}else if(9 == h && m == 0){
						data[count]=9;
						arraylist.add(mon.getAAnemometer());
						count++;
					}else if(10 == h && m == 0){
						data[count]=10;
						arraylist.add(mon.getAAnemometer());
						count++;
					}else if(11 == h && m == 0){
						data[count]=11;
						arraylist.add(mon.getAAnemometer());
						count++;
					}else if(12 == h && m == 0){
						data[count]=12;
						arraylist.add(mon.getAAnemometer());
						count++;
					}else if(13 == h && m == 0){
						data[count]=13;
						arraylist.add(mon.getAAnemometer());
						count++;
					}else if(14 == h && m == 0){
						data[count]=14;
						arraylist.add(mon.getAAnemometer());
						count++;
					}else if(15 == h && m == 0){
						data[count]=15;
						arraylist.add(mon.getAAnemometer());
						count++;
					}else if(16 == h && m == 0){
						data[count]=16;
						arraylist.add(mon.getAAnemometer());
						count++;
					}else if(17 == h && m == 0){
						data[count]=17;
						arraylist.add(mon.getAAnemometer());
						count++;
					}else if(18 == h && m == 0){
						data[count]=18;
						arraylist.add(mon.getAAnemometer());
						count++;
					}else if(19 == h && m == 0){
						data[count]=19;
						arraylist.add(mon.getAAnemometer());
						count++;
					}else if(20 == h && m == 0){
						data[count]=20;
						arraylist.add(mon.getAAnemometer());
						count++;
					}else if(21 == h && m == 0){
						data[count]=21;
						arraylist.add(mon.getAAnemometer());
						count++;
					}else if(22 == h && m == 0){
						data[count]=22;
						arraylist.add(mon.getAAnemometer());
						count++;
					}else if(23 == h && m == 0){
						data[count]=23;
						arraylist.add(mon.getAAnemometer());
						count++;
					}else if(0 == h && m == 0){
						data[count]=0;
						arraylist.add(mon.getAAnemometer());
						count++;
					}
				}
				
			
				
	      }	
	      
	      List<double[]> values = new ArrayList<double[]>();//点集的y坐标
	      for (int i = 0; i < titles.length; i++) {
	    	  x.add(data);
	    	  double[] detaildatadata = new double[arraylist.size()];
	    	  for (int j = 0; j < arraylist.size(); j++) {
	    		  detaildata = (String[]) arraylist.get(j);
	    		  detaildatadata[j] = Double.parseDouble(detaildata[i]);
	    		  
	    	  }
	    	  values.add(detaildatadata);
	    	  /*maxdata = detaildatadata[0];
	    	  mindata = detaildatadata[0];*/
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]>maxdata){

	    			  maxdata=detaildatadata[j];

	    		  }
              }
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]<mindata){

	    			  mindata=detaildatadata[j];

	    		  }
              }
	      }
	   
	      
	    //每条曲线点的颜色
		    int[] colors = new int[titles.length];
		    for (int i = 0; i < titles.length; i++) {
		    	colors[i]=chartcolors[i];
		    }
		    //每条曲线点的形状
		    PointStyle[] styles = new PointStyle[titles.length] ;
		    for (int i = 0; i < titles.length; i++) {
		    	styles[i]=chartstyles[i];
		    }
	      
	    
	    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
	    int length = renderer.getSeriesRendererCount();
	    for (int i = 0; i < length; i++) {
	      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
	    }
	    
	    setChartSettings(renderer, "", "时间", "ms-1", 0, 24, mindata-10, maxdata+10,
	    		 Color.BLACK, Color.BLACK);
	    renderer.setMarginsColor(Color.WHITE);
	    renderer.setZoomEnabled(false, false);//设置不允许放大缩小. 
	    renderer.setPanEnabled(false, false);//设置是否可以平移
	    renderer.setLabelsTextSize(20);    //数轴刻度字体大小
	    renderer.setAxisTitleTextSize(20); 
	    renderer.setXLabels(12);
	    renderer.setYLabels(10);
	    renderer.setShowGrid(true);
	    renderer.setXLabelsAlign(Align.RIGHT);
	    renderer.setYLabelsAlign(Align.RIGHT);
	    //renderer.setZoomButtonsVisible(true);
	    //renderer.setPanLimits(new double[] { 0, 25, -10,  50});
	    //renderer.setZoomLimits(new double[] { 0, 25, -10, 50 });
	    
	    view = ChartFactory.getCubeLineChartView(context, buildDataset(titles, x, values),
		        renderer, 0.33f);
	}else if(key1=="MAnemometer"){
		String[] titles = new String[Manemometer.length];
		  for (int i = 0; i < Manemometer.length; i++) {
			  titles[i] = "最大风速"+(i+1);
		  }
		
		  int count = 0;
		 
	      for (int i = 0; i < monitorlist.size(); i++) {
				mon = monitorlist.get(i); 
				
				if (monitorlist.get(0).getTime().substring(0, 10).equals(monitorlist.get(i).getTime().substring(0, 10))) {
					int h = Integer.valueOf(mon.getTime().substring(11, 13));//小时
					int m = Integer.valueOf(mon.getTime().substring(14, 15));//分钟：0
					
					 Calendar c = Calendar.getInstance(Locale.CHINA);
					  /*int mDay = c.get(Calendar.DAY_OF_MONTH);//获取当前月份的日期号码 
					  
					  int d = Integer.valueOf(mon.getTime().substring(8, 10));*/
				
					if(1 == h && m == 0){
						data[count]=1;
						
						arraylist.add(mon.getMAnemometer());
						
						count++;
					}else if(2 == h && m == 0){
						data[count]=2;
						arraylist.add(mon.getMAnemometer());
						count++;
					}else if(3 == h && m == 0){
						data[count]=3;
						arraylist.add(mon.getMAnemometer());
						count++;
					}else if(4 == h && m == 0){
						data[count]=4;
						arraylist.add(mon.getMAnemometer());
						count++;
					}else if(5 == h && m == 0){
						data[count]=5;
						arraylist.add(mon.getMAnemometer());
						count++;
					}else if(6 == h && m == 0){
						data[count]=6;
						arraylist.add(mon.getMAnemometer());
						count++;
					}else if(7 == h && m == 0){
						data[count]=7;
						arraylist.add(mon.getMAnemometer());
						count++;
					}else if(8 == h && m == 0){
						data[count]=8;
						arraylist.add(mon.getMAnemometer());
						count++;
					}else if(9 == h && m == 0){
						data[count]=9;
						arraylist.add(mon.getMAnemometer());
						count++;
					}else if(10 == h && m == 0){
						data[count]=10;
						arraylist.add(mon.getMAnemometer());
						count++;
					}else if(11 == h && m == 0){
						data[count]=11;
						arraylist.add(mon.getMAnemometer());
						count++;
					}else if(12 == h && m == 0){
						data[count]=12;
						arraylist.add(mon.getMAnemometer());
						count++;
					}else if(13 == h && m == 0){
						data[count]=13;
						arraylist.add(mon.getMAnemometer());
						count++;
					}else if(14 == h && m == 0){
						data[count]=14;
						arraylist.add(mon.getMAnemometer());
						count++;
					}else if(15 == h && m == 0){
						data[count]=15;
						arraylist.add(mon.getMAnemometer());
						count++;
					}else if(16 == h && m == 0){
						data[count]=16;
						arraylist.add(mon.getMAnemometer());
						count++;
					}else if(17 == h && m == 0){
						data[count]=17;
						arraylist.add(mon.getMAnemometer());
						count++;
					}else if(18 == h && m == 0){
						data[count]=18;
						arraylist.add(mon.getMAnemometer());
						count++;
					}else if(19 == h && m == 0){
						data[count]=19;
						arraylist.add(mon.getMAnemometer());
						count++;
					}else if(20 == h && m == 0){
						data[count]=20;
						arraylist.add(mon.getMAnemometer());
						count++;
					}else if(21 == h && m == 0){
						data[count]=21;
						arraylist.add(mon.getMAnemometer());
						count++;
					}else if(22 == h && m == 0){
						data[count]=22;
						arraylist.add(mon.getMAnemometer());
						count++;
					}else if(23 == h && m == 0){
						data[count]=23;
						arraylist.add(mon.getMAnemometer());
						count++;
					}else if(0 == h && m == 0){
						data[count]=0;
						arraylist.add(mon.getMAnemometer());
						count++;
					}
				
				}
				
				
				
	      }	
	      
	      List<double[]> values = new ArrayList<double[]>();//点集的y坐标
	      for (int i = 0; i < titles.length; i++) {
	    	  x.add(data);
	    	  double[] detaildatadata = new double[arraylist.size()];
	    	  for (int j = 0; j < arraylist.size(); j++) {
	    		  detaildata = (String[]) arraylist.get(j);
	    		  detaildatadata[j] = Double.parseDouble(detaildata[i]);
	    		  
	    	  }
	    	  values.add(detaildatadata);
	    	  
	    	  /*maxdata = detaildatadata[0];
	    	  mindata = detaildatadata[0];*/
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]>maxdata){

	    			  maxdata=detaildatadata[j];

	    		  }
              }
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]<mindata){

	    			  mindata=detaildatadata[j];

	    		  }
              }
	      }
	   
	      
	    //每条曲线点的颜色
		    int[] colors = new int[titles.length];
		    for (int i = 0; i < titles.length; i++) {
		    	colors[i]=chartcolors[i];
		    }
		    //每条曲线点的形状
		    PointStyle[] styles = new PointStyle[titles.length] ;
		    for (int i = 0; i < titles.length; i++) {
		    	styles[i]=chartstyles[i];
		    }
	      
	    
	    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
	    int length = renderer.getSeriesRendererCount();
	    for (int i = 0; i < length; i++) {
	      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
	    }
	    
	    setChartSettings(renderer, "", "时间", "ms-1", 0, 24, mindata-10, maxdata+10,
	    		 Color.BLACK, Color.BLACK);
	    renderer.setMarginsColor(Color.WHITE);
	    renderer.setZoomEnabled(false, false);//设置不允许放大缩小. 
	    renderer.setPanEnabled(false, false);//设置是否可以平移
	    renderer.setLabelsTextSize(20);    //数轴刻度字体大小
	    renderer.setAxisTitleTextSize(20); 
	    renderer.setXLabels(12);
	    renderer.setYLabels(10);
	    renderer.setShowGrid(true);
	    renderer.setXLabelsAlign(Align.RIGHT);
	    renderer.setYLabelsAlign(Align.RIGHT);
	    //renderer.setZoomButtonsVisible(true);
	    //renderer.setPanLimits(new double[] { 0, 25, -10,  50});
	    //renderer.setZoomLimits(new double[] { 0, 25, -10, 50 });
	    
	    view = ChartFactory.getCubeLineChartView(context, buildDataset(titles, x, values),
		        renderer, 0.33f);
	}else if(key1=="FengshiKE"){
		String[] titles = new String[fengshiKE.length];
		  for (int i = 0; i < fengshiKE.length; i++) {
			  titles[i] = "风腐蚀KE"+(i+1);
		  }
		
		  int count = 0;
		 
	      for (int i = 0; i < monitorlist.size(); i++) {
				mon = monitorlist.get(i); 
				
				if (monitorlist.get(0).getTime().substring(0, 10).equals(monitorlist.get(i).getTime().substring(0, 10))) {
					int h = Integer.valueOf(mon.getTime().substring(11, 13));//小时
					int m = Integer.valueOf(mon.getTime().substring(14, 15));//分钟：0
					
					 Calendar c = Calendar.getInstance(Locale.CHINA);
					  /*int mDay = c.get(Calendar.DAY_OF_MONTH);//获取当前月份的日期号码 
					  
					  int d = Integer.valueOf(mon.getTime().substring(8, 10));*/
				
					if(1 == h && m == 0){
						data[count]=1;
						
						arraylist.add(mon.getFengshiKE());
						
						count++;
					}else if(2 == h && m == 0){
						data[count]=2;
						arraylist.add(mon.getFengshiKE());
						count++;
					}else if(3 == h && m == 0){
						data[count]=3;
						arraylist.add(mon.getFengshiKE());
						count++;
					}else if(4 == h && m == 0){
						data[count]=4;
						arraylist.add(mon.getFengshiKE());
						count++;
					}else if(5 == h && m == 0){
						data[count]=5;
						arraylist.add(mon.getFengshiKE());
						count++;
					}else if(6 == h && m == 0){
						data[count]=6;
						arraylist.add(mon.getFengshiKE());
						count++;
					}else if(7 == h && m == 0){
						data[count]=7;
						arraylist.add(mon.getFengshiKE());
						count++;
					}else if(8 == h && m == 0){
						data[count]=8;
						arraylist.add(mon.getFengshiKE());
						count++;
					}else if(9 == h && m == 0){
						data[count]=9;
						arraylist.add(mon.getFengshiKE());
						count++;
					}else if(10 == h && m == 0){
						data[count]=10;
						arraylist.add(mon.getFengshiKE());
						count++;
					}else if(11 == h && m == 0){
						data[count]=11;
						arraylist.add(mon.getFengshiKE());
						count++;
					}else if(12 == h && m == 0){
						data[count]=12;
						arraylist.add(mon.getFengshiKE());
						count++;
					}else if(13 == h && m == 0){
						data[count]=13;
						arraylist.add(mon.getFengshiKE());
						count++;
					}else if(14 == h && m == 0){
						data[count]=14;
						arraylist.add(mon.getFengshiKE());
						count++;
					}else if(15 == h && m == 0){
						data[count]=15;
						arraylist.add(mon.getFengshiKE());
						count++;
					}else if(16 == h && m == 0){
						data[count]=16;
						arraylist.add(mon.getFengshiKE());
						count++;
					}else if(17 == h && m == 0){
						data[count]=17;
						arraylist.add(mon.getFengshiKE());
						count++;
					}else if(18 == h && m == 0){
						data[count]=18;
						arraylist.add(mon.getFengshiKE());
						count++;
					}else if(19 == h && m == 0){
						data[count]=19;
						arraylist.add(mon.getFengshiKE());
						count++;
					}else if(20 == h && m == 0){
						data[count]=20;
						arraylist.add(mon.getFengshiKE());
						count++;
					}else if(21 == h && m == 0){
						data[count]=21;
						arraylist.add(mon.getFengshiKE());
						count++;
					}else if(22 == h && m == 0){
						data[count]=22;
						arraylist.add(mon.getFengshiKE());
						count++;
					}else if(23 == h && m == 0){
						data[count]=23;
						arraylist.add(mon.getFengshiKE());
						count++;
					}else if(0 == h && m == 0){
						data[count]=0;
						arraylist.add(mon.getFengshiKE());
						count++;
					}
				}
				
			
				
	      }	
	      
	      List<double[]> values = new ArrayList<double[]>();//点集的y坐标
	      for (int i = 0; i < titles.length; i++) {
	    	  x.add(data);
	    	  double[] detaildatadata = new double[arraylist.size()];
	    	  for (int j = 0; j < arraylist.size(); j++) {
	    		  detaildata = (String[]) arraylist.get(j);
	    		  detaildatadata[j] = Double.parseDouble(detaildata[i]);
	    		  
	    	  }
	    	  values.add(detaildatadata);
	    	  
	    	  /*maxdata = detaildatadata[0];
	    	  mindata = detaildatadata[0];*/
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]>maxdata){

	    			  maxdata=detaildatadata[j];

	    		  }
              }
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]<mindata){

	    			  mindata=detaildatadata[j];

	    		  }
              }
	      }
	   
	      
	    //每条曲线点的颜色
		    int[] colors = new int[titles.length];
		    for (int i = 0; i < titles.length; i++) {
		    	colors[i]=chartcolors[i];
		    }
		    //每条曲线点的形状
		    PointStyle[] styles = new PointStyle[titles.length] ;
		    for (int i = 0; i < titles.length; i++) {
		    	styles[i]=chartstyles[i];
		    }
	      
	    
	    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
	    int length = renderer.getSeriesRendererCount();
	    for (int i = 0; i < length; i++) {
	      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
	    }
	    
	    setChartSettings(renderer, "", "时间", "J", 0, 24, mindata-100, maxdata+500,
	    		 Color.BLACK, Color.BLACK);
	    renderer.setMarginsColor(Color.WHITE);
	    renderer.setZoomEnabled(false, false);//设置不允许放大缩小. 
	    renderer.setPanEnabled(false, false);//设置是否可以平移
	    renderer.setLabelsTextSize(20);    //数轴刻度字体大小
	    renderer.setAxisTitleTextSize(20); 
	    renderer.setXLabels(12);
	    renderer.setYLabels(10);
	    renderer.setShowGrid(true);
	    renderer.setXLabelsAlign(Align.RIGHT);
	    renderer.setYLabelsAlign(Align.RIGHT);
	    //renderer.setZoomButtonsVisible(true);
	    //renderer.setPanLimits(new double[] { 0, 25, -10,  100000});
	    //renderer.setZoomLimits(new double[] { 0, 25, -10, 100000 });
	    
	    view = ChartFactory.getCubeLineChartView(context, buildDataset(titles, x, values),
		        renderer, 0.33f);
	}else if(key1=="Jingfushe"){
		String[] titles = new String[jingfushe.length];
		  for (int i = 0; i < jingfushe.length; i++) {
			  titles[i] = "净辐射"+(i+1);
		  }
		
		  int count = 0;
		 
	      for (int i = 0; i < monitorlist.size(); i++) {
				mon = monitorlist.get(i); 
				
				if (monitorlist.get(0).getTime().substring(0, 10).equals(monitorlist.get(i).getTime().substring(0, 10))) {
					int h = Integer.valueOf(mon.getTime().substring(11, 13));//小时
					int m = Integer.valueOf(mon.getTime().substring(14, 15));//分钟：0
					
					 Calendar c = Calendar.getInstance(Locale.CHINA);
					  /*int mDay = c.get(Calendar.DAY_OF_MONTH);//获取当前月份的日期号码 
					  
					  int d = Integer.valueOf(mon.getTime().substring(8, 10));*/
				
					if(1 == h && m == 0){
						data[count]=1;
						
						arraylist.add(mon.getJingfushe());
						
						count++;
					}else if(2 == h && m == 0){
						data[count]=2;
						arraylist.add(mon.getJingfushe());
						count++;
					}else if(3 == h && m == 0){
						data[count]=3;
						arraylist.add(mon.getJingfushe());
						count++;
					}else if(4 == h && m == 0){
						data[count]=4;
						arraylist.add(mon.getJingfushe());
						count++;
					}else if(5 == h && m == 0){
						data[count]=5;
						arraylist.add(mon.getJingfushe());
						count++;
					}else if(6 == h && m == 0){
						data[count]=6;
						arraylist.add(mon.getJingfushe());
						count++;
					}else if(7 == h && m == 0){
						data[count]=7;
						arraylist.add(mon.getJingfushe());
						count++;
					}else if(8 == h && m == 0){
						data[count]=8;
						arraylist.add(mon.getJingfushe());
						count++;
					}else if(9 == h && m == 0){
						data[count]=9;
						arraylist.add(mon.getJingfushe());
						count++;
					}else if(10 == h && m == 0){
						data[count]=10;
						arraylist.add(mon.getJingfushe());
						count++;
					}else if(11 == h && m == 0){
						data[count]=11;
						arraylist.add(mon.getJingfushe());
						count++;
					}else if(12 == h && m == 0){
						data[count]=12;
						arraylist.add(mon.getJingfushe());
						count++;
					}else if(13 == h && m == 0){
						data[count]=13;
						arraylist.add(mon.getJingfushe());
						count++;
					}else if(14 == h && m == 0){
						data[count]=14;
						arraylist.add(mon.getJingfushe());
						count++;
					}else if(15 == h && m == 0){
						data[count]=15;
						arraylist.add(mon.getJingfushe());
						count++;
					}else if(16 == h && m == 0){
						data[count]=16;
						arraylist.add(mon.getJingfushe());
						count++;
					}else if(17 == h && m == 0){
						data[count]=17;
						arraylist.add(mon.getJingfushe());
						count++;
					}else if(18 == h && m == 0){
						data[count]=18;
						arraylist.add(mon.getJingfushe());
						count++;
					}else if(19 == h && m == 0){
						data[count]=19;
						arraylist.add(mon.getJingfushe());
						count++;
					}else if(20 == h && m == 0){
						data[count]=20;
						arraylist.add(mon.getJingfushe());
						count++;
					}else if(21 == h && m == 0){
						data[count]=21;
						arraylist.add(mon.getJingfushe());
						count++;
					}else if(22 == h && m == 0){
						data[count]=22;
						arraylist.add(mon.getJingfushe());
						count++;
					}else if(23 == h && m == 0){
						data[count]=23;
						arraylist.add(mon.getJingfushe());
						count++;
					}else if(0 == h && m == 0){
						data[count]=0;
						arraylist.add(mon.getJingfushe());
						count++;
					}
				
				}
				
				
	      }	
	      
	      List<double[]> values = new ArrayList<double[]>();//点集的y坐标
	      for (int i = 0; i < titles.length; i++) {
	    	  x.add(data);
	    	  double[] detaildatadata = new double[arraylist.size()];
	    	  for (int j = 0; j < arraylist.size(); j++) {
	    		  detaildata = (String[]) arraylist.get(j);
	    		  detaildatadata[j] = Double.parseDouble(detaildata[i]);
	    	 }
	    	  values.add(detaildatadata);
	    	  
	    	  /*maxdata = detaildatadata[0];
	    	  mindata = detaildatadata[0];*/
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]>maxdata){

	    			  maxdata=detaildatadata[j];

	    		  }
              }
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]<mindata){

	    			  mindata=detaildatadata[j];

	    		  }
              }
	      }
	   
	      
	    //每条曲线点的颜色
		    int[] colors = new int[titles.length];
		    for (int i = 0; i < titles.length; i++) {
		    	colors[i]=chartcolors[i];
		    }
		    //每条曲线点的形状
		    PointStyle[] styles = new PointStyle[titles.length] ;
		    for (int i = 0; i < titles.length; i++) {
		    	styles[i]=chartstyles[i];
		    }
	      
	    
	    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
	    int length = renderer.getSeriesRendererCount();
	    for (int i = 0; i < length; i++) {
	      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
	    }
	    
	    setChartSettings(renderer, "", "时间", "wm-2", 0, 24, mindata-100, maxdata+100,
	    		 Color.BLACK, Color.BLACK);
	    renderer.setMarginsColor(Color.WHITE);
	    renderer.setZoomEnabled(false, false);//设置不允许放大缩小. 
	    renderer.setPanEnabled(false, false);//设置是否可以平移
	    renderer.setLabelsTextSize(20);    //数轴刻度字体大小
	    renderer.setAxisTitleTextSize(20); 
	    renderer.setXLabels(12);
	    renderer.setYLabels(10);
	    renderer.setShowGrid(true);
	    renderer.setXLabelsAlign(Align.RIGHT);
	    renderer.setYLabelsAlign(Align.RIGHT);
	    //renderer.setZoomButtonsVisible(true);
	    //renderer.setPanLimits(new double[] { 0, 25, -10,  2000});
	    //renderer.setZoomLimits(new double[] { 0, 25, -10, -2000 });
	    
	    view = ChartFactory.getCubeLineChartView(context, buildDataset(titles, x, values),
		        renderer, 0.33f);
	}else if(key1=="Guangliangdu"){
		String[] titles = new String[guangliangdu.length];
		  for (int i = 0; i < guangliangdu.length; i++) {
			  titles[i] = "光亮度"+(i+1);
		  }
		
		  int count = 0;
		 
	      for (int i = 0; i < monitorlist.size(); i++) {
				mon = monitorlist.get(i); 
				
				if (monitorlist.get(0).getTime().substring(0, 10).equals(monitorlist.get(i).getTime().substring(0, 10))) {
					int h = Integer.valueOf(mon.getTime().substring(11, 13));//小时
					int m = Integer.valueOf(mon.getTime().substring(14, 15));//分钟：0
					
					 Calendar c = Calendar.getInstance(Locale.CHINA);
					 /* int mDay = c.get(Calendar.DAY_OF_MONTH);//获取当前月份的日期号码 
					  
					  int d = Integer.valueOf(mon.getTime().substring(8, 10));*/
				
					if(1 == h && m == 0){
						data[count]=1;
						
						arraylist.add(mon.getGuangliangdu());
						
						count++;
					}else if(2 == h && m == 0){
						data[count]=2;
						arraylist.add(mon.getGuangliangdu());
						count++;
					}else if(3 == h && m == 0){
						data[count]=3;
						arraylist.add(mon.getSoilTemprature());
						count++;
					}else if(4 == h && m == 0){
						data[count]=4;
						arraylist.add(mon.getGuangliangdu());
						count++;
					}else if(5 == h && m == 0){
						data[count]=5;
						arraylist.add(mon.getGuangliangdu());
						count++;
					}else if(6 == h && m == 0){
						data[count]=6;
						arraylist.add(mon.getGuangliangdu());
						count++;
					}else if(7 == h && m == 0){
						data[count]=7;
						arraylist.add(mon.getGuangliangdu());
						count++;
					}else if(8 == h && m == 0){
						data[count]=8;
						arraylist.add(mon.getGuangliangdu());
						count++;
					}else if(9 == h && m == 0){
						data[count]=9;
						arraylist.add(mon.getGuangliangdu());
						count++;
					}else if(10 == h && m == 0){
						data[count]=10;
						arraylist.add(mon.getGuangliangdu());
						count++;
					}else if(11 == h && m == 0){
						data[count]=11;
						arraylist.add(mon.getGuangliangdu());
						count++;
					}else if(12 == h && m == 0){
						data[count]=12;
						arraylist.add(mon.getGuangliangdu());
						count++;
					}else if(13 == h && m == 0){
						data[count]=13;
						arraylist.add(mon.getGuangliangdu());
						count++;
					}else if(14 == h && m == 0){
						data[count]=14;
						arraylist.add(mon.getGuangliangdu());
						count++;
					}else if(15 == h && m == 0){
						data[count]=15;
						arraylist.add(mon.getGuangliangdu());
						count++;
					}else if(16 == h && m == 0){
						data[count]=16;
						arraylist.add(mon.getGuangliangdu());
						count++;
					}else if(17 == h && m == 0){
						data[count]=17;
						arraylist.add(mon.getGuangliangdu());
						count++;
					}else if(18 == h && m == 0){
						data[count]=18;
						arraylist.add(mon.getGuangliangdu());
						count++;
					}else if(19 == h && m == 0){
						data[count]=19;
						arraylist.add(mon.getGuangliangdu());
						count++;
					}else if(20 == h && m == 0){
						data[count]=20;
						arraylist.add(mon.getGuangliangdu());
						count++;
					}else if(21 == h && m == 0){
						data[count]=21;
						arraylist.add(mon.getGuangliangdu());
						count++;
					}else if(22 == h && m == 0){
						data[count]=22;
						arraylist.add(mon.getGuangliangdu());
						count++;
					}else if(23 == h && m == 0){
						data[count]=23;
						arraylist.add(mon.getGuangliangdu());
						count++;
					}else if(0 == h && m == 0){
						data[count]=0;
						arraylist.add(mon.getGuangliangdu());
						count++;
					}
				}
				
			
	      }	
	      
	      List<double[]> values = new ArrayList<double[]>();//点集的y坐标
	      for (int i = 0; i < titles.length; i++) {
	    	  x.add(data);
	    	  double[] detaildatadata = new double[arraylist.size()];
	    	  for (int j = 0; j < arraylist.size(); j++) {
	    		  detaildata = (String[]) arraylist.get(j);
	    		  detaildatadata[j] = Double.parseDouble(detaildata[i]);
	    		  
	    	  }
	    	  values.add(detaildatadata);
	    	  
	    	  /*maxdata = detaildatadata[0];
	    	  mindata = detaildatadata[0];*/
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]>maxdata){

	    			  maxdata=detaildatadata[j];

	    		  }
              }
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]<mindata){

	    			  mindata=detaildatadata[j];

	    		  }
              }
	      }
	   
	      
	    //每条曲线点的颜色
		    int[] colors = new int[titles.length];
		    for (int i = 0; i < titles.length; i++) {
		    	colors[i]=chartcolors[i];
		    }
		    //每条曲线点的形状
		    PointStyle[] styles = new PointStyle[titles.length] ;
		    for (int i = 0; i < titles.length; i++) {
		    	styles[i]=chartstyles[i];
		    }
	      
	    
	    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
	    int length = renderer.getSeriesRendererCount();
	    for (int i = 0; i < length; i++) {
	      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
	    }
	    
	    setChartSettings(renderer, "", "时间", "klux", 0, 24, mindata-20, maxdata+50,
	    		 Color.BLACK, Color.BLACK);
	    renderer.setMarginsColor(Color.WHITE);
	    renderer.setZoomEnabled(false, false);//设置不允许放大缩小. 
	    renderer.setPanEnabled(false, false);//设置是否可以平移
	    renderer.setLabelsTextSize(20);    //数轴刻度字体大小
	    renderer.setAxisTitleTextSize(20); 
	    renderer.setXLabels(12);
	    renderer.setYLabels(10);
	    renderer.setShowGrid(true);
	    renderer.setXLabelsAlign(Align.RIGHT);
	    renderer.setYLabelsAlign(Align.RIGHT);
	    //renderer.setZoomButtonsVisible(true);
	    //renderer.setPanLimits(new double[] { 0, 25, -10,  500});
	    //renderer.setZoomLimits(new double[] { 0, 25, -10, 500});
	    
	    view = ChartFactory.getCubeLineChartView(context, buildDataset(titles, x, values),
		        renderer, 0.33f);
	}else if(key1=="oilpH"){
		String[] titles = new String[oilPH.length];
		  for (int i = 0; i < oilPH.length; i++) {
			  titles[i] = "土壤pH"+(i+1);
		  }
		
		  int count = 0;
		 
	      for (int i = 0; i < monitorlist.size(); i++) {
				mon = monitorlist.get(i); 
				
				if (monitorlist.get(0).getTime().substring(0, 10).equals(monitorlist.get(i).getTime().substring(0, 10))) {
					int h = Integer.valueOf(mon.getTime().substring(11, 13));//小时
					int m = Integer.valueOf(mon.getTime().substring(14, 15));//分钟：0
					
					 Calendar c = Calendar.getInstance(Locale.CHINA);
					  /*int mDay = c.get(Calendar.DAY_OF_MONTH);//获取当前月份的日期号码 
					  
					  int d = Integer.valueOf(mon.getTime().substring(8, 10));*/
				
					if(1 == h && m == 0){
						data[count]=1;
						
						arraylist.add(mon.getOilpH());
						
						count++;
					}else if(2 == h && m == 0){
						data[count]=2;
						arraylist.add(mon.getOilpH());
						count++;
					}else if(3 == h && m == 0){
						data[count]=3;
						arraylist.add(mon.getOilpH());
						count++;
					}else if(4 == h && m == 0){
						data[count]=4;
						arraylist.add(mon.getOilpH());
						count++;
					}else if(5 == h && m == 0){
						data[count]=5;
						arraylist.add(mon.getOilpH());
						count++;
					}else if(6 == h && m == 0){
						data[count]=6;
						arraylist.add(mon.getOilpH());
						count++;
					}else if(7 == h && m == 0){
						data[count]=7;
						arraylist.add(mon.getOilpH());
						count++;
					}else if(8 == h && m == 0){
						data[count]=8;
						arraylist.add(mon.getOilpH());
						count++;
					}else if(9 == h && m == 0){
						data[count]=9;
						arraylist.add(mon.getOilpH());
						count++;
					}else if(10 == h && m == 0){
						data[count]=10;
						arraylist.add(mon.getOilpH());
						count++;
					}else if(11 == h && m == 0){
						data[count]=11;
						arraylist.add(mon.getOilpH());
						count++;
					}else if(12 == h && m == 0){
						data[count]=12;
						arraylist.add(mon.getOilpH());
						count++;
					}else if(13 == h && m == 0){
						data[count]=13;
						arraylist.add(mon.getOilpH());
						count++;
					}else if(14 == h && m == 0){
						data[count]=14;
						arraylist.add(mon.getOilpH());
						count++;
					}else if(15 == h && m == 0){
						data[count]=15;
						arraylist.add(mon.getOilpH());
						count++;
					}else if(16 == h && m == 0){
						data[count]=16;
						arraylist.add(mon.getOilpH());
						count++;
					}else if(17 == h && m == 0){
						data[count]=17;
						arraylist.add(mon.getOilpH());
						count++;
					}else if(18 == h && m == 0){
						data[count]=18;
						arraylist.add(mon.getOilpH());
						count++;
					}else if(19 == h && m == 0){
						data[count]=19;
						arraylist.add(mon.getOilpH());
						count++;
					}else if(20 == h && m == 0){
						data[count]=20;
						arraylist.add(mon.getOilpH());
						count++;
					}else if(21 == h && m == 0){
						data[count]=21;
						arraylist.add(mon.getOilpH());
						count++;
					}else if(22 == h && m == 0){
						data[count]=22;
						arraylist.add(mon.getOilpH());
						count++;
					}else if(23 == h && m == 0){
						data[count]=23;
						arraylist.add(mon.getOilpH());
						count++;
					}else if(0 == h && m == 0){
						data[count]=0;
						arraylist.add(mon.getOilpH());
						count++;
					}
				
				}
				
				
	      }	
	      
	      List<double[]> values = new ArrayList<double[]>();//点集的y坐标
	      for (int i = 0; i < titles.length; i++) {
	    	  x.add(data);
	    	  double[] detaildatadata = new double[arraylist.size()];
	    	  for (int j = 0; j < arraylist.size(); j++) {
	    		  detaildata = (String[]) arraylist.get(j);
	    		  detaildatadata[j] = Double.parseDouble(detaildata[i]);
	    		  
	    	  }
	    	  values.add(detaildatadata);
	    	  /*maxdata = detaildatadata[0];
	    	  mindata = detaildatadata[0];*/
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]>maxdata){

	    			  maxdata=detaildatadata[j];

	    		  }
              }
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]<mindata){

	    			  mindata=detaildatadata[j];

	    		  }
              }
	      }
	   
	      
	    //每条曲线点的颜色
		    int[] colors = new int[titles.length];
		    for (int i = 0; i < titles.length; i++) {
		    	colors[i]=chartcolors[i];
		    }
		    //每条曲线点的形状
		    PointStyle[] styles = new PointStyle[titles.length] ;
		    for (int i = 0; i < titles.length; i++) {
		    	styles[i]=chartstyles[i];
		    }
	      
	    
	    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
	    int length = renderer.getSeriesRendererCount();
	    for (int i = 0; i < length; i++) {
	      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
	    }
	    
	    setChartSettings(renderer, "", "时间", "T", 0, 24, mindata-10, maxdata+10,
	    		 Color.BLACK, Color.BLACK);
	    renderer.setMarginsColor(Color.WHITE);
	    renderer.setZoomEnabled(false, false);//设置不允许放大缩小. 
	    renderer.setPanEnabled(false, false);//设置是否可以平移
	    renderer.setLabelsTextSize(20);    //数轴刻度字体大小
	    renderer.setAxisTitleTextSize(20); 
	    renderer.setXLabels(12);
	    renderer.setYLabels(10);
	    renderer.setShowGrid(true);
	    renderer.setXLabelsAlign(Align.RIGHT);
	    renderer.setYLabelsAlign(Align.RIGHT);
	    //renderer.setZoomButtonsVisible(true);
	    //renderer.setPanLimits(new double[] { 0, 25, -10,  40});
	    //renderer.setZoomLimits(new double[] { 0, 25, -10, 40 });
	    
	    view = ChartFactory.getCubeLineChartView(context, buildDataset(titles, x, values),
		        renderer, 0.33f);
	}else if(key1=="SoilHeatFlux"){
		String[] titles = new String[SoilHeatFlux.length];
		  for (int i = 0; i < SoilHeatFlux.length; i++) {
			  titles[i] = "土壤热通量"+(i+1);
		  }
		
		  int count = 0;
		 
	      for (int i = 0; i < monitorlist.size(); i++) {
				mon = monitorlist.get(i); 
				
				if (monitorlist.get(0).getTime().substring(0, 10).equals(monitorlist.get(i).getTime().substring(0, 10))) {
					int h = Integer.valueOf(mon.getTime().substring(11, 13));//小时
					int m = Integer.valueOf(mon.getTime().substring(14, 15));//分钟：0
					
					 Calendar c = Calendar.getInstance(Locale.CHINA);
					  /*int mDay = c.get(Calendar.DAY_OF_MONTH);//获取当前月份的日期号码 
					  
					  int d = Integer.valueOf(mon.getTime().substring(8, 10));*/
				
					if(1 == h && m == 0){
						data[count]=1;
						
						arraylist.add(mon.getSoilHeatFlux());
						
						count++;
					}else if(2 == h && m == 0){
						data[count]=2;
						arraylist.add(mon.getSoilHeatFlux());
						count++;
					}else if(3 == h && m == 0){
						data[count]=3;
						arraylist.add(mon.getSoilHeatFlux());
						count++;
					}else if(4 == h && m == 0){
						data[count]=4;
						arraylist.add(mon.getSoilHeatFlux());
						count++;
					}else if(5 == h && m == 0){
						data[count]=5;
						arraylist.add(mon.getSoilHeatFlux());
						count++;
					}else if(6 == h && m == 0){
						data[count]=6;
						arraylist.add(mon.getSoilHeatFlux());
						count++;
					}else if(7 == h && m == 0){
						data[count]=7;
						arraylist.add(mon.getSoilHeatFlux());
						count++;
					}else if(8 == h && m == 0){
						data[count]=8;
						arraylist.add(mon.getSoilHeatFlux());
						count++;
					}else if(9 == h && m == 0){
						data[count]=9;
						arraylist.add(mon.getSoilHeatFlux());
						count++;
					}else if(10 == h && m == 0){
						data[count]=10;
						arraylist.add(mon.getSoilHeatFlux());
						count++;
					}else if(11 == h && m == 0){
						data[count]=11;
						arraylist.add(mon.getSoilHeatFlux());
						count++;
					}else if(12 == h && m == 0){
						data[count]=12;
						arraylist.add(mon.getSoilHeatFlux());
						count++;
					}else if(13 == h && m == 0){
						data[count]=13;
						arraylist.add(mon.getSoilHeatFlux());
						count++;
					}else if(14 == h && m == 0){
						data[count]=14;
						arraylist.add(mon.getSoilHeatFlux());
						count++;
					}else if(15 == h && m == 0){
						data[count]=15;
						arraylist.add(mon.getSoilHeatFlux());
						count++;
					}else if(16 == h && m == 0){
						data[count]=16;
						arraylist.add(mon.getSoilHeatFlux());
						count++;
					}else if(17 == h && m == 0){
						data[count]=17;
						arraylist.add(mon.getSoilHeatFlux());
						count++;
					}else if(18 == h && m == 0){
						data[count]=18;
						arraylist.add(mon.getSoilHeatFlux());
						count++;
					}else if(19 == h && m == 0){
						data[count]=19;
						arraylist.add(mon.getSoilHeatFlux());
						count++;
					}else if(20 == h && m == 0){
						data[count]=20;
						arraylist.add(mon.getSoilHeatFlux());
						count++;
					}else if(21 == h && m == 0){
						data[count]=21;
						arraylist.add(mon.getSoilHeatFlux());
						count++;
					}else if(22 == h && m == 0){
						data[count]=22;
						arraylist.add(mon.getSoilHeatFlux());
						count++;
					}else if(23 == h && m == 0){
						data[count]=23;
						arraylist.add(mon.getSoilHeatFlux());
						count++;
					}else if(0 == h && m == 0){
						data[count]=0;
						arraylist.add(mon.getSoilHeatFlux());
						count++;
					}
				}
				
			
				
	      }	
	      
	      List<double[]> values = new ArrayList<double[]>();//点集的y坐标
	      for (int i = 0; i < titles.length; i++) {
	    	  x.add(data);
	    	  double[] detaildatadata = new double[arraylist.size()];
	    	  for (int j = 0; j < arraylist.size(); j++) {
	    		  detaildata = (String[]) arraylist.get(j);
	    		  detaildatadata[j] = Double.parseDouble(detaildata[i]);
	    		  
	    	  }
	    	  values.add(detaildatadata);
	    	  
	    	  /*maxdata = detaildatadata[0];
	    	  mindata = detaildatadata[0];*/
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]>maxdata){

	    			  maxdata=detaildatadata[j];

	    		  }
              }
	    	  for (int j = 0; j < detaildatadata.length; j++) {
	    		  if(detaildatadata[j]<mindata){

	    			  mindata=detaildatadata[j];

	    		  }
              }
	      }
	   
	      
	    //每条曲线点的颜色
		    int[] colors = new int[titles.length];
		    for (int i = 0; i < titles.length; i++) {
		    	colors[i]=chartcolors[i];
		    }
		    //每条曲线点的形状
		    PointStyle[] styles = new PointStyle[titles.length] ;
		    for (int i = 0; i < titles.length; i++) {
		    	styles[i]=chartstyles[i];
		    }
	      
	    
	    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
	    int length = renderer.getSeriesRendererCount();
	    for (int i = 0; i < length; i++) {
	      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
	    }
	    
	    setChartSettings(renderer, "", "时间", "w/m-2", 0, 24, mindata-100, maxdata+200,
	    		 Color.BLACK, Color.BLACK);
	    renderer.setMarginsColor(Color.WHITE);
	    renderer.setZoomEnabled(false, false);//设置不允许放大缩小. 
	    renderer.setPanEnabled(false, false);//设置是否可以平移
	    renderer.setLabelsTextSize(20);    //数轴刻度字体大小
	    renderer.setAxisTitleTextSize(20); 
	    renderer.setXLabels(12);
	    renderer.setYLabels(10);
	    renderer.setShowGrid(true);
	    renderer.setXLabelsAlign(Align.RIGHT);
	    renderer.setYLabelsAlign(Align.RIGHT);
	    //renderer.setZoomButtonsVisible(true);
	    //renderer.setPanLimits(new double[] { 0, 25, -10,  15000});
	    //renderer.setZoomLimits(new double[] { 0, 25, -10, 15000 });
	    
	    view = ChartFactory.getCubeLineChartView(context, buildDataset(titles, x, values),
		        renderer, 0.33f);
	}
    return view;
    
  }



}

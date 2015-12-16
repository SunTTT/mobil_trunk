package com.mobile.publiclass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;



public class PublicHttpActivity {
	
	public static InputStream getJSONData( String url,List<NameValuePair> params) {
		InputStream result = null;
		try {
			result = HttpPost(url, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result = null;			
		} 
		return result;
	}

	/*
	 * 判读当前网络是否可用
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static InputStream HttpPost( String url, List<NameValuePair> params)  {		
		InputStream inputStream = null;
		HttpPost httpRequest = new HttpPost(url);
		
		try {
			// 请求httpRequest
			  httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8)); // 设置参数的编码y);
			  
			  HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest); // 发送请求并获取反馈
			  Log.i("PublicHttpActivity", "" + httpResponse.getStatusLine().getStatusCode());
			  // 解析返回的内容
			  if (httpResponse.getStatusLine().getStatusCode() != 404)
			  {
				  HttpEntity httpEntity = httpResponse.getEntity();
				  Log.e("^^^^^",""+httpEntity.toString());
				  if (httpEntity != null) {
						inputStream = httpEntity.getContent();
						 Log.e("PublicHttpActivity", "inputStream= " + inputStream);
				  }
				    
			  }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return inputStream;
	}

	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(is, "GB2312"),// 防止模拟器上的乱码
					512 * 1024);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			return null;
		}
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			Log.e("DataProvier convertStreamToString", e.getLocalizedMessage(),
					e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				return null;
			}
		}
		return sb.toString();
	}

	public static JSONObject getObject(String jsonString) throws JSONException,
			Exception {
		return new JSONObject(jsonString);
	}

	public static JSONArray getArray(String jsonString) throws JSONException,
			Exception {
		return new JSONArray(jsonString);
	}

	// 将json 数组转换为Map 对象
	public static Map<String, Object> getMap(String jsonString) {
		JSONObject jsonObject;
		Map<String, Object> valueMap = new HashMap<String, Object>();
		try {
			jsonObject = new JSONObject(jsonString);
			Iterator<String> keyIter = jsonObject.keys();
			String key;
			Object value;
			while (keyIter.hasNext()) {
				key = (String) keyIter.next();
				value = jsonObject.get(key);
				valueMap.put(key, value);
			}
			return valueMap;
		} catch (JSONException e) {

			return valueMap;
		}

	}

	// 把json 转换为HashMap
	public static ArrayList<HashMap<String, Object>> getArrayList(
			String jsonString) {
		
		ArrayList<HashMap<String, Object>> hashlist = new ArrayList<HashMap<String, Object>>();
		try {
			
			JSONArray jsonArray = new JSONArray(jsonString);
			Log.v("PublicHttp/ArrayList/jsonArray", "---jsonArray---" + jsonArray);
			
			JSONObject jsonObject;
			String key;
			Object value;

			// Log.i("数组长度：", " "+jsonArray.length());
			for (int i = 0; i < jsonArray.length(); i++) {
				HashMap<String, Object> valueMap = new HashMap<String, Object>();
				jsonObject = jsonArray.getJSONObject(i);
				Iterator keyIter = jsonObject.keys();
				while (keyIter.hasNext()) {
					key = (String) keyIter.next();
					value = jsonObject.get(key);
					valueMap.put(key, value);
				}
				hashlist.add(valueMap);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.i("PublicHttpActivity/@@@@@@@", "## " + e);
			return hashlist;
		}

		return hashlist;
	}

	// 把json 转换为 ArrayList 形式
	public static List<Map<String, Object>> getList(String jsonString) {
		List<Map<String, Object>> list = null;
		try {
			Log.d("jsonString", jsonString);
			JSONArray jsonArray = new JSONArray(jsonString);

			JSONObject jsonObject;
			list = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < jsonArray.length(); i++) {
				jsonObject = jsonArray.getJSONObject(i);
				Log.d("list", jsonObject.toString());
				list.add(getMap(jsonObject.toString()));
			}
		} catch (Exception e) {
			return list;
		}

		return list;
	}

	

	// 把jso n 转换为 List<CItem> 形式
	public static List<NameValuePair> getNameValuePair(String jsonString) {
		List<NameValuePair> list = null;
		JSONObject jsonObject;

		try {
			jsonObject = new JSONObject(jsonString);
			@SuppressWarnings("unchecked")
			Iterator<String> keyIter = jsonObject.keys();
			String key;
			Object value;
			list = new ArrayList<NameValuePair>();
			// BasicNameValuePair valueMap = new BasicNameValuePair(key,(String)
			// value);
			while (keyIter.hasNext()) {
				key = (String) keyIter.next();
				value = jsonObject.get(key).toString();
				BasicNameValuePair valueMap = new BasicNameValuePair(key,
						(String) value);
				list.add(valueMap);
			}
			return list;
		} catch (Exception e) {
			return list;
		}

	}

	

	public static String getCountdown(String Countdown) {
		// int diff ;
		Log.i("", Countdown);
		int diff = Integer.valueOf(Countdown);
		Log.i("", "678567");
		if (diff < 0) {
			Log.i("", "9990");
			diff = 0;
		} else {
			Log.i("", "dfdsf");
			diff = Integer.valueOf(Countdown);
		}
		String countdown;
		int days = (int) diff / 86400;
		diff %= 86400;
		int hours = (int) diff / 3600;
		diff %= 3600;
		int minutes = (int) diff / 60;
		diff %= 60;
		int seconds = diff;

		if (days > 0) {
			countdown = days + "天" + hours + "小时";
		} else {
			if (hours > 0) {
				countdown = hours + "小时" + minutes;
			} else {
				if (minutes > 0) {
					countdown = minutes + "分钟" + seconds + "秒";
				} else {
					if (seconds > 0) {
						countdown = seconds + "秒";
					} else {
						countdown = "已经结束";
					}
				}
			}
		}

		return countdown;
	}

	// 把json 转换为HashMap
	public static ArrayList<HashMap<String, Object>> getliveList(
			String jsonString) {
		ArrayList<HashMap<String, Object>> hashlist = new ArrayList<HashMap<String, Object>>();
		try {
			JSONArray jsonArray = new JSONArray(jsonString);
			JSONObject jsonObject;
			String key;
			Object value;
			String id_key;
			// Log.d("数组长度：", " "+jsonArray.length());
			for (int i = 0; i < jsonArray.length(); i++) {
				HashMap<String, Object> valueMap = new HashMap<String, Object>();
				jsonObject = jsonArray.getJSONObject(i);
				Iterator keyIter = jsonObject.keys();
				while (keyIter.hasNext()) {

					key = (String) keyIter.next();
					value = jsonObject.get(key);
					if (key == "id") {
						// id_key = value ;
					}

					valueMap.put(key, value);
				}
				hashlist.add(valueMap);

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			return hashlist;
		}

		return hashlist;
	}

}

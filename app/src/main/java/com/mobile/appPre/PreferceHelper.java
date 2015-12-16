package com.mobile.appPre;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferceHelper {

	SharedPreferences sp;

	SharedPreferences.Editor editor;

	Context context;

	String XMLNAME;

	public PreferceHelper(Context c, String name) {
		context = c;
		XMLNAME = name;
		sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
		editor = sp.edit();
	}

	public void putValue(String key, String value) {

		editor = sp.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public String getValue(String value) {
		return sp.getString(value, null);
	}

	public void RemoveItem(String key) {
		SharedPreferences s = context.getSharedPreferences(XMLNAME, 0);
		s.edit().remove(key).commit();
	}

	public void ClearData() {
		editor.clear().commit();
	}
}

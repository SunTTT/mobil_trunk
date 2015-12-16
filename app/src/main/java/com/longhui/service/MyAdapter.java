package com.longhui.service;

import java.util.List;

import com.longhui.entity.Movie;
import com.mobile.appPre.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private List<Movie> list;
	Context context;

	public List<Movie> getList() {
		return list;
	}

	public void setList(List<Movie> list) {
		this.list = list;
	}

	public MyAdapter(Context context) {
		this.context = context;
	}

	public int getCount() {
		return list.size();
	}

	public Object getItem(int item) {
		return item;
	}

	public long getItemId(int id) {
		return id;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = null;
		ImageView imageView = null;
		TextView textView = null;

		if (convertView == null) {
			view = inflater.inflate(R.layout.item, null);
		} else {
			view = (View) convertView;
		}
		imageView = (ImageView) view.findViewById(R.id.image);
		textView = (TextView) view.findViewById(R.id.result);

		Movie movie = list.get(position);
		imageView.setImageBitmap(movie.getBitmap());
		textView.setText(movie.getTitle());
		
		return view;
	}

	public Movie getMovie(int id) {
		return list.get(id);
	}
}

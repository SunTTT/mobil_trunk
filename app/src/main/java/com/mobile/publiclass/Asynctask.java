package com.mobile.publiclass;

import android.os.AsyncTask;

public class Asynctask extends AsyncTask<Void, Void, Void> {

	@Override
	protected Void doInBackground(Void... arg0) {
		// TODO Auto-generated method stub
		//ScientificData scientificData = new ScientificData();
		try {
			Thread.sleep(500);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

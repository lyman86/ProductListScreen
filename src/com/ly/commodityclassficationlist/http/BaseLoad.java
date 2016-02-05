package com.ly.commodityclassficationlist.http;

import android.app.Activity;
import android.os.Handler;

import com.ly.commodityclassficationlist.myinterface.OnLoadListener;

public abstract class BaseLoad extends Thread {
	protected OnLoadListener onLoadListener;
	public String url;
	public BaseLoad(String url,Activity activity) {
		this.url = url;
		setOnLoadListener((OnLoadListener) activity);
	}
	public void setOnLoadListener(OnLoadListener onLoadListener){
			this.onLoadListener = onLoadListener;
	}
	
	public Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			handleMyMessage(msg);
		};
	};
	public abstract void handleMyMessage(android.os.Message msg);
	public abstract void load();
	@Override
	public void run() {
		load();
	}
	
	
}

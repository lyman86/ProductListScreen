package com.ly.commodityclassficationlist.http;
import android.app.Activity;
import android.os.Message;
public class LoadProductCategory extends BaseLoad {

	public LoadProductCategory(String url, Activity activity) {
		super(url, activity);
	}
	@Override
	public void handleMyMessage(Message msg) {
		String result = (String) msg.obj;
		if (result!=null) {
			onLoadListener.onLoadSuccess(result, -1);
		}else{
			onLoadListener.onLoadFailed("...");
		}
	}

	@Override
	public void load() {
		    String result = "good";
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Message message = Message.obtain();
			message.obj = result;
			mHandler.sendMessage(message);
	}

}

package com.example.productlistscreen;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

import com.example.productlistscreen.base.BasePopUpWindow;
import com.example.productlistscreen.base.TypePopUpWindow;
import com.example.productlistscreen.data.TestData;
import com.example.productlistscreen.view.FlowLayout;
import com.example.productlistscreen.view.FlowLayout.onTagItemClickListener;

public class MainActivity extends Activity implements OnClickListener,OnDismissListener{
	private LinearLayout typeLlayout;
	private LinearLayout brandLlayout;
	private LinearLayout screenLlayout;
	private LinearLayout sortLlayout;
	
	//private LinearLayout screenTypeLlayout;
	
	private TextView typeTv;
	private TextView brandTv;
	private TextView screenTv;
	private TextView sortTv;
	
	private ImageView typeIv;
	private ImageView brandIv;
	private ImageView screenIv;
	private ImageView sortIv;
	
	private boolean isOpen = false;
	private BasePopUpWindow popUpWindow;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initView();
		initDatas();
		initEvent();

	}

	private void initView() {
		typeLlayout = (LinearLayout) findViewById(R.id.type_llayout);
		brandLlayout = (LinearLayout) findViewById(R.id.brand_llayout);
		screenLlayout = (LinearLayout) findViewById(R.id.screen_llayout);
		sortLlayout = (LinearLayout) findViewById(R.id.sort_llayout);
		//screenTypeLlayout = (LinearLayout) findViewById(R.id.screen_type_llayout);
		
		typeTv = (TextView) findViewById(R.id.type_tv);
		brandTv = (TextView) findViewById(R.id.brand_tv);
		screenTv = (TextView) findViewById(R.id.screen_tv);
		sortTv = (TextView) findViewById(R.id.sort_tv);
		typeIv = (ImageView) findViewById(R.id.type_iv);
		brandIv = (ImageView) findViewById(R.id.brand_iv);
		screenIv = (ImageView) findViewById(R.id.screen_iv);
		sortIv = (ImageView) findViewById(R.id.sort_iv);
	}

	private void initDatas() {
		

	}

	private void initEvent() {
		typeLlayout.setOnClickListener(this);
		brandLlayout.setOnClickListener(this);
		screenLlayout.setOnClickListener(this);
		sortLlayout.setOnClickListener(this);
		
		
	}

	@SuppressLint("NewApi") @Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.type_llayout:
			popUpWindow = new TypePopUpWindow(this);
			popUpWindow.setOnDismissListener(this);
			setOriginAllStatus();
			changeStatus(typeTv,typeIv,R.drawable.select_type);
			
//			typeTv.setTextColor(getResources().getColor(R.color.text_select_color));
//			typeIv.setImageResource(R.drawable.select_type);
			
			break;
		case R.id.brand_llayout:
			setOriginAllStatus();
			brandTv.setTextColor(getResources().getColor(R.color.text_select_color));
			brandIv.setImageResource(R.drawable.select_type);
			break;
		case R.id.screen_llayout:
			setOriginAllStatus();
			screenTv.setTextColor(getResources().getColor(R.color.text_select_color));
			screenIv.setImageResource(R.drawable.select_screen);
			break;
		case R.id.sort_llayout:
			setOriginAllStatus();
			sortTv.setTextColor(getResources().getColor(R.color.text_select_color));
			sortIv.setImageResource(R.drawable.select_sort);
			break;

		}
	}
	
	private void changeStatus(TextView tv,ImageView iv,int res) {
		if (!isOpen) {
			popUpWindow.isSplitTouchEnabled();
			popUpWindow.isOutsideTouchable();
			popUpWindow.showAsDropDown(findViewById(R.id.line_01),0,0);
			//popUpWindow.lightOff(this);
			//popUpWindow.showAtLocation(,Gravity.TOP, 0, 0);
			tv.setTextColor(getResources().getColor(R.color.text_select_color));
			iv.setImageResource(res);
			isOpen = true;
		}else{
			popUpWindow.dismiss();
			//popUpWindow.lightOn(this);
			isOpen = false;
		}
	}

	private void setOriginAllStatus(){
		typeTv.setTextColor(getResources().getColor(R.color.text_unselect_color));
		brandTv.setTextColor(getResources().getColor(R.color.text_unselect_color));
		screenTv.setTextColor(getResources().getColor(R.color.text_unselect_color));
		sortTv.setTextColor(getResources().getColor(R.color.text_unselect_color));
		typeIv.setImageResource(R.drawable.un_select_type);
		brandIv.setImageResource(R.drawable.un_select_type);
		screenIv.setImageResource(R.drawable.unselect_screen);
		sortIv.setImageResource(R.drawable.un_select_sort);
	}

	@Override
	public void onDismiss() {
		setOriginAllStatus();
		//popUpWindow.lightOn(this);
		isOpen = false;
	}

	

}

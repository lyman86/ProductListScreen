package com.example.productlistscreen;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.productlistscreen.data.TestData;
import com.example.productlistscreen.view.FlowLayout;
import com.example.productlistscreen.view.FlowLayout.onTagItemClickListener;

public class MainActivity extends Activity implements OnClickListener,
		onTagItemClickListener {
	private LinearLayout typeLlayout;
	private LinearLayout brandLlayout;
	private LinearLayout screenLlayout;
	private LinearLayout sortLlayout;

	private LinearLayout screenTypeLlayout;
	private FlowLayout screenTypeFlayout;
	private TextView typeTv;
	private TextView brandTv;
	private TextView screenTv;
	private TextView sortTv;

	private ImageView typeIv;
	private ImageView brandIv;
	private ImageView screenIv;
	private ImageView sortIv;
	private boolean tagClick[] = new boolean [4];

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
		screenTypeLlayout = (LinearLayout) findViewById(R.id.screen_type_llayout);
		screenTypeFlayout = (FlowLayout) findViewById(R.id.screen_type_flayout);

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
		screenTypeFlayout.addFlowTag(TestData.list);

	}

	private void initEvent() {
		typeLlayout.setOnClickListener(this);
		brandLlayout.setOnClickListener(this);
		screenLlayout.setOnClickListener(this);
		sortLlayout.setOnClickListener(this);
		screenTypeFlayout.setOnTagItemClickListener(this);

	}

	@SuppressLint("NewApi")
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.type_llayout:
			setOriginAllStatus();
			changeStatus(typeTv,typeIv,screenTypeLlayout,R.drawable.select_type,0);
			break;
		case R.id.brand_llayout:
			setOriginAllStatus();
			changeStatus(brandTv,brandIv,null,R.drawable.select_type,1);
			break;
		case R.id.screen_llayout:
			setOriginAllStatus();
			changeStatus(screenTv,screenIv,null,R.drawable.select_screen,2);
			break;
		case R.id.sort_llayout:
			setOriginAllStatus();
			changeStatus(sortTv,sortIv,null,R.drawable.select_sort,3);
			break;

		}
	}

	public void changeStatus(TextView tv,ImageView iv,LinearLayout layout,int res,int index){
			if (!tagClick[index]) {
				tv.setTextColor(getResources().getColor(R.color.text_select_color));
				iv.setImageResource(res);
				if (layout!=null) {
					layout.setVisibility(View.VISIBLE);
				}
				
				for (int i = 0; i < 4; i++) {
					if (i==index) {
						tagClick[i] = true;
					}else{
						tagClick[i] = false;
					}
				}
			}else{
				tagClick[index] = false;
			}
	}

	private void setOriginAllStatus() {
		typeTv.setTextColor(getResources().getColor(R.color.text_unselect_color));
		brandTv.setTextColor(getResources().getColor(R.color.text_unselect_color));
		screenTv.setTextColor(getResources().getColor(R.color.text_unselect_color));
		sortTv.setTextColor(getResources().getColor(R.color.text_unselect_color));
		typeIv.setImageResource(R.drawable.un_select_type);
		brandIv.setImageResource(R.drawable.un_select_type);
		screenIv.setImageResource(R.drawable.unselect_screen);
		sortIv.setImageResource(R.drawable.un_select_sort);
		screenTypeLlayout.setVisibility(View.GONE);
	}

	@Override
	public void onTagItemClick(View v, int position) {

	}

}

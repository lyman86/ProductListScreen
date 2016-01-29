package com.example.productlistscreen.base;

import com.example.productlistscreen.R;
import com.example.productlistscreen.data.TestData;
import com.example.productlistscreen.view.FlowLayout;
import com.example.productlistscreen.view.FlowLayout.onTagItemClickListener;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class TypePopUpWindow extends BasePopUpWindow implements onTagItemClickListener{
	
	private Context context;
	private FlowLayout screenTypeFlayout;
	public TypePopUpWindow(Context context) {
		this.context = context;
		setLayout(context, R.layout.pop_type, 0, LayoutParams.MATCH_PARENT, android.app.ActionBar.LayoutParams.WRAP_CONTENT);
	}
	

	@Override
	public void initId() {
		screenTypeFlayout = (FlowLayout) mConvertView.findViewById(R.id.screen_type_flayout);

	}
	
	@Override
	public void initDatas() {
		screenTypeFlayout.addFlowTag(TestData.list);
		
	}
	@Override
	public void initEvent() {
		screenTypeFlayout.setOnTagItemClickListener(this);
	}


	@Override
	public void onTagItemClick(View v, int position) {
		// TODO Auto-generated method stub
		
	}


	

}

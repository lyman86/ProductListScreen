package com.ly.commodityclassficationlist.adapter;

import java.util.List;

import android.content.Context;

import com.example.productlistscreen.R;
import com.ly.commodityclassficationlist.bean.DataBean;
import com.ly.commodityclassficationlist.utils.SPUtils;
import com.ly.commodityclassficationlist.utils.ViewHolder;

public class DataDetailAdapter extends CommonAdapter<DataBean> {
	private int position;
	
	
	public DataDetailAdapter(Context context) {
			position = (Integer)SPUtils.get(context, "pos", 0);
			System.out.println(position+" positionLLLLL");
	}

	public DataDetailAdapter(Context context, List<DataBean> list, int layoutId) {
		super(context, list, layoutId);
		position = (Integer)SPUtils.get(context, "pos", 0);
	}

	@Override
	public void convert(ViewHolder viewHolder, DataBean t, int position) {
				viewHolder.setText(R.id.data_detail_tv, t.cityName);
				if (this.position==position) {
					viewHolder.setImageResource(R.id.data_detail_iv,R.drawable.btn_check_buttonless_on);
				}else{
					viewHolder.setImageResource(R.id.data_detail_iv,-1);
				}
				
	}
	
	public void setCorrectPosition(int pos){
			this.position = pos;
	}
	public void setData(List<DataBean> list){
		super.list = list;
	}

}

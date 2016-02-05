package com.ly.commodityclassficationlist.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ly.commodityclassficationlist.utils.ViewHolder;

public abstract class CommonAdapter<T> extends BaseAdapter {

	protected List<T> list;
	protected LayoutInflater commonInflater;
	protected Context context;
	protected int layoutId;

	public CommonAdapter(Context context, List<T> list, int layoutId) {
		this.context = context;
		this.list = list;
		this.layoutId = layoutId;
	}
	
	public CommonAdapter() {}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public T getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = ViewHolder.get(context, convertView, parent,
				layoutId, position);
		
		convert(viewHolder, getItem(position),position);
		
		return viewHolder.getConvertView();
	}

	public abstract void convert(ViewHolder viewHolder, T t,int position);

}

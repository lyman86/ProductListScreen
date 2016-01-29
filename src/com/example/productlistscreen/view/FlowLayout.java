package com.example.productlistscreen.view;

import java.util.ArrayList;
import java.util.List;
import com.example.productlistscreen.R;
import com.example.productlistscreen.utils.GetFlowLayoutBoundUtil;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FlowLayout extends ViewGroup {

	private Context context;
	//每个标签的默认高度
	private int itemHeight = 60;
	//每个标签的默认背景
	private int backGroundDrawable = R.drawable.flag_02;
	//每个标签的文字的默认颜色
	private List<TextView>tvs = new ArrayList<TextView>();
	private onTagItemClickListener mListener;
	private List<String>texts = new ArrayList<String>();
	private List<String>currentList= new ArrayList<String>();
	public interface onTagItemClickListener{
			void onTagItemClick(View v,int position);
	}
	
	public void setOnTagItemClickListener(onTagItemClickListener listener){
		this.mListener = listener;
	}
	
	public FlowLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
	}

	public FlowLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	public FlowLayout(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
		int width,height;
		GetFlowLayoutBoundUtil getBoundUtil2 = new GetFlowLayoutBoundUtil(this);
		int count = getChildCount();
		for (int i = 0; i < count; i++) {
			View child = getChildAt(i);
			measureChild(child, widthMeasureSpec, heightMeasureSpec);
			getBoundUtil2.setBound(sizeWidth, i);
			
		}
		//设置父控件的宽高
		height = (modeHeight==MeasureSpec.EXACTLY)?sizeHeight:getBoundUtil2.allHeight;
		width = (modeWidth==MeasureSpec.EXACTLY)?sizeWidth:getBoundUtil2.maxWidth;
		setMeasuredDimension(width, height);
		
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		if (changed) {
			int count = getChildCount();
			GetFlowLayoutBoundUtil getBoundUtil = new GetFlowLayoutBoundUtil(this);
			int width = getMeasuredWidth();
			for (int i = 0; i < count; i++) {
				final int pos = i;
				final View child = getChildAt(i);
				//设置cl,ct,cr,cb
				getBoundUtil.setBound(width, pos);
				child.layout(getBoundUtil.newCl,getBoundUtil.newCt, getBoundUtil.newCr, getBoundUtil.newCb);
				
				child.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
							if (mListener!=null) {
								mListener.onTagItemClick(child, pos);
								isCheck[pos] = !isCheck[pos];
								if (isCheck[pos]) {
									getTv(pos).setBackgroundDrawable(getResources().getDrawable(R.drawable.flag_03));
								
									currentList.add(texts.get(pos));
								}else{
									getTv(pos).setBackgroundDrawable(getResources().getDrawable(R.drawable.flag_02));
									currentList.remove(texts.get(pos));
								}
							}
					}
				});
			}
		}

	}
	
	public List<String>getCurrentList(){
		return currentList;
	}
	
	//默认为非选中状态
	private boolean []isCheck = new boolean[17];
	public void addFlowTag(List<String> strings) {
		TextView tv;
		for (int i = 0; i < strings.size(); i++) {

			tv = new TextView(context);
			tv.setTextColor(Color.WHITE);
			tv.setGravity(Gravity.CENTER);
			MarginLayoutParams lp = new MarginLayoutParams(
					LayoutParams.WRAP_CONTENT, itemHeight);
			lp.leftMargin = 40;
			lp.rightMargin = 40;
			lp.topMargin = 50;
			lp.bottomMargin = 50;
			tv.setText(strings.get(i));
			tv.setBackgroundDrawable(getResources().getDrawable(backGroundDrawable));
			tvs.add(tv);
			addView(tv, lp);
			
		}
		texts = strings;
	}
	
	public void removeFlowTag(){
		for (int i = 0; i < getChildCount(); i++) {
			removeViewAt(i);
			//tvs.clear();
		}
		System.out.println(tvs.size()+"    "+getChildCount());
		
	}
	
	/**
	 * 添加标签，标签的高度以60为基准
	 * @param strings 标签中的文字
	 * @param itemHeight 每个标签的父布局的高度
	 * @param TextColor 文字的颜色
	 * @param backGroundDrawable 标签的背景
	 */
	public void addFlowTag(List<String> strings,int itemHeight,int TextColor,int backGroundDrawable) {
		TextView tv;
		for (int i = 0; i < strings.size(); i++) {
			
			tv = new TextView(context);
			tv.setTextColor(TextColor);
			tv.setGravity(Gravity.CENTER);
			MarginLayoutParams lp = new MarginLayoutParams(
					LayoutParams.WRAP_CONTENT, itemHeight);
			lp.leftMargin = 10;
			lp.rightMargin = 10;
			lp.topMargin = 10;
			lp.bottomMargin = 10;
			
			tv.setText(strings.get(i));
			tv.setBackgroundDrawable(getResources().getDrawable(backGroundDrawable));
			tvs.add(tv);
			//texts.get(i)=strings.get(i);
			addView(tv, lp);
		}
		texts = strings;
	}

	public int getItemHeight() {
		return itemHeight;
	}

	public void setItemHeight(int itemHeight) {
		this.itemHeight = itemHeight;
	}

	public int getBackGroundDrawable() {
		return backGroundDrawable;
	}

	public void setBackGroundDrawable(int backGroundDrawable) {
		this.backGroundDrawable = backGroundDrawable;
	}
	
	public TextView getTv(int position){
		return tvs.get(position);
	}
	
	

}

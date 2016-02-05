package com.ly.commodityclassficationlist.utils;

import android.view.View;

import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
/**
 * 该类是用来获取每个标签的cl,ct,cr,cb
 */
public class GetFlowLayoutBoundUtil {
	public  int cl;
	public  int ct;
	public  int newCr;
	public  int newCb;
	public int newCl;
	public int newCt;
	private ViewGroup viewGroup;
	
	public int allHeight = 0;
	public int maxWidth = 0;
	//行
	private int row = 0;
	private int lastChildWidth =0;
	public int maxHeight;
	private boolean first = true;
	//列
	//private int culum = 0;
	public GetFlowLayoutBoundUtil(ViewGroup group) {
		this.viewGroup = group;
		
	}
	/**
	 * 设置cl,ct,cr,cb
	 * @param parentWidth 父控件的宽度
	 * @param pos 每个控件所对应的下标位置
	 */
	public void setBound(int parentWidth,int pos){
		MarginLayoutParams lp = null;
		int childWidth = 0;
		int childHeight = 0;
			View child = viewGroup.getChildAt(pos);
			lp = (MarginLayoutParams) child.getLayoutParams();
			childWidth = child.getMeasuredWidth();
			childHeight = child.getMeasuredHeight();
			if (cl+childWidth+lastChildWidth+lp.rightMargin> parentWidth) {
				allHeight+=(childHeight+lp.topMargin);
				row++;
				cl = 0;
				cl = cl+lp.leftMargin; 
			}else{
				cl = cl+lp.leftMargin+lastChildWidth; 
			}
			if (pos==0) {
				allHeight=childHeight+lp.topMargin+lp.bottomMargin;
			}
			ct = childHeight*row + lp.topMargin*(row+1);
			lastChildWidth = childWidth;
		    newCr = cl + childWidth;
		    newCb = ct + childHeight;
		    newCl = cl;
		    newCt = ct;
		
	}
	
	public int getAllHeight(){
		return allHeight;
	}
	
}

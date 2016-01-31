package com.example.productlistscreen.utils;

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
			if (cl+childWidth+lp.leftMargin+lp.rightMargin> parentWidth) {
				maxWidth = Math.max(cl+lp.leftMargin+lp.rightMargin, maxWidth);
				cl = (lp.leftMargin + childWidth);
				ct += (childHeight + lp.topMargin);
				allHeight+=childHeight+lp.bottomMargin+lp.topMargin;
			} else {
				cl += (lp.leftMargin+childWidth);
			}
			if (pos==viewGroup.getChildCount()-1) {
				allHeight+=(childHeight+lp.bottomMargin+lp.topMargin);
				maxWidth = Math.max(cl, maxWidth); 
			}
		
		newCr = cl;
		newCb = ct + childHeight + lp.topMargin;
		newCl = cl-childWidth;
		newCt = ct + lp.topMargin;
	}

}

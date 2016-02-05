package com.ly.commodityclassficationlist.adapter;
import java.util.List;
import android.content.Context;
import android.widget.ImageView;
import com.example.productlistscreen.R;
import com.ly.commodityclassficationlist.activity.MainActivity;
import com.ly.commodityclassficationlist.bean.CatalogProductListBean;
import com.ly.commodityclassficationlist.config.Config;
import com.ly.commodityclassficationlist.utils.ViewHolder;

public class CommodityClassficationListShow extends CommonAdapter<CatalogProductListBean> {
	private MainActivity activity;
	private int flag;
	public CommodityClassficationListShow(Context context,List<CatalogProductListBean> list, 
			int layoutId,int flag,MainActivity activity) {
		super(context, list, layoutId);
		this.activity = activity;
		this.flag = flag;
	}
	@Override
	public void convert(ViewHolder viewHolder, CatalogProductListBean t,int position) {
			viewHolder.setText(R.id.commodity_name_tv, t.commodityName).
			setText(R.id.brand_model_tv,t.brandModel).
			setText(R.id.order_number_tv, t.OrderNumber).
			setText(R.id.old_price_tv, t.oldPrice).
			setText(R.id.current_price_tv, t.newPrice);
			if (flag==Config.LIST_VIEW_SHOW) {
				viewHolder.setText(R.id.delivery_time_tv, t.deliveryTime);
			}
			initEvent(viewHolder,position);
	}

	private void initEvent(ViewHolder viewHolder, int position) {
			ImageView shoppingCartIv = viewHolder.getView(R.id.cart_iv);
			shoppingCartIv.setTag(position);
			shoppingCartIv.setOnClickListener(activity);
	}
	public void setLayoutId(int layoutId,int flag){
		  super.layoutId = layoutId;
		  this.flag = flag;
	}
}

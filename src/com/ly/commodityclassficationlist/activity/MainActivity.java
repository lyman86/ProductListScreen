package com.ly.commodityclassficationlist.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.productlistscreen.R;
import com.ly.commodityclassficationlist.adapter.CommodityClassficationListShow;
import com.ly.commodityclassficationlist.adapter.DataDetailAdapter;
import com.ly.commodityclassficationlist.animation.MenuAnim;
import com.ly.commodityclassficationlist.bean.CatalogProductListBean;
import com.ly.commodityclassficationlist.config.Config;
import com.ly.commodityclassficationlist.data.TestData;
import com.ly.commodityclassficationlist.dialog.Mydialog;
import com.ly.commodityclassficationlist.http.BaseLoad;
import com.ly.commodityclassficationlist.http.LoadProductCategory;
import com.ly.commodityclassficationlist.myinterface.OnLoadListener;
import com.ly.commodityclassficationlist.utils.DensityUtils;
import com.ly.commodityclassficationlist.utils.DialogWindow;
import com.ly.commodityclassficationlist.utils.MyThreadPool;
import com.ly.commodityclassficationlist.utils.SPUtils;
import com.ly.commodityclassficationlist.view.CustomSelectItem;
import com.ly.commodityclassficationlist.view.CustomTitleBarView;
import com.ly.commodityclassficationlist.view.CustomTitleBarView.OnBarViewClickListener;
import com.ly.commodityclassficationlist.view.FlowLayout;

public class MainActivity extends Activity implements OnClickListener,
													  OnLoadListener,
		                                              OnBarViewClickListener,
		                                              OnItemClickListener {
	//类别选择器
	private LinearLayout catogoryLlayout;
	//品牌选择器
	private LinearLayout brandLlayout;
	//筛选选择器
	private LinearLayout screenLlayout;
	//排序选择器
	private LinearLayout sortLlayout;
	//类别选择菜单
	private LinearLayout catogoryMenu;
	//品牌选择菜单
	private LinearLayout brandMenu;
	//筛选选择菜单
	private LinearLayout screenMenu;
	//排序选择菜单
	private LinearLayout sortMenu;
	//类别选择菜单里的流式标签
	private FlowLayout catogoryFlayout;
	//品牌选择带单里的流式标签
	private FlowLayout brandFlayout;
	//筛选菜单里的包含筛选条件的菜单数据
	private LinearLayout dataDetailMenu;
	//存储所有菜单的打开，以便按返回键时让当前的菜单关闭
	private Stack<LinearLayout>allMenu;
	//类别筛选器中的textView
	private TextView catogoryTv;
	//品牌筛选器中的textView
	private TextView brandTv;
	//筛选筛选器中的textView
	private TextView screenTv;
	//排序筛选器中的textView
	private TextView sortTv;
	//提示用户注册textView
	private TextView remindTv;
	//类别筛选器中的imageView
	private ImageView typeIv;
	//品牌筛选器中的imageView
	private ImageView brandIv;
	//筛选筛选器中的imageView
	private ImageView screenIv;
	//排序筛选器中的imageView
	private ImageView sortIv;
	//标题栏切换模式按钮
	private ImageView changeModeIv;
	//分类系类筛选按钮
	private Button catogoryBtn;
	//品牌筛选按钮
	private Button brandBtn;
	//筛选菜单的titlebar
	private CustomTitleBarView screenTitleBar;
	//筛选菜单里筛选条件菜单的titlebar
	private CustomTitleBarView screenChildTitleBar;
	//筛选菜单里筛选条件（选择地址）
	private CustomSelectItem selectAddress;
	//筛选菜单里筛选条件(选择货期)
	private CustomSelectItem selectTime;
	//筛选菜单里筛选条件(选择类别)
	private CustomSelectItem selectCategory;
	//筛选菜单里筛选条件(选择品牌)
	private CustomSelectItem selectBrand;
	//筛选菜单里筛选条件(选择系列)
	private CustomSelectItem selectSeries;
	//筛选菜单里筛选条件(选择规格)
	private CustomSelectItem selectSpecifications;
	//筛选菜单里的按钮(清除数据)
	private Button cleanUpDataBtn;
	//三级目录产品列表list
	private ListView listView;
	//三级目录产品列表listShowAdapter
	private CommodityClassficationListShow listShowAdapter;
	//点击筛选中筛选条件所展现的list
	private ListView dataDetailList;
	//点击筛选中筛选条件所使用的adapter
	private DataDetailAdapter dataDetailAdapter;
	//判断四个选择器中的打开关闭状态
	private boolean tagClick[] = new boolean[4];
	//四种选择器赋值
	private final int TAG_ONE = 0;
	private final int TAG_TWO = 1;
	private final int TAG_THREE = 2;
	private final int TAG_FOUR = 3;
	
	private DialogWindow dialogWindow;
	private Mydialog.Builder builder;
	private BaseLoad baseLoad;
	private boolean isRegist = false;
	private GridView gridView;
	private boolean listViewMode = true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initView();
		initDatas();
		initEvent();
		showListViewContent();
	}
	private void initView() {
		//四种选择器
		catogoryLlayout = (LinearLayout) findViewById(R.id.type_llayout);
		brandLlayout = (LinearLayout) findViewById(R.id.brand_llayout);
		screenLlayout = (LinearLayout) findViewById(R.id.screen_llayout);
		sortLlayout = (LinearLayout) findViewById(R.id.sort_llayout);
		//五种菜单
		catogoryMenu = (LinearLayout) findViewById(R.id.catogory_menu);
		brandMenu = (LinearLayout) findViewById(R.id.brand_menu);
		screenMenu = (LinearLayout) findViewById(R.id.menu_screen);
		sortMenu = (LinearLayout) findViewById(R.id.menu_sort);
		dataDetailMenu = (LinearLayout) findViewById(R.id.menu_data_detail);
		//四种选择器textView和imageView
		catogoryTv = (TextView) findViewById(R.id.type_tv);
		brandTv = (TextView) findViewById(R.id.brand_tv);
		screenTv = (TextView) findViewById(R.id.screen_tv);
		sortTv = (TextView) findViewById(R.id.sort_tv);
		typeIv = (ImageView) findViewById(R.id.type_iv);
		brandIv = (ImageView) findViewById(R.id.brand_iv);
		screenIv = (ImageView) findViewById(R.id.screen_iv);
		sortIv = (ImageView) findViewById(R.id.sort_iv);
		//筛选选择器中选择的六个条件
		selectAddress = (CustomSelectItem) findViewById(R.id.select_address);
		selectTime = (CustomSelectItem) findViewById(R.id.select_time);
		selectCategory = (CustomSelectItem) findViewById(R.id.select_category);
		selectBrand = (CustomSelectItem) findViewById(R.id.select_brand);
		selectSeries = (CustomSelectItem) findViewById(R.id.select_series);
		selectSpecifications = (CustomSelectItem) findViewById(R.id.select_specifications);
		//类别选择菜单的流式标签
		catogoryFlayout = (FlowLayout) catogoryMenu.findViewById(R.id.screen_type_flayout);
		brandFlayout = (FlowLayout) brandMenu.findViewById(R.id.screen_type_flayout);
		listView = (ListView) findViewById(R.id.commodity_lv);
		dataDetailList = (ListView) findViewById(R.id.data_detail_lv);
		screenTitleBar = (CustomTitleBarView) findViewById(R.id.cTitleBar);
		screenChildTitleBar = (CustomTitleBarView) findViewById(R.id.cChildTitleBar);
		cleanUpDataBtn = (Button) findViewById(R.id.clean_up_data_btn);
		catogoryBtn = (Button) catogoryMenu.findViewById(R.id.screen_btn);
		brandBtn = (Button) brandMenu.findViewById(R.id.screen_btn);
		remindTv = (TextView) findViewById(R.id.remind_tv);
		changeModeIv = (ImageView) findViewById(R.id.change_mode_iv);
		gridView = (GridView) findViewById(R.id.commodity_gv);
	}
	private void initDatas() {
		if (!SPUtils.contains(this,"pos")) SPUtils.put(this, "pos", 0);
		dataDetailAdapter = new DataDetailAdapter(this,TestData.getDataDetailBean(),R.layout.item_data_detail);
		selectAddress.setRightText(dataDetailAdapter.getItem((Integer)SPUtils.get(this, "pos", 0)).cityName);
		catogoryFlayout.addFlowTag(TestData.list);
		brandFlayout.addFlowTag(TestData.list2);
		listShowAdapter = new CommodityClassficationListShow(this, getData(),R.layout.item_commodity_list, Config.LIST_VIEW_SHOW,this);
		allMenu = new Stack<LinearLayout>();
		dialogWindow = new DialogWindow(this);
		builder = new Mydialog.Builder(this);
	}
	private void initEvent() {
		catogoryLlayout.setOnClickListener(this);
		brandLlayout.setOnClickListener(this);
		screenLlayout.setOnClickListener(this);
		sortLlayout.setOnClickListener(this);
		screenTitleBar.setOnBarViewClickListener(this);
		screenChildTitleBar.setOnBarViewClickListener(this);
		selectAddress.setOnClickListener(this);
		selectTime.setOnClickListener(this);
		selectCategory.setOnClickListener(this);
		selectBrand.setOnClickListener(this);
		selectSeries.setOnClickListener(this);
		selectSpecifications.setOnClickListener(this);
		cleanUpDataBtn.setOnClickListener(this);
		dataDetailList.setOnItemClickListener(this);
		catogoryBtn.setOnClickListener(this);
		brandBtn.setOnClickListener(this);
		changeModeIv.setOnClickListener(this);
	}
	/**
	 * 测试数据
	 * @return
	 */
	private List<CatalogProductListBean> getData() {    
		List<CatalogProductListBean> list = new ArrayList<CatalogProductListBean>();
		for (int i = 0; i < 30; i++) {
			list.add(new CatalogProductListBean("产品" + i, "产品型号" + i,
					"订货号" + i, "货期 " + i + " 天", "￥" + i, "￥" + i));
		}
		return list;
	}
	private void showListViewContent() {
		//网络获取用户是否注册认证，目前单机版
		if (!isRegist) remindTv.setVisibility(View.VISIBLE);
		builder.showDialogLoading(dialogWindow.getLoadingDilogWidth(),dialogWindow.getLoadingDialogHeight(), "加载中...");
		baseLoad = new LoadProductCategory("",this);
		MyThreadPool.getInstance().ExecuteMyThread(baseLoad);
	}

	@SuppressLint("NewApi")
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		//类别
		case R.id.type_llayout:
			setOriginAllStatus();
			changeStatus(catogoryTv, typeIv, catogoryMenu, R.id.down_view,R.drawable.select_type,TAG_ONE);
			break;
		//品牌
		case R.id.brand_llayout:
			setOriginAllStatus();
			changeStatus(brandTv, brandIv, brandMenu, R.id.down_view,R.drawable.select_type, TAG_TWO);
			break;
		//筛选
		case R.id.screen_llayout:
			setOriginAllStatus();
			changeStatus(screenTv, screenIv, screenMenu,R.id.left_layout,R.drawable.select_screen,TAG_THREE);
			break;
		//排序
		case R.id.sort_llayout:
			setOriginAllStatus();
			changeStatus(sortTv, sortIv, sortMenu, R.id.down_view_sort,R.drawable.select_sort, TAG_FOUR);
			break;
		//系统筛选
		case R.id.screen_btn:
			System.out.println(catogoryFlayout.getCurrentList()+"  "+brandFlayout.getCurrentList());
			break;
		//购物车
		case R.id.cart_iv:
			int position = (Integer) v.getTag();
			// todo
			Toast.makeText(this, position + "", Toast.LENGTH_SHORT).show();
			break;
		//选择地址
		case R.id.select_address:
			dataDetailAdapter.setData(TestData.getDataDetailBean());
			dataDetailList.setAdapter(dataDetailAdapter);
			dataDetailAdapter.setCorrectPosition((Integer)SPUtils.get(this,"pos", 0));
			MenuAnim.openMenu(MenuAnim.RIGHT_LEFT,this, dataDetailMenu,R.id.left_view);
			allMenu.push(dataDetailMenu);
			break;
		//选择货期
		case R.id.select_time:
			dataDetailAdapter.setData(TestData.getDataDetailBean2());
			dataDetailList.setAdapter(dataDetailAdapter);
			MenuAnim.openMenu(MenuAnim.RIGHT_LEFT,this, dataDetailMenu,R.id.left_view);
			allMenu.push(dataDetailMenu);
			Toast.makeText(this,"select_time", Toast.LENGTH_SHORT).show();
			break;
		//选择类别
		case R.id.select_category:
			Toast.makeText(this,"select_category", Toast.LENGTH_SHORT).show();
			break;
		//选择品牌
		case R.id.select_brand:
			Toast.makeText(this,"select_brand", Toast.LENGTH_SHORT).show();
			break;
		//选择系列
		case R.id.select_series:
			Toast.makeText(this,"select_series", Toast.LENGTH_SHORT).show();
			break;
		//选择规格
		case R.id.select_specifications:
			Toast.makeText(this,"select_specifications", Toast.LENGTH_SHORT).show();
			break;
		//清除数据
		case R.id.clean_up_data_btn:
			Toast.makeText(this,"clean_up_data_btn", Toast.LENGTH_SHORT).show();
			break;
		//切换显示模式
		case R.id.change_mode_iv:
			changeMode();
			
			break;
		}
	}
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return super.dispatchTouchEvent(ev);
		
	}
	private void changeMode() {
			if (listViewMode) {
				listShowAdapter.setLayoutId(R.layout.item_commodity_gride_list,Config.GRIDE_VIEW_SHOW);
				listView.setVisibility(View.GONE);
				gridView.setAdapter(listShowAdapter);
				gridView.setVisibility(View.VISIBLE);
				changeModeIv.setImageResource(R.drawable.change_mode_iv_and_tv);
			}else{
				listShowAdapter.setLayoutId(R.layout.item_commodity_list,Config.LIST_VIEW_SHOW);
				gridView.setVisibility(View.GONE);
				listView.setAdapter(listShowAdapter);
				listView.setVisibility(View.VISIBLE);
				changeModeIv.setImageResource(R.drawable.change_mode_product);
			}
			listViewMode = !listViewMode;
	}
	@Override
	public void onLoadSuccess(Object success, int flag) {
		listView.setAdapter(listShowAdapter);
		builder.dismissMyDialog();
	}
	@Override
	public void onLoadFailed(Object failed) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 改变选择器和菜单的状态
	 * @param tv
	 * @param iv
	 * @param layout
	 * @param viewId
	 * @param res
	 * @param index
	 */
	public void changeStatus(TextView tv, ImageView iv, LinearLayout layout,int viewId,int res, int index) {
		if (!tagClick[index]) {
			setSelectConfig(tv, iv, layout, viewId,res, index);
		} else {
			setUnSelectConfig(index,layout,viewId);
		}
	}
	/**
	 * 设置菜单关闭
	 * @param index
	 * @param layout
	 * @param viewId
	 */
	private void setUnSelectConfig(int index,LinearLayout layout,int viewId) {
		MenuAnim.closeMenu(MenuAnim.UP_DOWN,this, layout,viewId);
		allMenu.pop();
		tagClick[index] = false;
	}
	/**
	 * 设置菜单打开
	 * @param tv
	 * @param iv
	 * @param layout
	 * @param viewId
	 * @param res
	 * @param index
	 */
	private void setSelectConfig(TextView tv, ImageView iv,LinearLayout layout,int viewId, int res, int index) {
		layout.setTag(index);
		tv.setTextColor(getResources().getColor(R.color.text_select_color));
		iv.setImageResource(res);
		if (index==TAG_THREE) {
			MenuAnim.openMenu(MenuAnim.RIGHT_LEFT,this, layout,viewId);
		}else{
			MenuAnim.openMenu(MenuAnim.UP_DOWN,this, layout,viewId);
		}
		allMenu.push(layout);
		for (int i = 0; i < tagClick.length; i++) {
			if (i == index) {
				tagClick[i] = true;
			} else {
				tagClick[i] = false;
			}
		}
	}
	/**
	 * 所有选择器和菜单的初始化
	 */
	private void setOriginAllStatus() {
		resetFilter();
		resetMenu();
	}
	/**
	 * 所有菜单隐藏
	 */
	private void resetMenu() {
		catogoryMenu.setVisibility(View.GONE);
		brandMenu.setVisibility(View.GONE);
		screenMenu.setVisibility(View.GONE);
		sortMenu.setVisibility(View.GONE);
	}
	/**
	 * 重置所有选择器
	 */
	private void resetFilter() {
		catogoryTv.setTextColor(getResources().getColor(R.color.text_unselect_color));
		brandTv.setTextColor(getResources().getColor(R.color.text_unselect_color));
		screenTv.setTextColor(getResources().getColor(R.color.text_unselect_color));
		sortTv.setTextColor(getResources().getColor(R.color.text_unselect_color));
		typeIv.setImageResource(R.drawable.un_select_type);
		brandIv.setImageResource(R.drawable.un_select_type);
		screenIv.setImageResource(R.drawable.unselect_screen);
		sortIv.setImageResource(R.drawable.un_select_sort);
	}
	/**
	 * titlebar
	 */
	@Override
	public void onBarViewClick(View v, int witch) {
		switch (v.getId()) {
		case R.id.cTitleBar:
			switch (witch) {
			case CustomTitleBarView.LEFT:
				screenTv.setTextColor(getResources().getColor(R.color.text_unselect_color));
				screenIv.setImageResource(R.drawable.unselect_screen);
				MenuAnim.closeMenu(MenuAnim.RIGHT_LEFT,this,screenMenu,R.id.left_layout);
				screenMenu.setVisibility(View.GONE);
				for (int i = 0; i < tagClick.length; i++)
					tagClick[i] = false;
				break;
			case CustomTitleBarView.RIGHT:
				// todo
				Toast.makeText(this, "确定", Toast.LENGTH_SHORT).show();
				break;
			}
			break;

		case R.id.cChildTitleBar:
			switch (witch) {
			case CustomTitleBarView.LEFT:
				MenuAnim.closeMenu(MenuAnim.RIGHT_LEFT,this,dataDetailMenu,R.id.left_view);
				dataDetailMenu.setVisibility(View.GONE);
				break;
			}
			break;
		}
		
	}
    /**
     * listView item响应
     */
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
		    SPUtils.put(this,"pos", pos);
			dataDetailAdapter.setCorrectPosition(pos);
			dataDetailAdapter.notifyDataSetChanged();
			MenuAnim.closeMenu(MenuAnim.RIGHT_LEFT,this,dataDetailMenu,R.id.left_view);
			dataDetailMenu.setVisibility(View.GONE);
			selectAddress.setRightText(dataDetailAdapter.getItem(pos).cityName);
			allMenu.pop();
	}
	/**
	 * 按返回键菜单关闭或直接退出
	 */
	@Override
	public void onBackPressed() {
		if (!allMenu.isEmpty()) {
			allMenu.get(allMenu.size()-1).setVisibility(View.GONE);
			resetFilter();
			if (allMenu.get(allMenu.size()-1).getTag()!=null) {
				int index = (Integer) allMenu.get(allMenu.size()-1).getTag();
				tagClick[index] = false;
			}
			allMenu.pop();
		}else{
			finish();
		}
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (!allMenu.isEmpty()) {
				int index = (Integer) allMenu.get(allMenu.size()-1).getTag();
				float x = event.getX();
				float y= event.getY();
				System.out.println(y+"  YYYYY"+"   "+DensityUtils.dp2px(this, 120)+catogoryFlayout.getAllHeight());
				if (index==TAG_ONE&&y>DensityUtils.dp2px(this, 120)+catogoryFlayout.getAllHeight()) {
					allMenu.get(allMenu.size()-1).setVisibility(View.GONE);
					resetFilter();
					tagClick[index] = false;
				}else{
					
				}
			}
			
			
			break;

		default:
			break;
		}
		return super.onTouchEvent(event);
	}
	

	 

}

package com.ly.commodityclassficationlist.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.productlistscreen.R;
import com.ly.commodityclassficationlist.bean.DataBean;

public class TestData {
	public static String[] data = new String[]{"施耐德","西门子","ABB"};
	public static List<String>list = Arrays.asList(data);
	public static String[] data2 = new String[]{"施耐德","西门子","ABB","施耐德","西门子","ABB","施耐德","西门子","ABB","施耐德","西门子","ABB","施耐德","西门子","ABB","施耐德施耐德ABB"};
	public static List<String>list2 = Arrays.asList(data2);
	public static String[] city = {"上海","广州","深圳","合肥","南京","镇江","苏州","云南","九江","西藏","武汉","杭州","宁波","美国","上海","广州","深圳","合肥","南京","镇江","苏州","云南","九江","西藏","武汉","杭州","宁波","美国"};
	
	public static List<DataBean>getDataDetailBean(){
		List<DataBean>list = new ArrayList<DataBean>();
		for (int i = 0; i < city.length; i++) {
			list.add(new DataBean(city[i]));
		}
		return list;
	}
	
	public static List<DataBean>getDataDetailBean2(){
		List<DataBean>list = new ArrayList<DataBean>();
		for (int i = 0; i < data2.length; i++) {
			list.add(new DataBean(data2[i]));
		}
		return list;
	}
	
}

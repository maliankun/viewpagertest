package com.miapsoft.vptest.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.viewpagertest.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 
 * @author liyakun
 * 创建时间：2014年11月6日
 */
public class MainActivity extends Activity{

	private ViewPager vp_mms;
	private List<View> groupview;
	private TextView tv_title;
	private int count=9;  //页面的个数
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initobj();
	}
	
	public void initobj(){
		vp_mms=(ViewPager) findViewById(R.id.vp_mms);
		groupview=new ArrayList<View>();
		for(int i=0;i<count;i++){
			LayoutInflater flater = LayoutInflater.from(this);
			View view=flater.inflate(R.layout.format, null);
			tv_title=(TextView) view.findViewById(R.id.tv_title);
			tv_title.setText("这是第"+i+"个页面。");
			groupview.add(view);
			vp_mms.setAdapter(VPagerAdapter);
			vp_mms.setOnPageChangeListener(new MyPageChangeListener());
			vp_mms.setCurrentItem(90);
		}
	}
	
	/**
	 * 为vp设置adapter
	 * 为了使vp能够无限循环滑动，假定这个vp的view组有无数个view。
	 * 这些view都设定的view组
	 */
	PagerAdapter VPagerAdapter = new PagerAdapter() {

		@Override
		public int getCount() {

			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {

			int pos = (Integer)arg1 % count;
			boolean f = arg0 == groupview.get(pos);
			return f;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			int pos = position % count;
			View view = groupview.get(pos);
			((ViewPager) container).removeView(view);
		}

		@Override
		public Object instantiateItem(View container, int position) {

			int pos = position % count;
			View view = groupview.get(pos);
			((ViewPager) container).addView(view);
			return position;
		}
	};
	
	public class MyPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageSelected(int arg0) {
			int [] ids ={R.id.page00,R.id.page01,R.id.page02,R.id.page03,R.id.page04,R.id.page05,R.id.page06,R.id.page07,R.id.page08};
				for(int i=0;i<count;i++){
					int whichView = ids[i];
					ImageView a = (ImageView) findViewById(whichView);
					if(i==arg0%count){
						a.setImageDrawable(getResources().getDrawable(R.drawable.dot_press));
					}else{
						a.setImageDrawable(getResources().getDrawable(R.drawable.dot));
					}
				}
			
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
		}
		}
	
	
	

}

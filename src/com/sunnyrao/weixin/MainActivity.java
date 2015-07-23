package com.sunnyrao.weixin;

import java.util.ArrayList;
import java.util.List;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements
		View.OnClickListener {

	private ViewPager mViewPager;
	private FragmentPagerAdapter mAdapter;
	private List<Fragment> mData;

	private TextView mChatTextView;
	private TextView mFriendTextView;
	private TextView mContactTextView;

	private LinearLayout mChatLayout;
	private LinearLayout mFriendLayout;
	private LinearLayout mContactLayout;

	private ImageView mTabline;
	private int mScreen1_3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initTop2();
		initTabline();
		initView();
	}

	private void initTop2() {
		mChatLayout = (LinearLayout) findViewById(R.id.id_ll_chat);
		mFriendLayout = (LinearLayout) findViewById(R.id.id_ll_friend);
		mContactLayout = (LinearLayout) findViewById(R.id.id_ll_contact);
		mChatLayout.setOnClickListener(this);
		mFriendLayout.setOnClickListener(this);
		mContactLayout.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.id_ll_chat:
			mViewPager.setCurrentItem(0, true);
			break;
		case R.id.id_ll_friend:
			mViewPager.setCurrentItem(1, true);
			break;
		case R.id.id_ll_contact:
			mViewPager.setCurrentItem(2, true);
			break;
		}
	}

	private void initTabline() {
		mTabline = (ImageView) findViewById(R.id.id_iv_tabline);
		Display display = getWindow().getWindowManager().getDefaultDisplay();
		DisplayMetrics outMetrics = new DisplayMetrics();
		display.getMetrics(outMetrics);
		mScreen1_3 = outMetrics.widthPixels / 3;
		LayoutParams lp = mTabline.getLayoutParams();
		lp.width = mScreen1_3;
		mTabline.setLayoutParams(lp);
	}

	private void initView() {
		mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
		mChatTextView = (TextView) findViewById(R.id.id_tv_chat);
		mFriendTextView = (TextView) findViewById(R.id.id_tv_friend);
		mContactTextView = (TextView) findViewById(R.id.id_tv_contact);

		mData = new ArrayList<Fragment>();
		ChatMainFragment tab01 = new ChatMainFragment();
		FriendMainFragment tab02 = new FriendMainFragment();
		ContactMainFragment tab03 = new ContactMainFragment();
		mData.add(tab01);
		mData.add(tab02);
		mData.add(tab03);

		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

			@Override
			public int getCount() {
				return mData.size();
			}

			@Override
			public Fragment getItem(int arg0) {
				return mData.get(arg0);
			}
		};
		mViewPager.setAdapter(mAdapter);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				resetTextView();
				switch (position) {
				case 0:
					mChatTextView.setTextColor(Color.parseColor("#008000"));
					break;
				case 1:
					mFriendTextView.setTextColor(Color.parseColor("#008000"));
					break;
				case 2:
					mContactTextView.setTextColor(Color.parseColor("#008000"));
					break;
				}
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPx) {
				// Log.e("TAG", position + " , " + positionOffset + " , "
				// + positionOffsetPx);
				
				// LinearLayout.LayoutParams lp =
				// (android.widget.LinearLayout.LayoutParams) mTabline
				// .getLayoutParams();
				// lp.leftMargin = (int) ((position + positionOffset) *
				// mScreen1_3);
				// mTabline.setLayoutParams(lp);
				
				int offset = (int) ((position + positionOffset) * mScreen1_3);
				mTabline.setTranslationX(offset);
			}

			@Override
			public void onPageScrollStateChanged(int position) {
			}
		});
	}

	protected void resetTextView() {
		mChatTextView.setTextColor(Color.BLACK);
		mFriendTextView.setTextColor(Color.BLACK);
		mContactTextView.setTextColor(Color.BLACK);
	}
}

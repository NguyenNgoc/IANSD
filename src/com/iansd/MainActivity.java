package com.iansd;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;

import com.fpt.robot.app.RobotFragmentActivity;

public class MainActivity extends RobotFragmentActivity implements TabListener {

	ViewPager mainPager;
	ActionBar actionBar;
	TabAdapter tabAdapter;
	String[] titles = new String[] { "Đơn giản", "Tiện dụng", "Điều khiển",
			"Đánh dấu" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);

		init();
	}

	private void init() {
		Robot.createRobot();

		mainPager = (ViewPager) findViewById(R.id.mainPager);
		actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		tabAdapter = new TabAdapter(getSupportFragmentManager());
		mainPager.setAdapter(tabAdapter);

		for (String tabName : titles) {
			Tab tab = actionBar.newTab();
			tab.setTabListener(this);
			tab.setText(tabName);
			actionBar.addTab(tab);
		}

		mainPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int index) {
				actionBar.setSelectedNavigationItem(index);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.imScan: {
			break;
		}
		case R.id.imHelp: {
			break;
		}
		case R.id.imAboutUs: {
			break;
		}
		case R.id.imExit: {
			break;
		}
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		mainPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {

	}
}

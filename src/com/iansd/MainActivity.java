package com.iansd;

import SDK_TV.Controller;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.fpt.robot.app.RobotFragmentActivity;
import com.fragments.FourthFragment;

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
		TV.context = this;
		Controller.context = this;
		Util.context = this;
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
			if (getRobot() == null) {
				scan();
				return false;
			} else {
				Toast.makeText(getApplicationContext(), "Đã kết nối tới Robot",
						Toast.LENGTH_SHORT).show();
			}
			Robot.createRobot(getRobot());
			Controller.currentRobot = getRobot();
			break;
		}

		case R.id.imConnectTV: {
			if (getRobot() == null) {
				Toast.makeText(this, "Hãy kết nối tới Robot trước",
						Toast.LENGTH_SHORT).show();
				scan();
				return false;
			}
			TV.context = this;
			TV.start();
			break;
		}

		case R.id.imHelp: {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setIcon(R.drawable.iansd_small);
			builder.setTitle("Hướng dẫn sử dụng\n");
			builder.setMessage(" 1. Kết nối tới Robot bằng cách nhấp vào biểu"
					+ " tượng cái kính lúp trên trên thanh menu\n"
					+ " 2. Kết nối tới TV bằng cách nhấp vào biểu tượng"
					+ " hình cái cờ lê bên cạnh cái kính lúp\n" + " 3. \n"
					+ "   3.1: Nếu muốn dùng điều khiển xem truyền hình :"
					+ " Sử dụng ứng dụng trên thiết bị Android để"
					+ " điều khiển\n"
					+ "   3.2: Nếu muốn dùng cho ứng dụng tìm kiếm hình ảnh,"
					+ " video, hay tra cứu : Cần mở ứng dụng trên thiết bị"
					+ " Smart TV trước, rồi dùng ứng dụng trên thiết bị"
					+ " Android để điều khiển\n"
					+ " 4. Chúc các bạn vui vẻ và thoải mái với IANSD");
			builder.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
						}

					});
			builder.show();
			break;
		}
		case R.id.imAboutUs: {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setIcon(R.drawable.iansd_small);
			builder.setTitle("SMAC Challenge 2014");
			builder.setMessage("Tên sản phẩm:   IANSD"
					+ "\nTên nhóm:          BK-TBG" + "\nThành viên nhóm: "
					+ "\n                 Nguyễn Hữu Ngọc"
					+ "\n                 Nguyễn Thanh Tùng"
					+ "\n                 Trương Anh Dũng"
					+ "\n                 Đào Thị Giang");
			builder.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
						}

					});
			builder.show();
			break;
		}
		case R.id.imExit: {
			finish();
		}
		}
		if (item.getTitle().equals("Thêm")) {
			FourthFragment.setEnable();
		}
		if (item.getTitle().equals("Xóa hết")) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Xóa hết");
			builder.setPositiveButton("Đồng ý",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							FourthFragment.deleteAll();
						}
					});
			builder.setNegativeButton("Hủy", null);
			builder.show();
		}
		return true;
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

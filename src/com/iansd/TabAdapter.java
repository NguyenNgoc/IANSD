package com.iansd;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fragments.FirstFragment;
import com.fragments.FourthFragment;
import com.fragments.SecondFragment;
import com.fragments.ThirdFragment;

public class TabAdapter extends FragmentPagerAdapter {

	public TabAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {
		switch (index) {
		case 0:
			return new FirstFragment();
		case 1:
			return new SecondFragment();
		case 2:
			return new ThirdFragment();
		case 3:
			return new FourthFragment();
		}
		return null;
	}

	@Override
	public int getCount() {
		return 4;
	}

}

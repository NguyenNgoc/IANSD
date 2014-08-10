package com.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fpt.robot.app.RobotFragment;
import com.iansd.R;

public class FourthFragment extends RobotFragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fourth_fragment, null);
		return view;
	}

}

package com.fragments;

import SDK_TV.Controller;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.fpt.robot.app.RobotFragment;
import com.iansd.R;

public class ThirdFragment extends RobotFragment implements OnClickListener {

	Button btPower;
	Button btMute;
	Button btOK;
	Button btUp;
	Button btDown;
	Button btLeft;
	Button btRight;
	Button btExit;
	Button btMenu;
	Button btPrev;
	Button btNext;
	Button btVolDown;
	Button btVolUp;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.third_fragment, null);

		btPrev = (Button) view.findViewById(R.id.btPrev);
		btNext = (Button) view.findViewById(R.id.btNext);
		btVolDown = (Button) view.findViewById(R.id.btVolDown);
		btVolUp = (Button) view.findViewById(R.id.btVolUp);
		btPower = (Button) view.findViewById(R.id.btPower);
		btMute = (Button) view.findViewById(R.id.btMute);
		btOK = (Button) view.findViewById(R.id.btOK);
		btRight = (Button) view.findViewById(R.id.btRight);
		btLeft = (Button) view.findViewById(R.id.btLeft);
		btDown = (Button) view.findViewById(R.id.btDown);
		btUp = (Button) view.findViewById(R.id.btUp);
		btExit = (Button) view.findViewById(R.id.btExit);
		btMenu = (Button) view.findViewById(R.id.btMenu);

		btPower.setOnClickListener(this);
		btMute.setOnClickListener(this);
		btOK.setOnClickListener(this);
		btUp.setOnClickListener(this);
		btDown.setOnClickListener(this);
		btRight.setOnClickListener(this);
		btLeft.setOnClickListener(this);
		btExit.setOnClickListener(this);
		btMenu.setOnClickListener(this);
		btPrev.setOnClickListener(this);
		btNext.setOnClickListener(this);
		btVolDown.setOnClickListener(this);
		btVolUp.setOnClickListener(this);

		return view;
	}

	@Override
	public void onClick(View v) {
		if (getRobotActivity().getRobot() == null) {
			Toast.makeText(getRobotActivity(), "Hãy kết nối tới Robot trước",
					Toast.LENGTH_SHORT).show();
			getRobotActivity().scan();
			return;
		}
		Controller.currentRobot = getRobotActivity().getRobot();
		switch (v.getId()) {
		case R.id.btPower: {
			Controller.pressKey("power");
			break;
		}
		case R.id.btMute: {
			Controller.pressKey("mute");
			break;
		}
		case R.id.btOK: {
			Controller.pressKey("ok");
			break;
		}
		case R.id.btLeft: {
			Controller.pressKey("left");
			break;
		}
		case R.id.btRight: {
			Controller.pressKey("right");
			break;
		}
		case R.id.btDown: {
			Controller.pressKey("down");
			break;
		}
		case R.id.btUp: {
			Controller.pressKey("up");
			break;
		}
		case R.id.btExit: {
			Controller.pressKey("exit");
			break;
		}
		case R.id.btMenu: {
			Controller.pressKey("menu");
			break;
		}
		case R.id.btVolUp: {
			Controller.pressKey("vol_up");
			break;
		}
		case R.id.btVolDown: {
			Controller.pressKey("vol_down");
			break;
		}
		case R.id.btNext: {
			Controller.pressKey("next_ch");
			break;
		}
		case R.id.btPrev: {
			Controller.pressKey("prev_ch");
			break;
		}
		}
	}

}

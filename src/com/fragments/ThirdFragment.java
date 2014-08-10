package com.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.fpt.robot.app.RobotFragment;
import com.iansd.R;

public class ThirdFragment extends RobotFragment implements OnClickListener {

	Button btPower;
	Button btMute;
	Button btOK;
	Button btReduceVolumn;
	Button btIncreVolumn;
	Button btPrevChannel;
	Button btNextChannel;
	Button btSearch;
	Button btExit;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.third_fragment, null);

		btPower = (Button) view.findViewById(R.id.btPower);
		btMute = (Button) view.findViewById(R.id.btMute);
		btOK = (Button) view.findViewById(R.id.btOK);
		btReduceVolumn = (Button) view.findViewById(R.id.btReduceVolumn);
		btIncreVolumn = (Button) view.findViewById(R.id.btIncreVolumn);
		btPrevChannel = (Button) view.findViewById(R.id.btPrevChannel);
		btNextChannel = (Button) view.findViewById(R.id.btNextChannel);
		btSearch = (Button) view.findViewById(R.id.btSearch);
		btExit = (Button) view.findViewById(R.id.btExit);

		return view;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btPower:{
			break;
		}
		case R.id.btMute:{
			break;
		}
		case R.id.btOK:{
			break;
		}
		case R.id.btReduceVolumn:{
			break;
		}
		case R.id.btIncreVolumn:{
			break;
		}
		case R.id.btPrevChannel:{
			break;
		}
		case R.id.btNextChannel:{
			break;
		}
		case R.id.btSearch:{
			break;
		}
		case R.id.btExit:{
			break;
		}
		}
	}

}

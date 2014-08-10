package com.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.fpt.lib.asr.Languages;
import com.fpt.lib.asr.Result;
import com.fpt.lib.asr.SpeakToText;
import com.fpt.lib.asr.SpeakToTextListener;
import com.fpt.robot.app.RobotFragment;
import com.iansd.R;
import com.iansd.Robot;
import com.iansd.TV;

public class SecondFragment extends RobotFragment implements
		SpeakToTextListener {

	Button btVoice;
	Button btOK;
	EditText etOrder;
	SpeakToText stt;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		stt = new SpeakToText(Languages.VIETNAMESE, this);
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.second_fragment, null);

		btVoice = (Button) view.findViewById(R.id.btVoice);
		btOK = (Button) view.findViewById(R.id.btOK);
		etOrder = (EditText) view.findViewById(R.id.etOrder);

		btOK.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		btVoice.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						stt.recognize(4000, 5000);
					}
				}).start();
			}
		});

		return view;
	}

	@Override
	public void onError(Exception arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProcessing() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRecording() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onResult(Result result) {
		String message = result.result[0].alternative[0].transcript;
		Log.i("iansd", message);
		TV.receiveOrder(message);
	}

	@Override
	public void onStopped() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTimeout() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onWaiting() {
		// TODO Auto-generated method stub

	}

}

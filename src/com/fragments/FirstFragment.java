package com.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.fpt.lib.asr.Languages;
import com.fpt.lib.asr.Result;
import com.fpt.lib.asr.SpeakToText;
import com.fpt.lib.asr.SpeakToTextListener;
import com.fpt.robot.app.RobotFragment;
import com.iansd.R;
import com.iansd.Robot;
import com.iansd.TV;

public class FirstFragment extends RobotFragment implements SpeakToTextListener {
	Button btVoice;
	SpeakToText stt;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		stt = new SpeakToText(Languages.VIETNAMESE, this);
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.first_fragment, null);
		btVoice = (Button) view.findViewById(R.id.btVoice);
		btVoice.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
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
	public void onWaiting() {

	}

	@Override
	public void onRecording() {
		Log.i("iansd", "Recording");
	}

	@Override
	public void onError(Exception ex) {
		Log.i("iansd", "Error");
	}

	@Override
	public void onTimeout() {
		Log.i("iansd", "Timeout");
	}

	@Override
	public void onProcessing() {
		Log.i("iansd", "Processing");
	}

	@Override
	public void onResult(Result result) {
		String message = result.result[0].alternative[0].transcript;
		Log.i("iansd", message);
		TV.receiveOrder(message);
	}

	@Override
	public void onStopped() {
		Log.i("iansd", "Stopped");
	}
}

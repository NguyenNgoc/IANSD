package com.fragments;

import SDK_TV.Controller;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.fpt.lib.asr.Languages;
import com.fpt.lib.asr.Result;
import com.fpt.lib.asr.SpeakToText;
import com.fpt.lib.asr.SpeakToTextListener;
import com.fpt.robot.app.RobotFragment;
import com.iansd.R;
import com.iansd.Robot;
import com.iansd.TV;
import com.iansd.Util;

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
				if (getRobotActivity().getRobot() != null) {
					Robot.createRobot(getRobotActivity().getRobot());
					Controller.currentRobot = getRobotActivity().getRobot();
				} else {
					Toast.makeText(getRobotActivity(),
							"Bạn hãy chọn Robot trước", Toast.LENGTH_SHORT)
							.show();
					getRobotActivity().scan();
					return;
				}
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

	private ProgressDialog progressDialog = null;

	protected void showProgress(final String message) {
		getRobotActivity().runOnUiThread(new Runnable() {
			public void run() {
				if (progressDialog == null) {
					progressDialog = new ProgressDialog(getRobotActivity());
				}
				if (message != null) {
					progressDialog.setMessage(message);
				}
				progressDialog.setIndeterminate(true);
				progressDialog.setCancelable(true);
				progressDialog.show();
			}
		});
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void cancelProgress() {
		getRobotActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (progressDialog != null) {
					progressDialog.dismiss();
				}
			}
		});
	}

	@Override
	public void onWaiting() {
		showProgress("Waiting...");
	}

	@Override
	public void onRecording() {
		showProgress("Recording...");
	}

	@Override
	public void onError(Exception ex) {
		showProgress("Error...");
		cancelProgress();
	}

	@Override
	public void onTimeout() {
		showProgress("Timeout...");
		cancelProgress();
	}

	@Override
	public void onProcessing() {
		showProgress("Processing...");
	}

	@Override
	public void onResult(Result result) {
		String message = result.result[0].alternative[0].transcript;
		Util.log("...===>.."+message);
		TV.receiveOrder(message);
		cancelProgress();
	}

	@Override
	public void onStopped() {
		showProgress("Stopped...");
		cancelProgress();
	}

}

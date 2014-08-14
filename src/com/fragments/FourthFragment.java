package com.fragments;

import java.util.ArrayList;

import SDK_TV.Controller;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

import com.fpt.lib.asr.Languages;
import com.fpt.lib.asr.Result;
import com.fpt.lib.asr.SpeakToText;
import com.fpt.lib.asr.SpeakToTextListener;
import com.fpt.robot.app.RobotFragment;
import com.iansd.R;
import com.iansd.Robot;
import com.iansd.TV;

public class FourthFragment extends RobotFragment implements
		SpeakToTextListener {

	Button btOK;
	Button btVoice;
	EditText etText;
	LinearLayout layout;
	ArrayList<String> currentList = new ArrayList<String>();
	SharedPreferences pref;
	boolean haveDeleted = false;
	SpeakToText stt;
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			etText.setText(msg.obj.toString());
		}

	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		pref = getActivity().getSharedPreferences("data", 0);
		int sizeOfList = pref.getAll().size();
		for (int i = 0; i < sizeOfList; i++) {
			currentList.add(pref.getString(String.valueOf(i), ""));
		}
		stt = new SpeakToText(Languages.VIETNAMESE, this);
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fourth_fragment, null);
		btOK = (Button) view.findViewById(R.id.btOK);
		btVoice = (Button) view.findViewById(R.id.btVoice);
		etText = (EditText) view.findViewById(R.id.etText);
		layout = (LinearLayout) view.findViewById(R.id.layout);
		restorePreferences();

		btOK.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (!etText.getText().toString().trim().equals("")) {
					String require = etText.getText().toString();
					addNewButton(require);
					currentList.add(require);

					savePreferences();

					 btOK.setEnabled(false);
					 btVoice.setEnabled(false);
					 etText.setEnabled(false);
				}
			}
		});

		btVoice.setOnClickListener(new View.OnClickListener() {

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
		setHasOptionsMenu(true);
		return view;
	}

	private void addNewButton(String str) {
		final Button btRequire = new Button(getRobotActivity());
		btRequire.setText(str);
		btRequire.setBackgroundResource(R.drawable.custom_button);
		btRequire.setGravity(Gravity.FILL);
		final LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.bottomMargin = 5;
		btRequire.setLayoutParams(params);
		btRequire.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
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
				TV.receiveOrder(btRequire.getText().toString());
			}
		});
		layout.addView(btRequire);
		etText.setText("");
		Handler(btRequire, params);
	}

	public void savePreferences() {
		SharedPreferences.Editor editor = pref.edit();
		if (haveDeleted == false) {
			editor.putString(String.valueOf(currentList.size() - 1),
					currentList.get(currentList.size() - 1));
		} else {
			editor.clear();
			for (int i = 0; i < currentList.size(); i++) {
				editor.putString(String.valueOf(i), currentList.get(i));
			}
			haveDeleted = false;
		}
		editor.commit();
	}

	public void restorePreferences() {
		layout.removeAllViews();
		for (String str : currentList) {
			addNewButton(str);
		}
	}

	public void Handler(final Button btn, final LayoutParams params) {
		if (btn.getLayoutParams() == params) {
			btn.setOnLongClickListener(new View.OnLongClickListener() {

				@Override
				public boolean onLongClick(View v) {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							getActivity());
					builder.setMessage("Hãy chọn thao tác");
					builder.setTitle("Menu");
					builder.setPositiveButton("Chỉnh sửa",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									if (btn != null) {
										etText.setText(btn.getText());
										btOK.setEnabled(true);
										btVoice.setEnabled(true);
										etText.setEnabled(true);
										layout.removeView(btn);
										currentList.remove(btn.getText());
										haveDeleted = true;
									}
								}
							});
					builder.setNeutralButton("Xóa",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									layout.removeView(btn);
									currentList.remove(btn.getText());
									haveDeleted = true;
									savePreferences();
								}
							});
					builder.setNegativeButton("Đóng",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
								}
							});
					builder.create().show();
					return true;
				}
			});
		}
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
	public void onStopped() {
		showProgress("Stopped...");
		cancelProgress();
	}

	@Override
	public void onResult(Result result) {
		final String message = result.result[0].alternative[0].transcript;
		TV.receiveOrder(message);
		Message msg = handler.obtainMessage();
		msg.obj = message;
		handler.sendMessage(msg);
		cancelProgress();
	}
}

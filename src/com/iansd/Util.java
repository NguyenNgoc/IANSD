package com.iansd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.AlertDialog;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.fpt.robot.RobotException;
import com.fpt.robot.tts.RobotTextToSpeech;

public class Util {

	public static Context context;

	public static void log(String str) {
		Log.i("IANSD", str);
	}

	public static void notifyError(String message) {
		try {
			RobotTextToSpeech.say(Robot.robotSpeaker, message, RobotTextToSpeech.ROBOT_TTS_LANG_VI);
		} catch (RobotException e) {
			e.printStackTrace();
		}
	}

	public static String getMacAddress(Context context) {
		WifiManager wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		return wifiManager.getConnectionInfo().getMacAddress();
	}

	public static void makeHttpRequest(final String urlString,
			final HttpRequestListener httpRequestListener) {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				URL url = null;
				try {
					url = new URL(urlString);
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
					httpRequestListener.onError(e1.getMessage());
					return;
				}
				URLConnection urlConnection = null;
				try {
					urlConnection = url.openConnection();
				} catch (IOException e1) {
					e1.printStackTrace();
					httpRequestListener.onError(e1.getMessage());
					return;
				}
				BufferedReader in = null;
				try {
					in = new BufferedReader(new InputStreamReader(
							urlConnection.getInputStream()));
				} catch (IOException e) {
					e.printStackTrace();
					httpRequestListener.onError(e.getMessage());
					return;
				}
				String lines = "";
				String inputLine;

				try {
					while ((inputLine = in.readLine()) != null) {
						lines += inputLine;
					}
					httpRequestListener.onSuccess(lines);
				} catch (IOException e) {
					e.printStackTrace();
					httpRequestListener.onError(e.getMessage());
					return;
				}
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});
		thread.start();
	}

	public static abstract class HttpRequestListener {
		public abstract void onSuccess(String s);

		public abstract void onError(String error);
	}

}

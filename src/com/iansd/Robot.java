package com.iansd;

import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Robot {
	public static Robot robot;

	public static String ACCESS_TOKEN = "b0e58933-67f3-42f3-9ee1-2806be87e1b6";
	public boolean isSendingMessage = false;

	private Robot() {

	}

	public static void createRobot() {
		if (robot == null) {
			robot = new Robot();
		}
	}

	public void sendMessage(String message) {
		isSendingMessage = true;

		Util.makeHttpRequest(
				"http://tech.fpt.com.vn/AIML/api/bots/53beb17ee4b041df124d1144/chat?request="
						+ URLEncoder.encode(message) + "&token=" + ACCESS_TOKEN,
				new Util.HttpRequestListener() {

					@Override
					public void onSuccess(String s) {
						JSONObject jsonResult;
						try {
							jsonResult = new JSONObject(s);
							String result = jsonResult.getString("status");
							if (result.trim().endsWith("success")) {
								String response = jsonResult
										.getString("response");
								Log.i("Ket qua day ngoc oi", response);
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}

						isSendingMessage = false;
					}

					@Override
					public void onError(String error) {
						isSendingMessage = false;
					}

				});
	}

}

package com.iansd;

import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.fpt.robot.RobotException;
import com.fpt.robot.tts.RobotTextToSpeech;

public class Robot {
	public static Robot robot;

	private com.fpt.robot.Robot robotSpeaker;

	public static String ACCESS_TOKEN = "b0e58933-67f3-42f3-9ee1-2806be87e1b6";
	public boolean isSendingMessage = false;

	private Robot(com.fpt.robot.Robot robotSpeaker) {
		this.robotSpeaker = robotSpeaker;
	}

	public static void createRobot(com.fpt.robot.Robot robotSpeaker) {
		if (robot == null) {
			robot = new Robot(robotSpeaker);
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
								final String response = jsonResult
										.getString("response");
								try {
									new Thread(new Runnable() {
										
										@Override
										public void run() {
											try {
												RobotTextToSpeech
												.say(robotSpeaker,
														response,
														RobotTextToSpeech.ROBOT_TTS_LANG_VI);
											} catch (RobotException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
									}).start();
									
									new Thread(new Runnable() {
										
										@Override
										public void run() {
											if (Behaviors.robot == null){
												Behaviors.robot = robotSpeaker;
											}
											Behaviors.action(response);
										}
									}).start();
									
								} catch (Exception e) {
									Log.e("Có lỗi rồi : ", e.toString());
									e.printStackTrace();
								}
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

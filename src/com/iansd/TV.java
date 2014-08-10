package com.iansd;

import android.util.Log;

public class TV {
	public static void receiveOrder(String order) {
		if (order.startsWith("video")) {
			Log.i("TV Info", Util.makeJSONText("video", order.substring(6)));
		} else if (order.startsWith("hình ảnh")) {
			Log.i("TV Info", Util.makeJSONText("hình ảnh", order.substring(9)));
		} else if (order.startsWith("wiki")) {
			Log.i("TV Info", Util.makeJSONText("wiki", order.substring(5)));
		} else {
			Robot.robot.sendMessage(order);
		}
	}
}

package com.iansd;

import java.util.List;

import SDK_TV.NSConnection;
import SDK_TV.NSDevice;
import SDK_TV.NSListener;
import android.content.Context;
import android.util.Log;

public class TV {

	public static Context context;

	public static void receiveOrder(String order) {
		if (order.startsWith("video")) {
			sendMessage("video", order.substring(6));
		} else if (order.startsWith("hình ảnh")) {
			sendMessage("image", order.substring(9));
		} else if (order.startsWith("tra cứu")) {
			sendMessage("wiki", order.substring(7));
		} else if (order.trim().equals("chuyển tiếp")) {
			sendCommand("command", "next");
		} else if (order.trim().equals("quay lại")) {
			sendCommand("command", "prev");
		} else if (order.trim().equals("tạm dừng")) {
			sendCommand("command", "pause");
		} else if (order.trim().equals("đầy đủ màn hình")) {
			sendCommand("command", "fullscreen");
		} else if (order.trim().equals("bỏ toàn màn hình")) {
			sendCommand("command", "exitFullscreen");
		} else if (order.trim().equals("Trang tiếp theo")) {
			sendCommand("command", "nextPage");
		} else if (order.trim().equals("Tua đi")) {
			sendCommand("command", "forward");
		} else if (order.trim().equals("Tua về")) {
			sendCommand("command", "backward");
		} else if (order.trim().equals("Trang trước")) {
			sendCommand("command", "prevPage");
		} else if (order.trim().equals("mở")) {
			sendCommand("command", "resume");
		} else {
			Robot.robot.sendMessage(order);
		}
	}

	static void sendMessage(String type, String keyword) {
		String message = "{\"type\" : \"" + type + "\" , \"keyword\" : \""
				+ keyword + "\"}";
		Log.i("TV", message);
		connection.sendMessage(message);
	}

	static void sendCommand(String type, String keyword) {
		String message = "{\"type\" : \"" + type + "\" , \"command\" : \""
				+ keyword + "\"}";
		Log.i("TV", message);
		connection.sendMessage(message);
	}

	static NSConnection connection = null;

	public static void start() {

		NSListener listener = new NSListener("abc") {
			@Override
			public void onWifiChanged() {
				Util.log("onWifiChanged");
			}

			@Override
			public void onDeviceChanged(List<NSDevice> devices) {
				Util.log("onDeviceChanged");
				if (devices.size() > 0) {
					final NSDevice device = devices.get(0);
					connection.connect(device);
				}
			}

			@Override
			public void onConnected() {
				Util.log("onConnected");
			}

			@Override
			public void onDisconnected() {
				Util.log("onDisconnected");
			}

			@Override
			public void onConnectionFailed(int code) {
				Util.log("onConnectionFailed");
			}

			@Override
			public void onMessageSent() {
				Util.log("onMessageSent");
			}

			@Override
			public void onMessageSendFailed(int code) {
				Util.log("onMessageSendFailed: " + code);
			}

			@Override
			public void onMessageReceived(String message) {
				Util.log("onMessageReceived: " + message);
			}
		};
		connection = new NSConnection("testtv2", context);
		connection.registerListener(listener);
		connection.searchDevices();
	}
}

package com.iansd;

import java.util.List;

import SDK_TV.Controller;
import SDK_TV.NSConnection;
import SDK_TV.NSDevice;
import SDK_TV.NSListener;
import android.content.Context;
import android.util.Log;

public class TV {

	public static Context context;

	public static void receiveOrder(String msg) {
		String order = UseCase.createOrder(msg);
		Util.log("nhan duoc -----"+order);
		try {
			if (order.startsWith("video")) {
				UseCase.status = 1;
				Robot.robot.sendMessage("tìm kiếm " + order);
				sendMessage("video", order.substring(6));
			} else if (order.startsWith("hình ảnh")) {
				UseCase.status = 2;
				Robot.robot.sendMessage("tìm kiếm " + order);
				sendMessage("image", order.substring(9));
			} else if (order.startsWith("tra cứu")) {
				UseCase.status = 3;
				Robot.robot.sendMessage("tìm kiếm " + order);
				sendMessage("wiki", order.substring(7));
			} else if (order.trim().equals("chuyển tiếp")) {
				Robot.robot.sendMessage("chuyển tiếp");
				sendCommand("command", "next");
			} else if (order.trim().equals("quay lại")) {
				Robot.robot.sendMessage("quay lại");
				sendCommand("command", "prev");
			} else if (order.trim().equals("tạm dừng")) {
				Robot.robot.sendMessage("tạm dừng");
				sendCommand("command", "pause");
			} else if (order.trim().equals("toàn màn hình")) {
				Robot.robot.sendMessage("toàn màn hình");
				sendCommand("command", "fullscreen");
			} else if (order.trim().equals("thu màn hình")) {
				Robot.robot.sendMessage("thu màn hình");
				sendCommand("command", "exitFullscreen");
			} else if (order.trim().equals("trang tiếp theo")) {
				Robot.robot.sendMessage("trang tiếp theo");
				sendCommand("command", "nextPage");
			} else if (order.trim().equals("tua đi")) {
				Robot.robot.sendMessage("tua đi");
				sendCommand("command", "forward");
			} else if (order.trim().equals("tua về")
					|| order.trim().equals("tua chậm")) {
				Robot.robot.sendMessage("tua về");
				sendCommand("command", "backward");
			} else if (order.trim().equals("trang trước")) {
				Robot.robot.sendMessage("trang trước");
				sendCommand("command", "prevPage");
			} else if (order.trim().equals("chạy")) {
				Robot.robot.sendMessage("chạy");
				sendCommand("command", "resume");
			} else if (order.trim().equals("tăng âm")) {
				Robot.robot.sendMessage("tăng âm");
				Controller.pressKey("vol_up");
			} else if (order.trim().equals("giảm âm")) {
				Robot.robot.sendMessage("giảm âm");
				Controller.pressKey("vol_down");
			} else if (order.trim().equals("xem truyền hình")) {
				Robot.robot.sendMessage("xem truyền hình");
				Controller.pressKey("exit");
			} else if (order.trim().equals("kênh tiếp theo")) {
				Robot.robot.sendMessage("kênh tiếp theo");
				Controller.pressKey("next_ch");
			} else if (order.trim().equals("kênh trước")) {
				Robot.robot.sendMessage("kênh trước");
				Controller.pressKey("prev_ch");
			} else if (order.trim().equals("mở")) {
				Robot.robot.sendMessage("mở");
				Controller.pressKey("power");
			} else if (order.trim().equals("tắt")) {
				Robot.robot.sendMessage("tắt");
				Controller.pressKey("power");
			} else if (order.trim().equals("im lặng")) {
				Robot.robot.sendMessage("im lặng");
				Controller.pressKey("mute");
			} else {
				Util.log("da chay vao day");
				Robot.robot.sendMessage(order);
			}
		} catch (Exception e) {
			//Robot.robot
				//	.sendMessage("Có lỗi xảy ra, không gửi được yêu cầu tới Ti Vi");
		}
	}

	static void sendMessage(String type, String keyword) {
		String message = "{\"type\" : \"" + type + "\" , \"keyword\" : \""
				+ keyword + "\"}";
		Log.i("TV", message);
		connection.sendMessage(message);
	}

	static void sendCommand(String type, String command) {
		String message = "{\"type\" : \"" + type + "\" , \"command\" : \""
				+ command + "\"}";
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
				} else if (devices.size() == 0) {
					Util.notifyError("Không tìm thấy TV");
					Util.log("TV not found");
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
				Util.notifyError("Kết nối thất bại");

			}

			@Override
			public void onMessageSent() {
				Util.log("onMessageSent");
			}

			@Override
			public void onMessageSendFailed(int code) {
				Util.log("onMessageSendFailed: " + code);
				Util.notifyError("Gửi lệnh thất bại");
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

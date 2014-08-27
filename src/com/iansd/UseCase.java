package com.iansd;

public class UseCase {
	public static int status = 0;

	static String createOrder(String msg) {
		String order = "";
		if ((msg.contains("tìm") && msg.contains("video về"))
				|| (msg.contains("tra cứu") && msg.contains("video về"))
				|| (msg.contains("xem") && msg.contains("video về"))
				|| msg.contains("video về")) {
			order = "video " + msg.substring(msg.indexOf("video về") + 8);
		} else if ((msg.contains("tìm") && msg.contains("video của"))
				|| (msg.contains("xem") && msg.contains("video của"))
				|| (msg.contains("tra cứu") && msg.contains("video của"))
				|| msg.contains("video của")) {
			order = "video " + msg.substring(msg.indexOf("video của") + 9);
		} else if ((msg.contains("tìm") && msg.contains("video liên quan đến"))
				|| (msg.contains("xem") && msg.contains("video liên quan đến"))
				|| (msg.contains("tra cứu") && msg
						.contains("video liên quan đến"))
				|| msg.contains("video liên quan đến")) {
			order = "video "
					+ msg.substring(msg.indexOf("video liên quan đến") + 20);
		} else if ((msg.contains("tìm") && msg.contains("video liên quan tới"))
				|| (msg.contains("xem") && msg.contains("video liên quan tới"))
				|| (msg.contains("tra cứu") && msg
						.contains("video liên quan tới"))
				|| msg.contains("video liên quan tới")) {
			order = "video "
					+ msg.substring(msg.indexOf("video liên quan tới") + 20);
		} else if (msg.contains("tìm video") || msg.contains("tìm kiếm video")
				|| msg.contains("tra cứu video") || msg.contains("xem video")
				|| msg.contains("video")) {
			order = "video " + msg.substring(msg.indexOf("video") + 6);
		} else if ((msg.contains("tìm") && msg.contains("clip"))
				|| (msg.contains("xem") && msg.contains("clip"))
				|| msg.contains("clip")) {
			order = "video " + msg.substring(msg.indexOf("clip") + 5);
		} else if ((msg.contains("tìm") && msg.contains("ảnh về"))
				|| (msg.contains("xem") && msg.contains("ảnh về"))
				|| (msg.contains("tra cứu") && msg.contains("ảnh về"))
				|| msg.contains("ảnh về")) {
			order = "hình ảnh " + msg.substring(msg.indexOf("ảnh về") + 7);
		} else if ((msg.contains("tìm") && msg.contains("ảnh của"))
				|| (msg.contains("xem") && msg.contains("ảnh của"))
				|| (msg.contains("tra cứu") && msg.contains("ảnh của"))
				|| msg.contains("ảnh của")) {
			order = "hình ảnh " + msg.substring(msg.indexOf("ảnh của") + 8);
		} else if ((msg.contains("tìm") && msg.contains("ảnh có mặt"))
				|| (msg.contains("xem") && msg.contains("ảnh có mặt"))
				|| (msg.contains("tra cứu") && msg.contains("ảnh có mặt"))
				|| msg.contains("ảnh có mặt")) {
			order = "hình ảnh " + msg.substring(msg.indexOf("ảnh có mặt") + 11);
		} else if ((msg.contains("tìm") && msg.contains("ảnh liên quan đến"))
				|| (msg.contains("xem") && msg.contains("ảnh liên quan đến"))
				|| (msg.contains("tra cứu") && msg
						.contains("ảnh liên quan đến"))
				|| msg.contains("ảnh liên quan đến")) {
			order = "hình ảnh "
					+ msg.substring(msg.indexOf("ảnh liên quan đến") + 18);
		} else if ((msg.contains("tìm") && msg.contains("ảnh liên quan tới"))
				|| (msg.contains("xem") && msg.contains("ảnh liên quan tới"))
				|| (msg.contains("tra cứu") && msg
						.contains("ảnh liên quan tới"))
				|| msg.contains("ảnh liên quan tới")) {
			order = "hình ảnh "
					+ msg.substring(msg.indexOf("ảnh liên quan tới") + 18);
		} else if (msg.contains("tìm hình ảnh")
				|| msg.contains("tìm kiếm hình ảnh")
				|| msg.contains("tra cứu hình ảnh")
				|| msg.contains("xem hình ảnh") || msg.contains("hình ảnh")
				|| msg.contains("tìm ảnh") || msg.contains("tìm kiếm ảnh")
				|| msg.contains("tra cứu ảnh") || msg.contains("xem ảnh")
				|| msg.contains("ảnh")) {
			order = "hình ảnh " + msg.substring(msg.indexOf("ảnh") + 4);
		} else if ((msg.contains("tra cứu") && msg.contains("thông tin về"))
				|| (msg.contains("tìm") && msg.contains("thông tin về"))
				|| (msg.contains("xem") && msg.contains("thông tin về"))
				|| msg.contains("thông tin về")) {
			order = "tra cứu "
					+ msg.substring(msg.indexOf("thông tin về") + 13);
		} else if ((msg.contains("tra cứu") && msg.contains("thông tin của"))
				|| (msg.contains("tìm") && msg.contains("thông tin của"))
				|| (msg.contains("xem") && msg.contains("thông tin của"))
				|| msg.contains("thông tin của")) {
			order = "tra cứu "
					+ msg.substring(msg.indexOf("thông tin của") + 14);
		} else if ((msg.contains("tra cứu") && msg
				.contains("thông tin liên quan đến"))
				|| (msg.contains("tìm") && msg
						.contains("thông tin liên quan đến"))
				|| (msg.contains("xem") && msg
						.contains("thông tin liên quan đến"))
				|| msg.contains("thông tin liên quan đến")) {
			order = "tra cứu "
					+ msg.substring(msg.indexOf("thông tin liên quan đến") + 24);
		} else if ((msg.contains("tra cứu") && msg
				.contains("thông tin liên quan tới"))
				|| (msg.contains("tìm") && msg
						.contains("thông tin liên quan tới"))
				|| (msg.contains("xem") && msg
						.contains("thông tin liên quan tới"))
				|| msg.contains("thông tin liên quan tới")) {
			order = "tra cứu "
					+ msg.substring(msg.indexOf("thông tin liên quan tới") + 24);
		} else if (msg.contains("tìm")) {
			order = "tra cứu " + msg.substring(msg.indexOf("tìm") + 4);
		} else if (msg.contains("tìm kiếm ")) {
			order = "tra cứu " + msg.substring(msg.indexOf("tìm kiếm") + 9);
		} else if (msg.contains("tắt")) {
			order = "tắt";
		} else if ((msg.contains("tăng") && msg.contains("âm"))
				|| (msg.contains("tăng") && msg.contains("tiếng"))
				|| (msg.contains("to") && msg.contains("âm"))
				|| (msg.contains("to") && msg.contains("tiếng"))
				|| (msg.contains("tăng") && msg.contains("volume"))) {
			order = "tăng âm";
		} else if ((msg.contains("giảm") && msg.contains("âm"))
				|| (msg.contains("giảm") && msg.contains("tiếng"))
				|| (msg.contains("nhỏ") && msg.contains("âm"))
				|| (msg.contains("nhỏ") && msg.contains("tiếng"))
				|| (msg.contains("giảm") && msg.contains("volume"))) {
			order = "giảm âm";
		} else if (msg.contains("ai là")) {
			order = "tra cứu " + msg.substring(msg.indexOf("ai là") + 6);
		} else if (msg.contains("im lặng") || msg.contains("tắt tiếng")) {
			order = "im lặng";
		} else if (msg.contains("hỏi") && msg.contains("là")) {
			order = "tra cứu "
					+ msg.substring(msg.indexOf("hỏi") + 3, msg.indexOf("là"));
		} else if (msg.contains("biết") && msg.contains("là")) {
			order = "tra cứu "
					+ msg.substring(msg.indexOf("biết") + 3, msg.indexOf("là"));
		} else if (msg.contains("là") && msg.contains("gì")) {
			order = "tra cứu " + msg.substring(0, msg.indexOf("là"));
		} else if (msg.contains("tra cứu") && msg.contains("là")) {
			order = "tra cứu "
					+ msg.substring(msg.indexOf("tra cứu"), msg.indexOf("là"));
		} else if (msg.contains("tra cứu ")) {
			order = "tra cứu " + msg.substring(msg.indexOf("tra cứu") + 8);
		}

		switch (status) {
		case 0: {
			if ((msg.contains("kênh") && msg.contains("tiếp"))
					|| (msg.contains("kênh") && msg.contains("sau"))) {
				order = "kênh tiếp theo";
			} else if (msg.contains("kênh") && msg.contains("trước")) {
				order = "kênh trước";
			} else if (order.trim() == "") {
				order = msg;
			}
			break;
		}
		case 1: {
			if (msg.contains("chuyển tiếp")
					|| (msg.contains("video") && msg.contains("tiếp"))
					|| (msg.contains("video") && msg.contains("sau"))
					|| (msg.contains("video") && msg.contains("dưới"))) {
				order = "chuyển tiếp";
			} else if (msg.contains("chuyển về")
					|| (msg.contains("video") && msg.contains("trước"))
					|| (msg.contains("video") && msg.contains("trên"))
					|| msg.contains("quay lại")) {
				order = "quay lại";
			} else if ((msg.contains("clip") && msg.contains("tiếp"))
					|| (msg.contains("clip") && msg.contains("sau"))
					|| (msg.contains("clip") && msg.contains("dưới"))) {
				order = "chuyển tiếp";
			} else if ((msg.contains("clip") && msg.contains("trước"))
					|| (msg.contains("clip") && msg.contains("trên"))
					|| msg.contains("quay lại")) {
				order = "quay lại";
			} else if (msg.contains("toàn màn hình")
					|| msg.contains("phóng to")
					|| msg.contains("phóng to màn hình")
					|| msg.contains("bật to màn hình")
					|| msg.contains("phóng lớn màn hình")) {
				order = "toàn màn hình";
			} else if (msg.contains("thu màn hình") || msg.contains("thu nhỏ")
					|| msg.contains("thu nhỏ màn hình")
					|| msg.contains("bật nhỏ màn hình")) {
				order = "thu màn hình";
			} else if ((msg.contains("trang") && msg.contains("tiếp"))
					|| (msg.contains("trang") && msg.contains("sau"))) {
				order = "trang tiếp theo";
			} else if ((msg.contains("trang") && msg.contains("trước"))) {
				order = "trang trước";
			} else if (msg.contains("tua đi") || msg.contains("tua nhanh")
					|| msg.contains("tua lên")) {
				order = "tua đi";
			} else if (msg.contains("tua về") || msg.contains("tua chậm")) {
				order = "tua về";
			} else if (msg.contains("dừng") || msg.contains("ngưng")
					|| msg.contains("ngừng")) {
				order = "dừng";
			} else if (msg.contains("tiếp tục") || msg.contains("chạy tiếp")
					|| msg.contains("phát tiếp")) {
				order = "chạy";
			} else if ((msg.contains("xem") && msg.contains("ti vi"))
					|| (msg.contains("xem") && msg.contains("truyền hình"))
					|| (msg.contains("xem") && msg.contains("chương trình"))
					|| msg.contains("thoát")) {
				order = "xem truyền hình";
			} else if (order.trim() == "") {
				order = msg;
			}
			break;
		}
		case 2: {
			if (msg.contains("chuyển về")
					|| (msg.contains("ảnh") && msg.contains("trước"))
					|| (msg.contains("ảnh") && msg.contains("trên"))
					|| msg.contains("quay lại")) {
				order = "quay lại";
			} else if (msg.contains("toàn màn hình")
					|| msg.contains("phóng to")
					|| msg.contains("phóng to màn hình")
					|| msg.contains("bật to màn hình")
					|| msg.contains("phóng lớn màn hình")) {
				order = "toàn màn hình";
			} else if (msg.contains("thu màn hình") || msg.contains("thu nhỏ")
					|| msg.contains("thu nhỏ màn hình")
					|| msg.contains("bật nhỏ màn hình")) {
				order = "thu màn hình";
			} else if ((msg.contains("trang") && msg.contains("tiếp"))
					|| (msg.contains("trang") && msg.contains("sau"))) {
				order = "trang tiếp theo";
			} else if ((msg.contains("trang") && msg.contains("trước"))) {
				order = "trang trước";
			} else if ((msg.contains("xem") && msg.contains("ti vi"))
					|| (msg.contains("xem") && msg.contains("truyền hình"))
					|| (msg.contains("xem") && msg.contains("chương trình"))
					|| msg.contains("thoát")) {
				order = "xem truyền hình";
			} else if (order.trim() == "") {
				order = msg;
			}
			break;
		}
		case 3: {
			if (order.trim() == "") {
				order = msg;
			}
			break;
		}
		default: {
			break;
		}
		}
		Util.log(order);
		return order;
	}
}

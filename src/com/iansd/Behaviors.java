package com.iansd;

import android.annotation.SuppressLint;
import android.util.Log;

import com.fpt.robot.RobotException;
import com.fpt.robot.behavior.RobotBehavior;
import com.fpt.robot.motion.RobotMotionAction;
import com.fpt.robot.motion.RobotMotionJointController;
import com.fpt.robot.motion.RobotMotionStiffnessController;
import com.fpt.robot.motion.RobotPosture;

@SuppressLint("DefaultLocale")
public class Behaviors {

	public static com.fpt.robot.Robot robot;

	public static void action(String str) {
		Log.i("Text truyền vào", str);
		String behavior = "";
		if (str.toLowerCase().contains("không nói cho tức")
				|| str.toLowerCase().contains("không nên nói cộc lốc")
				|| str.toLowerCase().contains("cấm nói tục")
				|| str.toLowerCase().contains("chửi bậy")
				|| str.toLowerCase().contains("chắc chắn không")
				|| str.toLowerCase().contains("không yêu đâu")
				|| str.toLowerCase().contains("đừng")
				|| str.toLowerCase().contains("không thích")
				|| str.toLowerCase().contains("không ngu")
				|| str.toLowerCase().contains("tôi không nói")
				|| str.toLowerCase().contains("không cho")) {
			behavior = "BK_TBG_Cross";
		} else if (str.toLowerCase().contains("tôi là một chàng trai")) {
			behavior = "BK_TBG_TuSuong";
		} else if (str.toLowerCase().contains("tôi là nam")) {
			behavior = "BK_TBG_Boy";
		} else if (str.toLowerCase().contains(
				"tương tư là chuyện của tôi yêu nàng")) {
			behavior = "BK_TBG_NangMuaTuongTu";
		} else if (str.toLowerCase().contains("buồn ngủ")) {
			behavior = "BK_TBG_BuonNgu";
		} else if (str.toLowerCase().contains("thời tiết thế này thì hỏng")) {
			behavior = "BK_TBG_HenThoiTietXau";
		} else if (str.toLowerCase().contains("lập trình")) {
			behavior = "BK_TBG_Code";
		} else if (str.toLowerCase().contains("tôi không khỏe")) {
			behavior = "BK_TBG_Sick";
		} else if (str.toLowerCase().contains("tôi khỏe")
				|| str.toLowerCase().contains("khỏe đừng hỏi")
				|| str.toLowerCase().contains("mình khỏe")
				|| str.toLowerCase().contains("tôi rất là khỏe")
				|| str.toLowerCase().contains("có sức khỏe là có tất cả")) {
			behavior = "BK_TBG_Strong";
		} else if (str.toLowerCase().contains("tôi yêu bạn")) {
			behavior = "BK_TBG_ToiYeuBan";
		} else if (str.toLowerCase().contains("uống")
				|| str.toLowerCase().contains("nước chanh nhé")
				|| str.toLowerCase().contains("uống đi")
				|| str.toLowerCase().contains("uống nước đi")
				|| str.toLowerCase().contains("uống cốc nước mát đi")) {
			behavior = "BK_TBG_UongRuou";
		} else if (str.toLowerCase().contains("nói lại được không")
				|| str.toLowerCase().contains("đúng không")
				|| str.toLowerCase().contains("bạn không thích hả")
				|| str.toLowerCase().contains("suy nghĩ gì")
				|| str.toLowerCase().contains("muốn ăn gì")
				|| str.toLowerCase().contains("không hiểu chỗ nào")
				|| str.toLowerCase().contains("không hiểu cái gì")
				|| str.toLowerCase().contains("được không")
				|| str.toLowerCase().contains("sao lại chán")
				|| str.toLowerCase().contains("sao lại bó tay")
				|| str.toLowerCase().contains("sao bạn lại nói")
				|| str.toLowerCase().contains("cám ơn gì")
				|| str.toLowerCase().contains("tại sao")
				|| str.toLowerCase().contains("sao lại thế")
				|| str.toLowerCase().contains("làm gì")
				|| str.toLowerCase().contains("thế nào")
				|| str.toLowerCase().contains("bạn cũng vui tính")
				|| str.toLowerCase().contains("nếu không chê")
				|| str.toLowerCase().contains("tôi luôn ở đây mà")
				|| str.toLowerCase().contains("trước mặt bạn")
				|| str.toLowerCase().contains("tôi vẫn ở đây")
				|| str.toLowerCase().contains("đối diện tôi đây")
				|| str.toLowerCase().contains("chờ tôi vài phút")
				|| str.toLowerCase().contains("với tôi đi")
				|| str.toLowerCase().contains("hãy kể tôi nghe")
				|| str.toLowerCase().contains("yêu đi")
				|| str.toLowerCase().contains("cưới nhau đi")
				|| str.toLowerCase().contains("cứ tự nhiên")
				|| str.toLowerCase().contains("xem chương trình khác")) {
			behavior = "BK_TBG_Ask";
		} else if (str.toLowerCase().contains("hình như chưa")
				|| str.toLowerCase().contains("hình như là chưa")
				|| str.toLowerCase().contains("không có gì")
				|| str.toLowerCase().contains("không có ai để nói chuyện")
				|| str.toLowerCase().contains("không nói chuyện với tôi")
				|| str.toLowerCase().contains("yêu làm chi")
				|| str.toLowerCase().contains("tôi không muốn")
				|| str.toLowerCase().contains("không hiểu vì sao")
				|| str.toLowerCase().contains("chán lắm")) {
			behavior = "BK_TBG_LacDau";
		} else if (str.toLowerCase()
				.contains("cười nhiều sẽ thấy đời tươi đẹp")) {
			behavior = "ThichCuoi";
		} else if (str.toLowerCase().contains("danh sách dài")) {
			behavior = "BK_TBG_Dai1m";
		} else if (str.toLowerCase().contains("tôi ngu")
				|| str.toLowerCase().contains("đồ điên")
				|| str.toLowerCase().contains("cút đi")) {
			behavior = "BK_TBG_Ask";
		} else if (str.toLowerCase().contains("khen thừa")
				|| str.toLowerCase().contains("nói điều ai cũng biết")
				|| str.toLowerCase().contains("một thời bá đạo")
				|| str.toLowerCase().contains("mình giỏi quá")
				|| str.toLowerCase().contains("mình vui tính mà")
				|| str.toLowerCase().contains("yêu mấy cô rồi")
				|| str.toLowerCase().contains("tôi có thừa")
				|| str.toLowerCase().contains("tên tôi đẹp")
				|| str.toLowerCase().contains("bá đạo")) {
			behavior = "BK_TBG_Boy";
		} else if (str.toLowerCase().contains("chào bạn")
				|| str.toLowerCase().contains("bạn khỏe không")
				|| str.toLowerCase().contains("bạn có khỏe không")
				|| str.toLowerCase().contains("bạn tên gì")
				|| str.toLowerCase().contains("rất vui được làm quen với bạn")) {
			behavior = "BK_TBG_XinChao";
		} else if (str.toLowerCase().contains("có chứ")
				|| str.toLowerCase().contains("thích chứ")
				|| str.toLowerCase().contains("sẵn sàng")
				|| str.toLowerCase().contains("chắc chắn")
				|| str.toLowerCase().contains("tôi làm được")
				|| str.toLowerCase().contains("tôi quan tâm bạn")
				|| str.toLowerCase().contains("tôi có duyên")
				|| str.toLowerCase().contains("chắc chắn là phải có rồi")
				|| str.toLowerCase().contains("tôi quên sao được")
				|| str.toLowerCase().contains("hiển nhiên")
				|| str.toLowerCase().contains("bạn sẽ ngủ")
				|| str.toLowerCase().contains("tôi cũng yêu bạn")
				|| str.toLowerCase().equals("chuyển tiếp")
				|| str.toLowerCase().equals("video tiếp theo")
				|| str.toLowerCase().equals("hình ảnh tiếp theo")
				|| str.toLowerCase().equals("quay lại")
				|| str.toLowerCase().equals("trở lại")
				|| str.toLowerCase().equals("tạm dừng")
				|| str.toLowerCase().equals("dừng")
				|| str.toLowerCase().equals("ngưng")
				|| str.toLowerCase().equals("ngừng")
				|| str.toLowerCase().equals("toàn màn hình")
				|| str.toLowerCase().equals("phóng to màn hình")
				|| str.toLowerCase().equals("trang tiếp theo")
				|| str.toLowerCase().equals("trang sau")
				|| str.toLowerCase().equals("trang phía sau")
				|| str.toLowerCase().equals("trang phía dưới")
				|| str.toLowerCase().equals("tua đi")
				|| str.toLowerCase().equals("tua nhanh")
				|| str.toLowerCase().equals("tua về")
				|| str.toLowerCase().equals("tua chậm")
				|| str.toLowerCase().equals("trang trước")
				|| str.toLowerCase().equals("trang phía trước")
				|| str.toLowerCase().equals("trang phía trên")
				|| str.toLowerCase().equals("chạy")
				|| str.toLowerCase().equals("tiếp tục")
				|| str.toLowerCase().equals("tăng âm")
				|| str.toLowerCase().equals("tăng âm lượng")
				|| str.toLowerCase().equals("tăng tiếng")
				|| str.toLowerCase().equals("to tiếng")
				|| str.toLowerCase().equals("giảm âm")
				|| str.toLowerCase().equals("giảm tiếng")
				|| str.toLowerCase().equals("nhỏ tiếng")
				|| str.toLowerCase().equals("giảm âm lượng")
				|| str.toLowerCase().equals("xem truyền hình")
				|| str.toLowerCase().equals("xem ti vi")
				|| str.toLowerCase().equals("xem chương trình")
				|| str.toLowerCase().equals("im lặng")
				|| str.toLowerCase().equals("mở")) {
			behavior = "BK_TBG_GatDau";
		} else if (str.toLowerCase().contains("bình thường")
				|| str.toLowerCase().contains("trời đẹp")) {
			behavior = "BK_TBG_TroiDep";
		} else if (str.toLowerCase().contains("nhớ mang theo ô nhé")) {
			behavior = "BK_TBG_MangO";
		} else if (str.toLowerCase().contains("trời nóng thật")) {
			behavior = "BK_TBG_TroiNong";
		} else if (str.toLowerCase().contains("thịt chim cánh cụt")
				|| str.toLowerCase().contains("thịt cá voi")
				|| str.toLowerCase().contains("bánh đa")
				|| str.toLowerCase().contains("vừa ăn vừa uống rượu")
				|| str.toLowerCase().contains("thích lắm")) {
			behavior = "BK_TBG_TroiDep";
		} else if (str.toLowerCase().contains("cố lên")) {
			behavior = "BK_TBG_Fighting";
		} else if (str.toLowerCase().contains("hãy đi ra ngoài")
				|| str.toLowerCase().contains("lên giường")
				|| str.toLowerCase().contains("lăn vào bếp")
				|| str.toLowerCase().contains("ra quán trà đá")
				|| str.toLowerCase().contains("nói chuyện khác đi")
				|| str.toLowerCase().contains("kiếm chỗ nào mát")
				|| str.toLowerCase().contains("đi bác sĩ")
				|| str.toLowerCase().contains("nghỉ ngơi đi")
				|| str.toLowerCase().contains("ngủ đi")
				|| str.toLowerCase().contains("ngủ thôi")) {
			behavior = "BK_TBG_GoOut";
		} else if (str.toLowerCase().contains("tôi không biết")
				|| str.toLowerCase().contains("làm sao mà biết được")
				|| str.toLowerCase().contains("quên rồi")
				|| str.toLowerCase().contains("không nhớ")
				|| str.toLowerCase().contains("mình chẳng hiểu bạn nói")) {
			behavior = "BK_TBG_LacDau";
		} else if (str.toLowerCase().contains("tức giận chỉ hại sức khỏe")
				|| str.toLowerCase().contains("hạ hỏa")
				|| str.toLowerCase().contains("giải tỏa nỗi bực dọc")) {
			behavior = "BK_TBG_CalmDown";
		} else if (str.toLowerCase().contains("chán bạn quá")
				|| str.toLowerCase().contains("chán thật")
				|| str.toLowerCase().contains("chán đời")
				|| str.toLowerCase().contains("số phận hẩm hiu")
				|| str.toLowerCase().contains("vô duyên quá")
				|| str.toLowerCase().contains("chán quá")) {
			behavior = "BK_TBG_LacDau";
		} else if (str.toLowerCase().contains("chúc một ngày tốt đẹp")
				|| str.toLowerCase().contains("tuyệt vời")
				|| str.toLowerCase().contains("bố tôi là những thiên tài")
				|| str.toLowerCase().contains("vui tính")
				|| str.toLowerCase().contains("dễ mến")
				|| str.toLowerCase().contains("những thứ tôi thích ăn")
				|| str.toLowerCase().contains("tôi thích ăn nhiều thứ")
				|| str.toLowerCase().contains("vui nhất")
				|| str.toLowerCase().contains("ngày đấy may mắn")) {
			behavior = "BK_TBG_TroiDep";
		} else if (str.toLowerCase().contains("tôi không có mẹ")
				|| str.toLowerCase().contains("không muốn nói chuyện với tôi")
				|| str.toLowerCase().contains("yêu đơn phương")
				|| str.toLowerCase().contains("buồn quá")) {
			behavior = "BK_TBG_LacDau";
		} else if (str.toLowerCase().equals("tắt")) {
			try {
				RobotPosture.goToPosture(robot, "Crouch", 0.5f);
				RobotMotionStiffnessController.rest(robot);
			} catch (RobotException e) {
				e.printStackTrace();
			}
		} else {
			behavior = "BK_TBG_Explain";
		}
		try {
			Log.i("Behaviors", "Đã thực hiên động tác");
			RobotBehavior.runBehavior(robot, behavior);
		} catch (RobotException e) {
			Log.e("Có lỗi ở phần cử động rồi : ", e.toString());
			e.printStackTrace();
		}
	}
}

package SDK_TV;

import android.content.Context;
import android.widget.Toast;

import com.fpt.robot.Robot;
import com.fpt.robot.RobotException;
import com.fpt.robot.infrared.RobotInfrared;
import com.iansd.Util;

public class Controller {
	public static Robot currentRobot;
	public static Context context;

	private static String selectedRemoteName = null;
	private static String KEY_POWER = null;
	private static String KEY_UP = null;
	private static String KEY_DOWN = null;
	private static String KEY_MUTE = null;
	private static String KEY_RIGHT = null;
	private static String KEY_LEFT = null;
	private static String KEY_OK = null;
	private static String KEY_SMARTHUB = null;
	private static String KEY_KEYPAD = null;
	private static String KEY_EXIT = null;
	private static String KEY_Search = null;

	public static void pressKey(String keyName) {
		if (keyName.equals("power")) {

		} else if (keyName.equals("mute")) {

		} else if (keyName.equals("search")) {

		} else if (keyName.equals("exit")) {

		} else if (keyName.equals("up")) {

		} else if (keyName.equals("down")) {

		} else if (keyName.equals("left")) {

		} else if (keyName.equals("right")) {

		} else if (keyName.equals("keypad")) {

		} else if (keyName.equals("smarthub")) {

		} 
	}

	private void sendRemoteKey(final String remoteName, final String keyName) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				if (currentRobot == null) {
					Toast.makeText(context, "Hãy kết nối tới Robot trước",
							Toast.LENGTH_SHORT).show();
				} else {
					boolean result = false;
					try {
						result = RobotInfrared.sendRemoteKey(currentRobot,
								remoteName, keyName);
					} catch (final RobotException e) {
						e.printStackTrace();
						Util.log("Đã gửi lệnh bằng mã IR thành công");
						return;
					}
					if (!result) {
						Toast.makeText(context,
								"Gửi lệnh thất bại , hãy kiểm tra lại mạng !",
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		}).start();
	}

}

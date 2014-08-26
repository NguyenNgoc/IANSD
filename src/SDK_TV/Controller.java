package SDK_TV;

import android.content.Context;

import com.fpt.robot.Robot;
import com.fpt.robot.RobotException;
import com.fpt.robot.infrared.RobotInfrared;
import com.iansd.TV;
import com.iansd.Util;

public class Controller {
	public static Robot currentRobot;
	public static Context context;

	private static String remoteName = "Samsung_BN59-00603A";
	private static String KEY_POWER = "KEY_POWER";
	private static String KEY_UP = "KEY_UP";
	private static String KEY_DOWN = "KEY_DOWN";
	private static String NEXT_CH = "KEY_CHANNELUP";
	private static String PREV_CH = "KEY_CHANNELDOWN";
	private static String KEY_MUTE = "KEY_MUTE";
	private static String KEY_RIGHT = "KEY_RIGHT";
	private static String KEY_LEFT = "KEY_LEFT";
	private static String VOL_UP = "KEY_VOLUMEUP";
	private static String VOL_DOWN = "KEY_VOLUMEDOWN";
	private static String KEY_OK = "Enter/OK";
	private static String KEY_EXIT = "KEY_EXIT";
	private static String KEY_MENU = "KEY_MENU";

	public static void pressKey(String keyName) {
		if (keyName.equals("power")) {
			sendRemoteKey(remoteName, KEY_POWER);
		} else if (keyName.equals("mute")) {
			sendRemoteKey(remoteName, KEY_MUTE);
		} else if (keyName.equals("exit")) {
			sendRemoteKey(remoteName, KEY_EXIT);
		} else if (keyName.equals("up")) {
			sendRemoteKey(remoteName, KEY_UP);
		} else if (keyName.equals("down")) {
			sendRemoteKey(remoteName, KEY_DOWN);
		} else if (keyName.equals("left")) {
			sendRemoteKey(remoteName, KEY_LEFT);
		} else if (keyName.equals("right")) {
			sendRemoteKey(remoteName, KEY_RIGHT);
		} else if (keyName.equals("vol_up")) {
			sendRemoteKey(remoteName, VOL_UP);
		} else if (keyName.equals("vol_down")) {
			sendRemoteKey(remoteName, VOL_DOWN);
		} else if (keyName.equals("next_ch")) {
			sendRemoteKey(remoteName, NEXT_CH);
		} else if (keyName.equals("prev_ch")) {
			sendRemoteKey(remoteName, PREV_CH);
		} else if (keyName.equals("ok")) {
			sendRemoteKey(remoteName, KEY_OK);
		} else if (keyName.equals("menu")) {
			sendRemoteKey(remoteName, KEY_MENU);
		}
	}

	private static void sendRemoteKey(final String remoteName,
			final String keyName) {
		try {
			RobotInfrared.sendRemoteKey(currentRobot, remoteName, keyName);
		} catch (RobotException e) {
			Util.notifyError("Không gửi được mã IR");
			e.printStackTrace();
		}
	}
}

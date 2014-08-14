package SDK_TV;

import java.util.ArrayList;

public class IR_Keys {
	public static ArrayList<String> key_power = new ArrayList<String>();
	public static ArrayList<String> key_up = new ArrayList<String>();
	public static ArrayList<String> key_down = new ArrayList<String>();
	public static ArrayList<String> key_right = new ArrayList<String>();
	public static ArrayList<String> key_left = new ArrayList<String>();
	public static ArrayList<String> key_mute = new ArrayList<String>();
	public static ArrayList<String> key_ok = new ArrayList<String>();
	public static ArrayList<String> key_exit = new ArrayList<String>();
	public static ArrayList<String> key_search = new ArrayList<String>();
	public static ArrayList<String> key_smarthub = new ArrayList<String>();
	public static ArrayList<String> key_keypad = new ArrayList<String>();

	public static void init() {
		addDictKeyMute();
		addDictKeyPower();
	}

	

	private static void addDictKeyPower() {
		key_power.add("KEY_POWER");
		key_power.add("POWER");
		key_power.add("power");
	}

	private static void addDictKeyMute() {
		key_mute.add("KEY_MUTE");
		key_mute.add("MUTE");
		key_mute.add("mute");
	}
}

package com.sainti.blackcard.mtuils;

import android.util.Log;

public class MLog {
	private static final boolean DEBUG = true;

	public static final void d(String tag, String msg) {
		if (DEBUG) {
			Log.d(tag, msg);
		}
	}

	public static final void e(String tag, String msg) {
		if (DEBUG) {
			Log.e(tag, msg);
		}
	}

	public static void v(String tag, String msg) {
		if (DEBUG) {
			Log.v(tag, msg);
		}
	}
}

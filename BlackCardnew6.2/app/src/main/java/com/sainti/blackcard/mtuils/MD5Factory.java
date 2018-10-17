package com.sainti.blackcard.mtuils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Factory {
	private String str = "";
	byte bt[] = null;
	private String result = "";

	public MD5Factory(String str) {
		super();
		this.str = str;
	}

	public void digestStr() {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes("GBK"));
			bt = md.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			md.reset();
		}

	}

	public String getResult() {
		result = "";
		for (int i = 0; i < bt.length; i++) {
			result += Integer.toHexString((bt[i] & 0x00000000ff) | 0xffffff00)
					.substring(6);
		}
		return result;
	}

}
package com.bigshark.budejie_mvp.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Util {
	public static boolean isMobileNO(String mobiles) {
	    /* 
	    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188 
	    联通：130、131、132、152、155、156、185、186 
	    电信：133、153、180、189、（1349卫通） 
	    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9 
	    */  
	    String telRegex = "[1][3458]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
	    if (mobiles.equals(""))
	    	return false;  
	    else 
	    	return mobiles.matches(telRegex);  
	   }
	
	/**
	 * 对密码加密
	 * 
	 * @param salt
	 * @param pass
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String getPasswordByMd5(String salt, String pass)
			throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte md5[] = md.digest(pass.getBytes());
		byte codes[] = md.digest(salt.getBytes());
		byte[] passwordMd5 = md
				.digest((byte32toString(md5) + byte32toString(codes))
						.getBytes());
		String password = byte32toString(passwordMd5);
		return password;
	}

	public static String byte32toString(byte[] b) {
		StringBuffer buf = new StringBuffer("");
		for (int offset = 0; offset < b.length; offset++) {
			int i = b[offset];
			if (i < 0)
				i += 256;
			if (i < 16)
				buf.append("0");
			buf.append(Integer.toHexString(i));
		}
		return buf.toString();
	}
	
	
	public static String getCurrentTime(String format) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
		String currentTime = sdf.format(date);
		return currentTime;
	}

	public static String getCurrentTime() {
		return getCurrentTime("yyyy-MM-dd  HH:mm:ss");
	}
	
}

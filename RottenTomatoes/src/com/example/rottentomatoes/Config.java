/*
a. Midterm
b. Config.java
c. Udaykumar Bhupendra Kumar
 */

package com.example.rottentomatoes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Config {

	public static final String YOUR49ERUSERNAME = "ubhupend";
	public static final String SEED = "127301982675016259572345";

	public static final String getUid() {
		return Config.md5(YOUR49ERUSERNAME + SEED);
	}

	public static final String md5(final String s) {
		try {
			// Create MD5 Hash
			MessageDigest digest = java.security.MessageDigest
					.getInstance("MD5");
			digest.update(s.getBytes());
			byte messageDigest[] = digest.digest();

			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < messageDigest.length; i++) {
				String h = Integer.toHexString(0xFF & messageDigest[i]);
				while (h.length() < 2)
					h = "0" + h;
				hexString.append(h);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
}
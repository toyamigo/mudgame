package be.vdab.util;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

public class Paswoord {

	public static String paswoordEncoder(String paswoord, String userName) {
		ShaPasswordEncoder encoder = new ShaPasswordEncoder();
		return encoder.encodePassword(paswoord, userName);
	}

}

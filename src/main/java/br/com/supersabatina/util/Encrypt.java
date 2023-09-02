package br.com.supersabatina.util;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public final class Encrypt {

	public static String encrypt(String password) {
		Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(password.getBytes());
	}
	
	public static String decrypt(String encryptedPassword) {
		Decoder decoder = Base64.getDecoder();
		byte[] bytes = decoder.decode(encryptedPassword);
		return new String(bytes);
	}
}

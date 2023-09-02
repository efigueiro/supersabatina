package br.com.supersabatina.util;

import java.util.ArrayList;
import java.util.List;

public final class Messenger {

	public static List<String> messageList = new ArrayList<String>();
	public static String divClass;
	public static String divRole;

	private Messenger() {

	}

	public static void addSuccessMessage(String message) {

		messageList.add(message);
		divClass = "alert alert-success";
		divRole = "alert";

	}

	public static void addInfoMessage(String message) {

		messageList.add(message);
		divClass = "alert alert-info";
		divRole = "alert";

	}

	public static void addWarningMessage(String message) {

		messageList.add(message);
		divClass = "alert alert-warning";
		divRole = "alert";

	}

	public static void addDangerMessage(String message) {

		messageList.add(message);
		divClass = "alert alert-danger";
		divRole = "alert";

	}

	public static void resetMessenger() {
		messageList.clear();
		divRole = "";
		divClass = "";
	}

}

package br.com.supersabatina.util;

import java.text.DecimalFormat;

public final class Statistic {

	public static String successRate(int correctAnswerCount, int incorrectAnswerCount) {
		
		int attempts = correctAnswerCount + incorrectAnswerCount;

        double sucessRate = ((double) correctAnswerCount / attempts) * 100;
        
        DecimalFormat format = new DecimalFormat("##");

        String strSuccessRate = format.format(sucessRate);
		
		return strSuccessRate;
	}

	public static String failureRate(int correctAnswerCount, int incorrectAnswerCount) {
		
		int attempts = correctAnswerCount + incorrectAnswerCount;

        double failureRate = ((double) incorrectAnswerCount / attempts) * 100;
        
        DecimalFormat format = new DecimalFormat("##");

        String strFailureRate = format.format(failureRate);
		
		return strFailureRate;
	}
}

package br.com.supersabatina.service;

public class RevisionDateService {
	
	

	public void validateRevisionDate(long questionGroupId, long questionId) {

		int correctAnswerCount = 1; // Número inteiro de 1 a 5

		switch (correctAnswerCount) {
		case 1:
			System.out.println("Número um");
			break;
		case 2:
			System.out.println("Número dois");
			break;
		case 3:
			System.out.println("Número três");
			break;
		case 4:
			System.out.println("Número quatro");
			break;
		case 5:
			System.out.println("Número cinco");
			break;
		default:
			System.out.println("Número fora do intervalo de 1 a 5");
			break;
		}

	}

}

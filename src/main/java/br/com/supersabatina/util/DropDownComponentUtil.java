package br.com.supersabatina.util;

import java.util.ArrayList;
import java.util.List;

import br.com.supersabatina.model.entity.Option;

public final class DropDownComponentUtil {

	private DropDownComponentUtil() {
		
	}

	public static List<Option> getRetrieveQuestionScreenVisibilityOptionList() {
		
		// Creating retrieveQuestionScreenVisibilityOptionList
		List<Option> retrieveQuestionScreenVisibilityOptionList = new ArrayList<Option>();
		Option option1 = new Option();
		option1.setValue("all");
		option1.setLabel("Buscar em todas as perguntas");
		retrieveQuestionScreenVisibilityOptionList.add(option1);

		Option option2 = new Option();
		option2.setValue("private");
		option2.setLabel("Buscar somente em minhas perguntas");
		retrieveQuestionScreenVisibilityOptionList.add(option2);

		Option option3 = new Option();
		option3.setValue("public");
		option3.setLabel("Buscar somente em perguntas públicas");
		retrieveQuestionScreenVisibilityOptionList.add(option3);
		
		return retrieveQuestionScreenVisibilityOptionList;
	}
}

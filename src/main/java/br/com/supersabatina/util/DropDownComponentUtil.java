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
		option1.setValue("private");
		option1.setLabel("Buscar somente em minhas perguntas");
		retrieveQuestionScreenVisibilityOptionList.add(option1);

		Option option2 = new Option();
		option2.setValue("all");
		option2.setLabel("Busca global");
		retrieveQuestionScreenVisibilityOptionList.add(option2);

		return retrieveQuestionScreenVisibilityOptionList;
	}
}

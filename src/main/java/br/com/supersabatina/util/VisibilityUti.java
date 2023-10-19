package br.com.supersabatina.util;

import java.util.ArrayList;
import java.util.List;

import br.com.supersabatina.model.entity.Option;

public class VisibilityUti {

	List<Option> visibilityList = new ArrayList<Option>();

	public VisibilityUti() {
		Option visibility1 = new Option();
		visibility1.setValue("all");
		visibility1.setLabel("Buscar em todas as perguntas");
		visibilityList.add(visibility1);

		Option visibility2 = new Option();
		visibility2.setValue("private");
		visibility2.setLabel("Buscar somente em minhas perguntas");
		visibilityList.add(visibility2);

		Option visibility3 = new Option();
		visibility3.setValue("public");
		visibility3.setLabel("Buscar somente em perguntas públicas");
		visibilityList.add(visibility3);
	}

	public List<Option> getVisibilityList() {
		return visibilityList;
	}

	public void setVisibilityList(List<Option> visibilityList) {
		this.visibilityList = visibilityList;
	}

}

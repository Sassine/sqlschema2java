package dev.sassine.api.structure.model.java;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityModel {

	private String name;
	private String pkPolicy;
	private String pkType;

	private List<FieldModel> fields = new ArrayList<>();

	public FieldModel getFieldForName(final String name) {
		return fields.stream().filter(field -> field.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}

	public EntityModel(String name, String pkPolicy) {
		super();
		this.name = name;
		this.pkPolicy = pkPolicy;
	}
}

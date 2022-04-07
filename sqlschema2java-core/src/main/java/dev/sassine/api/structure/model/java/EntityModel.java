package dev.sassine.api.structure.model.java;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EntityModel {

	private String name;
	private String pkPolicy;
	private String pkType;

	private List<FieldModel> fields = new ArrayList<FieldModel>();

	public FieldModel getFieldForName(final String name) {
		return fields.stream().filter(field -> field.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}

	public EntityModel(String name, String pkPolicy) {
		super();
		this.name = name;
		this.pkPolicy = pkPolicy;
	}
}

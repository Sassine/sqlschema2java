package dev.sassine.api.structure.model.java;

import java.util.ArrayList;
import java.util.List;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EntityModel {

	private String name;
	private String pkPolicy;
	private String pkType;

	private List<FieldModel> fields = new ArrayList<FieldModel>();

	public FieldModel getFieldForName(final String name) {
		return fields.stream().filter(fl -> fl.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public List<FieldModel> getFields() {
		return fields;
	}

	public void setFields(final List<FieldModel> fields) {
		this.fields = fields;
	}

	public String getPkPolicy() {
		return pkPolicy;
	}

	public void setPkPolicy(final String pkPolicy) {
		this.pkPolicy = pkPolicy;
	}


	public String getPkType() {
		return pkType;
	}

	public void setPkType(String pkType) {
		this.pkType = pkType;
	}

	public EntityModel(String name, String pkPolicy) {
		super();
		this.name = name;
		this.pkPolicy = pkPolicy;
	}
}

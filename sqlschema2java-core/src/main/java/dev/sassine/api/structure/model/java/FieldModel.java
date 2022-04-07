package dev.sassine.api.structure.model.java;

import static org.apache.commons.text.CaseUtils.toCamelCase;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FieldModel {

	private static final char CHAR = '_';
	
	private String name;
	private String type;
	private Boolean isPrimaryKey;
	private Boolean nullable;
	private String defaultValue;
	private Integer minOccurs;
	private String maxOccurs;

	public FieldModel(String name) {
		this.name = name;
	}

	public String getCamelName() {
		return toCamelCase(name, false, CHAR);
	}
	
	public String getCamelNameUpper() {
		return toCamelCase(name, true, CHAR);
	}

}

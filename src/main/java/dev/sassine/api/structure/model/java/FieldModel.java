package dev.sassine.api.structure.model.java;

import static org.apache.commons.text.CaseUtils.toCamelCase;

public class FieldModel {

	private static final char CHAR = '_';
	
	private String name;
	private String type;
	private Boolean isPrimaryKey;
	private Boolean nullable;
	private String defaultValue;
	private Integer minOccurs;
	private String maxOccurs;


	public String getCamelName() {
		return toCamelCase(name, false, CHAR);
	}
	
	public String getCamelNameUpper() {
		return toCamelCase(name, true, CHAR);
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public Boolean getIsPrimaryKey() {
		return isPrimaryKey;
	}

	public void setIsPrimaryKey(final Boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}

	public Boolean getNullable() {
		return nullable;
	}

	public void setNullable(final Boolean nullable) {
		this.nullable = nullable;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(final String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Integer getMinOccurs() {
		return minOccurs;
	}

	public void setMinOccurs(final Integer minOccurs) {
		this.minOccurs = minOccurs;
	}

	public String getMaxOccurs() {
		return maxOccurs;
	}

	public void setMaxOccurs(final String maxOccurs) {
		this.maxOccurs = maxOccurs;
	}

}

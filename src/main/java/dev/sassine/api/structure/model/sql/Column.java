package dev.sassine.api.structure.model.sql;

public class Column {

	private String name;
	private String type;
	private String length;
	private Boolean isNotNull = false;
	private String convertedType;
	private String defaultValue;

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

	public String getLength() {
		return length;
	}

	public void setLength(final String length) {
		this.length = length;
	}

	public Boolean getIsNotNull() {
		return isNotNull;
	}

	public void setIsNotNull(final Boolean isNotNull) {
		this.isNotNull = isNotNull;
	}

	public String getConvertedType() {
		return convertedType;
	}

	public void setConvertedType(final String convertedType) {
		this.convertedType = convertedType;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(final String defaultValue) {
		this.defaultValue = defaultValue;
	}

}

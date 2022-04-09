package dev.sassine.api.structure.model.sql;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Column {

	private String name;
	private String type;
	private String length;
	private Boolean isNotNull = false;
	private String convertedType;
	private String defaultValue;

}

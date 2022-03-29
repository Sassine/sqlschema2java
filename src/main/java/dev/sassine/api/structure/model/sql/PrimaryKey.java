package dev.sassine.api.structure.model.sql;

import java.util.ArrayList;
import java.util.List;

public class PrimaryKey {

	List<String> columnNames = new ArrayList<String>();

	public List<String> getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(final List<String> columnNames) {
		this.columnNames = columnNames;
	}

}

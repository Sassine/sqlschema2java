package dev.sassine.api.structure.model.sql;

import java.util.ArrayList;
import java.util.List;

public class Database {

	private List<TableModel> tables = new ArrayList<TableModel>();

	public TableModel getTableForName(final String tableName) {
		return tables.stream().filter(table -> table.getName().equalsIgnoreCase(tableName)).findAny().orElse(null);
	}

	public List<TableModel> getTables() {
		return tables;
	}

	public void setTables(final List<TableModel> tables) {
		this.tables = tables;
	}

}

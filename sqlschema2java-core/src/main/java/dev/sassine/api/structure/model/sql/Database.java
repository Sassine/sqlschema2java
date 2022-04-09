package dev.sassine.api.structure.model.sql;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Database {

	private List<TableModel> tables = new ArrayList<TableModel>();

	public TableModel getTableForName(final String tableName) {
		return tables.stream().filter(table -> table.getName().equalsIgnoreCase(tableName)).findAny().orElse(null);
	}

}

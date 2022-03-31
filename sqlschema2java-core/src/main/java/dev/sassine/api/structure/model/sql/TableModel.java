package dev.sassine.api.structure.model.sql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableModel {

	private String name;
	private Map<String,Column> columnByNames = new HashMap<String,Column>();
	private PrimaryKey primaryKey = new PrimaryKey();
	private List<ForeignKey> foreignKeys = new ArrayList<ForeignKey>();

	public ForeignKey getForeignKeyForColumnNameOrigin(final Column column) {
		for (ForeignKey fk : foreignKeys) {	
			if(fk.getColumnNameOrigins().stream().anyMatch(cl -> column.getName().equals(cl))) return fk;
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Map<String, Column> getColumnByNames() {
		return columnByNames;
	}

	public void setColumnByNames(final Map<String, Column> columnByNames) {
		this.columnByNames = columnByNames;
	}

	public PrimaryKey getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(final PrimaryKey primaryKey) {
		this.primaryKey = primaryKey;
	}

	public List<ForeignKey> getForeignKeys() {
		return foreignKeys;
	}

	public void setForeignKeys(final List<ForeignKey> foreignKeys) {
		this.foreignKeys = foreignKeys;
	}

}

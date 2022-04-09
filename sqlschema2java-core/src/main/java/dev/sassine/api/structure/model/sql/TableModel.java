package dev.sassine.api.structure.model.sql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}

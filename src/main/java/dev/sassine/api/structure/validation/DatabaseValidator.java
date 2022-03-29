package dev.sassine.api.structure.validation;

import dev.sassine.api.structure.model.sql.Database;
import dev.sassine.api.structure.model.sql.ForeignKey;
import dev.sassine.api.structure.model.sql.TableModel;

public class DatabaseValidator {


	public void validate(final Database database) {
		for(final TableModel table : database.getTables()) {
			validateTable(table);
		}
	}

	public void validateTable(final TableModel table) {
		
		if(table.getPrimaryKey().getColumnNames().size() > 1) {
			throw new RuntimeException("More than one column in the primary key!");
		}

		for(final ForeignKey foreignKey : table.getForeignKeys()) {
			if((foreignKey.getColumnNameOrigins().size() > 1) || (foreignKey.getColumnNameTargets().size() > 1)) {
				throw new RuntimeException("More than one column in the foreign key!");
			}
		}
	}

}

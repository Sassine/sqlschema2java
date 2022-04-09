package dev.sassine.api.structure.validation;

import static org.apache.logging.log4j.LogManager.getLogger;

import org.apache.logging.log4j.Logger;

import dev.sassine.api.structure.model.sql.Database;
import dev.sassine.api.structure.model.sql.ForeignKey;
import dev.sassine.api.structure.model.sql.TableModel;

public class DatabaseValidator {

	private static final Logger log = getLogger();
	
	public static void validate(final Database database) {
		for(final TableModel table : database.getTables()) {
			validateTable(table);
		}
	}

	public static void validateTable(final TableModel table) {
		if(table.getPrimaryKey().getColumnNames().size() > 1) {
			log.error("More than one column in the primary key!");
			throw new RuntimeException("More than one column in the primary key!");
		}

		for(final ForeignKey foreignKey : table.getForeignKeys()) {
			if((foreignKey.getColumnNameOrigins().size() > 1) || (foreignKey.getColumnNameTargets().size() > 1)) {
				log.error("More than one column in the foreign key!");
				throw new RuntimeException("More than one column in the foreign key!");
			}
		}
	}

}

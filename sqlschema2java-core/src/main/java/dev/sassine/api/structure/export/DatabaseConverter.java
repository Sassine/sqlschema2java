package dev.sassine.api.structure.export;

import static java.util.stream.Collectors.toList;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.List;

import org.apache.logging.log4j.Logger;

import dev.sassine.api.structure.model.java.EntityModel;
import dev.sassine.api.structure.model.java.FieldModel;
import dev.sassine.api.structure.model.sql.Column;
import dev.sassine.api.structure.model.sql.Database;
import dev.sassine.api.structure.model.sql.ForeignKey;
import dev.sassine.api.structure.model.sql.TableModel;

public class DatabaseConverter {
	
	private static final Logger log = getLogger();
	
	public static List<EntityModel> convert(final Database database) {
		log.debug("Converter Database :: Tables Size ({})",database.getTables().size());
		return database.getTables().stream().map(table -> {
			log.debug("Converter table ({}) ",table.getName());
			final EntityModel entity = new EntityModel(table.getName(),"user_generated_value");
			List<FieldModel> fields = entity.getFields();
			generateDefaultPK(table, fields);
			table.getColumnByNames().forEach((columnName,column) -> {
				final FieldModel field = new FieldModel(column.getName());
				final ForeignKey foreignKey = table.getForeignKeyForColumnNameOrigin(column);
				generateFK(column, field, foreignKey);
				flagColumnPK(table, columnName, field);
				flagNullable(column, field);
				setDefaultValueColumn(column, field);
				fields.add(field);
				log.debug("Field ({}) added", field.getName());
			});
			return entity;
		}).collect(toList());
	}

	private static void setDefaultValueColumn(final Column column, final FieldModel field) {
		log.debug("Set Default Value ({}) ",column.getDefaultValue());
		field.setDefaultValue(column.getDefaultValue());
	}

	private static void flagNullable(final Column column, final FieldModel field) {
		log.debug("Set Nullable flag");
		field.setNullable(!(column.getIsNotNull() != null) && column.getIsNotNull());
	}

	private static void flagColumnPK(final TableModel table, final String columnName, final FieldModel field) {
		if(table.getPrimaryKey().getColumnNames().stream().anyMatch(cl -> cl.equals(columnName))) 
			field.setIsPrimaryKey(true);
	}

	private static void generateFK(final Column column, final FieldModel field, final ForeignKey foreignKey) {
		log.debug("Generate FK");
		if(foreignKey == null && column.getConvertedType() != null) {
			field.setType(column.getConvertedType());
		} else {
			field.setType(foreignKey.getTableNameTarget());
			field.setMinOccurs(0);
			field.setMaxOccurs("*");
		}
	}

	private static void generateDefaultPK(final TableModel table, final List<FieldModel> fields) {
		log.debug("Check exist Primary Key");
		if (table.getPrimaryKey().getColumnNames().size() != 1) {
			log.debug("PrimaryKey not found, adding default field PK");
			final FieldModel field = new FieldModel();
			field.setName("id");
			field.setType("Integer");
			field.setIsPrimaryKey(true);
			field.setNullable(false);
			fields.add(field);
		}
	}

}

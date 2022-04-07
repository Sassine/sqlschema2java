package dev.sassine.api.structure.export;

import static java.util.stream.Collectors.toList;

import java.util.List;

import dev.sassine.api.structure.model.java.EntityModel;
import dev.sassine.api.structure.model.java.FieldModel;
import dev.sassine.api.structure.model.sql.Column;
import dev.sassine.api.structure.model.sql.Database;
import dev.sassine.api.structure.model.sql.ForeignKey;
import dev.sassine.api.structure.model.sql.TableModel;

public class DatabaseConverter {

	public static List<EntityModel> convert(final Database database) {
		return database.getTables().stream().map(table -> {
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
			});
			return entity;
		}).collect(toList());
	}

	private static void setDefaultValueColumn(final Column column, final FieldModel field) {
		field.setDefaultValue(column.getDefaultValue());
	}

	private static void flagNullable(final Column column, final FieldModel field) {
		field.setNullable(!(column.getIsNotNull() != null) && column.getIsNotNull());
	}

	private static void flagColumnPK(final TableModel table, final String columnName, final FieldModel field) {
		if(table.getPrimaryKey().getColumnNames().stream().anyMatch(cl -> cl.equals(columnName))) 
			field.setIsPrimaryKey(true);
	}

	private static void generateFK(final Column column, final FieldModel field, final ForeignKey foreignKey) {
		if(foreignKey == null && column.getConvertedType() != null) {
				field.setType(column.getConvertedType());
		} else {
			field.setType(foreignKey.getTableNameTarget());
			field.setMinOccurs(0);
			field.setMaxOccurs("*");
		}
	}

	private static void generateDefaultPK(final TableModel table, final List<FieldModel> fields) {
		if (table.getPrimaryKey().getColumnNames().size() != 1) {
			final FieldModel field = new FieldModel();
			field.setName("id");
			field.setType("Integer");
			field.setIsPrimaryKey(true);
			field.setNullable(false);
			fields.add(field);
		}
	}

}

package dev.sassine.api.structure.export;

import java.util.ArrayList;
import java.util.List;

import dev.sassine.api.structure.model.java.EntityModel;
import dev.sassine.api.structure.model.java.FieldModel;
import dev.sassine.api.structure.model.sql.Column;
import dev.sassine.api.structure.model.sql.Database;
import dev.sassine.api.structure.model.sql.ForeignKey;
import dev.sassine.api.structure.model.sql.TableModel;

public class DatabaseConverter {

	public List<EntityModel> convert(final Database database) {
		List<EntityModel> entitys = new ArrayList<>();
		for (final TableModel table : database.getTables()) {
			final EntityModel entity = new EntityModel(table.getName(),"user_generated_value");
			generateDefaultPK(table, entity);
			for (final String columnName : table.getColumnByNames().keySet()) {
				final Column column = table.getColumnByNames().get(columnName);
				final FieldModel field = new FieldModel(column.getName());
				final ForeignKey foreignKey = table.getForeignKeyForColumnNameOrigin(column);
				generateFK(column, field, foreignKey);
				flagColumnPK(table, columnName, field);
				flagNullable(column, field);
				setDefaultValueColumn(column, field);
				entity.getFields().add(field);
			}

			entitys.add(entity);
		}
		return entitys;
	}

	private void setDefaultValueColumn(final Column column, final FieldModel field) {
		field.setDefaultValue(column.getDefaultValue());
	}

	private void flagNullable(final Column column, final FieldModel field) {
		if ((column.getIsNotNull() != null) && column.getIsNotNull()) {
			field.setNullable(false);
		} else {
			field.setNullable(true);
		}
	}

	private void flagColumnPK(final TableModel table, final String columnName, final FieldModel field) {
		if (table.getPrimaryKey().getColumnNames().size() == 1) {
			for (final String columnNameInPrimaryKey : table.getPrimaryKey().getColumnNames()) {
				if (columnName.equals(columnNameInPrimaryKey)) {
					field.setIsPrimaryKey(true);
				}
			}
		}
	}

	private void generateFK(final Column column, final FieldModel field, final ForeignKey foreignKey) {
		if(foreignKey == null) {
			if(column.getConvertedType() != null) {
				field.setType(column.getConvertedType());
			}
		} else {
			field.setType(foreignKey.getTableNameTarget());
			field.setMinOccurs(0);
			field.setMaxOccurs("*");
		}
	}

	private void generateDefaultPK(final TableModel table, final EntityModel entity) {
		if (table.getPrimaryKey().getColumnNames().size() != 1) {
			final FieldModel field = new FieldModel();
			field.setName("id");
			field.setType("Integer");
			field.setIsPrimaryKey(true);
			field.setNullable(false);
			entity.getFields().add(field);
		}
	}

}

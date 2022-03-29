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
			final EntityModel entity = new EntityModel();
			entity.setName(table.getName());
			entity.setPkPolicy("user_generated_value");

			if (table.getPrimaryKey().getColumnNames().size() != 1) {
				final FieldModel field = new FieldModel();
				field.setName("id");
				field.setType("String");
				field.setIsPrimaryKey(true);
				field.setNullable(false);
				entity.getFields().add(field);
			}

			for (final String columnName : table.getColumnByNames().keySet()) {
				final Column column = table.getColumnByNames().get(columnName);
				final FieldModel field = new FieldModel();
				field.setName(column.getName());
				final ForeignKey foreignKey = table.getForeignKeyForColumnNameOrigin(column);
				if(foreignKey == null) {
					if(column.getConvertedType() != null) {
						field.setType(column.getConvertedType());
					}
				} else {
					field.setType(foreignKey.getTableNameTarget());
					field.setMinOccurs(0);
					field.setMaxOccurs("*");
				}
				
				if (table.getPrimaryKey().getColumnNames().size() == 1) {
					for (final String columnNameInPrimaryKey : table.getPrimaryKey().getColumnNames()) {
						if (columnName.equals(columnNameInPrimaryKey)) {
							field.setIsPrimaryKey(true);
						}
					}
				}

				if ((column.getIsNotNull() != null) && column.getIsNotNull()) {
					field.setNullable(false);
				} else {
					field.setNullable(true);
				}
				
				field.setDefaultValue(column.getDefaultValue());

				entity.getFields().add(field);
			}

			entitys.add(entity);
		}
		return entitys;
	}

}

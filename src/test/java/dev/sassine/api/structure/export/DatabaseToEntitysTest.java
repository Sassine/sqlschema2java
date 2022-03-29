package dev.sassine.api.structure.export;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import dev.sassine.api.structure.model.java.EntityModel;
import dev.sassine.api.structure.model.java.FieldModel;
import dev.sassine.api.structure.model.sql.Column;
import dev.sassine.api.structure.model.sql.Database;
import dev.sassine.api.structure.model.sql.ForeignKey;
import dev.sassine.api.structure.model.sql.TableModel;

public class DatabaseToEntitysTest {

	private DatabaseConverter databaseToEntitys = new DatabaseConverter();

	@Test
	public void getLines1() {
		final Database database = new Database();
		final List<EntityModel> entitys = databaseToEntitys.convert(database);
		assertTrue(entitys.isEmpty());
	}

	@Test
	public void getLines2() {
		final Database database = new Database();
		final TableModel table1 = new TableModel();
		database.getTables().add(table1);
		table1.setName("table 1");
		final List<EntityModel> entitys = databaseToEntitys.convert(database);
		assertEquals(1, entitys.size());
		final EntityModel entity = entitys.get(0);
		assertEquals("table 1", entity.getName());
		assertEquals("user_generated_value", entity.getPkPolicy());
		assertEquals(1, entity.getFields().size());
		final FieldModel id = entity.getFields().get(0);
		assertEquals("id", id.getName());
		assertEquals("String", id.getType());
		assertEquals(true, id.getIsPrimaryKey());
		assertEquals(false, id.getNullable());
		assertNull(id.getDefaultValue());
		assertNull(id.getMinOccurs());
		assertNull(id.getMaxOccurs());
	}

	@Test
	public void getLines3() {
		final Database database = new Database();
		final TableModel table1 = new TableModel();
		database.getTables().add(table1);
		table1.setName("table 1");
		final Column column1 = new Column();
		column1.setName("column 1");
		table1.getColumnByNames().put(column1.getName(), column1);
		final List<EntityModel> entitys = databaseToEntitys.convert(database);
		assertEquals(1, entitys.size());
		final EntityModel entity = entitys.get(0);
		assertEquals("table 1", entity.getName());
		assertEquals("user_generated_value", entity.getPkPolicy());
		assertEquals(2, entity.getFields().size());
		final FieldModel id = entity.getFields().get(0);
		assertEquals("id", id.getName());
		assertEquals("String", id.getType());
		assertEquals(true, id.getIsPrimaryKey());
		assertEquals(false, id.getNullable());
		assertNull(id.getDefaultValue());
		assertNull(id.getMinOccurs());
		assertNull(id.getMaxOccurs());
		final FieldModel field1 = entity.getFields().get(1);
		assertEquals("column 1", field1.getName());
		assertNull(field1.getType());
		assertNull(field1.getIsPrimaryKey());
		assertTrue(field1.getNullable());
		assertNull(field1.getDefaultValue());
		assertNull(field1.getMinOccurs());
		assertNull(field1.getMaxOccurs());
	}

	@Test
	public void getLines4_primaryKey() {
		final Database database = new Database();
		final TableModel table1 = new TableModel();
		database.getTables().add(table1);
		table1.setName("table 1");
		final Column column1 = new Column();
		column1.setName("column 1");
		table1.getColumnByNames().put(column1.getName(), column1);
		table1.getPrimaryKey().getColumnNames().add("column 1");
		final List<EntityModel> entitys = databaseToEntitys.convert(database);
		assertEquals(1, entitys.size());
		final EntityModel entity = entitys.get(0);
		assertEquals("table 1", entity.getName());
		assertEquals("user_generated_value", entity.getPkPolicy());
		assertEquals(1, entity.getFields().size());
		final FieldModel field1 = entity.getFields().get(0);
		assertEquals("column 1", field1.getName());
		assertNull(field1.getType());
		assertEquals(true, field1.getIsPrimaryKey());
		assertTrue(field1.getNullable());
		assertNull(field1.getDefaultValue());
		assertNull(field1.getMinOccurs());
		assertNull(field1.getMaxOccurs());
	}

	@Test
	public void getLines5_primaryKey_with_more_than_one_column() {
		final Database database = new Database();
		final TableModel table1 = new TableModel();
		database.getTables().add(table1);
		table1.setName("table 1");
		final Column column1 = new Column();
		column1.setName("column 1");
		table1.getColumnByNames().put(column1.getName(), column1);
		table1.getPrimaryKey().getColumnNames().add("column 1");
		table1.getPrimaryKey().getColumnNames().add("column 2");
		final List<EntityModel> entitys = databaseToEntitys.convert(database);
		assertEquals(1, entitys.size());
		final EntityModel entity = entitys.get(0);
		assertEquals("table 1", entity.getName());
		assertEquals("user_generated_value", entity.getPkPolicy());
		assertEquals(2, entity.getFields().size());
		final FieldModel id = entity.getFields().get(0);
		assertEquals("id", id.getName());
		assertEquals("String", id.getType());
		assertEquals(true, id.getIsPrimaryKey());
		assertEquals(false, id.getNullable());
		assertNull(id.getDefaultValue());
		assertNull(id.getMinOccurs());
		assertNull(id.getMaxOccurs());
		final FieldModel field1 = entity.getFields().get(1);
		assertEquals("column 1", field1.getName());
		assertNull(field1.getType());
		assertNull(field1.getIsPrimaryKey());
		assertTrue(field1.getNullable());
		assertNull(field1.getDefaultValue());
		assertNull(field1.getMinOccurs());
		assertNull(field1.getMaxOccurs());
	}

	@Test
	public void getLines_foreignKey() {
		final Database database = new Database();
		final TableModel table1 = new TableModel();
		database.getTables().add(table1);
		table1.setName("table 1");
		final Column column1 = new Column();
		column1.setName("column 1");
		table1.getColumnByNames().put(column1.getName(), column1);
		final ForeignKey foreignKey = new ForeignKey();
		foreignKey.setTableNameOrigin("tableNameOrigin");
		foreignKey.setTableNameTarget("tableNameTarget");
		foreignKey.getColumnNameOrigins().add("column 1");
		foreignKey.getColumnNameTargets().add("columnNameTarget");
		table1.getForeignKeys().add(foreignKey);
		final List<EntityModel> entitys = databaseToEntitys.convert(database);
		assertEquals(1, entitys.size());
		final EntityModel entity = entitys.get(0);
		assertEquals("table 1", entity.getName());
		assertEquals("user_generated_value", entity.getPkPolicy());
		assertEquals(2, entity.getFields().size());
		final FieldModel id = entity.getFields().get(0);
		assertEquals("id", id.getName());
		assertEquals("String", id.getType());
		assertEquals(true, id.getIsPrimaryKey());
		assertEquals(false, id.getNullable());
		assertNull(id.getDefaultValue());
		assertNull(id.getMinOccurs());
		assertNull(id.getMaxOccurs());
		final FieldModel field1 = entity.getFields().get(1);
		assertEquals("column 1", field1.getName());
		assertEquals("tableNameTarget", field1.getType());
		assertNull(field1.getIsPrimaryKey());
		assertTrue(field1.getNullable());
		assertNull(field1.getDefaultValue());
		assertEquals(Integer.valueOf(0), field1.getMinOccurs());
		assertEquals("*", field1.getMaxOccurs());
	}

	@Test
	public void getLines6() {
		final Database database = new Database();
		final TableModel table1 = new TableModel();
		database.getTables().add(table1);
		table1.setName("table 1");
		final Column column1 = new Column();
		table1.getColumnByNames().put(column1.getName(), column1);
		column1.setName("column 1");
		column1.setType("type");
		column1.setConvertedType("convertedType");
		column1.setLength("length");
		column1.setIsNotNull(true);
		column1.setDefaultValue("default");
		final List<EntityModel> entitys = databaseToEntitys.convert(database);
		assertEquals(1, entitys.size());
		final EntityModel entity = entitys.get(0);
		assertEquals("table 1", entity.getName());
		assertEquals("user_generated_value", entity.getPkPolicy());
		assertEquals(2, entity.getFields().size());
		final FieldModel id = entity.getFields().get(0);
		assertEquals("id", id.getName());
		assertEquals("String", id.getType());
		assertEquals(true, id.getIsPrimaryKey());
		assertEquals(false, id.getNullable());
		assertNull(id.getDefaultValue());
		assertNull(id.getMinOccurs());
		assertNull(id.getMaxOccurs());
		final FieldModel field1 = entity.getFields().get(1);
		assertEquals("column 1", field1.getName());
		assertEquals("convertedType", field1.getType());
		assertNull(field1.getIsPrimaryKey());
		assertEquals(false, field1.getNullable());
		assertEquals("default", field1.getDefaultValue());
		assertNull(field1.getMinOccurs());
		assertNull(field1.getMaxOccurs());
	}

	@Test
	public void getLines7() {
		final Database database = new Database();
		final TableModel table1 = new TableModel();
		database.getTables().add(table1);
		table1.setName("table 1");
		final Column column1 = new Column();
		table1.getColumnByNames().put(column1.getName(), column1);
		column1.setName("column 1");
		column1.setType("type");
		column1.setConvertedType("convertedType");
		column1.setLength("length");
		column1.setIsNotNull(false);
		column1.setDefaultValue("default");
		final List<EntityModel> entitys = databaseToEntitys.convert(database);
		assertEquals(1, entitys.size());
		final EntityModel entity = entitys.get(0);
		assertEquals("table 1", entity.getName());
		assertEquals("user_generated_value", entity.getPkPolicy());
		assertEquals(2, entity.getFields().size());
		final FieldModel id = entity.getFields().get(0);
		assertEquals("id", id.getName());
		assertEquals("String", id.getType());
		assertEquals(true, id.getIsPrimaryKey());
		assertEquals(false, id.getNullable());
		assertNull(id.getDefaultValue());
		assertNull(id.getMinOccurs());
		assertNull(id.getMaxOccurs());
		final FieldModel field1 = entity.getFields().get(1);
		assertEquals("column 1", field1.getName());
		assertEquals("convertedType", field1.getType());
		assertNull(field1.getIsPrimaryKey());
		assertEquals(true, field1.getNullable());
		assertEquals("default", field1.getDefaultValue());
		assertNull(field1.getMinOccurs());
		assertNull(field1.getMaxOccurs());
	}

	@Test
	public void getLines8() {
		final Database database = new Database();
		final TableModel table1 = new TableModel();
		database.getTables().add(table1);
		table1.setName("table 1");
		final Column column1 = new Column();
		table1.getColumnByNames().put(column1.getName(), column1);
		column1.setName("column 1");
		column1.setType("type");
		column1.setConvertedType("convertedType");
		column1.setLength("length");
		column1.setIsNotNull(false);
		column1.setDefaultValue("default");
		final ForeignKey foreignKey = new ForeignKey();
		foreignKey.setTableNameOrigin("tableNameOrigin");
		foreignKey.setTableNameTarget("tableNameTarget");
		foreignKey.getColumnNameOrigins().add("column 1");
		foreignKey.getColumnNameTargets().add("columnNameTarget");
		table1.getForeignKeys().add(foreignKey);
		final List<EntityModel> entitys = databaseToEntitys.convert(database);
		assertEquals(1, entitys.size());
		final EntityModel entity = entitys.get(0);
		assertEquals("table 1", entity.getName());
		assertEquals("user_generated_value", entity.getPkPolicy());
		assertEquals(2, entity.getFields().size());
		final FieldModel id = entity.getFields().get(0);
		assertEquals("id", id.getName());
		assertEquals("String", id.getType());
		assertEquals(true, id.getIsPrimaryKey());
		assertEquals(false, id.getNullable());
		assertNull(id.getDefaultValue());
		assertNull(id.getMinOccurs());
		assertNull(id.getMaxOccurs());
		final FieldModel field1 = entity.getFields().get(1);
		assertEquals("column 1", field1.getName());
		assertEquals("tableNameTarget", field1.getType());
		assertNull(field1.getIsPrimaryKey());
		assertEquals(true, field1.getNullable());
		assertEquals("default", field1.getDefaultValue());
		assertEquals(Integer.valueOf(0), field1.getMinOccurs());
		assertEquals("*", field1.getMaxOccurs());
	}

}

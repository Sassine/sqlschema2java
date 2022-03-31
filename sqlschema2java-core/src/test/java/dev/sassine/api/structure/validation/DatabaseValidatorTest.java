package dev.sassine.api.structure.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import dev.sassine.api.structure.model.sql.Database;
import dev.sassine.api.structure.model.sql.ForeignKey;
import dev.sassine.api.structure.model.sql.TableModel;

public class DatabaseValidatorTest {

	@Test
	public void testValidateTable_ok() {
		final DatabaseValidator databaseValidator = new DatabaseValidator();
		final Database database = new Database();
		final TableModel table = new TableModel();
		database.getTables().add(table);
		databaseValidator.validate(database);
	}

	@Test
	public void testValidateTable_pk_with_one_column() {
		final DatabaseValidator databaseValidator = new DatabaseValidator();
		final Database database = new Database();
		final TableModel table = new TableModel();
		database.getTables().add(table);
		table.getPrimaryKey().getColumnNames().add("c1");
		databaseValidator.validate(database);
	}

	@Test(expected = RuntimeException.class)
	public void testValidateTable_pk_with_two_columns() {
		try {
			final DatabaseValidator databaseValidator = new DatabaseValidator();
			final Database database = new Database();
			final TableModel table = new TableModel();
			database.getTables().add(table);
			table.getPrimaryKey().getColumnNames().add("c1");
			table.getPrimaryKey().getColumnNames().add("c2");

			databaseValidator.validate(database);

			fail("More than one column in the primary key did not throw!");
		} catch (RuntimeException re) {
			String message = "More than one column in the primary key!";
			assertEquals(message, re.getMessage());
			throw re;
		}

	}

	@Test
	public void testValidateTable_fk_with_one_column() {
		final DatabaseValidator databaseValidator = new DatabaseValidator();

		final Database database = new Database();
		final TableModel table = new TableModel();
		database.getTables().add(table);
		final ForeignKey foreignKey = new ForeignKey();
		table.getForeignKeys().add(foreignKey);
		foreignKey.getColumnNameOrigins().add("c1");
		foreignKey.getColumnNameTargets().add("c1");

		databaseValidator.validate(database);

	}

	@Test(expected = RuntimeException.class)
	public void testValidateTable_fk_with_two_columns() {
		try {
			final DatabaseValidator databaseValidator = new DatabaseValidator();
			final Database database = new Database();
			final TableModel table = new TableModel();
			database.getTables().add(table);
			final ForeignKey foreignKey = new ForeignKey();
			table.getForeignKeys().add(foreignKey);
			foreignKey.getColumnNameOrigins().add("c1");
			foreignKey.getColumnNameOrigins().add("c2");
			foreignKey.getColumnNameTargets().add("c1");
			foreignKey.getColumnNameTargets().add("c2");

			databaseValidator.validate(database);

			fail("More than one column in the foreign key did not throw!");
		} catch (RuntimeException re) {
			String message = "More than one column in the foreign key!";
			assertEquals(message, re.getMessage());
			throw re;
		}
	}

}

package dev.sassine.api.structure.parser;

import dev.sassine.api.structure.SqlBaseListener;
import dev.sassine.api.structure.SqlParser;
import dev.sassine.api.structure.SqlParser.Alter_table_add_constraintContext;
import dev.sassine.api.structure.SqlParser.Alter_table_stmtContext;
import dev.sassine.api.structure.SqlParser.Any_nameContext;
import dev.sassine.api.structure.SqlParser.Fk_origin_column_nameContext;
import dev.sassine.api.structure.SqlParser.Fk_target_column_nameContext;
import dev.sassine.api.structure.SqlParser.Foreign_tableContext;
import dev.sassine.api.structure.SqlParser.Indexed_columnContext;
import dev.sassine.api.structure.SqlParser.Source_table_nameContext;
import dev.sassine.api.structure.SqlParser.Table_constraint_foreign_keyContext;
import dev.sassine.api.structure.SqlParser.Table_constraint_primary_keyContext;
import dev.sassine.api.structure.model.sql.Column;
import dev.sassine.api.structure.model.sql.Database;
import dev.sassine.api.structure.model.sql.ForeignKey;
import dev.sassine.api.structure.model.sql.TableModel;
import dev.sassine.api.structure.util.Util;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AlterTableParseListener extends SqlBaseListener {

	private final SqlParser sqlParser;
	private final Database database;

	TableModel table;
	Column column;
	ForeignKey foreignKey;

	boolean inAlter_table_stmt = false; 
	boolean inAlter_table_add_constraint = false; 
	boolean inTable_constraint_primary_key = false; 
	boolean inTable_constraint_foreign_key = false; 


	public AlterTableParseListener(final SqlParser sqlParser, final Database database) {
		this.sqlParser = sqlParser;
		this.database = database;
	}

	@Override
	public void exitAny_name(final Any_nameContext ctx) {
		log.debug(" {} | context: {} " ,ctx.getText(), ctx.toInfoString(sqlParser));
	}


	@Override
	public void enterAlter_table_stmt(final Alter_table_stmtContext ctx) {
		inAlter_table_stmt = true;
	}

	@Override
	public void exitAlter_table_stmt(final Alter_table_stmtContext ctx) {
		inAlter_table_stmt = false;
	}

	@Override
	public void exitSource_table_name(final Source_table_nameContext ctx) {
		if(inAlter_table_stmt) {
			table = database.getTableForName(Util.unformatSqlName(ctx.getText()));
		}
	}


	@Override
	public void enterAlter_table_add_constraint(
			final Alter_table_add_constraintContext ctx) {
		inAlter_table_add_constraint = true;
	}

	@Override
	public void exitAlter_table_add_constraint(
			final Alter_table_add_constraintContext ctx) {
		inAlter_table_add_constraint = false;
	}

	@Override
	public void enterTable_constraint_primary_key(
			final Table_constraint_primary_keyContext ctx) {
		inTable_constraint_primary_key = true;
	}

	@Override
	public void exitTable_constraint_primary_key(final Table_constraint_primary_keyContext ctx) {
		inTable_constraint_primary_key = false;
	}

	@Override
	public void exitIndexed_column(final Indexed_columnContext ctx) {
		if(inAlter_table_stmt && inTable_constraint_primary_key) {
			final String columnName = Util.unformatSqlName(ctx.getText());
			table.getPrimaryKey().getColumnNames().add(columnName);
		}
	}


	@Override
	public void enterTable_constraint_foreign_key(final Table_constraint_foreign_keyContext ctx) {
		inTable_constraint_foreign_key = true;
		if (inAlter_table_stmt) {
			foreignKey = new ForeignKey();
			foreignKey.setTableNameOrigin(table.getName());
		}
	}

	@Override
	public void exitTable_constraint_foreign_key(final Table_constraint_foreign_keyContext ctx) {
		if(inAlter_table_stmt) {
			foreignKey.setTableNameOrigin(table.getName());
			table.getForeignKeys().add(foreignKey);
			foreignKey = null;
		}
		inTable_constraint_foreign_key = false;
	}

	@Override
	public void exitForeign_table(final Foreign_tableContext ctx) {
		if(inTable_constraint_foreign_key) {
			foreignKey.setTableNameTarget(Util.unformatSqlName(ctx.getText()));
		}
	}

	@Override
	public void exitFk_origin_column_name(final Fk_origin_column_nameContext ctx) {
		if(inTable_constraint_foreign_key) {
			foreignKey.getColumnNameOrigins().add(Util.unformatSqlName(ctx.getText()));
		}
	}

	@Override
	public void exitFk_target_column_name(
final Fk_target_column_nameContext ctx) {
		if(inTable_constraint_foreign_key) {
			foreignKey.getColumnNameTargets().add(Util.unformatSqlName(ctx.getText()));
		}
	}

}

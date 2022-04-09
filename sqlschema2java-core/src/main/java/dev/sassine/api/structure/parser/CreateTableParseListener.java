package dev.sassine.api.structure.parser;

import static java.lang.String.format;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dev.sassine.api.structure.SqlBaseListener;
import dev.sassine.api.structure.SqlParser;
import dev.sassine.api.structure.SqlParser.Any_nameContext;
import dev.sassine.api.structure.SqlParser.Column_constraint_not_nullContext;
import dev.sassine.api.structure.SqlParser.Column_constraint_primary_keyContext;
import dev.sassine.api.structure.SqlParser.Column_defContext;
import dev.sassine.api.structure.SqlParser.Column_default_valueContext;
import dev.sassine.api.structure.SqlParser.Column_nameContext;
import dev.sassine.api.structure.SqlParser.Create_table_stmtContext;
import dev.sassine.api.structure.SqlParser.Fk_origin_column_nameContext;
import dev.sassine.api.structure.SqlParser.Fk_target_column_nameContext;
import dev.sassine.api.structure.SqlParser.Foreign_key_clauseContext;
import dev.sassine.api.structure.SqlParser.Foreign_tableContext;
import dev.sassine.api.structure.SqlParser.Indexed_columnContext;
import dev.sassine.api.structure.SqlParser.NameContext;
import dev.sassine.api.structure.SqlParser.Table_constraint_foreign_keyContext;
import dev.sassine.api.structure.SqlParser.Table_constraint_primary_keyContext;
import dev.sassine.api.structure.SqlParser.Table_nameContext;
import dev.sassine.api.structure.SqlParser.Type_nameContext;
import dev.sassine.api.structure.SqlParser.UnknownContext;
import dev.sassine.api.structure.model.sql.Column;
import dev.sassine.api.structure.model.sql.Database;
import dev.sassine.api.structure.model.sql.ForeignKey;
import dev.sassine.api.structure.model.sql.TableModel;
import dev.sassine.api.structure.util.Util;

public class CreateTableParseListener extends SqlBaseListener {
	private static final Logger log = LogManager.getLogger();
	private static final String AUTOINCREMENT = "AUTOINCREMENT";
	private static final String FORMAT_COLUMN_TYPE = "%s %s";
	private final SqlParser sqlParser;
	private final Database database;

	private TableModel table;
	private Column column;
	private ForeignKey foreignKey;

	private boolean inCreateTable = false;
	private boolean inColumnDef = false;
	private boolean inTypeName = false;
	private boolean inTable_constraint_primary_key = false;

	public CreateTableParseListener(final SqlParser sqlParser, final Database database) {
		this.sqlParser = sqlParser;
		this.database = database;
	}

	@Override
	public void exitAny_name(final Any_nameContext ctx) {
		log.debug(" {} | context: {} ", ctx.getText(), ctx.toInfoString(sqlParser));
	}

	@Override
	public void exitUnknown(final UnknownContext ctx) {
		log.debug(" {} | context: {} ", ctx.getText(), ctx.toInfoString(sqlParser));
	}

	@Override
	public void enterCreate_table_stmt(final Create_table_stmtContext ctx) {
		inCreateTable = true;
		table = new TableModel();
	}

	@Override
	public void exitCreate_table_stmt(final Create_table_stmtContext ctx) {
		database.getTables().add(table);
		table = null;
		inCreateTable = false;
	}

	@Override
	public void exitTable_name(final Table_nameContext ctx) {
		if (inCreateTable) {
			table.setName(Util.unformatSqlName(ctx.getText()));
		}
	}

	@Override
	public void enterColumn_def(final Column_defContext ctx) {
		inColumnDef = true;
		if (inCreateTable) {
			column = new Column();
		}
	}

	@Override
	public void exitColumn_def(final Column_defContext ctx) {
		if (inCreateTable) {
			if ((column != null) && (column.getName() != null)) {
				table.getColumnByNames().put(column.getName(), column);
			}
			column = null;
		}
		inColumnDef = false;
	}

	@Override
	public void exitColumn_name(final Column_nameContext ctx) {
		if (inCreateTable && inColumnDef) {
			column.setName(Util.unformatSqlName(ctx.getText()));
		}
	}

	@Override
	public void enterType_name(final Type_nameContext ctx) {
		inTypeName = true;
	}

	@Override
	public void exitType_name(final Type_nameContext ctx) {
		inTypeName = false;
	}

	@Override
	public void exitName(final NameContext ctx) {
		if (inCreateTable && inColumnDef && inTypeName) {
			if (column.getType() == null) {
				column.setType(Util.unformatSqlName(ctx.getText()));
			} else {
				String ctxType = !ctx.getText().replace("_", "").equals(AUTOINCREMENT) ? Util.unformatSqlName(ctx.getText()) : "";
				column.setType(format(FORMAT_COLUMN_TYPE, column.getType(), ctxType)); 
			}
		}
	}

	@Override
	public void exitColumn_default_value(final Column_default_valueContext ctx) {
		if (inCreateTable && inColumnDef) {
			column.setDefaultValue(Util.unformatSqlName(ctx.getText()));
		}
	}

	@Override
	public void exitColumn_constraint_not_null(final Column_constraint_not_nullContext ctx) {
		if (inCreateTable && inColumnDef) {
			column.setIsNotNull(true);
		}
	}

	@Override
	public void exitColumn_constraint_primary_key(final Column_constraint_primary_keyContext ctx) {
		if (inCreateTable && inColumnDef) {
			table.getPrimaryKey().getColumnNames().add(column.getName());
		}
	}

	@Override
	public void enterTable_constraint_primary_key(final Table_constraint_primary_keyContext ctx) {
		inTable_constraint_primary_key = true;
	}

	@Override
	public void exitTable_constraint_primary_key(final Table_constraint_primary_keyContext ctx) {
		inTable_constraint_primary_key = false;
	}

	@Override
	public void exitIndexed_column(final Indexed_columnContext ctx) {
		if (inCreateTable && inTable_constraint_primary_key) {
			final String columnName = Util.unformatSqlName(ctx.getText());
			table.getPrimaryKey().getColumnNames().add(columnName);
		}
	}

	@Override
	public void enterTable_constraint_foreign_key(final Table_constraint_foreign_keyContext ctx) {
		if (inCreateTable) {
			foreignKey = new ForeignKey();
			foreignKey.setTableNameOrigin(table.getName());
		}
	}

	@Override
	public void exitTable_constraint_foreign_key(final Table_constraint_foreign_keyContext ctx) {
		if (inCreateTable) {
			foreignKey.setTableNameOrigin(table.getName());
			table.getForeignKeys().add(foreignKey);
			foreignKey = null;
		}
	}

	@Override
	public void enterForeign_key_clause(final Foreign_key_clauseContext ctx) {
		if (inCreateTable && inColumnDef) {
			foreignKey = new ForeignKey();
			foreignKey.setTableNameOrigin(table.getName());
			foreignKey.getColumnNameOrigins().add(column.getName());
		}
	}

	@Override
	public void exitForeign_key_clause(final Foreign_key_clauseContext ctx) {
		if (inCreateTable && inColumnDef) {
			foreignKey.setTableNameOrigin(table.getName());
			table.getForeignKeys().add(foreignKey);
			foreignKey = null;
		}
	}

	@Override
	public void exitForeign_table(final Foreign_tableContext ctx) {
		if (inCreateTable) {
			foreignKey.setTableNameTarget(Util.unformatSqlName(ctx.getText()));
		}
	}

	@Override
	public void exitFk_origin_column_name(final Fk_origin_column_nameContext ctx) {
		if (foreignKey != null) {
			foreignKey.getColumnNameOrigins().add(Util.unformatSqlName(ctx.getText()));
		}
	}

	@Override
	public void exitFk_target_column_name(final Fk_target_column_nameContext ctx) {
		if (inCreateTable) {
			foreignKey.getColumnNameTargets().add(Util.unformatSqlName(ctx.getText()));
		}
	}

}

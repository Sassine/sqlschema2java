package dev.sassine.api.structure.parser;

import static java.util.Objects.isNull;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.logging.log4j.Logger;

import dev.sassine.api.structure.SqlLexer;
import dev.sassine.api.structure.SqlParser;
import dev.sassine.api.structure.model.sql.Database;
import dev.sassine.api.structure.parser.error.SqlImportErrorListener;

public class SqlImport {
	private static final Logger log = getLogger();
	private static final String ALTER_TABLE = "ALTER TABLE";
	private static final String CREATE_TABLE = "CREATE TABLE";

	public static SqlImport init() {
		return new SqlImport();
	}
	
	public Database getDatabase(final String content) {
		if (isNull(content)) return null;
		final GetSqlQuery getSqlQuery = new GetSqlQuery();
		final List<String> querys = getSqlQuery.getSqlQuerys(content);
		return read(querys);
	}

	public Database read(final List<String> querys) {
		final Database database = new Database();
		for (final String query : querys) {
			readOneQuery(database, query);
		}
		return database;
	}

	public void readOneQuery(final Database database, final String query) {
		if (isNull(query)) return;
		
		final ANTLRInputStream in = new ANTLRInputStream(query);
		final SqlLexer lexler = new SqlLexer(in);
		final SqlParser parser = new SqlParser(new CommonTokenStream(lexler));
		parser.addErrorListener(new SqlImportErrorListener(query));

		log.debug(" Parse the query : \n {} ", query);

		if (query.toUpperCase().indexOf(CREATE_TABLE) == 0) {
			parser.addParseListener(new CreateTableParseListener(parser, database));
		} else if (query.toUpperCase().indexOf(ALTER_TABLE) == 0) {
			parser.addParseListener(new AlterTableParseListener(parser, database));
		} else {
			log.error(" No parse listener (Create or Alter Table) for the query : \n {} ", query);
			throw new RuntimeException("No parse listener for the query : " + query);
		}

		parser.parse();

	}

}

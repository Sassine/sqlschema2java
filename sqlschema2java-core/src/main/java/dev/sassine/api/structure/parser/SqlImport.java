package dev.sassine.api.structure.parser;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import dev.sassine.api.structure.SqlLexer;
import dev.sassine.api.structure.SqlParser;
import dev.sassine.api.structure.model.sql.Database;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SqlImport {

	private static final String ALTER_TABLE = "ALTER TABLE";
	private static final String CREATE_TABLE = "CREATE TABLE";

	public class SqlImportErrorListener extends BaseErrorListener {

		public String query;

		@Override
		public void syntaxError(final Recognizer<?, ?> recognizer, final Object offendingSymbol, final int line,
				final int charPositionInLine, final String msg, final RecognitionException e) {

			log.error(" Error on query: \n {} ", query);
			log.error(" Line: {} | {}", line, msg);
			
			if (nonNull(e) && nonNull(e.getCtx()))
				log.error(" Context: {} ", e.getCtx());
			
			if (nonNull(e) && nonNull(e.getMessage()))
				log.error(e.getMessage());

		}
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

		final SqlImportErrorListener listener = new SqlImportErrorListener();
		listener.query = query;
		parser.addErrorListener(listener);

		log.debug(" Parse the query : \n {} ", query);

		if (query.toUpperCase().indexOf(CREATE_TABLE) == 0) {
			parser.addParseListener(new CreateTableParseListener(parser, database));
		} else if (query.toUpperCase().indexOf(ALTER_TABLE) == 0) {
			parser.addParseListener(new AlterTableParseListener(parser, database));
		} else {
			log.error(" No parse listener for the query : \n {} ", query);
			throw new RuntimeException("No parse listener for the query : " + query);
		}

		parser.parse();

	}

}

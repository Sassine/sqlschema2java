package dev.sassine.api.structure.parser.error;

import static java.util.Objects.nonNull;
import static org.apache.logging.log4j.LogManager.getLogger;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.apache.logging.log4j.Logger;

public class SqlImportErrorListener extends BaseErrorListener {

	private static final Logger log = getLogger();

	private String query;
	
	public SqlImportErrorListener(String query) {
		this.query = query;
	}


	@Override
	public void syntaxError(final Recognizer<?, ?> recognizer, final Object offendingSymbol, final int line,
			final int charPositionInLine, final String msg, final RecognitionException e) {
		log.error(" Error on query: \n {} ", query);
		log.error(" Line: {} | {}", line, msg);
		if (nonNull(e) && nonNull(e.getCtx())) log.error(" Context: {} ", e.getCtx());
		if (nonNull(e) && nonNull(e.getMessage())) log.error(e.getMessage());
	}

}

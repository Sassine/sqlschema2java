package dev.sassine.api.structure.parser;

import java.util.ArrayList;
import java.util.List;

public class GetSqlQuery {

	private static final String ADD_FOREIGN_KEY = "ADD FOREIGN KEY";
	private static final String ADD_PRIMARY_KEY = "ADD PRIMARY KEY";
	private static final String PRIMARY_KEY = "PRIMARY KEY";
	private static final String FOREIGN_KEY = "FOREIGN KEY";
	private static final String ADD_CONSTRAINT = "ADD CONSTRAINT";
	private static final String ALTER_TABLE = "ALTER TABLE";
	private static final String CREATE_TABLE = "CREATE TABLE";

	public boolean isQueryFiltered(final String query) {
		final String queryUpperCase = query.toUpperCase();
		if(queryUpperCase.indexOf(CREATE_TABLE) == 0) {
			return false;
		}
		
		if(queryUpperCase.indexOf(ALTER_TABLE) == 0) {
			if(queryUpperCase.indexOf(ADD_CONSTRAINT) != -1) {
				if((queryUpperCase.indexOf(PRIMARY_KEY) != -1) || (queryUpperCase.indexOf(FOREIGN_KEY) != -1)) {
					return false;
				}
			}
			if(queryUpperCase.indexOf(ADD_PRIMARY_KEY) != -1) {
				return false;
			}
			if(queryUpperCase.indexOf(ADD_FOREIGN_KEY) != -1) {
				return false;
			}
		}

		return true;
	}

	public List<String> getSqlQuerys(final String content) {
		final List<String> querys = new ArrayList<String>();

		int posStart = getPosStartQuery(content, 0);
		int posEnd = getPosEndQuery(content, posStart);
		while((posStart != -1) && (posStart < content.length()) && (posEnd < content.length())) {
			final String query = content.substring(posStart, posEnd);
			final boolean isFiltered = isQueryFiltered(query);
			if(!isFiltered) {
				querys.add(query);
			}

			if((posEnd + 1) >= content.length()) {
				posStart = -1;
			} else {
				posStart = getPosStartQuery(content,posEnd+1);
				posEnd = getPosEndQuery(content, posStart);
			}
		}

		return querys;
	}
	
	private int getPosStartQuery(final String content, int pos) {

		boolean inLineComment = false;
		boolean inMultiLineComment = false;
		boolean inStringValue = false;

		while(pos < content.length()) {
			final char character = content.charAt(pos);
			if(character == '/') {
				if(!inStringValue && !inLineComment && !inMultiLineComment) {
					if(((pos+1) < content.length()) && (content.charAt(pos+1) == '*')) {
						inMultiLineComment = true;
						pos = pos+2;
						continue;
					}
				}
			}
			if(character == '*') {
				if(!inStringValue && !inLineComment && inMultiLineComment) {
					if(((pos+1) < content.length()) && (content.charAt(pos+1) == '/')) {
						inMultiLineComment = false;
						pos = pos+2;
						continue;
					}
				}
			}
			if(character == '"') {
				if(!inLineComment && !inMultiLineComment) {
					inStringValue = !inStringValue;
				}
			}
			if(character == '-') {
				if(!inStringValue && !inLineComment && !inMultiLineComment) {
					if(((pos+1) < content.length()) && (content.charAt(pos+1) == '-')) {
						inLineComment = true;
						pos = pos + 2;
						continue;
					}
				}
			}
			if((character == '\n') || (character == '\r') ) {
				if(inLineComment) {
					inLineComment = false;
				}
			}
			if(((character >= 'a') && (character <= 'z')) ||((character >= 'A') && (character <= 'Z'))) {
				if(!inStringValue && !inLineComment && !inMultiLineComment) {
					break;
				}
			}
			pos++;
		}

		return pos;
	}

	private int getPosEndQuery(final String content, int pos) {

		boolean inLineComment = false;
		boolean inMultiLineComment = false;
		boolean inStringValue = false;

		while(pos < content.length()) {
			final char character = content.charAt(pos);
			if(character == '/') {
				if(!inStringValue && !inLineComment && !inMultiLineComment) {
					if(((pos+1) < content.length()) && (content.charAt(pos+1) == '*')) {
						inMultiLineComment = true;
						pos = pos+2;
						continue;
					}
				}
			}
			if(character == '*') {
				if(!inStringValue && !inLineComment && inMultiLineComment) {
					if(((pos+1) < content.length()) && (content.charAt(pos+1) == '/')) {
						inMultiLineComment = false;
						pos = pos+2;
						continue;
					}
				}
			}
			if(character == '"') {
				if(!inLineComment && !inMultiLineComment) {
					inStringValue = !inStringValue;
				}
			}
			if(character == '-') {
				if(!inStringValue && !inLineComment && !inMultiLineComment) {
					if(((pos+1) < content.length()) && (content.charAt(pos+1) == '-')) {
						inLineComment = true;
						pos = pos + 2;
						continue;
					}
				}
			}
			if((character == '\n') || (character == '\r') ) {
				if(inLineComment) {
					inLineComment = false;
				}
			}
			if(character == ';') {
				if(!inStringValue && !inLineComment && !inMultiLineComment) {
					break;
				}
			}
			pos++;
		}

		return pos;
	}


}

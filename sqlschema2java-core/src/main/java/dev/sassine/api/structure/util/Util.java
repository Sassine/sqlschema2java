package dev.sassine.api.structure.util;

import static java.util.Objects.isNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Util {
	private static final Logger log = LogManager.getLogger();
	private static final char CHAR2 = '`';
	private static final char CHAR = '"';

	public static File getFileByClassPath(final String fileName) throws URISyntaxException {
		return new File(Util.class.getResource(fileName).toURI());
	}

	public static String read(final InputStream is) {
		final StringBuilder sb = new StringBuilder();
		String line;
		try (InputStreamReader isr = new InputStreamReader(is); BufferedReader br = new BufferedReader(isr);) {
			boolean isFirst = true;
			while ((line = br.readLine()) != null) {
				if (isFirst) isFirst = false;
				sb.append(isFirst ? "" : "\n").append(line);
			}
		} catch (final IOException e) {
			log.error(e.getMessage(), e);
		}

		return sb.toString();
	}

	public static String unformatSqlName(final String sqlName) {
		if (isNull(sqlName)) return null;
		else if ((sqlName.length() >= 2) && (((sqlName.charAt(0) == '"') && (sqlName.charAt(sqlName.length() - 1) == CHAR))
				|| ((sqlName.charAt(0) == '`') && (sqlName.charAt(sqlName.length() - 1) == CHAR2))))
			return sqlName.substring(1, sqlName.length() - 1);
		else return sqlName;
	}

}

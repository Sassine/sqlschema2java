package dev.sassine.api.structure.util;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Util {

	private static final char CHAR2 = '`';
	private static final char CHAR = '"';

	public static File getFileByClassPath(final String fileName) {
		final URL url = Util.class.getResource(fileName);
		if (nonNull(url)) {
			try {
				return new File(url.toURI());
			} catch (final URISyntaxException e) {
				log.error("Cannot convert URL to URI (file '{}' )", fileName);
				throw new RuntimeException("Cannot convert URL to URI (file '" + fileName + "')");
			}
		} else {
			log.error("File '{}' not found)", fileName);
			throw new RuntimeException("File '" + fileName + "' not found");
		}
	}

	public static InputStream getInputStream(final String filename) {
		try {
			return new FileInputStream(filename);
		} catch (final FileNotFoundException e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	public static OutputStream getOutputStream(final String filename) {
		try {
			final String path = filename.substring(0, filename.lastIndexOf(File.separatorChar));
			final File dir = new File(path);
			dir.mkdirs();
			final File file = new File(filename);
			return new FileOutputStream(file);
		} catch (final Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
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

	public static void write(final List<String> lines, final OutputStream os) {
		try (OutputStreamWriter out = new OutputStreamWriter(os); BufferedWriter bw = new BufferedWriter(out);) {
			boolean isFirst = true;
			for (final String line : lines) {
				if (isFirst) isFirst = false;
				bw.write(isFirst ? "" : "\n");
				bw.write(line);
			}
		} catch (final IOException e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	public static void write(final String content, final OutputStream os) {
		try (OutputStreamWriter out = new OutputStreamWriter(os); BufferedWriter bw = new BufferedWriter(out);) {
			if (content != null) {
				bw.write(content);
			}
		} catch (final IOException e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	public static String unformatSqlName(final String sqlName) {
		if (isNull(sqlName)) return null;
		else if ((sqlName.length() >= 2) && (((sqlName.charAt(0) == '"') && (sqlName.charAt(sqlName.length() - 1) == CHAR))
				|| ((sqlName.charAt(0) == '`') && (sqlName.charAt(sqlName.length() - 1) == CHAR2))))
			return sqlName.substring(1, sqlName.length() - 1);
		else return sqlName;
	}

}

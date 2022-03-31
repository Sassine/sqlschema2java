package dev.sassine.api.structure.util;

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

	public File getFileByClassPath(final String fileName) {
		final URL url = Util.class.getResource(fileName);
		if (nonNull(url)) {
			try {
				return new File(url.toURI());
			} catch (final URISyntaxException e) {
				log.error("Cannot convert URL to URI (file '{}' )" , fileName);
				throw new RuntimeException("Cannot convert URL to URI (file '" + fileName + "')");
			}
		}
		else {
			log.error("File '{}' not found)" , fileName);
			throw new RuntimeException("File '" + fileName + "' not found");
		}
	}

	public InputStream getInputStream(final String filename) {
		try {
			final File file = new File(filename);
			final InputStream in = new FileInputStream(file);
			return in;
		} catch (final FileNotFoundException e) {
			log.error(e.getMessage(),e);
			throw new RuntimeException(e);
		}
	}

	public OutputStream getOutputStream(final String filename) {
		try {

			final String path = filename.substring(0, filename.lastIndexOf(File.separatorChar));
			final File dir = new File(path);
			dir.mkdirs();

			final File file = new File(filename);
			return new FileOutputStream(file);

		} catch (final Exception e) {
			log.error(e.getMessage(),e);
			throw new RuntimeException(e);
		}
	}

	public String read(final InputStream is) {
		BufferedReader br = null;
		final StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			boolean isFirst = true;
			while ((line = br.readLine()) != null) {
				if(isFirst) {
					isFirst = false;
				} else {
					sb.append("\n");
				}
				sb.append(line);
			}

		} catch (final IOException e) {
			log.error(e.getMessage(),e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (final IOException e) {
					log.error(e.getMessage(),e);
				}
			}
		}

		return sb.toString();
	}

	public void write(final List<String> lines, final OutputStream os) {
		BufferedWriter bw = null;
		bw = new BufferedWriter(new OutputStreamWriter(os));
		try {
			boolean isFirst = true;
			for(final String line : lines) {
				if(isFirst) {
					isFirst = false;
				} else {
					bw.write("\n");
				}
				bw.write(line);
			}
			bw.close();
		} catch (final IOException e) {
			log.error(e.getMessage(),e);
			throw new RuntimeException(e);
		}
	}

	public void write(final String content, final OutputStream os) {
		BufferedWriter bw = null;
		bw = new BufferedWriter(new OutputStreamWriter(os));
		try {
			if(content != null) {
				bw.write(content);
			}
			bw.close();
		} catch (final IOException e) {
			log.error(e.getMessage(),e);
			throw new RuntimeException(e);
		}
	}

	public String unformatSqlName(final String sqlName) {
		if(sqlName == null) return null;
		String name = sqlName;
		if((name.length() >= 2) 		
		&& (((name.charAt(0) == '"') && (name.charAt(name.length()-1) == CHAR)) 
		|| ((name.charAt(0) == '`') && (name.charAt(name.length()-1) == CHAR2))))
			name = name.substring(1,name.length()-1);
		return name;
	}

}

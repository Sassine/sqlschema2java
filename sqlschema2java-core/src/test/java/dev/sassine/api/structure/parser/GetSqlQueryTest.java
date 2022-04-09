package dev.sassine.api.structure.parser;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Test;

import dev.sassine.api.structure.util.Util;


public class GetSqlQueryTest {

	private GetSqlQuery getSqlQuery = new GetSqlQuery();

	@Test
	public void testRead_standard() throws FileNotFoundException, URISyntaxException {
		final File file = Util.getFileByClassPath("/standard.sql");
		final InputStream in = new FileInputStream(file);
		final String sqlContent = Util.read(in);
		final List<String> lines = getSqlQuery.getSqlQuerys(sqlContent);
		assertEquals(4, lines.size());
	}

	@Test
	public void testRead_mysql() throws FileNotFoundException, URISyntaxException {
		final File file = Util.getFileByClassPath("/mysql1.sql");
		final InputStream in = new FileInputStream(file);
		final String sqlContent = Util.read(in);
		final List<String> lines = getSqlQuery.getSqlQuerys(sqlContent);
		assertEquals(6, lines.size());
	}

	@Test
	public void testRead_postgres() throws FileNotFoundException, URISyntaxException {
		final File file = Util.getFileByClassPath("/postgres.sql");
		final InputStream in = new FileInputStream(file);
		final String sqlContent = Util.read(in);
		final List<String> lines = getSqlQuery.getSqlQuerys(sqlContent);
		assertEquals(9, lines.size());
	}

	@Test
	public void testRead_oracle1() throws FileNotFoundException, URISyntaxException {
		final File file = Util.getFileByClassPath("/oracle1.sql");
		final InputStream in = new FileInputStream(file);
		final String sqlContent = Util.read(in);
		final List<String> lines = getSqlQuery.getSqlQuerys(sqlContent);
		assertEquals(10, lines.size());
	}

	@Test
	public void testRead_oracle2() throws FileNotFoundException, URISyntaxException {
		final File file = Util.getFileByClassPath("/oracle2.sql");
		final InputStream in = new FileInputStream(file);
		final String sqlContent = Util.read(in);
		final List<String> lines = getSqlQuery.getSqlQuerys(sqlContent);
		assertEquals(3, lines.size());
	}


}

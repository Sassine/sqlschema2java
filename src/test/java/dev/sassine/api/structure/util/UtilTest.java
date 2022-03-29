package dev.sassine.api.structure.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class UtilTest {

	private Util util = new Util();

	@Test
	public void testUnformatSql() {
		assertEquals(null, util.unformatSqlName(null));
		assertEquals("", util.unformatSqlName(""));
		
		assertEquals("\"", util.unformatSqlName("\""));
		assertEquals("", util.unformatSqlName("\"\""));
		assertEquals("table", util.unformatSqlName("table"));
		assertEquals("table", util.unformatSqlName("\"table\""));
		assertEquals("\"table", util.unformatSqlName("\"table"));

		assertEquals("`", util.unformatSqlName("`"));
		assertEquals("", util.unformatSqlName("``"));
		assertEquals("table", util.unformatSqlName("table"));
		assertEquals("table", util.unformatSqlName("`table`"));
		assertEquals("`table", util.unformatSqlName("`table"));
	}

}

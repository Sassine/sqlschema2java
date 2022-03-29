package dev.sassine.api.structure.type;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;


public class TypeConverterTest {

	private static final String UUID = "UUID";
	private static final String LOCAL_DATE_TIME = "LocalDateTime";
	private static final String BOOLEAN = "Boolean";
	private static final String FLOAT = "Float";
	private static final String STRING = "String";
	private static final String INTEGER = "Integer";

	@Test(expected = RuntimeException.class)
	public void testConvertTypeFromSQLtoEntityStoreUnknownAndNull() {
		final TypeConverter typeConverter = new TypeConverter();
		final String sqlType = null;
		assertNull(typeConverter.convertTypeFromSQLToEntityStore(sqlType,false));
		try {
			typeConverter.convertTypeFromSQLToEntityStore("UNKNOWN",false);
			fail("Unknown SQL type did not throw!");
		} catch (RuntimeException re) {
			String message = "Unknown SQL type UNKNOWN";
			assertEquals(message, re.getMessage());
			throw re;
		}
	}
	
	@Test
	public void testConvertTypeFromSQLToEntityStore() {
		final TypeConverter typeConverter = new TypeConverter();
		assertEquals(STRING, typeConverter.convertTypeFromSQLToEntityStore(" bfile ",false));
		assertEquals(INTEGER, typeConverter.convertTypeFromSQLToEntityStore(" bigint ",false));
		assertEquals(INTEGER, typeConverter.convertTypeFromSQLToEntityStore(" bigserial ",false));
		assertEquals(STRING, typeConverter.convertTypeFromSQLToEntityStore(" binary ",false));
		assertEquals(FLOAT, typeConverter.convertTypeFromSQLToEntityStore(" binary_double ",false));
		assertEquals(FLOAT, typeConverter.convertTypeFromSQLToEntityStore(" binary_float ",false));
		assertEquals(STRING, typeConverter.convertTypeFromSQLToEntityStore(" bit ",false));
		assertEquals(STRING, typeConverter.convertTypeFromSQLToEntityStore(" bit varying ",false));
		assertEquals(STRING, typeConverter.convertTypeFromSQLToEntityStore(" blob ",false));
		assertEquals(BOOLEAN, typeConverter.convertTypeFromSQLToEntityStore(" bool ",false));
		assertEquals(BOOLEAN, typeConverter.convertTypeFromSQLToEntityStore(" boolean ",false));
		assertEquals(STRING, typeConverter.convertTypeFromSQLToEntityStore(" bytea ",false));
		assertEquals(STRING, typeConverter.convertTypeFromSQLToEntityStore(" char ",false));
		assertEquals(STRING, typeConverter.convertTypeFromSQLToEntityStore(" character ",false));
		assertEquals(STRING, typeConverter.convertTypeFromSQLToEntityStore(" character varying ",false));
		assertEquals(STRING, typeConverter.convertTypeFromSQLToEntityStore(" clob ",false));
		assertEquals(LOCAL_DATE_TIME, typeConverter.convertTypeFromSQLToEntityStore(" date ",false));
		assertEquals(LOCAL_DATE_TIME, typeConverter.convertTypeFromSQLToEntityStore(" datetime ",false));
		assertEquals(FLOAT, typeConverter.convertTypeFromSQLToEntityStore(" dec ",false));
		assertEquals(FLOAT, typeConverter.convertTypeFromSQLToEntityStore(" decimal ",false));
		assertEquals(FLOAT, typeConverter.convertTypeFromSQLToEntityStore(" double ",false));
		assertEquals(FLOAT, typeConverter.convertTypeFromSQLToEntityStore(" double precision ",false));
		assertEquals(STRING, typeConverter.convertTypeFromSQLToEntityStore(" enum ",false));
		assertEquals(FLOAT, typeConverter.convertTypeFromSQLToEntityStore(" fixed ",false));
		assertEquals(FLOAT, typeConverter.convertTypeFromSQLToEntityStore(" float ",false));
		assertEquals(INTEGER, typeConverter.convertTypeFromSQLToEntityStore(" int ",false));
		assertEquals(INTEGER, typeConverter.convertTypeFromSQLToEntityStore(" integer ",false));
		assertEquals(STRING, typeConverter.convertTypeFromSQLToEntityStore(" interval ",false));
		assertEquals(INTEGER, typeConverter.convertTypeFromSQLToEntityStore(" interval day [day_precision] to second [fractional seconds] ",false));
		assertEquals(INTEGER, typeConverter.convertTypeFromSQLToEntityStore(" interval year [year_precision] to month ",false));
		assertEquals(INTEGER, typeConverter.convertTypeFromSQLToEntityStore(" long ",false));
		assertEquals(STRING, typeConverter.convertTypeFromSQLToEntityStore(" long raw ",false));
		assertEquals(STRING, typeConverter.convertTypeFromSQLToEntityStore(" longblob ",false));
		assertEquals(STRING, typeConverter.convertTypeFromSQLToEntityStore(" longtext ",false));
		assertEquals(STRING, typeConverter.convertTypeFromSQLToEntityStore(" mediumblob ",false));
		assertEquals(INTEGER, typeConverter.convertTypeFromSQLToEntityStore(" mediumint ",false));
		assertEquals(STRING, typeConverter.convertTypeFromSQLToEntityStore(" mediumtext ",false));
		assertEquals(FLOAT, typeConverter.convertTypeFromSQLToEntityStore(" money ",false));
		assertEquals(STRING, typeConverter.convertTypeFromSQLToEntityStore(" national character ",false));
		assertEquals(STRING, typeConverter.convertTypeFromSQLToEntityStore(" national character varying ",false));
		assertEquals(STRING, typeConverter.convertTypeFromSQLToEntityStore(" nchar ",false));
		assertEquals(STRING, typeConverter.convertTypeFromSQLToEntityStore(" nclob ",false));
		assertEquals(FLOAT, typeConverter.convertTypeFromSQLToEntityStore(" number ",false));
		assertEquals(FLOAT, typeConverter.convertTypeFromSQLToEntityStore(" numeric ",false));
		assertEquals(STRING, typeConverter.convertTypeFromSQLToEntityStore(" nvarchar ",false));
		assertEquals(STRING, typeConverter.convertTypeFromSQLToEntityStore(" nvarchar2 ",false));
		assertEquals(STRING, typeConverter.convertTypeFromSQLToEntityStore(" raw ",false));
		assertEquals(FLOAT, typeConverter.convertTypeFromSQLToEntityStore(" real ",false));
		assertEquals(STRING, typeConverter.convertTypeFromSQLToEntityStore(" rowid ",false));
		assertEquals(INTEGER, typeConverter.convertTypeFromSQLToEntityStore(" serial ",false));
		assertEquals(STRING, typeConverter.convertTypeFromSQLToEntityStore(" set ",false));
		assertEquals(INTEGER, typeConverter.convertTypeFromSQLToEntityStore(" smallint ",false));
		assertEquals(INTEGER, typeConverter.convertTypeFromSQLToEntityStore(" smallserial ",false));
		assertEquals(STRING, typeConverter.convertTypeFromSQLToEntityStore(" text ",false));
		assertEquals(LOCAL_DATE_TIME, typeConverter.convertTypeFromSQLToEntityStore(" time ",false));
		assertEquals(LOCAL_DATE_TIME, typeConverter.convertTypeFromSQLToEntityStore(" time with time zone ",false));
		assertEquals(LOCAL_DATE_TIME, typeConverter.convertTypeFromSQLToEntityStore(" timestamp ",false));
		assertEquals(LOCAL_DATE_TIME, typeConverter.convertTypeFromSQLToEntityStore(" timestamp with time zone ",false));
		assertEquals(LOCAL_DATE_TIME, typeConverter.convertTypeFromSQLToEntityStore(" timestamptz ",false));
		assertEquals(LOCAL_DATE_TIME, typeConverter.convertTypeFromSQLToEntityStore(" timetz ",false));
		assertEquals(STRING, typeConverter.convertTypeFromSQLToEntityStore(" tinyblob ",false));
		assertEquals(INTEGER, typeConverter.convertTypeFromSQLToEntityStore(" tinyint ",false));
		assertEquals(STRING, typeConverter.convertTypeFromSQLToEntityStore(" tinytext ",false));
		assertEquals(STRING, typeConverter.convertTypeFromSQLToEntityStore(" urowid ",false));
		assertEquals(STRING, typeConverter.convertTypeFromSQLToEntityStore(" uuid ",false));
		assertEquals(UUID, typeConverter.convertTypeFromSQLToEntityStore(" uuid ",true));
		assertEquals(STRING, typeConverter.convertTypeFromSQLToEntityStore(" varbinary ",false));
		assertEquals(STRING, typeConverter.convertTypeFromSQLToEntityStore(" varchar ",false));
		assertEquals(STRING, typeConverter.convertTypeFromSQLToEntityStore(" varchar2 ",false));
		assertEquals(INTEGER, typeConverter.convertTypeFromSQLToEntityStore(" year ",false));
	}

}

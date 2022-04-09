package dev.sassine.api.structure.type;

import static dev.sassine.api.structure.type.TypeConverter.TYPE_BOOLEAN;
import static dev.sassine.api.structure.type.TypeConverter.TYPE_FLOAT;
import static dev.sassine.api.structure.type.TypeConverter.TYPE_INTEGER;
import static dev.sassine.api.structure.type.TypeConverter.TYPE_LOCAL_DATE;
import static dev.sassine.api.structure.type.TypeConverter.TYPE_LOCAL_DATE_TIME;
import static dev.sassine.api.structure.type.TypeConverter.TYPE_LOCAL_TIME;
import static dev.sassine.api.structure.type.TypeConverter.TYPE_STRING;
import static dev.sassine.api.structure.type.TypeConverter.TYPE_UUID;
import static dev.sassine.api.structure.type.TypeConverter.convertTypeFromSQLDataBaseToEntityStore;
import static dev.sassine.api.structure.type.TypeConverter.convertTypeFromSQLToEntityStore;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import dev.sassine.api.structure.model.sql.Column;
import dev.sassine.api.structure.model.sql.Database;
import dev.sassine.api.structure.model.sql.TableModel;

public class TypeConverterTest {

	@Test(expected = RuntimeException.class)
	public void testConvertTypeFromSQLtoEntityStoreUnknownAndNull() {
		convertTypeFromSQLToEntityStore("UNKNOWN", false);
	}

	@Test
	public void convertTypeFromSQLToEntityStoreDatabase() {
		Database dataBase = new Database();
		TableModel tableModel = new TableModel();
		Column column = new Column();
		column.setType(TYPE_UUID);
		tableModel.getColumnByNames().put("FIELD_1", column);
		dataBase.setTables(List.of());
		convertTypeFromSQLDataBaseToEntityStore(dataBase, false);
		assertEquals(TYPE_UUID, convertTypeFromSQLToEntityStore(" uuid ", true));
	}
	
	@Test
	public void testConvertTypeFromSQLToEntityStore() {
		assertEquals(TYPE_STRING, convertTypeFromSQLToEntityStore(" bfile ", false));
		assertEquals(TYPE_INTEGER, convertTypeFromSQLToEntityStore(" bigint ", false));
		assertEquals(TYPE_INTEGER, convertTypeFromSQLToEntityStore(" bigserial ", false));
		assertEquals(TYPE_STRING, convertTypeFromSQLToEntityStore(" binary ", false));
		assertEquals(TYPE_FLOAT, convertTypeFromSQLToEntityStore(" binary_double ", false));
		assertEquals(TYPE_FLOAT, convertTypeFromSQLToEntityStore(" binary_float ", false));
		assertEquals(TYPE_STRING, convertTypeFromSQLToEntityStore(" bit ", false));
		assertEquals(TYPE_STRING, convertTypeFromSQLToEntityStore(" bit varying ", false));
		assertEquals(TYPE_STRING, convertTypeFromSQLToEntityStore(" blob ", false));
		assertEquals(TYPE_BOOLEAN, convertTypeFromSQLToEntityStore(" bool ", false));
		assertEquals(TYPE_BOOLEAN, convertTypeFromSQLToEntityStore(" boolean ", false));
		assertEquals(TYPE_STRING, convertTypeFromSQLToEntityStore(" bytea ", false));
		assertEquals(TYPE_STRING, convertTypeFromSQLToEntityStore(" char ", false));
		assertEquals(TYPE_STRING, convertTypeFromSQLToEntityStore(" character ", false));
		assertEquals(TYPE_STRING, convertTypeFromSQLToEntityStore(" character varying ", false));
		assertEquals(TYPE_STRING, convertTypeFromSQLToEntityStore(" clob ", false));
		assertEquals(TYPE_LOCAL_DATE, convertTypeFromSQLToEntityStore(" date ", false));
		assertEquals(TYPE_LOCAL_DATE, convertTypeFromSQLToEntityStore(" datetime ", false));
		assertEquals(TYPE_FLOAT, convertTypeFromSQLToEntityStore(" dec ", false));
		assertEquals(TYPE_FLOAT, convertTypeFromSQLToEntityStore(" decimal ", false));
		assertEquals(TYPE_FLOAT, convertTypeFromSQLToEntityStore(" double ", false));
		assertEquals(TYPE_FLOAT, convertTypeFromSQLToEntityStore(" double precision ", false));
		assertEquals(TYPE_STRING, convertTypeFromSQLToEntityStore(" enum ", false));
		assertEquals(TYPE_FLOAT, convertTypeFromSQLToEntityStore(" fixed ", false));
		assertEquals(TYPE_FLOAT, convertTypeFromSQLToEntityStore(" float ", false));
		assertEquals(TYPE_INTEGER, convertTypeFromSQLToEntityStore(" int ", false));
		assertEquals(TYPE_INTEGER, convertTypeFromSQLToEntityStore(" integer ", false));
		assertEquals(TYPE_STRING, convertTypeFromSQLToEntityStore(" interval ", false));
		assertEquals(TYPE_INTEGER, convertTypeFromSQLToEntityStore(" interval day [day_precision] to second [fractional seconds] ", false));
		assertEquals(TYPE_INTEGER, convertTypeFromSQLToEntityStore(" interval year [year_precision] to month ", false));
		assertEquals(TYPE_INTEGER, convertTypeFromSQLToEntityStore(" long ", false));
		assertEquals(TYPE_STRING, convertTypeFromSQLToEntityStore(" long raw ", false));
		assertEquals(TYPE_STRING, convertTypeFromSQLToEntityStore(" longblob ", false));
		assertEquals(TYPE_STRING, convertTypeFromSQLToEntityStore(" longtext ", false));
		assertEquals(TYPE_STRING, convertTypeFromSQLToEntityStore(" mediumblob ", false));
		assertEquals(TYPE_INTEGER, convertTypeFromSQLToEntityStore(" mediumint ", false));
		assertEquals(TYPE_STRING, convertTypeFromSQLToEntityStore(" mediumtext ", false));
		assertEquals(TYPE_FLOAT, convertTypeFromSQLToEntityStore(" money ", false));
		assertEquals(TYPE_STRING, convertTypeFromSQLToEntityStore(" national character ", false));
		assertEquals(TYPE_STRING, convertTypeFromSQLToEntityStore(" national character varying ", false));
		assertEquals(TYPE_STRING, convertTypeFromSQLToEntityStore(" nchar ", false));
		assertEquals(TYPE_STRING, convertTypeFromSQLToEntityStore(" nclob ", false));
		assertEquals(TYPE_FLOAT, convertTypeFromSQLToEntityStore(" number ", false));
		assertEquals(TYPE_FLOAT, convertTypeFromSQLToEntityStore(" numeric ", false));
		assertEquals(TYPE_STRING, convertTypeFromSQLToEntityStore(" nvarchar ", false));
		assertEquals(TYPE_STRING, convertTypeFromSQLToEntityStore(" nvarchar2 ", false));
		assertEquals(TYPE_STRING, convertTypeFromSQLToEntityStore(" raw ", false));
		assertEquals(TYPE_FLOAT, convertTypeFromSQLToEntityStore(" real ", false));
		assertEquals(TYPE_STRING, convertTypeFromSQLToEntityStore(" rowid ", false));
		assertEquals(TYPE_INTEGER, convertTypeFromSQLToEntityStore(" serial ", false));
		assertEquals(TYPE_STRING, convertTypeFromSQLToEntityStore(" set ", false));
		assertEquals(TYPE_INTEGER, convertTypeFromSQLToEntityStore(" smallint ", false));
		assertEquals(TYPE_INTEGER, convertTypeFromSQLToEntityStore(" smallserial ", false));
		assertEquals(TYPE_STRING, convertTypeFromSQLToEntityStore(" text ", false));
		assertEquals(TYPE_LOCAL_TIME, convertTypeFromSQLToEntityStore(" time ", false));
		assertEquals(TYPE_LOCAL_TIME, convertTypeFromSQLToEntityStore(" time with time zone ", false));
		assertEquals(TYPE_LOCAL_TIME, convertTypeFromSQLToEntityStore(" timestamp with time zone ", false));
		assertEquals(TYPE_LOCAL_DATE_TIME, convertTypeFromSQLToEntityStore(" timestamp ", false));
		assertEquals(TYPE_LOCAL_DATE_TIME, convertTypeFromSQLToEntityStore(" timestamptz ", false));
		assertEquals(TYPE_LOCAL_DATE_TIME, convertTypeFromSQLToEntityStore(" timetz ", false));
		assertEquals(TYPE_STRING, convertTypeFromSQLToEntityStore(" tinyblob ", false));
		assertEquals(TYPE_INTEGER, convertTypeFromSQLToEntityStore(" tinyint ", false));
		assertEquals(TYPE_STRING, convertTypeFromSQLToEntityStore(" tinytext ", false));
		assertEquals(TYPE_STRING, convertTypeFromSQLToEntityStore(" urowid ", false));
		assertEquals(TYPE_STRING, convertTypeFromSQLToEntityStore(" uuid ", false));
		assertEquals(TYPE_UUID, convertTypeFromSQLToEntityStore(" uuid ", true));
		assertEquals(TYPE_STRING, convertTypeFromSQLToEntityStore(" varbinary ", false));
		assertEquals(TYPE_STRING, convertTypeFromSQLToEntityStore(" varchar ", false));
		assertEquals(TYPE_STRING, convertTypeFromSQLToEntityStore(" varchar2 ", false));
		assertEquals(TYPE_INTEGER, convertTypeFromSQLToEntityStore(" year ", false));
	}

}

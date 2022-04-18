package dev.sassine.api.structure.type;

import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.LogManager.getLogger;

import org.apache.logging.log4j.Logger;

import dev.sassine.api.structure.model.sql.Database;

public class TypeConverter {
	private static final Logger log = getLogger();
	public static final String TYPE_STRING = "String";
	public static final String TYPE_UUID = "UUID";
	public static final String TYPE_FLOAT = "Float";
	public static final String TYPE_INTEGER = "Integer";
	public static final String TYPE_BOOLEAN = "Boolean";
	public static final String TYPE_LOCAL_DATE_TIME = "LocalDateTime";
	public static final String TYPE_LOCAL_DATE = "LocalDate";
	public static final String TYPE_LOCAL_TIME = "LocalTime";

	public static void convertTypeFromSQLDataBaseToEntityStore(final Database database, boolean isPostgress) {
		log.debug("Parameters ::: isPostgress: {} ",isPostgress);
		database.getTables().forEach(table -> {
			table.getColumnByNames().values().forEach(column -> {
				column.setConvertedType(convertTypeFromSQLToEntityStore(ofNullable(column.getType()).orElse(""), isPostgress));
			});
		});
	}

	public static String convertTypeFromSQLToEntityStore(String sqlType, boolean isPostgres) {
		if (sqlType == null) return "";
		else {
			String sqlTypeFormatted = sqlType.trim().toUpperCase();
			if ((sqlTypeFormatted.indexOf("INTERVAL DAY ") == 0)) 
				return TYPE_INTEGER;
			else if ((sqlTypeFormatted.indexOf("INTERVAL YEAR ") == 0)) 
				return TYPE_INTEGER;
			else 
			switch (sqlTypeFormatted) {
			case "BFILE":
			case "BINARY":
			case "BIT VARYING":
			case "BIT":
			case "AQUI":
			case "BLOB":
			case "BYTEA":
			case "CHAR":
			case "CHARACTER VARYING":
			case "CHARACTER":
			case "CLOB":
			case "ENUM":
			case "INTERVAL":
			case "LONG RAW":
			case "LONGBLOB":
			case "LONGTEXT":
			case "MEDIUMBLOB":
			case "MEDIUMTEXT":
			case "NATIONAL CHARACTER VARYING":
			case "NATIONAL CHARACTER":
			case "NCHAR":
			case "NCLOB":
			case "NVARCHAR":
			case "NVARCHAR2":
			case "RAW":
			case "ROWID":
			case "TINYBLOB":
			case "TINYTEXT":
			case "UROWID":
			case "VARBINARY":
			case "VARCHAR":
			case "VARCHAR2":
			case "TEXT":
			case "SET":
				return TYPE_STRING;
			case "UUID":
				return isPostgres ? TYPE_UUID : TYPE_STRING;
			case "BOOL":
			case "BOOLEAN":
				return TYPE_BOOLEAN;
			case "DEC":
			case "BINARY_DOUBLE":
			case "BINARY_FLOAT":
			case "DECIMAL":
			case "DOUBLE PRECISION":
			case "DOUBLE":
			case "FIXED":
			case "FLOAT":
			case "MONEY":
			case "NUMBER":
			case "NUMERIC":
			case "REAL":
				return TYPE_FLOAT;
			case "BIGINT":
			case "BIGSERIAL":
			case "YEAR":
			case "TINYINT":
			case "SMALLSERIAL":
			case "MEDIUMINT":
			case "LONG":
			case "INTERVAL YEAR":
			case "INTERVAL DAY":
			case "SERIAL":
			case "SMALLINT":
			case "INTEGER":
			case "INT":
				return TYPE_INTEGER;
			case "TIMETZ":
			case "TIMESTAMPTZ":
			case "TIMESTAMP":
				return TYPE_LOCAL_DATE_TIME;
			case "TIMESTAMP WITH TIME ZONE":
			case "TIME":
			case "TIME WITH TIME ZONE":
				return TYPE_LOCAL_TIME;
			case "DATETIME":
			case "DATE":
				return TYPE_LOCAL_DATE;
			default:
				log.error("Unknown SQL type {}",sqlTypeFormatted);
				throw new RuntimeException("Unknown SQL type "+sqlTypeFormatted);
			}
		}
	}
}

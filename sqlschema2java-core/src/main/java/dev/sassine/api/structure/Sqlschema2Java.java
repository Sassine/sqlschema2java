package dev.sassine.api.structure;

import static dev.sassine.api.structure.export.DatabaseConverter.convert;
import static dev.sassine.api.structure.type.TypeConverter.convertTypeFromSQLDataBaseToEntityStore;
import static dev.sassine.api.structure.util.Util.read;
import static dev.sassine.api.structure.validation.DatabaseValidator.validate;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.logging.log4j.Logger;

import dev.sassine.api.structure.export.builder.ClassSourceBuilder;
import dev.sassine.api.structure.model.java.EntityModel;
import dev.sassine.api.structure.model.sql.Database;
import dev.sassine.api.structure.parser.SqlImport;

public class Sqlschema2Java {

	private static final Logger log = getLogger();

	public static void generate(String inputFile, boolean isPostgress, boolean isAutoGenerated, String packageName) {
		log.debug("preparing to read SQL file('{}') ", inputFile);
		try (InputStream in = new FileInputStream(inputFile)) {
			log.debug("file read successfull");
			log.debug("Parameters ::: isPostgress: {} | isAutoGenerated: {} | packageName: {}", isPostgress, isAutoGenerated, packageName);
			process(read(in), isPostgress, isAutoGenerated, packageName);
			log.info("Congratulations build successful [√]");
		} catch (final Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	private static void process(final String sqlContent, boolean isPostgress, boolean isAutoGenerated, String packageName) {
		final Database database = SqlImport.init().getDatabase(sqlContent);
		convertTypeFromSQLDataBaseToEntityStore(database, isPostgress);
		validate(database);
		List<EntityModel> entityModel = convert(database);
		ClassSourceBuilder.init().build(entityModel, isAutoGenerated, packageName);
	}

}
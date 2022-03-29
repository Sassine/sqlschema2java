package dev.sassine.api.structure;

import java.io.InputStream;
import java.util.List;

import dev.sassine.api.structure.export.DatabaseConverter;
import dev.sassine.api.structure.export.builder.BuilderEntity;
import dev.sassine.api.structure.model.java.EntityModel;
import dev.sassine.api.structure.model.sql.Database;
import dev.sassine.api.structure.parser.SqlImport;
import dev.sassine.api.structure.type.TypeConverter;
import dev.sassine.api.structure.util.Util;
import dev.sassine.api.structure.validation.DatabaseValidator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Sqlschema2Java {

	public void compile() {
		String input = "C:\\poc\\poc.sql";
		final Util util = new Util();
		log.debug("preparing to read SQL file( '{}')",input);
		try (InputStream in = util.getInputStream(input)) {
			log.debug("file read successfully");
			process(util.read(in));
		} catch (final Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	private void process(final String sqlContent) {
		final SqlImport sqlImport = new SqlImport();
		final Database database = sqlImport.getDatabase(sqlContent);
		new TypeConverter().convertTypeFromSQLToEntityStore(database);
		new DatabaseValidator().validate(database);
		List<EntityModel> entityModel = new DatabaseConverter().convert(database);
		new BuilderEntity().build(entityModel);
	}

}

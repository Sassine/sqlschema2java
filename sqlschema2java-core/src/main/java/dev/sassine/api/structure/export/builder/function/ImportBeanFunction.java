package dev.sassine.api.structure.export.builder.function;

import static dev.sassine.api.structure.type.TypeConverter.TYPE_LOCAL_DATE;
import static dev.sassine.api.structure.type.TypeConverter.TYPE_LOCAL_DATE_TIME;
import static dev.sassine.api.structure.type.TypeConverter.TYPE_LOCAL_TIME;
import static java.lang.String.format;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.burningwave.core.classes.UnitSourceGenerator;

import dev.sassine.api.structure.model.java.FieldModel;

public class ImportBeanFunction {
	
	private static final String FORMAT_IMPORT_ENTITY_PACKAGE = "%s.domain.%sEntity";
	
	public static void importEntityClass(String nameClass, String packageName, UnitSourceGenerator gen) {
		gen.addImport(format(FORMAT_IMPORT_ENTITY_PACKAGE, packageName, nameClass));
	}
	
	public static void importJavaTime(UnitSourceGenerator gen, FieldModel fieldModel) {
		if (TYPE_LOCAL_DATE_TIME.equals(fieldModel.getType())) gen.addImport(LocalDateTime.class);
		else if (TYPE_LOCAL_DATE.equals(fieldModel.getType())) gen.addImport(LocalDate.class);
		else if (TYPE_LOCAL_TIME.equals(fieldModel.getType())) gen.addImport(LocalTime.class);
	}
	
}

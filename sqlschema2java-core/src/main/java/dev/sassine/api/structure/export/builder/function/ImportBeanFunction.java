package dev.sassine.api.structure.export.builder.function;

import static dev.sassine.api.structure.type.TypeConverter.TYPE_LOCAL_DATE;
import static dev.sassine.api.structure.type.TypeConverter.TYPE_LOCAL_DATE_TIME;
import static dev.sassine.api.structure.type.TypeConverter.TYPE_LOCAL_TIME;
import static java.lang.String.format;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.burningwave.core.classes.AnnotationSourceGenerator;
import org.burningwave.core.classes.TypeDeclarationSourceGenerator;
import org.burningwave.core.classes.UnitSourceGenerator;
import org.burningwave.core.classes.VariableSourceGenerator;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;

import dev.sassine.api.structure.model.java.FieldModel;

public class ImportBeanFunction {
	
	private static final String TIME_DESERIALIZER = "LocalTimeDeserializer.class";
	private static final String DATE_DESERIALIZER = "LocalDateDeserializer.class";
	private static final String DATE_TIME_DESERIALIZER = "LocalDateTimeDeserializer.class";
	private static final String PARAM_USING = "using";
	private static final String FORMAT_IMPORT_ENTITY_PACKAGE = "%s.domain.%sEntity";
	
	public static void importEntityClass(String nameClass, String packageName, UnitSourceGenerator gen) {
		gen.addImport(format(FORMAT_IMPORT_ENTITY_PACKAGE, packageName, nameClass));
	}
	
	public static void importJavaTime(UnitSourceGenerator gen, FieldModel fieldModel) {
		if (TYPE_LOCAL_DATE_TIME.equals(fieldModel.getType())) gen.addImport(LocalDateTime.class);
		else if (TYPE_LOCAL_DATE.equals(fieldModel.getType())) gen.addImport(LocalDate.class);
		else if (TYPE_LOCAL_TIME.equals(fieldModel.getType())) gen.addImport(LocalTime.class);
	}

	public static void importJavaTimeAndJSONDeserialize(VariableSourceGenerator field, UnitSourceGenerator gen,
			FieldModel fieldModel) {
		if (TYPE_LOCAL_DATE_TIME.equals(fieldModel.getType())) {
			gen.addImport(LocalDateTime.class);
			gen.addImport(LocalDateTimeDeserializer.class);
			field.addAnnotation(AnnotationSourceGenerator.create(JsonDeserialize.class).addParameter(PARAM_USING,
					VariableSourceGenerator.create(TypeDeclarationSourceGenerator.create(DATE_TIME_DESERIALIZER))));
		} else if (TYPE_LOCAL_DATE.equals(fieldModel.getType())) {
			gen.addImport(LocalDate.class);
			gen.addImport(LocalDateDeserializer.class);
			field.addAnnotation(AnnotationSourceGenerator.create(JsonDeserialize.class).addParameter(PARAM_USING,
					VariableSourceGenerator.create(TypeDeclarationSourceGenerator.create(DATE_DESERIALIZER))));
		} else if (TYPE_LOCAL_TIME.equals(fieldModel.getType())) {
			gen.addImport(LocalTime.class);
			gen.addImport(LocalTimeDeserializer.class);
			field.addAnnotation(AnnotationSourceGenerator.create(JsonDeserialize.class).addParameter(PARAM_USING,
					VariableSourceGenerator.create(TypeDeclarationSourceGenerator.create(TIME_DESERIALIZER))));

		}
	}
}

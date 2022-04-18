package dev.sassine.api.structure;

import static dev.sassine.api.structure.Sqlschema2Java.generate;
import static dev.sassine.api.structure.model.java.EnvironmentModel.builder;
import static java.nio.file.Paths.get;

import java.io.FileNotFoundException;

public class Main {

	public static void main(final String[] args) throws FileNotFoundException {
		generate(builder()
				.packageName("dev.sassine.api.structure.generated")
				.sourceDirectory(get("src","test","resources","default.sql").toFile().getAbsolutePath())
				.build());
	}

}

package dev.sassine.api.structure;

import static dev.sassine.api.structure.Sqlschema2Java.generate;
import static java.nio.file.Paths.get;

import java.io.FileNotFoundException;

import dev.sassine.api.structure.model.java.EnvironmentModel;

public class Main {

	public static void main(final String[] args) throws FileNotFoundException {
		generate(EnvironmentModel.builder()
				.packageName("dev.sassine.api.structure.generated")
				.sourceDirectory(get("src","test","resources","default.sql").toFile().getAbsolutePath())
				.build());
	}

}

package dev.sassine.api.structure.export.builder.function;

import static java.lang.System.getProperty;

import org.burningwave.core.classes.UnitSourceGenerator;

public class StoreClassFuncation {
	
	private static final String SRC_TEST_JAVA = "/src/test/java/";
	private static final String SRC_MAIN_JAVA = "/src/main/java/";
	private static final String USER_DIR = getProperty("user.dir");

	public static void store(UnitSourceGenerator gen) {
		gen.storeToClassPath(USER_DIR.concat(SRC_MAIN_JAVA));
	}
	
	public static void storeTest(UnitSourceGenerator gen) {
		gen.storeToClassPath(USER_DIR.concat(SRC_TEST_JAVA));
	}
}

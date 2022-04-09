package dev.sassine.api.structure;

import java.io.FileNotFoundException;

public class Main {

	public static void main(final String[] args) throws FileNotFoundException {
		Sqlschema2Java.generate("C:\\poc\\poc.sql", false, false,"dev.sassine.api.structure.delete");
	}
 
}

 

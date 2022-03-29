package dev.sassine.api.structure;

import java.io.FileNotFoundException;

public class Main {

	public static void main(final String[] args) throws FileNotFoundException {
		new Sqlschema2Java().compile();
	}

}

 

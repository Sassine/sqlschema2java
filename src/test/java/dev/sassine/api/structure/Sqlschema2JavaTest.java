package dev.sassine.api.structure;


import org.junit.Test;

public class Sqlschema2JavaTest {

	@Test(expected = Exception.class)
	public void testGetDatabase_nofile() {
		new Sqlschema2Java().compile();
	}


}

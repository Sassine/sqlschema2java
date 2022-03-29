package dev.sassine.api.structure;


import org.junit.Test;

public class Sqlschema2JavaTest {

	@Test(expected = Exception.class)
	public void testGetDatabase_nofile() throws Exception {
		new Sqlschema2Java().compile();
		throw new Exception();
	}


}

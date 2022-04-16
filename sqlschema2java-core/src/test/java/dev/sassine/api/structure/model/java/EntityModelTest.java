package dev.sassine.api.structure.model.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class EntityModelTest {
	

	@Test
	public void getFieldForNameTest() {
		var entity = new EntityModel("table", "auto");
		var field = new FieldModel();
		field.setName("teste_1");
		field.setType("Integer");
		entity.getFields().add(field);
		var value = entity.getFieldForName("teste_1");
		assertEquals("teste1",value.getCamelName());
		assertEquals("Teste1",value.getCamelNameUpper());
		assertEquals("Integer",value.getType());
		assertNull(entity.getFieldForName("teste_2"));
	}

}

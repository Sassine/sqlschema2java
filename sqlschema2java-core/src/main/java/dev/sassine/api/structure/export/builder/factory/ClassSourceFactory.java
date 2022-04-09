package dev.sassine.api.structure.export.builder.factory;

import static org.apache.logging.log4j.LogManager.getLogger;

import org.apache.logging.log4j.Logger;

import dev.sassine.api.structure.export.builder.factory.impl.DTOFactory;
import dev.sassine.api.structure.export.builder.factory.impl.EntityFactory;
import dev.sassine.api.structure.export.builder.factory.impl.RepositoryFactory;

public class ClassSourceFactory extends AbstractClassSourceFactory {
	
	private static final Logger log = getLogger();

	@Override
	public EntityFactory createEntity() {
		log.debug("initialize EntityFactory()");
		return new EntityFactory();
	}

	@Override
	public DTOFactory createDTO() {
		log.debug("initialize DTOFactory()");
		return new DTOFactory();
	}

	@Override
	public RepositoryFactory createRepository() {
		log.debug("initialize RepositoryFactory()");
		return new RepositoryFactory();
	}

}

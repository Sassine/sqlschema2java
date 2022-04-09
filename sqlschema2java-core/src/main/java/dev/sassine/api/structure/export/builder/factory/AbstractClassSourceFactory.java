package dev.sassine.api.structure.export.builder.factory;

import dev.sassine.api.structure.export.builder.factory.impl.DTOFactory;
import dev.sassine.api.structure.export.builder.factory.impl.EntityFactory;
import dev.sassine.api.structure.export.builder.factory.impl.RepositoryFactory;

public abstract class AbstractClassSourceFactory {
	
	abstract EntityFactory createEntity();
    abstract DTOFactory createDTO();
    abstract RepositoryFactory createRepository();
    
}

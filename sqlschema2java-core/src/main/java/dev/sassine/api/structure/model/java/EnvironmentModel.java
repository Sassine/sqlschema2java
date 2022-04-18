package dev.sassine.api.structure.model.java;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EnvironmentModel {
    
	private String sourceDirectory;
	
	private String packageName;
	
	@Builder.Default
	private Boolean usePostgreSQL = false;
	
	@Builder.Default
	private Boolean useAutoIncrement = true;

}

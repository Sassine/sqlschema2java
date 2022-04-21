package dev.sassine.api.structure.model.sql;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForeignKey {

	private List<String> columnNameOrigins = new ArrayList<>();
	private List<String> columnNameTargets = new ArrayList<>();
	private String tableNameOrigin;
	private String tableNameTarget;

}

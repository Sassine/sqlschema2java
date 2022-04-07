package dev.sassine.api.structure.model.sql;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrimaryKey {

	List<String> columnNames = new ArrayList<String>();

}

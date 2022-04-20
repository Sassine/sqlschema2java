package dev.sassine.api.structure.delete.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.sassine.api.structure.delete.domain.HistPolicyInfoEntity;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HistPolicyInfoDTO { 

	@JsonProperty (
		value =  	"id" 
	)
	private Integer id;
	@JsonProperty (
		value =  	"coverage" 
	)
	private Integer coverage;
	@JsonProperty (
		value =  	"sys_end" 
	)
	private LocalDateTime sysEnd;
	@JsonProperty (
		value =  	"policy_id" 
	)
	private String policyId;
	@JsonProperty (
		value =  	"create_id" 
	)
	private LocalDateTime createId;
	@JsonProperty (
		value =  	"bus_end" 
	)
	private LocalDateTime busEnd;
	@JsonProperty (
		value =  	"bus_start" 
	)
	private String busStart;
	@JsonProperty (
		value =  	"sys_start" 
	)
	private LocalDateTime sysStart; 

	public HistPolicyInfoEntity toEntity() {
		HistPolicyInfoEntity entity = new HistPolicyInfoEntity(); 
		this.id = entity.getId(); 
		this.coverage = entity.getCoverage(); 
		this.sysEnd = entity.getSysEnd(); 
		this.policyId = entity.getPolicyId(); 
		this.createId = entity.getCreateId(); 
		this.busEnd = entity.getBusEnd(); 
		this.busStart = entity.getBusStart(); 
		this.sysStart = entity.getSysStart(); 
		return entity; 
	} 

}
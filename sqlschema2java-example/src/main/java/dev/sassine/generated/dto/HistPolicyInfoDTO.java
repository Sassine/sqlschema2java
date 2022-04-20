package dev.sassine.generated.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import dev.sassine.generated.domain.HistPolicyInfoEntity;
import java.time.LocalDate;
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
	@JsonDeserialize (
		using =  	LocalDateTimeDeserializer.class 
	)
	private LocalDateTime sysEnd;
	@JsonProperty (
		value =  	"policy_id" 
	)
	private String policyId;
	@JsonProperty (
		value =  	"create_id" 
	)
	@JsonDeserialize (
		using =  	LocalDateTimeDeserializer.class 
	)
	private LocalDateTime createId;
	@JsonProperty (
		value =  	"bus_end" 
	)
	@JsonDeserialize (
		using =  	LocalDateDeserializer.class 
	)
	private LocalDate busEnd;
	@JsonProperty (
		value =  	"bus_start" 
	)
	private String busStart;
	@JsonProperty (
		value =  	"sys_start" 
	)
	@JsonDeserialize (
		using =  	LocalDateTimeDeserializer.class 
	)
	private LocalDateTime sysStart; 

	public HistPolicyInfoEntity toEntity() {
		HistPolicyInfoEntity entity = new HistPolicyInfoEntity(); 
		entity.setId(this.id); 
		entity.setCoverage(this.coverage); 
		entity.setSysEnd(this.sysEnd); 
		entity.setPolicyId(this.policyId); 
		entity.setCreateId(this.createId); 
		entity.setBusEnd(this.busEnd); 
		entity.setBusStart(this.busStart); 
		entity.setSysStart(this.sysStart); 
		return entity; 
	} 

}
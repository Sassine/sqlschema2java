package dev.sassine.api.default.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table (
	name =  	"hist_policy_info" 
)
public class HistPolicyInfoEntity { 

	@Column (
		name =  	"id",
		nullable =  	false 
	)
	@Id
	private Integer id;
	@Column (
		name =  	"coverage",
		nullable =  	false 
	)
	private Integer coverage;
	@Column (
		name =  	"sys_end",
		nullable =  	false 
	)
	private LocalDateTime sysEnd;
	@Column (
		name =  	"policy_id",
		nullable =  	false 
	)
	private String policyId;
	@Column (
		name =  	"create_id",
		nullable =  	false 
	)
	private LocalDateTime createId;
	@Column (
		name =  	"bus_end",
		nullable =  	false 
	)
	private LocalDate busEnd;
	@Column (
		name =  	"bus_start",
		nullable =  	false 
	)
	private String busStart;
	@Column (
		name =  	"sys_start",
		nullable =  	false 
	)
	private LocalDateTime sysStart; 

}
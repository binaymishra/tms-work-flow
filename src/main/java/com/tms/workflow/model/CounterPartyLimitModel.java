package com.tms.workflow.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CounterPartyLimitModel {
	
	Long id;
	Long limits;
	String cpLimitType;
	String status; 
	String approvedBy;

}

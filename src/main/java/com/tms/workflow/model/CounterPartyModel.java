package com.tms.workflow.model;

import lombok.Data;

@Data
public class CounterPartyModel {
	
	Long id;
	String name;
	String status;
	String aladdinCode;
	String approvedBy;

}

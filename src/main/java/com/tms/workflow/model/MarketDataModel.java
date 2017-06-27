package com.tms.workflow.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarketDataModel {
	
	Long id;
	Long capacity; 
	Long ticketSize; 
	Long roundingFactor;
	String currency;

}

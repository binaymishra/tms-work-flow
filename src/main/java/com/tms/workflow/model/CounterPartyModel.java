package com.tms.workflow.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CounterPartyModel {
	
	Long id;
	String name;
	String status;
	String aladdinCode;
	String approvedBy;
	
	List<CounterPartyLimitModel> limits;
	List<MarketRatingModel> ratingModels;
	List<MarketDataModel> marketDataModels;

}

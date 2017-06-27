package com.tms.workflow.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarketRatingModel {
	
	Long id;
	String ratingType;
	String fitchRating;
	String sadnpRating;
	String moodyRating;

}

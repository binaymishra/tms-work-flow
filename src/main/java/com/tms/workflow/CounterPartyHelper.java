package com.tms.workflow;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tms.workflow.entity.CounterParty;
import com.tms.workflow.entity.CounterPartyLimit;
import com.tms.workflow.entity.MarketData;
import com.tms.workflow.entity.MarketRating;
import com.tms.workflow.model.CounterPartyLimitModel;
import com.tms.workflow.model.CounterPartyModel;
import com.tms.workflow.model.MarketDataModel;
import com.tms.workflow.model.MarketRatingModel;

@Component
public class CounterPartyHelper {
	
	public CounterParty mapValues(final CounterPartyModel from, final CounterParty to){
		to.setAladdinCode(from.getAladdinCode());
		to.setApprovedBy(from.getApprovedBy());
		to.setStatus(from.getStatus());
		to.setName(from.getName());
		to.setId(from.getId());
		
		List<CounterPartyLimit> limits = new ArrayList<>();
		for(CounterPartyLimitModel l :from.getLimits()){
			CounterPartyLimit limit = new CounterPartyLimit();
			limit.setCpLimitType(l.getCpLimitType());
			limit.setLimits(l.getLimits());
			limit.setStatus(l.getStatus());
			limit.setCounterParty(to);
			limits.add(limit);
		}
		to.setLimitSet(limits);
		return to;
	}
	
	public List<CounterPartyModel> counterPartyMapper(List<CounterParty> counterPartyList){
		
		List<CounterPartyModel>  counterPartyModels = new ArrayList<>();
		for(CounterParty cp: counterPartyList){
			//CounterPartyLimitModel
			List<CounterPartyLimitModel> limitModels = new ArrayList<>();
			for(CounterPartyLimit limit : cp.getLimitSet()){
				CounterPartyLimitModel limitModel = new CounterPartyLimitModel(
						limit.getId(), 
						limit.getLimits(), 
						limit.getCpLimitType(), 
						limit.getStatus(), 
						limit.getApprovedBy());
				
				limitModels.add(limitModel);
			}
			//MarketDataModels
			List<MarketDataModel> marketDataModels = new ArrayList<>();
			for(MarketData marketData: cp.getMarketDataSet()){
				MarketDataModel marketDataModel = new MarketDataModel(
						marketData.getId(), 
						marketData.getCapacity(), 
						marketData.getTicketSize(), 
						marketData.getRoundingFactor(), 
						marketData.getCurrency());
				
				marketDataModels.add(marketDataModel);
			}
			//MarketRatingModels
			List<MarketRatingModel> ratingModels = new ArrayList<>();
			for(MarketRating rating:cp.getRatingSet()){
				MarketRatingModel marketRatingModel = new MarketRatingModel(
						rating.getId(), 
						rating.getRatingType(), 
						rating.getFitchRating(), 
						rating.getSadnpRating(), 
						rating.getMoodyRating());
				
				ratingModels.add(marketRatingModel);
			}
			
			CounterPartyModel counterPartyModel = new CounterPartyModel(
					cp.getId(),
					cp.getName(), 
					cp.getStatus(), 
					cp.getAladdinCode(), 
					cp.getApprovedBy(), 
					limitModels, ratingModels, marketDataModels);
			counterPartyModels.add(counterPartyModel);
		}
		return counterPartyModels;
	}


	
}


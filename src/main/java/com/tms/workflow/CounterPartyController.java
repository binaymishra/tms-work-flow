package com.tms.workflow;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tms.workflow.entity.CounterParty;
import com.tms.workflow.entity.CounterPartyLimit;
import com.tms.workflow.entity.MarketData;
import com.tms.workflow.entity.MarketRating;
import com.tms.workflow.model.CounterPartyLimitModel;
import com.tms.workflow.model.CounterPartyModel;
import com.tms.workflow.model.MarketDataModel;
import com.tms.workflow.model.MarketRatingModel;
import com.tms.workflow.repo.CounterPartyRepository;
import com.tms.workflow.repo.CounterPartyRevisionRepository;

@RestController
@RequestMapping("/counterparty")
public class CounterPartyController {
	
	static final ExecutorService executor = Executors.newFixedThreadPool(1);
	
	@Autowired
	CounterPartyRepository repository;
	
	@Autowired
	CounterPartyRevisionRepository revisionRepository;
	
	
	@PostMapping
	@ResponseBody void createCounterParty(@RequestBody CounterPartyModel model){
		//default create status is 'pending'
		repository.save(mapValues(model, new CounterParty()));
		
	}
	
	@PatchMapping("/{id}")
	@ResponseBody void approveCounterParty(@PathVariable("id") Long id,
			@RequestBody CounterPartyModel model){
		CounterParty entity = repository.findOne(id);
		entity.setStatus(model.getStatus());
		entity.setApprovedBy(model.getApprovedBy());
		//Because its is approved, therefore making it active, may other logic be implemented.
		entity = repository.save(entity);
		
		final CounterParty activete = entity;
		activete.setStatus("active");
		executor.submit(()-> repository.save(activete));
		
	}
	
	@GetMapping
	@ResponseBody List<CounterPartyModel> findAllCounterParties(@RequestParam(value = "status", required=false) String status){
		List<CounterParty> counterParties;
		if(StringUtils.isEmpty(status))
			 counterParties = repository.findAll();
		else
			counterParties = repository.findBystatus(status);
		List<CounterPartyModel>  counterPartyModels = counterPartyMapper(counterParties);
		return counterPartyModels;
	}
	
	@GetMapping("/revision/{cpId}")
	@ResponseBody List<CounterParty> findCounterPartyRevisions(@PathVariable("cpId") Long cpId){
		return revisionRepository.finCounterPartyRevisions(cpId);
	}
	
	private CounterParty mapValues(final CounterPartyModel from, final CounterParty to){
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

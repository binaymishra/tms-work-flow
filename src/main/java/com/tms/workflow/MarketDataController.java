package com.tms.workflow;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tms.workflow.entity.MarketData;
import com.tms.workflow.repo.MarketDataRepository;

@RestController
@RequestMapping("/marketdata")
public class MarketDataController {
	
	MarketDataRepository repository;
	
	@Autowired
	public MarketDataController(MarketDataRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping
	Collection<MarketData> findAll(){
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	MarketData findMarketData(@PathVariable("id") long id){
		return repository.findOne(id);
	}
	
	@GetMapping("/{id}/{cpId}")
	MarketData findOneMarketDataForCounterParty(@PathVariable("id") long id, @PathVariable("cpId") long cpId){
		return repository.findOneMarketDataForCounterParty(id, cpId);
	}
	
	@GetMapping("/counterparty/{cpId}")
	Set<MarketData> findMarketDataforCounterParty(@PathVariable("cpId") long cpId){
		return null;
	}
	
	@PostMapping("/counterparty/{cpId}")
	@ResponseBody void addMarketDataToCounterParty(@PathVariable("cpId") long cpId,
			@RequestBody MarketData marketData){
		
	}
	
	@PutMapping("/{id}/counterparty/{cpId}")
	@ResponseBody void updateMarketDataOfCounterParty(@PathVariable("id") long id, @PathVariable("cpId") long cpId,
			@RequestBody MarketData marketData){
		
	}
	
	@PatchMapping("/{id}/counterparty/{cpId}")
	@ResponseBody void approveMarketDataOfCounterParty(@PathVariable("id") long id, @PathVariable("cpId") long cpId,
			@RequestBody MarketData marketData){
		
	}
	
	@DeleteMapping("/{id}/counterparty/{cpId}")
	@ResponseBody void deleteMarketDataFromCounterParty(){
		
	}

}

package com.tms.workflow;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
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
import com.tms.workflow.model.CounterPartyModel;
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
	@ResponseBody CounterParty createCounterParty(@RequestBody CounterParty counterParty){
		//default create status is 'pending'
		List<CounterPartyLimit> limits = counterParty.getLimits();
			final CounterParty cp = repository.save(counterParty); // saving counterparty data.
		if(!CollectionUtils.isEmpty(limits)){
			limits.forEach(l -> {l.setCounterParty(cp);});
		}
		counterParty.setLimits(limits);
		return counterParty = repository.save(counterParty);
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
	@ResponseBody List<CounterParty> findAllCounterParties(@RequestParam(value = "status", required=false) String status){
		List<CounterParty> counterParties;
		if(StringUtils.isEmpty(status))
			 counterParties = repository.findAll();
		else
			counterParties = repository.findBystatus(status);
		return counterParties;
	}

	@GetMapping("/revision/{cpId}")
	@ResponseBody List<CounterParty> findCounterPartyRevisions(@PathVariable("cpId") Long cpId){
		return revisionRepository.finCounterPartyRevisions(cpId);
	}



}

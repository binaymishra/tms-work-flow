package com.tms.workflow;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tms.workflow.entity.CounterParty;
import com.tms.workflow.model.CounterPartyRequest;
import com.tms.workflow.model.CounterPartyResponse;
import com.tms.workflow.service.CounterPartyService;

@RestController
@RequestMapping("/counterparty")
public class CounterPartyController {

	static final ExecutorService executor = Executors.newFixedThreadPool(1);

	@Autowired
	CounterPartyService service;

	@PostMapping
	@ResponseBody ResponseEntity<CounterPartyResponse> createCounterParty(@RequestBody CounterPartyRequest counterParty){
		if(Objects.isNull(counterParty.getCounterParty()))
				return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(new CounterPartyResponse(service.add(counterParty.getCounterParty()).get()));
	}

	@GetMapping
	@ResponseBody ResponseEntity<CounterPartyResponse> findAllCounterParties(@RequestParam(value = "status", required=false) String status){
		List<CounterParty> counterParties;
		if(StringUtils.isEmpty(status))
			counterParties = service.findAll();
		else
			counterParties = service.findAll(status);
		return counterParties.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(new CounterPartyResponse(counterParties));
	}

	@GetMapping("/{cpId}")
	ResponseEntity<CounterPartyResponse> findOneCounterParty(@PathVariable("cpId") Long id){
		Optional<CounterParty> cp = service.findOne(id);
		if(cp.isPresent())
			return ResponseEntity.ok(new CounterPartyResponse(cp.get()));
		return ResponseEntity.notFound().build();
	}

	/*@GetMapping("/revision/{cpId}")
	@ResponseBody List<CounterParty> findCounterPartyRevisions(@PathVariable("cpId") Long cpId){
		return revisionRepository.finCounterPartyRevisions(cpId);
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

	}*/


}

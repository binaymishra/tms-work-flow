package com.tms.workflow;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tms.workflow.entity.CounterParty;
import com.tms.workflow.model.CounterPartyRequest;
import com.tms.workflow.model.CounterPartyResponse;
import com.tms.workflow.repo.CounterPartyRevisionRepository;
import com.tms.workflow.service.CounterPartyService;

@RestController
@RequestMapping("/counterparty")
public class CounterPartyController {

	@Autowired
	private CounterPartyRevisionRepository revRepo; // for testing

	final CounterPartyService service;

	@Autowired
	public CounterPartyController(CounterPartyService service) {
		this.service = service;

	}

	@PostMapping
	@ResponseBody ResponseEntity<CounterPartyResponse> createCounterParty(@RequestBody CounterPartyRequest counterParty){
		if(Objects.isNull(counterParty.getCounterParty()))
				return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(new CounterPartyResponse(service.add(counterParty.getCounterParty()).get()));
	}

	/**
	 * @param id
	 * @param counterParty
	 * @return
	 */
	@PutMapping("/{id}")
	@ResponseBody ResponseEntity<CounterPartyResponse> updateCounterParty(@PathVariable("id") Long id,
			@RequestBody CounterPartyRequest counterParty){
			if(Objects.isNull(counterParty.getCounterParty()))
				return ResponseEntity.badRequest().build();
			counterParty.getCounterParty().setId(id);
			return ResponseEntity.ok(new CounterPartyResponse(service.update(counterParty.getCounterParty())));
	}

	@PatchMapping("/{id}")
	@ResponseBody ResponseEntity<CounterPartyResponse> approveCounterParty(@PathVariable("id") Long id,
			@RequestBody CounterParty counterParty){
			counterParty.setId(id);
			return ResponseEntity.ok(new CounterPartyResponse(service.approve(counterParty)));
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

	@GetMapping("/revision/{cpId}")
	@ResponseBody List<CounterParty> findCounterPartyRevisions(@PathVariable("cpId") Long cpId){
		return revRepo.finCounterPartyRevisions(cpId);
	}



}

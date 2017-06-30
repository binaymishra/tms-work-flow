package com.tms.workflow.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.tms.workflow.entity.CounterParty;

public interface CounterPartyService {


	List<CounterParty> findAll();

	List<CounterParty> findAll(String status);

	Optional<CounterParty> findOne(Long id);

	Optional<CounterParty> add(CounterParty counterParty);

	Map<String, CounterParty> approve(CounterParty counterParty);

	Map<String, CounterParty> update(CounterParty counterParty);

}

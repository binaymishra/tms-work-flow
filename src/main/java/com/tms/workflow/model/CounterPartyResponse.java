package com.tms.workflow.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tms.workflow.entity.CounterParty;

@JsonInclude(value=Include.NON_EMPTY, content=Include.NON_NULL)
public class CounterPartyResponse {

	CounterParty counterParty;

	List<CounterParty> counterParties;



	public CounterPartyResponse() {
		// Default constructor
	}

	public CounterPartyResponse(List<CounterParty> counterParties) {
		this.counterParties = counterParties;
	}

	public CounterPartyResponse(CounterParty counterParty) {
		this.counterParty = counterParty;
	}

	public CounterParty getCounterParty() {
		return counterParty;
	}

	public void setCounterParty(CounterParty counterParty) {
		this.counterParty = counterParty;
	}

	public List<CounterParty> getCounterParties() {
		return counterParties;
	}

	public void setCounterParties(List<CounterParty> counterParties) {
		this.counterParties = counterParties;
	}



}

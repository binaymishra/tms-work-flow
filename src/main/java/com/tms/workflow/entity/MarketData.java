package com.tms.workflow.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Audited
@Access(AccessType.FIELD)
@Table(name = "tt_cpty_market_data")
public class MarketData {

	static final long ROUNDING_FACTOR = 25000L;


	Long id;
	Long capacity;
	Long ticketSize;
	Long roundingFactor = Long.valueOf(ROUNDING_FACTOR);
	String currency;

	CounterParty counterParty;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCapacity() {
		return capacity;
	}

	public void setCapacity(Long capacity) {
		this.capacity = capacity;
	}

	public Long getTicketSize() {
		return ticketSize;
	}

	public void setTicketSize(Long ticketSize) {
		this.ticketSize = ticketSize;
	}

	public Long getRoundingFactor() {
		return roundingFactor;
	}

	public void setRoundingFactor(Long roundingFactor) {
		this.roundingFactor = roundingFactor;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@JsonIgnore //breaking infinite recursion
	@ManyToOne
	@JoinColumn(name = "cp_id")
	public CounterParty getCounterParty() {
		return counterParty;
	}

	public void setCounterParty(CounterParty counterParty) {
		this.counterParty = counterParty;
	}





}

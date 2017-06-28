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


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	Long capacity;
	Long ticketSize;
	Long roundingFactor = Long.valueOf(ROUNDING_FACTOR);
	String currency;

	@JsonIgnore //breaking infinite recursion
	@ManyToOne
	@JoinColumn(name = "cp_id")
	CounterParty counterParty;
}

package com.tms.workflow.entity;

import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Audited
@Access(AccessType.FIELD)
@Table(name = "tt_cpty")
public class CounterParty {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	String name;
	String status;
	@Column(unique=true)
	String aladdinCode;
	String approvedBy;
	
	@JsonBackReference(value="cp-marketdata")
	@OneToMany(mappedBy = "counterParty", cascade = CascadeType.ALL)
	List<MarketData> marketDataSet;
	
	@JsonBackReference(value="cp-rating")
	@OneToMany(mappedBy = "counterParty", cascade = CascadeType.ALL)
	List<MarketRating> ratingSet;
	
	@JsonBackReference(value="cp-limit")
	@OneToMany(mappedBy = "counterParty", cascade = CascadeType.ALL)
	List<CounterPartyLimit> limitSet;
	
	

}

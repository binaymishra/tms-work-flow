package com.tms.workflow.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Audited
//@Access(AccessType.PROPERTY)
@Table(name = "tt_cpty")
public class CounterParty {

	Long id;
	String name;
	String status;
	String aladdinCode;
	String approvedBy;


	List<MarketData> marketDatas;
	List<MarketRating> marketRatings;
	List<CounterPartyLimit> limits;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(unique=true)
	public String getAladdinCode() {
		return aladdinCode;
	}

	public void setAladdinCode(String aladdinCode) {
		this.aladdinCode = aladdinCode;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	@OneToMany(mappedBy = "counterParty", cascade = CascadeType.ALL)
	public List<MarketData> getMarketDatas() {
		return marketDatas;
	}

	public void setMarketDatas(List<MarketData> marketDatas) {
		this.marketDatas = marketDatas;
	}

	@OneToMany(mappedBy = "counterParty", cascade = CascadeType.ALL)
	public List<MarketRating> getMarketRatings() {
		return marketRatings;
	}

	public void setMarketRatings(List<MarketRating> marketRatings) {
		this.marketRatings = marketRatings;
	}

	@OneToMany(mappedBy = "counterParty", cascade = CascadeType.ALL)
	public List<CounterPartyLimit> getLimits() {
		return limits;
	}

	public void setLimits(List<CounterPartyLimit> limits) {
		this.limits = limits;
	}





}

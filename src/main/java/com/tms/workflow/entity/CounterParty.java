package com.tms.workflow.entity;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Audited
@Access(AccessType.FIELD)
@Table(name = "tt_cpty")
//@JsonInclude(value=Include.NON_EMPTY, content=Include.NON_NULL)
public class CounterParty {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	String name;
	String status;
	String aladdinCode;
	String approvedBy;
	String operation;
	String decision;

	@OneToMany(mappedBy = "counterParty", cascade = CascadeType.ALL)
	List<MarketData> marketDatas;

	@OneToMany(mappedBy = "counterParty", cascade = CascadeType.ALL)
	List<MarketRating> marketRatings;

	@OneToMany(mappedBy = "counterParty", cascade = CascadeType.ALL)
	List<CounterPartyLimit> limits;

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

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public List<MarketData> getMarketDatas() {
		return marketDatas;
	}

	public void setMarketDatas(List<MarketData> marketDatas) {
		this.marketDatas = marketDatas;
	}

	public List<MarketRating> getMarketRatings() {
		return marketRatings;
	}

	public void setMarketRatings(List<MarketRating> marketRatings) {
		this.marketRatings = marketRatings;
	}

	public List<CounterPartyLimit> getLimits() {
		return limits;
	}

	public void setLimits(List<CounterPartyLimit> limits) {
		this.limits = limits;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aladdinCode == null) ? 0 : aladdinCode.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CounterParty other = (CounterParty) obj;
		if (aladdinCode == null) {
			if (other.aladdinCode != null)
				return false;
		} else if (!aladdinCode.equals(other.aladdinCode))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CounterParty [id=" + id + ", name=" + name + ", status=" + status + ", aladdinCode=" + aladdinCode
				+ ", approvedBy=" + approvedBy + ", operation=" + operation + ", decision=" + decision + "]";
	}


}

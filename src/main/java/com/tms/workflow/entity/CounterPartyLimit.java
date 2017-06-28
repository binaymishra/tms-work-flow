package com.tms.workflow.entity;

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
//@Access(AccessType.FIELD)
@Table(name = "tt_cpty_limits")
public class CounterPartyLimit {


	Long id;
	Long limits;
	String cpLimitType;
	String status;
	String approvedBy;

	CounterParty counterParty;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLimits() {
		return limits;
	}

	public void setLimits(Long limits) {
		this.limits = limits;
	}

	public String getCpLimitType() {
		return cpLimitType;
	}

	public void setCpLimitType(String cpLimitType) {
		this.cpLimitType = cpLimitType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
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

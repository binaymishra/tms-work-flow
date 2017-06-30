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
@Table(name = "tt_cpty_limits")
public class CounterPartyLimit {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	Long limits;
	String cpLimitType;
	String status;
	String approvedBy;
	String operation;
	String decision;

	@JsonIgnore //breaking infinite recursion
	@ManyToOne
	@JoinColumn(name = "cp_id")
	CounterParty counterParty;

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

	public CounterParty getCounterParty() {
		return counterParty;
	}

	public void setCounterParty(CounterParty counterParty) {
		this.counterParty = counterParty;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		CounterPartyLimit other = (CounterPartyLimit) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CounterPartyLimit [id=" + id + ", limits=" + limits + ", cpLimitType=" + cpLimitType + ", status="
				+ status + ", approvedBy=" + approvedBy + ", operation=" + operation + ", decision=" + decision + "]";
	}


}

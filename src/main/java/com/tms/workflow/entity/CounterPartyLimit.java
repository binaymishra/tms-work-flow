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

import lombok.Data;

@Data
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
	Boolean enable;

	@JsonIgnore //breaking infinite recursion
	@ManyToOne
	@JoinColumn(name = "cp_id")
	CounterParty counterParty;
}

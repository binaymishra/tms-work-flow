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
@Table(name = "tt_cpty_rating")
public class MarketRating {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	String ratingType;
	String fitchRating;
	String sadnpRating;
	String moodyRating;

	@JsonIgnore //breaking infinite recursion
	@ManyToOne
	@JoinColumn(name = "cp_id")
	CounterParty counterParty;

}

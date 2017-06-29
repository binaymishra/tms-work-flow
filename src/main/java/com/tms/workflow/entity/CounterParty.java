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

import lombok.Data;

@Data
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
	Boolean enable;

	@OneToMany(mappedBy = "counterParty", cascade = CascadeType.ALL)
	List<MarketData> marketDatas;

	@OneToMany(mappedBy = "counterParty", cascade = CascadeType.ALL)
	List<MarketRating> marketRatings;

	@OneToMany(mappedBy = "counterParty", cascade = CascadeType.ALL)
	List<CounterPartyLimit> limits;
}

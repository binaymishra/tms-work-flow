package com.tms.workflow.repo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tms.workflow.entity.CounterParty;

@Repository("counterPartyRevisionRepo")
@Transactional
public class CounterPartyRevisionRepository {

	@PersistenceContext
    private EntityManager entityManager;

	@Autowired
	CounterPartyRepository repository;

	public List<CounterParty> finCounterPartyRevisions(Long id){
		AuditReader auditReader = AuditReaderFactory.get(entityManager);
		List<Number> revisions = auditReader.getRevisions(CounterParty.class, id);
		List<CounterParty> cpRevisions = new ArrayList<CounterParty>();
		for (Number revision : revisions) {
			CounterParty cp = auditReader.find(CounterParty.class, id, revision);
			cpRevisions.add(cp);
		}
		return cpRevisions;
	}


}

package com.tms.workflow.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tms.workflow.entity.CounterParty;
import com.tms.workflow.repo.CounterPartyRevisionRepository;

/**
 * @author Binay Mishra
 *
 */
@Component
public class CounterPartyServiceHelper {

	static final ExecutorService executor = Executors.newFixedThreadPool(1);

	private CounterPartyRevisionRepository revisionRepo;

	@Autowired
	public CounterPartyServiceHelper(CounterPartyRevisionRepository revisionRepo) {
		this.revisionRepo = revisionRepo;
	}

	@Transactional(readOnly = true)
	public void generateApproveResponse(Long id, final Map<String, CounterParty> map) {
		List<CounterParty> revisions = revisionRepo.finCounterPartyRevisions(id);
		map.put("pending", revisions.get(revisions.size() - 1));
	}

	@Transactional(readOnly = true)
	public void generateUpdateResponse(Long id, Map<String, CounterParty> map) {
		List<CounterParty> revisions = revisionRepo.finCounterPartyRevisions(id);
		map.put("lastApproved", revisions.get(revisions.size() - 1));

	}



}

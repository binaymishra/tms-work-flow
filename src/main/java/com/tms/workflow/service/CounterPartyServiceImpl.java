package com.tms.workflow.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.tms.workflow.entity.CounterParty;
import com.tms.workflow.entity.CounterPartyLimit;
import com.tms.workflow.entity.MarketData;
import com.tms.workflow.entity.MarketRating;
import com.tms.workflow.repo.CounterPartyRepository;

@Service("counterPartyService")
public class CounterPartyServiceImpl implements CounterPartyService {

	private CounterPartyServiceHelper helper;

	private CounterPartyRepository repo;


	@Autowired
	public CounterPartyServiceImpl(CounterPartyRepository repo, CounterPartyServiceHelper helper) {
		this.helper = helper;
		this.repo = repo;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<CounterParty> findOne(final Long id) {
		return Optional.ofNullable(repo.findOneCounterPartyById(id));
	}

	@Override
	@Transactional(readOnly = true)
	public List<CounterParty> findAll(){
		List<CounterParty> counterParties = Collections.emptyList();
		counterParties = repo.findAll();
		return counterParties;

	}

	@Override
	@Transactional(readOnly = true)
	public List<CounterParty> findAll(String status){
		List<CounterParty> counterParties = Collections.emptyList();
		counterParties = repo.findBystatus(status);
		return counterParties;
	}

	@Override
	@Transactional(noRollbackFor = Throwable.class)
	public Optional<CounterParty> add(CounterParty counterParty) {
		List<CounterPartyLimit> limits = counterParty.getLimits();
		List<MarketData> marketDatas = counterParty.getMarketDatas();
		List<MarketRating> marketRatings = counterParty.getMarketRatings();

		counterParty.setStatus("inactive");
		counterParty.setDecision("pending");
		counterParty.setOperation("created");

		final CounterParty cp = repo.save(counterParty); // saving counterparty
															// data.

		if (!CollectionUtils.isEmpty(limits))
			limits.forEach(limit -> {
				limit.setCounterParty(cp);
				// at the time of add 'fund_treasury_limit' has same status,
				// decision, operation as counter party.
				limit.setStatus(cp.getStatus());
				limit.setDecision(cp.getDecision());
				limit.setOperation(cp.getOperation());
			});

		if (!CollectionUtils.isEmpty(marketDatas))
			marketDatas.forEach(marketData -> {
				marketData.setCounterParty(cp);
			});

		if (!CollectionUtils.isEmpty(marketRatings))
			marketRatings.forEach(ratings -> {
				ratings.setCounterParty(cp);
			});

		counterParty.setLimits(limits);
		counterParty.setMarketDatas(marketDatas);
		counterParty.setMarketRatings(marketRatings);
		counterParty = repo.save(counterParty);
		return Optional.ofNullable(counterParty);

	}

	@Override
	@Transactional(noRollbackFor = Throwable.class)
	public Map<String, CounterParty> approve(CounterParty cp) {
		CounterParty counterParty = repo.findOneCounterPartyById(cp.getId());
		// Approve counter party
		counterParty.setStatus(cp.getStatus());
		counterParty.setDecision(cp.getDecision());
		counterParty.setOperation(cp.getOperation());
		counterParty.setApprovedBy(cp.getApprovedBy());
		counterParty.getLimits().forEach(limit -> {
			limit.setStatus(cp.getStatus());
			limit.setDecision(cp.getDecision());
			limit.setOperation(cp.getOperation());
			limit.setApprovedBy(cp.getApprovedBy());
		});
		counterParty = repo.save(counterParty);
		final Map<String, CounterParty> map = new HashMap<>(2);
		map.put("approved", counterParty);
		helper.generateApproveResponse(counterParty.getId(), map);
		return map;

	}

	@Override
	@Transactional(noRollbackFor = Throwable.class)
	public Map<String, CounterParty> update(final CounterParty counterParty) {
		CounterParty existingCp = repo.findOneCounterPartyById(counterParty.getId());

		List<CounterPartyLimit> limits 		= existingCp.getLimits();

		existingCp.setStatus("inactive");
		existingCp.setDecision("pending");
		existingCp.setOperation("updated");

		existingCp.setName(counterParty.getName());
		existingCp.setApprovedBy(null);
		final CounterParty cp = repo.save(existingCp); // saving counterparty
															// data.

		if (!CollectionUtils.isEmpty(limits))
			limits.forEach(limit -> {
				limit.setCounterParty(cp);
				counterParty.getLimits().forEach(l -> {
					if("fund_treasury_limit".equalsIgnoreCase(l.getCpLimitType()))
						limit.setLimits(l.getLimits());
				});
				// at the time of update 'fund_treasury_limit' has same status,
				// decision, operation as counter party.
				limit.setStatus(cp.getStatus());
				limit.setDecision(cp.getDecision());
				limit.setOperation(cp.getOperation());
				limit.setApprovedBy(cp.getApprovedBy());
			});


		existingCp.setLimits(limits);

		CounterParty saved = repo.save(existingCp); // save ratings
		final Map<String, CounterParty> map = new HashMap<>(2);
		map.put("pendingApproval", saved);
		helper.generateUpdateResponse(saved.getId(), map);
		return map;
	}

}

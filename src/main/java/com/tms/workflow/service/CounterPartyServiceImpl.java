package com.tms.workflow.service;

import java.util.Collections;
import java.util.List;
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
import com.tms.workflow.repo.CounterPartyRevisionRepository;

@Service("counterPartyService")
public class CounterPartyServiceImpl implements CounterPartyService {

	private CounterPartyRevisionRepository revisionRepo;

	private CounterPartyRepository repo;

	@Autowired
	public CounterPartyServiceImpl(CounterPartyRevisionRepository revisionRepo, CounterPartyRepository repo) {
		this.revisionRepo = revisionRepo;
		this.repo = repo;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<CounterParty> findOne(final Long id) {
		return Optional.ofNullable(repo.findOne(id));
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
	public Optional<CounterParty> add(CounterParty counterParty){
	List<CounterPartyLimit> limits   = counterParty.getLimits();
	List<MarketData> marketDatas     = counterParty.getMarketDatas();
	List<MarketRating> marketRatings = counterParty.getMarketRatings();
	final CounterParty cp = repo.save(counterParty); // saving counterparty data.

		if(!CollectionUtils.isEmpty(limits))
			limits.forEach(limit -> {limit.setCounterParty(cp);});

		if(!CollectionUtils.isEmpty(marketDatas))
			marketDatas.forEach(marketData -> {marketData.setCounterParty(cp);});

		if(!CollectionUtils.isEmpty(marketRatings))
			marketRatings.forEach(ratings -> {ratings.setCounterParty(cp);});

	counterParty.setLimits(limits);
	counterParty.setMarketDatas(marketDatas);
	counterParty.setMarketRatings(marketRatings);

	counterParty = repo.save(counterParty);

	return Optional.ofNullable(counterParty);

	}


}

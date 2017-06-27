package com.tms.workflow.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tms.workflow.entity.MarketData;

@Repository("marketDataRepository")
public interface MarketDataRepository extends JpaRepository<MarketData, Long>{
	
	@Query("select m from MarketData m where m.id = id1 and m.counterParty = cpId2")
	MarketData findOneMarketDataForCounterParty(@Param("id") long id, @Param("cpId") long cpId);

}

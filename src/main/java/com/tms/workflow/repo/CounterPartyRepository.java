package com.tms.workflow.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tms.workflow.entity.CounterParty;

@Repository("counterPartyRepository")
public interface CounterPartyRepository extends JpaRepository<CounterParty, Long>{

	@Query("select cp from CounterParty cp where cp.status = ?1")
	List<CounterParty> findBystatus(String status);

	@Query("select cp from CounterParty cp where cp.id = ?1")
	CounterParty findOneCounterPartyById(Long id);

}

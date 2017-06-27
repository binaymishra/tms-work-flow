package com.tms.workflow.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tms.workflow.entity.CounterPartyLimit;

@Repository("counterPartyLimitRepository")
public interface CounterPartyLimitRepository extends JpaRepository<CounterPartyLimit, Long>{

}

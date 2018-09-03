package com.simcode.fps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simcode.fps.repository.model.Fees;

@Repository
public interface FeeRepository extends JpaRepository<Fees, Long> {
	
}

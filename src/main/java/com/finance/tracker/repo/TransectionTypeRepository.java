package com.finance.tracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finance.tracker.entity.TransectionType;

@Repository
public interface TransectionTypeRepository extends JpaRepository<TransectionType, Long>{

}

package com.finance.tracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finance.tracker.dto.TransectionCategoryDto;
import com.finance.tracker.entity.TransectionCategory;

@Repository
public interface TransectionCategoryRepository extends JpaRepository<TransectionCategory, Long>{

	
}

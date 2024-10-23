package com.nt.repo;



import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.model.Rule;

public interface RuleRepository extends JpaRepository<Rule, Long> {
}

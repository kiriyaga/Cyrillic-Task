package com.task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.task.beans.Farm;

public interface FarmRepository extends JpaRepository<Farm, Long>,JpaSpecificationExecutor<Farm> {

}

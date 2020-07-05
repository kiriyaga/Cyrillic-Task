package com.task.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.task.beans.Farm;
import com.task.repositories.FarmRepository;

@Service
public class FarmServiceImpl implements CrudService<Farm> {
	
	@Autowired
	FarmRepository farmRepository;

	@Override
	public List<Farm> getAll(Specification<Farm> specifications) {
		
		return farmRepository.findAll(Specification.where(specifications));
	}
}

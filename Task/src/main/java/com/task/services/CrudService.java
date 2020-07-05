package com.task.services;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.sipios.springsearch.anotation.SearchSpec;

public interface CrudService<T> {
	
	 List<T> getAll(@SearchSpec Specification<T> specs);
}

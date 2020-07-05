package com.task.controllers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sipios.springsearch.anotation.SearchSpec;
import com.task.beans.Farm;
import com.task.services.FarmServiceImpl;
import com.task.utils.logger.Messages;
import com.task.utils.logger.OperationEnum;

@Controller
@RequestMapping("farms")
public class FarmController {
	
	private static final Logger logger = LogManager.getLogger(FarmController.class);
	
	@Autowired
	FarmServiceImpl farmService;
	
	/**
	 * 
	 * A end-point used to receive inquiries concerning farms. 
	 * It is possible to send requests that will filter the return result. 
	 * Example of a query:
	 * /farms?search=id:1001
	 * @param specs - search criteria
	 * @return list of filtered farms
	 */
	@PreAuthorize("@securityService.hasProtectedAccess('USER_WHO_CAN_GET_ALL_FARMS_FROM_ALL_USERS')")
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllCustomers(@SearchSpec Specification<Farm> specs) {

		logger.info(Messages.getLoggerMessage(OperationEnum.Entered, FarmController.class));
		return new ResponseEntity<>(farmService.getAll(specs), HttpStatus.OK);
	}

}

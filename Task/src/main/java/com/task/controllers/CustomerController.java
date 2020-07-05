package com.task.controllers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sipios.springsearch.anotation.SearchSpec;
import com.task.beans.Customer;
import com.task.services.CustomerServiceImpl;
import com.task.utils.logger.Messages;
import com.task.utils.logger.OperationEnum;

@Controller
@RequestMapping("customers")
public class CustomerController {

	private static final Logger logger = LogManager.getLogger(CustomerController.class);

	@Autowired
	CustomerServiceImpl customerService;
	
	/**
	 * 
	 * A end-point used to receive inquiries concerning customers. 
	 * It is possible to send requests that will filter the return result. 
	 * Example of a query:
	 * /customer?search=firstName:'Admin' AND username:'admin'
	 * @param specs - search criteria
	 * @return list of filtered customers
	 */
	@PreAuthorize("@securityService.hasProtectedAccess('USER_WHO_CAN_GET_ALL_USERS')")
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllCustomers(@SearchSpec Specification<Customer> specs) {

		logger.info(Messages.getLoggerMessage(OperationEnum.Entered, UserController.class));
		return new ResponseEntity<>(customerService.getAll(specs), HttpStatus.OK);
	}
	
	/**
	 * A end-point used to return all customer accounts. 
	 * @return list of accounts
	 */
	@PreAuthorize("@securityService.hasProtectedAccess('USER_WHO_CAN_GET_ALL_ACCOUNTS')")
	@RequestMapping(method = RequestMethod.GET, value = "accounts", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllAccountsFromCustomer() {

		logger.info(Messages.getLoggerMessage(OperationEnum.Entered, UserController.class));
		return new ResponseEntity<>(
				customerService.getAccountsFromCustomer(
						SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()),
				HttpStatus.OK);
	}

	/**
	 * A end-point used to return all customer farms. 
	 * @return list of farms
	 */
	@PreAuthorize("@securityService.hasProtectedAccess('USER_WHO_CAN_GET_ALL_FARMS')")
	@RequestMapping(method = RequestMethod.GET, value = "accounts/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllFarmsFromCustomerAndAccount(@PathVariable  Long accountId) {
		
		logger.info(Messages.getLoggerMessage(OperationEnum.Entered, UserController.class));
		return new ResponseEntity<>(
				customerService.getFarmsFromCustomerAccount(
						SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString(), accountId),
				HttpStatus.OK);
	}

}

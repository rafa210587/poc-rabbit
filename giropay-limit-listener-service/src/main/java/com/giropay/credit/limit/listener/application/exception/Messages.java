package com.giropay.credit.limit.listener.application.exception;

/**
 * Interface that centralizes the messages used in the collection integration
 * 
 * @author victor.nakamatsu
 */
public interface Messages {

	// API
	// BUSINESS
	public static final String BUSINESS_ERROR_LIMIT_INQUIRER = "Error while Sending Message to Queue in the method (CreditLimitBusiness.sendMessage): ";

	// SERVICE
	public static final String SERVICE_ERROR_LIMIT_INQUIRER = "Error while Sending Message to Queue in the method (CreditLimitMessageProducerService.sendMessage): ";
}

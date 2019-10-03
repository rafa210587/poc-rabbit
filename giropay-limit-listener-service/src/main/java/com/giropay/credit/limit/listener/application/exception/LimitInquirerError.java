package com.giropay.credit.limit.listener.application.exception;


import comum.exception.handler.domain.GenericError;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Class representing the code and description of layers or integrations
 * @author rafael.goncalves
 */
@Getter
@AllArgsConstructor
public enum LimitInquirerError implements GenericError {

	ERROR_LIMIT_INQUIRER_BUSINESS("ERROR IN THE API (CREDIT INQUIRER SERVICES) ERROR IN THE BATCH BUSINESS LAYER"),

	ERROR_LIMIT_INQUIRER_SERVICE("ERROR IN THE API (CREDIT INQUIRER SERVICES) ERROR IN THE SERVICE LAYER");

	String errorDescription;

	@Override
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
}

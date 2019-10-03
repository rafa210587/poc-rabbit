package com.giropay.credit.limit.listener.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Properties with queue name and exchanges bindings
 * @author victor.nakamatsu
 */
@Configuration
@Data
public class ApplicationQueueProperties {

	@Value("${credit.limit.exchange.name}")
	private String creditLimitExchange;

	@Value("${credit.limit.queue.name}")
	private String approvedCreditLimitQueue;

	@Value("${credit.limit.routing.key}")
	private String approvedCreditLimitRoutingKey;

	@Value("${credit.limit.error.exchange.name}")
	private String errorCreditLimitExchange;

	@Value("${credit.limit.error.queue.name}")
	private String errorApprovedCreditLimitQueue;

	@Value("${credit.limit.error.routing.key}")
	private String errorApprovedCreditLimitRoutingKey;

}
package com.giropay.credit.limit.producer.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Properties with queue name and exchanges bindings
 * @author victor.nakamatsu
 */
@Configuration
@Data
public class MessageQueueProperties {

	@Value("${credit.limit.exchange.name}")
	private String creditLimitExchange;

	@Value("${credit.limit.queue.name}")
	private String creditLimitProcessingQueue;

	@Value("${credit.limit.routing.key}")
	private String creditLimitRoutingKey;

}
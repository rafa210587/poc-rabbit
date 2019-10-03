package com.giropay.credit.limit.listener.configuration;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
@AllArgsConstructor
public class RabbitConfig implements RabbitListenerConfigurer {

	private ApplicationQueueProperties applicationConfig;

	/* Creating a bean for the Message queue Exchange */
	@Bean
	public TopicExchange getCreditLimitAdminTopic() {
		return new TopicExchange(applicationConfig.getCreditLimitExchange());
	}
	/* Creating a bean for the Message queue Exchange */
	@Bean
	public TopicExchange getCreditLimitErrorAdminTopic() {
		return new TopicExchange(applicationConfig.getErrorCreditLimitExchange());
	}

	/* Creating a bean for the Message queue */
	@Bean
	public Queue getCreditLimitApprovedQueue() {
		return new Queue(applicationConfig.getApprovedCreditLimitQueue());
	}

	/* Creating a bean for the Message queue */
	@Bean
	public Queue getCreditLimitErrorQueue() {
		return new Queue(applicationConfig.getErrorApprovedCreditLimitQueue());
	}

	/* Binding between Exchange and Queue using routing key */
	@Bean
	public Binding declareBindingApprovedCreditLimit() {
		return BindingBuilder.bind(getCreditLimitApprovedQueue()).to(getCreditLimitAdminTopic())
				.with(applicationConfig.getApprovedCreditLimitRoutingKey());
	}

	/* Binding between error Exchange and Queue using routing key */
	@Bean
	public Binding declareBindingCreditLimitError() {
		return BindingBuilder.bind(getCreditLimitErrorQueue()).to(getCreditLimitErrorAdminTopic())
				.with(applicationConfig.getErrorApprovedCreditLimitRoutingKey());
	}

	@Bean
	public MappingJackson2MessageConverter jackson2MessageConverter() {
		return new MappingJackson2MessageConverter();
	}

	@Bean
	public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
		DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
		factory.setMessageConverter(jackson2MessageConverter());
		return factory;
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	/* Bean for rabbitTemplate */
	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		Message.addWhiteListPatterns("*");
		RetryTemplate retryTemplate = new RetryTemplate();
		ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
		backOffPolicy.setInitialInterval(3000);
		backOffPolicy.setMultiplier(10.0);
		backOffPolicy.setMaxInterval(30000);
		retryTemplate.setBackOffPolicy(backOffPolicy);
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		rabbitTemplate.setRetryTemplate(retryTemplate);
		return rabbitTemplate;
	}

	@Override
	public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
		registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
	}
}

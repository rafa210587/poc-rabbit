package com.giropay.credit.limit.producer.configuration;

import com.giropay.credit.limit.producer.GiropayCreditLimitProducerApplication;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration Class with exchanges and queues bindings and Message Configuration
 * @author victor.nakamatsu
 */
@Configuration
@AllArgsConstructor
public class RabbitConfig{

	private MessageQueueProperties applicationConfig;

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GiropayCreditLimitProducerApplication.class);
	}

	/* Creating a bean for the Message queue Exchange */
	@Bean
	public TopicExchange getCreditLimitAdminTopic() {
		return new TopicExchange(applicationConfig.getCreditLimitExchange());
	}

	/* Creating a bean for the Message queue */
	@Bean
	public Queue getCreditLimitProcessingQueue() {
		return new Queue(applicationConfig.getCreditLimitProcessingQueue());
	}

	/* Binding between Exchange and Queue using routing key */
	@Bean
	public Binding declareBindingProcessingCreditLimit() {
		return BindingBuilder.bind(getCreditLimitProcessingQueue()).to(getCreditLimitAdminTopic())
				.with(applicationConfig.getCreditLimitRoutingKey());
	}

	/* Bean for rabbitTemplate */
	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		Message.addWhiteListPatterns("*");
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
}

package com.srm.giropay.rabbitmq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Message sender to send message to queue using exchange.
 * 
 * @author rafael.meira *
 */

@Service
@Slf4j
public class MessageSender {

	/**
	 * 
	 * @param rabbitTemplate
	 * @param exchange
	 * @param routingKey
	 * @param data
	 */
	public void sendMessage(RabbitTemplate rabbitTemplate, String exchange, String routingKey, Object data) {
		log.info("Sending message to the queue using routingKey {}. Message= {}", routingKey, data);
		rabbitTemplate.convertAndSend(exchange, routingKey, data);
		log.info("The message has been sent to the queue.");
	}

	public void sendMessagePublicCorpus(RabbitTemplate rabbitTemplate, String exchange, String routingKey, String url,
			Object data) {
		log.info("Sending message to the queue using routingKey {}.Url={}. Message= {}", routingKey, url, data);
		rabbitTemplate.convertAndSend(exchange, routingKey, data);
		log.info("The message has been sent to the queue.");
	}

}

package com.giropay.credit.limit.producer.application.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Message sender to send message to queue using exchange.
 * 
 * @author victor.nakamatsu
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
		log.debug("Sending message to the queue using routingKey {}. Message= {}", routingKey, data);
		rabbitTemplate.convertAndSend(exchange, routingKey, data);
	}

	public void sendMessageWithHeader(RabbitTemplate rabbitTemplate, String exchange, String routingKey, Object data, HashMap<?,?> headers) {
		log.debug("Sending message to the queue using routingKey {}. Message= {}", routingKey, data);
		rabbitTemplate.convertAndSend(exchange, routingKey, data, m -> {
			headers.forEach((k,v) -> m.getMessageProperties().getHeaders().put(String.valueOf(k),v));
			return m;
		});
	}

	public void sendMessagePublicCorpus(RabbitTemplate rabbitTemplate, String exchange, String routingKey, String url,
			Object data) {
		log.info("Sending message to the queue using routingKey {}.Url={}. Message= {}", routingKey, url, data);
		rabbitTemplate.convertAndSend(exchange, routingKey, data);
		log.info("The message has been sent to the queue.");
	}

}

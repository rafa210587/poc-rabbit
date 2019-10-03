package com.giropay.credit.limit.producer.application.service;

import com.giropay.credit.limit.producer.application.exception.LimitInquirerError;
import com.giropay.credit.limit.producer.application.exception.Messages;
import com.giropay.credit.limit.producer.configuration.MessageQueueProperties;
import com.giropay.credit.limit.producer.configuration.util.Constants;
import com.giropay.credit.limit.producer.domain.dto.CarrierLimit;
import com.giropay.credit.limit.producer.domain.message.CarrierLimitMessage;
import comum.exception.handler.layer.ServiceException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Service to communicate with BI's RabbitMQ Queue and Send client's infos to be analised if how many Credit Limit is
 * possible for him.
 *
 * @author victor.nakamatsu
 */
@Service
@AllArgsConstructor
@Slf4j
public class CreditLimitMessageProducerService {
    RabbitTemplate rabbitTemplate;
    MessageQueueProperties messageQueueProperties;
    MessageSender messageSender;

    public ResponseEntity<?> sendMessage (CarrierLimit carrierLimit){

        try{
            HashMap<String,Integer> header = new HashMap<>();
            header.put("RETRY-COUNT", 0);
            messageSender.sendMessageWithHeader(rabbitTemplate,
                    messageQueueProperties.getCreditLimitExchange(),
                    messageQueueProperties.getCreditLimitRoutingKey(),
                    toMessage(carrierLimit),
                    header);
        }catch (Exception e){
            log.error("Error while Sending Message to Queue. " + e.getMessage());
            throw new ServiceException(
                    new StringBuilder(Messages.SERVICE_ERROR_LIMIT_INQUIRER).append(e.getMessage()).toString(),
                    LimitInquirerError.ERROR_LIMIT_INQUIRER_SERVICE, e);
        }
        return new ResponseEntity<>(Constants.IN_QUEUE, HttpStatus.OK);
    }

    private CarrierLimitMessage toMessage(CarrierLimit carrierLimit){
        CarrierLimitMessage message = new CarrierLimitMessage(
                carrierLimit.getDocument(),
                carrierLimit.getProduct());
        return message;
    }
}

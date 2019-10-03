package com.giropay.credit.limit.listener.application.service;

import com.giropay.credit.limit.listener.application.business.CreditLimitBusiness;
import com.giropay.credit.limit.listener.configuration.ApplicationQueueProperties;
import com.giropay.credit.limit.listener.domain.dto.ApprovedCarrierLimit;
import com.giropay.credit.limit.listener.domain.message.ApprovedCarrierLimitMessage;
import comum.exception.handler.layer.ServiceException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

/**
 * Service to communicate with BI's RabbitMQ Queue and Send client's infos to be analysed ,how many Credit Limit is
 * approved for him.
 *
 * @author victor.nakamatsu
 */
@Service
@AllArgsConstructor
@Slf4j
public class ApprovedCreditLimitMessageService {
    ApplicationQueueProperties applicationQueueProperties;
    MessageSender messageSender;
    RabbitTemplate rabbitTemplate;
    CreditLimitBusiness creditLimitBusiness;

    /**
     * If an ServiceException is thrown, then the message will be sent to Troubleshooting exchange with
     * credit.limit routing key.
     * @param approvedCarrierLimitMessage
     */
    @RabbitListener(queues = "${credit.limit.queue.name}")
    public void processedCreditLimitListener(ApprovedCarrierLimitMessage approvedCarrierLimitMessage){
        log.debug("Retrieving messages from " + "${credit.limit.queue.name}");
        log.debug("retrived message: " + approvedCarrierLimitMessage.toString());
        ApprovedCarrierLimit approvedCarrierLimit = toDTO(approvedCarrierLimitMessage);

    }

    private ApprovedCarrierLimit toDTO(ApprovedCarrierLimitMessage approvedCarrierLimitMessage){
        ApprovedCarrierLimit dto = new ApprovedCarrierLimit(
                approvedCarrierLimitMessage.getDocument(),
                approvedCarrierLimitMessage.getLimitValue());
        return dto;
    }
}

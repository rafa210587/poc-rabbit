package com.giropay.credit.limit.listener.application.business;

import com.giropay.credit.limit.listener.application.service.MessageSender;
import com.giropay.credit.limit.listener.configuration.ApplicationQueueProperties;
import com.giropay.credit.limit.listener.configuration.util.Constants;
import com.giropay.credit.limit.listener.domain.dto.ApprovedCarrierLimit;
import com.giropay.credit.limit.listener.domain.message.ApprovedCarrierLimitMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Business rule that communicate with BI Credit Limit Queue using AMQP.
 * @author victor.nakamatsu
 */
@AllArgsConstructor
@Component
@Slf4j
public class CreditLimitBusiness {
    MessageSender messageSender;
    RabbitTemplate rabbitTemplate;
    ApplicationQueueProperties applicationQueueProperties;

    public ResponseEntity<?> sendCreditLimitMessage(ApprovedCarrierLimitMessage approvedCarrierLimit) {
        try{
            log.info("SENDING MESSAGE TO PROCESSED CREDIT LIMIT QUEUE.");
            messageSender.sendMessage(rabbitTemplate,
                    applicationQueueProperties.getCreditLimitExchange(),
                    applicationQueueProperties.getApprovedCreditLimitRoutingKey(),
                    approvedCarrierLimit);
        }catch (Exception e){
            log.error("Error while sending message to queue");
            throw e;
        }
        return ResponseEntity.ok(Constants.IN_QUEUE);
    }

    public void persistCreditLimit(ApprovedCarrierLimit approvedCarrierLimit){

    }
}

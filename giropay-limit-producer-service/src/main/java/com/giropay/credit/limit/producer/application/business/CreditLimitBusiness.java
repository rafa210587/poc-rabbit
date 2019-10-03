package com.giropay.credit.limit.producer.application.business;

import com.giropay.credit.limit.producer.application.exception.LimitInquirerError;
import com.giropay.credit.limit.producer.application.exception.Messages;
import com.giropay.credit.limit.producer.application.service.CreditLimitMessageProducerService;
import com.giropay.credit.limit.producer.domain.dto.CarrierLimit;
import comum.exception.handler.layer.BusinessException;
import comum.exception.handler.layer.ServiceException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Business rule that communicate with BI Credit Limit Queue using AMQP.
 * @author victor.nakamatsu
 */
@AllArgsConstructor
@Component
public class CreditLimitBusiness {
    private CreditLimitMessageProducerService creditLimitMessageProducerService;

    public ResponseEntity<?> sendMessage(CarrierLimit carrierLimit){
        try {
            return creditLimitMessageProducerService.sendMessage(carrierLimit);
        }catch (ServiceException e){
            throw e;
        }catch (Exception e){
            throw new BusinessException(
                    new StringBuilder(Messages.BUSINESS_ERROR_LIMIT_INQUIRER).append(e.getMessage()).toString(),
                    LimitInquirerError.ERROR_LIMIT_INQUIRER_BUSINESS, e);
        }
    }
}

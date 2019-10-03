package com.giropay.credit.limit.producer.controller.limit;

import com.giropay.credit.limit.producer.application.business.CreditLimitBusiness;
import com.giropay.credit.limit.producer.domain.dto.CarrierLimit;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/giropay-limit-inquirer/v1/limit")
@AllArgsConstructor
@Api(value = "Service to integrate with Credit Limit BI Queue")
public class CreditLimitController {

    private CreditLimitBusiness creditLimitBusiness;

    @PostMapping("/bi-queue-producer")
    public ResponseEntity<?> sendMessage(@RequestBody CarrierLimit carrierLimit){
        return creditLimitBusiness.sendMessage(carrierLimit);
    }
}

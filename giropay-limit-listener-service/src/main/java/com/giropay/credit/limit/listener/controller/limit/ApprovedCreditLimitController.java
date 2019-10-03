package com.giropay.credit.limit.listener.controller.limit;

import com.giropay.credit.limit.listener.application.business.CreditLimitBusiness;
import com.giropay.credit.limit.listener.domain.dto.ApprovedCarrierLimit;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/giropay-limit-inquirer/v1/limit")
@AllArgsConstructor
@Api(value = "Service to integrate with Credit Limit BI Queue")
public class ApprovedCreditLimitController {
    private CreditLimitBusiness creditLimitBusiness;


    @PostMapping("/manual-approve")
    public ResponseEntity<?> sendMessage(@RequestBody ApprovedCarrierLimit approvedCarrierLimit){
        return creditLimitBusiness.sendCreditLimitMessage(approvedCarrierLimit);

    }
}

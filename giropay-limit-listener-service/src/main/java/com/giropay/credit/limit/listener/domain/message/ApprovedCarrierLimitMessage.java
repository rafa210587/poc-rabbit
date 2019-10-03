package com.giropay.credit.limit.listener.domain.message;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ApprovedCarrierLimitMessage implements Serializable {

    private String document;

    private BigDecimal limitValue;
}

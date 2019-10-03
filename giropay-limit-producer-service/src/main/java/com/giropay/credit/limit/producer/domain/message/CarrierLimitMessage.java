package com.giropay.credit.limit.producer.domain.message;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CarrierLimitMessage implements Serializable {

    private String document;

    private String productName;
}

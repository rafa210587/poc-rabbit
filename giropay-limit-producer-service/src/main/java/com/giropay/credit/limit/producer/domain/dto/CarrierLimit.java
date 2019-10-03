package com.giropay.credit.limit.producer.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CarrierLimit implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Document - CPF/CNPJ")
    private String document;

    @ApiModelProperty(value = "Product Code")
    private String product;

}

package com.giropay.credit.limit.listener.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovedCarrierLimit{

    @ApiModelProperty(value = "Document - CPF/CNPJ")
    @JsonProperty("document")
    private String document;

    @ApiModelProperty(value = "Approved Limit")
    @JsonProperty("limitValue")
    private BigDecimal limitValue;

}

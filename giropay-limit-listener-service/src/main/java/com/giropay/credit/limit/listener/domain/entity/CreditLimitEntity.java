package com.giropay.credit.limit.listener.domain.entity;

import com.giropay.credit.limit.listener.domain.constants.CreditLimitStatus;
import lombok.Data;
import lombok.NonNull;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.Date;

@Data
@Valid
public class CreditLimitEntity {

	private Long id;

	@NonNull
	private Long document;

	@NonNull
	private BigInteger originalValue;

	@NonNull
	private BigInteger value;

	@NonNull
	private CreditLimitStatus idStatusLimit;

	private Date dateInclusion;
	private Date dateExclusion;

}

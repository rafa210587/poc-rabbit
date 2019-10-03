package com.giropay.credit.limit.listener.domain.constants;

import lombok.Getter;

@Getter
public enum CreditLimitStatus {

	ACTIVE(1l), IN_ANALYSIS(2l);

	private Long id;

	CreditLimitStatus(Long id) {
		this.id = id;
	}

	public static CreditLimitStatus valueOf(Long idStatusLimit) {
		for (CreditLimitStatus status : values()) {
			if (status.getId() == idStatusLimit) {
				return status;
			}
		}

		return null;
	}

}

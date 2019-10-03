package com.srm.giropay.rabbitmq.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class UserDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cpf;

	private String productName;

}

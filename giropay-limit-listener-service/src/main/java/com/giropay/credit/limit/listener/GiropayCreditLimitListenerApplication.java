package com.giropay.credit.limit.listener;

import comum.exception.handler.handler.ExceptionConfig;
import io.swagger.annotations.Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

/**
 * Main class credit-limit-inquirer
 *
 * @author victor.nakamatsu
 */
@Api(value = "BI's Queue Integration Service")
@SpringBootApplication
@Import({ ExceptionConfig.class})
@EnableConfigurationProperties
@EnableDiscoveryClient
public class GiropayCreditLimitListenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GiropayCreditLimitListenerApplication.class, args);
	}
	
}
